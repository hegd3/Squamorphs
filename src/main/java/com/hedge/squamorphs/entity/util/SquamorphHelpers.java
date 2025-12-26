package com.hedge.squamorphs.entity.util;

import com.hedge.squamorphs.client.modellayers.AllTextures;
import com.hedge.squamorphs.entity.living.SquamorphEntity;
import com.hedge.squamorphs.entity.squamorphparts.SquamorphElement;
import net.minecraft.util.RandomSource;

public class SquamorphHelpers {


    private static final int[] NATURAL_EYE_COLORS = new int[]{0x000000, 0x34394A, 0x38193B,
            0xD9C923, 0x705580, 0xB8B2B0, 0x697A8C, 0xED2D2D, 0xDB799E, 0xF2330A};

    private static final int[] FIRE_COLORS = new int[]{0xFF6B24, 0xFA4C00, 0xBA5441, 0xE87481, 0xC74444, 0x874A4A};
    private static final int[] ICE_COLORS = new int[]{0x9FB5BF, 0x86C6E3, 0x74ADE3, 0x6C86D4, 0x448FC2};
    private static final int[] CAUSTIC_COLORS = new int[]{0x76A67B, 0x7EBF85, 0x77E067, 0xA2BD9D, 0x90C940, 0x26C73C};
    private static final int[] ENDER_COLORS = new int[]{0xA363A8, 0x8C3296, 0x5E2D66, 0x8C1DA1, 0x572A44};
    private static final int[] SOUL_COLORS = new int[]{0xA5D9CF, 0xA5D9B9, 0x89F0D4, 0x58A698, 0x735D57};
    private static final int[] UMBRA_COLORS = new int[]{0x4F3F3F, 0x362F36, 0x47353A, 0x4C150D};
    private static final int[] RADIANT_COLORS = new int[]{0xBFBB99, 0xD1CA7D, 0xDEB42A, 0xCAD15C};
    private static final int[] METAL_COLORS = new int[]{0x75787D, 0x778F81, 0x998E94};
    private static final int[] FORCE_COLORS = new int[]{0xF23F3F, 0xBA4151, 0xE3344B};

    private static final int[][] ALL_COLORS = new int[][]{
            FIRE_COLORS, ICE_COLORS, CAUSTIC_COLORS, ENDER_COLORS, SOUL_COLORS, UMBRA_COLORS, RADIANT_COLORS, METAL_COLORS,
            FORCE_COLORS
    };

    public static int generateBodyColor(int index, RandomSource random) {
        return ALL_COLORS[index][random.nextInt(ALL_COLORS[index].length) % ALL_COLORS[index].length];
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


        entity.setPrimaryColor(SquamorphHelpers.generateBodyColor(entity.getPrimaryElementIndex(), random));
        entity.setSecondaryColor(SquamorphHelpers.generateBodyColor(entity.getSecondaryElementIndex(), random));

        entity.setEyeColor(SquamorphHelpers.generateEyeColor(random));
    }



}
