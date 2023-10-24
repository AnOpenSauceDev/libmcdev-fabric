package com.github.anopensaucedev.libmcdevfabric;

import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.nio.charset.StandardCharsets;

public class OSUtils {

    private static String LSB_REMOVAL_PATTERN = "Description:\t";

    //fetch distribution here.
    public static String fetchOSData(){

        try {
            // this should work on: Arch Linux, Debian + derivatives. NOTE: not all distro's, (especially older ones) support this
            Process data = Runtime.getRuntime().exec("lsb_release -d");
            String osdata = new String(data.getInputStream().readAllBytes(),StandardCharsets.UTF_8); // We now have our OS data, but it has annoying fluff still.
            return osdata.replace(LSB_REMOVAL_PATTERN,""); // remove "Description:\t", so we only have the distro name (\t is the tab escape code)
        } catch (IOException e) {
            e.printStackTrace();
            return "Unknown OS"; // fail "gracefully"
        }



    }

}
