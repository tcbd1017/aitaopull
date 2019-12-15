package cn.kgc.tangcco.tcbd1017.on.system.text;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSON;

import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.pojo.SetMeal;
import cn.kgc.tangcco.tcbd1017.on.system.SetMealDao;
import cn.kgc.tangcco.tcbd1017.on.system.impl.SetMealDaoImpl;

/**
	 * 
	 * @author 薛彤
	 * @version 1.0 2019年12月13日 下午2:14:19
	 *
	 */

public class SetMealDaoTest {
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
		map.put("set_meal_creat_time", "2019-12-12 14:19:17");
		map.put("set_meal_update_time", "2019-12-13 14:19:21");
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
	public void selectCountSetMeal() {
		Map<String, Object> map = new HashMap<String, Object>();
			int count;
			try {
				count = setMealDao.selectCountSetMeal(map);
				System.out.println(count);
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
	public void updateSetMeal() {
		SetMeal setMeal =new SetMeal();
		setMeal.setSet_meal_id(1);
		setMeal.setSet_meal_name("大优惠");
		setMeal.setSet_meal_interest(0.03);
		try {
			int count= setMealDao.updateSetMeal(setMeal);
			System.out.println(count);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void deleteSetMeal() {
		try {
			int setMeal = setMealDao.deleteSetMeal(1);
			System.out.println(setMeal);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	
	public static void main(String[] args) {
		
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("set_meal_name", "优惠3号");
//		map.put("set_meal_price", 1000);
//		map.put("set_meal_interest", 0.05);
//		map.put("set_meal_duration", 53072000);
//		map.put("set_meal_status", 2);
		SetMeal setMeal =new SetMeal();
		setMeal.setSet_meal_id(1);
		setMeal.setSet_meal_name("大优惠");
		setMeal.setSet_meal_interest(0.03);
		System.out.println(JSON.toJSONString(setMeal));
		
	}
}