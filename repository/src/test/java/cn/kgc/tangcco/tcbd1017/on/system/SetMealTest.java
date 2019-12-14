package cn.kgc.tangcco.tcbd1017.on.system;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.junit.Test;

import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.pojo.SetMeal;

/**
	 * 
	 * @author 薛彤
	 * @version 1.0 2019年12月13日 下午2:14:19
	 *
	 */

public class SetMealTest {
	private static SetMealDao setMealDao;
	private static ClassPathXmlApplicationContext path = new ClassPathXmlApplicationContext("ApplicationContext_on.xml");
	static {
		try {		
			setMealDao = (SetMealDao) path.getBean(SetMealDao.class.getSimpleName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void selectAllSetMeal() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("set_meal_id", "1");
		try {
			List<SetMeal> set = setMealDao.selectAllSetMeal(map);
			ListIterator<SetMeal> it = set.listIterator();
			while (it.hasNext()) {
				SetMeal s =  it.next();
				System.out.println(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void selectSetMeal() {
		try {
			SetMeal setMeal = setMealDao.selectSetMeal(1);
			System.out.println(setMeal);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void insertSetMeal() {
		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("set_meal_id", 2);
		map.put("set_meal_name", "优惠3号");
		map.put("set_meal_price", 1000);
		map.put("set_meal_interest", 0.05);
		map.put("set_meal_duration", 53072000);
		map.put("set_meal_status", 2);
		try {
			int count = setMealDao.insertSetMeal(map);
			System.out.println(count);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void deleteSetMeal() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("set_meal_id", 4);
		int delete;
		try {
			delete = setMealDao.deleteSetMeal(map);
			System.out.println(delete);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}