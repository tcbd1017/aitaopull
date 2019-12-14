package cn.kgc.tangcco.tcbd1017.lo.commons.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 
 * @author 李昊哲
 * @version 1.0 <br>
 *          创建时间: 2019年9月25日 上午10:18:06<br>
 *          类描述:
 *
 */
@SuppressWarnings("unchecked")
public abstract class MultipleFactory {

	public static <T> T getInstance(String className) {
		try {
			// 获取构造方法
			Constructor<?> declaredConstructor = Class.forName(className).getDeclaredConstructor();
			// 设置构造方法可访问权限
			declaredConstructor.setAccessible(true);
			// 返回该类的实例化对象
			return (T) declaredConstructor.newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
}
