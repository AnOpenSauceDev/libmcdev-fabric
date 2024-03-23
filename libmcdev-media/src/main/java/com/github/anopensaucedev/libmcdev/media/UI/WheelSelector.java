package com.github.anopensaucedev.libmcdev.media.UI;


import com.github.anopensaucedev.libmcdevfabric.Debug;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Drawable;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

// wheel at top, a bunch of elements around it, with slots that can hold any element
public class WheelSelector {

    public WheelSelector(WheelSlot[] wheelSlots){
        slots = wheelSlots;
        index = 0;
    }

    /**
     * A slot for a WheelSelector. Only accepts square textures.
     *  @param <T> the data stored within our slot
     *
     */
    public static class WheelSlot <T>{

        /**
         * @param textureID texture identifier
         * @param textureSize the width of your texture, must be a 1:1 aspect ratio.
         */
        public WheelSlot(Identifier textureID,int textureSize){
            texture = textureID;
            dimensions = textureSize;
        }

        T SlotData;

        public Identifier texture;
        public int dimensions;

    }

    private int index;

    public WheelSlot[] slots;

    public void renderWheel(TextRenderer renderer, DrawContext ctx, @Nullable Text text){
        if(text == null){
            text = Text.literal("Selected Index: " + index);
        }

        for (int i = 0; i < slots.length; i++) {
            if(slots[i] == null){
                slots[i] = new WheelSlot(new Identifier("libmcdev-tests","textures/particle/dust_puff.png"),64);
            }
        }

        // for this, we put the text down by 0.9x of the screen, then offset it by the text width.
        ctx.drawText(renderer,text,(int) ((ctx.getScaledWindowWidth()/2) - (renderer.getWidth(text) * 0.5)), (int) (ctx.getScaledWindowHeight() - (ctx.getScaledWindowHeight() * 0.9)),0xFFFFFF,true);

        var scale = 32;

        // ctx.drawGuiTexture is completely borked here for some reason.

        // selected index
        RenderTexture(ctx,slots[index].texture,(int) ((ctx.getScaledWindowWidth()/2) - (scale * 0.5)), (int) (ctx.getScaledWindowHeight() - (ctx.getScaledWindowHeight() * 0.90)),32,32);
        //ctx.drawTexture(slots[index].texture,(int) ((ctx.getScaledWindowWidth()/2) - (scale * 0.5)), (int) (ctx.getScaledWindowHeight() - (ctx.getScaledWindowHeight() * 0.90)),0,0,scale,scale,slots[index].dimensions,slots[index].dimensions);

        // to the right
        RenderTexture(ctx,slots[getNextIncrement()].texture, ((ctx.getScaledWindowWidth()/2) + (scale)), (int) (ctx.getScaledWindowHeight() - (ctx.getScaledWindowHeight() * 0.98)),32,32);
        //ctx.drawTexture(slots[getNextIncrement()].texture,,0,0,scale,scale,slots[getNextIncrement()].dimensions,slots[getNextIncrement()].dimensions);

        // to the left
        RenderTexture(ctx,slots[getNextDecrement()].texture, ((ctx.getScaledWindowWidth()/2) - (scale * 2)), (int) (ctx.getScaledWindowHeight() - (ctx.getScaledWindowHeight() * 0.98)),32,32);
    }

    // yoinked from Odometer (thanks past me!)
    public void RenderTexture(DrawContext ctx,Identifier id, int x, int y, int scaleX, int scaleY){
        ctx.drawTexture(id, x, y, 0.0F, 0.0F, scaleX, scaleY, scaleX, scaleY);
    }


    public int getNextIncrement(){
        if(index + 1 < slots.length){
            return index + 1;
        }else {
            return 0;
        }
    }

    public int getNextDecrement(){
        if(index - 1 >= 0){
            return index - 1;
        }else {
            return slots.length - 1;
        }
    }

    public void increment(){
        if(index < slots.length - 1){
            index++;
        }else{
            index = 0;
        }
    }

    public void decrement(){
        if(index > 0){
            index--;
        }else {
            index = slots.length - 1;
        }
    }

}
