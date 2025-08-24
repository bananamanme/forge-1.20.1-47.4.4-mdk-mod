package net.bananaman.it_starts_with_magic.modstuff;

import net.minecraft.world.item.ItemStack;

public interface ISpellbookHolder {
    ItemStack getSpellbook();
    void setSpellbook(ItemStack stack);
}
