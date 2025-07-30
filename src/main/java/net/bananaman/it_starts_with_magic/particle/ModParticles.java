package net.bananaman.it_starts_with_magic.particle;

import net.bananaman.it_starts_with_magic.ItStartsWithMagicMod;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES,ItStartsWithMagicMod.MOD_ID);

    public static final RegistryObject<SimpleParticleType> MODSONICBOOMPARTICLE =
            PARTICLE_TYPES.register("mod_sonic_boom_particle",()->new SimpleParticleType(true));

    public static void register(IEventBus eventBus){
        PARTICLE_TYPES.register(eventBus);

    }
}
