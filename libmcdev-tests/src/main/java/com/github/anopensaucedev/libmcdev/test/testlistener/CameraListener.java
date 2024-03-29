package com.github.anopensaucedev.libmcdev.test.testlistener;

import com.github.anopensaucedev.libmcdevfabric.entity.EntityUtils;
import com.github.anopensaucedev.libmcdevfabric.entity.HelperPredicates;
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

                var entities = EntityUtils.getEntitiesInRadius(HelperPredicates.NOT_PLAYER,10,player);
                Entity[] entities1 = entities.toArray(new Entity[0]);
                var meanpos = EntityUtils.CreateMeanPosOfEntities(true,entities1);

                if(entities1.length != 0){
                camera.setPos(meanpos.x,meanpos.y,meanpos.z);
                }

            }
        }

    }
}
