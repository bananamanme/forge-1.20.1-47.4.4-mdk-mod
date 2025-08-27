package net.bananaman.it_starts_with_magic.networking.packet;

import net.bananaman.it_starts_with_magic.ItStartsWithMagicMod;
import net.bananaman.it_starts_with_magic.item.ModItems;
import net.bananaman.it_starts_with_magic.item.custom.TheSpellBook;
import net.bananaman.it_starts_with_magic.networking.ModMessages;
import net.bananaman.it_starts_with_magic.spells.BoomBeam;
import net.bananaman.it_starts_with_magic.spells.Spell;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.network.NetworkEvent;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.function.Supplier;

import static net.bananaman.it_starts_with_magic.networking.packet.CycleSpellPacket.findSpellBook;

public record CastSpellPacket(String spellid) {
    public static final ResourceLocation ID = new ResourceLocation(ItStartsWithMagicMod.MOD_ID, "cast_spell");


    public static void register() {
        int id = 0;
        ModMessages.INSTANCE.messageBuilder(CastSpellPacket.class, id++)
                .encoder(CastSpellPacket::encode)
                .decoder(CastSpellPacket::decode)
                .consumerMainThread(CastSpellPacket::handle)
                .add();
    }

    public void encode(FriendlyByteBuf buf) { buf.writeUtf(spellid); }

    public static CastSpellPacket decode(FriendlyByteBuf buf) {
        return new CastSpellPacket(buf.readUtf());
    }


    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player == null) return;

            // Only allow casting if the spell book is equipped
            boolean hasBook = CuriosApi.getCuriosHelper()
                    .findEquippedCurio(ModItems.THESPELLBOOK.get(), player).isPresent();

            ItemStack book = findSpellBook(player);   // helper you already wrote
            if (book.isEmpty()) return;

            Spell spell = TheSpellBook.getSelectedSpell(book);
            if (spell != null) {
                spell.cast(player);                   // delegates to BoomBeam, Fireball, etc.
            }
        });
        ctx.get().setPacketHandled(true);
    }

}
