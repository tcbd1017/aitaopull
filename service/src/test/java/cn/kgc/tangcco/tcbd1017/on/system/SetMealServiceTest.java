package cn.kgc.tangcco.tcbd1017.on.system;

import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;

/**
	 * @author XUE TONG
	 * @version 1.0 2019年12月15日下午2:19:46
	 * </br>
	 **/

public class SetMealServiceTest {
	private static SetMealService setMealService;
	private static ClassPathXmlApplicationContext path = new ClassPathXmlApplicationContext(
			"ApplicationContext_on.xml");
	static {
		try {
			setMealService = (SetMealService) path.getBean(SetMealService.class.getSimpleName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}