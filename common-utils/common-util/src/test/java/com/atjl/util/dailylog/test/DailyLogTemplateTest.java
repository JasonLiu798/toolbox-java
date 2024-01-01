package com.atjl.util.dailylog.test;

import com.atjl.util.dailylog.DailyLogTemplate;
import com.atjl.util.dailylog.TimeItem;
import com.atjl.util.log.LogUtil;
import org.junit.Test;

import java.util.List;

public class DailyLogTemplateTest {


    @Test
    public void testGet(){
        List<TimeItem> tis =  DailyLogTemplate.getNormal();
        System.out.println("res:"+ LogUtil.toJsonStringFmt(tis));
    }
}
