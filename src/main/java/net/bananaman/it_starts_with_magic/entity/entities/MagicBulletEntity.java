package net.bananaman.it_starts_with_magic.entity.entities;


import net.bananaman.it_starts_with_magic.entity.ModEntityTypes;
import net.bananaman.it_starts_with_magic.particle.ModParticles;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;


public class MagicBulletEntity extends ThrowableProjectile {
    private int lifeTime =0;
    private int maxLifeTime = 160;
    private int piercing = 3;

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
        Entity hitEntity = pResult.getEntity();
        hitEntity.hurt(damageSources().magic(),5);
        super.onHitEntity(pResult);
        if (level().isClientSide()) {
            for (int i = 0; i < 13; i++) {
                // Get the location of the hit entity
                double x = hitEntity.getX();
                double y = hitEntity.getY() + hitEntity.getBbHeight() / 2.0;
                double z = hitEntity.getZ();

                // Create random velocities for a scattered effect
                double motionX = (this.random.nextDouble() * 2.0 - 1.0) * 0.2;
                double motionY = (this.random.nextDouble() * 2.0 - 1.0) * 0.2;
                double motionZ = (this.random.nextDouble() * 2.0 - 1.0) * 0.2;

                this.level().addParticle(ParticleTypes.DRAGON_BREATH,
                        x, y, z, motionX, motionY, motionZ);
            }
        }
        piercing--;
        if (piercing == 0){
            this.discard();
            piercing =3;
        }

    }

    // This handles the projectile hitting a block
    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        super.onHitBlock(pResult);
        if (!this.level().isClientSide()) {

            // Get the coordinates of the block hit
            double hitX = pResult.getLocation().x();
            double hitY = pResult.getLocation().y();
            double hitZ = pResult.getLocation().z();

            // Get the direction of the face that was hit
            Direction hitDirection = pResult.getDirection();

            // Use the SERVER's Level to spawn particles that will be
            // automatically synchronized to nearby clients.
            // The cast to ServerLevel is safe because we are on the server side.
            ServerLevel serverLevel = (ServerLevel) this.level();

            // Spawn particles with motion away from the hit face and a random scatter
            for (int i = 0; i < 15; i++) {
                // Get the normal vector of the hit face
                double normalX = hitDirection.getStepX();
                double normalY = hitDirection.getStepY();
                double normalZ = hitDirection.getStepZ();

                // Create a random scatter vector
                double randomX = (this.random.nextDouble() * 2.0 - 1.0) * 0.2;
                double randomY = (this.random.nextDouble() * 2.0 - 1.0) * 0.2;
                double randomZ = (this.random.nextDouble() * 2.0 - 1.0) * 0.2;

                // Combine the normal vector with the random scatter for the final motion.
                double motionX = normalX * 0.1 + randomX;
                double motionY = normalY * 0.1 + randomY;
                double motionZ = normalZ * 0.1 + randomZ;

                // ➡️ Use serverLevel.sendParticles or a similar utility.
                // A simple method is using the ServerLevel's addParticle method,
                // but the preferred 1.20 method is ServerLevel.sendParticles.

                // For simplicity and compatibility with existing code structure:
                // The method below tells the server to spawn 1 particle at the location
                // with the specified motion, which it then sends to clients.
                serverLevel.sendParticles(
                        ParticleTypes.DRAGON_BREATH,
                        hitX, hitY, hitZ,
                        1, // count
                        0.0, 0.0, 0.0, // spread (dx, dy, dz) - 0.0 for point source
                        0.15 // speed, and motion is passed in here
                );
            }
        }

        // DISCARD THE ENTITY LAST
        this.discard();
    }
    @Override
    public void tick() {
        super.tick();
        this.lifeTime++;

        if (this.lifeTime >= this.maxLifeTime) {
            this.discard();
        }

        if (this.level().isClientSide()&& lifeTime % 5 == 0) {

            this.level().addParticle(ModParticles.MODSONICBOOMPARTICLE.get(),
                    this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
        }
    }
}





