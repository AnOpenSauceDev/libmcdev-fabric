package com.github.anopensaucedev.libmcdev.media;

import com.github.anopensaucedev.libmcdevfabric.Debug;
import com.github.anopensaucedev.libmcdevfabric.Libmcdev;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.io.InputStream;
import java.net.URL;

@Environment(EnvType.CLIENT)
public class MCDevURLImage {

    boolean isRegistered = false;

    @Nullable URL imageLocation;

    public MCDevURLImage(@Nullable URL url, String name){
        this.imageLocation = url;
        this.name = name;
        this.textureID = new Identifier("libmcdev-generated",name);
        updateImage(true);
    }

    public MCDevURLImage(@Nullable URL url, String name,boolean registerImage){
        this.imageLocation = url;
        this.name = name;
        this.textureID = new Identifier("libmcdev-generated",name);
        updateImage(registerImage);
    }

    public Identifier textureID;
    public String name;
    public NativeImageBackedTexture texture;

    private NativeImage image;

    public int width,height;

    public void updateImage(boolean register){
        if(!Libmcdev.isClient) return;
        try{




                    new Thread(() -> {

                        if (imageLocation != null) {
                            try {
                            InputStream stream = imageLocation.openStream();
                            image = NativeImage.read(stream);
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }else {

                            image = new NativeImage(1,1,true); // useStb seems to do nothing judjing by spark. This is the #1 lag-causer

                        }

                        texture = new NativeImageBackedTexture(image);
                        if(register) {
                        MinecraftClient.getInstance().getTextureManager().registerTexture(textureID, texture);
                        isRegistered = true;
                            width = image.getWidth();
                            height = image.getHeight();
                        }
                    }).run();




        }catch (Exception ex){
            Debug.InternalLogError("ERROR: failed to fetch image: " + ex.getLocalizedMessage());
            ex.printStackTrace();
        }

    }

    public void RegisterTexture(){
        MinecraftClient.getInstance().getTextureManager().registerTexture(textureID, texture);
        isRegistered = true;
    }

}
