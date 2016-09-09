package com.jason798.performance;

import com.jason798.number.LongCalculator;
import com.jason798.number.NumberHelper;
import com.jason798.number.RandomGenerator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


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
    public void testGetStat() throws Exception {
        List<Long> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(RandomGenerator.generateLong(1,99999));
        }
        System.out.println(list);
        RunTimeStatisticsDto dto = RunTimeStatisticsHelper.
                getStat(
                        list.toArray(new Long[list.size()]),
                        (o1, o2) -> NumberHelper.long2Int(o1 - o2),
                        new LongCalculator(),
                        num -> num);
        Collections.sort(list);
        System.out.println(list);
        System.out.println(dto);
    }

    /**
     * Method: clear()
     */
    @Test
    public void testClear() throws Exception {

    }


}
