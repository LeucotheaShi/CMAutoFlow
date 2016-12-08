package cmsz.autoflow.engine.helper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateHelper {

private static final String DATE_FORMAT_DEFAULT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 返回标准格式的当前时间
	 * @return
	 */
	public static String getTime() {
		SimpleDateFormat format=new SimpleDateFormat(DATE_FORMAT_DEFAULT);
		Date date=new Date();
		return format.format(date);
	}
	
	/**
	 * 在当前日期基础上，增加或加少i天， 返回yyyyMMdd格式的日期
	 * @param i		 增加或减少的天数
	 * @return
	 */
	public static String getDate(Integer i)
	{
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, i);
		SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd");
		return format.format(cal.getTime());
	}
}
