package net.bananaman.it_starts_with_magic.networking;

import net.bananaman.it_starts_with_magic.ItStartsWithMagicMod;
import net.bananaman.it_starts_with_magic.mana.ManaSyncPacket;
import net.bananaman.it_starts_with_magic.mana.NotEnoughManaPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessages {
    private static SimpleChannel INSTANCE;
    private static int packetId = 0;

    private static int id() {
        return packetId++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(ItStartsWithMagicMod.MOD_ID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.registerMessage(id(), ManaSyncPacket.class, ManaSyncPacket::toBytes, ManaSyncPacket::new, ManaSyncPacket::handle);
        net.registerMessage(id(), NotEnoughManaPacket.class, NotEnoughManaPacket::toBytes, NotEnoughManaPacket::new, NotEnoughManaPacket::handle);
    }

    public static <MSG> void sendToPlayer(MSG message, net.minecraft.server.level.ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
}
