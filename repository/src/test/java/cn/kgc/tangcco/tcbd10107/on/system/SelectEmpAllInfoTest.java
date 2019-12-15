package cn.kgc.tangcco.tcbd10107.on.system;

import java.sql.SQLException;

import org.junit.Test;

import cn.kgc.tangcco.tcbd1017.on.pojo.Emp;
import cn.kgc.tangcco.tcbd1017.on.pojo.EmpAllInfo;
import cn.kgc.tangcco.tcbd1017.on.system.SelectEmpAllInfo;
import cn.kgc.tangcco.tcbd1017.on.system.impl.SelectEmpAllInfoImpl;

/**
 * 
 * @author zhangmiao
 * @version	2019年12月15日	下午12:00:34
 *
 */

public class SelectEmpAllInfoTest {
	@Test
	public void selectEmpAllInfoTest() {
		SelectEmpAllInfo seai = new SelectEmpAllInfoImpl();
		
		try {
			EmpAllInfo EAI = seai.selectEmpAllInfo(new Emp(1, null, null, null, null, null, null, 2));
			System.out.println(EAI);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
