package com.github.anopensaucedev.libmcdevfabric.media;


import com.github.anopensaucedev.libmcdevfabric.Debug;
import com.github.anopensaucedev.libmcdevfabric.TempNameGenerator;
import com.github.anopensaucedev.libmcdevfabric.entity.HelperPredicates;
import com.github.anopensaucedev.libmcdevfabric.inspector.Inspectable;
import com.github.anopensaucedev.libmcdevfabric.media.UI.FillablePanel;
import com.github.anopensaucedev.libmcdevfabric.media.UI.Panel;
import com.github.anopensaucedev.libmcdevfabric.render.CameraUtils;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.ai.TargetPredicate;
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

    int testOffset = 0;

    int progticks;

    Inspectable<Integer> DebugMargin = new Inspectable<>(20,"Example Panel Debug Margin");

    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {

    }

    public int LerpScreenEffectColour(int startingColour,int endingColour,float progress){
       return (int) Math.lerp(startingColour,endingColour,progress);
    }

}
