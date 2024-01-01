package com.atjl.util.date;

import com.atjl.util.api.LunarDTO;

import java.time.LocalDate;
import java.util.Date;

public class HolidayUtils {

    // 判断日期是否是周末
    public static boolean isWeekend(Date date) {
        int dayOfWeek = DateUtil.getDayOfWeek(date);
        return dayOfWeek == 6 || dayOfWeek == 7;
    }


    /**
     * 判断日期是否是法定节假日
     */
    public static boolean isPublicHoliday(Date date) {
        LunarDTO l = LunarUtil.coverte(date);
        if (l == null) {
            return false;
        }
        int month = l.getMonth();
        int day = l.getDay();
        // 判断春节
        if (month == 1 && (day >= 1 && day <= 7)) {
            return true;
        }
        // 判断清明节
        if (month == 4 && (day >= 4 && day <= 6)) {
            return true;
        }

        // 判断劳动节
        if (month == 5 && (day >= 1 && day <= 3)) {
            return true;
        }

        // 判断国庆节
        if (month == 10 && (day >= 1 && day <= 7)) {
            return true;
        }
        return false;
    }

    // 判断日期是否是传统节日
    public static boolean isTraditionalHoliday(Date date) {
        // TODO: 实现传统节日的判断逻辑
        return false;
    }

    // 判断日期是否是节假日
    public static boolean isHoliday(Date date) {
        return isWeekend(date) || isPublicHoliday(date) || isTraditionalHoliday(date);
    }

}