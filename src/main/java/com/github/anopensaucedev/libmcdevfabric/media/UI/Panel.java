package com.github.anopensaucedev.libmcdevfabric.media.UI;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;

public class Panel {


    public Panel(int x, int y, Identifier Texture,int width, int height){
        this.Texture = Texture;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int x,y,width,height;

    private Identifier Texture;

    public void draw(DrawContext context){
        context.drawGuiTexture(Texture,x,y,0,0,width,height,width,height);
    }

}
