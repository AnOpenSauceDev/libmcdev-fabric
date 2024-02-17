package com.github.anopensaucedev.libmcdevfabric.mixin;

import com.github.anopensaucedev.libmcdevfabric.render.CameraUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Camera.class)
public class CameraMixin {

    @Shadow private boolean thirdPerson;

    // a hack to render the player when the camera is attached to something else.
    @Inject(method = "isThirdPerson",at=@At("HEAD"),cancellable = true)
    public void renderPlayerHack(CallbackInfoReturnable<Boolean> cir){
        CameraUtils.validateAttachState(MinecraftClient.getInstance());
        cir.setReturnValue(CameraUtils.isAttachedToOtherEntity || thirdPerson);
    }

}
