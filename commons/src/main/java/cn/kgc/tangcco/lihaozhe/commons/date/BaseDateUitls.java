package cn.kgc.tangcco.lihaozhe.commons.date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class BaseDateUitls {
	//mysql数据库中的时间类格式,
	private final static String formatString = "yyyy-MM-dd HH:mm:ss";
	
	//传入一个Date类，调用parse进行格式化成String
	public static String getDateString(Date date) {
		return getDateString(date, formatString);
	}
	//SimpleDateFormat把date转化成String
	public static String getDateString(Date date, String formatString) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatString);
		return sdf.format(date);
	}
	
	//把string 格式的时间，转化成Date类
	public static Date parse(String source) throws ParseException {
		return parse(source, formatString);
	}
	//用SimpleDateFormat类，来解析String类时间，并返回一个date类
	public static Date parse(String source, String formatString) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(formatString);
		return sdf.parse(source);
	}
}
