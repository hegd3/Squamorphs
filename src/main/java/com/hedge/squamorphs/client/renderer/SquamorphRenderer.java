package com.hedge.squamorphs.client.renderer;

import com.hedge.squamorphs.Squamorphs;
import com.hedge.squamorphs.client.modellayers.ModelLayers;
import com.hedge.squamorphs.client.renderer.layers.*;
import com.hedge.squamorphs.client.models.SquamorphModel;
import com.hedge.squamorphs.entity.living.SquamorphEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SquamorphRenderer extends MobRenderer<SquamorphEntity, SquamorphModel> {


    public SquamorphRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new SquamorphModel(pContext.bakeLayer(ModelLayers.SQUAMORPH_LAYER)), 0.5F);

        this.addLayer(new SquamorphBodyLayer(this));
        this.addLayer(new SquamorphLegLayer(this));
        this.addLayer(new SquamorphTailBaseLayer(this));
        this.addLayer(new SquamorphTailLayer(this));
        this.addLayer(new SquamorphBodyAttachmentLayer(this));
        this.addLayer(new SquamorphHeadLayer(this));
        this.addLayer(new SquamorphMouthLayer(this));
        this.addLayer(new SquamorphEyeLayer(this));
        this.addLayer(new SquamorphInnerMouthLayer(this));
        this.addLayer(new SquamorphTeethLayer(this));
        this.addLayer(new SquamorphPatternLayer(this));



    }

    @Override
    public ResourceLocation getTextureLocation(SquamorphEntity squamorphEntity) {
        return new ResourceLocation(Squamorphs.MODID, "textures/entity/squamorph/mouth/innermouths/innermouth_0.png");
    }

    @Override
    public void render(SquamorphEntity entity, float pEntityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {

        if (entity.isBaby()) {
            poseStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(entity, pEntityYaw, partialTicks, poseStack, buffer, packedLight);
    }


}
