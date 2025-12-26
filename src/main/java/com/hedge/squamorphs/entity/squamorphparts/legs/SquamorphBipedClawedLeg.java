package com.hedge.squamorphs.entity.squamorphparts.legs;

import com.hedge.squamorphs.client.animations.squamorphAnimation;
import com.hedge.squamorphs.entity.living.SquamorphEntity;
import com.hedge.squamorphs.entity.util.EntityHelpers;
import jdk.jfr.Percentage;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class SquamorphBipedClawedLeg extends SquamorphBipedLeg {

    public SquamorphBipedClawedLeg(int index, int cooldown, String name) {
        super(index, cooldown, name);
    }


    @Override
    public int getColor(SquamorphEntity entity) {
        return entity.getSecondaryColor();
    }

    @Override
    public boolean hasMelee() {
        return true;
    }

    @Override
    public boolean canUseAbility(SquamorphEntity owner, LivingEntity target) {
        return owner.getLegAbilityCD() <= 0 && owner.getPerceivedTargetDistanceSquareForMeleeAttack(target) <= owner.getBbWidth() * 3F * owner.getBbWidth() * 3F + target.getBbWidth() && Math.abs(owner.getY() - target.getY()) < 3;
    }

    @Override
    public void tickAttack(SquamorphEntity entity, int animTicks, LivingEntity target, double dist) {
        if (animTicks == 6) {
            entity.setDeltaMovement(entity.getDeltaMovement().add(entity.getLookAngle().scale(1.3)));
        }
        else if (animTicks == 8) {
            this.performMeleeAttack(entity, target, dist);
        } else if (animTicks >= 14) {
            entity.addCooldowns();
            entity.setLegCD(this.getCooldown());
            entity.setAttackState(0);
        }
    }

    @Override
    public void performMeleeAttack(SquamorphEntity owner, LivingEntity target, double dist) {
        double d0 = owner.getMeleeAttackRangeSqr(target) * 1.2;
        if (dist <= d0) {
            owner.swing(InteractionHand.MAIN_HAND);
            owner.betterDoHurt(target, this.getDamage(owner), 1.0f);
            owner.getSecondaryElement().applyElement(target, owner, 1, 5);
            EntityHelpers.particleOnhitEffect(owner.getSecondaryElement().getParticle(), target, owner.level(), 1);
            owner.setAttackDirection(!owner.isAttackingLeft());
        }
    }

    @Override
    public AnimationDefinition getAbilityAnim(SquamorphEntity owner) {
        if (owner.isAttackingLeft()) return squamorphAnimation.claw_slash_left;
        return squamorphAnimation.claw_slash_right;
    }

    @Override
    public float getDamage(SquamorphEntity owner) {
        return (float)owner.getAttribute(Attributes.ATTACK_DAMAGE).getValue() * 1.5f;
    }
}
