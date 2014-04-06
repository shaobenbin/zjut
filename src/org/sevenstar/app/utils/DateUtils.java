package org.sevenstar.app.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.sevenstar.persistent.db.ibatis.IbatisDao;

public class DateUtils {
	public static int getWeekDay(Date date) {
		int month = date.getMonth();
		int year = date.getYear();
		int day = date.getDay();
		if (month <= 2) {
			year -= 1;
			month += 12;
		}

		int week = (day + ((8 + (13 * month)) / 5) + (year + (year / 4)
				- (year / 100) + (year / 400))) % 7;
		return week;
	}
	
	public static String getWeekDay(){
		String outValue = "";
	    Calendar calendar = Calendar.getInstance();
	    switch(calendar.get(Calendar.DAY_OF_WEEK) ){
	      case Calendar.MONDAY:
	        outValue = "星期一";
	        break;
	      case Calendar.TUESDAY:
	        outValue = "星期二";
	        break;
	      case Calendar.WEDNESDAY:
	        outValue = "星期三";
	        break;
	      case Calendar.THURSDAY:
	        outValue = "星期四";
	        break;
	      case Calendar.FRIDAY:
	        outValue = "星期五";
	        break;
	      case Calendar.SATURDAY:
	        outValue = "星期六";
	        break;
	      case Calendar.SUNDAY:
	        outValue = "星期日";
	        break;
	      default:
	          ;
	      }
	      return outValue;
	}

	public static Date stringToDate(String stringDate) {
		Date date = null; 
		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = inputFormat.parse(stringDate); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	public static long getDay(Date beginTime, Date finishTime) {
		long ss = finishTime.getTime() - beginTime.getTime();
		long day =(long)(ss /(24 * 3600*1000));
		return ++day;
	}
	
	public static String getDateString(Date date, String dateFormat){
		if (date == null){
			return "";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(date);
	}
	public static Date getcurrentDate(){
		return new Date(IbatisDao.getCurrentDBTimeInMillis());
	}
	
	public static Date getDateFromString(String sdate, String datepattern){
		Date date = null;
		if ((sdate == null) || (sdate.length() == 0)) {
			return date;
		}
		DateFormat formatter = new SimpleDateFormat(datepattern);
		try {
			date = (Date) formatter.parse(sdate);
		} catch (ParseException e) {
			e.printStackTrace();
		}	
		return date;
	}

}
