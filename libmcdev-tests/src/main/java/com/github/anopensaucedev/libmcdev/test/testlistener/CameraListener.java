package com.github.anopensaucedev.libmcdev.test.testlistener;

import com.github.anopensaucedev.libmcdevfabric.entity.EntityUtils;
import com.github.anopensaucedev.libmcdevfabric.entity.HelperPredicates;
import com.github.anopensaucedev.libmcdevfabric.render.CameraUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.entity.Entity;
import net.minecraft.world.BlockView;
import org.joml.Vector3f;

public class CameraListener implements CameraUtils.CameraUpdateCallback {

    MinecraftClient client = MinecraftClient.getInstance();
    @Override
    public void cameraUpdate(Camera camera, BlockView area, Entity focusedEntity, boolean thirdPerson, boolean inverseView, float tickDelta) {

        var player = client.player;

        if(player != null){
            if(client.gameRenderer.getCamera().isThirdPerson()){

                var entities = EntityUtils.getEntitiesInRadiusIncludingSource(HelperPredicates.IS_DEAD.negate(),25,player);
                Entity[] entities1 = entities.toArray(new Entity[0]);
                var meanpos = EntityUtils.CreateMeanPosOfEntities(true,entities1);

                if(entities1.length != 0){
                Vector3f pos = new Vector3f(0,0,3).negate().rotate(camera.getRotation()); // move back 3
                    pos.div(4);
                    var finalpos = camera.getPos().add(pos.x,pos.y,pos.z);
                camera.setPos(finalpos.x,finalpos.y,finalpos.z);
                }

            }
        }

    }
}
