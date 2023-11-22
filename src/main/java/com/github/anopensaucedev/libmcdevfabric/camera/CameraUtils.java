package com.github.anopensaucedev.libmcdevfabric.camera;

import net.minecraft.client.MinecraftClient;

public class CameraUtils {

    public static void DetachCamera(MinecraftClient client){

        client.cameraEntity.detach();

    }
}
