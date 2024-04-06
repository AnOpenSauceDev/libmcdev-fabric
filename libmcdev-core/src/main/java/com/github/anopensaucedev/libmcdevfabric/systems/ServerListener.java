package com.github.anopensaucedev.libmcdevfabric.systems;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;

import java.util.ArrayList;
import java.util.List;

public class ServerListener implements ServerTickEvents.EndTick{

    public static List<UpdateTracker> trackers = new ArrayList<>();

    @Override
    public void onEndTick(MinecraftServer server) {


        for (UpdateTracker tracker : trackers){
            tracker.update(server);
        }

    }
}
