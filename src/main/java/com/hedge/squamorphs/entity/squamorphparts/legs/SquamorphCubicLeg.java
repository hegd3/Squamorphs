package com.hedge.squamorphs.entity.squamorphparts.legs;

import com.hedge.squamorphs.client.animations.squamorphAnimation;
import com.hedge.squamorphs.entity.living.SquamorphEntity;
import net.minecraft.client.animation.AnimationDefinition;

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

    public float getAnimSpeed() {
        return 2f;
    }
}
