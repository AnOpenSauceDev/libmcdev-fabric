package com.github.anopensaucedev.libmcdevfabric.client;

import com.github.anopensaucedev.libmcdevfabric.Libmcdev;
import com.github.anopensaucedev.libmcdevfabric.entity.DisplayModel;
import com.github.anopensaucedev.libmcdevfabric.entity.DisplayRenderer;
import com.github.anopensaucedev.libmcdevfabric.media.HudRenderCallbackListener;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class LibmcdevClient implements ClientModInitializer {

    public static final EntityModelLayer SCREEN_LAYER = new EntityModelLayer(new Identifier("libmcdev", "devscreen"), "main");

    /**
     * Runs the mod initializer on the client environment.
     */
    @Override
    public void onInitializeClient() {
        Libmcdev.isClient = true; // race conditions shouldn't be a problem, as long as you're careful.
        HudRenderCallbackListener listener = new HudRenderCallbackListener();

        HudRenderCallback.EVENT.register(listener);

        EntityRendererRegistry.INSTANCE.register(Libmcdev.DISPLAY, (context) -> {
            return new DisplayRenderer(context);
        });

        EntityModelLayerRegistry.registerModelLayer(SCREEN_LAYER, DisplayModel::getTexturedModelData);

    }
}
