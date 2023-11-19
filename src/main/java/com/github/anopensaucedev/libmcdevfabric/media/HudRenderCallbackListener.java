package com.github.anopensaucedev.libmcdevfabric.media;


import com.github.anopensaucedev.libmcdevfabric.Debug;
import com.github.anopensaucedev.libmcdevfabric.MCDEVMathUtils;
import com.github.anopensaucedev.libmcdevfabric.TempNameGenerator;
import com.github.anopensaucedev.libmcdevfabric.media.UI.Panel;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.Window;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShapes;
import org.joml.*;

import java.net.MalformedURLException;
import java.net.URL;

import static com.github.anopensaucedev.libmcdevfabric.MCDEVMathUtils.*;
import static com.github.anopensaucedev.libmcdevfabric.media.MCDevURLImage.BLACK_TRANSPARENT;
import static com.github.anopensaucedev.libmcdevfabric.media.MCDevURLImage.WHITE_RGBA;

public class HudRenderCallbackListener implements net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback {


    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {


        //this took a whole day to figure out, due to me thinking that minecraft would be sane.
        MinecraftClient client = MinecraftClient.getInstance();
        Window mainWindow = client.getWindow();


        Vector2d point = projectWorldPointToScreenSpace(new Vector3f(0,128,0),mainWindow,client.gameRenderer,drawContext.getMatrices());

        Debug.LogInternal("x:" + point.x + " y: " + point.y + " s: " + mainWindow.getWidth() + " a " + mainWindow.getHeight() + " s:" + mainWindow.getScaledWidth());

        client.textRenderer.draw(point.toString(), (float) point.x, (float) point.y,WHITE_RGBA,true,drawContext.getMatrices().peek().getPositionMatrix(), drawContext.getVertexConsumers(), TextRenderer.TextLayerType.NORMAL,BLACK_TRANSPARENT,WHITE_RGBA);


    }
}
