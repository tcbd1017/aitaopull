package cn.kgc.tangcco.tcbd1017.on.system;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import cn.kgc.tangcco.tcbd1017.on.pojo.Emp;
import cn.kgc.tangcco.tcbd1017.on.system.impl.SelectEmpPowerImpl;

/**
* @author 许佳瑞
* @version 创建时间：2019年12月15日 上午10:47:41
* @edition 1.0
* @Description 类描述
*/
public class SelectEmpPowerTest {
	@Test
	public void test01() {
		SelectEmpPower selectEmpPower=new SelectEmpPowerImpl();
		Emp emp=new Emp();
		emp.setEmp_id(1);
		Map<String, Object>map=new HashMap<String, Object>();
		try {
			map=selectEmpPower.selectEmpPower(emp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(map);
	}
}
