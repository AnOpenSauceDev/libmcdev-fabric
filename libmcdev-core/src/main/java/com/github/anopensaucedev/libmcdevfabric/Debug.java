package com.github.anopensaucedev.libmcdevfabric;

import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Debug {


    public String DebugLoggerName; // the name of our logger

    public static String InternalLoggerName = "libMCdev Debug Logger";

    public Logger DebugLogger;

    public static Logger InternalLogger = LoggerFactory.getLogger(InternalLoggerName);

    public static boolean isDev = FabricLoader.getInstance().isDevelopmentEnvironment(); // Useful for running developer-only code in-game

    public void setLoggerName(String newName){ // allows developers to change debug logger names.
        DebugLoggerName = newName;
        DebugLogger = LoggerFactory.getLogger(DebugLoggerName); // reset our logger
    }

    public Debug(String debugLoggerName){ // create a logger instance
        this.DebugLoggerName = debugLoggerName;
        this.DebugLogger = LoggerFactory.getLogger(DebugLoggerName);
    }

    public static void LogInternal(String... strings){
        if(isDev)
            for(int x = 0; x < strings.length; x++){
                InternalLogger.info(strings[x]);
            }
    }

    public static void InternalLogWarning(String... strings){
        if(isDev)
            for(int x = 0; x < strings.length; x++){
                InternalLogger.warn(strings[x]);
            }
    }

    public static void InternalLogError(String... strings){
        if(isDev)
            for(int x = 0; x < strings.length; x++){
                InternalLogger.error(strings[x]);
            }
    }

    public void Log(String... strings){
        if(isDev)
            for(int x = 0; x < strings.length; x++){
            DebugLogger.info(strings[x]);
            }
    }

    public void LogWarning(String... strings){
        if(isDev)
            for(int x = 0; x < strings.length; x++){
                DebugLogger.warn(strings[x]);
            }
    }

    public void LogError(String... strings){
        if(isDev)
            for(int x = 0; x < strings.length; x++){
                DebugLogger.error(strings[x]);
            }
    }
}
