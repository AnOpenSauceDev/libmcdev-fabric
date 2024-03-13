package com.github.anopensaucedev.libmcdev.test;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.minecraft.client.particle.FlameParticle;
import net.minecraft.client.particle.Particle;

@Environment(EnvType.CLIENT)
public class libMCdevTestsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ParticleFactoryRegistry.getInstance().register(libMCdevTests.DUST_PUFF,DustPuffParticle.Factory::new);
    }
}
