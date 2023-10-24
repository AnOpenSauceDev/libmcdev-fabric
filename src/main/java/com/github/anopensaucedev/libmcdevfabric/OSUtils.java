package com.github.anopensaucedev.libmcdevfabric;

import org.apache.commons.lang3.SystemUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class OSUtils {

    private static String LSB_REMOVAL_PATTERN = "Description:\t"; // we don't use lsb_release, because most distros either a) don't have it, or b) allow /etc/os-release to be accessed.

    public static boolean IS_UNIX_LIKE = SystemUtils.IS_OS_LINUX || SystemUtils.IS_OS_UNIX || SystemUtils.IS_OS_SOLARIS || SystemUtils.IS_OS_FREE_BSD;
    //fetch distribution here.
    public static String fetchOSName(){

        // usually SystemUtils will pull most of the weight with Linux, but if you want distro-level names, you may have to use this.

        if(IS_UNIX_LIKE) {
            try {

                Process data = Runtime.getRuntime().exec("cat /etc/os-release");
                String osdata = new String(data.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
                String[] osrelease = osdata.split("\\r?\\n"); // so we can actually read the lines
                String osReleaseName;
                for (int x = 0; x < osrelease.length; x++) {
                    if (osrelease[x].contains("NAME")) {
                        osReleaseName = osrelease[x];
                        String Distro = osReleaseName.substring(5).replace("\"", ""); // remove quotes
                        if(Distro.matches("(?i)Linux")){
                            return "Generic Linux";
                        }else {
                            return Distro;
                        }

                    }
                }
                return "Generic Unix-Like OS";
            } catch (IOException e) {
                e.printStackTrace();
                return "Unknown Linux"; // fail "gracefully"
            }
        }

        if(SystemUtils.IS_OS_WINDOWS){
            return System.getProperty("os.name");
        }

        if(SystemUtils.IS_OS_MAC){
            return System.getProperty("os.name");
        }

        return "Unknown OS";


    }



}
