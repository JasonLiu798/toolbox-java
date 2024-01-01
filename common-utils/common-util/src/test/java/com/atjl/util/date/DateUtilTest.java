package com.atjl.util.date;

import org.junit.Test;

import java.util.Date;

public class DateUtilTest {

    @Test
    public void testGetDayOfWeek() {
        int w = DateUtil.getDayOfWeek(new Date());
        System.out.println("res:" + w);
    }
}
