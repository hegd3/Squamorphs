package com.hedge.squamorphs.entity.squamorphparts;

import com.hedge.squamorphs.client.animations.squamorphAnimation;
import com.hedge.squamorphs.entity.living.SquamorphEntity;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.world.entity.LivingEntity;

public class SquamorphPart {

    private final int cooldown;
    private final int index;
    private final String name;
    public SquamorphPart(int index, int cooldown, String name) {
        this.cooldown = cooldown;
        this.index = index;
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void applyStats(SquamorphEntity owner) {
    }

    public void removeStats(SquamorphEntity owner) {

    }

    public int getColor(SquamorphEntity owner) {
        return owner.getPrimaryColor();
    }

    public int getIndex() {
        return this.index;
    }

    public void tick() {

    }

    public boolean canUseAbility(SquamorphEntity owner, LivingEntity target) {
        return false;
    }

    public void performRangedAttack(SquamorphEntity owner, LivingEntity pTarget) {
    }

    public void performMeleeAttack(SquamorphEntity owner, LivingEntity pTarget, double dist) {
    }

    public AnimationDefinition getAbilityAnim(SquamorphEntity owner) {return squamorphAnimation.mouth_shoot;}

    public int getAbilityAnimState() {return 0;}

    public int getCooldown() {
        return this.cooldown;
    }


    public boolean hasMelee() {
        return false;
    }

    public boolean hasRanged() {
        return false;
    }

    public void tickAttack(SquamorphEntity entity, int animTicks, LivingEntity target, double dist) {

    }

    public float getDamage(SquamorphEntity entity) {
        return 0;
    }




}
