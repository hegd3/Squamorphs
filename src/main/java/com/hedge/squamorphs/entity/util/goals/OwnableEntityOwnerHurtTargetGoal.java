package com.hedge.squamorphs.entity.util.goals;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;

import java.util.EnumSet;

public class OwnableEntityOwnerHurtTargetGoal<E extends PathfinderMob & OwnableEntity> extends TargetGoal {

    private final E entity;
    private LivingEntity ownerLastHurt;
    private int timeStamp;

    public OwnableEntityOwnerHurtTargetGoal(E entity, boolean pMustSee) {
        super(entity, pMustSee);
        this.entity = entity;
        this.setFlags(EnumSet.of(Goal.Flag.TARGET));
    }


    @Override
    public boolean canUse() {
        LivingEntity owner = this.entity.getOwner();
        if (owner == null) {
            return false;
        }

        this.ownerLastHurt = owner.getLastHurtByMob();
        int i = owner.getLastHurtMobTimestamp();
        return i != this.timeStamp && this.canAttack(this.ownerLastHurt, TargetingConditions.DEFAULT);

    }

    public void start() {
        this.mob.setTarget(this.ownerLastHurt);
        LivingEntity livingentity = this.entity.getOwner();
        if (livingentity != null) {
            this.timeStamp = livingentity.getLastHurtMobTimestamp();
        }

        super.start();
    }
}
