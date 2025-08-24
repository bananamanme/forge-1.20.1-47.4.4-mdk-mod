package net.bananaman.it_starts_with_magic.networking.packet;

import net.bananaman.it_starts_with_magic.modstuff.SpellBookHolderProvider;
import net.bananaman.it_starts_with_magic.networking.ModMessages;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class UnEquipSpellbookC2SPacket {
    public UnEquipSpellbookC2SPacket() {}

    public UnEquipSpellbookC2SPacket(FriendlyByteBuf buffer) {}

    public void toBytes(FriendlyByteBuf buffer) {}

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player != null) {
                player.getCapability(SpellBookHolderProvider.SPELLBOOK_HOLDER_CAPABILITY).ifPresent(holder -> {
                    ItemStack equippedSpellbook = holder.getSpellbook();
                    if (!equippedSpellbook.isEmpty()) {
                        // Attempt to add the item back to the player's inventory
                        if (player.getInventory().add(equippedSpellbook)) {
                            holder.setSpellbook(ItemStack.EMPTY);
                            // Synchronize the change to the client
                            ModMessages.sendToPlayer(new SpellbookSyncS2CPacket(ItemStack.EMPTY), player);
                        }
                    }
                });
            }
        });
        return true;
    }
}
