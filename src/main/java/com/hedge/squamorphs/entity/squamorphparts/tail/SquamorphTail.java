package com.hedge.squamorphs.entity.squamorphparts.tail;

import com.hedge.squamorphs.entity.living.SquamorphEntity;
import com.hedge.squamorphs.entity.squamorphparts.SquamorphPart;

public class SquamorphTail extends SquamorphPart {
    public SquamorphTail(int index, int cooldown, String name) {
        super(index, cooldown, name);
    }

    @Override
    public int getColor(SquamorphEntity entity) {
        return entity.getSecondaryColor();
    }
}
