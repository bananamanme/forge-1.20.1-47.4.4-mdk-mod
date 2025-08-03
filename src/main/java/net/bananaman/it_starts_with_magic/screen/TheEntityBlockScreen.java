package net.bananaman.it_starts_with_magic.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.bananaman.it_starts_with_magic.ItStartsWithMagicMod;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class TheEntityBlockScreen extends AbstractContainerScreen<TheEntityBlockMenu> {

    private static final ResourceLocation TEXTURE =
            new ResourceLocation(ItStartsWithMagicMod.MOD_ID,"textures/gui/the_entity_block_gui.png");

    public TheEntityBlockScreen(TheEntityBlockMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
        this.inventoryLabelY=10000;
        this.titleLabelY=10000;
    }

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0f,1.0f,1.0f,1.0f);
        RenderSystem.setShaderTexture(0,TEXTURE);
        int x =(width-imageWidth) / 2;
        int y =(height-imageHeight) / 2;

        pGuiGraphics.blit(TEXTURE,x,y,0,0,imageWidth,imageHeight);


        renderProgressArrow(pGuiGraphics,x,y);
    }

    private void renderProgressArrow(GuiGraphics GuiGraphics, int x, int y) {
        if (menu.isCrafting()) {
            GuiGraphics.blit(TEXTURE,x + 85, y + 30, 176,0,8,menu.getScaledProgress());
        }
    }

    @Override
    public void render(GuiGraphics guiGraphics, int pMouseX, int pMouseY, float delta) {
        renderBackground(guiGraphics);
        super.render(guiGraphics, pMouseX, pMouseY, delta);
        renderTooltip(guiGraphics,pMouseX,pMouseY);
    }
}
