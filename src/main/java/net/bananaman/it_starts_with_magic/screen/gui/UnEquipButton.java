//package net.bananaman.it_starts_with_magic.screen.gui;
//
//import net.bananaman.it_starts_with_magic.networking.ModMessages;
//import net.bananaman.it_starts_with_magic.networking.packet.UnEquipSpellbookC2SPacket;
//import net.bananaman.it_starts_with_magic.screen.SpellBookScreen;
//import net.minecraft.client.gui.GuiGraphics;
//import net.minecraft.client.gui.components.Button;
//import net.minecraft.network.chat.Component;
//import net.minecraftforge.api.distmarker.Dist;
//import net.minecraftforge.api.distmarker.OnlyIn;
//
//
//public class UnEquipButton extends Button {
//
//    private final SpellBookScreen parentScreen;
//
//    public UnEquipButton(int x, int y, int width, int height, SpellBookScreen parentScreen) {
//        super(x, y, width, height, Component.literal("X"), button -> {
//            // Send the packet to unequip the spellbook
//            ModMessages.sendToServer(new UnEquipSpellbookC2SPacket());
//        }, Button.DEFAULT_NARRATION);
//        this.parentScreen = parentScreen;
//    }
//
//    @Override
//    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
//        super.renderWidget(guiGraphics, mouseX, mouseY, partialTicks);
//        // You can customize the button appearance here
//    }
//}
