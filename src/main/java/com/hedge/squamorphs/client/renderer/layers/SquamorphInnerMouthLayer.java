package com.hedge.squamorphs.client.renderer.layers;

import com.hedge.squamorphs.client.modellayers.AllTextures;
import com.hedge.squamorphs.client.models.SquamorphModel;
import com.hedge.squamorphs.client.renderer.SquamorphRenderer;
import com.hedge.squamorphs.entity.living.SquamorphEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.RenderLayer;

public class SquamorphInnerMouthLayer extends RenderLayer<SquamorphEntity, SquamorphModel> {

    public SquamorphInnerMouthLayer(SquamorphRenderer pRenderer) {
        super(pRenderer);
    }



    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLightIn, SquamorphEntity entity, float v, float v1, float v2, float v3, float v4, float v5) {

        if (entity.getMouth().hasInnerMouth()) {


            renderColoredCutoutModel(getParentModel(), AllTextures.getInnerMouthTex(entity.getMouthType()), poseStack, buffer, packedLightIn, entity, 1.0f, 1.0f, 1.0f);
        }


    }
}
