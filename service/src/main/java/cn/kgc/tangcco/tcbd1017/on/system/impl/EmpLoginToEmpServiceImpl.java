package cn.kgc.tangcco.tcbd1017.on.system.impl;

import java.sql.SQLException;

import org.apache.commons.lang3.ObjectUtils;

import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.pojo.Emp;
import cn.kgc.tangcco.tcbd1017.on.pojo.EmpLogin;
import cn.kgc.tangcco.tcbd1017.on.system.EmpLoginToEmp;
import cn.kgc.tangcco.tcbd1017.on.system.EmpLoginToEmpService;

/**
 * 
 * @author zhangmiao
 * @version 2019年12月18日 上午11:06:52
 *
 */
public class EmpLoginToEmpServiceImpl implements EmpLoginToEmpService {
	private static EmpLoginToEmp elte;
	static {
		ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(
				"ApplicationContext_on.xml");
		try {
			elte = (EmpLoginToEmp) classPathXmlApplicationContext.getBean(EmpLoginToEmp.class.getSimpleName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Emp empLoginToEmpService(EmpLogin empLogin) {
		Emp emp = null;
		if (!ObjectUtils.isEmpty(empLogin)) {
			try {
				emp= elte.loginToEmp(empLogin);
				return emp;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return emp; 
	}

}
