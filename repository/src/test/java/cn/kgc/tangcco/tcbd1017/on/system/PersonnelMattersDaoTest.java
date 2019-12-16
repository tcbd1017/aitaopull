package cn.kgc.tangcco.tcbd1017.on.system;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import cn.kgc.tangcco.tcbd1017.on.pojo.Emp;
import cn.kgc.tangcco.tcbd1017.on.system.impl.PersonnelMattersDaoImpl;
import cn.kgc.tangcco.tcbd1017.on.system.impl.SelectEmpPowerImpl;

/**
* @author 许佳瑞
* @version 创建时间：2019年12月15日 上午10:14:31
* @edition 1.0
* @Description 类描述
*/
public class PersonnelMattersDaoTest {
	@Test
	public void test01() {
		PersonnelMattersDao personnelMattersDao=new PersonnelMattersDaoImpl();
		Map<String, Object>map =new HashMap<String, Object>();
		Emp emp=new Emp();
		emp.setEmp_id(1);
		SelectEmpPower selectEmpPower=new SelectEmpPowerImpl();
		try {
			map =selectEmpPower.selectEmpPower(emp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put("emp_id",3);
		map.put("dept_id",2);
		int i=personnelMattersDao.updateEmpdept(map);
		System.out.println(i);
	}
	
	@Test
	public void test02() {
		PersonnelMattersDao personnelMattersDao=new PersonnelMattersDaoImpl();
		Map<String, Object>map =new HashMap<String, Object>();
		Emp emp=new Emp();
		emp.setEmp_id(2);
		SelectEmpPower selectEmpPower=new SelectEmpPowerImpl();
		try {
			map =selectEmpPower.selectEmpPower(emp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map.put("emp_id",3);
		map.put("emp_status",3);
		personnelMattersDao.delecteandupdateEmp(map);
		int i=personnelMattersDao.delecteandupdateEmp(map);
		System.out.println(i);
	}
}
