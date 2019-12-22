package BrandDaoTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import cn.kgc.tangcco.dao.impl.ShoploginDaoImpl;

public class loginTest {
	ShoploginDaoImpl aa=new ShoploginDaoImpl();
	@Test
	public void test2() {
		Map<String,Object>map=new HashMap<String, Object>();
		map.put("shop_account", "shouji");
		map.put("shop_password", "1f4a79344c3e1f58");
		List<Object> loginShop = aa.loginShop(map);
		for (Object object : loginShop) {
			System.out.println(object.toString());
		}
	}
	
	@Test
	public void test() {
		Map<String,Object>map=new HashMap<String, Object>();
		map.put("emp_account", "niuwei");
		map.put("emp_password", "1f4a79344c3e1f58");
		List<Object> loginShop = aa.empLogin(map);
		for (Object object : loginShop) {
			System.out.println(object.toString());
		}
	}
}
