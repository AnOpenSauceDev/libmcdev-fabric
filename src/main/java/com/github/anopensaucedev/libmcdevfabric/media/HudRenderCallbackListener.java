package com.github.anopensaucedev.libmcdevfabric.media;


import com.github.anopensaucedev.libmcdevfabric.Debug;
import com.github.anopensaucedev.libmcdevfabric.MCDEVMathUtils;
import com.github.anopensaucedev.libmcdevfabric.TempNameGenerator;
import com.github.anopensaucedev.libmcdevfabric.camera.CameraUtils;
import com.github.anopensaucedev.libmcdevfabric.media.UI.Panel;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import org.joml.Vector2d;
import org.joml.Vector2f;
import org.joml.Vector2i;
import org.joml.Vector3f;

import java.net.MalformedURLException;
import java.net.URL;

import static com.github.anopensaucedev.libmcdevfabric.media.GraphicsUtils.WHITE_RGBA;

public class HudRenderCallbackListener implements net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback {


    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {


        /*
            if(Debug.isDev){
                // lower = higher, vice versa
                Panel messagePanel = new Panel(drawContext.getScaledWindowWidth() - 300, drawContext.getScaledWindowHeight() - 80, new Identifier("libmcdev","textures/gui/panel-wide.png"),128,64);
                messagePanel.draw(drawContext);
                drawContext.drawText(MinecraftClient.getInstance().textRenderer,"Hello there! Welcome", messagePanel.x + 10, messagePanel.y + 20, 0xFFFFFFFF,true);
                drawContext.drawText(MinecraftClient.getInstance().textRenderer,"to libMCdev!", messagePanel.x + 10, messagePanel.y + 28, 0xFFFFFFFF,true);
            }
           */

        if(MinecraftClient.getInstance().player.isSneaking()){
            TextRenderer renderer = MinecraftClient.getInstance().textRenderer;
            MinecraftClient client = MinecraftClient.getInstance();
            Vector2f res = MCDEVMathUtils.projectWorldPointToScreenSpace(new Vector3f(32,128,32),client.getWindow(),client);
            // the "normal" method of just casting a double used to work, but this literally WILL. NOT. WORK. WHY!?!? WHO DECIDED THAT!?? IN WHAT WORLD IS 3.5124 = 35124?!?!
            Debug.LogInternal("x:" + res.x + " y:" + res.y);
            Debug.LogInternal("you see:" + (int) Double.valueOf(3.12031023).doubleValue() + ", but:" + (int) res.x);
            drawContext.drawText(renderer,"guh", (int) res.x, (int) res.y,WHITE_RGBA,true);
        }



    }
}
