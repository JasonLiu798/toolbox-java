package com.atjl.util.date;

import com.atjl.util.api.LunarDTO;
import com.atjl.util.log.LogUtil;
import org.junit.Test;

import java.util.Date;

public class LunarUtilTest {

    @Test
    public void test() {

        Date d = new Date();
        LunarDTO l = LunarUtil.coverte(d);
        System.out.println("res:" + LogUtil.toJsonStringNoExcep(l));

    }
}
