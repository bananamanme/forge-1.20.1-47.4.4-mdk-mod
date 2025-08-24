package net.bananaman.it_starts_with_magic.networking;

import net.bananaman.it_starts_with_magic.ItStartsWithMagicMod;
import net.bananaman.it_starts_with_magic.mana.ManaSyncPacket;
import net.bananaman.it_starts_with_magic.mana.NotEnoughManaPacket;
import net.bananaman.it_starts_with_magic.networking.packet.CastSpellC2SPacket;
import net.bananaman.it_starts_with_magic.networking.packet.OpenSpellbookGUIC2SPacket;
import net.bananaman.it_starts_with_magic.networking.packet.SpellbookSyncS2CPacket;
import net.bananaman.it_starts_with_magic.networking.packet.UnEquipSpellbookC2SPacket;
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
        net.registerMessage(id(), SpellbookSyncS2CPacket.class, SpellbookSyncS2CPacket::toBytes, SpellbookSyncS2CPacket::new, SpellbookSyncS2CPacket::handle);
        net.registerMessage(id(), CastSpellC2SPacket.class, CastSpellC2SPacket::toBytes, CastSpellC2SPacket::new, CastSpellC2SPacket::handle);
        net.registerMessage(id(), UnEquipSpellbookC2SPacket.class, UnEquipSpellbookC2SPacket::toBytes, UnEquipSpellbookC2SPacket::new, UnEquipSpellbookC2SPacket::handle);
        net.registerMessage(id(), OpenSpellbookGUIC2SPacket.class, OpenSpellbookGUIC2SPacket::toBytes, OpenSpellbookGUIC2SPacket::new, OpenSpellbookGUIC2SPacket::handle);
    }

    public static <MSG> void sendToPlayer(MSG message, net.minecraft.server.level.ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }
}
