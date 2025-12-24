package com.hedge.squamorphs.entity.util.goals;

import com.hedge.squamorphs.entity.util.AttackStateEntity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.pathfinder.Path;

import java.util.EnumSet;

public class GenericMeleeGoal<E extends PathfinderMob & AttackStateEntity> extends Goal {
    private final E entity;
    private final double speedModifier;
    private final boolean followingTargetEvenIfNotSeen;
    private Path path;
    private int ticksUntilNextPathRecalculation;
    private long lastCanUseCheck;
    private boolean canPenalize = false;

    public GenericMeleeGoal(E entity, double speedModifier, boolean followingTargetEvenIfNotSeen) {
        this.entity = entity;
        this.speedModifier = speedModifier;
        this.followingTargetEvenIfNotSeen = followingTargetEvenIfNotSeen;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public void start() {
        this.entity.setAggressive(true);
        this.ticksUntilNextPathRecalculation = 0;
    }

    @Override
    public void stop() {
        LivingEntity livingentity = this.entity.getTarget();
        if (!EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(livingentity)) {
            this.entity.setTarget((LivingEntity)null);
        }

        this.entity.setAggressive(false);
        this.entity.getNavigation().stop();
    }

    @Override
    public void tick() {
        LivingEntity livingentity = this.entity.getTarget();
        if (livingentity != null) {
            double d0 = this.entity.getPerceivedTargetDistanceSquareForMeleeAttack(livingentity);
            this.entity.getNavigation().moveTo(livingentity, this.speedModifier);
            if (entity.getAttackCD() <= 0 && entity.getAttackState() == 0 && d0 <= entity.getAttackRangeSqr(livingentity)) {
                entity.setAttackState(1);
            }
        }
    }

        @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }




    @Override
    public boolean canUse() {
        long i = this.entity.level().getGameTime();
        if (i - this.lastCanUseCheck < 20L) {
            return false;
        } else {
            this.lastCanUseCheck = i;
            LivingEntity livingentity = this.entity.getTarget();
            if (livingentity == null) {
                return false;
            } else if (!livingentity.isAlive()) {
                return false;
            } else {
                if (canPenalize) {
                    if (--this.ticksUntilNextPathRecalculation <= 0) {
                        this.path = this.entity.getNavigation().createPath(livingentity, 0);
                        this.ticksUntilNextPathRecalculation = 4 + this.entity.getRandom().nextInt(7);
                        return this.path != null;
                    } else {
                        return true;
                    }
                }
                this.path = this.entity.getNavigation().createPath(livingentity, 0);
                if (this.path != null) {
                    return true;
                } else {
                    return entity.getMeleeAttackRangeSqr(livingentity) >= this.entity.distanceToSqr(livingentity.getX(), livingentity.getY(), livingentity.getZ());
                }
            }
        }
    }

    @Override
    public boolean canContinueToUse() {
        LivingEntity livingentity = this.entity.getTarget();
        if (livingentity == null) {
            return false;
        } else if (!livingentity.isAlive()) {
            return false;
        } else if (!this.followingTargetEvenIfNotSeen) {
            return !this.entity.getNavigation().isDone();
        } else if (!this.entity.isWithinRestriction(livingentity.blockPosition())) {
            return false;
        } else {
            return !(livingentity instanceof Player) || !livingentity.isSpectator() && !((Player)livingentity).isCreative();
        }
    }
}
