package com.zx.xsk.spaylibrary.alipay.util;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
	private static final String TAG = DateUtil.class.getSimpleName();
	public static final SimpleDateFormat STRING_DATE_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	public static final SimpleDateFormat STRING_DATE_FORMAT2 = new SimpleDateFormat(
			"yyyy-MM-dd");
	public static final SimpleDateFormat DATE_STRING_FORMAT = new SimpleDateFormat(
			"yyyy/MM/dd");

	public static final SimpleDateFormat DATE_POINT_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm");

	public static final SimpleDateFormat DATE_STR_FORMAT = new SimpleDateFormat(
			"yyyy年MM月dd日 HH:mm");

	public static final SimpleDateFormat DATA_MAT = new SimpleDateFormat(
			"yyyy年MM月dd日");
	private static final SimpleDateFormat DATE_MM_SS_FORMAT = new SimpleDateFormat(
			"mm:ss");
	private static final SimpleDateFormat DATE_HH_MM_FORMAT = new SimpleDateFormat(
			"HH:mm");
	private static final long DAY_IN_MILLISECOND = 24 * 60 * 60 * 1000;
	private static final long HOUR_IN_MILLISECOND = 60 * 60 * 1000;
	private static final long MINUTE_IN_MILLISECOND = 60 * 1000;
	private static final long TEN_SECOND_IN_MILLISECOND = 10 * 1000;
	private static final long SECOND_IN_MILLISECOND = 1000;


	/**
	 * 
	 * @Title: timestamp 
	 * @Description: TODO(获取当前时间毫秒数) 
	 * @return
	 * long
	 * @throws
	 */
	public static long timestamp(){
		return System.currentTimeMillis()/1000;
	}
	
	/**
	 * 毫秒数转换成yyyy-MM-dd
	 */
	public static String getTime(long timeMillis){
		Date d = new Date(timeMillis);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(d);
	}
	/**
	 * 毫秒数转换成yyyy-MM-dd HH:mm:ss
	 */
	public static String getTimeFour(long timeMillis){
		Date d = new Date(timeMillis);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(d);
	}
	/**
	 * 毫秒数转换成yyyy.MM.dd
	 */
	public static String getTimeTwo(long timeMillis){
		Date d = new Date(timeMillis);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		return sdf.format(d);
	}
	/**
	 * 毫秒数转换成yyyy.MM.dd HH:mm:ss
	 */
	public static String getTimeThree(long timeMillis){
		Date d = new Date(timeMillis);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd  HH:mm:ss");
		return sdf.format(d);
	}
	/**
	 * 毫秒数转换成yyyy
	 */
	public static String getTimeYear(long timeMillis){
		Date d = new Date(timeMillis);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		return sdf.format(d);
	}
	/**
	 * 
	 * @param str yyyy-MM-dd HH:mm转化成HH：mm
	 * @return
	 */
	public static String getDateHHmm(String str) {
		String dateStr = DATE_HH_MM_FORMAT.format(DateUtil.getDate(str)
				.getTime());
		return dateStr;
	}

	/**
	 * 把str 2015-2-2转 2015-02-02
	 * 
	 * @param str
	 * @return
	 */
	public static String getFormatStr(String str) {
		if (TextUtils.isEmpty(str)) {
			return str;
		}
		Date date = null;
		;
		try {
			date = DATE_STRING_FORMAT.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String dateStr = DATE_STRING_FORMAT.format(date);
		return dateStr;
	}

	public static boolean isSelect(String year) {
		boolean flag = true;
		try {
			SimpleDateFormat aa = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date date = aa.parse(year);
			long selectTime = date.getTime();
			long selectTime2 = selectTime + 30 * MINUTE_IN_MILLISECOND;
			long currentTime = new Date().getTime();
			long cha = selectTime2 - currentTime;
			if (cha < 30 * MINUTE_IN_MILLISECOND) {
				flag = false;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	
	public static String getStrByStrDate(String strDate) {
		String week = null;
		try {
			Date date = DATE_POINT_FORMAT.parse(strDate);
			SimpleDateFormat dateFm = new SimpleDateFormat(
					"yyyy-MM-dd EEEE HH:mm");
			week = dateFm.format(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return week;
	}


	/*
	 * 毫秒转化时分秒毫秒
	 */
	public synchronized static String formatTime(Long ms) {
		Integer ss = 1000;
		Integer mi = ss * 60;
		Integer hh = mi * 60;
		Integer dd = hh * 24;
		// Long day = ms / dd;
		Long hour = ms / hh;
		Long minute = (ms - hour * hh) / mi;
		Long second = (ms - hour * hh - minute * mi) / ss;

		StringBuffer sb = new StringBuffer();
		/*
		 * if (day >= 0) { sb.append(day + "天"); }
		 */
		if (hour >= 0) {
			if (hour < 10) {

				sb.append("0" + hour + ":");
			} else {
				sb.append(hour + ":");
			}
		} else {
			sb.append("00:");
		}
		if (minute >= 0) {
			if (minute < 10) {
				sb.append("0" + minute + ":");
			} else {
				sb.append(minute + ":");
			}
		} else {
			sb.append("00:");
		}
		if (second >= 0) {
			if (second < 10) {
				sb.append("0" + second);
			} else {
				sb.append(second);
			}
		} else {
			sb.append("00");
		}
		return sb.toString();
	}

	/*
	 * yyyy-MM-dd HH:mm:ss转化毫秒
	 */
	public synchronized static Long getSecond(String time) {
		boolean flag = false;
		Date date = null;
		try {
			SimpleDateFormat foamat = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss", Locale.CHINA);
			date = foamat.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			date = getDate(time);
		}

		return date.getTime();
	}

	/*
	 * 获取当前日期yyyy-MM-dd HH:mm
	 */
	public synchronized static String getStrDate() {
		Date date = new Date();
		String str = "";
		try {
			str = STRING_DATE_FORMAT.format(date);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}

	/*
	 * yyyy-MM-dd HH:mm转化毫秒
	 */
	public synchronized static Date getDate(String time) {
		Date date = null;
		try {
			date = DATE_POINT_FORMAT.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	/*
	 * yyyy-MM-dd HH:mm转化毫秒
	 */
	public synchronized static Date getDateByStrDate(String time) {
		Date date = null;
		try {
			date = DATE_STR_FORMAT.parse(time);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				date = DATA_MAT.parse(time);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				try {
					date = DATE_STR_FORMAT.parse(time);
				} catch (ParseException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		}
		return date;
	}

	/**
	 * 
	 * @param  mss 要转换的毫秒数
	 * @return 该毫秒数转换为 * days * hours * minutes * seconds 后的格式
	 * @author fy.zhang
	 */
	public static String formatDuring(long mss) {
		long days = mss / (1000 * 60 * 60 * 24);
		long hours = (mss % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
		long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);
		long seconds = (mss % (1000 * 60)) / 1000;
		return days+"";
	}
	/**
	 * 
	 * @param begin 时间段的开始
	 * @param end	时间段的结束
	 * @return	输入的两个Date类型数据之间的时间间格用* days * hours * minutes * seconds的格式展示
	 * @author fy.zhang
	 */
	public static String formatDuring(Date begin, Date end) {
		return formatDuring(end.getTime() - begin.getTime());
	}

}
