package com.atjl.util.dailylog;

import lombok.Data;

import java.io.Serializable;

@Data
public class TimeItem implements Serializable, Comparable<TimeItem> {
    private static final long serialVersionUID = -5966081483028949977L;

    Long ts;

    String templateTime;

    String content;
    String tag;

    public TimeItem() {
    }

    public TimeItem(String templateTime, String content, String tag) {
        this.templateTime = templateTime;
        this.content = content;
        this.tag = tag;
    }

    @Override
    public int compareTo(TimeItem o) {
        return Math.toIntExact(o.getTs() - ts);
    }
}