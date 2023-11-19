package com.github.anopensaucedev.libmcdevfabric;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.Window;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec3d;
import org.joml.*;

import java.util.concurrent.ThreadLocalRandom;

public class MCDEVMathUtils {

    public static ThreadLocalRandom SHARED_RANDOM = ThreadLocalRandom.current();


    //WARNING! I AM NOT A GRAPHICS PROGRAMMER, EXPECT JANK!
    public static Vector2d projectWorldPointToScreenSpace(Vector3f worldPosition, Window window, GameRenderer renderer, MatrixStack stack) {
        Matrix4f viewMatrix = stack.peek().getPositionMatrix();
        Debug.LogInternal(viewMatrix.toString());
        Matrix4f projectionMatrix = renderer.getBasicProjectionMatrix(getProjectionViaOptions());
        Matrix4f viewProjMatrix = new Matrix4f(projectionMatrix);
        viewProjMatrix.mul(viewMatrix);
        Vector3f screenPosition = viewProjMatrix.transformProject(worldPosition);
        screenPosition.add(1f,1f,1f).mul(0.5f);
        return new Vector2d(screenPosition.x * window.getScaledWidth(),screenPosition.y * window.getScaledHeight());
    }

    public static Matrix4f projectWorldPointToScreenSpaceMatrix(GameRenderer renderer, MatrixStack stack){
        Matrix4f viewMatrix = stack.peek().getPositionMatrix();
        Matrix4f projectionMatrix = renderer.getBasicProjectionMatrix(getProjectionViaOptions());
        Matrix4f viewProjMatrix = new Matrix4f(projectionMatrix);
        viewProjMatrix.mul(viewMatrix);
        return viewProjMatrix;
    }

    public static Vector3d vec3ToVector3D(Vec3d vector){
        return new Vector3d(vector.x,vector.y,vector.z);
    }

    public static Vector3f vec3ToVector3F(Vec3d vector){
        return new Vector3f((float) vector.x,(float) vector.y,(float) vector.z);
    }

    public static int getProjectionViaOptions(){
        return MinecraftClient.getInstance().options.getFov().getValue();
    }

}
