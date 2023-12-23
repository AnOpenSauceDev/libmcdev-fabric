package com.github.anopensaucedev.libmcdevfabric.render;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.entity.Entity;

public class CameraUtils {

    public static void FocusCameraAtPoint(Camera camera){

    }

    // Other Entity in this case being anything but our own player
    public static boolean isAttachedToOtherEntity = false;

    public static void setCameraAsEntity(MinecraftClient client, Entity target){
        client.cameraEntity = target;
        validateAttachState(client);
    }

    public static void validateAttachState(MinecraftClient client){
        if(client.cameraEntity != client.player){
            isAttachedToOtherEntity = true;
        }else {
            isAttachedToOtherEntity = false;
        }
    }

    public static void resetCameraToPlayer(MinecraftClient client, Entity target){
        client.cameraEntity = client.player;
        isAttachedToOtherEntity = false;
    }

}
