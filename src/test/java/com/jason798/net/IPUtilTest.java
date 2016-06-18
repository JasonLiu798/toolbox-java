package com.jason798.net;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * IPUtil Tester.
 *
 * @version 1.0
 */
public class IPUtilTest {


    /**
     * Method: ip2Long(String ip)
     */
    @Test
    public void testIp2Long() throws Exception {
        long[] a = {
                2031683355,
                2104927966,
                3062488963l,
                455847545,
                1895469722
        };
        for (int i = 0; i < a.length; i++) {
            System.out.println(IPUtil.long2ip(a[i]));
        }
    }

    /**
     * Method: long2ip(long ip)
     */
    @Test
    public void testLong2ip() throws Exception {

    }

    /**
     * Method: main(String[] args)
     */
    @Test
    public void testMain() throws Exception {

    }

}
