package com.hedge.squamorphs.entity.util.goals;

import com.hedge.squamorphs.entity.squamorphparts.body.SquamorphWings;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.util.AirAndWaterRandomPos;
import net.minecraft.world.entity.ai.util.HoverRandomPos;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.EnumSet;

public class GenericFlyingGoal extends Goal {

    private int ticksFlying;
    private final PathfinderMob entity;

    public GenericFlyingGoal(PathfinderMob entity) {
        this.entity = entity;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));
    }


    @Override
    public void tick() {
        super.tick();
        ticksFlying++;
    }

    @Override
    public boolean canUse() {
        return entity.getNavigation().isDone() && entity.getRandom().nextInt(30) == 0;
    }


    @Override
    public boolean canContinueToUse() {
        if (ticksFlying > 20 && (entity.onGround() || entity.isInFluidType())) {
            return false;
        }
        return entity.getNavigation().isInProgress();
    }

    @Override
    public void start() {
        Vec3 vec3 = this.findPos();
        if (vec3 != null) {
            entity.getNavigation().moveTo(entity.getNavigation().createPath(BlockPos.containing(vec3), 1), 1.0D);
            this.ticksFlying = 0;
        }

    }

    @Nullable
    private Vec3 findPos() {
        Vec3 vec3;
        vec3 = entity.getViewVector(0.0F);

        Vec3 vec32 = HoverRandomPos.getPos(entity, 15, 7, vec3.x, vec3.z, ((float)Math.PI / 2F), 7, 2);
        return vec32 != null ? vec32 : AirAndWaterRandomPos.getPos(entity, 15, 7, -2, vec3.x, vec3.z, (double)((float)Math.PI / 2F));
    }
}
