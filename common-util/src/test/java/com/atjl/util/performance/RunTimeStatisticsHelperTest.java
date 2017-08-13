package com.atjl.util.performance;

import com.atjl.util.number.LongCalculator;
import com.atjl.util.number.NumberUtil;
import com.atjl.util.number.RandomUtil;
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
        RunTimeUtil.addTime();
        Thread.sleep(90);
        RunTimeUtil.addTime();
        Thread.sleep(80);
        RunTimeUtil.addTime();
        RunTimeDto d = RunTimeUtil.getRunTimeDto();
        RunTimeStatisticsUtil.addRunTimeDto(d);
        RunTimeUtil.clear();

        RunTimeUtil.addTime();
        Thread.sleep(100);
        RunTimeUtil.addTime();
        Thread.sleep(110);
        RunTimeUtil.addTime();
        Thread.sleep(60);
        RunTimeUtil.addTime();
        d = RunTimeUtil.getRunTimeDto();
        RunTimeStatisticsUtil.addRunTimeDto(d);
        RunTimeUtil.clear();

        String s = RunTimeStatisticsUtil.getStatisticsFormat();
        System.out.println(s);

    }

    /**
     * Method: getResultString()
     */
    @Test
    public void testGetStat() throws Exception {
        List<Long> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(RandomUtil.generateLong(1,99999));
        }
        System.out.println(list);
        RunTimeStatisticsDto dto = RunTimeStatisticsUtil.
                getStat(
                        list.toArray(new Long[list.size()]),
                        (o1, o2) -> NumberUtil.long2Int(o1 - o2),
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
