package cn.kgc.tangcco.lihaozhe.commons.localdatetime;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeUtil {
	/**
	 * 时间格式化模板字符串
	 */
	private static final String formatter = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 获取默认时间格式化模板
	 * 
	 * @return 默认时间格式化模板
	 */
	public static DateTimeFormatter getPattern() {
		return DateTimeFormatter.ofPattern(formatter);
	}

	/**
	 * 获取时间格式化模板
	 * 
	 * @return 时间格式化模板
	 */
	public static DateTimeFormatter getPattern(String formatter) {
		return DateTimeFormatter.ofPattern(formatter);
	}

	/**
	 * 获取当前北京时间的默认格式化后的字符串
	 * 
	 * @return 当前北京时间的默认格式化后的字符串
	 */
	public static String getStringByCurrentLocalDateTime() {
		return LocalDateTime.now(ZoneOffset.of("+8")).format(getPattern());
	}

	/**
	 * 获取当前北京时间的格式化后的字符串
	 * 
	 * @param formatter 格式化字符串
	 * @return 当前北京时间的格式化后的字符串
	 */
	public static String getStringByCurrentLocalDateTime(String formatter) {
		return LocalDateTime.now(ZoneOffset.of("+8")).format(getPattern(formatter));
	}

	/**
	 * 根据传入的字符串解析为LocalDateTime
	 * 
	 * @param text 传入的时间格式字符串
	 * @return 按照默认模板解析后的LocalDateTime
	 */
	public static LocalDateTime getLocalDateTimeByString(String text) {
		return LocalDateTime.parse(text, getPattern());
	}

	/**
	 * 根据传入的字符串解析为LocalDateTime
	 * 
	 * @param text 传入的时间格式字符串
	 * @param formatter 格式化字符串
	 * @return 解析后的LocalDateTime
	 */
	public static LocalDateTime getLocalDateTimeByString(String text, String formatter) {
		return LocalDateTime.parse(text, getPattern(formatter));
	}
}
