package com.github.anopensaucedev.libmcdev.media.UI;

import com.github.anopensaucedev.libmcdev.media.GraphicsUtils;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.Window;
import org.jetbrains.annotations.Nullable;
import org.joml.Vector2f;

public class FillablePanel {

    public Vector2f TOP_LEFT;
    public Vector2f BASE_RIGHT;

    public Vector2f CENTER;

    public FillablePanel(int margin, Window window, int offsetX, int offsetY, @Nullable float widthOverride,@Nullable float heightOverride){

        TOP_LEFT = GraphicsUtils.getTopLeftOfWindow(window).add(margin,margin); // makes top left closer to center
        BASE_RIGHT = GraphicsUtils.getBottomRightOfWindow(window).sub(margin,margin); // makes bottom right closer to center

        if(widthOverride != 0){
            TOP_LEFT.mul(widthOverride,0); // makes top left X closer to the center
            BASE_RIGHT.div(widthOverride,0); // makes bottom right X closer to the center
        }

        if(heightOverride != 0){
            TOP_LEFT.mul(0,heightOverride); // makes top left Y closer to the center
            BASE_RIGHT.div(0,heightOverride); // makes bottom right Y closer to the center
        }

        BASE_RIGHT.add(offsetX,0); // moves right by X
        TOP_LEFT.add(offsetX,0); // moves right by X
        BASE_RIGHT.add(0,-offsetY); // moves up by Y
        TOP_LEFT.add(0,-offsetY); // moves up by Y

        CENTER = new Vector2f(BASE_RIGHT).sub(TOP_LEFT);
    }

    public void fillPanel(DrawContext context,int colour){
        context.fill((int) TOP_LEFT.x, (int) TOP_LEFT.y, (int) BASE_RIGHT.x, (int) BASE_RIGHT.y,colour);
    }
}
