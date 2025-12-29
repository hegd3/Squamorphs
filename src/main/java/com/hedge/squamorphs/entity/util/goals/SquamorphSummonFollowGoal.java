package com.hedge.squamorphs.entity.util.goals;

import com.hedge.squamorphs.entity.living.summons.SquamorphSummon;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.event.entity.living.LivingGetProjectileEvent;

import java.util.EnumSet;

public class SquamorphSummonFollowGoal extends Goal {

    private final SquamorphSummon entity;
    private int timeToRecalcPath;

    public SquamorphSummonFollowGoal(SquamorphSummon entity) {

        this.entity = entity;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));

    }



    @Override
    public boolean canUse() {
        LivingEntity owner = this.entity.getOwner();
        return owner != null && !this.unableToMove();
    }

    @Override
    public void start() {
        this.timeToRecalcPath = 0;
    }

    @Override
    public void stop() {
        this.entity.getNavigation().stop();
    }

    @Override
    public void tick() {
        LivingEntity owner = this.entity.getOwner();
        if (owner != null) {
            this.entity.getLookControl().setLookAt(owner, 10.0F, (float) this.entity.getMaxHeadXRot());
            if (--this.timeToRecalcPath <= 0) {
                this.timeToRecalcPath = this.adjustedTickDelay(10);
                this.entity.getNavigation().moveTo(owner, 1.0);

            }
        }
    }

    @Override
    public boolean canContinueToUse() {
        if (entity.getOwner() == null) {
            return false;
        }
        else if (entity.getNavigation().isDone()) {
            return false;
        } else if (this.unableToMove()) {
            return false;
        } else {
            return !(this.entity.distanceToSqr(this.entity.getOwner()) <= 300);
        }
    }

    private boolean unableToMove() {
        return this.entity.isAggressive() || this.entity.isPassenger() || this.entity.isLeashed();
    }
}

