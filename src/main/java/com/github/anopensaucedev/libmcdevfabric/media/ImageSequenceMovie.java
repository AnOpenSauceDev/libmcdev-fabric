package com.github.anopensaucedev.libmcdevfabric.media;


import com.github.anopensaucedev.libmcdevfabric.TempNameGenerator;

public class ImageSequenceMovie extends ImageSequence { // ImageSequences don't have (despite what my scary code may tell you), and shouldn't have the ability to be treated like videos. This is a hack.

    public int frame = 1;

    MCDevURLImage currentFrameImage; // instead of looping through registered textures, which is slow at first, use one (technically 2) image(s) that are registered.

    public ImageSequenceMovie(int frames) {
        super(frames);
        this.currentFrameImage = new MCDevURLImage(null, TempNameGenerator.returnTempName(),true);
    }

    public MCDevURLImage playNextFrame(){ // increments frame, then returns said frame.
        if(frame < images.length) { // stop if we are at the very last frame in our video
            currentFrameImage.image = images[frame++].image; // increment frame
            currentFrameImage.texture.upload(); // update texture
            return currentFrameImage;
        }else {
            return images[1]; // return first frame. images[1] will always be registered.
        }
    }

    public MCDevURLImage getCurrentFrame(){
        return images[frame];
    }

}
