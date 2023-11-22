package com.github.anopensaucedev.libmcdevfabric.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.CameraSubmersionType;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(GameRenderer.class)
public class FovMixin {



    @Shadow private boolean renderingPanorama;

    @Shadow @Final private MinecraftClient client;

    @Shadow private float lastFovMultiplier;

    @Shadow private float fovMultiplier;

    public double getFov(Camera camera, float tickDelta, boolean changingFov) {
        CameraSubmersionType cameraSubmersionType;
        if (this.renderingPanorama) {
            return 90.0;
        }
        double d = 70.0;
        if (changingFov) {
            d = this.client.options.getFov().getValue().intValue();
            d *= (double) MathHelper.lerp(tickDelta, this.lastFovMultiplier, this.fovMultiplier);
        }
        if (camera.getFocusedEntity() instanceof LivingEntity && ((LivingEntity)camera.getFocusedEntity()).isDead()) {
            float f = Math.min((float)((LivingEntity)camera.getFocusedEntity()).deathTime + tickDelta, 20.0f);
            d /= (double)((1.0f - 500.0f / (f + 500.0f)) * 2.0f + 1.0f);
        }
        if ((cameraSubmersionType = camera.getSubmersionType()) == CameraSubmersionType.LAVA || cameraSubmersionType == CameraSubmersionType.WATER) {
            d *= MathHelper.lerp(this.client.options.getFovEffectScale().getValue(), 1.0, 0.8571428656578064);
        }
        return d;
    }

}
