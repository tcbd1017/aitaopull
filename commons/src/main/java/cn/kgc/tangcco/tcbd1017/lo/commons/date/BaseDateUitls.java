package cn.kgc.tangcco.tcbd1017.lo.commons.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public abstract class BaseDateUitls {
	private final static String formatString = "yyyy-MM-dd";

	public static String getDateString(Date date) {
		return getDateString(date, formatString);
	}

	public static String getDateString(Date date, String formatString) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatString);
		return sdf.format(date);
	}
	public static Date parse(String source) throws ParseException {
		return parse(source, formatString);
	}
	public static Date parse(String source, String formatString) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(formatString);
		return sdf.parse(source);
	}
	public static String getRandomString(int length){
	     String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	     Random random=new Random();
	     StringBuffer sb=new StringBuffer();
	     for(int i=0;i<length;i++){
	       int number=random.nextInt(62);
	       sb.append(str.charAt(number));
	     }
	     return sb.toString();
	 }
	
}
