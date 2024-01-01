package com.atjl.util.dailylog;

import com.atjl.util.date.DateUtil;
import com.atjl.util.date.TimestampUtil;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class Day implements Serializable {

    private static final long serialVersionUID = -8263938322656235199L;

    List<TimeItem> timeItems;

    private Long ts;

    /**
     * return string yyyy-MM-dd
     */
    public String getDay(){
        return TimestampUtil.format(ts, DateUtil.yyyy_MM_dd_EN);
    }

    public Day() {
        timeItems = new ArrayList<>();
    }

    public void addTimeItem(TimeItem ti){
        timeItems.add(ti);
    }
}
