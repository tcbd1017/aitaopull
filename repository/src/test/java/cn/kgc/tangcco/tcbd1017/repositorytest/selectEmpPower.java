package cn.kgc.tangcco.tcbd1017.repositorytest;

import java.sql.SQLException;
import java.util.Map;

import org.junit.Test;

import cn.kgc.tangcco.tcbd1017.on.pojo.Emp;
import cn.kgc.tangcco.tcbd1017.on.system.SelectEmpPower;
import cn.kgc.tangcco.tcbd1017.on.system.impl.SelectEmpPowerImpl;

/**
 * 
 * @author zhangmiao
 * @version	2019年12月13日	下午7:10:17
 *
 */

public class selectEmpPower {
	
	@Test
	public void selectEmpPowerTest() {
		Emp emp = new Emp(1, null, null, null, null, null, null, 2);
		SelectEmpPower sep =new SelectEmpPowerImpl();
		
		try {
			
			Map<String, Object> selectEmpPower = sep.selectEmpPower(emp);
			System.out.println(selectEmpPower.get("deptPower"));
			System.out.println(selectEmpPower.get("empPower"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
