package com.hedge.squamorphs.client.renderer;

import com.hedge.squamorphs.client.modellayers.AllTextures;
import com.hedge.squamorphs.client.modellayers.ModelLayers;
import com.hedge.squamorphs.client.models.ElementalSpiritModel;
import com.hedge.squamorphs.entity.living.summons.ElementalSpiritEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

public class ElementalSpiritRenderer extends MobRenderer<ElementalSpiritEntity, ElementalSpiritModel> {
    private static final ResourceLocation[] SPIRIT_TEX = AllTextures.generateElementTextureArray("textures/entity/summons/spirit/spirit");

    public ElementalSpiritRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new ElementalSpiritModel(pContext.bakeLayer(ModelLayers.SPIRIT_LAYER)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(ElementalSpiritEntity entity) {
        return SPIRIT_TEX[entity.getElementIndex()];
    }

    @Override
    protected int getBlockLightLevel(ElementalSpiritEntity pEntity, BlockPos pPos) {
        return 15;
    }

}
