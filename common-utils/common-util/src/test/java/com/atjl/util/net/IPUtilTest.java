package com.atjl.util.net;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

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


    @Test
    public void testIsIp() throws Exception {
        boolean res = IPUtil.isIp("127.0.0.1");
        assertEquals(true, res);
        res = IPUtil.isIp("255.255.255.255");
        assertEquals(true, res);
        res = IPUtil.isIp("1.2.23.4");
        assertEquals(true, res);

        res = IPUtil.isIp("23.4");
        assertEquals(false, res);
        res = IPUtil.isIp("256.123.23.4");
        assertEquals(false, res);
        res = IPUtil.isIp("123.2500.3.4");
        assertEquals(false, res);
        res = IPUtil.isIp("123.23.663.4");
        assertEquals(false, res);
        res = IPUtil.isIp("123.23.0.434");
        assertEquals(false, res);
        res = IPUtil.isIp("0.0.0.0");
        assertEquals(true, res);
    }


    @Test
    public void getMac(){
        String mac = IPUtil.getMac("127.0.0.1");
        System.out.println("res:"+mac);
    }
    /**
     * Method: main(String[] args)
     */
    @Test
    public void testMain() throws Exception {

    }

}
