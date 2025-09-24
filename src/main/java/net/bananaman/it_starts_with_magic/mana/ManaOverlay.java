package net.bananaman.it_starts_with_magic.mana;

import net.bananaman.it_starts_with_magic.item.custom.TheSpellBook;
import net.bananaman.it_starts_with_magic.spells.Spell;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import top.theillusivec4.curios.api.CuriosApi;

import static com.ibm.icu.impl.ValidIdentifiers.Datatype.x;

public class ManaOverlay {
    private static final ResourceLocation EMPTY_MANA_BAR_TEXTURE = new ResourceLocation("it_starts_with_magic", "textures/gui/mana_bar_empty.png");
    private static final ResourceLocation FILLED_MANA_BAR_TEXTURE = new ResourceLocation("it_starts_with_magic", "textures/gui/mana_bar_filled.png");
    private static final int BAR_WIDTH = 81;
    private static final int BAR_HEIGHT = 9;

    private static String messageToDisplay = null;
    private static long messageExpireTime = 0;
    private static long messageStartTime = 0;

    public static final IGuiOverlay MANA_OVERLAY = ((gui, guiGraphics, partialTicks, width, height) -> {
        Minecraft mc = gui.getMinecraft();
        mc.player.getCapability(ManaProvider.MANA_CAPABILITY).ifPresent(mana -> {
            int manaAmount = mana.getMana();
            int maxMana = mana.getMaxMana();

            // Calculate the position to draw the bar (bottom right, above the hotbar)
            int x = width - BAR_WIDTH - 2;
            int y = height - 39;

            // Draw the empty mana bar background (the full 81x9 texture)
            guiGraphics.blit(EMPTY_MANA_BAR_TEXTURE, x, y, 0, 0, BAR_WIDTH, BAR_HEIGHT, BAR_WIDTH, BAR_HEIGHT);

            // Calculate the width of the filled mana
            int filledWidth = (int) (((float) manaAmount / maxMana) * BAR_WIDTH);

            // Draw the filled portion of the mana bar over the background
            guiGraphics.blit(FILLED_MANA_BAR_TEXTURE, x, y, 0, 0, filledWidth, BAR_HEIGHT, BAR_WIDTH, BAR_HEIGHT);

            String manaText = manaAmount + " / " + maxMana;
            int textWidth = mc.font.width(manaText);
            int textX = x + (BAR_WIDTH / 2) - (textWidth / 2);
            int textY = y - 10;

            guiGraphics.drawString(mc.font, manaText, textX, textY, 0x00BFFF, true);


            String spell = getCurrentSpellName();
            if (!spell.isEmpty()) {
                String localized = Component.translatable(spell).getString(); // <- resolves en_us.json
                int spellWidth = mc.font.width(localized);
                int spellX = x + (BAR_WIDTH / 2) - (spellWidth / 2);
                int spellY = y + BAR_HEIGHT + 1;
                guiGraphics.drawString(mc.font, localized, spellX, spellY, 0xFFFFFF, true);
            }
        });





        if (messageToDisplay != null && System.currentTimeMillis() < messageExpireTime) {
            String notEnoughManaText = "Not enough mana!";
            int textWidth = mc.font.width(notEnoughManaText);
            int textX = (width - textWidth) / 2;
            int textY = height - 60;

            // Calculate the fade effect
            long elapsedTime = System.currentTimeMillis() - messageStartTime;
            long totalTime = 3000;
            float fadeFactor = 1.0f;
            if (elapsedTime > (totalTime - 1000)) { // Fade out during the last second
                fadeFactor = 1.0f - ((float) (elapsedTime - (totalTime - 1000)) / 1000.0f);
            }

            int alpha = (int) (255 * fadeFactor);
            int color = (alpha << 24) | 0xCC3333; // Combine alpha with the base color

            guiGraphics.drawString(mc.font, notEnoughManaText, textX, textY, color, true);
        } else {
            messageToDisplay = null;
        }

    });

    public static void displayNotEnoughManaMessage() {
        messageToDisplay = "Not enough mana!";
        messageStartTime = System.currentTimeMillis();
        messageExpireTime = messageStartTime + 3000;
    }


    private static String getCurrentSpellName() {
        Player player = Minecraft.getInstance().player;
        if (player == null) return "";

        return CuriosApi.getCuriosHelper()
                .findEquippedCurio(stack -> stack.getItem() instanceof TheSpellBook, player)
                .map(pair -> {
                    ItemStack book = pair.getRight(); // ← fix
                    Spell s = TheSpellBook.getSelectedSpell(book);
                    return s == null ? "" : s.getDisplayName();
                })
                .orElse("");
    }




}
