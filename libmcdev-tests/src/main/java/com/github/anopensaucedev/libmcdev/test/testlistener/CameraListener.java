package com.github.anopensaucedev.libmcdev.test.testlistener;

import com.github.anopensaucedev.libmcdevfabric.render.CameraUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.entity.Entity;
import net.minecraft.world.BlockView;

public class CameraListener implements CameraUtils.CameraUpdateCallback {

    MinecraftClient client = MinecraftClient.getInstance();
    @Override
    public void cameraUpdate(Camera camera, BlockView area, Entity focusedEntity, boolean thirdPerson, boolean inverseView, float tickDelta) {

        var player = client.player;

        if(player != null){
            if(player.isSneaking()){



            }
        }

    }
}
