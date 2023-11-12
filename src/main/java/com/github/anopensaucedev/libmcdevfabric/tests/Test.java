package com.github.anopensaucedev.libmcdevfabric.tests;

import com.github.anopensaucedev.libmcdevfabric.data.DataHandlingUtils;
import com.github.anopensaucedev.libmcdevfabric.Debug;
import com.github.anopensaucedev.libmcdevfabric.OSUtils;
import com.github.anopensaucedev.libmcdevfabric.TempNameGenerator;

public class Test {

    private static DataHandlingUtils dataHandlingUtils = new DataHandlingUtils("libmcdev-test");

    public static void runTests(){

        Debug testslogger = new Debug(TempNameGenerator.returnTempName());



        try{
            testslogger.Log("----------------------");
            testslogger.Log("| TEST NAME | RESULT |");
            dataHandlingUtils.Writer.WriteData("helloworld","Hello, World!".toCharArray());
            testslogger.Log("| Data Write: PASS |");
            dataHandlingUtils.Reader.ReadData("helloworld");
            testslogger.Log("| Data Read: PASS |");
            testslogger.Log("| OS FETCH: " + OSUtils.fetchOSName() + " |");
            testslogger.Log("----------------------");
            testslogger.Log("Is OS Unix-Like? " + OSUtils.IS_UNIX_LIKE);

        }catch (Exception exception){
            exception.printStackTrace();
            Debug.LogInternal("Tests have failed! Please fix these as soon as possible!");
            return;
        }

    }

}
