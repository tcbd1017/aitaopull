package cn.kgc.tangcco.tcbd1017.on.system.text;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.pojo.EmpAllInfo;
import cn.kgc.tangcco.tcbd1017.on.system.DeptDao;

/**
* @author 作者 : 廖斌
* @version 创建时间：Dec 14, 2019 6:17:21 PM
* 	
*/
public class DeptTest {
	static DeptDao deptdao = null;
	static {
		ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("ApplicationContext_on.xml");
		try {
			deptdao=(DeptDao)ioc.getBean("DeptDao");
			System.out.println("test");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void selectTest() {
		Map map=new HashMap();
		try {
			List<EmpAllInfo> list = deptdao.selectByDept(map);
			for (EmpAllInfo empAllInfo : list) {
				System.out.println(empAllInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
