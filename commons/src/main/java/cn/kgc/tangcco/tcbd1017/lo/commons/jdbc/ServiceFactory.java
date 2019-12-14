package cn.kgc.tangcco.tcbd1017.lo.commons.jdbc;
 
/**
 * @author Cui
 * @version 1.0 时间�?2019�?11�?14�?  下午6:12:40
 */
public class ServiceFactory {
	/**
	 * 取得动�?�代理对�?
	 * @param service
	 * @return	返回获取到的对象
	 */
	public static Object getService(Object service) {
		
		return new TransactionInvocationHandler(service).getProxy();
	}
}
