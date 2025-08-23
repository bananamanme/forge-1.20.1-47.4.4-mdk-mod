package net.bananaman.it_starts_with_magic.item.custom;

import net.bananaman.it_starts_with_magic.mana.ManaHelper;
import net.bananaman.it_starts_with_magic.mana.ManaProvider;
import net.bananaman.it_starts_with_magic.mana.ManaSyncPacket;
import net.bananaman.it_starts_with_magic.modstuff.SpellBookHolderProvider;
import net.bananaman.it_starts_with_magic.networking.ModMessages;
import net.bananaman.it_starts_with_magic.networking.packet.SpellbookSyncS2CPacket;
import net.bananaman.it_starts_with_magic.particle.ModParticles;
import net.bananaman.it_starts_with_magic.spells.BoomBeam;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TheSpellBook extends Item {
    public TheSpellBook(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack itemStack = pPlayer.getItemInHand(pUsedHand);

        // Server-side logic only
        if (!pLevel.isClientSide()) {

            // Get the player's capability
            pPlayer.getCapability(SpellBookHolderProvider.SPELLBOOK_HOLDER_CAPABILITY).ifPresent(holder -> {
                ItemStack equippedSpellbook = holder.getSpellbook();

                // If the equipped slot is empty, equip the current spellbook
                if (equippedSpellbook.isEmpty()) {
                    holder.setSpellbook(itemStack.copy());
                    pPlayer.setItemInHand(pUsedHand, ItemStack.EMPTY);
                } else {
                    // If the equipped slot is not empty, swap the items
                    holder.setSpellbook(itemStack.copy());
                    pPlayer.setItemInHand(pUsedHand, equippedSpellbook.copy());
                }

                // Synchronize the change to the client
                if (pPlayer instanceof ServerPlayer serverPlayer) {
                    ModMessages.sendToPlayer(new SpellbookSyncS2CPacket(holder.getSpellbook()), serverPlayer);
                }
            });
        }

        return InteractionResultHolder.success(itemStack);
    }

}
