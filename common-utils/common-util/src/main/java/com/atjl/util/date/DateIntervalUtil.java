package com.atjl.util.date;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by async on 2017/8/13.
 */
public class DateIntervalUtil {
	private DateIntervalUtil(){
		throw new UnsupportedOperationException();
	}
	/**
	 * 校验相关
	 */
	public static final String TM_UNIT_YEAR = "Y";
	public static final String TM_UNIT_MONTH = "M";
	public static final String TM_UNIT_DAY = "D";
	public static final String TM_UNIT_HOUR = "H";
	public static final String TM_UNIT_MINUTE = "m";
	public static final String TM_UNIT_SECOND = "S";
	public static final Set<String> UNIT_SET = new HashSet<>();
	static {
		UNIT_SET.add(TM_UNIT_YEAR);
		UNIT_SET.add(TM_UNIT_MONTH);
		UNIT_SET.add(TM_UNIT_DAY);
		UNIT_SET.add(TM_UNIT_HOUR);
		UNIT_SET.add(TM_UNIT_MINUTE);
		UNIT_SET.add(TM_UNIT_SECOND);
	}
	
	/**
	 * is valid unit
	 *
	 * @param u
	 * @return
	 */
	public static boolean validUnit(String u) {
		return UNIT_SET.contains(u);
	}
	
	/**
	 * get
	 *
	 * @param d
	 * @param diff
	 * @return
	 */
	public static Date getIntervalDate(Date d, String unit, int diff) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		switch (unit) {
			case TM_UNIT_YEAR:
				calendar.add(Calendar.YEAR, diff);
				break;
			case TM_UNIT_MONTH:
				calendar.add(Calendar.MONTH, diff);
				break;
			case TM_UNIT_DAY:
				calendar.add(Calendar.DAY_OF_MONTH, diff);
				break;
			case TM_UNIT_HOUR:
				calendar.add(Calendar.HOUR, diff);
				break;
			case TM_UNIT_MINUTE:
				calendar.add(Calendar.HOUR, diff);
				break;
			case TM_UNIT_SECOND:
				calendar.add(Calendar.SECOND, diff);
				break;
		}
		return calendar.getTime();
	}
	
	/**
	 * get interval second
	 * 注：内部转为 timestamp,date不能小于1970
	 * @param start
	 * @param unit
	 * @param diff
	 * @return
	 */
	public static Long getIntervalSec(Long start, String unit, int diff) {
		Date startDate = DateUtil.ts2Date(start);
		Date tgtDate = getIntervalDate(startDate, unit, diff);
		return getIntervalSec(startDate, tgtDate);
	}
	
	public static Long getInterval(Date start, Date end, TimeUnit tu) {
		//start.() ;
		return DateUtil.date2ts(end) - DateUtil.date2ts(start);
	}
	
	/**
	 * get interval second
	 * 注：内部转为 timestamp,date不能小于1970
	 * @param start
	 * @param end
	 * @return
	 */
	public static Long getIntervalSec(Date start, Date end) {
		return DateUtil.date2ts(end) - DateUtil.date2ts(start);
	}
	
	/**
	 * 转为timestamp，date不能小于1970
	 * @param start
	 * @param end
	 * @return
	 */
	public static Long getIntervalMs(Date start,Date end){
		return end.getTime() - start.getTime();
	}
}
