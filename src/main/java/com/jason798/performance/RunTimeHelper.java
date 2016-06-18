package com.jason798.performance;

import com.jason798.collection.CollectionHelper;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 链路计时工具
 * Created by JasonLiu798 on 16/4/5.
 */
public class RunTimeHelper {
    private static ThreadLocal<RunTimeDto> time = new ThreadLocal<>();
    /**
     * templates
     */
    private static final String DFT = "default";
    private static final String CSV = "csv";
    private static final String TOTAL_KEY = "totalcost";
    private static final String SECTION_KEY = "section";
    private static final String EMPTY_KEY = "empty";
    private static Map<String, Map<String, String>> fmtTemplates = new ConcurrentHashMap<>();

    public static void init() {
        // add
        time.set(new RunTimeDto());
        /**
         * init template
         */
        if (fmtTemplates.size() <= 0) {
            Map<String, String> defaultTemplate = new HashMap<>();
            defaultTemplate.put(TOTAL_KEY, "total cost: %s ms,%s");//  time,comment
            defaultTemplate.put(SECTION_KEY, "section %s cost: %s ms, %s");
            defaultTemplate.put(EMPTY_KEY, "cost time:not enough record");
            fmtTemplates.put(DFT, defaultTemplate);
            Map<String, String> csvTemplate = new HashMap<>();
            csvTemplate.put(TOTAL_KEY, "total cost, %s, %s");//  time,comment
            csvTemplate.put(SECTION_KEY, "section %s cost, %s, %s");
            csvTemplate.put(EMPTY_KEY, "cost time,no record, %s");
            fmtTemplates.put(CSV, csvTemplate);
        }
    }

    public static void reset() {
        init();
    }

    public static void resetAndRestart() {
        init();
        addTime();
    }


    public static String getFmtTimeForStdout() {
        return getFmtTimeForStdout(0);
    }

    public static String getFmtTimeForStdout(int type) {
        List<String> lines = getFmtTimeForWrite(type);
        String res = "";
        for (String line : lines) {
            res += line + "\n";
        }
        return res;
    }

    /**
     * 0
     * 1
     *
     * @param type
     * @return
     */
    public static List<String> getFmtTimeForWrite(int type) {
        List<String> res = new LinkedList<>();
        String fmtTemplateKey = "csv";

        Map<String, String> fmtTemplate = fmtTemplates.get(DFT);
        if (type == 1) {
            fmtTemplate = fmtTemplates.get(fmtTemplateKey);
        }

        List<Long> timeList = getTime();
        List<String> commentList = getComment();
        if (CollectionHelper.isNotEmpty(timeList) && CollectionHelper.isNotEmpty(commentList)) {
            Long startTime = timeList.get(0);
            Long endTime = timeList.get(timeList.size() - 1);
            String startComment = commentList.get(0);
            if (timeList.size() > 2) {//more than two record
                res.add(String.format(fmtTemplate.get(TOTAL_KEY), endTime - startTime, DFT));
                //add detail times
                for (int i = 0, j = 1; j < timeList.size(); j++, i++) {
                    long t1 = timeList.get(i);
                    long t2 = timeList.get(j);
                    String cmt = commentList.get(i);
//                    if(StringHelper.isEmpty(cmt)){
//                        cmt = "section "+j;
//                    }
                    res.add(String.format(fmtTemplate.get(SECTION_KEY), j, t2 - t1, cmt));
                }
            } else {
                res.add(String.format(fmtTemplate.get(TOTAL_KEY), endTime - startTime, startComment));
            }
        } else {
            res.add(fmtTemplate.get(EMPTY_KEY));
        }
        return res;
    }

    public static List<String> getComment() {
        if (time != null) {
            RunTimeDto rd = time.get();
            if (rd != null && rd.hasComment()) {
                return rd.getComments();
            }
        }
        return null;
    }

    public static List<Long> getTime() {
        if (time != null) {
            RunTimeDto rd = time.get();
            if (rd != null && rd.hasTime()) {
                return rd.getTimes();
            }
        }
        return null;
    }

    public static void addTime() {
        addTime("");
    }

    /**
     * @param cmt
     */
    public static void addTime(String cmt) {
        RunTimeDto rd = time.get();
        if (rd == null) {
            rd = new RunTimeDto();
        }
        rd.addTime(cmt);
        time.set(rd);
    }

//	public static void main(String[] args) {
//		System.out.println(RunTimeUtil.timeReserve(123456789012L));
//	}

}