package com.hedge.squamorphs.entity.squamorphparts.legs;

import com.hedge.squamorphs.client.animations.squamorphAnimation;
import net.minecraft.client.animation.AnimationDefinition;

public class SquamorphBipedLeg extends SquamorphLeg {

    public SquamorphBipedLeg(int index, int cooldown, String name) {
        super(index, cooldown, name);
    }

    @Override
    public AnimationDefinition getWalk() {
        return squamorphAnimation.biped_walk;
    }

    @Override
    public AnimationDefinition getIdle() {
        return squamorphAnimation.biped_idle;
    }

    @Override
    public float getHeight() {
        return 19.9f;
    }

    public float getAnimSpeed() {
        return 2f;
    }
}
