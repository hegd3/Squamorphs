package com.hedge.squamorphs.entity.squamorphparts.head;

import com.hedge.squamorphs.client.animations.SquamorphAbilityAnimation;
import com.hedge.squamorphs.client.animations.squamorphAnimation;
import com.hedge.squamorphs.entity.living.SquamorphEntity;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.world.entity.LivingEntity;

public class StrongSquamorphHead extends SquamorphHead {
    public StrongSquamorphHead(int index, int cooldown, String name, boolean hasEyes) {
        super(index, cooldown, name, hasEyes);
    }

    @Override
    public void tickAttack(SquamorphEntity entity, int animTicks, LivingEntity target, double dist) {
        if (animTicks == 13) {
            this.performRangedAttack(entity, target);
        } else if (animTicks >= 19) {
            entity.addCooldowns();
            entity.setHeadCD(this.getCooldown());
            entity.setAttackState(0);
            entity.resetMove();
        }
    }

    @Override
    public AnimationDefinition getAbilityAnim(SquamorphEntity owner) {
        return squamorphAnimation.mouth_shoot_strong;
    }
}
