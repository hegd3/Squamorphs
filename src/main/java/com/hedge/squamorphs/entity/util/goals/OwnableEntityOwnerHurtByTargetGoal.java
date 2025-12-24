package com.hedge.squamorphs.entity.util.goals;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;

import java.util.EnumSet;

public class OwnableEntityOwnerHurtByTargetGoal<E extends PathfinderMob & OwnableEntity> extends TargetGoal {

    private final E entity;
    private LivingEntity ownerLastHurtBy;
    private int timeStamp;

    public OwnableEntityOwnerHurtByTargetGoal(E entity, boolean pMustSee) {
        super(entity, pMustSee);
        this.entity = entity;
        this.setFlags(EnumSet.of(Flag.TARGET));
    }


    @Override
    public boolean canUse() {
        LivingEntity owner = this.entity.getOwner();
        if (owner == null) {
            return false;
        }

        this.ownerLastHurtBy = owner.getLastHurtMob();
        int i = owner.getLastHurtMobTimestamp();
        return i != this.timeStamp && this.canAttack(this.ownerLastHurtBy, TargetingConditions.DEFAULT);

    }

    public void start() {
        this.mob.setTarget(this.ownerLastHurtBy);
        LivingEntity livingentity = this.entity.getOwner();
        if (livingentity != null) {
            this.timeStamp = livingentity.getLastHurtMobTimestamp();
        }

        super.start();
    }
}
