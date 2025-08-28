package net.bananaman.it_starts_with_magic.spells;

import net.bananaman.it_starts_with_magic.mana.ManaHelper;
import net.bananaman.it_starts_with_magic.particle.ModParticles;
import net.bananaman.it_starts_with_magic.spells.api.spellcooldowns.SpellCooldownProvider;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BoomBeam implements Spell {

    public static final String SPELL_ID = "sonic_boom";
    public static final int MANA_COST = 20;
    public static final int COOLDOWN_TICKS = 40;

    @Override public String getId() { return SPELL_ID; }

    @Override
    public void cast(ServerPlayer player) {
        BoomBeam.castSpell(player.level(), player);
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

            // This code will run if the spell is not on cooldown.
            ManaHelper.consumeManaAndExecute(player, MANA_COST, () -> {
                // This code only runs if the ManaHelper successfully consumes mana.

                // Set the cooldown on the custom capability
                cooldown.setCooldown(SPELL_ID, level.getGameTime() + COOLDOWN_TICKS);

                shootSonicBoom(level, player);
                level.playSound(null, player.getX(), player.getY(), player.getZ(),
                        SoundEvents.WARDEN_SONIC_CHARGE, SoundSource.PLAYERS, 1.0F, 1.0F);
            });
        });
    }

    private static void shootSonicBoom(Level level, Player player) {
        if (level instanceof ServerLevel serverLevel) {
            Vec3 playerLook = player.getLookAngle();
            Vec3 startPos = player.getEyePosition();
            double reach = 32.0;
            double beamThickness = 0.6;

            serverLevel.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.WARDEN_SONIC_BOOM, SoundSource.HOSTILE, 3.0F, 1.0F);

            DamageSource sonicDamageSource = player.damageSources().sonicBoom(player);
            Set<LivingEntity> hitEntities = new HashSet<>();

            int steps = (int) (reach * 2);
            for (int i = 0; i <= steps; i++) {
                double fraction = (double) i / steps;
                Vec3 currentPos = startPos.lerp(startPos.add(playerLook.scale(reach)), fraction);

                serverLevel.sendParticles(ModParticles.MODSONICBOOMPARTICLE.get(), currentPos.x, currentPos.y, currentPos.z,
                        1, 0.0, 0.1, 0.0, 0.0);

                AABB checkArea = new AABB(currentPos.x - beamThickness, currentPos.y - beamThickness, currentPos.z - beamThickness,
                        currentPos.x + beamThickness, currentPos.y + beamThickness, currentPos.z + beamThickness);

                List<LivingEntity> entitiesInArea = serverLevel.getEntitiesOfClass(LivingEntity.class, checkArea,
                        (entity) -> entity != player && entity.isAlive() && !hitEntities.contains(entity));

                for (LivingEntity entity : entitiesInArea) {
                    entity.hurt(sonicDamageSource, 15.0F);
                    hitEntities.add(entity);
                }
            }
        }
    }
}
