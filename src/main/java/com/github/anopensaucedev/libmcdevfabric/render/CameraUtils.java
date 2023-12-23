package com.github.anopensaucedev.libmcdevfabric.render;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.entity.Entity;

public class CameraUtils {

    public static void FocusCameraAtPoint(Camera camera){

    }

    public static void setCameraAsEntity(MinecraftClient client, Entity target){
        client.cameraEntity = target;
    }

    public static void resetCameraToPlayer(MinecraftClient client, Entity target){
        client.cameraEntity = client.player;
    }

}
