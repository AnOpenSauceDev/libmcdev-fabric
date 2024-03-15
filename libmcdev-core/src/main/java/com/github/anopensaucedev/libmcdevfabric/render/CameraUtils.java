package com.github.anopensaucedev.libmcdevfabric.render;

import com.github.anopensaucedev.libmcdevfabric.Debug;
import com.github.anopensaucedev.libmcdevfabric.hacks.IServerPlayerEntityMixin;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MarkerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.joml.Vector3d;

import java.util.List;

public class CameraUtils {

    public static void FocusCameraAtPoint(Camera camera){

    }

    public static MarkerEntity cameraTracker;

    /**
     * focuses the camera towards the player, at the mean of where all entities are. Must be called on server.
     */
    public static Entity CreateMeanOfEntities(World world, List<Entity> entities){
        if (world != null) {

            if(entities.isEmpty()) return null;

            if(cameraTracker == null){
                 cameraTracker = new MarkerEntity(EntityType.MARKER,world);
                Debug.LogInternal("creating new camera tracker @ ",cameraTracker.getPos().toString());
                world.spawnEntity(cameraTracker);
            }

            Vector3d pos = new Vector3d(0,0,0); // "x" is final in Vec3d, why?

            for (Entity entity : entities) {
                pos.x += entity.getX(); // only this worked properly for some reason
                pos.y += entity.getY();
                pos.z += entity.getZ();
            }

            Vec3d meanPos = new Vec3d(pos.x/entities.size(), pos.y/entities.size(), pos.z/entities.size());



            cameraTracker.setPos(meanPos.x,meanPos.y,meanPos.z);
            //cameraTracker.lookAt(EntityAnchorArgumentType.EntityAnchor.EYES,client.player.getEyePos());

            return cameraTracker;
        }
        return null;
    }

    // Other Entity in this case being anything but our own player
    public static boolean isAttachedToOtherEntity = false;

    public static void setCameraAsEntity(ServerPlayerEntity player, Entity target){
        if(target == null){
            Debug.InternalLogError("Nonexistent entity called!");
            return;
        }
        ((IServerPlayerEntityMixin) (Object) player).SetCameraEntityProperly(target); // yummy mixin
        validateAttachState(player);
    }

    public static void validateAttachState(ServerPlayerEntity player){
        if(player.getCameraEntity() != player){
            isAttachedToOtherEntity = true;
        }else {
            isAttachedToOtherEntity = false;
        }
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
