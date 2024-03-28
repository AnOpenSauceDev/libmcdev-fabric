package com.github.anopensaucedev.libmcdev.media.UI;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;

public class DebugScreen extends Screen {
    public DebugScreen(Text title) {
        super(title);
    }

    Text name = Text.of("LibMCdev inspector");



    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta){
        var x = context.getScaledWindowWidth()/2 - textRenderer.getWidth(name);
        context.drawText(textRenderer,name, x ,65,0xFFFFFFFF,false);
        TextFieldWidget widget = new TextFieldWidget(textRenderer,x,65,32,16,Text.of("Example Value"));
        widget.render(context,mouseX,mouseY,delta);

    }


}
