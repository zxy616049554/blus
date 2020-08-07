
package com.rk.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;

import org.apache.commons.lang.time.DateUtils;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 * 
 * @author
 * @version 2018-5-15
 */
public class DateUtil extends DateUtils {

	private static String[] parsePatterns = { "yyyyMMdd", "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
			"yyyy-MM", "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM", "yyyy.MM.dd",
			"yyyy.MM.dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss.S", "yyyy.MM.dd HH:mm", "yyyy.MM", "HH:mm:ss" };

	/** 默认日期格式 yyyy-MM-dd */
	public static String FORMAT_DATE_DEFAULT = "yyyy-MM-dd";
	/** 日期格式 yyyyMMddhhmmss */
	public static String FORMAT_DATE_DATE_TIME = "yyyyMMddHHmmss";
	/** 日期格式 yyyy-MM-dd HH:mm:ss */
	public static String FORMAT_HOUR_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
	/** 日期格式 yyyyMMddhhmmssSSS */
	public static String FORMAT_MILLIS_SECOND_TIME = "yyyyMMddHHmmssSSS";
	/** 日期格式 yyyyMMdd */
	public static String FORMAT_DATE_DATE = "yyyyMMdd";
	/** 时间格式 HH:mm:ss */
	public static String FORMAT_DATE_TIME = "HH:mm:ss";
	/** 时间格式 HH:mm */
	public static String FORMAT_DATE_HOUR = "HH:mm";
	/** 时间格式 HHmmss */
	public static String FORMAT_TIME = "HHmmss";
	/** 当前月格式 yyyy-MM */
	public static String FORMAT_CURRENT_MONTH = "yyyy-MM";
	public static String Format_current_time = "yyyy-MM-dd HH:mm:ss.S";
	/** 精度到年，例如在时间比较 */
	public static final int ACCURACY_YEAR = 6;
	/** 精度到月 */
	public static final int ACCURACY_MONTH = 5;
	/** 精度到天 */
	public static final int ACCURACY_DAY = 4;
	/** 精度到时 */
	public static final int ACCURACY_HH = 3;
	/** 精度到分 */
	public static final int ACCURACY_MM = 2;
	/** 精度到秒 */
	public static final int ACCURACY_SS = 1;
	/** 自定义错误代码 */
	private static final long ERROR_CODE = 999999996L;

	/**
	 * 时间戳转换成日期格式字符串
	 * 
	 * @param seconds   精确到秒的字符串
	 * @param formatStr
	 * @return
	 */
	public static String timeStamp2Date(String seconds, String format) {
		if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
			return "";
		}
		if (format == null || format.isEmpty()) {
			format = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(new Date(Long.valueOf(seconds + "000")));
	}

	/**
	 * 获得该月第一天
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static String getFirstDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		// 设置年份
		cal.set(Calendar.YEAR, year);
		// 设置月份
		cal.set(Calendar.MONTH, month - 1);
		// 获取某月最小天数
		int firstDay = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
		// 设置日历中月份的最小天数
		cal.set(Calendar.DAY_OF_MONTH, firstDay);
		// 格式化日期
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE_DEFAULT);
		String firstDayOfMonth = sdf.format(cal.getTime());
		return firstDayOfMonth;
	}

	/**
	 * 获得该月最后一天
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static String getLastDayOfMonth(int year, int month) {
		Calendar cal = Calendar.getInstance();
		// 设置年份
		cal.set(Calendar.YEAR, year);
		// 设置月份
		cal.set(Calendar.MONTH, month - 1);
		// 获取某月最大天数
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		// 设置日历中月份的最大天数
		cal.set(Calendar.DAY_OF_MONTH, lastDay);
		// 格式化日期
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE_DEFAULT);
		String lastDayOfMonth = sdf.format(cal.getTime());
		return lastDayOfMonth;
	}

	/**
	 * 获取与当天日期比相隔天数的日期
	 * 
	 * @param pattern
	 * @param days    天数 向前- 向后+
	 * @return 与当天日期比相隔天数的日期
	 */
	public static String getDatebyDay(Date date, String pattern, int days) {
		date.setTime(date.getTime() + 24 * 60 * 60 * 1000 * days);
		return getDateStr(date, pattern);
	}

	/**
	 * 获取与当天日期比相隔天数的日期
	 * 
	 * @param date
	 * @param pattern
	 * @param days
	 * @return
	 */
	public static String getDatebyDays(Date date, String pattern, int days) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, -days);
		return getDateStr(c.getTime(), pattern);
	}

	/**
	 * 获得指定格式的 当前日期 字符串
	 * 
	 * @return 获得指定格式的 当前日期 字符串
	 */
	public static String getDateCurrent(String pattern) {
		return getDateStr(new Date(), pattern);
	}

	/**
	 * 获得默认格式的当前日期 格式（yyyy-MM-dd）
	 * 
	 * @return 获得默认格式的当前日期
	 */
	public static String getDateCurrent() {
		return getDateCurrent(DateUtil.FORMAT_DATE_DEFAULT);
	}

	/**
	 * 根据指定日期和格式 返回对应的时间字符串
	 *
	 * @param date    日期变量
	 * @param pattern 日期格式
	 * @return 日期时间字符串
	 */
	public static String getDateStr(Date date, String pattern) {
		DateFormat df = new SimpleDateFormat(pattern);
		return df.format(date);
	}

	/**
	 * 获取指定日期的 默认格式 字符串， 格式：yyyy-MM-dd
	 * 
	 * @return 字符串
	 */
	public static String getDateStr(Date date) {
		return getDateStr(date, DateUtil.FORMAT_DATE_DEFAULT);
	}

	/**
	 * 以默认格式返回当前日期， 格式：yyyy-MM-dd
	 * 
	 * @return Date
	 */
	public static Date getCurrentStr() {
		return parseDate(getDateStr(new Date(), DateUtil.FORMAT_DATE_DEFAULT));
	}

	/**
	 * 以默认格式返回当前日期的前一天， 格式：yyyy-MM-dd
	 * 
	 * @return Date
	 */
	public static Date getBeforeStr() {
		Calendar calendar = Calendar.getInstance();
		// 得到前一天
		calendar.add(Calendar.DATE, -1);
		Date date = calendar.getTime();
		return parseDate(getDateStr(date, DateUtil.FORMAT_DATE_DEFAULT));
	}

	/**
	 * 获取 符合指定格式的当前日期，格式：yyyy-MM-dd HH:mm:ss
	 * 
	 * @return Date
	 */
	public static Date getCurrentDate() {
		return parseDate(getDateStr(new Date(), DateUtil.FORMAT_HOUR_DATE_TIME));
	}

	/**
	 * 获取 按指定格式返回 传入的日期
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static Date getDateByPattern(Date date, String pattern) {
		return parseDate(getDateStr(date, pattern));
	}

	/**
	 * 获取当前月 字符串 ，格式：yyyy-MM
	 * 
	 * @return
	 */
	public static String getCurrentMonthStr() {
		return getDateStr(new Date(), DateUtil.FORMAT_CURRENT_MONTH);
	}

	/**
	 * 字符串转换成时间格式 <br/>
	 * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy/MM/dd",
	 * "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy.MM.dd", "yyyy.MM.dd
	 * HH:mm:ss", "yyyy.MM.dd HH:mm" }
	 * 
	 * @param cStr 待转换的字符串
	 * @return 转换后的日期
	 */
	public static Date parseDate(String cStr) {
		if (cStr == null) {
			return null;
		}
		try {
			return parseDate(cStr, parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 *
	 * 比较两个日期,以天为基本单位:<br/>
	 * 如果第一个日期早于第二个日期，返回-1;<br/>
	 * 如果第一个日期等于第二个日期，返回0;<br/>
	 * 如果第一个日期晚于第二个日期，返回1
	 * 
	 * @param a 第一个日期
	 * @param b 第二个日期
	 * @return 比较结果，如果参数不对或缺少，返回9
	 */
	public static int compare(Date a, Date b) {
		return compare(a, b, DateUtil.ACCURACY_DAY);
	}

	/**
	 *
	 * 比较两个日期:<br/>
	 * 如果第一个日期早于第二个日期，返回-1;<br/>
	 * 如果第一个日期等于第二个日期，返回0;<br/>
	 * 如果第一个日期晚于第二个日期，返回1
	 * 
	 * @param a        第一个日期
	 * @param
	 * @param accuracy 精度DateUtil.ACCURACY_YEAR,DateUtil.ACCURACY_DAY....
	 * @return 比较结果，如果参数不对或缺少，返回9
	 */
	public static int compare(Date a, Date b, int accuracy) {
		long distance = getDateDistancePrivate(a, b, accuracy);
		if (distance < 0) {
			return -1;
		} else if (distance > 0 && distance != ERROR_CODE) {
			return 1;
		} else if (distance == 0) {
			return 0;
		} else {
			return 9;
		}
	}

	/**
	 * 通过起始日期和终止日期计算以时间间隔单位为计量标准的时间间隔，舍弃法 author: HST 起始日期，(String,格式："YYYY-MM-DD")
	 * 
	 * @param startDate 终止日期，(String,格式："YYYY-MM-DD")
	 * @param endDate   时间间隔单位，可用值("Y"--年 "M"--月 "D"--日)
	 * @param unit      String 时间间隔,整形变量int
	 * @return int
	 */
	public static int calInterval(Date startDate, Date endDate, String unit) {
		int interval = 0;

		GregorianCalendar sCalendar = new GregorianCalendar();
		sCalendar.setTime(startDate);
		int sYears = sCalendar.get(Calendar.YEAR);
		int sMonths = sCalendar.get(Calendar.MONTH);
		int sDays = sCalendar.get(Calendar.DAY_OF_MONTH);

		GregorianCalendar eCalendar = new GregorianCalendar();
		eCalendar.setTime(endDate);
		int eYears = eCalendar.get(Calendar.YEAR);
		int eMonths = eCalendar.get(Calendar.MONTH);
		int eDays = eCalendar.get(Calendar.DAY_OF_MONTH);

		if (unit.equals("Y")) {
			interval = eYears - sYears;

			if (eMonths < sMonths) {
				interval--;
			} else {
				if (eMonths == sMonths && eDays < sDays) {
					interval--;
					if (eMonths == 1) { // 如果同是2月，校验润年问题
						if ((sYears % 4) == 0 && (eYears % 4) != 0) { // 如果起始年是润年，终止年不是润年
							if (eDays == 28) { // 如果终止年不是润年，且2月的最后一天28日，那么补一
								interval++;
							}
						}
					}
				}
			}
		}
		if (unit.equals("M")) {
			interval = eYears - sYears;
			// interval = interval * 12;
			interval *= 12;
			// interval = eMonths - sMonths + interval;
			interval += eMonths - sMonths;

			if (eDays < sDays) {
				interval--;
				// eDays如果是月末，则认为是满一个月
				int maxDate = eCalendar.getActualMaximum(Calendar.DATE);
				if (eDays == maxDate) {
					interval++;
				}
			}
		}
		if (unit.equals("D")) {
			sCalendar.set(sYears, sMonths, sDays);
			eCalendar.set(eYears, eMonths, eDays);
			long lInterval = (eCalendar.getTime().getTime() - sCalendar.getTime().getTime()) / 86400000;
			interval = (int) lInterval;
		}
		return interval;
	}

	/**
	 * 判断时间是否在时间段内 传入的3个时间参数需要采用相同的格式，例：HH:mm:ss 如果 开始时间为空 则仅判断结束时间，结束时间为空 则仅判断开始时间
	 * 
	 * @param nowTime
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	public static boolean belongCalendar(Date nowTime, Date beginTime, Date endTime) {
		Calendar date = Calendar.getInstance();
		date.setTime(nowTime);

		Calendar begin = Calendar.getInstance();
		if (beginTime != null) {
			begin.setTime(beginTime);
		}

		Calendar end = Calendar.getInstance();
		if (endTime != null) {
			end.setTime(endTime);
		}

		if (beginTime != null && endTime != null && date.after(begin) && date.before(end)) {
			return true;
		}
		if (beginTime == null && endTime != null && date.before(end)) {
			return true;
		}
		if (endTime == null && beginTime != null && date.after(begin)) {
			return true;
		}
		return false;
	}

	/*
	 * 计算两个时间差，只为整数，第三个参数是精度。支持时,分,秒,天。不支持年和月<br/>
	 * 
	 * @param a 第一个时间
	 * 
	 * @param b 第二个时间
	 * 
	 * @param accuracy 时间精度
	 * 
	 * @return 时间差，当缺少参数时，返回999999996
	 */
	private static long getDateDistancePrivate(Date a, Date b, int accuracy) {
		if (a == null || b == null) {
			return ERROR_CODE;
		}
		long d1 = a.getTime();// 第一个时间
		long d2 = b.getTime();// 第二个时间
		long distance = d2 - d1;// 时间差
		switch (accuracy) {

		case DateUtil.ACCURACY_DAY:
			distance = distance / 86400000;
			break;
		case DateUtil.ACCURACY_HH:
			distance = distance / 3600000;
			break;
		case DateUtil.ACCURACY_MM:
			distance = distance / 60000;
			break;
		case DateUtil.ACCURACY_SS:
			distance = distance / 1000;
			break;
		default:
			distance = -1;
			break;
		}
		return distance;
	}

	/**
	 *
	 * 根据生日获取年龄,字符格式的日期
	 * 
	 * @param birthDay 出生日期
	 * @return 返回年龄，传入时间大于当前时间，返回-1
	 */
	public static int getAge(String birthDay) {
		Date date = parseDate(birthDay);
		return getAge(date);
	}

	/**
	 *
	 * 根据生日获取年龄
	 * 
	 * @param birthDay 出生日期
	 * @return 返回年龄，传入时间大于当前时间，返回-1
	 */
	public static int getAge(Date birthDay) {
		int age = 0;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(birthDay);
		Calendar now = Calendar.getInstance();
		if (calendar.after(now)) {
			return -1;
		}
		age = now.get(Calendar.YEAR) - calendar.get(Calendar.YEAR);
		if (calendar.get(Calendar.MONTH) > now.get(Calendar.MONTH)) {
			age--;
		} else if (calendar.get(Calendar.MONTH) == now.get(Calendar.MONTH)
				&& calendar.get(Calendar.DAY_OF_MONTH) > now.get(Calendar.DAY_OF_MONTH)) {
			age--;
		}
		return age;
	}

	/**
	 *
	 * 传入当前日期,返回当前时分秒
	 * 
	 * @param date
	 * @return
	 */
	public static String getTime(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE_TIME);
		return sdf.format(date);
	}

	/**
	 * 获取以"-"分隔日期值的中文表示
	 * 
	 * @param strDate String 日期
	 * @return String YYYY年MM月DD日
	 */
	public static String getChnDate(String strDate) {
		String strReturn = getVisaYear(strDate) + "年" + getVisaMonth(strDate) + "月" + getVisaDay(strDate) + "日";
		return strReturn;
	}

	/**
	 * 获取以"-"分隔日期值中的年份
	 * 
	 * @param strDate String 日期
	 * @return String 年
	 */
	public static String getVisaYear(String strDate) {
		int intPosition = 0;
		String strReturn = "";
		int intYear = 0;

		if ((strDate != null) && (strDate.trim().length() > 0)) {
			intPosition = StrTool.getPos(strDate, StrTool.VISADATEDELIMITER, 1);
			if (intPosition > 0) {
				strReturn = strDate.substring(0, intPosition);
				intYear = new Integer(strReturn).intValue();
				if ((intYear <= 0)) {
					strReturn = "";
				} else {
					strReturn = "" + intYear;
				}

				if ((intYear < 10) && (!strReturn.equals(""))) {
					strReturn = "0" + strReturn;
				}
			}
		}
		return strReturn;
	}

	/**
	 * 获取以"-"分隔日期值中的月份
	 * 
	 * @param strDate String 日期
	 * @return String 月
	 */
	public static String getVisaMonth(String strDate) {
		int intPosition1 = 0, intPosition2 = 0;
		String strReturn = "";
		int intMonth = 0;
		if ((strDate != null) && (strDate.trim().length() > 0)) {
			intPosition1 = StrTool.getPos(strDate, StrTool.VISADATEDELIMITER, 1);
			intPosition2 = StrTool.getPos(strDate, StrTool.VISADATEDELIMITER, 2);
			if ((intPosition1 > 0) && intPosition2 > intPosition1) {

				strReturn = strDate.substring(intPosition1 + 1, intPosition2);

				intMonth = new Integer(strReturn).intValue();
				if ((intMonth <= 0) || (intMonth > 12)) {
					strReturn = "";
				} else {
					strReturn = "" + intMonth;
				}

				if ((intMonth < 10) && (!strReturn.equals(""))) {
					strReturn = "0" + strReturn;
				}
			}
		}
		return strReturn;
	}

	/**
	 * 获取以"-"分隔日期值中的天
	 * 
	 * @param strDate String 日期
	 * @return String 天
	 */
	public static String getVisaDay(String strDate) {
		int intPosition = 0;
		String strReturn = "";
		int intDay = 0;
		if ((strDate != null) && (strDate.trim().length() > 0)) {
			intPosition = StrTool.getPos(strDate, StrTool.VISADATEDELIMITER, 2);
			if (intPosition > 0) {

				strReturn = strDate.substring(intPosition + 1);

				intDay = new Integer(strReturn).intValue();

				if ((intDay <= 0) || (intDay > 31)) {
					strReturn = "";
				} else {
					strReturn = "" + intDay;
				}

				if ((intDay < 10) && (!strReturn.equals(""))) {
					strReturn = "0" + strReturn;
				}
			}
		}
		return strReturn;
	}

	/***
	 *
	 * 传入date返回当前时间 "yyyy-MM-dd HH:mm:ss";
	 * 
	 * @param date
	 * @return
	 */
	public static String getYmdhms(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_HOUR_DATE_TIME);
		return sdf.format(date);
	}

	/***
	 *
	 * 传入String返回date;
	 * 
	 * @param date
	 * @return
	 */
	public static Date getDateTime(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE_DATE_TIME);
		Date time = null;
		try {
			time = sdf.parse(date);
		} catch (ParseException e) {
			return null;
		}
		return time;
	}

	public static Date calDate(Date baseDate, int interval, String unit, Date compareDate) {
		Date returnDate = null;

		GregorianCalendar tBaseCalendar = new GregorianCalendar();
		tBaseCalendar.setTime(baseDate);

		if (unit.equals("Y")) {
			tBaseCalendar.add(Calendar.YEAR, interval);
		}
		if (unit.equals("M")) {
			tBaseCalendar.add(Calendar.MONTH, interval);
		}
		if (unit.equals("D")) {
			tBaseCalendar.add(Calendar.DATE, interval);
		}

		if (compareDate != null) {
			GregorianCalendar tCompCalendar = new GregorianCalendar();
			tCompCalendar.setTime(compareDate);
			int nBaseYears = tBaseCalendar.get(Calendar.YEAR);
			int nBaseMonths = tBaseCalendar.get(Calendar.MONTH);
			int nCompMonths = tCompCalendar.get(Calendar.MONTH);
			int nCompDays = tCompCalendar.get(Calendar.DATE);

			if (unit.equals("Y")) {
				tCompCalendar.set(nBaseYears, nCompMonths, nCompDays);
				if (tCompCalendar.before(tBaseCalendar)) {
					tBaseCalendar.set(nBaseYears + 1, nCompMonths, nCompDays);
					returnDate = tBaseCalendar.getTime();
				} else {
					returnDate = tCompCalendar.getTime();
				}
			}
			if (unit.equals("M")) {
				tCompCalendar.set(nBaseYears, nBaseMonths, nCompDays);
				if (tCompCalendar.before(tBaseCalendar)) {
					tBaseCalendar.set(nBaseYears, nBaseMonths + 1, nCompDays);
					returnDate = tBaseCalendar.getTime();
				} else {
					returnDate = tCompCalendar.getTime();
				}
			}
			if (unit.equals("D")) {
				returnDate = tBaseCalendar.getTime();
			}
			tCompCalendar = null;
		} else {
			returnDate = tBaseCalendar.getTime();
			GregorianCalendar tLeapCalendar = new GregorianCalendar();
			tLeapCalendar.setTime(returnDate);
			tLeapCalendar = null;
		}
		tBaseCalendar = null;

		return returnDate;
	}

	/**
	 *
	 * 获取上周一时间
	 * 
	 * @param date
	 * @return
	 */
	public static String getLastWeekMonday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getThisWeekMonday(date));
		cal.add(Calendar.DATE, -7);
		return getDateStr(cal.getTime());
	}

	/**
	 *
	 * 获取上周日时间
	 * 
	 * @param date
	 * @return
	 */
	public static String getLastWeekSunday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getThisWeekMonday(date));
		cal.add(Calendar.DATE, -1);
		return getDateStr(cal.getTime());
	}

	/**
	 *
	 * 获取上周六时间
	 * 
	 * @param date
	 * @return
	 */
	public static String getLastWeekSaturday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getThisWeekMonday(date));
		cal.add(Calendar.DATE, -2);
		return getDateStr(cal.getTime());
	}

	/**
	 *
	 * 获取上周五时间
	 * 
	 * @param date
	 * @return
	 */
	public static String getLastWeekFriday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getThisWeekMonday(date));
		cal.add(Calendar.DATE, -3);
		return getDateStr(cal.getTime());
	}

	/**
	 *
	 * 获取这周四时间
	 * 
	 * @param date
	 * @return
	 */
	public static String getThisWeekThursday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getThisWeekMonday(date));
		cal.add(Calendar.DATE, 3);
		return getDateStr(cal.getTime());
	}

	/**
	 *
	 * 获取这周五时间
	 * 
	 * @param date
	 * @return
	 */
	public static String getThisWeekFriday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getThisWeekMonday(date));
		cal.add(Calendar.DATE, 4);
		return getDateStr(cal.getTime());
	}

	/**
	 *
	 * 获取本周一时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date getThisWeekMonday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		// 获得当前日期是一个星期的第几天
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
		if (1 == dayWeek) {
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		// 获得当前日期是一个星期的第几天
		int day = cal.get(Calendar.DAY_OF_WEEK);
		// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
		return cal.getTime();
	}

	/**
	 *
	 * 获取下周一时间
	 * 
	 * @param date
	 * @return
	 */
	public static String getNextWeekMonday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getThisWeekMonday(date));
		cal.add(Calendar.DATE, 7);
		return getDateStr(cal.getTime());
	}

	/**
	 *
	 * 获取下周四时间
	 * 
	 * @param date
	 * @return
	 */
	public static String getNextWeekThursday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getThisWeekMonday(date));
		cal.add(Calendar.DATE, 10);
		return getDateStr(cal.getTime());
	}

	/**
	 *
	 * 获取下周日时间
	 * 
	 * @param date
	 * @return
	 */
	public static String getNextWeekSunday(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getThisWeekMonday(date));
		cal.add(Calendar.DATE, 13);
		return getDateStr(cal.getTime());
	}

	/**
	 * 将毫秒数转化为"yyyy-MM-dd HH:mm:ss"
	 * 
	 * @param time
	 * @return
	 */
	public static String getDateTime(long time) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_HOUR_DATE_TIME);
		Date date = new Date();
		date.setTime(time);
		return simpleDateFormat.format(date);
	}

	/**
	 * 将毫秒数转化为"yyyy-MM-dd"
	 * 
	 * @param time
	 * @return
	 */
	public static String getDateString(long time) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_DATE_DEFAULT);
		Date date = new Date();
		date.setTime(time);
		return simpleDateFormat.format(date);
	}

	/**
	 * 将毫秒数转化为"HH:mm:ss"
	 * 
	 * @param time
	 * @return
	 */
	public static String getTimeString(long time) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_DATE_TIME);
		Date date = new Date();
		date.setTime(time);
		return simpleDateFormat.format(date);
	}

	/**
	 * 将毫秒数转化为"HH:mm"
	 * 
	 * @param time
	 * @return
	 */
	public static String getHHmmString(long time) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_DATE_HOUR);
		Date date = new Date();
		date.setTime(time);
		return simpleDateFormat.format(date);
	}

	/**
	 * 取得指定日期的dayCount天后的日期
	 * 
	 * @return 返回日期
	 */
	public static String getDateAfter(String beginDate, int dayCount) {
		Calendar begin = new GregorianCalendar();

		int[] tempArr = new int[3];
		int i = 0;
		StringTokenizer st = new StringTokenizer(beginDate, "-");
		while (st.hasMoreTokens()) {
			if (i >= 3) {
				throw new IllegalArgumentException("输入的指定日期不规范：" + beginDate);
			}
			tempArr[i++] = Integer.parseInt(st.nextToken());
		}

		begin.set(tempArr[0], tempArr[1] - 1, tempArr[2] + dayCount, 12, 0, 0);

		return getDate(begin);
	}

	/**
	 * 取得指定日期的dayCount天前的日期
	 * 
	 * @return 返回日期
	 */
	public static String getDateBefore(String beginDate, int dayCount) {
		Calendar begin = new GregorianCalendar();

		int[] tempArr = new int[3];
		int i = 0;
		StringTokenizer st = new StringTokenizer(beginDate, "-");
		while (st.hasMoreTokens()) {
			if (i >= 3) {
				throw new IllegalArgumentException("输入的指定日期不规范：" + beginDate);
			}
			tempArr[i++] = Integer.parseInt(st.nextToken());
		}

		begin.set(tempArr[0], tempArr[1] - 1, tempArr[2] - dayCount, 12, 0, 0);

		return getDate(begin);
	}

	/**
	 * 取得指定日期的字符串型
	 * 
	 * @return 返回字符串日期
	 */
	public static String getDate(Calendar date) {
		StringBuffer dateBuff = new StringBuffer();
		dateBuff.append(String.valueOf(date.get(Calendar.YEAR)));
		dateBuff.append("-");
		if (date.get(Calendar.MONTH) < 9) {
			dateBuff.append("0");
		}
		dateBuff.append(String.valueOf(date.get(Calendar.MONTH) + 1));
		dateBuff.append("-");
		if (date.get(Calendar.DATE) <= 9) {
			dateBuff.append("0");
		}
		dateBuff.append(String.valueOf(date.get(Calendar.DATE)));
		return dateBuff.toString();
	}

	/**
	 * @Description:校验时间是否在某一时间段内，闭区间(初版，待完善)
	 * @Param: 参考参数类型"12:00:00","16:00:00","10:00:00"
	 * @return: boolean
	 */
	public static boolean checkTime(String begin, String end, String time) {
		if (begin.compareTo(end) == 0) {
			return false;
		}
		// 开始时间小于结束时间
		if (begin.compareTo(end) < 0) {
			if (time.compareTo(begin) >= 0 && time.compareTo(end) <= 0) {
				return true;
			} else {
				return false;
			}
		}
		// 开始时间大于结束时间
		if (begin.compareTo(end) > 0) {
			if (time.compareTo(begin) < 0 && time.compareTo(end) > 0) {
				return false;
			} else {
				return true;
			}
		}

		return false;
	}

	/**
	 * @Description:校验时间是否在某一时间段内，开区间(初版，待完善)
	 * @Param: 参考参数类型"12:00:00","16:00:00","10:00:00"
	 * @return: boolean
	 */
	public static boolean checkTime1(String begin, String end, String time) {
		if (begin.compareTo(end) == 0) {
			return false;
		}
		// 开始时间小于结束时间
		if (begin.compareTo(end) < 0) {
			if (time.compareTo(begin) > 0 && time.compareTo(end) < 0) {
				return true;
			} else {
				return false;
			}
		}
		// 开始时间大于结束时间
		if (begin.compareTo(end) > 0) {
			if (time.compareTo(begin) <= 0 && time.compareTo(end) >= 0) {
				return false;
			} else {
				return true;
			}
		}

		return false;
	}

	/**
	 * 返回的格式为：年份后两位+月份，如201905则表示为1905
	 * 
	 * @param n：当前月份的后n个月的日期
	 * @return
	 */
	public static String getMonth(int n) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, n);
		SimpleDateFormat dft = new SimpleDateFormat("yyMM");
		String lastMonth = dft.format(cal.getTime());
		return lastMonth;
	}
	
	/**
	 * 获取当月的第一天
	 * @return
	 */
	public static String getMonthNowFirstDay() {
		// 获取当月第一天和最后一天
	    Calendar cale = null;
	    cale = Calendar.getInstance();
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    String firstday, lastday;
	    // 获取本月的第一天
	    cale = Calendar.getInstance();
	    cale.add(Calendar.MONTH, 0);
	    cale.set(Calendar.DAY_OF_MONTH, 1);
	    firstday = format.format(cale.getTime());
	    // 获取本月的最后一天
	    cale = Calendar.getInstance();
	    cale.add(Calendar.MONTH, 1);
	    cale.set(Calendar.DAY_OF_MONTH, 0);
	    lastday = format.format(cale.getTime());
	    System.out.println("本月第一天和最后一天分别是 ： " + firstday + " and " + lastday);
	    return firstday;
	}
	/**
	 * 获取当月的最后一天
	 * @return
	 */
	public static String getMonthNowLastDay() {
		// 获取当月第一天和最后一天
	    Calendar cale = null;
	    cale = Calendar.getInstance();
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    String firstday, lastday;
	    // 获取本月的第一天
	    cale = Calendar.getInstance();
	    cale.add(Calendar.MONTH, 0);
	    cale.set(Calendar.DAY_OF_MONTH, 1);
	    firstday = format.format(cale.getTime());
	    // 获取本月的最后一天
	    cale = Calendar.getInstance();
	    cale.add(Calendar.MONTH, 1);
	    cale.set(Calendar.DAY_OF_MONTH, 0);
	    lastday = format.format(cale.getTime());
	    System.out.println("本月第一天和最后一天分别是 ： " + firstday + " and " + lastday);
	    return lastday;
	}
	/**
	 * 当年第一天
	 * 
	 * @param date
	 *            日期
	 * @return 天
	 * @throws Exception
	 *             异常
	 */
	public static String getThisYear(String date) throws Exception {
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	    SimpleDateFormat format3 = new SimpleDateFormat("yyyy");
	    Date time = format3.parse(date);
	    String time1 = format3.format(time);
	    Date startTime = format.parse(time1 + "-01-01");
	    String dates = format.format(startTime);
	    return dates;
	}
	/**
	 * 判断日期是否为今天
	 * @param args
	 * @throws Exception
	 */
	public static boolean isNow(Date date) {
		Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String nowDate = format.format(now);
		String day = format.format(date);
		return day.equals(nowDate);
	}
	public static Date getYesDay() {
		Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DATE,-1);
        Date d=cal.getTime();
		return d;
	}
	public static void main(String[] args) throws Exception {
	}
}
