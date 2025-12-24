// Made with Blockbench 5.0.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

package com.hedge.squamorphs.client.models;

import com.hedge.squamorphs.client.animations.flyAnimation;
import com.hedge.squamorphs.client.modellayers.ModelLayers;
import com.hedge.squamorphs.entity.living.summons.ElementalFlyEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

import javax.swing.text.html.parser.Entity;

public class ElementalFlyModel extends HierarchicalModel<ElementalFlyEntity> {
	public static final ModelLayerLocation LAYER_LOCATION = ModelLayers.FLY_LAYER;
	private final ModelPart root;
	private final ModelPart flightcontrol;
	private final ModelPart body;
	private final ModelPart leftwing;
	private final ModelPart rightwing;
	private final ModelPart leftlegs;
	private final ModelPart rightlegs;

	public ElementalFlyModel(ModelPart base) {
		this.root = base.getChild("root");
		this.flightcontrol = this.root.getChild("flightcontrol");
		this.body = this.flightcontrol.getChild("body");
		this.leftwing = this.body.getChild("leftwing");
		this.rightwing = this.body.getChild("rightwing");
		this.leftlegs = this.body.getChild("leftlegs");
		this.rightlegs = this.body.getChild("rightlegs");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition flightcontrol = root.addOrReplaceChild("flightcontrol", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body = flightcontrol.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, -3.0F, -5.0F, 7.0F, 6.0F, 9.0F, new CubeDeformation(0.0F))
		.texOffs(20, 22).addBox(0.5F, -4.0F, -6.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(20, 22).mirror().addBox(-4.5F, -4.0F, -6.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(28, 18).addBox(0.0F, 1.0F, -6.0F, 0.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(28, 15).addBox(0.0F, -1.0F, 4.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, 1.0F));

		PartDefinition leftwing = body.addOrReplaceChild("leftwing", CubeListBuilder.create().texOffs(0, 15).mirror().addBox(0.0F, 0.0F, -3.5F, 7.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(3.5F, -3.0F, 0.5F));

		PartDefinition rightwing = body.addOrReplaceChild("rightwing", CubeListBuilder.create().texOffs(0, 15).addBox(-7.0F, 0.0F, -3.5F, 7.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.5F, -3.0F, 0.5F));

		PartDefinition leftlegs = body.addOrReplaceChild("leftlegs", CubeListBuilder.create().texOffs(0, 22).addBox(0.0F, 0.0F, -3.5F, 3.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(3.5F, 3.0F, -0.5F));

		PartDefinition rightlegs = body.addOrReplaceChild("rightlegs", CubeListBuilder.create().texOffs(0, 22).mirror().addBox(-3.0F, 0.0F, -3.5F, 3.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-3.5F, 3.0F, -0.5F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return root;
	}

	@Override
	public void setupAnim(ElementalFlyEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.flightcontrol.xRot = headPitch * ((float)Math.PI / 180F);
		this.animateWalk(flyAnimation.fly, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.animate(entity.idleAnimationState, flyAnimation.fly, ageInTicks, 1f);
		this.animate(entity.attackAnimationState, flyAnimation.attack, ageInTicks, 1f);
	}
}