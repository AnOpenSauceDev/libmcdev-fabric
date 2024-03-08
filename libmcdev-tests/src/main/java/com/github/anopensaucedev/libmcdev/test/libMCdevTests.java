package com.github.anopensaucedev.libmcdev.test;

import com.github.anopensaucedev.libmcdev.test.displayEntity.DeveloperDisplayEntity;
import com.github.anopensaucedev.libmcdevfabric.Debug;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class libMCdevTests implements ModInitializer {

    public static EntityType<DeveloperDisplayEntity> DISPLAY;
    @Override
    public void onInitialize() {

        if(Debug.isDev){
            DISPLAY = Registry.register(
                    Registries.ENTITY_TYPE,
                    new Identifier("libmcdev", "devscreen"),
                    FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, DeveloperDisplayEntity::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
            );
            FabricDefaultAttributeRegistry.register(DISPLAY, DeveloperDisplayEntity.createMobAttributes());
            Test.runTests();
        }
    }
}
