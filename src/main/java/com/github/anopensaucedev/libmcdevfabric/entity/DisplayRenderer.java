package com.github.anopensaucedev.libmcdevfabric.entity;

import com.github.anopensaucedev.libmcdevfabric.client.LibmcdevClient;
import com.github.anopensaucedev.libmcdevfabric.media.HudRenderCallbackListener;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class DisplayRenderer extends MobEntityRenderer<DisplayEntity,DisplayModel> {
    public DisplayRenderer(EntityRendererFactory.Context ctx) {
        super(ctx,new DisplayModel(ctx.getPart(LibmcdevClient.SCREEN_LAYER)),0.5f);
    }

    @Override
    public Identifier getTexture(DisplayEntity entity) {
        return HudRenderCallbackListener.currentImage.textureID;
    }
}
