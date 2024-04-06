package com.github.anopensaucedev.libmcdevfabric.systems;

import net.minecraft.server.MinecraftServer;

// run code every tick, linked to an item
public class UpdateTracker<T extends Object>{

    public T tracked;

    public T getTracked() {
        return tracked;
    }

    private UpdaterInterface updaterInterface;

    public UpdateTracker(T data, UpdaterInterface updaterInterface){
        this.tracked = data;
        this.updaterInterface = updaterInterface;
        ServerListener.trackers.add(this);
    }

    /**
     * destroy the updater
     */
    public void remove(){
        ServerListener.trackers.remove(this);
        tracked = null;
        updaterInterface = null;
    }

    /**
     * update an updateTracker
     * @param server
     */
    public void update(MinecraftServer server){
        updaterInterface.onRun(server);
    }

    public interface UpdaterInterface {
        void onRun(MinecraftServer server);
    }

}
