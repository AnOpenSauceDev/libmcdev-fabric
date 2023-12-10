package com.github.anopensaucedev.libmcdevfabric.data.sync;

import net.minecraft.world.gen.chunk.NoiseChunkGenerator;

import java.util.ArrayList;
import java.util.List;

public class SyncedVarManager {

    public static List<Synced> syncedVarList = new ArrayList<>();

    public static List<Synced> getSyncedVarList() {
        return syncedVarList;
    }

    public void add(Synced synced){
        syncedVarList.add(synced);
    }

    public void remove(Synced synced){
        syncedVarList.remove(synced);
    }


    public void SyncVariables(){
        for(int x = 0; x < syncedVarList.size(); x++){
            syncedVarList.get(x).sync();
        }
    }

}
