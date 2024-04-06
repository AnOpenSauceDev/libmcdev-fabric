package com.github.anopensaucedev.libmcdevfabric;

import com.github.anopensaucedev.libmcdevfabric.systems.ServerListener;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

public class Libmcdev implements ModInitializer {
    /**
     * Runs the mod initializer.
     */

    public static boolean isClient = false;



    public static final String MOD_ID = "libmcdev";

    //DataHandlingUtils LibMCDevUtilsHandler = new DataHandlingUtils(MOD_ID);

    @Override
    public void onInitialize() {

        ServerTickEvents.END_SERVER_TICK.register(new ServerListener());

    }

}
