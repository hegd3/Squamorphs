package com.hedge.squamorphs.entity.squamorphparts.legs;

import com.hedge.squamorphs.client.animations.SquamorphAbilityAnimation;
import com.hedge.squamorphs.client.animations.squamorphAnimation;
import com.hedge.squamorphs.entity.living.SquamorphEntity;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class SquamorphCubicLeg extends SquamorphLeg {

    public SquamorphCubicLeg(int index, int cooldown, String name) {
        super(index, cooldown, name);
    }

    @Override
    public AnimationDefinition getWalk() {
        return squamorphAnimation.cubic_walk;
    }

    @Override
    public AnimationDefinition getIdle() {
        return squamorphAnimation.cubic_idle;
    }

    @Override
    public float getHeight() {
        return 19.9f;
    }

    @Override
    public boolean hasMelee() {
        return true;
    }

    @Override
    public AnimationDefinition getAbilityAnim(SquamorphEntity owner) {
        return SquamorphAbilityAnimation.stomp;
    }

    @Override
    public void tickAttack(SquamorphEntity entity, int animTicks, LivingEntity target, double dist) {
        if (animTicks == 13) {
            this.performMeleeAttack(entity, target, dist);
        } else if (animTicks >= 17) {
            entity.addCooldowns();
            entity.setLegCD(this.getCooldown());
            entity.setAttackState(0);
            entity.resetMove();
        }
    }

    @Override
    public void performMeleeAttack(SquamorphEntity owner, LivingEntity target, double dist) {
        owner.aoeAttack(0.0, 3.0, 1.0, 3.0, this.getDamage(owner), 2f, 20, owner.getPrimaryElement(), owner.getLegsLevel());
    }

    @Override
    public boolean isImmobileWhenUsing(SquamorphEntity entity) {
        return true;
    }

    @Override
    public boolean canUseAbility(SquamorphEntity owner, LivingEntity target) {
        return owner.getLegAbilityCD() <= 0 && owner.getPerceivedTargetDistanceSquareForMeleeAttack(target) <= owner.getBbWidth() * 3F * owner.getBbWidth() * 3F + target.getBbWidth();
    }

    @Override
    public float getAnimSpeed() {
        return 4f;
    }

    @Override
    public float getDamage(SquamorphEntity owner) {
        return (float)owner.getAttribute(Attributes.ATTACK_DAMAGE).getValue() * 3f + (float)Math.pow(owner.getLegsLevel(), 2.3) * 2;
    }
}
