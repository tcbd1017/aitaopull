package cn.kgc.tangcco.tcbd1017.on.system.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.tcbd1017.on.pojo.Emp;
import cn.kgc.tangcco.tcbd1017.on.pojo.EmpLogin;
import cn.kgc.tangcco.tcbd1017.on.system.EmpLoginToEmp;

/**
 * 
 * @author zhangmiao
 * @version	2019年12月14日	下午6:49:17
 *
 */
public class EmpLoginToEmpImpl implements EmpLoginToEmp{

	@Override
	public Emp loginToEmp(EmpLogin empLogin) throws SQLException {	
		QueryRunner qr = new QueryRunner();
		StringBuilder sql = new StringBuilder("SELECT * FROM 0301_emp WHERE 1=1 AND 0301_emp.emp_uuid = (SELECT 0303_emp_login.emp_uuid FROM 0303_emp_login WHERE 0303_emp_login.emp_uuid = ? AND 0303_emp_login.emp_login_status = 2 ) AND 0301_emp.emp_status =2 ");
		Object[] param = {empLogin.getEmp_uuid()};
		Emp query = qr.query(BaseDBUtils.getConnection(), sql.toString(), param, new BeanHandler<Emp>(Emp.class));
		return query;
	}

}
