package com.ssm.pt.util;
/*package com.cn.zsl.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

*//**
 * @author asus
 *
 *//*
*//**
 * ����ת���� ת��һ�� java.util.Date ����һ���ַ����Լ� һ���ַ�����һ�� java.util.Date ����.
 *//*
public class DateUtil {
	public static final long SECOND = 1000;

	public static final long MINUTE = SECOND * 60;

	public static final long HOUR = MINUTE * 60;

	public static final long DAY = HOUR * 24;

	public static final long WEEK = DAY * 7;

	public static final long MONTH = DAY * 30;

	public static final long YEAR = DAY * 365;

	public static final String TYPE_DATE = "date";

	public static final String TYPE_TIME = "time";

	public static final String TYPE_DATETIME = "datetime";

	private static final ThreadLocal<Long> LASTTIME = new ThreadLocal<Long>();

	*//**
	 * ģʽ:yyyy-MM-dd HH:mm
	 *//*
	public static final String PATTERN_DATETIME = ResourceUtil
			.getString("date.format.datetime");

	*//**
	 * ģʽ:yyyy-MM-dd
	 *//*
	public static final String PATTERN_DATE = ResourceUtil
			.getString("date.format.date");

	private static final String DEFAULT_PATTERN = PATTERN_DATETIME;

	public static final String[] TYPE_ALL = { TYPE_DATE, TYPE_DATETIME,
			TYPE_TIME };

	*//**
	 * ���ַ���ת��ΪDate����
	 * 
	 * @param strDate
	 * @param pattern
	 *            ��ʽ
	 * @return
	 * @throws ParseException
	 *//*
	public static Date convertStringToDate(String strDate, String pattern) {
		if (StringUtil.isNull(strDate))
			return null;
		if (StringUtil.isNull(pattern))
			pattern = DEFAULT_PATTERN;
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		try {
			return df.parse(strDate);
		} catch (ParseException e) {
			throw new KmssRuntimeException(e);
		}
	}

	*//**
	 * ���ַ���ת��ΪDate����
	 * 
	 * @param strDate
	 * @param type
	 *            �������е�TYPE_*����
	 * @param locale
	 * @return
	 * @throws ParseException
	 *//*
	public static Date convertStringToDate(String strDate, String type,
			Locale locale) {
		if (StringUtil.isNull(strDate))
			return null;
		if (StringUtil.isNull(type))
			type = TYPE_DATETIME;
		String pattern = ResourceUtil.getString("date.format." + type, locale);
		KmssRuntimeException rtnE;
		try {
			return convertStringToDate(strDate, pattern);
		} catch (KmssRuntimeException e) {
			rtnE = e;
		}
		for (int i = 0; i < TYPE_ALL.length; i++) {
			pattern = ResourceUtil.getString("date.format." + TYPE_ALL[i],
					locale);
			try {
				return convertStringToDate(strDate, pattern);
			} catch (KmssRuntimeException e) {
			}
		}
		throw rtnE;
	}

	*//**
	 * ��Dateת��Ϊ�ַ���
	 * 
	 * @param aDate
	 * @param pattern
	 *            ��ʽ
	 * @return
	 *//*
	public static String convertDateToString(Date aDate, String pattern) {
		if (aDate == null)
			return null;
		if (StringUtil.isNull(pattern))
			pattern = DEFAULT_PATTERN;
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		return df.format(aDate);
	}

	*//**
	 * ��Dateת��Ϊ�ַ���
	 * 
	 * @param aDate
	 * @param type
	 *            �������е�TYPE_*����
	 * @param locale
	 * @return
	 *//*
	public static String convertDateToString(Date aDate, String type,
			Locale locale) {
		if (aDate == null)
			return null;
		if (type == null)
			type = TYPE_DATETIME;
		String pattern = ResourceUtil.getString("date.format." + type, locale);
		return convertDateToString(aDate, pattern);
	}

	*//**
	 * �����ڡ�ʱ��ϲ��ɳ���������
	 * 
	 * @param date
	 *            ����
	 * @param time
	 *            ʱ��
	 * @return
	 *//*
	public static long getDateTimeNumber(Date date, Date time) {
		Calendar dateCal = getCalendar(date);
		Calendar timeCal = getCalendar(time);
		dateCal.set(Calendar.HOUR_OF_DAY, timeCal.get(Calendar.HOUR_OF_DAY));
		dateCal.set(Calendar.MINUTE, timeCal.get(Calendar.MINUTE));
		dateCal.set(Calendar.SECOND, timeCal.get(Calendar.SECOND));
		dateCal.set(Calendar.MILLISECOND, timeCal.get(Calendar.MILLISECOND));
		return dateCal.getTimeInMillis();
	}

	*//**
	 * �����ڵ�ʱ�䲿�������ת����long����
	 * 
	 * @param date
	 * @return
	 *//*
	public static long getDateNumber(Date date) {
		return removeTime(date).getTimeInMillis();
	}

	*//**
	 * ��ȡ����(��ȡ��������getDate(0))
	 * 
	 * @param day
	 * @return
	 *//*
	public static Date getDate(int day) {
		Calendar cal = getCalendar(new Date());
		cal.add(Calendar.DATE, day);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	*//**
	 * ��ʱ������ڲ��������ת����long����
	 * 
	 * @param date
	 * @return
	 *//*
	public static long getTimeNubmer(Date date) {
		return getCalendar(date).getTimeInMillis() - getDateNumber(date);
	}

	*//**
	 * ��һ�����������ڵ�ʱ������ת��ΪDate���ͣ����е�����Ϊ����
	 * 
	 * @param l
	 * @return
	 *//*
	public static Date getTimeByNubmer(long l) {
		return new Date(getDateNumber(new Date()) + l);
	}

	public static Calendar getCalendar(long millis) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(millis);
		return cal;
	}

	public static Calendar getCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	public static Calendar removeTime(Date date) {
		if (date == null) {
			return null;
		}

		Calendar cal = getCalendar(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal;
	}

	public static Date getNextDay(Date date, int day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, day);
		return cal.getTime();
	}

	*//**
	 * ��ȡ��ǰʱ�䣬����֤��ͬһ�߳��ں�����õ�ʱ����ǰ�����֮�󣨼���ͬһ�����ڣ�
	 *//*
	public static Date getDateQueue() {
		long now = System.currentTimeMillis();
		Long t = LASTTIME.get();
		if (t != null) {
			if (now <= t.longValue()) {
				now = t.longValue() + 1;
			}
		}
		LASTTIME.set(now);
		return new Date(now);
	}
}*/