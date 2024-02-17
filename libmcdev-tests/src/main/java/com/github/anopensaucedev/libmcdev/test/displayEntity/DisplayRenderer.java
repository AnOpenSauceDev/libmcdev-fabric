package com.github.anopensaucedev.libmcdev.test.displayEntity;

import com.github.anopensaucedev.libmcdev.media.MediaPlaceholders;
import com.github.anopensaucedev.libmcdev.media.MCDevURLImage;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

public class DisplayRenderer extends MobEntityRenderer<DeveloperDisplayEntity,DisplayModel> {


    public MCDevURLImage frame;

    private long oldtd,td2 = 0,deltaTime,timeSinceLastFrame;

    public DisplayRenderer(EntityRendererFactory.Context ctx) {
        super(ctx,new DisplayModel(ctx.getPart(DisplayEntityClientInitializer.SCREEN_LAYER)),0.5f);
    }

    @Override
    public void render(DeveloperDisplayEntity screen, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {



        oldtd = td2;
        td2 = Util.getMeasuringTimeNano();
        deltaTime = td2 - oldtd;
        timeSinceLastFrame += deltaTime;

        //Debug.LogInternal("delta: " + deltaTime + " since last frame: " + timeSinceLastFrame + " td2:" + td2 + " oldtd" +oldtd);
        deltaTime = 0;
        if(timeSinceLastFrame > 33000000) { // 33ms in nanoseconds
            frame = screen.storedMovie.playNextFrame();
            timeSinceLastFrame = 0;
        }
        if(screen.storedMovie.frame == screen.storedMovie.images.length){
            screen.storedMovie.frame = 0; // loop our video if we reach the end.
        }
        super.render(screen, f, g, matrixStack, vertexConsumerProvider, i);
        Entity entity = ((MobEntity)screen).getHoldingEntity();


/*
        if (entity == null) {
            return;
        }
        //this.renderLeash(screen, g, matrixStack, vertexConsumerProvider, entity);
*/




    }

    @Override
    public Identifier getTexture(DeveloperDisplayEntity entity) {
        if(this.frame != null){
        return frame.textureID;
        }else {
            return MediaPlaceholders.PLACEHOLDER_URL_IMAGE.textureID;
        }
    }
}
