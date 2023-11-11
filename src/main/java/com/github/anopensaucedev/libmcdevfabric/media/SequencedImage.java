package com.github.anopensaucedev.libmcdevfabric.media;

import com.github.anopensaucedev.libmcdevfabric.Debug;
import com.github.anopensaucedev.libmcdevfabric.Libmcdev;
import net.minecraft.client.MinecraftClient;

import java.io.File;
import java.nio.file.Path;

public class SequencedImage { // a really jank brute-force method im using to play Bad Apple in minecraft without using VLCJ, don't expect decent code here

    public MCDevURLImage[] images;

    public MCDevURLImage[] RetriveImages(int frames){
        images = new MCDevURLImage[frames];
        Debug.InternalLogWarning("this code is probably gonna crash, rip");
        new Thread(()->{
        try{
            if(Libmcdev.isClient){
            Path dir1 = MinecraftClient.getInstance().runDirectory.toPath().resolve("imgseq").resolve("images");
               File file = dir1.toFile();
               file.mkdirs();
                Debug.LogInternal(String.format("%s.png",1));

                for(int x = 1; x < frames; x++){
                    File dir = file.toPath().resolve(String.format("%s.png",x)).toFile();
                    images[x] = new MCDevURLImage(dir.toURL(),x +"");

                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        }).run();
        return images;
    }

}
