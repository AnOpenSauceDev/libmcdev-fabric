package com.github.anopensaucedev.libmcdevfabric;

import com.github.anopensaucedev.libmcdevfabric.entity.DisplayEntity;
import com.github.anopensaucedev.libmcdevfabric.tests.Test;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.spongepowered.include.com.google.common.base.Charsets;

public class Libmcdev implements ModInitializer {
    /**
     * Runs the mod initializer.
     */

    public static boolean isClient = false;

    public static EntityType<DisplayEntity> DISPLAY;

    @Override
    public void onInitialize() {


        if(Debug.isDev){
             DISPLAY = Registry.register(
                    Registries.ENTITY_TYPE,
                    new Identifier("libmcdev", "devscreen"),
                    FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, DisplayEntity::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
            );
            FabricDefaultAttributeRegistry.register(DISPLAY, DisplayEntity.createMobAttributes());
        }

        Test.runTests();

        //testDebug.Log(new String(testDataHandler.Reader.ReadData("helloworld"), Charsets.UTF_8));

    }
}
