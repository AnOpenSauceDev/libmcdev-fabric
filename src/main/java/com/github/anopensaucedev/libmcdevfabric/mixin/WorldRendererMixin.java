package com.github.anopensaucedev.libmcdevfabric.mixin;

import com.github.anopensaucedev.libmcdevfabric.render.CameraUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {

    // MC checks if the entity...
    // is the camera
    // and is NOT in 3rd person
    // and is NOT a client entity
    // we do a neat trick by not caring if the camera instance is attached via CameraUtils.
    @Redirect(method = "render",at = @At(value = "INVOKE",target = "Lnet/minecraft/client/render/Camera;getFocusedEntity()Lnet/minecraft/entity/Entity;"))
    public Entity getFocusedEntity(Camera instance){
        if(CameraUtils.isAttachedToOtherEntity){
            return MinecraftClient.getInstance().player;
        }else {
            return MinecraftClient.getInstance().gameRenderer.getCamera().getFocusedEntity();
        }
    }
}
