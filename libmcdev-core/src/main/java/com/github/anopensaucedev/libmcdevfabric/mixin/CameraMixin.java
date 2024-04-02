package com.github.anopensaucedev.libmcdevfabric.mixin;

import com.github.anopensaucedev.libmcdevfabric.render.CameraUtils;
import net.minecraft.client.render.Camera;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Camera.class)
public abstract class CameraMixin {

    @Shadow protected abstract void setPos(Vec3d pos);

    void SetCameraAtPos(double x, double y, double z){
        setPos(new Vec3d(x,y,z));
    }

    @Inject(method = "update", at = @At("TAIL"))
    void doCameraStuff(BlockView area, Entity focusedEntity, boolean thirdPerson, boolean inverseView, float tickDelta, CallbackInfo ci){
        CameraUtils.CAMERA_INSTANCE = (Camera) (Object) this;
        CameraUtils.CameraUpdateCallback.EVENT.invoker().cameraUpdate(CameraUtils.CAMERA_INSTANCE,area,focusedEntity,thirdPerson,inverseView,tickDelta);
    }



}
