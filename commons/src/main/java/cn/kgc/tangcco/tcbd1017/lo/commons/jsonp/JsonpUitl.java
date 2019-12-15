package cn.kgc.tangcco.tcbd1017.lo.commons.jsonp;

import com.alibaba.fastjson.JSON;

/**
 * @author 李昊哲
 * @version v1.0<br>
 * 创建时间:	2019年11月5日	下午12:14:12<br>
 * 类描述:
 */
public abstract class JsonpUitl {
	public static String getResponseText(String callback ,String json) {
		return callback + "(" + json + ")";
	}
	public static String getResponseText(String callback ,Object object) {
		return getResponseText(callback, JSON.toJSONString(object));
	}
}
