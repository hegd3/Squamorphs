// Made with Blockbench 5.0.7
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

package com.hedge.squamorphs.client.models;

import com.hedge.squamorphs.client.animations.SpiritAnimation;
import com.hedge.squamorphs.client.animations.flyAnimation;
import com.hedge.squamorphs.client.modellayers.ModelLayers;
import com.hedge.squamorphs.entity.living.summons.ElementalSpiritEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class ElementalSpiritModel extends HierarchicalModel<ElementalSpiritEntity> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = ModelLayers.SPIRIT_LAYER;
	private final ModelPart root;
	private final ModelPart flycontrol;
	private final ModelPart headrot;
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart leftarm;
	private final ModelPart rightarm;
	private final ModelPart body2;

	public ElementalSpiritModel(ModelPart root) {
		this.root = root.getChild("root");
		this.flycontrol = this.root.getChild("flycontrol");
		this.headrot = this.flycontrol.getChild("headrot");
		this.head = this.headrot.getChild("head");
		this.body = this.flycontrol.getChild("body");
		this.leftarm = this.body.getChild("leftarm");
		this.rightarm = this.body.getChild("rightarm");
		this.body2 = this.body.getChild("body2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 12.0F, 0.0F));

		PartDefinition flycontrol = root.addOrReplaceChild("flycontrol", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, 0.0F));

		PartDefinition headrot = flycontrol.addOrReplaceChild("headrot", CubeListBuilder.create(), PartPose.offset(0.0F, -5.0F, 0.0F));

		PartDefinition head = headrot.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.5F, -11.0F, -5.0F, 9.0F, 11.0F, 9.0F, new CubeDeformation(0.01F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body = flycontrol.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 20).addBox(-4.5F, -12.0F, -4.0F, 9.0F, 12.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 7.0F, 0.0F));

		PartDefinition leftarm = body.addOrReplaceChild("leftarm", CubeListBuilder.create().texOffs(32, 31).addBox(0.5F, 0.0F, -1.5F, 3.0F, 15.0F, 3.0F, new CubeDeformation(0.01F)), PartPose.offset(4.0F, -12.0F, -0.5F));

		PartDefinition rightarm = body.addOrReplaceChild("rightarm", CubeListBuilder.create().texOffs(32, 31).mirror().addBox(-2.5F, 0.0F, -1.5F, 3.0F, 15.0F, 3.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offset(-5.0F, -12.0F, -0.5F));

		PartDefinition body2 = body.addOrReplaceChild("body2", CubeListBuilder.create().texOffs(32, 20).addBox(-3.5F, 0.0F, -3.0F, 7.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return this.root;
	}

	@Override
	public void setupAnim(ElementalSpiritEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		netHeadYaw = Mth.clamp(netHeadYaw, -30.0F, 30.0F);
		headPitch = Mth.clamp(headPitch, -25.0F, 45.0F);

		this.headrot.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.headrot.xRot = headPitch * ((float)Math.PI / 180F);

		this.flycontrol.xRot = headPitch * ((float)Math.PI / 180F);
		this.animateWalk(SpiritAnimation.fly, limbSwing, limbSwingAmount, 3f, 2.5f);
		this.animate(entity.idleAnimationState, SpiritAnimation.idle, ageInTicks, 1f);
		this.animate(entity.attackAnimationState, this.getAnim(entity), ageInTicks, 1f);

	}

	private AnimationDefinition getAnim(ElementalSpiritEntity entity) {
		switch (entity.getAttackState()) {
            case 2 -> {
                return SpiritAnimation.summon;
            }
            case 3 -> {
                return SpiritAnimation.lunge;
            }
        }
		return entity.swingingLeft() ? SpiritAnimation.attack_left : SpiritAnimation.attack_right;
	}
}