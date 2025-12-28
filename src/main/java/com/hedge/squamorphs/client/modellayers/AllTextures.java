package com.hedge.squamorphs.client.modellayers;

import com.hedge.squamorphs.Squamorphs;
import com.hedge.squamorphs.entity.squamorphparts.SquamorphElement;
import net.minecraft.resources.ResourceLocation;

public class AllTextures {

    public static final ResourceLocation[] HEAD_TEXTURES = generateTextureArray(19, "head");
    public static final ResourceLocation[] BODY_TEXTURES = generateTextureArray(20, "body");
    public static final ResourceLocation[] LEG_TEXTURES = generateTextureArray(20, "legs");
    public static final ResourceLocation[] MOUTH_TEXTURES = generateTextureArray(19, "mouth");
    public static final ResourceLocation[] TAIL_TEXTURES = generateTextureArray(20, "tail");
    public static final ResourceLocation[] PATTERN_TEXTURES = generateTextureArray(9, "pattern");





    private static ResourceLocation[] generateTextureArray(int size, String part) {
        ResourceLocation[] arr = new ResourceLocation[size];

        for (int i = 0; i < size; i++) {
            arr[i] = new ResourceLocation(Squamorphs.MODID, "textures/entity/squamorph/" + part + "/"
                    + part + "_" + (i + 1) + ".png");
        }

        return arr;

    }

    public static ResourceLocation[] generateElementTextureArray(String path) {
        ResourceLocation[] arr = new ResourceLocation[SquamorphElement.ALL_ELEMENTS.length];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = new ResourceLocation(Squamorphs.MODID, path + "_" + SquamorphElement.ALL_ELEMENTS[i].getElementName() + ".png");
        }

        return arr;

    }



    public static ResourceLocation getEyeTex(int index) {
        int i = 0;
        switch (index) {
            case 1, 9, 10, 13, 14, 15, 17, 18 -> i = index;
        };
        return new ResourceLocation(Squamorphs.MODID, "textures/entity/squamorph/head/eyes/eyes_" + i + ".png");
    }

    public static ResourceLocation getTeethTex(int index) {
        return new ResourceLocation(Squamorphs.MODID, "textures/entity/squamorph/mouth/teeth/teeth_" + index + ".png");
    }

    public static ResourceLocation getInnerMouthTex(int index) {

        return new ResourceLocation(Squamorphs.MODID, "textures/entity/squamorph/mouth/innermouths/innermouth_" + index + ".png");

    }


}
