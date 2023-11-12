package com.github.anopensaucedev.libmcdevfabric.entity;

import com.github.anopensaucedev.libmcdevfabric.Debug;
import com.github.anopensaucedev.libmcdevfabric.Placeholders;
import com.github.anopensaucedev.libmcdevfabric.client.LibmcdevClient;
import com.github.anopensaucedev.libmcdevfabric.media.HudRenderCallbackListener;
import com.github.anopensaucedev.libmcdevfabric.media.MCDevURLImage;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

public class DisplayRenderer extends MobEntityRenderer<DisplayEntity,DisplayModel> {


    public MCDevURLImage frame;

    private long oldtd,td2 = 0,deltaTime,timeSinceLastFrame;

    public DisplayRenderer(EntityRendererFactory.Context ctx) {
        super(ctx,new DisplayModel(ctx.getPart(LibmcdevClient.SCREEN_LAYER)),0.5f);
    }

    @Override
    public void render(DisplayEntity screen, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {



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
    public Identifier getTexture(DisplayEntity entity) {
        if(this.frame != null){
        return frame.textureID;
        }else {
            return Placeholders.PLACEHOLDER_URL_IMAGE.textureID;
        }
    }
}
