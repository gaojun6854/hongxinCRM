package com.hongxin.utils;
/**
 *shijian
 */
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Date2String8 {
	/**
	 * 时间转换
	 * @param date
	 * @return
	 */
	public static String date2String(Date date){
		if (date==null) {
			date=new Date();	
		}
		SimpleDateFormat dateFormater=new SimpleDateFormat("yyyy-MM-dd");
		String dateStr=dateFormater.format(date);
		String[] dt = dateStr.split("-");
		String year=dt[0];
		String month=dt[1];
		String day=dt[2];
		return year+month+day;
	}
	
	/**
	 * timeת6λ�ַ�
	 * @return
	 */
	public static String time2String(){
		Date date=new Date();	
		SimpleDateFormat dateFormater=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr=dateFormater.format(date);
		String time=dateStr.substring(11, dateStr.length());
		String[] timeArr=time.split(":");
		String hou=timeArr[0];
		String min=timeArr[1];
		String sec=timeArr[2];
		return hou+min+sec;
	}
	
	public static void main(String[] args) {
		SimpleDateFormat dateFormater=new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
	       c.add(Calendar.MONTH, 1); // Ŀǰʱ���1����   
	       System.out.println(dateFormater.format(c.getTime()));   
	       c.add(Calendar.HOUR, 3); // Ŀǰʱ���3Сʱ   
	       System.out.println(dateFormater.format(c.getTime()));   
	       c.add(Calendar.YEAR, -2); // Ŀǰʱ���2��   
	       System.out.println(dateFormater.format(c.getTime()));
	       c.add(Calendar.DAY_OF_WEEK, 7); // Ŀǰ��ʱ���7��   
	       System.out.println(dateFormater.format(c.getTime())); 
	       
	       
	       String a="1122";
	       System.out.println(a.charAt(0));
	       
	       
	       
	       
	     List<String> strs=new ArrayList<String>();
	     strs.add("a");
	     strs.add("b");
	     strs.add("d");
	     strs.add("c");
	     strs.add("e");
	     strs.add("f");
	     for (String str : strs) {
			System.out.println();
			System.out.println(str);
		}
	}
	
}
