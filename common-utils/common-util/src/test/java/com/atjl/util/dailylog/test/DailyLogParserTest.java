package com.atjl.util.dailylog.test;

import com.atjl.util.dailylog.DailyLogParser;
import com.atjl.util.dailylog.Day;
import com.atjl.util.log.LogUtil;
import org.junit.Test;

import java.util.Map;

public class DailyLogParserTest {

    @Test
    public void testRead() {

        String fileR = "log/daily.log";
        Map<String, Day> map = DailyLogParser.parse(fileR);

        System.out.println("res:" + LogUtil.toJsonStringFmt(map));

    }
}
