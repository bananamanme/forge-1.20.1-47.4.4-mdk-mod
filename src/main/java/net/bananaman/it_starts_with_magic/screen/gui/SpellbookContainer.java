package net.bananaman.it_starts_with_magic.screen.gui;

import net.bananaman.it_starts_with_magic.modstuff.SpellBookHolderProvider;
import net.bananaman.it_starts_with_magic.screen.ModMenuTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SpellbookContainer extends AbstractContainerMenu {
    private final Player player;
    private final IItemHandler spellbookHandler;

    public SpellbookContainer(int pContainerId, Inventory pPlayerInventory) {
        super(ModMenuTypes.SPELLBOOK_MENU.get(), pContainerId);
        this.player = pPlayerInventory.player; // Get the player from the inventory
        this.spellbookHandler = this.player.getCapability(ForgeCapabilities.ITEM_HANDLER).orElse(null);

        // Add the spellbook slot
        if (this.spellbookHandler != null) {
            this.addSlot(new SlotItemHandler(this.spellbookHandler, 0, 8, 8));
        }

        // Add the player's inventory slots
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                this.addSlot(new Slot(pPlayerInventory, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }

        // Add the player's hotbar slots
        for (int col = 0; col < 9; ++col) {
            this.addSlot(new Slot(pPlayerInventory, col, 8 + col * 18, 142));
        }
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return true;
    }
}
