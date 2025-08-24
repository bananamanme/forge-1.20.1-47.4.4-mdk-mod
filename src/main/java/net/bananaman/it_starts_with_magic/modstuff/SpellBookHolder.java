package net.bananaman.it_starts_with_magic.modstuff;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class SpellBookHolder implements ISpellbookHolder, IItemHandler {
    private final ItemStackHandler inventory = new ItemStackHandler(1) {
        @Override
        protected void onContentsChanged(int slot) {
            // Can be used to mark for saving if needed
        }
    };

    @Override
    public int getSlots() {
        return inventory.getSlots();
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return inventory.getStackInSlot(slot);
    }

    @Override
    public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
        return inventory.insertItem(slot, stack, simulate);
    }

    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        return inventory.extractItem(slot, amount, simulate);
    }

    @Override
    public int getSlotLimit(int slot) {
        return inventory.getSlotLimit(slot);
    }

    @Override
    public boolean isItemValid(int slot, ItemStack stack) {
        return inventory.isItemValid(slot, stack);
    }

    @Override
    public ItemStack getSpellbook() {
        return inventory.getStackInSlot(0);
    }

    @Override
    public void setSpellbook(ItemStack stack) {
        inventory.setStackInSlot(0, stack);
    }

    public void copyFrom(ISpellbookHolder source) {
        this.inventory.setStackInSlot(0, source.getSpellbook().copy());
    }

    public CompoundTag serializeNBT() {
        return inventory.serializeNBT();
    }

    public void deserializeNBT(CompoundTag nbt) {
        inventory.deserializeNBT(nbt);
    }
}
