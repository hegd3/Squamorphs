package com.hedge.squamorphs.client.renderer;

import com.hedge.squamorphs.Squamorphs;
import com.hedge.squamorphs.client.modellayers.AllTextures;
import com.hedge.squamorphs.client.modellayers.ModelLayers;
import com.hedge.squamorphs.client.models.SquamorphProjectileModel;
import com.hedge.squamorphs.entity.projectile.BlastProjectile;
import com.hedge.squamorphs.entity.projectile.BoltProjectile;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

public class BlastRenderer extends EntityRenderer<BlastProjectile> {
    private static final ResourceLocation[] BLAST_TEX = AllTextures.generateElementTextureArray("textures/entity/projectile/blast/blast");
    private final SquamorphProjectileModel model;

    public BlastRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        this.model = new SquamorphProjectileModel(pContext.bakeLayer(ModelLayers.PROJECTILE_LAYER));
    }

    @Override
    public ResourceLocation getTextureLocation(BlastProjectile pEntity) {
        return BLAST_TEX[pEntity.getElementIndex()];
    }

    @Override
    public void render(BlastProjectile entity, float yaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int light) {

        poseStack.pushPose();
        Vec3 motion = entity.getDeltaMovement();
        float xRot = -((float) (Mth.atan2(motion.horizontalDistance(), motion.y) * (double) (180F / (float) Math.PI)) - 90.0F);
        float yRot = -((float) (Mth.atan2(motion.z, motion.x) * (double) (180F / (float) Math.PI)) + 90.0F);
        poseStack.mulPose(Axis.YP.rotationDegrees(yRot));
        poseStack.mulPose(Axis.XP.rotationDegrees(xRot));
        VertexConsumer vertexconsumer = bufferSource.getBuffer(RenderType.entityTranslucentEmissive(getTextureLocation(entity)));
        this.model.renderToBuffer(poseStack, vertexconsumer, LightTexture.FULL_BRIGHT, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        poseStack.popPose();
        super.render(entity, yaw, partialTicks, poseStack, bufferSource, light);

    }






}
