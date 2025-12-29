package com.hedge.squamorphs.entity.squamorphparts.legs;

import com.hedge.squamorphs.client.animations.squamorphAnimation;
import com.hedge.squamorphs.entity.living.SquamorphEntity;
import net.minecraft.client.animation.AnimationDefinition;

public class SquamorphLegless extends SquamorphLeg {

    public SquamorphLegless() {
        super(0, 0, "legless");
    }

    @Override
    public AnimationDefinition getWalk() {
        return squamorphAnimation.legless_walk;
    }

    @Override
    public AnimationDefinition getIdle() {
        return squamorphAnimation.legless_idle;
    }

    @Override
    public float getAnimSpeed() {
        return 2.5f;
    }

}
