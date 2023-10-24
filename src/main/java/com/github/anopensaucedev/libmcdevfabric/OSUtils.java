package com.github.anopensaucedev.libmcdevfabric;

import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.nio.charset.StandardCharsets;

public class OSUtils {

    private static String LSB_REMOVAL_PATTERN = "Description:\t"; // we don't use lsb_release, because most distros either a) don't have it, or b) allow /etc/os-release to be accessed.


    //fetch distribution here.
    public static String fetchOSData(){

        try {

            Process data = Runtime.getRuntime().exec("cat /etc/os-release");
            String osdata = new String(data.getInputStream().readAllBytes(),StandardCharsets.UTF_8);
            String[] osrelease = osdata.split("\\r?\\n"); // so we can actually read the lines
            String osReleaseName;
            for (int x = 0; x < osrelease.length; x++){
                if(osrelease[x].contains("NAME")){
                    osReleaseName = osrelease[x];
                    return osReleaseName.substring(5).replace("\"", ""); // remove quotes
                }
            }
            return "Generic Linux";
        } catch (IOException e) {
            e.printStackTrace();
            return "Unknown OS"; // fail "gracefully"
        }



    }

}
