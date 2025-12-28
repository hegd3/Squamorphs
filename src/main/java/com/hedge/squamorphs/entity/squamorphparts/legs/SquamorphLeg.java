package com.hedge.squamorphs.entity.squamorphparts.legs;

import com.hedge.squamorphs.client.animations.squamorphAnimation;
import com.hedge.squamorphs.entity.living.SquamorphEntity;
import com.hedge.squamorphs.entity.squamorphparts.SquamorphPart;
import net.minecraft.client.animation.AnimationDefinition;

public class SquamorphLeg extends SquamorphPart {




    public SquamorphLeg(int index, int cooldown, String name) {
        super(index, cooldown, name);
    }

    public AnimationDefinition getWalk() {
        return squamorphAnimation.basic_walk;
    }

    public AnimationDefinition getIdle() {
        return squamorphAnimation.basic_idle;
    }

    public int getAbilityAnimState() {return 4;}


    public float getHeight() {
        return 23.0f;
    }

    @Override
    public String getName() {
        return "basic leg";
    }

    public float getAnimSpeed() {
        return 1.4f;
    }
}
