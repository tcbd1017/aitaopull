package cn.kgc.tangcco.tcbd1017.on.system.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.dbutils.handlers.MapListHandler;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.pojo.Emp;
import cn.kgc.tangcco.tcbd1017.on.system.PersonnelMattersDao;
import cn.kgc.tangcco.tcbd1017.on.system.PersonnelMattersService;
import cn.kgc.tangcco.tcbd1017.on.system.SelectEmpPower;

/**
* @author 许佳瑞
* @version 创建时间：2019年12月15日 下午3:07:05
* @edition 1.0
* @Description 类描述
*/
public class PersonnelMattersServiceImpl implements PersonnelMattersService {
	static PersonnelMattersDao personnelMattersDao = null;
	static {
		ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("ApplicationContext_on.xml");
		try {
			personnelMattersDao = (PersonnelMattersDao) ioc.getBean("PersonnelMattersDao");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 *    根据权限删除员工
	 */
	@Override
	public Map<String, Object> removeandmodifyEmp(Map<String, Object> map) {
		/**
		 * mapstates 往前端传递的map
		 * map 往后端传的map
		 * map1 中间map1存操作者的权限
		 */
		Map<String, Object>mapstates=new HashMap<String, Object>();
		SelectEmpPower selectEmpPower= new SelectEmpPowerImpl();
		mapstates.put("status", "failed");
		Emp emp =new Emp();
		int emp_id=(int)map.get("Operator");
		emp.setEmp_id(emp_id);
		Map<String,Object>map1=new HashMap<String, Object>();
		try {
			map1=selectEmpPower.selectEmpPower(emp);
			System.out.println(map1.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put("empPower", map1.get("empPower"));
		map.put("deptPower", map1.get("deptPower"));
		int i=personnelMattersDao.delecteandupdateEmp(map);
		if (i>0) {
			mapstates.put("status", "success");
		}
		try {
			BaseDBUtils.closeAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mapstates;
	}
	
	/**
	 * 修改部门
	 */
	@Override
	public Map<String, Object> modifyEmpDept(Map<String, Object> map) {
		/**
		 * mapstates 往前端传递的map
		 * map 往后端传的map
		 * map1 中间map1存操作者的权限
		 */
		Map<String, Object>mapstates=new HashMap<String, Object>();
		SelectEmpPower selectEmpPower= new SelectEmpPowerImpl();
		mapstates.put("status", "failed");
		Emp emp =new Emp();
		int emp_id=(int)map.get("Operator");
		emp.setEmp_id(emp_id);
		Map<String,Object>map1=new HashMap<String, Object>();
		try {
			/**
			 * 查看操作者权限
			 */
			map1=selectEmpPower.selectEmpPower(emp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put("empPower", map1.get("empPower"));
		map.put("deptPower", map1.get("deptPower"));
		int i=personnelMattersDao.updateEmpdept(map);
		if (i>0) {
			mapstates.put("status", "Success");
		}
		try {
			BaseDBUtils.closeAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mapstates;
	}
	
	
	
	
		
}
