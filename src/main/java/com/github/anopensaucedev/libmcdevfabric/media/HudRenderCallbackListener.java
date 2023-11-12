package com.github.anopensaucedev.libmcdevfabric.media;


import com.github.anopensaucedev.libmcdevfabric.Debug;
import com.github.anopensaucedev.libmcdevfabric.TempNameGenerator;
import net.minecraft.client.gui.DrawContext;

import java.net.MalformedURLException;
import java.net.URL;

public class HudRenderCallbackListener implements net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback {

    MCDevURLImage testImage;





    boolean b = true;
    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
            if(Debug.isDev){
            //if(b){CreateTestImage(); b = false; sImg.RetriveImages(6572);}
            //drawContext.drawTexture(testImage.textureID, (MinecraftClient.getInstance().getWindow().getScaledWidth() / 2) - 200, (MinecraftClient.getInstance().getWindow().getScaledWidth() / 2) - 200, 0, 0, testImage.width/4,testImage.height/4,testImage.width/4,testImage.height/4);

            //drawContext.drawTexture(sImg.images[fcount].textureID, (MinecraftClient.getInstance().getWindow().getScaledWidth() / 2) - 200, (MinecraftClient.getInstance().getWindow().getScaledWidth() / 2) - 200, 0, 0, testImage.width/4,testImage.height/4,testImage.width/4,testImage.height/4);


            }
    }

    public void CreateTestImage(){
        try {
            testImage = new MCDevURLImage(new URL("https://static.wikia.nocookie.net/amogus/images/c/cb/Susremaster.png/revision/latest?cb=20210806124552"), TempNameGenerator.returnTempName());

            Debug.LogInternal("generated image " + testImage.name);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
