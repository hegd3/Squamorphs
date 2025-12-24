package com.hedge.squamorphs.client.renderer;

import com.hedge.squamorphs.Squamorphs;
import com.hedge.squamorphs.client.modellayers.ModelLayers;
import com.hedge.squamorphs.client.models.ElementalFlyModel;
import com.hedge.squamorphs.entity.living.summons.ElementalFlyEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.allay.Allay;

public class ElementalFlyRenderer extends MobRenderer<ElementalFlyEntity, ElementalFlyModel> {

    public ElementalFlyRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new ElementalFlyModel(pContext.bakeLayer(ModelLayers.FLY_LAYER)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(ElementalFlyEntity entity) {
        return new ResourceLocation(Squamorphs.MODID, "textures/entity/summons/fly/elemental_fly_" + entity.getElement().getElementName() + ".png");
    }

    @Override
    protected int getBlockLightLevel(ElementalFlyEntity entity, BlockPos pos) {
        return 15;
    }

}
