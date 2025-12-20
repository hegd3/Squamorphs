package com.hedge.squamorphs.entity.util;


import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class GoalHelpers {

    @Nullable
    public static Vec3 getRandomFlyablePos(PathfinderMob mob, int radius, int verticalDistance) {

        Level level = mob.level();
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

        for (int i = 0; i < 30; i++) {
            double x = mob.getX() + (mob.getRandom().nextDouble() * 2 - 1) * radius;
            double y = mob.getY() + (mob.getRandom().nextDouble() * 2 - 1) * verticalDistance;
            double z = mob.getZ() + (mob.getRandom().nextDouble() * 2 - 1) * radius;

            pos.set(Mth.floor(x), Mth.floor(y), Mth.floor(z));

            if (isClearAirSpace(level, pos))
                return new Vec3(x, y, z);

        }
        return null;
    }

    private static boolean isClearAirSpace(Level level, BlockPos pos) {
        return level.getBlockState(pos).isAir();
    }

}
