package com.github.anopensaucedev.libmcdevfabric.render;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.util.Window;
import org.joml.Matrix4f;
import org.joml.Vector2d;
import org.joml.Vector3f;


@Environment(EnvType.CLIENT)
public class ProjectionUtils {

    public static Matrix4f MODELVIEW_MATRIX = RenderSystem.getModelViewMatrix();
    public static Matrix4f PROJECTION_MATRIX = RenderSystem.getProjectionMatrix();

    public static Matrix4f MODEL_VIEW_PROJECTION_MATRIX;

    public static void updateMatrices(){
        MODELVIEW_MATRIX = RenderSystem.getModelViewMatrix();
        PROJECTION_MATRIX = RenderSystem.getProjectionMatrix();
        MODEL_VIEW_PROJECTION_MATRIX = new Matrix4f(MODELVIEW_MATRIX).mul(PROJECTION_MATRIX); // (M * V * P) for projection
    }

    public static Vector2d project(float x, float y, float z, int height){
        Vector3f screencoords = new Vector3f();
        int[] viewport = { GlStateManager.Viewport.getX(), GlStateManager.Viewport.getY(), GlStateManager.Viewport.getWidth(),GlStateManager.Viewport.getHeight() }; // X,Y,W,H
        MODEL_VIEW_PROJECTION_MATRIX.project(x,y,z,viewport,screencoords);
        return new Vector2d(screencoords.x,height - screencoords.y);
    }

    public static Matrix4f getModelviewMatrix() {
        return MODELVIEW_MATRIX;
    }

    public static Matrix4f getModelViewProjectionMatrix() {
        return MODEL_VIEW_PROJECTION_MATRIX;
    }

    public static Matrix4f getProjectionMatrix() {
        return PROJECTION_MATRIX;
    }


}
