package com.jason798.performance;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.concurrent.ThreadPoolExecutor;


public class RunTimeStatisticsHelperTest {

    /**
     * Method: addRunTimeDto(RunTimeDto rtd)
     */
    @Test
    public void testAddRunTimeDto() throws Exception {
        RunTimeHelper.addTime();
        Thread.sleep(90);
        RunTimeHelper.addTime();
        Thread.sleep(80);
        RunTimeHelper.addTime();
        RunTimeDto d = RunTimeHelper.getRunTimeDto();
        RunTimeStatisticsHelper.addRunTimeDto(d);
        RunTimeHelper.clear();

        RunTimeHelper.addTime();
        Thread.sleep(100);
        RunTimeHelper.addTime();
        Thread.sleep(110);
        RunTimeHelper.addTime();
        Thread.sleep(60);
        RunTimeHelper.addTime();
        d = RunTimeHelper.getRunTimeDto();
        RunTimeStatisticsHelper.addRunTimeDto(d);
        RunTimeHelper.clear();

        String s = RunTimeStatisticsHelper.getStatisticsFormat();
        System.out.println(s);

    }

    /**
     * Method: getResultString()
     */
    @Test
    public void testGetResultString() throws Exception {

    }

    /**
     * Method: clear()
     */
    @Test
    public void testClear() throws Exception {

    }



}
