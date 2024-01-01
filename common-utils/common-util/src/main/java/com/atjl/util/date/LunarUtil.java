package com.atjl.util.date;

import com.atjl.util.log.LogUtil;
import com.nlf.calendar.Lunar;
import com.atjl.util.api.LunarDTO;

import java.util.Date;

public class LunarUtil {

    public static LunarDTO coverte(Date d) {
        Lunar l = Lunar.fromDate(d);
        if (l == null) {
            return null;
        }
        LunarDTO lo = new LunarDTO();
        lo.setDay(l.getDay());
        lo.setMonth(l.getMonth());
        lo.setYear(l.getYear());
        return lo;
    }


}
