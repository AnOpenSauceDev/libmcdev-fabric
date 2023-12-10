package com.github.anopensaucedev.libmcdevfabric.data.sync;

import net.minecraft.util.Identifier;

public abstract class Synced {

    Identifier identifier;

    public Synced(Identifier id){
        identifier = id;
    }

    public abstract void sync();

}
