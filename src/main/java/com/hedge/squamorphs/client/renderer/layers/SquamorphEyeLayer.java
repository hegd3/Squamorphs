package com.hedge.squamorphs.client.renderer.layers;

import com.hedge.squamorphs.client.modellayers.AllTextures;
import com.hedge.squamorphs.client.models.SquamorphModel;
import com.hedge.squamorphs.client.renderer.SquamorphRenderer;
import com.hedge.squamorphs.entity.living.SquamorphEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.RenderLayer;

public class SquamorphEyeLayer extends RenderLayer<SquamorphEntity, SquamorphModel> {


    public SquamorphEyeLayer(SquamorphRenderer pRenderer) {

        super(pRenderer);
    }



    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLightIn, SquamorphEntity entity, float v, float v1, float v2, float v3, float v4, float v5) {

        int index = entity.getHeadType();
        if (index != 16 && index != 8) {
            int color = entity.getEyeColor();


            float r = (float) (color >> 16 & 255) / 255.0F;
            float g = (float) (color >> 8 & 255) / 255.0F;
            float b = (float) (color & 255) / 255.0F;


            renderColoredCutoutModel(getParentModel(), entity.getHead().hasEyes() ? AllTextures.getEyeTex(index) : AllTextures.getEyeTex(0), poseStack, buffer, packedLightIn, entity, r, g, b);
        }

    }
}
