package com.hedge.squamorphs.entity.util;

import net.minecraft.world.entity.LivingEntity;

public interface AttackStateEntity {

    public void setAttackState(int i);

    public int getAttackState();

    public double getAttackRangeSqr(LivingEntity target);

    public int getAttackCD();

    public void resetAttackCD();
}
