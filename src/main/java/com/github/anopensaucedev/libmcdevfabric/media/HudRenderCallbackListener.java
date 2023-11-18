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
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShapes;
import org.joml.Vector4f;

import java.net.MalformedURLException;
import java.net.URL;

import static com.github.anopensaucedev.libmcdevfabric.MCDEVMathUtils.*;
import static com.github.anopensaucedev.libmcdevfabric.media.MCDevURLImage.BLACK_TRANSPARENT;
import static com.github.anopensaucedev.libmcdevfabric.media.MCDevURLImage.WHITE_RGBA;

public class HudRenderCallbackListener implements net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback {


    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {



        MinecraftClient client = MinecraftClient.getInstance();
        TextRenderer textRenderer = client.textRenderer;

        Window mainWindow = MinecraftClient.getInstance().getWindow();

        Vec3d Pos = client.player.getPos();

        Vector4f screenPos = projectWorldPointToScreenSpace(vec3ToVector3D(Pos), client.gameRenderer, drawContext.getMatrices());

        screenPos.div(1.0f);

        textRenderer.draw(
                Text.of(screenPos.toString()),
                screenPos.x,   screenPos.y, // Use the projected screen coordinates
                0xFFFFFFFF,
                false,
                drawContext.getMatrices().peek().getPositionMatrix(),
                drawContext.getVertexConsumers(),
                TextRenderer.TextLayerType.NORMAL,
                BLACK_TRANSPARENT,
                WHITE_RGBA
        );
        }

}
