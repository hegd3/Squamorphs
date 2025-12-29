package com.hedge.squamorphs.client.particle.sonic_boom;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SonicBoomParticle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.FastColor;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ElementalSonicBoomParticle extends SonicBoomParticle {
    protected ElementalSonicBoomParticle(ClientLevel pLevel, double pX, double pY, double pZ, double pQuadSizeMultiplier, SpriteSet pSprites, float r, float g, float b) {
        super(pLevel, pX, pY, pZ, pQuadSizeMultiplier, pSprites);
        this.quadSize = 0.55f;
        this.setColor(r, g, b);
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<ElementalSonicBoomParticleOptions> {
        private final SpriteSet sprites;

        public Provider(SpriteSet pSprites) {
            this.sprites = pSprites;
        }

        public Particle createParticle(ElementalSonicBoomParticleOptions options, ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
            return new ElementalSonicBoomParticle(pLevel, pX, pY, pZ, pXSpeed, this.sprites, options.getColor().x, options.getColor().y, options.getColor().z);
        }

    }

}
