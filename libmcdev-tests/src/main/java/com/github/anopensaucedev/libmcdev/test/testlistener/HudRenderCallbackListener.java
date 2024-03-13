package com.github.anopensaucedev.libmcdev.test.testlistener;

import com.github.anopensaucedev.libmcdev.media.MCDevURLImage;
import com.github.anopensaucedev.libmcdev.media.UI.FillablePanel;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

import java.net.MalformedURLException;
import java.net.URL;

public class HudRenderCallbackListener implements HudRenderCallback {

    MinecraftClient client = MinecraftClient.getInstance();

    MCDevURLImage img;

    {
        try {
            img = new MCDevURLImage(new URL("https://cdn.modrinth.com/data/mOgUt4GM/icon.png"),"asdf");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    FillablePanel panel;
    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        if(MinecraftClient.getInstance().player != null){

        }

    }

}
