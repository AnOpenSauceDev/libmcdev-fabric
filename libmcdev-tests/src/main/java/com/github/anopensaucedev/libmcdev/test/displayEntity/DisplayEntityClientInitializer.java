package com.github.anopensaucedev.libmcdev.test.displayEntity;

import com.github.anopensaucedev.libmcdev.test.libMCdevTests;
import com.github.anopensaucedev.libmcdevfabric.Debug;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class DisplayEntityClientInitializer implements ClientModInitializer {

    public static final EntityModelLayer SCREEN_LAYER = new EntityModelLayer(new Identifier("libmcdev", "devscreen"), "main");

    @Override
    public void onInitializeClient() {
        if(Debug.isDev) {
            EntityRendererRegistry.INSTANCE.register(libMCdevTests.DISPLAY, DisplayRenderer::new);
            EntityModelLayerRegistry.registerModelLayer(SCREEN_LAYER, DisplayModel::getTexturedModelData);

        }
    }
}
