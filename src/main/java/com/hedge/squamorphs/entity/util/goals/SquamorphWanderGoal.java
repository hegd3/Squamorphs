package com.hedge.squamorphs.entity.util.goals;

import com.hedge.squamorphs.entity.living.SquamorphEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.navigation.AmphibiousPathNavigation;
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;


public class SquamorphWanderGoal extends RandomStrollGoal {

    private final SquamorphEntity entity;

    public SquamorphWanderGoal(SquamorphEntity entity, double pSpeedModifier) {
        super(entity, pSpeedModifier, 20);
        this.entity = entity;
    }

    @Override
    public boolean canUse() {
        return isLandNav() && super.canUse();
    }

    @Override
    public boolean canContinueToUse() {
        return isLandNav() && super.canContinueToUse();
    }

    private boolean isLandNav() {
        return this.entity.getNavigation() instanceof AmphibiousPathNavigation;
    }

    @Nullable
    protected Vec3 getPosition() {
        return entity.isInFluidType() ? BehaviorUtils.getRandomSwimmablePos(this.mob, 10, 7) : super.getPosition();
    }

}
