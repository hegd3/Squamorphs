package com.hedge.squamorphs.entity.squamorphparts.body;

import com.hedge.squamorphs.entity.living.SquamorphEntity;
import com.hedge.squamorphs.entity.squamorphparts.SquamorphPart;

public class SquamorphBody extends SquamorphPart {

    public SquamorphBody(int index, int cooldown, String name) {
        super(index, cooldown, name);
    }

    @Override
    public int getAbilityAnimState() {return 3;}

}
