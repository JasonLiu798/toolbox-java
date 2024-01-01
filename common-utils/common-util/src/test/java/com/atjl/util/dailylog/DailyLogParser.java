package com.atjl.util.dailylog;

import com.atjl.util.date.DateUtil;
import com.atjl.util.file.FileUtilEx;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

@Slf4j
public class DailyLogParser {


    public static final String CUR_YEAR = "2023";

    public static void cov(String file) {

        String res = FileUtilEx.catFromClassPath(file);
        log.info("read {}", res);
        String[] contentArr = res.split("\n");
        Map<String, Day> yearMap = new HashMap<>();

        Boolean first = true;
        Day curDay = null;
        List<String> contents = new ArrayList<>();
        for (String line : contentArr) {
            if (StringUtils.isBlank(line)) {
                continue;
            }
            log.info("line {}", line);
            if (line.startsWith("##")) {
                if (!first) {
                    // process last
                    if (CollectionUtils.isNotEmpty(contents)) {
                        for (String c : contents) {

                        }
                    }
                }

                // process new
                String dayStr = line.substring(2);
                dayStr = dayStr.trim();
                // yyyy-MM-dd HH:mm:ss
                Date date = DateUtil.parse(CUR_YEAR + "/" + dayStr, "yyyy/MM/dd");
                Day d = new Day();
                d.setTs(date.getTime() / 1000);
                yearMap.put(d.getDay(), d);

                curDay = d;
                contents = new ArrayList<>();
                // process
            } else {
                contents.add(line);


            }
        }
    }


    private List<TimeItem> parseItems(String day, List<String> lines) {

        List<TimeItem> itemAll = new ArrayList<>();
        for (String line : lines) {
            List<TimeItem> items = parseItem(line);
            if (CollectionUtils.isNotEmpty(items)) {
                itemAll.addAll(items);
            }
        }


        return null;
    }

    private List<TimeItem> parseItem(String line) {
        // C2 包菜炒肉；西红柿炒鸡蛋，糖放多了
        String[] lineContentArr = line.split(" ");
        String typeAndHour = lineContentArr[0];
        String type = typeAndHour.substring(0, 1);
        String hour = typeAndHour.substring(1);
        Integer hourInt = Integer.parseInt(hour);

        StringBuilder sb = new StringBuilder();
        int last = lineContentArr.length - 1;
        for (int i = 1; i < lineContentArr.length; i++) {
            sb.append(lineContentArr[i]);
            if (i != last) {
                sb.append("-");
            }
        }

        String tag = null;
        if ("C".equals(type)) {
            // cook
            tag = TimeItemTagEnum.GAME.getCode();
        } else if ("W".equals(type)) {
            tag = TimeItemTagEnum.FORCE.getCode();
        } else if ("T".equals(type)) {
            tag = TimeItemTagEnum.GAME.getCode();
        } else if ("G".equals(type)) {
            tag = TimeItemTagEnum.GAME.getCode();
        } else if ("M".equals(type)) {
            tag = TimeItemTagEnum.GAME.getCode();
        } else if ("D".equals(type)) {
            tag = TimeItemTagEnum.EFFICIENT.getCode();
        } else {
            tag = TimeItemTagEnum.WASTE.getCode();
        }

        List<TimeItem> items = new ArrayList<>();
        for (int i = 0; i < hourInt * 2; i++) {
            TimeItem ti = new TimeItem();
            ti.setTag(tag);
            ti.setContent(sb.toString());
            items.add(ti);
        }
        return items;
    }


}
