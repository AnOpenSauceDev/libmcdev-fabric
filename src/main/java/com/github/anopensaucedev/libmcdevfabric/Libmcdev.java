package com.github.anopensaucedev.libmcdevfabric;

import com.github.anopensaucedev.libmcdevfabric.tests.Test;
import net.fabricmc.api.ModInitializer;
import org.spongepowered.include.com.google.common.base.Charsets;

public class Libmcdev implements ModInitializer {
    /**
     * Runs the mod initializer.
     */



    @Override
    public void onInitialize() {

        Test.runTests();

        //testDebug.Log(new String(testDataHandler.Reader.ReadData("helloworld"), Charsets.UTF_8));

    }
}
