package com.hedge.squamorphs.entity.squamorphparts.mouth;

import com.hedge.squamorphs.client.animations.squamorphAnimation;
import com.hedge.squamorphs.entity.living.SquamorphEntity;
import com.hedge.squamorphs.entity.squamorphparts.SquamorphPart;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.world.entity.LivingEntity;

public class SquamorphMouth extends SquamorphPart {


    private final boolean hasInnerMouth;
    private final boolean hasTeeth;

    public SquamorphMouth(int index, int cooldown, String name, boolean hasInnerMouth, boolean hasTeeth) {
        super(index, cooldown, name);
        this.hasInnerMouth = hasInnerMouth;
        this.hasTeeth = hasTeeth;
    }

    public AnimationDefinition getBiteAnim() {
        return squamorphAnimation.bite;
    }

    public int getAbilityAnimState() {return 1;}


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

    public boolean canUseAbility(SquamorphEntity owner, LivingEntity target) {
        return owner.distanceToSqr(target) < owner.getBbWidth() * 2.0F * owner.getBbWidth() * 2.0F + target.getBbWidth()  && Math.abs(owner.getY() - target.getY()) < 3;

    }

}
