package net.bananaman.it_starts_with_magic.spells;

import net.bananaman.it_starts_with_magic.mana.ManaHelper;
import net.bananaman.it_starts_with_magic.particle.ModParticles;
import net.bananaman.it_starts_with_magic.spells.spellcooldowns.SpellCooldownProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class FireBeam implements Spell {

    public static final String SPELL_ID = "fire_beam";
    public static final int MANA_COST = 20;
    public static final int COOLDOWN_TICKS = 40;

    @Override
    public String getId() {
        return SPELL_ID;
    }

    @Override
    public void cast(ServerPlayer player) {
        FireBeam.castSpell(player.level(), player);
    }

    public static void castSpell(Level level, Player player) {
        if (level.isClientSide) return;

        player.getCapability(SpellCooldownProvider.SPELL_COOLDOWN_CAPABILITY).ifPresent(cooldown -> {
            if (cooldown.isOnCooldown(SPELL_ID, level.getGameTime())) return;

            ManaHelper.consumeManaAndExecute(player, MANA_COST, () -> {
                cooldown.setCooldown(SPELL_ID, level.getGameTime() + COOLDOWN_TICKS);
                shootFireBeam(level, player);
                level.playSound(
                        null,
                        player.getX(), player.getY(), player.getZ(),
                        SoundEvents.BLAZE_SHOOT,
                        SoundSource.PLAYERS,
                        1.0F, 1.0F
                );
            });
        });
    }
    private static void shootFireBeam(Level level, Player player) {
        if (!(level instanceof ServerLevel serverLevel)) return;

        Vec3 look = player.getLookAngle();
        Vec3 start = player.getEyePosition();
        double reach = 32.0;
        double radius = 1.5;
        int steps = (int) (reach * 2);

        for (int i = 0; i <= steps; i++) {
            double frac = (double) i / steps;
            Vec3 center = start.add(look.scale(frac * reach));

            // particles
            serverLevel.sendParticles(ModParticles.MODSONICBOOMPARTICLE.get(),
                    center.x, center.y, center.z, 1, 0, 0.1, 0, 0);

            // cylinder around the current point
            BlockPos centerPos = new BlockPos(Mth.floor(center.x),
                    Mth.floor(center.y),
                    Mth.floor(center.z));

            int r = Mth.ceil(radius);
            for (int dx = -r; dx <= r; dx++) {
                for (int dy = -r; dy <= r; dy++) {
                    for (int dz = -r; dz <= r; dz++) {
                        BlockPos p = centerPos.offset(dx, dy, dz);
                        // only inside sphere
                        if (p.distToCenterSqr(center.x, center.y, center.z) <= radius * radius) {
                            BlockState st = level.getBlockState(p);
                            if (!st.isAir() && st.getDestroySpeed(level, p) >= 0) {
                                level.destroyBlock(p, true, player);
                            }
                        }
                    }
                }
            }
        }
    }
}
