package com.github.anopensaucedev.libmcdevfabric;

import com.github.anopensaucedev.libmcdevfabric.mixin.FovMixin;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Window;
import net.minecraft.util.math.Vec3d;
import org.joml.*;

import java.nio.FloatBuffer;
import java.util.concurrent.ThreadLocalRandom;

public class MCDEVMathUtils {

    public static ThreadLocalRandom SHARED_RANDOM = ThreadLocalRandom.current();


    public static Matrix4f FetchModelViewMatrix(MinecraftClient client){ // code mostly from JMonkey
        Quaternionf rotation =  client.gameRenderer.getCamera().getRotation();
        float norm = norm(rotation);
        // we explicitly test norm against one here, saving a division
        // at the cost of a test and branch.  Is it worth it?
        float s = (norm == 1f) ? 2f : (norm > 0f) ? 2f / norm : 0;

        // compute xs/ys/zs first to save 6 multiplications, since xs/ys/zs
        // will be used 2-4 times each.
        float xs = rotation.x * s;
        float ys = rotation.y * s;
        float zs = rotation.z * s;
        float xx = rotation.x * xs;
        float xy = rotation.x * ys;
        float xz = rotation.x * zs;
        float xw = rotation.w * xs;
        float yy = rotation.y * ys;
        float yz = rotation.y * zs;
        float yw = rotation.w * ys;
        float zz = rotation.z * zs;
        float zw = rotation.w * zs;


        float[] stuff = new float[16];

        // using s=2/norm (instead of 1/norm) saves 9 multiplications by 2 here
        stuff[0] = 1 - (yy + zz);
        stuff[1] = (xy - zw);
        stuff[2] = (xz + yw);
        stuff[4] = (xy + zw);
        stuff[5] = 1 - (xx + zz);
        stuff[6] = (yz - xw);
        stuff[8] = (xz - yw);
        stuff[9] = (yz + xw);
        stuff[10] = 1 - (xx + yy);



        Matrix4f data = new Matrix4f().set(stuff);

        Debug.LogInternal("quatmodelviewMatrix"+data.toString());
        return data;

    }

    public static Matrix4f fetchModelViewMatrixMethod2(Quaternionf rotation, Vector3f camerapos){
       return new Matrix4f().rotation(rotation).translate(camerapos);
    }

    public static Matrix4f getProjectionMatrix(MinecraftClient client){
        Matrix4f mtx =  client.gameRenderer.getBasicProjectionMatrix(client.options.getFov().getValue());
       return mtx;
    }

    public static Vector4f GetScreenSpaceVec4(MinecraftClient client, Window window, Vector3f pos){
        Matrix4f ModelViewProjection = fetchModelViewMatrixMethod2(client.gameRenderer.getCamera().getRotation(),client.gameRenderer.getCamera().getPos().toVector3f()).mul(getProjectionMatrix(client)); // M * V * P
        int[] viewport = { 0,0,window.getWidth(), window.getHeight()};
        Vector4f windowcoords = new Vector4f();
        ModelViewProjection.project(pos,viewport,windowcoords);
        Debug.LogInternal("windowcoords:"+windowcoords.toString() + " blank:" + new Vector4f() + " vp:" + ModelViewProjection.toString());
        return windowcoords;
    }



    public static float norm(Quaternionf quat) {
        return quat.w * quat.w + quat.x * quat.x + quat.y * quat.y + quat.z * quat.z;
    }

    public static Vector2d projectWorldPointToScreenSpace(Vec3d worldPosition, Window window, MinecraftClient client) {
        Debug.LogInternal("uhh what the hell? " + worldPosition);
        Vector3f posfixed = Vec3dtoVector3d(worldPosition); // for some reason if i dont do this i get infinity as a value?!?!
        Matrix4f ModelViewProjection = FetchModelViewMatrix(client).mul(getProjectionMatrix(client)); // M * V * P
        Vector3f screenPosition = ModelViewProjection.transformProject(posfixed);
        Debug.LogInternal(screenPosition.toString());
        screenPosition.add(1f,1f,1f).mul(0.5f);

        return new Vector2d(screenPosition.x * window.getWidth(),screenPosition.y * window.getHeight());
    }

    public static Vector3f Vec3dtoVector3d(Vec3d d){
        return new Vector3f((float) d.x,(float) d.y,(float) d.z);
    }

}
