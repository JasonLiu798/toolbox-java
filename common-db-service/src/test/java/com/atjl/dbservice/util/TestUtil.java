package com.atjl.dbservice.util;


import org.junit.Test;

public class TestUtil {

    @Test
    public void test() {
        String raw = "1";
        String tgtStr = "1";
        boolean res = raw.compareTo(tgtStr)>=0;
        System.out.println("res:"+res);
    }
}
