package com.github.anopensaucedev.libmcdev.test;

import com.github.anopensaucedev.libmcdev.test.displayEntity.DeveloperDisplayEntity;
import com.github.anopensaucedev.libmcdev.test.testlistener.CameraListener;
import com.github.anopensaucedev.libmcdev.test.testlistener.HudRenderCallbackListener;
import com.github.anopensaucedev.libmcdevfabric.Debug;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.concurrent.ThreadLocalRandom;

public class libMCdevTests implements ModInitializer {

    public static EntityType<DeveloperDisplayEntity> DISPLAY;

    public static String TESTS_ID = "libmcdev-tests";

    public static ThreadLocalRandom RANDOM_INSTANCE = ThreadLocalRandom.current();


    public static final DefaultParticleType DUST_PUFF = FabricParticleTypes.simple();

    @Override
    public void onInitialize() {

        if(Debug.isDev){

            Registry.register(Registries.PARTICLE_TYPE, new Identifier(TESTS_ID,"dust_puff"),DUST_PUFF);

            HudRenderCallbackListener.EVENT.register(new HudRenderCallbackListener());

            DISPLAY = Registry.register(
                    Registries.ENTITY_TYPE,
                    new Identifier("libmcdev", "devscreen"),
                    FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, DeveloperDisplayEntity::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
            );
            FabricDefaultAttributeRegistry.register(DISPLAY, DeveloperDisplayEntity.createMobAttributes());
            Test.runTests();

            CameraListener.EVENT.register(new CameraListener());
        }
    }
}
