package com.github.anopensaucedev.libmcdev.test;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.DefaultParticleType;

public class DustPuffParticle extends SpriteBillboardParticle {

    public DustPuffParticle(ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
        super(clientWorld, d, e, f, g, h, i);
    }

    @Override
    public void tick(){
        age++;

        scale += 0.01f;

        alpha -=0.001f;

        if(alpha <= 0){
            markDead();
        };
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        public Particle createParticle(DefaultParticleType defaultParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
            DustPuffParticle dustpuff = new DustPuffParticle(clientWorld, d, e, f, g, h, i);
            dustpuff.setAlpha(0.95F);
            dustpuff.scale = 1;
            dustpuff.setSprite(this.spriteProvider);
            double scale = 2;
            dustpuff.setVelocity(libMCdevTests.RANDOM_INSTANCE.nextDouble(-1,1)*scale,libMCdevTests.RANDOM_INSTANCE.nextDouble(0,1)*scale,libMCdevTests.RANDOM_INSTANCE.nextDouble(-1,1)*scale);
            return dustpuff;
        }
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_TRANSLUCENT;
    }
}
