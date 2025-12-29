package com.hedge.squamorphs.entity.squamorphparts.mouth;

import com.hedge.squamorphs.client.animations.squamorphAnimation;
import com.hedge.squamorphs.entity.living.SquamorphEntity;
import com.hedge.squamorphs.entity.squamorphparts.SquamorphPart;
import com.hedge.squamorphs.entity.util.EntityHelpers;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class SquamorphMouth extends SquamorphPart {


    private final boolean hasInnerMouth;
    private final boolean hasTeeth;

    public SquamorphMouth(int index, int cooldown, String name, boolean hasInnerMouth, boolean hasTeeth) {
        super(index, cooldown, name);
        this.hasInnerMouth = hasInnerMouth;
        this.hasTeeth = hasTeeth;
    }

    public AnimationDefinition getAbilityAnim(SquamorphEntity owner) {
        return squamorphAnimation.bite;
    }

    public int getAbilityAnimState() {return 1;}

    @Override
    public void performMeleeAttack(SquamorphEntity owner, LivingEntity target, double dist) {
        double d0 = owner.getMeleeAttackRangeSqr(target);
        if (dist <= d0) {
            owner.swing(InteractionHand.MAIN_HAND);
            if (owner.doHurtTarget(target)) {
                owner.getPrimaryElement().applyElement(target, owner, 1, 5);
                EntityHelpers.particleOnhitEffect(owner.getPrimaryElement().getTrailParticle(), target, owner.level(), 1);
            }
        }
    }

    @Override
    public void tickAttack(SquamorphEntity entity, int animTicks, LivingEntity target, double dist) {
        if (animTicks >= 8) {
            this.performMeleeAttack(entity, target, dist);
            entity.addCooldowns();
            entity.setMouthCD(this.getCooldown());
            entity.setAttackState(0);
            entity.resetMove();
        }
    }


    @Override
    public boolean hasMelee() {
        return true;
    }

    public boolean hasInnerMouth() {
        return this.hasInnerMouth;
    }

    public boolean hasTeeth() {
        return this.hasTeeth;
    }

    @Override
    public boolean canUseAbility(SquamorphEntity owner, LivingEntity target) {
        return owner.getMouthAbilityCD() <= 0 && owner.getPerceivedTargetDistanceSquareForMeleeAttack(target) <= owner.getBbWidth() * 2.0F * owner.getBbWidth() * 2.0F + target.getBbWidth() && Math.abs(owner.getY() - target.getY()) < 3;
    }

    @Override
    public float getDamage(SquamorphEntity owner) {
        return (float)owner.getAttribute(Attributes.ATTACK_DAMAGE).getValue() * (float)Math.pow((owner.getMouthLevel() + 1), 2);
    }

}
