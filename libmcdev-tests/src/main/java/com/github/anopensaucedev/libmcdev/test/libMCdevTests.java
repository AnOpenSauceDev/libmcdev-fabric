package com.github.anopensaucedev.libmcdev.test;

import com.github.anopensaucedev.libmcdevfabric.Debug;
import net.fabricmc.api.ModInitializer;

public class libMCdevTests implements ModInitializer {
    @Override
    public void onInitialize() {
        Debug.LogInternal("tests are working!");
    }
}
