package com.github.anopensaucedev.libmcdevfabric.media;


public class ImageSequenceMovie extends ImageSequence { // ImageSequences don't have (despite what my scary code may tell you), and shouldn't have the ability to be treated like videos. This is a hack.

    public int frame = 0;

    public ImageSequenceMovie(int frames) {
        super(frames);
    }

    public MCDevURLImage playNextFrame(){ // increments frame, then returns said frame.
        if(frame < images.length) { // stop if we are at the very last frame in our video
            return images[frame++]; // post-increment frame.
        }else {
            return images[1]; // return first frame.
        }
    }



    public MCDevURLImage getCurrentFrame(){
        return images[frame];
    }

}
