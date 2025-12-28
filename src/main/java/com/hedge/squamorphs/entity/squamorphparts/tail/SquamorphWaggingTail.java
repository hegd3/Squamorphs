package com.hedge.squamorphs.entity.squamorphparts.tail;

import com.hedge.squamorphs.client.animations.squamorphAnimation;
import com.hedge.squamorphs.entity.living.SquamorphEntity;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.world.entity.LivingEntity;

public class SquamorphWaggingTail extends SquamorphTail{
    public SquamorphWaggingTail(int index, int cooldown, String name) {
        super(index, cooldown, name);
    }

    @Override
    public AnimationDefinition getAbilityAnim(SquamorphEntity owner) {
        return squamorphAnimation.rattle;
    }

    @Override
    public boolean hasRanged() {
        return true;
    }

    @Override
    public void performRangedAttack(SquamorphEntity owner, LivingEntity pTarget) {
        super.performRangedAttack(owner, pTarget);
    }

    @Override
    public void tickAttack(SquamorphEntity entity, int animTicks, LivingEntity target, double dist) {
        if (animTicks == 20) {
            performRangedAttack(entity, target);
        } else if (animTicks >= 35) {
            entity.addCooldowns();
            entity.setTailCD(this.getCooldown());
            entity.setAttackState(0);

        }
    }

    public double getRange() {
        return 400;
    }


    @Override
    public boolean canUseAbility(SquamorphEntity owner, LivingEntity target) {
        return owner.getTailAbilityCD() <= 0 && owner.getPerceivedTargetDistanceSquareForMeleeAttack(target) < getRange();
    }
}
