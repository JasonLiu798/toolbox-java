package com.atjl.util.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * date util
 *
 * @author JasonLiu798
 * @date 2014/2/6 11:41
 */
public class DateUtil {
	private DateUtil(){
		throw new UnsupportedOperationException();
	}

    private static Logger log = LoggerFactory.getLogger(DateUtil.class);

    public static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String yyyy_EN = "yyyy";
    public static final String yyyy_MM_dd_EN = "yyyy-MM-dd";
    public static final String yyyy_MM_dd_HH_mm_EN = "yyyy-MM-dd HH:mm";
    public static final String yyyyMMdd_EN = "yyyyMMdd";
    public static final String yyyy_MM_EN = "yyyy-MM";
    public static final String yyyyMM_EN = "yyyyMM";
    public static final String yyyy_MM_dd_HH_mm_ss_EN = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss sss";
    public static final String YYYYMMDDHHMMSSSSS = "yyyyMMddHHmmsssss";
    public static final String yyyyMMddHHmmss_EN = "yyyyMMddHHmmss";
    public static final String yyyy_MM_dd_CN = "yyyy年MM月dd日";
    public static final String yyyy_MM_dd_HH_mm_ss_CN = "yyyy年MM月dd日HH时mm分ss秒";
    public static final String yyyy_MM_dd_HH_mm_CN = "yyyy年MM月dd日HH时mm分";
    public static final String GTM_DATE_FROM = "EEE, d MMM yyyy HH:mm:ss 'GMT'";
	

	
	/**
	 * ########################## unix timestamp #########################
	 */
	/**
	 * get now timestamp
	 * format:long
	 *
	 * @return
	 */
	public static long getNowTS() {
		return new Date().getTime() / 1000;
	}
	
	public static Date ts2Date(long ts) {
		return new Date(ts * 1000);
	}
	
	public static Date tsms2Date(long ts) {
		return new Date(ts);
	}
	/**
	 * date format to unix timestamp
	 *
	 * @param date
	 * @return
	 */
	public static long date2ts(Date date) {
		return date.getTime() / 1000;
	}
	
	/**
	 * self adaption of ltime's length
	 *
	 * @param ltime
	 * @return
	 */
	public static Date long2DateSelfAdaption(long ltime) {
		//1478855477
		//9999999999
		if (ltime > 9999999999L) {
			//ms
			return new Date(ltime);
		} else {
			//second
			return new Date(ltime * 1000);
		}
	}
	
	/**
	 * unix timestamp long format to yyyymmdd long
	 *
	 * @param timestamp
	 * @return
	 */
	public static long ts2yyymmddL(long timestamp) {
		return Long.parseLong(ts2yyymmdd(timestamp));
	}
	/**
	 * unix timestamp long format to yyyymmdd String
	 */
	public static String ts2yyymmdd(long timestamp) {
		Date tmp = ts2Date(timestamp);
		SimpleDateFormat sdf = new SimpleDateFormat(yyyyMMdd_EN);
		return sdf.format(tmp);
	}
	
	
	
	/**
	 * 获取秒
	 * @return
	 */
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
     * ##################### formats ##################################
     */
    /**
     * @param date
     * @param fmt
     * @return
     */
    public static String format(Date date, String fmt) {
        SimpleDateFormat sdf = new SimpleDateFormat(fmt);
        return sdf.format(date);
    }

    public static String formatYYYYMMDD(Date date) {
        return format(date, yyyyMMdd_EN);
    }

    public static String formatDefault(Date date) {
        return format(date, DEFAULT_FORMAT);
    }
	
	
	/**
	 * ########################## target time ###############################
	 */
    /**
     * get date
     *
     * @param day -xxx ~ xxx
     * @return target date
     * ...
     * 1 = tomorrow
     * 0 = now date
     * -1 = yesterday
     * -2 = the day before yesterday
     * ...
     */
    public static Date getDate(int day) {
        Calendar cd = Calendar.getInstance();
        cd.add(Calendar.DATE, day);
        Date dat = cd.getTime();
        return dat;
    }

    public static long getDateTS(int day) {
        return date2ts(getDate(day));
    }

    public static Date getDateZero(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, day);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date dat = calendar.getTime();
        return dat;
    }

    public static long getDateZeroTS(int day) {
        return date2ts(getDateZero(day));
    }

    public static Date getDate12(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, day);
        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public static long getDate12TS(int day) {
        return date2ts(getDate12(day));
    }

    /**
     * get last month firtst day  0:0
     *
     * @return java.util.Date
     */
    public static Date getLastMonthFirstDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * get last month first day 0:0
     *
     * @return timestamp
     */
    public static long getLastMonthFirstDayTimestamp() {
        return getLastMonthFirstDay().getTime() / 1000;
    }

    /**
     * get last month same day
     *
     * @return
     */
    public static Date getLastMonthSameDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        return calendar.getTime();
    }

    public static long getLastMonthSameDayTS() {
        return getLastMonthSameDay().getTime() / 1000;
    }

    /**
     * get last month same day before one day
     *
     * @return
     */
    public static Date getLastMonthSameDayBeforOneDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return calendar.getTime();
    }

    public static long getLastMonthSameDayBeforOneDayTS() {
        return getLastMonthSameDayBeforOneDay().getTime() / 1000;
    }

    public final static Date strToDateTime(String year, String month, String day, String hour, String minute, String
            second, String timezone) {
        Calendar calendar = new GregorianCalendar();
        SimpleDateFormat formatymdhms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        StringBuilder sb = new StringBuilder();
        sb.append(year);
        sb.append("-");
        sb.append(month);
        sb.append("-");
        sb.append(day);
        sb.append(" ");
        sb.append(hour);
        sb.append(":");
        sb.append(minute);
        sb.append(":");
        sb.append(second);
//		formatymdhms.setTimeZone(TimeZone.getTimeZone(timezone));
        try {
            date = formatymdhms.parse(sb.toString());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        calendar.setTimeZone(TimeZone.getTimeZone(timezone));
        calendar.setTime(date);
//		calendar.add(Calendar.HOUR_OF_DAY, 8);
        return calendar.getTime();
    }

    public final static Date getGMT2East8TimeZone(String year, String month, String day, String hour, String minute,
                                                  String second, String timezone) {
        Calendar calendar = new GregorianCalendar();
        SimpleDateFormat formatymdhms = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        StringBuilder sb = new StringBuilder();
        sb.append(year);
        sb.append("-");
        sb.append(month);
        sb.append("-");
        sb.append(day);
        sb.append(" ");
        sb.append(hour);
        sb.append(":");
        sb.append(minute);
        sb.append(":");
        sb.append(second);

//		formatymdhms.setTimeZone(TimeZone.getTimeZone(timezone));
        try {
            date = formatymdhms.parse(sb.toString());
        } catch (ParseException e) {
            log.debug("SimpleDateFormat error");
            return null;
        }
        calendar.setTimeZone(TimeZone.getTimeZone(timezone));
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, 8);
        return calendar.getTime();
    }

    public final static Date getGMT2East8TimeZone(String sDate, String sTime, String sTimezone) {
        SimpleDateFormat formatymdhms = new SimpleDateFormat("ddMMyyHHmmss");
        Date date = null;
        try {
            date = formatymdhms.parse(sDate + sTime);
        } catch (ParseException e) {
            log.debug("SimpleDateFormat");
            return null;
        }
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeZone(TimeZone.getTimeZone(sTimezone));
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, 8);
        return calendar.getTime();
    }

    public static String getDate2Str(String date) throws Exception {
        SimpleDateFormat formatymdhms = new SimpleDateFormat("yyyyMMddHHmmss");
        return formatymdhms.format(date);
    }






    /**
     * 获取月份，返回秒数
     *
     * @param date
     * @param monthAdd
     * @return
     */
    public static Long getMonth(Long date, int monthAdd) {
        Date now = new Date(date * 1000);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(now);
        calendar.add(Calendar.MONTH, monthAdd);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis() / 1000;
    }

    /**
     * 获取月份，返回秒数
     *
     * @param date
     * @return
     */
    public static Long getMonth(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis() / 1000;
    }

    /**
     * 获取年，返回秒数
     *
     * @param date
     * @return
     */
    public static Long getYear(Long date) {
        Date now = new Date(date * 1000);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(now);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis() / 1000;
    }

    /**
     * 获取年，返回秒数
     *
     * @param date
     * @return
     */
    public static Long getYear(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis() / 1000;
    }

    /**
     * 获取明年，返回秒数
     *
     * @param date
     * @return
     */
    public static Long getNextYear(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, 1);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis() / 1000;
    }

    /**
     * 获取去年，返回秒数
     *
     * @param date
     * @return
     */
    public static Long getLastYear(Long date) {
        Date now = new Date(date * 1000);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(now);
        calendar.add(Calendar.YEAR, -1);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis() / 1000;
    }

    /**
     * 获取次年，返回秒数
     *
     * @param date
     * @return
     */
    public static Long getNextYear(Long date) {
        Date now = new Date(date * 1000);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(now);
        calendar.add(Calendar.YEAR, 1);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis() / 1000;
    }

    /**
     * ms to formatted string
     * 0~60, xx s
     * 0~60min, xx m
     * >60min, xx h
     *
     * @param mslong
     * @return
     */
    public static String msFmt(Long mslong) {
        String delayStr = "";
        if (mslong != null) {
            long sec = mslong / 1000;
            if (sec < 60) {//0~60
                delayStr = sec + "s";
                return delayStr;
            }
            long min = sec / 60;
            long secMod = sec % 60;
            if (min < 60) {// 60*60S
                delayStr = min + "m" + secMod + "s";
                return delayStr;
            }
            long hour = min / 60;
            long hourMod = min % 60;
            delayStr = hour + "h" + hourMod + "m" + secMod + "s";
            return delayStr;
        }
        return delayStr;
    }

}
