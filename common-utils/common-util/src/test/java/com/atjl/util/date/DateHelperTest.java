package com.atjl.util.date;

import com.atjl.util.date.DateUtil;
import org.junit.Test;

import java.util.Date;

public class DateHelperTest {

    /**
     * Method: getNowLastNTS(Integer n)
     */
    @Test
    public void testGetNowLastNTS() throws Exception {
        Date d = DateUtil.getDate(0);
        System.out.println(d);
        d = DateUtil.getDate(-1);
        System.out.println(d);
        d = DateUtil.getDate(1);
        System.out.println(d);

    }

    /**
     * Method: getTimeSeconds()
     */
    @Test
    public void testGetTimeSeconds() throws Exception {

    }

    /**
     * Method: getNowTS()
     */
    @Test
    public void testGetNowTS() throws Exception {

    }

    /**
     * Method: long2Date(long ltime)
     */
    @Test
    public void testLong2Date() throws Exception {

    }

    /**
     * Method: date2ts(Date date)
     */
    @Test
    public void testDate2Timestamp() throws Exception {

    }

    /**
     * Method: ts2yyymmdd(long timestamp)
     */
    @Test
    public void testTimestamp2yyymmdd() throws Exception {

    }

    /**
     * Method: ts2yyymmddL(long timestamp)
     */
    @Test
    public void testTimestamp2yyymmddL() throws Exception {

    }

    /**
     * Method: getDate(int day)
     */
    @Test
    public void testGetDate() throws Exception {

    }

    /**
     * Method: getDateTS(int day)
     */
    @Test
    public void testGetDateTs() throws Exception {

    }

    /**
     * Method: getLastMonthFirstDay()
     */
    @Test
    public void testGetLastMonthFirstDay() throws Exception {

    }

    /**
     * Method: getLastMonthFirstDayTimestamp()
     */
    @Test
    public void testGetLastMonthFirstDayTimestamp() throws Exception {

    }

    /**
     * Method: getLastMonthSameDay()
     */
    @Test
    public void testGetLastMonthSameDay() throws Exception {

    }

    /**
     * Method: getLastMonthSameDayTS()
     */
    @Test
    public void testGetLastMonthSameDayTS() throws Exception {

    }

    /**
     * Method: getLastMonthSameDayBeforOneDay()
     */
    @Test
    public void testGetLastMonthSameDayBeforOneDay() throws Exception {

    }

    /**
     * Method: getLastMonthSameDayBeforOneDayTS()
     */
    @Test
    public void testGetLastMonthSameDayBeforOneDayTS() throws Exception {

    }

    /**
     * Method: getTheDayBeforeYesterday()
     */
    @Test
    public void testGetTheDayBeforeYesterday() throws Exception {

    }

    /**
     * Method: getTheDayBeforeYesterdayHumanRead()
     */
    @Test
    public void testGetTheDayBeforeYesterdayHumanRead() throws Exception {

    }

    /**
     * Method: getTheDayBeforeYesterdayTS()
     */
    @Test
    public void testGetTheDayBeforeYesterdayTS() throws Exception {

    }

    /**
     * Method: getYesterdayMoon()
     */
    @Test
    public void testGetYesterdayMoon() throws Exception {

    }

    /**
     * Method: getYesterdayMoonTS()
     */
    @Test
    public void testGetYesterdayMoonTS() throws Exception {

    }

    /**
     * Method: getYesterdayNight0()
     */
    @Test
    public void testGetYesterdayNight0() throws Exception {

    }

    /**
     * Method: getYesterdayNight0TS()
     */
    @Test
    public void testGetYesterdayNight0TS() throws Exception {

    }

    /**
     * Method: formatCnYMDHMS(long date)
     */
    @Test
    public void testFormatCnYMDHMS() throws Exception {

    }

    /**
     * Method: strToDateTime(String year, String month, String day, String hour, String minute, String
     * second, String timezone)
     */
    @Test
    public void testStrToDateTime() throws Exception {

    }

    /**
     * Method: getGMT2East8TimeZone(String year, String month, String day, String hour, String minute, String second, String timezone)
     */
    @Test
    public void testGetGMT2East8TimeZoneForYearMonthDayHourMinuteSecondTimezone() throws Exception {

    }

    /**
     * Method: getGMT2East8TimeZone(String sDate, String sTime, String sTimezone)
     */
    @Test
    public void testGetGMT2East8TimeZoneForSDateSTimeSTimezone() throws Exception {

    }

    /**
     * Method: getDate2Str(String date)
     */
    @Test
    public void testGetDate2Str() throws Exception {

    }

    /**
     * Method: main(String[] args)
     */
    @Test
    public void testMain() throws Exception {

    }



    @Test
    public void testSelfAdpation()
    {
        long time = 1478855477734l;
        Date d = DateUtil.long2DateSelfAdaption(time);
        System.out.println(d);
    }
}
