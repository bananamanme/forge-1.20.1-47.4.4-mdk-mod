package net.bananaman.it_starts_with_magic.networking.packet;

import net.bananaman.it_starts_with_magic.screen.gui.SpellbookContainer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PacketDistributor;
import net.minecraft.network.chat.Component;

import java.util.function.Supplier;

public class OpenSpellbookGUIC2SPacket {
    public OpenSpellbookGUIC2SPacket() {
    }

    public OpenSpellbookGUIC2SPacket(FriendlyByteBuf buf) {
    }

    public void toBytes(FriendlyByteBuf buf) {
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player != null) {
                NetworkHooks.openScreen(player, new SimpleMenuProvider(
                        (id, inventory, playerEntity) -> new SpellbookContainer(id, inventory),
                        Component.translatable("gui.it_starts_with_magic.spellbook_screen")
                ));
            }
        });
        return true;
    }
}
