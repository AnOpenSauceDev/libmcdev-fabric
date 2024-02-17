package com.github.anopensaucedev.libmcdevfabric;

public class TempNameGenerator {

    static String[] Adverbs = {"amazingly", "astonishingly", "awesomely", "fantastically", "refreshingly"};

    static String[] Adjectives = {"designed", "crafted", "bodged", "prepared", "written"};

    static String[] Noun = {"piglin", "chair", "stick", "trashheap", "spider"};

    public static String returnTempName(){ // messy, but cool
        return Adverbs[MCDEVMathUtils.SHARED_RANDOM.nextInt(Adverbs.length)] + "-" + Adjectives[MCDEVMathUtils.SHARED_RANDOM.nextInt(Adjectives.length)] + "-" + Noun[MCDEVMathUtils.SHARED_RANDOM.nextInt(Noun.length)];
    }

}
