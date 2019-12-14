package cn.kgc.tangcco.tcbd1017.on.system.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.tcbd1017.on.pojo.Emp;
import cn.kgc.tangcco.tcbd1017.on.system.SelectEmpPower;

/**
 * 
 * @author zhangmiao
 * @version 2019年12月13日 下午5:02:03
 *
 */
public class SelectEmpPowerImpl implements SelectEmpPower {

	@Override
	public Map<String, Object> selectEmpPower(Emp emp) throws SQLException {
		Connection conn = BaseDBUtils.getConnection();
		Map<String, Object> map = null;

		if (ObjectUtils.isEmpty(emp)) {
			return map;
		}

		map = new HashMap<String, Object>();

		StringBuffer sqlDeptPower = new StringBuffer(
				"SELECT 0301_emp.emp_name , 0301_emp.emp_uuid , 030204_deptpower.deptpower_level FROM 0301_emp INNER JOIN 030100_emp_and_dept INNER JOIN 0302_dept INNER JOIN 030201_dept_and_deptrole  INNER JOIN 030202_deptrole INNER JOIN 030203_deptrole_and_deptpower INNER JOIN 030204_deptpower on 1=1 AND 0301_emp.emp_id = 030100_emp_and_dept.emp_id AND 030100_emp_and_dept.dept_id=0302_dept.dept_id AND 0302_dept.dept_id=030201_dept_and_deptrole.dept_id AND 030201_dept_and_deptrole.deptrole_id = 030202_deptrole.deptrole_id AND 030202_deptrole.deptrole_id = 030203_deptrole_and_deptpower.deptrole_id AND 030203_deptrole_and_deptpower.deptpower_id = 030204_deptpower.deptpower_id AND 0301_emp.emp_id = ? ");
		PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sqlDeptPower.toString());
		Object[] param = { emp.getEmp_id() };
		ResultSet rs = BaseDBUtils.executeQuery(pst, param);
		while (rs.next()) {
			map.put("deptPower", rs.getInt(3));

		}

		StringBuffer sqlEmpPower = new StringBuffer(
				"SELECT 0301_emp.emp_name , 030104_emppower.emppower_level , 0301_emp.emp_id FROM \n" + "\n"
						+ "0301_emp INNER JOIN 030101_emp_and_emprole INNER JOIN 030102_emprole INNER JOIN 030103_emprole_and_emppower INNER JOIN 030104_emppower\n"
						+ "\n"
						+ "ON 0301_emp.emp_id = 030101_emp_and_emprole.emp_id AND 030101_emp_and_emprole.emprole_id = 030102_emprole.emprole_id AND 030102_emprole.emprole_id = 030103_emprole_and_emppower.emprole_id AND 030103_emprole_and_emppower.emppower_id = 030104_emppower.empower_id AND 0301_emp.emp_id =? ");
		PreparedStatement pst2 = BaseDBUtils.getPreparedStatement(conn, sqlEmpPower.toString());
		Object[] param2 = { emp.getEmp_id() };
		ResultSet rs2 = BaseDBUtils.executeQuery(pst2, param2);
		while (rs2.next()) {
			map.put("empPower", rs2.getInt(2));
		}
		return map;
	}

}
