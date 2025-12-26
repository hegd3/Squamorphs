// Made with Blockbench 5.0.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
package com.hedge.squamorphs.client.models;


import com.hedge.squamorphs.client.animations.squamorphAnimation;
import com.hedge.squamorphs.client.modellayers.ModelLayers;
import com.hedge.squamorphs.entity.living.SquamorphEntity;
import com.hedge.squamorphs.entity.squamorphparts.body.SquamorphBody;
import com.hedge.squamorphs.entity.squamorphparts.body.SquamorphWings;
import com.hedge.squamorphs.entity.squamorphparts.head.SquamorphHead;
import com.hedge.squamorphs.entity.squamorphparts.legs.SquamorphLeg;
import com.hedge.squamorphs.entity.squamorphparts.mouth.SquamorphMouth;
import com.hedge.squamorphs.entity.squamorphparts.tail.SquamorphTail;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class SquamorphModel extends HierarchicalModel<SquamorphEntity> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = ModelLayers.SQUAMORPH_LAYER;
	private final ModelPart root;
	private final ModelPart anglecontrol;
	private final ModelPart restofbody;
	private final ModelPart bodyandtail;
	private final ModelPart body;
	private final ModelPart cannon;
	private final ModelPart leftwing;
	private final ModelPart rightwing;
	private final ModelPart leftleg1;
	private final ModelPart rightleg1;
	private final ModelPart extraleftleg;
	private final ModelPart cubicleftleg1;
	private final ModelPart cubicrightleg1;
	private final ModelPart extrarightleg;
	private final ModelPart tail;
	private final ModelPart tail2;
	private final ModelPart leftleg2;
	private final ModelPart rightleg2;
	private final ModelPart cubicleftleg2;
	private final ModelPart cubicrightleg2;
	private final ModelPart head;
	private final ModelPart headrot;
	private final ModelPart leftmandible;
	private final ModelPart rightmandible;
	private final ModelPart jaw;

	public SquamorphModel(ModelPart base) {
		this.root = base.getChild("root");
		this.anglecontrol = this.root.getChild("anglecontrol");
		this.restofbody = this.anglecontrol.getChild("restofbody");
		this.bodyandtail = this.restofbody.getChild("bodyandtail");
		this.body = this.bodyandtail.getChild("body");
		this.cannon = this.body.getChild("cannon");
		this.leftwing = this.body.getChild("leftwing");
		this.rightwing = this.body.getChild("rightwing");
		this.leftleg1 = this.bodyandtail.getChild("leftleg1");
		this.rightleg1 = this.bodyandtail.getChild("rightleg1");
		this.extraleftleg = this.bodyandtail.getChild("extraleftleg");
		this.cubicleftleg1 = this.bodyandtail.getChild("cubicleftleg1");
		this.cubicrightleg1 = this.bodyandtail.getChild("cubicrightleg1");
		this.extrarightleg = this.bodyandtail.getChild("extrarightleg");
		this.tail = this.bodyandtail.getChild("tail");
		this.tail2 = this.tail.getChild("tail2");
		this.leftleg2 = this.restofbody.getChild("leftleg2");
		this.rightleg2 = this.restofbody.getChild("rightleg2");
		this.cubicleftleg2 = this.restofbody.getChild("cubicleftleg2");
		this.cubicrightleg2 = this.restofbody.getChild("cubicrightleg2");
		this.head = this.anglecontrol.getChild("head");
		this.headrot = this.head.getChild("headrot");
		this.leftmandible = this.headrot.getChild("leftmandible");
		this.rightmandible = this.headrot.getChild("rightmandible");
		this.jaw = this.headrot.getChild("jaw");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 1.0F));

		PartDefinition anglecontrol = root.addOrReplaceChild("anglecontrol", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -1.0F));

		PartDefinition restofbody = anglecontrol.addOrReplaceChild("restofbody", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, -5.0F));

		PartDefinition bodyandtail = restofbody.addOrReplaceChild("bodyandtail", CubeListBuilder.create(), PartPose.offset(0.0F, -3.0F, 5.5F));

		PartDefinition body = bodyandtail.addOrReplaceChild("body", CubeListBuilder.create().texOffs(44, 12).addBox(-4.5F, -4.0F, -5.5F, 9.0F, 8.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(0, 33).addBox(-5.5F, -8.0F, -6.5F, 11.0F, 4.0F, 13.0F, new CubeDeformation(0.0F))
		.texOffs(0, 89).addBox(0.0F, -9.0F, -5.5F, 0.0F, 5.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cannon = body.addOrReplaceChild("cannon", CubeListBuilder.create().texOffs(22, 89).addBox(-2.5F, -4.0F, -5.0F, 5.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 0.5F));

		PartDefinition leftwing = body.addOrReplaceChild("leftwing", CubeListBuilder.create().texOffs(44, 0).addBox(0.0F, 0.0F, -4.0F, 13.0F, 0.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(4.5F, -4.0F, -1.5F));

		PartDefinition rightwing = body.addOrReplaceChild("rightwing", CubeListBuilder.create().texOffs(44, 0).mirror().addBox(-13.0F, 0.0F, -4.0F, 13.0F, 0.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-4.5F, -4.0F, -1.5F));

		PartDefinition leftleg1 = bodyandtail.addOrReplaceChild("leftleg1", CubeListBuilder.create().texOffs(106, 54).addBox(0.0F, 0.0F, -2.5F, 7.0F, 0.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(22, 101).addBox(0.0F, -1.0F, -2.5F, 7.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(4.5F, 3.97F, -4.0F));

		PartDefinition rightleg1 = bodyandtail.addOrReplaceChild("rightleg1", CubeListBuilder.create().texOffs(106, 54).mirror().addBox(-7.0F, 0.0F, -2.5F, 7.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(22, 101).mirror().addBox(-7.0F, -1.0F, -2.5F, 7.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-4.5F, 3.97F, -4.0F));

		PartDefinition extraleftleg = bodyandtail.addOrReplaceChild("extraleftleg", CubeListBuilder.create().texOffs(106, 59).addBox(0.0F, 0.0F, -2.5F, 7.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(4.5F, 3.97F, 0.0F));

		PartDefinition cubicleftleg1 = bodyandtail.addOrReplaceChild("cubicleftleg1", CubeListBuilder.create().texOffs(112, 97).addBox(-1.5F, 0.5F, -1.5F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.02F)), PartPose.offset(2.0F, 3.5F, -4.0F));

		PartDefinition cubicrightleg1 = bodyandtail.addOrReplaceChild("cubicrightleg1", CubeListBuilder.create().texOffs(112, 97).mirror().addBox(-1.5F, 0.5F, -1.5F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.02F)).mirror(false), PartPose.offset(-2.0F, 3.5F, -4.0F));

		PartDefinition extrarightleg = bodyandtail.addOrReplaceChild("extrarightleg", CubeListBuilder.create().texOffs(106, 59).mirror().addBox(-7.0F, 0.0F, -2.5F, 7.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-4.5F, 3.97F, 0.0F));

		PartDefinition tail = bodyandtail.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(38, 75).addBox(-2.5F, -2.5F, 0.0F, 5.0F, 5.0F, 9.0F, new CubeDeformation(0.01F))
		.texOffs(90, 109).addBox(-2.5F, -2.5F, 0.0F, 5.0F, 5.0F, 4.0F, new CubeDeformation(0.01F))
		.texOffs(48, 31).addBox(0.0F, -7.5F, 0.0F, 0.0F, 10.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.5F, 5.5F));

		PartDefinition tail2 = tail.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -8.5F, -7.0F, 0.0F, 11.0F, 22.0F, new CubeDeformation(0.0F))
		.texOffs(0, 50).addBox(-3.5F, -2.5F, 0.0F, 7.0F, 4.0F, 11.0F, new CubeDeformation(0.0F))
		.texOffs(76, 90).addBox(-1.5F, -7.5F, -1.0F, 3.0F, 9.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(66, 81).addBox(-4.5F, -1.5F, 0.0F, 9.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(112, 89).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(94, 0).addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 9.0F));

		PartDefinition leftleg2 = restofbody.addOrReplaceChild("leftleg2", CubeListBuilder.create().texOffs(96, 104).addBox(0.0F, 0.0F, -2.5F, 7.0F, 0.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(21, 132).addBox(0.0F, -1.0F, -2.5F, 7.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(4.5F, 0.97F, 9.5F));

		PartDefinition rightleg2 = restofbody.addOrReplaceChild("rightleg2", CubeListBuilder.create().texOffs(96, 104).mirror().addBox(-7.0F, 0.0F, -2.5F, 7.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(21, 132).mirror().addBox(-7.0F, -1.0F, -2.5F, 7.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-4.5F, 0.97F, 9.5F));

		PartDefinition cubicleftleg2 = restofbody.addOrReplaceChild("cubicleftleg2", CubeListBuilder.create().texOffs(56, 113).addBox(-1.5F, 0.5F, -1.5F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.02F)), PartPose.offset(2.0F, 0.5F, 9.5F));

		PartDefinition cubicrightleg2 = restofbody.addOrReplaceChild("cubicrightleg2", CubeListBuilder.create().texOffs(56, 113).mirror().addBox(-1.5F, 0.5F, -1.5F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.02F)).mirror(false), PartPose.offset(-2.0F, 0.5F, 9.5F));

		PartDefinition head = anglecontrol.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, -2.5F, -5.0F));

		PartDefinition headrot = head.addOrReplaceChild("headrot", CubeListBuilder.create().texOffs(84, 12).addBox(-3.5F, -2.5F, -7.0F, 7.0F, 3.0F, 7.0F, new CubeDeformation(0.01F))
		.texOffs(112, 12).addBox(-2.5F, -3.5F, -5.0F, 5.0F, 1.0F, 4.0F, new CubeDeformation(0.01F))
		.texOffs(48, 98).addBox(-2.5F, -2.5F, -13.0F, 5.0F, 3.0F, 6.0F, new CubeDeformation(0.01F))
		.texOffs(36, 55).addBox(-4.5F, -1.5F, -12.0F, 9.0F, 2.0F, 10.0F, new CubeDeformation(0.009F))
		.texOffs(70, 106).addBox(-1.5F, -2.5F, -14.0F, 3.0F, 2.0F, 7.0F, new CubeDeformation(0.01F))
		.texOffs(108, 45).addBox(-2.5F, -1.5F, -15.0F, 5.0F, 0.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(38, 67).addBox(-6.5F, -2.5F, -12.0F, 13.0F, 3.0F, 5.0F, new CubeDeformation(0.01F))
		.texOffs(74, 67).addBox(-6.5F, 0.5F, -12.0F, 13.0F, 2.0F, 5.0F, new CubeDeformation(0.01F))
		.texOffs(68, 115).addBox(0.5F, -5.5F, -3.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.01F))
		.texOffs(68, 115).mirror().addBox(-2.5F, -5.5F, -3.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.01F)).mirror(false)
		.texOffs(110, 74).addBox(-3.5F, -2.5F, -9.0F, 7.0F, 3.0F, 2.0F, new CubeDeformation(0.01F))
		.texOffs(28, 78).addBox(0.5F, -5.5F, -3.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.01F))
		.texOffs(28, 78).mirror().addBox(-3.5F, -5.5F, -3.0F, 3.0F, 3.0F, 2.0F, new CubeDeformation(0.01F)).mirror(false)
		.texOffs(96, 81).addBox(-3.5F, -5.5F, -6.0F, 7.0F, 3.0F, 5.0F, new CubeDeformation(0.01F))
		.texOffs(108, 37).addBox(-3.5F, -2.5F, -10.0F, 7.0F, 5.0F, 3.0F, new CubeDeformation(0.01F))
		.texOffs(44, 113).addBox(-1.5F, -4.5F, -10.0F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.01F))
		.texOffs(70, 98).addBox(0.0F, -2.5F, -8.0F, 0.0F, 7.0F, 1.0F, new CubeDeformation(0.01F))
		.texOffs(112, 17).addBox(2.0F, -6.5F, -6.0F, 0.0F, 4.0F, 6.0F, new CubeDeformation(0.01F))
		.texOffs(3, 131).addBox(0.0F, -2.5F, -23.0F, 0.0F, 3.0F, 13.0F, new CubeDeformation(0.01F))
		.texOffs(112, 17).mirror().addBox(-2.0F, -6.5F, -6.0F, 0.0F, 4.0F, 6.0F, new CubeDeformation(0.01F)).mirror(false)
		.texOffs(110, 64).addBox(-1.5F, -4.5F, -12.0F, 3.0F, 5.0F, 5.0F, new CubeDeformation(0.01F))
		.texOffs(108, 114).addBox(-1.5F, 0.5F, -12.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.01F))
		.texOffs(96, 89).addBox(0.0F, -9.5F, -8.0F, 0.0F, 7.0F, 8.0F, new CubeDeformation(0.01F))
		.texOffs(0, 105).addBox(-3.5F, -2.5F, -11.0F, 7.0F, 3.0F, 4.0F, new CubeDeformation(0.01F))
		.texOffs(16, 114).addBox(-2.5F, -4.5F, -2.0F, 5.0F, 2.0F, 2.0F, new CubeDeformation(0.01F))
		.texOffs(66, 75).addBox(3.5F, -3.5F, -4.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.01F))
		.texOffs(66, 75).mirror().addBox(-4.5F, -3.5F, -4.0F, 1.0F, 3.0F, 3.0F, new CubeDeformation(0.01F)).mirror(false)
		.texOffs(30, 114).addBox(1.5F, -3.5F, -3.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.01F))
		.texOffs(30, 114).mirror().addBox(-4.5F, -3.5F, -3.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.01F)).mirror(false)
		.texOffs(0, 78).addBox(-3.5F, 0.5F, -7.0F, 7.0F, 4.0F, 7.0F, new CubeDeformation(0.01F))
		.texOffs(112, 31).addBox(-3.5F, 0.5F, -9.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.01F))
		.texOffs(108, 109).addBox(-3.5F, 0.5F, -11.0F, 7.0F, 1.0F, 4.0F, new CubeDeformation(0.01F))
		.texOffs(28, 83).addBox(0.5F, 0.5F, -8.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.01F))
		.texOffs(28, 83).mirror().addBox(-2.5F, 0.5F, -8.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition leftmandible = headrot.addOrReplaceChild("leftmandible", CubeListBuilder.create().texOffs(74, 55).addBox(0.0F, 0.0F, -12.0F, 4.0F, 0.0F, 12.0F, new CubeDeformation(0.01F)), PartPose.offset(1.5F, 0.5F, -4.0F));

		PartDefinition rightmandible = headrot.addOrReplaceChild("rightmandible", CubeListBuilder.create().texOffs(74, 55).mirror().addBox(-4.0F, 0.0F, -12.0F, 4.0F, 0.0F, 12.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offset(-1.5F, 0.5F, -4.0F));

		PartDefinition jaw = headrot.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(112, 27).addBox(-3.5F, 0.0F, -9.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(84, 22).addBox(-3.5F, 0.0F, -7.0F, 7.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(76, 37).addBox(-4.5F, 0.0F, -8.0F, 9.0F, 2.0F, 7.0F, new CubeDeformation(0.01F))
		.texOffs(76, 46).addBox(-4.5F, -1.0F, -8.0F, 9.0F, 1.0F, 7.0F, new CubeDeformation(0.01F))
		.texOffs(48, 90).addBox(-3.5F, -1.0F, -7.0F, 7.0F, 1.0F, 7.0F, new CubeDeformation(0.02F))
		.texOffs(44, 107).addBox(-3.5F, 0.0F, -11.0F, 7.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(36, 50).addBox(-1.5F, 0.0F, -10.0F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 112).addBox(-1.5F, 0.0F, -12.0F, 3.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(36, 83).mirror().addBox(1.5F, -2.0F, -8.0F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.02F)).mirror(false)
		.texOffs(36, 83).addBox(-1.5F, -2.0F, -8.0F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.02F))
		.texOffs(74, 74).addBox(-6.5F, 0.0F, -12.0F, 13.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(76, 31).addBox(-6.5F, -1.0F, -12.0F, 13.0F, 1.0F, 5.0F, new CubeDeformation(0.02F))
		.texOffs(0, 67).addBox(-4.5F, 0.0F, -12.0F, 9.0F, 1.0F, 10.0F, new CubeDeformation(0.01F))
		.texOffs(22, 107).addBox(-2.5F, 0.0F, -13.0F, 5.0F, 1.0F, 6.0F, new CubeDeformation(0.01F)), PartPose.offset(0.0F, 0.5F, 0.0F));

		return LayerDefinition.create(meshdefinition, 256, 256);
	}

	@Override
	public void setupAnim(SquamorphEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

        SquamorphMouth mouthPart = entity.getMouth();
        SquamorphHead headPart = entity.getHead();
        SquamorphBody bodyPart = entity.getBody();
		SquamorphLeg legPart = entity.getLeg();
		SquamorphTail tailPart = entity.getTail();

		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.applyRot(entity, netHeadYaw, headPitch, ageInTicks);

        if (legPart != null) {
            if (entity.isFlying()) {
                this.animateWalk(squamorphAnimation.fly, limbSwing, limbSwingAmount, 2f, 2.5f);
            } else if (entity.isInFluidType()) {
                this.animateWalk(squamorphAnimation.swim, limbSwing, limbSwingAmount, 2f, 2.5f);
            } else {
                this.animateWalk(legPart.getWalk(), limbSwing, limbSwingAmount, 2f, legPart.getAnimSpeed());
            }
            this.animate(entity.idleAnimationState, entity.isFlying()? squamorphAnimation.fly : entity.isInFluidType() ? squamorphAnimation.swim_idle : legPart.getIdle(), ageInTicks, 1f);

            switch (entity.getAttackState()) {
                case 2 -> this.animate(entity.currentMoveAnimationState, headPart.getAbilityAnim(entity), ageInTicks, 1f);
				case 3 -> this.animate(entity.currentMoveAnimationState, bodyPart.getAbilityAnim(entity), ageInTicks, 1f);
				case 4 -> this.animate(entity.currentMoveAnimationState, legPart.getAbilityAnim(entity), ageInTicks, 1f);
				case 5 -> this.animate(entity.currentMoveAnimationState, tailPart.getAbilityAnim(entity), ageInTicks, 1f);
                default -> this.animate(entity.currentMoveAnimationState, mouthPart.getAbilityAnim(entity), ageInTicks, 1f);
            }


            if (bodyPart instanceof SquamorphWings w) {
                this.animate(entity.wingWalkAnimationState, w.getWalk(), ageInTicks, 1f);
                this.animate(entity.wingIdleAnimationState, w.getIdle(), ageInTicks, 1f);
            }

            this.root.y = legPart.getHeight();

        }


	}

	private void applyRot(SquamorphEntity entity, float netHeadYaw, float headPitch, float AgeinTicks) {
		netHeadYaw = Mth.clamp(netHeadYaw, -30.0F, 30.0F);
		headPitch = Mth.clamp(headPitch, -25.0F, 45.0F);

		this.headrot.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.headrot.xRot = headPitch * ((float)Math.PI / 180F);
        if ((entity.isFlying() || entity.isInWater()) && entity.getDeltaMovement().horizontalDistanceSqr() > 1.0E-6) {
            this.anglecontrol.xRot = headPitch * ((float)Math.PI / 180F);
        } else {
            this.anglecontrol.xRot = 0;
        }
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return root;
	}

}