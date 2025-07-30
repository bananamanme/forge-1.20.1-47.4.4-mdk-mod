package net.bananaman.it_starts_with_magic.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import org.jetbrains.annotations.Nullable;

public class ModSonicBoomParticle extends HugeExplosionParticle {


    protected ModSonicBoomParticle(ClientLevel pLevel, double pX, double pY, double pZ, double pQuadSizeMultiplier, SpriteSet spriteSet) {
        super(pLevel, pX, pY, pZ, pQuadSizeMultiplier, spriteSet);
        this.lifetime = 32;
        this.quadSize = 1.5f;
        this.friction = 0.8f;
        this.setSpriteFromAge(spriteSet);

        this.rCol=1f;
        this.gCol=1f;
        this.bCol=1f;
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }


    public static class Provider implements ParticleProvider<SimpleParticleType>{
        private final SpriteSet spriteSet;

        public Provider(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType simpleParticleType, ClientLevel clientLevel, double v, double v1, double v2, double v3, double v4, double v5) {
            return new ModSonicBoomParticle(clientLevel,v,v1,v2,v3,this.spriteSet);
        }
    }
}
