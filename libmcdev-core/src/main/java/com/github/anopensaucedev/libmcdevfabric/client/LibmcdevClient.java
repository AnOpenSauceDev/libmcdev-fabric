package com.github.anopensaucedev.libmcdevfabric.client;

import com.github.anopensaucedev.libmcdevfabric.Debug;
import com.github.anopensaucedev.libmcdevfabric.Libmcdev;
import com.github.anopensaucedev.libmcdevfabric.TempNameGenerator;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
@Environment(EnvType.CLIENT)
public class LibmcdevClient implements ClientModInitializer {


    /**
     * Runs the mod initializer on the client environment.
     */
    @Override
    public void onInitializeClient() {
        Libmcdev.isClient = true; // race conditions shouldn't be a problem, as long as you're careful.
    }

}
