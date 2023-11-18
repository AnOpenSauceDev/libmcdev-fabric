package com.github.anopensaucedev.libmcdevfabric;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.Window;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec3d;
import org.joml.*;

import java.util.concurrent.ThreadLocalRandom;

public class MCDEVMathUtils {

    public static ThreadLocalRandom SHARED_RANDOM = ThreadLocalRandom.current();


    public static Vector4f projectWorldPointToScreenSpace(Vector3d worldPosition, GameRenderer renderer, MatrixStack stack) {
        Matrix4f viewMatrix = stack.peek().getPositionMatrix(); // this should be the view matrix, but it has nothing for some reason
        Debug.LogInternal("ViewMatrix: " + viewMatrix.toString());
        Matrix4f projectionMatrix = renderer.getBasicProjectionMatrix(getProjectionViaOptions()); // yep
        Debug.LogInternal("ProjMatrix: " + projectionMatrix.toString());
        Matrix4f viewProjMatrix = new Matrix4f(projectionMatrix);
        viewProjMatrix.mul(viewMatrix);
        Vector4f worldPos = new Vector4f((float) worldPosition.x, (float) worldPosition.y, (float) worldPosition.z, 1.0f);
        viewProjMatrix.transform(worldPos);
        return worldPos;
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

    public static int getProjectionViaOptions(){
        return MinecraftClient.getInstance().options.getFov().getValue();
    }

}
