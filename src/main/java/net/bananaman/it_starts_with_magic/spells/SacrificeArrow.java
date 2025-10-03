package net.bananaman.it_starts_with_magic.spells;

import net.bananaman.it_starts_with_magic.entity.entities.SacrificeArrowEntity;
import net.bananaman.it_starts_with_magic.mana.ManaHelper;
import net.bananaman.it_starts_with_magic.spells.api.spellcooldowns.SpellCooldownProvider;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class SacrificeArrow implements Spell{
    public static final String SPELL_ID = "sacrifice_arrow";
    public static final int MANA_COST = 10;
    public static final int COOLDOWN_TICKS = 20;


    @Override
    public String getId() {
        return SPELL_ID;
    }

    @Override
    public void cast(ServerPlayer player) {
        SacrificeArrow.castSpell(player.level(), player);


    }

    public static void castSpell(Level level, Player player) {
        if (level.isClientSide) {
            return;
        }
        player.getCapability(SpellCooldownProvider.SPELL_COOLDOWN_CAPABILITY).ifPresent(cooldown -> {
            if (cooldown.isOnCooldown(SPELL_ID, level.getGameTime())) {
                // Do nothing, spell is on cooldown
                return;
            }
            ManaHelper.consumeManaAndExecute(player, MANA_COST, () -> {
                // This code only runs if the ManaHelper successfully consumes mana.

                // Set the cooldown on the custom capability
                cooldown.setCooldown(SPELL_ID, level.getGameTime() + COOLDOWN_TICKS);

                shootSacrificeArrow(level, player);
                level.playSound(null, player.getX(), player.getY(), player.getZ(),
                        SoundEvents.BLAZE_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F);
            });
        });



    }

    public static void shootSacrificeArrow(Level level, Player player) {
        if (!level.isClientSide) { // Only run on the server
            SacrificeArrowEntity sacrificeArrow = new SacrificeArrowEntity(level, player);
            sacrificeArrow.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
            level.addFreshEntity(sacrificeArrow); // Spawn the entity into the world
        }
    }
}
