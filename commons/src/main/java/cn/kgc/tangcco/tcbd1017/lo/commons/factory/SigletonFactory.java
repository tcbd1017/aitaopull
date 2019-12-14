package cn.kgc.tangcco.tcbd1017.lo.commons.factory;
/**
 * 
 * @author 李昊哲
 * @version 1.0 <br>
 * 创建时间:	2019年9月23日	上午11:43:54<br>
 * 类描述:
 *
 */

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public abstract class SigletonFactory {
	// 存储实例化对象
	private static Map<String, Object> map = new HashMap<String, Object>();
	/**
	 * 
	 * @param key		xml文件中id的值
	 * @param className	xml文件中接口的实现类的完全限定名
	 * @return			key对应的接口的实现类实例化对象
	 */
	public static Object getInstance(String key,String className) {
		try {
			// 获取该类的Class对象
			Class clazz = Class.forName(className);
			// 获取构造方法
			Constructor declaredConstructor = clazz.getDeclaredConstructor();
			// 设置访问权限
			declaredConstructor.setAccessible(true);
			// 如果该key在map中存在则从map中获取并返回
			if (map.containsKey(key)) {
				return map.get(key);
			}
			// 如果该key在map中不存在则实例化
			Object newInstance = declaredConstructor.newInstance();
			// 将实例化对象与key关联存储在map中
			map.put(key, newInstance);
			// 返回实例化对象
			return newInstance;
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}
	}
}
