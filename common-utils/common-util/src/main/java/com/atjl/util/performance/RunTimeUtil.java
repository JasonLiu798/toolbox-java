package com.atjl.util.performance;

import com.atjl.util.collection.CollectionUtilEx;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 链路计时工具
 * usage:
 * 1.simplest way
 * RunTimeUtil.addTime();
 * 	xxx
 * RunTimeUtil.addTime();
 *  yyy
 * System.out.println(RunTimeUtil.addTimeAndGetStdout());
 *
 * 2.normal usage
 *
 * 性能测试需要注意的问题：
 * 1.初始化耗时
 * 2.数据较大情况下的 GC耗时
 *
 * Created by JasonLiu798 on 16/4/5.
 */
public final class RunTimeUtil {
	private RunTimeUtil(){
		throw new UnsupportedOperationException();
	}
	
	/**
	 * store data
	 */
    private static ThreadLocal<RunTimeDto> time = new ThreadLocal<>();

    /**
     * templates
     */
    public static final String DFT = "default";
    public static final String CSV = "csv";
    private static final String TOTAL_KEY = "totalcost";
    private static final String SECTION_KEY = "section";
    private static final String EMPTY_KEY = "empty";
    private static Map<String, Map<String, String>> fmtTemplates = new ConcurrentHashMap<>();

    static {
        /**
         * init template
         */
        if (fmtTemplates.size() <= 0) {
            //init default template
            Map<String, String> defaultTemplate = new HashMap<>();
            defaultTemplate.put(TOTAL_KEY, "total cost: %s ms,%s");//  time,comment
            defaultTemplate.put(SECTION_KEY, "section %s cost: %s ms, %s");
            defaultTemplate.put(EMPTY_KEY, "cost time:not enough record");
            fmtTemplates.put(DFT, defaultTemplate);
            //init csv template
            Map<String, String> csvTemplate = new HashMap<>();
            csvTemplate.put(TOTAL_KEY, "total cost, %s, %s");//  time,comment
            csvTemplate.put(SECTION_KEY, "section %s cost, %s, %s");
            csvTemplate.put(EMPTY_KEY, "cost time,no record, %s");
            fmtTemplates.put(CSV, csvTemplate);
        }
    }

    public static void clear() {
        time.set(null);
    }

    public static RunTimeDto getRunTimeDto() {
        return time.get();
    }

    public static void init() {
        time.set(new RunTimeDto());
    }

    public static void resetAndRestart() {
        init();
        addTime();
    }


    public static String addTimeAndGetStdout() {
        addTime();
        return getFmtTimeForStdout();
    }


    public static String getFmtTimeForStdout(RunTimeDto dto) {
        return getFmtTimeForStdout(dto, 0);
    }

    public static String getFmtTimeForStdout() {
        RunTimeDto dto = time.get();
        return getFmtTimeForStdout(dto, 0);
    }

    public static String getFmtTimeForStdout(RunTimeDto dto, int type) {
        List<String> lines = getFmtTimeForWrite(dto, type);
        StringBuilder res = new StringBuilder() ;
        for (String line : lines) {
            res .append( line).append( "\n");
        }
        return res.toString();
    }

    /**
     * 0
     * 1
     *
     * @param type
     * @return
     */
    public static List<String> getFmtTimeForWrite(RunTimeDto dto, int type) {
        List<String> res = new LinkedList<>();
        String fmtTemplateKey = "csv";

        Map<String, String> fmtTemplate = fmtTemplates.get(DFT);
        if (type == 1) {
            fmtTemplate = fmtTemplates.get(fmtTemplateKey);
        }

        List<Long> timeList = getTime(dto);
        List<String> commentList = getComment(dto);

        if (CollectionUtilEx.isNotEmpty(timeList) && CollectionUtilEx.isNotEmpty(commentList)) {
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

    public static List<String> getComment(RunTimeDto rd) {
        //RunTimeDto rd = time.get();
        if (rd != null && rd.hasComment()) {
            return rd.getComments();
        }
        return null;
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


    public static List<Long> getTime(RunTimeDto dto) {
        if (dto != null && dto.hasTime()) {
            return dto.getTimes();
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
     * add a system.currentmill
     * PS: first add time ,the comment will not show
     * @param cmt comment of the pre time to this time 's meaning
     */
    public static void addTime(String cmt) {
        RunTimeDto rd = time.get();
        if (rd == null) {
            rd = new RunTimeDto();
        }
        rd.addTime(cmt);
        time.set(rd);
    }


    /**
     * get output template keys
     *
     * @return
     */
    public static Set<String> getTemplates() {
        return fmtTemplates.keySet();
    }


}