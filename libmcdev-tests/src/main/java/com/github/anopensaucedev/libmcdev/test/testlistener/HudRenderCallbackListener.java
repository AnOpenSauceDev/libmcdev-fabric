package com.github.anopensaucedev.libmcdev.test.testlistener;

import com.github.anopensaucedev.libmcdev.media.MCDevURLImage;
import com.github.anopensaucedev.libmcdev.media.UI.FillablePanel;
import com.github.anopensaucedev.libmcdev.media.UI.WheelSelector;
import com.github.anopensaucedev.libmcdevfabric.render.ArbitraryRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.block.entity.EndPortalBlockEntityRenderer;
import net.minecraft.util.Identifier;

import java.net.MalformedURLException;
import java.net.URL;

public class HudRenderCallbackListener implements HudRenderCallback {

    MinecraftClient client = MinecraftClient.getInstance();

    WheelSelector.WheelSlot[] slots = new WheelSelector.WheelSlot[5];

    WheelSelector selector = new WheelSelector(slots);

    int t;

    long ticks;

    FillablePanel panel;
    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        ticks++;


        if(MinecraftClient.getInstance().player != null){

            if( t != 1){

                panel = new FillablePanel(30,client.getWindow(),0,0,0,0);



                slots[0] = new WheelSelector.WheelSlot(new Identifier("textures/item/barrier.png"),16);
                slots[1] = new WheelSelector.WheelSlot(new Identifier("textures/item/stick.png"),16);
                slots[2] = new WheelSelector.WheelSlot(new Identifier("textures/item/egg.png"),16);
                slots[3] = new WheelSelector.WheelSlot(new Identifier("textures/item/snowball.png"),16);
                slots[4] = new WheelSelector.WheelSlot(new Identifier("textures/item/beef.png"),16);
                t = 1;
            }


            //test arb
            ArbitraryRenderer.renderArbitraryFace(drawContext.getMatrices(),drawContext.getVertexConsumers(),client.player.getPos().add(0.0,0.0,4.0).toVector3f());
            ArbitraryRenderer.renderArbitraryFace(drawContext.getMatrices(),drawContext.getVertexConsumers(),client.player.getPos().add(0.0,0.0,4.0).toVector3f().negate());

            // what? writing 0xFFFFFF didn't work for me? Which ofc makes no sense as 0xFFFFFF == 16777215.
            //TODO: question sanity
            panel.fillPanel(drawContext,16777215);

            selector.renderWheel(MinecraftClient.getInstance().textRenderer,drawContext,null);
            if(ticks % 120 == 0) {
                selector.increment();
                ticks = 0;
            }
        }

    }

}
