package com.jason798.common;

/**
 * 开发 阶段用
 */
public class DevContext {

    public static final Boolean dev = true;

    public static boolean test = false;

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
