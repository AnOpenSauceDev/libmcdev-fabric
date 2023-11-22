package com.github.anopensaucedev.libmcdevfabric;

import com.github.anopensaucedev.libmcdevfabric.mixin.FovMixin;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Window;
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

        Debug.LogInternal(data.toString());
        return data;
    }

    public static Matrix4f getProjectionMatrix(MinecraftClient client){
        Matrix4f mtx =  client.gameRenderer.getBasicProjectionMatrix(client.options.getFov().getValue());
        Debug.LogInternal(mtx.toString());
       return mtx;
    }

    public static Vector4f GetScreenSpaceVec4(MinecraftClient client, Window window, Vector3f pos){
        Matrix4f ModelViewProjection = FetchModelViewMatrix(client).mul(getProjectionMatrix(client)); // M * V * P
        int[] viewport = {window.getWidth(), window.getHeight(), window.getX(),window.getY()};
        Vector4f windowcoords = new Vector4f();
        ModelViewProjection.unproject(pos,viewport,windowcoords);
        Debug.LogInternal(windowcoords.toString());
        return windowcoords;
    }

    public static Vector2f WorldPointToScreenSpace(MinecraftClient client, Window window, Vector3f pos){
       Vector4f space = GetScreenSpaceVec4(client,window,pos);
       float x = space.x / space.z;
       float y = space.y / space.z;
       return new Vector2f(x,y);
    }


    public static float norm(Quaternionf quat) {
        return quat.w * quat.w + quat.x * quat.x + quat.y * quat.y + quat.z * quat.z;
    }

}
