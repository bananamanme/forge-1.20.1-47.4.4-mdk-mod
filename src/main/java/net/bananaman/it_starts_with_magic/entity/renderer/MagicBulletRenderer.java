package net.bananaman.it_starts_with_magic.entity.renderer;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.bananaman.it_starts_with_magic.ItStartsWithMagicMod;
import net.bananaman.it_starts_with_magic.entity.MagicBulletEntity;
import net.bananaman.it_starts_with_magic.entity.models.MagicBulletModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;


public class MagicBulletRenderer extends EntityRenderer<MagicBulletEntity> {
    // Define texture and model location
    private static final ResourceLocation TEXTURE_LOCATION = new ResourceLocation(ItStartsWithMagicMod.MOD_ID, "textures/entity/magic_bullet.png");
    private final MagicBulletModel<MagicBulletEntity> model;

    public MagicBulletRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        // Initialize your model, baking it from the registered layer definition
        this.model = new MagicBulletModel<>(pContext.bakeLayer(MagicBulletModel.LAYER_LOCATION));
    }

    @Override
    public ResourceLocation getTextureLocation(MagicBulletEntity pEntity) {
        return TEXTURE_LOCATION;
    }

    // You MUST override the render method
    @Override
    public void render(MagicBulletEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
        pPoseStack.pushPose(); // Start a new transformation matrix

        float spin = (pEntity.tickCount + pPartialTicks) * 20.0F; // Adjust '20.0F' to make it spin faster or slower
        float yRot = Mth.lerp(pPartialTicks, pEntity.yRotO, pEntity.getYRot()) - 90.0F;
        float xRot = Mth.lerp(pPartialTicks, pEntity.xRotO, pEntity.getXRot());


        pPoseStack.mulPose(com.mojang.math.Axis.YP.rotationDegrees(yRot));
        pPoseStack.mulPose(Axis.XP.rotationDegrees(spin));
        pPoseStack.mulPose(com.mojang.math.Axis.ZP.rotationDegrees(xRot));

        pPoseStack.translate(0, -1.25, 0); // Optional: Adjust the model's position slightly if needed



        // Get the correct vertex consumer from the buffer
        VertexConsumer vertexconsumer = pBuffer.getBuffer(this.model.renderType(this.getTextureLocation(pEntity)));

        // Render the model
        this.model.renderToBuffer(pPoseStack, vertexconsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

        pPoseStack.popPose(); // Restore the previous transformation matrix
        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}

