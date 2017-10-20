package com.atjl.util.number;

import org.junit.Test;

import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;

/**
 * NumberUtil Tester.
 *
 * @author <Authors name>
 * @version 1.0
 */
public class NumberHelperTest {

    /**
     * Method: long2Int(long lnum)
     */
    @Test
    public void testLong2Int() throws Exception {

		HashMap hm = new HashMap();
		hm.put("","");
    }

    /**
     * Method: filterDot(String number)
     */
    @Test
    public void testFilterDot() throws Exception {
        assertEquals("23", NumberUtil.filterDot("23"));
        assertEquals("1", NumberUtil.filterDot("1.23"));
        assertEquals("0", NumberUtil.filterDot("0.23"));
    }

    /**
     * Method: minus(T t1, T t2)
     */
    @Test
    public void testMinus() throws Exception {

    }

    /**
     * Method: incr(T t1)
     */
    @Test
    public void testIncr() throws Exception {

    }

    /**
     * Method: add(T t1, T t2)
     */
    @Test
    public void testAdd() throws Exception {

    }

    /**
     * Method: div(T t1, T t2)
     */
    @Test
    public void testDiv() throws Exception {

    }

    /**
     * Method: mod(T t1, T t2)
     */
    @Test
    public void testMod() throws Exception {

    }

    /**
     * Method: getNumber(Class<T> cls, int i)
     */
    @Test
    public void testGetNumber() throws Exception {

    }

}
