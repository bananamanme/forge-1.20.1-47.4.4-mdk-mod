package net.bananaman.it_starts_with_magic.mana;

import net.bananaman.it_starts_with_magic.networking.ModMessages;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public class ManaHelper {
    public static boolean consumeManaAndExecute(Player player, int manaCost, Runnable action) {
        if (player.level().isClientSide) {
            return false;
        }
        if (player.isCreative()) {
            action.run();
            return true;
        }

        return player.getCapability(ManaProvider.MANA_CAPABILITY).map(mana -> {
            if (mana.consumeMana(manaCost)) {
                ModMessages.sendToPlayer(new ManaSyncPacket(mana.getMana()), (ServerPlayer) player);
                action.run();
                return true;
            } else {
                ModMessages.sendToPlayer(new NotEnoughManaPacket(), (ServerPlayer) player);
                return false;
            }
        }).orElse(false);
    }
}
