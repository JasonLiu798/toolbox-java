package com.jason798.common;

/**
 * for test,dev
 */
public class DevContext {

	//for dev
    public static final Boolean dev = true;
	
	public static boolean isDev(){
		return dev;
	}
	public static boolean isPrd(){
		return !dev;
	}
	
    //for temp test
    public static boolean test = false;
	public static boolean isNotTest(){
		return !test;
	}
	public static boolean isTest(){
		return test;
	}
	
	

}
