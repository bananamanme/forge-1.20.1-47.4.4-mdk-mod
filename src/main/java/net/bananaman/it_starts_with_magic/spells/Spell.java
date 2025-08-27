package net.bananaman.it_starts_with_magic.spells;

import net.minecraft.server.level.ServerPlayer;

public interface Spell {
    String getId();

    void cast(ServerPlayer player);

    default String getDisplayName() {
        return "spell." + getId();
    }
}
