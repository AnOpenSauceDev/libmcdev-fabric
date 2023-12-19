package com.github.anopensaucedev.libmcdevfabric.media;


import com.github.anopensaucedev.libmcdevfabric.Debug;
import com.github.anopensaucedev.libmcdevfabric.TempNameGenerator;
import com.github.anopensaucedev.libmcdevfabric.media.UI.FillablePanel;
import com.github.anopensaucedev.libmcdevfabric.media.UI.Panel;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;


import java.net.MalformedURLException;
import java.net.URL;
import net.minecraft.util.math.Vec3d;
import org.joml.Math;
import org.joml.Vector2d;
import org.joml.Vector2f;
import org.joml.Vector2i;
import org.joml.Vector3f;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import static com.github.anopensaucedev.libmcdevfabric.media.GraphicsUtils.BLACK_RGBA;
import static com.github.anopensaucedev.libmcdevfabric.media.GraphicsUtils.WHITE_RGBA;


public class HudRenderCallbackListener implements net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback {


    int progticks;



    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        progticks++;


        MinecraftClient client = MinecraftClient.getInstance();
        FillablePanel panel = new FillablePanel(500,client.getWindow(),0,30,0,0);
        panel.fillPanel(drawContext,WHITE_RGBA);
    }

    public int LerpScreenEffectColour(int startingColour,int endingColour,float progress){
       return (int) Math.lerp(startingColour,endingColour,progress);
    }

}
