package cn.kgc.tangcco.lihaozhe.commons.alipay.config;


/**
 * @author Aaron·Li
 * @date 2017年9月20日 上午11:51:31
 */
public class IDUtils {
	private static byte[] lock = new byte[0];
 
	// 位数，默认是8位
	private final static long w = 100000000;
 
	public static String createID() {
		long r = 0;
		synchronized (lock) {
			r = (long) ((Math.random() + 1) * w);
		}
 
		return System.currentTimeMillis() + String.valueOf(r).substring(1);
	}
	
	
	
	public static void main(String[] args) {
		
		System.out.println(IDUtils.createID());
	}
	
}
