package net.bananaman.it_starts_with_magic.item.custom;

import net.bananaman.it_starts_with_magic.spells.Spell;

import net.bananaman.it_starts_with_magic.spells.api.SpellRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;
import top.theillusivec4.curios.api.type.inventory.IDynamicStackHandler;

import java.util.List;
import java.util.Optional;

public class TheSpellBook extends Item implements ICurioItem {

    private static final String TAG_SELECTED_SPELL = "SelectedSpell";

    public TheSpellBook(Properties pProperties) {
        super(pProperties);
    }

    public static Spell getSelectedSpell(ItemStack book) {
        String id = book.getOrCreateTag().getString(TAG_SELECTED_SPELL);
        return SpellRegistry.byId(id);
    }

    public static void setSelectedSpell(ItemStack book, Spell spell) {
        book.getOrCreateTag().putString(TAG_SELECTED_SPELL, spell.getId());
    }

    @Override
    public void inventoryTick(ItemStack pStack, Level pLevel, Entity pEntity, int pSlotId, boolean pIsSelected) {
        if (!pLevel.isClientSide && !pStack.getOrCreateTag().contains(TAG_SELECTED_SPELL)) {
            setSelectedSpell(pStack, SpellRegistry.values().iterator().next());
        }

    }

    @Override
    public boolean canEquip(SlotContext slotContext, ItemStack stack) {
        // Only players, only one spell-book at a time
        return slotContext.entity() instanceof net.minecraft.world.entity.player.Player
                && !CuriosApi.getCuriosHelper().findEquippedCurio(this, slotContext.entity()).isPresent();
    }


    @Override
    public boolean canUnequip(SlotContext slotContext, ItemStack stack) {
        return true;
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack held = pPlayer.getItemInHand(pUsedHand);

        // only on server side
        if (!pLevel.isClientSide) {
            Optional<ICuriosItemHandler> opt = CuriosApi.getCuriosHelper()
                    .getCuriosHandler(pPlayer).resolve();
            if (opt.isPresent()) {
                ICuriosItemHandler handler = opt.get();
                // look for any slot that accepts the spellbook
                for (String id : handler.getCurios().keySet()) {
                    IDynamicStackHandler stacks = handler.getCurios().get(id).getStacks();
                    for (int i = 0; i < stacks.getSlots(); i++) {
                        if (stacks.getStackInSlot(i).isEmpty()
                                && stacks.isItemValid(i, held)) {
                            // insert and shrink the held stack
                            stacks.setStackInSlot(i, held.copyWithCount(1));
                            held.shrink(1);
                            pPlayer.swing(pUsedHand);
                            return InteractionResultHolder.sidedSuccess(held, pLevel.isClientSide);
                        }
                    }
                }
            }
        }
        return InteractionResultHolder.pass(held);
    }


    @Override
    public void onEquip(SlotContext slotContext, ItemStack prevStack, ItemStack stack) {
        slotContext.entity().level().playSound(
                null,
                slotContext.entity().blockPosition(),
                SoundEvents.BOOK_PAGE_TURN,
                SoundSource.PLAYERS,
                0.8F, 1.0F
        );
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level,
                                List<Component> lines, TooltipFlag flag) {
        Spell s = TheSpellBook.getSelectedSpell(stack);
        if (s == null) {                       // ← safety check
            lines.add(Component.translatable("spell.none_selected"));
            return;
        }
        lines.add(Component.translatable("spell.selected",
                Component.translatable(s.getDisplayName())));
    }
}
