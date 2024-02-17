package com.github.anopensaucedev.libmcdevfabric.render;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.command.argument.EntityAnchorArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MarkerEntity;
import net.minecraft.util.math.Vec3d;
import org.joml.Quaterniondc;
import org.joml.Vector3d;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class CameraUtils {

    public static void FocusCameraAtPoint(Camera camera){

    }

    /**
     * focuses the camera towards the player, at the mean of where all entities are.
     */
    public static void CreateMeanOfEntities(MinecraftClient client, List<Entity> entities){
        if (client.world != null) {
            MarkerEntity temp = new MarkerEntity(EntityType.MARKER,client.world);

            Vec3d pos = new Vec3d(0,0,0);

            for (int i = 0; i < entities.size(); i++) {
                pos.add(entities.get(i).getPos());
            }

            Vec3d meanPos = new Vec3d(pos.x/entities.size(), pos.y/entities.size(), pos.z/entities.size());



            temp.setPos(meanPos.x,meanPos.y,meanPos.z);
            temp.lookAt(EntityAnchorArgumentType.EntityAnchor.EYES,client.player.getEyePos());

            client.world.addEntity(temp);
        }

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
