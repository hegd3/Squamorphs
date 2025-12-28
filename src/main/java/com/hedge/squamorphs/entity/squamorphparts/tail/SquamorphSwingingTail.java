package com.hedge.squamorphs.entity.squamorphparts.tail;

import com.hedge.squamorphs.client.animations.squamorphAnimation;
import com.hedge.squamorphs.entity.living.SquamorphEntity;
import com.hedge.squamorphs.entity.squamorphparts.SquamorphPart;
import com.hedge.squamorphs.entity.util.EntityHelpers;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.List;

public class SquamorphSwingingTail extends SquamorphTail {
    public SquamorphSwingingTail(int index, int cooldown, String name) {
        super(index, cooldown, name);
    }

    @Override
    public boolean hasMelee() {
        return true;
    }

    @Override
    public boolean canUseAbility(SquamorphEntity owner, LivingEntity target) {
        owner.setAttackDirection(!EntityHelpers.rightOfEntity(owner, target));
        return owner.getTailAbilityCD() <= 0 && owner.getPerceivedTargetDistanceSquareForMeleeAttack(target) <= owner.getBbWidth() * 3F * owner.getBbWidth() * 3F + target.getBbWidth();
    }

    @Override
    public void tickAttack(SquamorphEntity entity, int animTicks, LivingEntity target, double dist) {
        if (animTicks == 16) {
            this.performMeleeAttack(entity, target, dist);
        } else if (animTicks >= 24) {
            entity.addCooldowns();
            entity.setTailCD(this.getCooldown());
            entity.setAttackState(0);
        }
    }

    @Override
    public void performMeleeAttack(SquamorphEntity owner, LivingEntity target, double dist) {

        List<LivingEntity> hit = owner.aoeAttack(1.5, 2, 1, 2, this.getDamage(owner), 2, 10);
        for (LivingEntity entity: hit) {
            owner.getSecondaryElement().applyElement(entity, owner, 1, 5);
            EntityHelpers.particleOnhitEffect(owner.getSecondaryElement().getParticle(), entity, owner.level(), 1);

        }
    }

    @Override
    public float getDamage(SquamorphEntity owner) {
        return (float)owner.getAttribute(Attributes.ATTACK_DAMAGE).getValue() * 1.5f;
    }

    @Override
    public AnimationDefinition getAbilityAnim(SquamorphEntity owner) {
        if (owner.isAttackingLeft()) return squamorphAnimation.tail_swipe_left;
        return squamorphAnimation.tail_swipe_right;
    }
}
