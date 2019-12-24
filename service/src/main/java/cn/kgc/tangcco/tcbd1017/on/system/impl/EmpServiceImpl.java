package cn.kgc.tangcco.tcbd1017.on.system.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.pojo.Emp;
import cn.kgc.tangcco.tcbd1017.on.pojo.EmpAllInfo;
import cn.kgc.tangcco.tcbd1017.on.system.EmpDao;
import cn.kgc.tangcco.tcbd1017.on.system.EmpService;


/**
	 * @author XUE TONG
	 * @version 1.0 2019年12月24日下午4:25:10
	 * </br>
	 **/

public class EmpServiceImpl implements EmpService{
	private static EmpDao empDao;
	private static ClassPathXmlApplicationContext path = new ClassPathXmlApplicationContext(
			"ApplicationContext_on.xml");
	static {
		try {
			empDao = (EmpDao) path.getBean(EmpDao.class.getSimpleName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Map<String, Object> selectEmp(Emp emp) throws SQLException {
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("code", 0);
		map1.put("msg", "");
		map1.put("count", 0);
		map1.put("status", "failed");
		EmpAllInfo empAllInfo = null;	
		try {
			empAllInfo =  empDao.selectEmp(emp);
			if (empAllInfo != null) {
				map1.put("status", "success");
				map1.put("date", empAllInfo);
			}
			BaseDBUtils.closeAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map1;
	}


}
