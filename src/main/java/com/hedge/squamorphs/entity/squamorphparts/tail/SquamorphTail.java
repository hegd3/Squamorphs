package com.hedge.squamorphs.entity.squamorphparts.tail;

import com.hedge.squamorphs.client.animations.squamorphAnimation;
import com.hedge.squamorphs.entity.living.SquamorphEntity;
import com.hedge.squamorphs.entity.squamorphparts.SquamorphPart;
import net.minecraft.client.animation.AnimationDefinition;

public class SquamorphTail extends SquamorphPart {
    public SquamorphTail(int index, int cooldown, String name) {
        super(index, cooldown, name);
    }

    @Override
    public int getAbilityAnimState() {return 5;}


    @Override
    public int getColor(SquamorphEntity entity) {
        return entity.getSecondaryColor();
    }


}
