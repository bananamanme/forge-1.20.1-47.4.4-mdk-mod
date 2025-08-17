package net.bananaman.it_starts_with_magic.mana;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class ManaSyncPacket {
    private final int mana;

    public ManaSyncPacket(int mana) {
        this.mana = mana;
    }

    public ManaSyncPacket(FriendlyByteBuf buf) {
        this.mana = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(this.mana);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // Get the player's mana capability on the client and update it
            Minecraft.getInstance().player.getCapability(ManaProvider.MANA_CAPABILITY).ifPresent(mana -> {
                mana.setMana(this.mana);
            });
        });
        return true;
    }
}
