package com.atjl.util.dailylog;

import com.atjl.util.file.FileUtilEx;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class DailyLogTemplate {

    public static final String NORMAL = "log/normal.log";

    public static final String WEEKEND = "log/weekend.log";

    public static List<TimeItem> getNormal() {
        return getTemplate(NORMAL);
    }

    public static List<TimeItem> getWeekend() {
        return getTemplate(WEEKEND);
    }


    public static List<TimeItem> getTemplate(String file) {
        String contents = FileUtilEx.catFromClassPath(file);
        String[] contentArr = contents.split("\r\n");
        List<TimeItem> list = new ArrayList<>();
        for (String line : contentArr) {
            if (StringUtils.isBlank(line)) {
                continue;
            }
            TimeItem ti = new TimeItem();
            if (line.contains("，")) {
                String[] item = line.split("，");
                ti.setTemplateTime(item[0]);
                ti.setContent(item[1]);
                ti.setTag(item[2]);
            } else {
                ti.setTemplateTime(line);
            }
            list.add(ti);
        }
        return list;
    }


}
