package com.github.anopensaucedev.libmcdevfabric.graphics;

import com.github.anopensaucedev.libmcdevfabric.Debug;
import com.github.anopensaucedev.libmcdevfabric.Libmcdev;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URL;

@Environment(EnvType.CLIENT)
public class MCDevURLImage {

    @Nullable URL imageLocation;

    public MCDevURLImage(URL url,String name){
        this.imageLocation = url;
        this.name = name;
        this.textureID = new Identifier("libmcdev-generated",name);
        updateImage();
    }

    public Identifier textureID;
    public String name;
    public NativeImageBackedTexture texture;

    public void updateImage(){
        if(!Libmcdev.isClient) return;
        try{
            BufferedImage img = ImageIO.read(imageLocation);
            NativeImage image = new NativeImage(NativeImage.Format.RGBA,img.getWidth(),img.getHeight(),true);

            new Thread(()->{ // blit stuff in a way that isn't really slow
                for(int x = 0; x < img.getWidth(); x++){
                    for(int y = 0; y < img.getHeight(); y++){
                        image.setColor(x,y,img.getRGB(x,y));
                    }
                }
                texture = new NativeImageBackedTexture(image);
                MinecraftClient.getInstance().getTextureManager().registerTexture(textureID,texture);

            }).run();

        }catch (Exception ex){
            Debug.InternalLogError("ERROR: failed to fetch image: " + ex.getLocalizedMessage());
            ex.printStackTrace();
        }

    }

}
