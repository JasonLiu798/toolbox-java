package com.jason.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * date util
 * @author JianLong
 * @date 2014/2/6 11:41
 */
public class DateUtil {

    public static final String yyyy_EN = "yyyy";
    public static final String yyyy_MM_dd_EN = "yyyy-MM-dd";
    public static final String yyyy_MM_dd_HH_mm_EN = "yyyy-MM-dd HH:mm";
    public static final String yyyyMMdd_EN = "yyyyMMdd";
    public static final String yyyy_MM_EN = "yyyy-MM";
    public static final String yyyyMM_EN = "yyyyMM";

    /** format(yyyy-MM-dd HH:mm:ss) */
    public static final String yyyy_MM_dd_HH_mm_ss_EN = "yyyy-MM-dd HH:mm:ss";
    /**
     * format(yyyy-MM-dd HH:mm:ss sss)
     */
    public static final String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss sss";
    /**
     * format(yyyyMMddHHmmsssss)
     */
    public static final String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmsssss";

    /** format(yyyyMMddHHmmss) */
    public static final String yyyyMMddHHmmss_EN = "yyyyMMddHHmmss";

    /** format(yyyy年MM月dd日) */
    public static final String yyyy_MM_dd_CN = "yyyy年MM月dd日";

    /** format(yyyy年MM月dd日HH时mm分ss秒) */
    public static final String yyyy_MM_dd_HH_mm_ss_CN = "yyyy年MM月dd日HH时mm分ss秒";

    /** format(yyyy年MM月dd日HH时mm分) */
    public static final String yyyy_MM_dd_HH_mm_CN = "yyyy年MM月dd日HH时mm分";

    public static final String GTM_DATE_FROM = "EEE, d MMM yyyy HH:mm:ss 'GMT'";


    /**
     * get timestamp last n number
     * @param n
     * @return
     */
    public static long getNowLastNTS(Integer n){
        long nowTs = System.currentTimeMillis();
        long base = 10;
        StringBuffer res = new StringBuffer();
        for(int i=0;i<n;i++){
            long inum = nowTs % base;
            res.append(inum);
            nowTs = nowTs / base;
        }
        return Long.parseLong(res.reverse().toString());
    }


    public static long getTimeSeconds() {
        long l = 0;
        Date date = new Date();
        int hour = date.getHours();
        int min = date.getMinutes();
        int second = date.getSeconds();
        l = second + min * 60 + hour * 60 * 60;
        return l;
    }

    /**
     * get now timestamp
     * format:long
     * @return
     */
    public static long getNowTS(){
        return new Date().getTime()/1000;
    }

    /**
     * unix timestamp to Date
     * @param ltime
     * @return
     */
    public static Date long2Date(long ltime){
        return new Date(ltime*1000);
    }

    /**
     * date format to unix timestamp
     * @param date
     * @return
     */
    public static long date2Timestamp(Date date){
        return date.getTime()/1000;
    }

    /**
     * unix timestamp long format to yyyymmdd String
     */
    public static String timestamp2yyymmdd(long timestamp){
        Date tmp = long2Date(timestamp);
        SimpleDateFormat sdf = new SimpleDateFormat(yyyyMMdd_EN);
        return sdf.format(tmp);
    }

    /**
     * unix timestamp long format to yyyymmdd long
     * @param timestamp
     * @return
     */
    public static long timestamp2yyymmddL(long timestamp){
        return Long.parseLong(timestamp2yyymmdd(timestamp));
    }

    /**
     * get last month firtst day  0:0
     * @return java.util.Date
     */
    public static Date getLastMonthFirstDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        return calendar.getTime();
    }

    /**
     * get last month first day 0:0
     * @return timestamp
     */
    public static long getLastMonthFirstDayTimestamp(){
        return getLastMonthFirstDay().getTime()/1000;
    }


    /**
     * get last month same day
     * @return
     */
    public static Date getLastMonthSameDay(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        return calendar.getTime();
    }
    public static long getLastMonthSameDayTS(){
        return getLastMonthSameDay().getTime()/1000;
    }

    /**
     * get last month same day before one day
     * @return
     */
    public static Date getLastMonthSameDayBeforOneDay(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.add(Calendar.DAY_OF_MONTH,-1);
        return calendar.getTime();
    }
    public static long getLastMonthSameDayBeforOneDayTS(){
        return getLastMonthSameDayBeforOneDay().getTime()/1000;
    }

    /**
     * get the day before yesterday
     * @return
     */
    public static Date getTheDayBeforeYesterday(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -2);
        return calendar.getTime();
    }

    /**
     *
     * @return
     */
    public static String  getTheDayBeforeYesterdayHumanRead(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -2);
        Date tmp = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat( yyyy_MM_dd_HH_mm_CN);
        return sdf.format(tmp);
    }

    /**
     * get the day before yesterday timestamp
     * @return
     */
    public static long getTheDayBeforeYesterdayTS(){
        return getTheDayBeforeYesterday().getTime()/1000;
    }

    /**
     * get yesterday moon
     * @return Date
     */
    public static Date getYesterdayMoon() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }
    public static long getYesterdayMoonTS(){
        return getYesterdayMoon().getTime()/1000;
    }

    /**
     * get yesterday night 23:59:59
     * @return
     */
    public static Date getYesterdayNight0() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }
    public static long getYesterdayNight0TS(){
        return getYesterdayNight0().getTime()/1000;
    }

    /**
     * long to yyyy 年MM月dd日HH时mm分ss秒
     * @param date
     * @return
     */
    public static String formatCnYMDHMS(long date){
        SimpleDateFormat sdf = new SimpleDateFormat(yyyy_MM_dd_HH_mm_ss_CN);
        return sdf.format(long2Date(date));
    }


    public static void main(String[] args) {
//        System.out.println(timestamp2yyymmdd(new Date().getTime()/1000));
//        System.out.println(timestamp2yyymmdd( 1445334700 ));
//        System.out.println(timestamp2yyymmddL( 1403085079 ));
//        long ts = date2Timestamp(getLastMonthFirstDay());
        System.out.println(getYesterdayMoon());
        System.out.println(getTheDayBeforeYesterday());
        System.out.println(getYesterdayMoonTS());
        System.out.println(getYesterdayNight0());
        System.out.println(getYesterdayNight0TS());
    }
}
