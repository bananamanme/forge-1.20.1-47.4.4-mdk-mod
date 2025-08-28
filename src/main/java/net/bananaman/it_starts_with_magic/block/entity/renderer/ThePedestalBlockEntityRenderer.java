package net.bananaman.it_starts_with_magic.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.bananaman.it_starts_with_magic.block.entity.custom.PedestalBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;

public class ThePedestalBlockEntityRenderer implements BlockEntityRenderer<PedestalBlockEntity> {
    public ThePedestalBlockEntityRenderer(BlockEntityRendererProvider.Context context) {

    }
    @Override
    public void render(PedestalBlockEntity pBlockEntity, float pPartialTick,
                       PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {

        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        ItemStack stack =pBlockEntity.invetory.getStackInSlot(0);

        pPoseStack.pushPose();
        pPoseStack.translate(0.5f,1.15f + pBlockEntity.getRenderPosition(),0.5f);
        pPoseStack.scale(0.4f,0.4f,0.4f);
        pPoseStack.mulPose(Axis.YP.rotationDegrees(pBlockEntity.getRenderRotation()));

        itemRenderer.renderStatic(stack, ItemDisplayContext.FIXED,getLightLevel(pBlockEntity.getLevel(),
                pBlockEntity.getBlockPos()), OverlayTexture.NO_OVERLAY, pPoseStack,pBuffer, pBlockEntity.getLevel(),1);

        pPoseStack.popPose();

    }

    private int getLightLevel(Level level, BlockPos pos) {
        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight,sLight);
    }
}
