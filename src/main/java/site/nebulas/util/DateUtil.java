package site.nebulas.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {
    /**
	 * 
	 * return : "2016-09-23"
	 * */
	public static String getDate() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
	
	/**
	 * param : int d 
	 * return : "2016-09-23"
	 * */
	public static String getDate(int d) {
		Date date = new Date();
		Calendar calendar  = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		calendar.setTime(date);
		calendar.add(Calendar.DATE,d);
		return sdf.format(calendar.getTime());
	}
	
	/**
	 * 
	 * return : "2016-09-23 22:19:56"
	 * */
	public static String getTime() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	
	/**
	 * 
	 * return : "2016-09-23 22:19:56"
	 * */
	public static String getTime(int d) {
		Date date = new Date();
		Calendar calendar  = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		calendar.setTime(date);
		calendar.add(Calendar.DATE,d);
		return sdf.format(calendar.getTime());
	}
	
	/**
	 * Param  ： "2016-09-28","2016-10-02"
	 * return : [2016-09-28, 2016-09-29, 2016-09-30, 2016-10-01, 2016-10-02]
	 * */
	public static List<String> getDateList(String begin,String end){
		List<String> list = new ArrayList<String>();
		Calendar calendar  = Calendar.getInstance(); 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date beginDate = sdf.parse(begin);
			Date endDate = sdf.parse(end);
			
			long days;
			if(endDate.getTime() >= beginDate.getTime()){
				days = (endDate.getTime() - beginDate.getTime())/(1000 * 60 * 60 * 24);
				calendar.setTime(beginDate);
				System.out.println(days);
				list.add(sdf.format(calendar.getTime()));
				for(int i = 0; i < days; i++){
					calendar.add(Calendar.DATE, 1);
					list.add(sdf.format(calendar.getTime()));
				}
			}else{
				days = (beginDate.getTime() - endDate.getTime())/(1000 * 60 * 60 * 24);
				calendar.setTime(endDate);
				System.out.println(days);
				list.add(sdf.format(calendar.getTime()));
				for(int i = 0; i < days; i++){
					calendar.add(Calendar.DATE, 1);
					list.add(sdf.format(calendar.getTime()));
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * //日期格式为 yyyy-MM-dd hh:mm:ss
	 * 比较两个时间的大小
	 * 如果previousDate小于later_date返回true,否则返回false
	 * */
	public static boolean compareTime(String previousDate,String laterDate){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			long previous = sdf.parse(previousDate).getTime();
			long later = sdf.parse(laterDate).getTime();
			if(previous < later)
				return true;				
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void main(String[] args) {
		System.out.println(compareTime("2016-09-23 22:19:56","2016-09-23 22:19:55"));
	}
}
