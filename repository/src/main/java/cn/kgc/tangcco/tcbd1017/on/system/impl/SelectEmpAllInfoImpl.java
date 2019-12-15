package cn.kgc.tangcco.tcbd1017.on.system.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.tcbd1017.on.pojo.Emp;
import cn.kgc.tangcco.tcbd1017.on.pojo.EmpAllInfo;
import cn.kgc.tangcco.tcbd1017.on.system.SelectEmpAllInfo;

/**
 * 
 * @author zhangmiao
 * @version 2019年12月14日 下午7:35:03
 *
 */
public class SelectEmpAllInfoImpl implements SelectEmpAllInfo {

	@Override
	public EmpAllInfo selectEmpAllInfo(Emp emp) throws SQLException {
		QueryRunner qr = new QueryRunner();

		StringBuilder sql = new StringBuilder(
				"SELECT 0301_emp.emp_id , 0301_emp.emp_uuid , 0301_emp.emp_name , 0301_emp.emp_mobile , 0301_emp.emp_mail , 0301_emp.emp_creat_time , 0301_emp.emp_update_time , 0301_emp.emp_status , \n"
						+ "\n"
						+ "0302_dept.dept_id , 0302_dept.dept_name , 0302_dept.dept_status , 030202_deptrole.deptrole_id , 030202_deptrole.deptrole_name , 030202_deptrole.deptrole_status ,\n"
						+ "030204_deptpower.deptpower_id , 030204_deptpower.deptpower_level , 030204_deptpower.deptpower_status ,\n"
						+ "030102_emprole.emprole_id , 030102_emprole.emprole_name , 030102_emprole.emprole_quantitative_limitation ,030102_emprole.emprole_status , \n"
						+ "030104_emppower.empower_id , 030104_emppower.emppower_level , 030104_emppower.emppower_status ,\n"
						+ "030301_emp_info.emp_info_gender , 030301_emp_info.emp_info_idcard , 030301_emp_info.emp_info_idcard_name , 030301_emp_info.emp_info_birthday , 030301_emp_info.emp_info_icon_url , 030301_emp_info.emp_info_address \n"
						+ "\n"
						+ "FROM 0301_emp INNER JOIN 030100_emp_and_dept INNER JOIN 030101_emp_and_emprole INNER JOIN 030102_emprole INNER JOIN 030103_emprole_and_emppower \n"
						+ "INNER JOIN 030104_emppower INNER JOIN 0302_dept INNER JOIN 030201_dept_and_deptrole  INNER JOIN 030202_deptrole INNER JOIN 030203_deptrole_and_deptpower \n"
						+ "INNER JOIN 030204_deptpower INNER JOIN 030301_emp_info on 1=1 AND 0301_emp.emp_id = 030100_emp_and_dept.emp_id AND 030100_emp_and_dept.dept_id=0302_dept.dept_id \n"
						+ "AND 0302_dept.dept_id=030201_dept_and_deptrole.dept_id AND 030201_dept_and_deptrole.deptrole_id = 030202_deptrole.deptrole_id \n"
						+ "AND 030202_deptrole.deptrole_id = 030203_deptrole_and_deptpower.deptrole_id AND 030203_deptrole_and_deptpower.deptpower_id = 030204_deptpower.deptpower_id \n"
						+ "AND 0301_emp.emp_id = 030101_emp_and_emprole.emp_id AND 030101_emp_and_emprole.emprole_id = 030102_emprole.emprole_id \n"
						+ "AND 030102_emprole.emprole_id = 030103_emprole_and_emppower.emprole_id AND 030103_emprole_and_emppower.emppower_id = 030104_emppower.empower_id \n"
						+ "and 0301_emp.emp_id = ? ");

		Object[] param = { emp.getEmp_id() };
		EmpAllInfo empAllInfo = qr.query(BaseDBUtils.getConnection(), sql.toString(), param,
				new BeanHandler<EmpAllInfo>(EmpAllInfo.class));

		return empAllInfo;
	}

}
