package com.hedge.squamorphs.client.models;// Made with Blockbench 5.0.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.hedge.squamorphs.client.modellayers.ModelLayers;
import com.hedge.squamorphs.entity.projectile.SquamorphProjectile;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class SquamorphProjectileModel extends EntityModel<SquamorphProjectile> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = ModelLayers.PROJECTILE_LAYER;
	private final ModelPart root;
	private final ModelPart scale_modifier;

	public SquamorphProjectileModel(ModelPart root) {
		this.root = root.getChild("root");
		this.scale_modifier = this.root.getChild("scale_modifier");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition scale_modifier = root.addOrReplaceChild("scale_modifier", CubeListBuilder.create().texOffs(0, 28).addBox(-2.0F, -0.5F, -6.0F, 4.0F, 4.0F, 16.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-3.5F, -4.5F, -6.0F, 7.0F, 8.0F, 20.0F, new CubeDeformation(0.0F))
		.texOffs(40, 28).addBox(-4.5F, -5.5F, 0.0F, 9.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(SquamorphProjectile entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root.x = -180;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}