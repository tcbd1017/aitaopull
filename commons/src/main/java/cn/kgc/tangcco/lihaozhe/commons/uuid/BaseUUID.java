package cn.kgc.tangcco.lihaozhe.commons.uuid;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public abstract class BaseUUID {
	/**
	 * 生成唯一标识符
	 * @return	唯一标识符
	 */
	public static String generate() {
		return Integer.toHexString(ThreadLocalRandom.current().nextInt(11111111,99999999)) + UUID.randomUUID().toString().replaceAll("-", "") + Integer.toHexString(ThreadLocalRandom.current().nextInt(11111111,99999999));
	}
	/**
	 * 生成一个int 随机数
	 * @return
	 */
	public static int rendem() {
		int number=(int)((Math.random()*9+1)*100000);
		return number;
	}
}
