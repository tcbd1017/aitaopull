package cn.kgc.tangcco.tcbd1017.on.system.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.pojo.EmpLogin;
import cn.kgc.tangcco.tcbd1017.on.system.EmpLoginDao;
import cn.kgc.tangcco.tcbd1017.on.system.EmpLoginService;

/**
	 * @author XUE TONG
	 * @version 1.0 2019年12月19日下午3:10:56
	 * </br>
	 **/

public class EmpLoginServiceImpl implements EmpLoginService{
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
	
	@Override
	public Map<String, Object> selectEmpLoginAccountAndPassword(Map<String, Object> map) throws SQLException {
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("status", "failed");
		EmpLogin empLogin = null;
		try {
			empLogin = empLoginDao.selectEmpLoginAccountAndPassword(map);
			if (empLogin != null) {
				map1.put("status", "success");
				map1.put("EmpLogin", empLogin);
			}
			BaseDBUtils.closeAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map1;
	}

}
