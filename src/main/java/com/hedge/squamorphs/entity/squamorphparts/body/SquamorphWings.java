package com.hedge.squamorphs.entity.squamorphparts.body;

import com.hedge.squamorphs.client.animations.squamorphAnimation;
import com.hedge.squamorphs.entity.living.SquamorphEntity;
import com.hedge.squamorphs.entity.util.goals.SquamorphFlyingGoal;
import net.minecraft.client.animation.AnimationDefinition;

public class SquamorphWings extends SquamorphBody {


    public SquamorphWings(int index, int cooldown, String name) {
        super(index, cooldown, name);
    }

    public AnimationDefinition getWalk() {
        return squamorphAnimation.wings_walk1;
    }

    public AnimationDefinition getIdle() {
        return squamorphAnimation.wings_idle1;
    }

    @Override
    public void applyStats(SquamorphEntity owner) {
        owner.goalSelector.addGoal(5, new SquamorphFlyingGoal(owner));
    }



}
