package com.atjl.dbtiming.util;


import java.util.Date;

public class DbTimingUtil {
    private DbTimingUtil(){
        throw new UnsupportedOperationException();
    }

    public static Long getIntervalMs(Date start, Date end) {
        return end.getTime() - start.getTime();
    }
}
