package cn.kgc.tangcco.tcbd1017.on.system;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSON;

import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.pojo.EmpLogin;


/**
 * @author XUE TONG
 * @version 1.0 2019年12月19日下午2:39:32 </br>
 **/

public class EmpLoginDaoTest {
	private static EmpLoginDao empLoginDao;
	private static ClassPathXmlApplicationContext path = new ClassPathXmlApplicationContext(
			"ApplicationContext_on.xml");
	static {
		try {
			empLoginDao = (EmpLoginDao) path.getBean(EmpLoginDao.class.getSimpleName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void selectEmpLoginAccountAndPassword() {
		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("account", "admin");
//		map.put("password", "123456");
		map.put("emp_login_face_token", "1234567890");
		EmpLogin empLogin = null;
		try {
			empLogin = empLoginDao.selectEmpLoginAccountAndPassword(map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(empLogin);
	}
	
	public static void main(String[] args) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("account", "admin");
		map.put("password", "123456");
//		map.put("emp_login_face_token", "1234567890");
		System.out.println(JSON.toJSONString(map));

	}
}
