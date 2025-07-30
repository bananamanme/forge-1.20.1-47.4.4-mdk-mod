package net.bananaman.it_starts_with_magic.item.custom;

import net.bananaman.it_starts_with_magic.particle.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
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
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
        if (!pLevel.isClientSide) {
            // Server-side logic for shooting the sonic boom
            shootSonicBoom(pLevel, pPlayer);

            // Add a cooldown
            pPlayer.getCooldowns().addCooldown(this, 40); // 2 second cooldown (20 ticks per second)

            // Optional: Play a sound when the player uses the item
            pLevel.playSound(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(),
                    SoundEvents.WARDEN_SONIC_CHARGE, SoundSource.PLAYERS, 1.0F, 1.0F);

        }

        return InteractionResultHolder.sidedSuccess(stack, pLevel.isClientSide());
    }

    private void shootSonicBoom(Level level, Player player) {
        if (level instanceof ServerLevel serverLevel) {
            Vec3 playerLook = player.getLookAngle();
            Vec3 startPos = player.getEyePosition();
            double reach = 32.0; // How far the sonic boom travels
            double beamThickness = 0.6; // Radius of the beam's damage area

            serverLevel.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.WARDEN_SONIC_BOOM, SoundSource.HOSTILE, 3.0F, 1.0F);

            DamageSource sonicDamageSource = player.damageSources().sonicBoom(player);
            Set<LivingEntity> hitEntities = new HashSet<>();

            int steps = (int) (reach * 2); // More steps for smoother beam and more frequent checks
            for (int i = 0; i <= steps; i++) {
                double fraction = (double) i / steps;
                Vec3 currentPos = startPos.lerp(startPos.add(playerLook.scale(reach)), fraction);

                // Spawn the original SONIC_BOOM particlew
                serverLevel.sendParticles(ModParticles.MODSONICBOOMPARTICLE.get(), currentPos.x, currentPos.y, currentPos.z,
                        1, // count
                        0.0, 0.1, 0.0, // dx, dy, dz (velocity randomness)
                        0.0); // speed


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
