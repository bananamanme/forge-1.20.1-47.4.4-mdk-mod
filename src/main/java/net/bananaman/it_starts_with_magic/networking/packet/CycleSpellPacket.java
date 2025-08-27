package net.bananaman.it_starts_with_magic.networking.packet;

import it.unimi.dsi.fastutil.Pair;
import net.bananaman.it_starts_with_magic.item.ModItems;
import net.bananaman.it_starts_with_magic.item.custom.TheSpellBook;
import net.bananaman.it_starts_with_magic.networking.ModMessages;
import net.bananaman.it_starts_with_magic.spells.Spell;
import net.bananaman.it_starts_with_magic.spells.api.SpellRegistry;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static net.bananaman.it_starts_with_magic.networking.ModMessages.id;


public record CycleSpellPacket(boolean next) {
    public static void register() {
        ModMessages.INSTANCE.registerMessage(
                id(),
                CycleSpellPacket.class,
                (pkt, buf) -> buf.writeBoolean(pkt.next),   // <-- here
                buf -> new CycleSpellPacket(buf.readBoolean()),
                (pkt, ctx) -> {
                    ctx.get().enqueueWork(() -> {
                        ServerPlayer p = ctx.get().getSender();
                        ItemStack book = findSpellBook(p);
                        if (book.isEmpty()) return;

                        List<Spell> list = new ArrayList<>(SpellRegistry.values());
                        Spell current = TheSpellBook.getSelectedSpell(book);
                        int idx = list.indexOf(current);
                        int newIdx = pkt.next()               // <-- and here
                                ? (idx + 1) % list.size()
                                : (idx - 1 + list.size()) % list.size();
                        TheSpellBook.setSelectedSpell(book, list.get(newIdx));
                    });
                    ctx.get().setPacketHandled(true);
                });
    }

    static ItemStack findSpellBook(Player pPlayer) {
        // 1. held in main hand
        ItemStack held = pPlayer.getMainHandItem();
        if (held.getItem() == ModItems.THESPELLBOOK.get()) {
            return held;
        }

        // 2. in curios
        Optional<ItemStack> curio = CuriosApi.getCuriosHelper()
                .findEquippedCurio(ModItems.THESPELLBOOK.get(), pPlayer)
                .map(pair -> pair.getRight());
        return curio.orElse(ItemStack.EMPTY);
    }
}

