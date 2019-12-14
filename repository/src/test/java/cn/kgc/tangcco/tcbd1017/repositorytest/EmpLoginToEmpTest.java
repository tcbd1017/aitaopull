package cn.kgc.tangcco.tcbd1017.repositorytest;

import java.sql.SQLException;

import org.junit.Test;

import cn.kgc.tangcco.tcbd1017.on.pojo.Emp;
import cn.kgc.tangcco.tcbd1017.on.pojo.EmpLogin;
import cn.kgc.tangcco.tcbd1017.on.system.EmpLoginToEmp;
import cn.kgc.tangcco.tcbd1017.on.system.impl.EmpLoginToEmpImpl;

/**
 * 
 * @author zhangmiao
 * @version	2019年12月14日	下午7:01:16
 *
 */
public class EmpLoginToEmpTest {
	@Test
	public void empLoginToEmpTest() {
		EmpLoginToEmp eltEmp = new EmpLoginToEmpImpl();
		EmpLogin empLogin = new EmpLogin(0, "176ad56f7b44e4a778c4e5298e84d3f461687c616ab8f4", null, null, null, null, null, 2);
		try {
			Emp loginToEmp = eltEmp.loginToEmp(empLogin);
			System.out.println(loginToEmp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
