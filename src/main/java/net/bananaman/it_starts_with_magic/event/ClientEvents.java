package net.bananaman.it_starts_with_magic.event;

import net.bananaman.it_starts_with_magic.ItStartsWithMagicMod;
import net.bananaman.it_starts_with_magic.item.custom.TheSpellBook;
import net.bananaman.it_starts_with_magic.networking.ModMessages;
import net.bananaman.it_starts_with_magic.networking.packet.CastSpellPacket;
import net.bananaman.it_starts_with_magic.networking.packet.CycleSpellPacket;
import net.bananaman.it_starts_with_magic.spells.BoomBeam;
import net.bananaman.it_starts_with_magic.util.keyBinding;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;



public class ClientEvents {
    @Mod.EventBusSubscriber(modid = ItStartsWithMagicMod.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(keyBinding.CASTING_KEY);
            event.register(keyBinding.OPEN_GUI_KEY);
            event.register(keyBinding.NEXT_SPELL_KEY);

        }
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            Player player = Minecraft.getInstance().player;
            if (keyBinding.NEXT_SPELL_KEY.consumeClick()) {
                if (player != null){
                    ModMessages.INSTANCE.sendToServer(new CycleSpellPacket(true));
                }
            }


            if (keyBinding.CASTING_KEY.consumeClick()) {
                if (player != null) {
                    ModMessages.INSTANCE.sendToServer(new CastSpellPacket(BoomBeam.SPELL_ID));
                }
            }
        }
    }


    @SubscribeEvent
    public static void onScroll(InputEvent.MouseScrollingEvent evt) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player == null || !player.isShiftKeyDown()) return;

        ItemStack held = player.getMainHandItem();
        if (!(held.getItem() instanceof TheSpellBook)) return;

        // send one packet to server: "cycle forward or backward"
        boolean forward = evt.getScrollDelta() > 0;
        ModMessages.INSTANCE.sendToServer(new CycleSpellPacket(forward));
        evt.setCanceled(true);        // stop normal item switching
    }
}
