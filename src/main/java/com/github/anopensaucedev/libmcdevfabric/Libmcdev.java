package com.github.anopensaucedev.libmcdevfabric;

import net.fabricmc.api.ModInitializer;

public class Libmcdev implements ModInitializer {
    /**
     * Runs the mod initializer.
     */

    Debug testDebug = new Debug("libMCdev's sanity checker"); // sanity checker

    @Override
    public void onInitialize() {
        Debug.LogInternal("libMCdev has loaded.");
        testDebug.Log("Test debugger sanity check 1");
        testDebug.setLoggerName("we love sanity tests here");
        testDebug.Log("yes we do");
    }
}
