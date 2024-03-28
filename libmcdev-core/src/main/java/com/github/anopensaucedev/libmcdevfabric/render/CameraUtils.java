package com.github.anopensaucedev.libmcdevfabric.render;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.render.Camera;
import net.minecraft.entity.Entity;
import net.minecraft.world.BlockView;

public class CameraUtils {

  public static Camera CAMERA_INSTANCE;

    // idea based and mostly taken off the (probably going to be closed) Fabric API PR #3155.
    public interface CameraUpdateCallback {
        Event<CameraUpdateCallback> EVENT = EventFactory.createArrayBacked(CameraUpdateCallback.class,((listeners) -> (camera,area,focusedEntity,thirdPerson,inverseView,tickDelta) -> {
            for (CameraUpdateCallback listener: listeners){
                listener.cameraUpdate(camera,area,focusedEntity,thirdPerson,inverseView,tickDelta);
            }
        }));

        void cameraUpdate(Camera camera, BlockView area, Entity focusedEntity, boolean thirdPerson, boolean inverseView, float tickDelta);

    }


}
