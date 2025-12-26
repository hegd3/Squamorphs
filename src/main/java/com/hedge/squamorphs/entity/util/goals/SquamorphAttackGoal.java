package com.hedge.squamorphs.entity.util.goals;

import com.hedge.squamorphs.entity.living.SquamorphEntity;
import com.hedge.squamorphs.entity.squamorphparts.SquamorphPart;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.pathfinder.Path;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class SquamorphAttackGoal extends Goal {

    private final SquamorphEntity entity;
    private final RandomSource random;
    private final double speedModifier;
    private final boolean followingTargetEvenIfNotSeen;
    private Path path;
    private int ticksUntilNextPathRecalculation;
    private long lastCanUseCheck;
    private boolean canPenalize = false;

    private boolean strafingClockwise;
    private boolean strafingBackwards;
    private int strafingTime = -1;

    public SquamorphAttackGoal(SquamorphEntity entity, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
        this.entity = entity;
        this.speedModifier = pSpeedModifier;
        this.followingTargetEvenIfNotSeen = pFollowingTargetEvenIfNotSeen;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        this.random = entity.getRandom();
    }

    public void start() {
        this.entity.setAggressive(true);
        this.ticksUntilNextPathRecalculation = 0;
    }

    public boolean requiresUpdateEveryTick() {
        return true;
    }



    public void tick() {
        LivingEntity livingentity = this.entity.getTarget();
        if (livingentity != null) {
            double d0 = this.entity.getPerceivedTargetDistanceSquareForMeleeAttack(livingentity);
            boolean inRange = entity.distanceToSqr(livingentity) < this.getRangedAttackReachSqr(livingentity) && Math.abs(entity.getY() - livingentity.getY()) < 3;
            boolean inHeadRange = d0 < entity.getHead().getRange();
            if (inRange || !inHeadRange) {
                this.pathToTarget(livingentity);
            } else {
                this.strafingTick(d0, livingentity);
            }


            if (entity.getAttackState() == 0) {
                SquamorphPart currentAbility = selectPart(livingentity);
                if (currentAbility != null) {
                    entity.startAttackAnim(d0, livingentity, currentAbility.getAbilityAnimState());
                }
            }
        }
    }

    private void pathToTarget(LivingEntity livingentity) {
        if (this.strafingTime <= -1) {
            this.entity.getNavigation().stop();
        }
        this.entity.getNavigation().moveTo(livingentity, this.speedModifier);
    }

    private void strafingTick(double d0, LivingEntity target) {
        if (d0 < 400) {
            this.entity.getNavigation().stop();
            ++this.strafingTime;
        } else {
            this.strafingTime = -1;
        }

        if (this.strafingTime >= 20) {
            if ((double)this.random.nextFloat() < 0.3D) {
                this.strafingClockwise = !this.strafingClockwise;
            }

            if ((double)this.random.nextFloat() < 0.3D) {
                this.strafingBackwards = !this.strafingBackwards;
            }

            this.strafingTime = 0;
        }

        if (this.strafingTime > -1) {
            if (d0 > (400 * 0.75F)) {
                this.strafingBackwards = false;
            } else if (d0 < (400 * 0.25F)) {
                this.strafingBackwards = true;
            }

            this.entity.getMoveControl().strafe(this.strafingBackwards ? -1F : 1F, this.strafingClockwise ? 1F : -1F);
            Entity entity = this.entity.getControlledVehicle();
            if (entity instanceof Mob) {
                Mob mob = (Mob)entity;
                mob.lookAt(target, 30.0F, 30.0F);
            }

            this.entity.lookAt(target, 30.0F, 30.0F);
        }
        this.entity.getLookControl().setLookAt(target, 30.0F, 30.0F);
    }


    public void stop() {
        LivingEntity livingentity = this.entity.getTarget();
        if (!EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(livingentity)) {
            this.entity.setTarget((LivingEntity)null);
        }

        this.entity.setAggressive(false);
        this.entity.getNavigation().stop();
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
                    return this.getAttackReachSqr(livingentity) >= this.entity.distanceToSqr(livingentity.getX(), livingentity.getY(), livingentity.getZ());
                }
            }
        }
    }

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


    private double getAttackReachSqr(LivingEntity pAttackTarget) {
        return this.entity.getBbWidth() * 2.0F * this.entity.getBbWidth() * 2.0F + pAttackTarget.getBbWidth();
    }

    private double getRangedAttackReachSqr(LivingEntity pAttackTarget) {
        return this.entity.getBbWidth() * 8.0F * this.entity.getBbWidth() * 8.0F + pAttackTarget.getBbWidth();
    }

    @Nullable
    private SquamorphPart selectPart(LivingEntity target) {
        for (SquamorphPart part: this.entity.getMeleeParts()) {
            if (part.canUseAbility(this.entity, target))
                return part;
        }
        for (SquamorphPart part: this.entity.getRangedParts()) {
            if (part.canUseAbility(this.entity, target))
                return part;
        }
        return null;
    }
}
