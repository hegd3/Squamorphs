package com.hedge.squamorphs.entity.util;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class EntityHelpers {
    public static double angleFromEntity(Entity entity, Entity other) {
        Vec3 forward = Vec3.directionFromRotation(0, entity.getYRot()).normalize();
        Vec3 toOther = other.position().subtract(entity.position()).normalize();
        double dot = forward.dot(toOther);

        return dot;
    }

    public static boolean rightOfEntity(Entity entity, Entity other) {
        Vec3 forward = Vec3.directionFromRotation(0, entity.getYRot()).normalize();
        Vec3 toOther = other.position().subtract(entity.position()).normalize();

        double crossZ = forward.x * toOther.z - forward.z * toOther.x;

        return crossZ < 0;
    }

    public static void spawnParticles(Level level, ParticleOptions particle, double x, double y, double z, int count, double deltaX, double deltaY, double deltaZ, double speed, boolean force) {
        level.getServer().getPlayerList().getPlayers().forEach(player -> ((ServerLevel) level).sendParticles(player, particle, force, x, y, z, count, deltaX, deltaY, deltaZ, speed));
    }

    public static double getRandomScaled(double sc) {
        return (2.0D * Math.random() - 1.0D) * sc;
    }

    public static Vec3 getRandomVec3(double sc) {
        return new Vec3(getRandomScaled(sc), getRandomScaled(sc), getRandomScaled(sc));
    }

}
