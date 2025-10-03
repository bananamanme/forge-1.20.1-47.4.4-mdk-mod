package net.bananaman.it_starts_with_magic.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.bananaman.it_starts_with_magic.ItStartsWithMagicMod;
import net.bananaman.it_starts_with_magic.entity.entities.SacrificeArrowEntity;
import net.bananaman.it_starts_with_magic.entity.models.SacrificeArrowModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;

/**
 * SacrificeArrowRenderer
 * - Always points forward (direction of travel), not affected by camera
 * - Spins strictly around its local X axis (like a bullet in a barrel)
 */
public class SacrificeArrowRenderer extends EntityRenderer<SacrificeArrowEntity> {
    private final SacrificeArrowModel<SacrificeArrowEntity> model;
    private static final ResourceLocation TEXTURE = new ResourceLocation(ItStartsWithMagicMod.MOD_ID, "textures/entity/sacrifice_arrow.png");

    // Offset for model forward direction
    private static final float MODEL_YAW_OFFSET = -90.0F; // adjust if pointing sideways/backwards
    private static final float SPIN_SPEED_DEGREES_PER_TICK = 40.0F; // adjust spin speed

    public SacrificeArrowRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new SacrificeArrowModel<>(context.bakeLayer(SacrificeArrowModel.LAYER_LOCATION));
    }

    @Override
    public ResourceLocation getTextureLocation(SacrificeArrowEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(SacrificeArrowEntity entity, float entityYaw, float partialTicks,
                       PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();

        // Motion vector
        Vec3 motion = entity.getDeltaMovement();
        double dx = motion.x;
        double dy = motion.y;
        double dz = motion.z;

        float yawDegrees;
        float pitchDegrees;

        double horizontal = Math.sqrt(dx * dx + dz * dz);
        final double EPS = 1e-6;

        if (horizontal < EPS && Math.abs(dy) < EPS) {
            yawDegrees = 0f;
            pitchDegrees = 0f;
        } else {
            yawDegrees = (float) Math.toDegrees(Math.atan2(dz, dx));
            pitchDegrees = (float) Math.toDegrees(Math.atan2(dy, horizontal));
        }

        // Rotate arrow to velocity direction
        poseStack.mulPose(Axis.YP.rotationDegrees(-yawDegrees + 90f)); // yaw correction
        poseStack.mulPose(Axis.XP.rotationDegrees(-pitchDegrees));     // pitch correction

        // Spin roll around forward axis
        float spin = (entity.tickCount + partialTicks) * 30f;
        poseStack.mulPose(Axis.ZP.rotationDegrees(spin % 360f));

// Center the arrow relative to the hitbox/particles
        poseStack.translate(0.0D, -1.50D, 0.0D);

// Render model
        VertexConsumer vc = buffer.getBuffer(model.renderType(getTextureLocation(entity)));
        model.renderToBuffer(poseStack, vc, packedLight, OverlayTexture.NO_OVERLAY, 1f, 1f, 1f, 1f);

        poseStack.popPose();
    }



}