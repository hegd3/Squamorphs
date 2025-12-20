package com.hedge.squamorphs.entity.util;

import com.hedge.squamorphs.client.modellayers.AllTextures;
import com.hedge.squamorphs.entity.living.SquamorphEntity;
import com.hedge.squamorphs.entity.squamorphparts.SquamorphElement;
import net.minecraft.util.RandomSource;

public class SquamorphHelpers {

    private static final int[] NATURAL_PRIMARY_COLORS = new int[]{0x009900, 0x999966,
            0x006600, 0x33cc33, 0x99ff33, 0x003300, 0xCF5940, 0x533A61, 0x4C3EC7, 0x59A4D9, 0xC95959, 0x211212, 0xB01212,
            0xB53619, 0x995C56, 0x6A7363, 0x9E859E, 0xE06D22};
    private static final int[] NATURAL_SECONDARY_COLORS = new int[]{0x795E8A, 0x7E8A9E,
            0x8BC4A2, 0xBBD1A7, 0x85826F, 0x33312F, 0xC2B2A9, 0x9085ED, 0x8BC1E8, 0xA5D4B8, 0xEBC773, 0xD14B7B, 0x787D74};
    private static final int[] NATURAL_EYE_COLORS = new int[]{0x000000, 0x34394A, 0x38193B,
            0xD9C923, 0x705580, 0xB8B2B0, 0x697A8C, 0xED2D2D, 0xDB799E, 0xF2330A};


    public static int generateBodyColor(RandomSource random) {
        return NATURAL_PRIMARY_COLORS[random.nextInt(NATURAL_PRIMARY_COLORS.length) % NATURAL_PRIMARY_COLORS.length];
    }

    public static int generateSecondaryColor(RandomSource random) {
        return NATURAL_SECONDARY_COLORS[random.nextInt(NATURAL_SECONDARY_COLORS.length) % NATURAL_SECONDARY_COLORS.length];
    }

    public static int generateEyeColor(RandomSource random) {
        return NATURAL_EYE_COLORS[random.nextInt(NATURAL_EYE_COLORS.length) % NATURAL_EYE_COLORS.length];
    }

    public static void randomizeParts(SquamorphEntity entity, RandomSource random) {
        entity.setHeadType(random.nextInt(AllTextures.HEAD_TEXTURES.length + 1));
        entity.setMouthType(random.nextInt(AllTextures.MOUTH_TEXTURES.length + 1));
        entity.setBodyType(random.nextInt(AllTextures.BODY_TEXTURES.length + 1));
        entity.setLegType(random.nextInt(AllTextures.LEG_TEXTURES.length + 1));
        entity.setTailType(random.nextInt(AllTextures.TAIL_TEXTURES.length + 1));
        entity.setPatternType(random.nextInt(AllTextures.PATTERN_TEXTURES.length + 1));
        entity.setPrimaryElementIndex(random.nextInt(SquamorphElement.ALL_ELEMENTS.length));
        entity.setSecondaryElementIndex(random.nextInt(SquamorphElement.ALL_ELEMENTS.length));


        entity.setPrimaryColor(SquamorphHelpers.generateBodyColor(random));
        entity.setSecondaryColor(SquamorphHelpers.generateSecondaryColor(random));
        entity.setEyeColor(SquamorphHelpers.generateEyeColor(random));
    }



}
