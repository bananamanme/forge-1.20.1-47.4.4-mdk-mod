package net.bananaman.it_starts_with_magic.event;

import net.bananaman.it_starts_with_magic.ItStartsWithMagicMod;
import net.bananaman.it_starts_with_magic.modstuff.SpellBookHolderProvider;
import net.bananaman.it_starts_with_magic.networking.ModMessages;
import net.bananaman.it_starts_with_magic.networking.packet.CastSpellC2SPacket;
import net.bananaman.it_starts_with_magic.networking.packet.OpenSpellbookGUIC2SPacket;
import net.bananaman.it_starts_with_magic.screen.gui.SpellbookContainer;
import net.bananaman.it_starts_with_magic.util.keyBinding;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.network.NetworkHooks;

public class ClientEvents {
    @Mod.EventBusSubscriber(modid = ItStartsWithMagicMod.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(keyBinding.CASTING_KEY);
            event.register(keyBinding.OPEN_GUI_KEY);

        }
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            if (keyBinding.OPEN_GUI_KEY.consumeClick()) {
                ModMessages.sendToServer(new OpenSpellbookGUIC2SPacket());
            }


            if (keyBinding.CASTING_KEY.consumeClick()) {
                Player player = Minecraft.getInstance().player;
                if (player != null) {
                    // Check if the player has a spellbook equipped in the custom slot
                    player.getCapability(SpellBookHolderProvider.SPELLBOOK_HOLDER_CAPABILITY).ifPresent(holder -> {
                        ItemStack equippedSpellbook = holder.getSpellbook();
                        // If a spellbook is equipped, send a packet to the server to cast the spell
                        if (!equippedSpellbook.isEmpty()) {
                            ModMessages.sendToServer(new CastSpellC2SPacket());
                        }
                    });
                }
            }
        }
    }
}
