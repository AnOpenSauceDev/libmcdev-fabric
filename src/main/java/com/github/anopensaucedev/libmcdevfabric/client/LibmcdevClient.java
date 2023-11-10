package com.github.anopensaucedev.libmcdevfabric.client;

import com.github.anopensaucedev.libmcdevfabric.Libmcdev;
import com.github.anopensaucedev.libmcdevfabric.graphics.HudRenderCallbackListener;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;

@Environment(EnvType.CLIENT)
public class LibmcdevClient implements ClientModInitializer {
    /**
     * Runs the mod initializer on the client environment.
     */
    @Override
    public void onInitializeClient() {
        Libmcdev.isClient = true; // race conditions shouldn't be a problem, as long as you're careful.
        HudRenderCallbackListener listener = new HudRenderCallbackListener();

        HudRenderCallback.EVENT.register(listener);

    }
}
