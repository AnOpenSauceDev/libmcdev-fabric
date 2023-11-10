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
import java.io.InputStream;
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

            InputStream stream = imageLocation.openStream();
            NativeImage image = NativeImage.read(stream);

            new Thread(()->{ // blit stuff in a way that isn't really slow
                texture = new NativeImageBackedTexture(image);
                MinecraftClient.getInstance().getTextureManager().registerTexture(textureID,texture);

            }).run();

        }catch (Exception ex){
            Debug.InternalLogError("ERROR: failed to fetch image: " + ex.getLocalizedMessage());
            ex.printStackTrace();
        }

    }

}
