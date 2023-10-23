package com.github.anopensaucedev.libmcdevfabric;

import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Debug {


    public String DebugLoggerName;

    public static String InternalLoggerName = "libMCdev Debug Logger";

    public Logger DebugLogger;

    public static Logger InternalLogger = LoggerFactory.getLogger(InternalLoggerName);

    public static boolean isDev = FabricLoader.getInstance().isDevelopmentEnvironment();

    public void setLoggerName(String newName){
        DebugLoggerName = newName;
        DebugLogger = LoggerFactory.getLogger(DebugLoggerName); // reset our logger
    }

    public Debug(String debugLoggerName){
        this.DebugLoggerName = debugLoggerName;
        this.DebugLogger = LoggerFactory.getLogger(DebugLoggerName);
    }

    public static void LogInternal(String x){
        if(isDev)
            InternalLogger.info(x);
    }

    public static void InternalLogWarning(String x){
        if(isDev)
            InternalLogger.warn(x);
    }

    public static void InternalLogError(String x){
        if(isDev)
            InternalLogger.error(x);
    }

    public void Log(String x){
        if(isDev)
            DebugLogger.info(x);
    }

    public void LogWarning(String x){
        if(isDev)
            DebugLogger.warn(x);
    }

    public void LogError(String x){
        if(isDev)
            DebugLogger.error(x);
    }
}
