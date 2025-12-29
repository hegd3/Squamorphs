package com.hedge.squamorphs.entity.squamorphparts.legs;

import com.hedge.squamorphs.client.animations.SquamorphAbilityAnimation;
import com.hedge.squamorphs.entity.living.SquamorphEntity;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;

import java.util.List;

public class SquamorphDiggingLeg extends SquamorphLeg{
    public SquamorphDiggingLeg(int index, int cooldown, String name) {
        super(index, cooldown, name);
    }

    @Override
    public AnimationDefinition getAbilityAnim(SquamorphEntity owner) {
        return SquamorphAbilityAnimation.dig;
    }

    @Override
    public boolean hasMelee() {
        return true;
    }

    @Override
    public boolean canUseAbility(SquamorphEntity owner, LivingEntity target) {
        return owner.getLegAbilityCD() == 0;
    }

    @Override
    public boolean isImmobileWhenUsing(SquamorphEntity entity) {
        return entity.getAnimTicks() < 50;
    }

    @Override
    public boolean isInvulernableWhenUsing(SquamorphEntity entity) {
        return entity.getAnimTicks() >= 20;
    }

    @Override
    public void tickAttack(SquamorphEntity entity, int animTicks, LivingEntity target, double dist) {
        if (entity.getAnimTicks() > 50) {
            entity.moveTo(target.getBlockX(), target.getBlockY(), target.getBlockZ());
            List<LivingEntity> aoe = entity.aoeAttack(1.0, 1.5, 2, 1, this.getDamage(entity), 1.1f, 4, entity.getPrimaryElement(), entity.getMouthLevel());
            for (LivingEntity e : aoe) {
                double f = 2.0 - e.getAttribute(Attributes.KNOCKBACK_RESISTANCE).getValue();
                if (f > 0)
                    e.setDeltaMovement(e.getDeltaMovement().add(0, f, 0));
            }
            entity.addCooldowns();
            entity.setLegCD(this.getCooldown());
            entity.setAttackState(0);
            entity.resetMove();
        }
    }

    @Override
    public float getDamage(SquamorphEntity entity) {
        return (float)entity.getAttribute(Attributes.ATTACK_DAMAGE).getValue() * 3f + (float)Math.pow(entity.getLegsLevel(), 2.3) * 2;

    }

}
