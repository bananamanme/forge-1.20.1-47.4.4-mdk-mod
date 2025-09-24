package net.bananaman.it_starts_with_magic.entity;


import net.bananaman.it_starts_with_magic.particle.ModParticles;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.Optional;


public class MagicBulletEntity extends ThrowableProjectile {
    private int lifeTime =0;

    public MagicBulletEntity(EntityType<? extends ThrowableProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public MagicBulletEntity(Level pLevel, LivingEntity pShooter) {
        super(ModEntityTypes.MAGIC_BULLET.get(), pShooter, pLevel);

        this.setPos(this.getX(),this.getY()-0.2,this.getZ());
    }

    // This method is required when extending ThrowableProjectile
    @Override
    protected void defineSynchedData() { }

    // Override this method to disable gravity
    @Override
    protected float getGravity() {
        return 0.0F;
    }


    // Leave this empty to pass through mobs
    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        pResult.getEntity().hurt(damageSources().magic(),5);
        super.onHitEntity(pResult);
    }

    // This handles the projectile hitting a block
    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        this.discard();
        super.onHitBlock(pResult);
    }
    @Override
    public void tick() {
        super.tick();
        this.lifeTime++;

        if (this.lifeTime >= 160) {
            this.discard();
        }

        if (this.level().isClientSide()&& lifeTime % 5 == 0) {

            this.level().addParticle(ModParticles.MODSONICBOOMPARTICLE.get(),
                    this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
        }
    }
}





