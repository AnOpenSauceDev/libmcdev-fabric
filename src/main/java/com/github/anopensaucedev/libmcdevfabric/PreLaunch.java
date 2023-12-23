package com.github.anopensaucedev.libmcdevfabric;

import com.github.anopensaucedev.libmcdevfabric.inspector.InspectorWindow;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;

public class PreLaunch implements PreLaunchEntrypoint {
    @Override
    public void onPreLaunch() { // we can't create windows after MC does so.
        /* Inspector is too messy
        if(Debug.isDev){
        InspectorWindow.SetupInspectorWindow();
        }
         */
    }
}
