package faz.darkvlight.com.darkvslight.client.models;
// Made with Blockbench 4.5.2
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import faz.darkvlight.com.darkvslight.Darkvslight;
import faz.darkvlight.com.darkvslight.entities.MeatballEntity;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class MeatballModel extends EntityModel<MeatballEntity> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Darkvslight.MODID, "meatball"), "main");
	private final ModelPart head;
	private final ModelPart rightleg;
	private final ModelPart leftleg;

	public MeatballModel(ModelPart root) {
		this.head = root.getChild("head");
		this.rightleg = root.getChild("rightleg");
		this.leftleg = root.getChild("leftleg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 8).addBox(-8.0F, -8.0F, -12.0F, 16.0F, 9.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(10, 24).addBox(-8.0F, -2.0F, -14.0F, 16.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(13, 27).addBox(-7.0F, -3.0F, -13.0F, 14.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(23, 6).addBox(-6.0F, -5.0F, -13.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(23, 6).addBox(-6.0F, -7.0F, -13.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(33, 6).addBox(2.0F, -5.0F, -13.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(33, 6).addBox(2.0F, -7.0F, -13.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(35, 6).addBox(2.0F, -6.0F, -13.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(25, 6).addBox(-4.0F, -6.0F, -13.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 8.0F));

		PartDefinition Jaw = head.addOrReplaceChild("Jaw", CubeListBuilder.create().texOffs(0, 15).addBox(-8.0F, 0.0F, -12.0F, 16.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.0F, -1.0F, 0.7418F, 0.0F, 0.0F));

		PartDefinition teeth = Jaw.addOrReplaceChild("teeth", CubeListBuilder.create().texOffs(0, 29).addBox(-8.0F, -2.0F, -12.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 29).addBox(-5.0F, -2.0F, -12.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 29).addBox(-1.0F, -2.0F, -12.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 29).addBox(3.0F, -2.0F, -12.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 29).addBox(6.0F, -2.0F, -12.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 29).addBox(-8.0F, -2.0F, -9.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 29).addBox(7.0F, -2.0F, -9.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 29).addBox(-8.0F, -2.0F, -6.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 29).addBox(7.0F, -2.0F, -6.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 29).addBox(-8.0F, -2.0F, -3.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 29).addBox(7.0F, -2.0F, -3.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition extensions = head.addOrReplaceChild("extensions", CubeListBuilder.create().texOffs(11, 25).addBox(-8.0F, -21.0F, 7.0F, 16.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 22.0F, -8.0F));

		PartDefinition teeth2 = head.addOrReplaceChild("teeth2", CubeListBuilder.create().texOffs(0, 29).addBox(-1.0F, -21.0F, -5.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 29).addBox(-5.0F, -21.0F, -5.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 29).addBox(3.0F, -21.0F, -5.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 29).addBox(-8.0F, -21.0F, -5.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 29).addBox(6.0F, -21.0F, -5.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 29).addBox(7.0F, -21.0F, -2.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 29).addBox(-8.0F, -21.0F, -2.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 29).addBox(7.0F, -21.0F, 1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 29).addBox(-8.0F, -21.0F, 1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 22.0F, -8.0F));

		PartDefinition rightleg = partdefinition.addOrReplaceChild("rightleg", CubeListBuilder.create().texOffs(4, 0).addBox(0.0F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.0F, 1.0F, 0.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(4, 4).addBox(-2.5F, 21.0F, -9.0F, 6.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(10, 5).addBox(-2.5F, 21.0F, -11.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(10, 5).addBox(2.5F, 21.0F, -11.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(10, 6).addBox(-0.5F, 21.0F, -10.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(7, 5).addBox(-1.5F, 20.0F, -8.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(10, 6).addBox(-0.5F, 19.0F, -7.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-9.0F, 2.0F, 7.0F));

		PartDefinition cube_r1 = rightleg.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -0.2881F, -0.828F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 13.7557F, -2.1335F, -0.5672F, 0.0F, 0.0F));

		PartDefinition cube_r2 = rightleg.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 1.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.75F, 0.0F, -0.3054F, 0.0F, 0.0F));

		PartDefinition leftleg = partdefinition.addOrReplaceChild("leftleg", CubeListBuilder.create().texOffs(4, 0).addBox(0.0F, -1.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(0.0F, 1.0F, 0.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(4, 4).addBox(-2.5F, 21.0F, -9.0F, 6.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(10, 5).addBox(-2.5F, 21.0F, -11.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(10, 5).addBox(2.5F, 21.0F, -11.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(10, 6).addBox(-0.5F, 21.0F, -10.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(7, 5).addBox(-1.5F, 20.0F, -8.0F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(10, 6).addBox(-0.5F, 19.0F, -7.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 2.0F, 7.0F));

		PartDefinition cube_r3 = leftleg.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -0.2881F, -0.828F, 1.0F, 8.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 13.7557F, -2.1335F, -0.5672F, 0.0F, 0.0F));

		PartDefinition cube_r4 = leftleg.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, 0.0F, 1.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.75F, 0.0F, -0.3054F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 32);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		rightleg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		leftleg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public void setupAnim(MeatballEntity p_102618_, float p_102619_, float p_102620_, float p_102621_, float p_102622_, float p_102623_) {

	}
}