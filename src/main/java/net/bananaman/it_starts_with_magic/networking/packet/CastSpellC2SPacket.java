package net.bananaman.it_starts_with_magic.networking.packet;

import net.bananaman.it_starts_with_magic.modstuff.SpellBookHolderProvider;
import net.bananaman.it_starts_with_magic.spells.BoomBeam;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class CastSpellC2SPacket {
    public CastSpellC2SPacket() {}

    public CastSpellC2SPacket(FriendlyByteBuf buffer) {}

    public void toBytes(FriendlyByteBuf buffer) {}

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // We are on the server side now
            ServerPlayer player = context.getSender();
            if (player != null) {
                // Check again to ensure the player has the spellbook equipped
                player.getCapability(SpellBookHolderProvider.SPELLBOOK_HOLDER_CAPABILITY).ifPresent(holder -> {
                    if (!holder.getSpellbook().isEmpty()) {
                        // Attempt to cast the spell
                        BoomBeam.castSpell(player.level(), player);
                    }
                });
            }
        });
        return true;
    }
}
