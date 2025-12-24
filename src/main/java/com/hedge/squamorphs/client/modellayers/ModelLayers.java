package com.hedge.squamorphs.client.modellayers;

import com.hedge.squamorphs.Squamorphs;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModelLayers {
    public static final ModelLayerLocation SQUAMORPH_LAYER = main("squamorph");
    public static final ModelLayerLocation PROJECTILE_LAYER = main("projectile");
    public static final ModelLayerLocation FLY_LAYER = main("fly");

    private static ModelLayerLocation main(String id) {
        return new ModelLayerLocation(new ResourceLocation(Squamorphs.MODID, id), "main");
    }
}
