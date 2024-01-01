package com.atjl.util.dailylog;

import com.atjl.util.date.DateUtil;
import com.atjl.util.date.HolidayUtils;
import com.atjl.util.file.FileUtilEx;
import com.atjl.util.log.LogUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

@Slf4j
public class DailyLogParser {

    public static final String ENTER = "\r\n";

    public static final String CUR_YEAR = "2023";

    public static Map<String, Day> parse(String file) {
        String res = FileUtilEx.catFromClassPath(file);
        log.info("read {}", res);
        String[] contentArr = res.split(ENTER);
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
                        List<TimeItem> ti = parseItems(curDay, contents);
                        curDay.setTimeItems(ti);
                    }
                } else {
                    first = false;
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
        return yearMap;
    }


    private static List<TimeItem> parseItems(Day day, List<String> lines) {
        // day format
        String dayStr = day.getDay();
        Date d = DateUtil.parse(dayStr, DateUtil.yyyy_MM_dd_EN);
        Boolean holiday = HolidayUtils.isHoliday(d);
        List<TimeItem> tiList = null;
        if (holiday) {
            tiList = DailyLogTemplate.getWeekend();
        } else {
            tiList = DailyLogTemplate.getNormal();
        }

        List<TimeItem> rawItemList = new ArrayList<>();
        for (String line : lines) {
            List<TimeItem> items = parseItem(line);
            if (CollectionUtils.isNotEmpty(items)) {
                rawItemList.addAll(items);
            }
        }

        int cnt = DailyLogBatchUtil.countEmptyContent(tiList);
        if (rawItemList.size() > cnt) {
            log.info("item count more than template,{}", LogUtil.toJsonStringNoExcep(day));
        }
        List<TimeItem> res = new ArrayList<>();
        int i = 0;
        for (TimeItem ti : tiList) {
            if (StringUtils.isBlank(ti.getContent())) {
                TimeItem input = rawItemList.get(i);
                ti.setContent(input.getContent());
                ti.setTag(input.getTag());
                // yyyy-MM-dd
                String dateTime = day.getDay() + " " + ti.getTemplateTime();
                Date itemDate = DateUtil.parse(dateTime, DateUtil.yyyy_MM_dd_EN + " " + "HHmm");
                ti.setTs(itemDate.getTime());
                res.add(ti);
            }
        }
        return res;
    }

    private static List<TimeItem> parseItem(String line) {
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
