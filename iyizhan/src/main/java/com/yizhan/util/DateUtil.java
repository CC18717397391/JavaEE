package com.yizhan.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {

	/**年-月-日  时：分：秒*/
	public static String getNowDateTime(){
		String time = new java.sql.Timestamp(new java.util.Date().getTime()).toString();
		return time.substring(0, time.lastIndexOf("."));
	}
	
	/**年-月-日*/
	public static Date getNowDate(){
		java.sql.Date nowDate = new java.sql.Date(System.currentTimeMillis());
		return nowDate;
	}
	
	/**获取指定格式的日期时间类型*/
	public static String getNowDateTimeByFmt(String dateFormat){
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		String dateStr = sdf.format(now);
		return dateStr;
	}
	
	/**字符串转日期*/
	public static Date convertToDate(String dateFormat, String dateStr){
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	
	/**日期转字符串*/
	public static String convertToString(String dateFormat, Date date){
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(date);
	}
	
	/**获取两个日期的天数差*/
	public static int getTwoDateDiff(String dateFormat, String dateBegin, String dateEnd){
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		int days = 0;
		try {
			Date begin = sdf.parse(dateBegin);
			Date end = sdf.parse(dateEnd);
			long time = Math.abs(begin.getTime()-end.getTime());
			days = (int) (time/(24*60*60*1000));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return days;
	}
	
	/**带时区的时间转换*/
	public static String parseTimeZoneDate(String timeZoneDate){
		String normalZoneDate = "";
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
		df.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
		try {
			Date date = df.parse(timeZoneDate);
			SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			normalZoneDate = df2.format(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return normalZoneDate;
	}
	
}
