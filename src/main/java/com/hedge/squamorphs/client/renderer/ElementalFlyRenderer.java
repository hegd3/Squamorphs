package com.hedge.squamorphs.client.renderer;

import com.hedge.squamorphs.client.modellayers.AllTextures;
import com.hedge.squamorphs.client.modellayers.ModelLayers;
import com.hedge.squamorphs.client.models.ElementalFlyModel;
import com.hedge.squamorphs.entity.living.summons.ElementalFlyEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

public class ElementalFlyRenderer extends MobRenderer<ElementalFlyEntity, ElementalFlyModel> {
    private static final ResourceLocation[] FLY_TEX = AllTextures.generateElementTextureArray("textures/entity/summons/fly/elemental_fly");

    public ElementalFlyRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new ElementalFlyModel(pContext.bakeLayer(ModelLayers.FLY_LAYER)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(ElementalFlyEntity entity) {
        return FLY_TEX[entity.getElementIndex()];
    }

    @Override
    protected int getBlockLightLevel(ElementalFlyEntity pEntity, BlockPos pPos) {
        return 15;
    }

}
