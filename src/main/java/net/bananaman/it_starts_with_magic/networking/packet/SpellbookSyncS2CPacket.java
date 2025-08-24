package net.bananaman.it_starts_with_magic.networking.packet;

import net.bananaman.it_starts_with_magic.modstuff.SpellBookHolderProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;
import java.util.function.Supplier;

public class SpellbookSyncS2CPacket {
    private final ItemStack spellbookStack;

    public SpellbookSyncS2CPacket(ItemStack stack) {
        this.spellbookStack = stack;
    }

    // This constructor is used when the packet is received by the client
    public SpellbookSyncS2CPacket(FriendlyByteBuf buf) {
        this.spellbookStack = buf.readItem();
    }

    // This method writes the packet data to a buffer
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeItem(this.spellbookStack);
    }

    // This method handles the packet on the client side
    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // Get the current player on the client side
            if (Minecraft.getInstance().player != null) {
                Minecraft.getInstance().player.getCapability(SpellBookHolderProvider.SPELLBOOK_HOLDER_CAPABILITY).ifPresent(holder -> {
                    // Update the client's capability with the item stack from the packet
                    holder.setSpellbook(this.spellbookStack);
                });
            }
        });
        return true;
    }
}
