package net.bananaman.it_starts_with_magic.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.bananaman.it_starts_with_magic.block.entity.TheEntityBlockBlockEntity;
import net.bananaman.it_starts_with_magic.particle.ModParticles;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.MinecartRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.vehicle.Minecart;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import org.joml.Quaternionf;
import org.joml.Random;

public class TheEntityBlockBlockEntityRenderer implements BlockEntityRenderer<TheEntityBlockBlockEntity> {
    public TheEntityBlockBlockEntityRenderer(BlockEntityRendererProvider.Context context) {

    }



    @Override
    public void render(TheEntityBlockBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack,
                       MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {

        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        ItemStack itemStack = pBlockEntity.getRenderStack();

        long gameTime = pBlockEntity.getLevel().getGameTime();
        float rotationAngle = (gameTime + pPartialTick) * 3.0f;

        pPoseStack.pushPose();
        pPoseStack.translate(0.5f,0.6f,0.5f);
        pPoseStack.scale(0.5f,0.5f,0.5f);
        pPoseStack.mulPose(Axis.YP.rotationDegrees(rotationAngle)); // Rotate around the Y-axis
        pPoseStack.mulPose(Axis.XP.rotationDegrees(90));





        itemRenderer.renderStatic(itemStack, ItemDisplayContext.FIXED, getLightLevel(pBlockEntity.getLevel(),pBlockEntity.getBlockPos()),
                OverlayTexture.NO_OVERLAY,pPoseStack, pBuffer, pBlockEntity.getLevel(),1);
        pPoseStack.popPose();



        // Spawn a particle
        Level level = pBlockEntity.getLevel();
        BlockPos pos = pBlockEntity.getBlockPos();
        Random random = new Random();

//        if (level != null && random.nextFloat() < 0.1f) { // Adjust the float value to control particle frequency
//            double x = pos.getX() + 0.5D;
//            double y = pos.getY() + 0.5D;
//            double z = pos.getZ() + 0.5D;
//
//            // Example: Spawning a "WITCH" particle
//            // You can choose any particle type from net.minecraft.core.particles.ParticleTypes
//            level.addParticle(ModParticles.MODSONICBOOMPARTICLE.get(), x, y, z,
//                    (random.nextFloat() - 0.5D) * 0.2D,
//                    (random.nextFloat() - 0.5D) * 0.2D,
//                    (random.nextFloat() - 0.5D) * 0.2D);
//        }


    }

    private int getLightLevel(Level level, BlockPos pos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight,sLight);
    }




}
