package com.github.anopensaucedev.libmcdev.media;

import net.minecraft.client.util.Window;
import org.joml.Vector2f;

public class GraphicsUtils {

    public static final int WHITE_RGBA = 0xFFFFFFFF; // WHITE_RGB with an extra alpha byte

    public static final int WHITE_RGB = 0xFFFFFF;

    public static final int BLACK_RGBA = 0x000000FF; // BLACK_RGB with an extra alpha byte

    public static final int BLACK_RGB = 0x000000;

    public static final int BLACK_TRANSPARENT = 0x00000000; // BLACK_RGBA, but the alpha byte is zero.

    public static Vector2f getTopLeftOfWindow(Window window){
        return new Vector2f(0,0); // 0,0 is always top left
    }
    public static Vector2f getTopRightOfWindow(Window window){
        return new Vector2f(window.getWidth(),0);
    }
    public static Vector2f getBottomLeftOfWindow(Window window){
        return new Vector2f(0,window.getHeight());
    }

    public static Vector2f getWindowCenter(Window window){
        return new Vector2f(window.getWidth()/2,window.getHeight()/2);
    }

    public static Vector2f getBottomRightOfWindow(Window window){
        return new Vector2f(window.getWidth(),window.getHeight());
    }

}
