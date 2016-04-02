package com.jason.test;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author JianLong
 * @date 2016/2/16 17:45
 */
public class TestTemplate {
    private static String sfield1="aaaaaaaaaaa";
    private int field1;
    private double field2;
    public static void main(String[] args) {
        int a=5966;
        ArrayList<Date> list = new ArrayList<Date>();
        Date date=new Date();
        list.add(date);
        Date myDate = list.get(0);
        TestTemplate t = new TestTemplate();
        double c=t.field1+t.field2;
        System.out.println(c);

    }
}
