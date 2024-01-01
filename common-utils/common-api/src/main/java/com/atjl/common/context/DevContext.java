package com.atjl.common.context;

/**
 * for test
 */
public class DevContext {

    public static Boolean dev = false;

    public static boolean test = false;

    public static void devOn(){
        dev=true;
    }

    public static void devOff(){
        dev=false;
    }

    public static boolean isDev(){
        return dev;
    }

    public static boolean isNotTest(){
        return !test;
    }
    public static boolean isTest(){
        return test;
    }

    public static boolean isPrd(){
        return !dev;
    }

}
