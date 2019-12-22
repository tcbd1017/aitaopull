package cn.kgc.tangcco.tcbd1017.on.system.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.tcbd1017.on.pojo.Emp;
import cn.kgc.tangcco.tcbd1017.on.system.UpdateEmpAllInfo;

/**
 * 
 * @author zhangmiao
 * @version 2019年12月16日 上午10:10:10
 *
 */
public class UpdateEmpAllInfoImpl implements UpdateEmpAllInfo {

	@Override
	public int updateEmpAllInfo(Map<String, Object> map) throws SQLException {

		Connection conn = BaseDBUtils.getConnection();

		StringBuilder sql = new StringBuilder(
				"UPDATE 0301_emp , 030100_emp_and_dept , 030101_emp_and_emprole , 030102_emprole , 030103_emprole_and_emppower \n"
						+ ", 030104_emppower , 0302_dept , 030201_dept_and_deptrole  , 030202_deptrole , 030203_deptrole_and_deptpower \n"
						+ ", 030204_deptpower , 030301_emp_info SET 0301_emp.emp_id = ? ");
		List<Object> list = new ArrayList<Object>();

		if (map.containsKey("emp")) {
			System.out.println(((Emp) map.get("emp")));
			String emp_idsString = Integer.toString(((Emp) map.get("emp")).getEmp_id());
			list.add(Integer.toString(((Emp) map.get("emp")).getEmp_id()));

		}
		// 176ad56f7b44e4a778c4e5298e84d3f461687c616ab8f4
		if (map.containsKey("emp_uuid")) {
			sql.append(" , 0301_emp.emp_uuid= ? ");
			if (map.get("emp_uuid") != null) {
				list.add(map.get("emp_uuid").toString());
			} else {
				list.add(" ");
			}
		}
		if (map.containsKey("emp_name")) {
			sql.append(" , 0301_emp.emp_name= ? ");
			if (map.get("emp_name") != null) {
				list.add(map.get("emp_name").toString());
			} else {
				list.add(" ");
			}
		}

		if (map.containsKey("emp_mobile")) {
			sql.append(" , emp_mobile= ? ");
			if (map.get("emp_mobile") != null) {
				list.add(map.get("0301_emp.emp_mobile").toString());
			} else {
				list.add(" ");
			}
		}

		if (map.containsKey("emp_mail")) {
			sql.append(" , 0301_emp.emp_mail= ? ");
			if (map.get("emp_mail") != null) {
				list.add(map.get("emp_mail").toString());
			} else {
				list.add(" ");
			}

		}
		if (map.containsKey("emp_creat_time")) {
			sql.append(" , 0301_emp.emp_creat_time= ? ");
			if (map.get("emp_creat_time") != null) {
				list.add(map.get("emp_creat_time").toString());
			} else {
				list.add((((Emp) map.get("emp")).getEmp_creat_time()));
			}

		}

		if (map.containsKey("emp_update_time")) {
			sql.append(" , 0301_emp.emp_update_time= ? ");
			if (map.get("emp_update_time") != null) {
				list.add(map.get("emp_update_time").toString());
			} else {
				list.add((((Emp) map.get("emp")).getEmp_update_time()));
			}

		}
		if (map.containsKey("emp_status")) {
			sql.append(" , 0301_emp.emp_status= ? ");
			if (map.get("emp_status") != null) {
				list.add(map.get("emp_status").toString());
			} else {
				list.add(" ");
			}

		}
		if (map.containsKey("emp_info_gender")) {
			sql.append(" , 030301_emp_info.emp_info_gender= ? ");
			if (map.get("emp_info_gender") != null) {
				list.add(map.get("emp_info_gender").toString());
			} else {
				list.add(" ");
			}

		}
		if (map.containsKey("emp_info_idcard")) {
			sql.append(" , 030301_emp_info.emp_info_idcard= ? ");
			if (map.get("emp_info_idcard") != null) {
				list.add(map.get("emp_info_idcard").toString());
			} else {
				list.add(" ");
			}

		}
		if (map.containsKey("emp_info_idcard_name")) {
			sql.append(" , 030301_emp_info.emp_info_idcard_name= ? ");
			if (map.get("emp_info_idcard_name") != null) {
				list.add(map.get("emp_info_idcard_name").toString());
			} else {
				list.add(" ");
			}

		}
		if (map.containsKey("emp_info_birthday")) {
			sql.append(" , 030301_emp_info.emp_info_birthday= ? ");
			if (map.get("emp_info_birthday") != null) {
				list.add(map.get("emp_info_birthday").toString());
			} else {
				list.add(" ");
			}

		}
		if (map.containsKey("emp_info_icon_url")) {
			sql.append(" , 030301_emp_info.emp_info_icon_url= ? ");
			if (map.get("emp_info_icon_url") != null) {
				list.add(map.get("emp_info_icon_url").toString());
			} else {
				list.add(" ");
			}

		}
		if (map.containsKey("emp_info_address")) {
			sql.append(" , 030301_emp_info.emp_info_address= ? ");
			if (map.get("emp_info_address") != null) {
				list.add(map.get("emp_info_address").toString());
			} else {
				list.add(" ");
			}

		}

		sql.append(" where 1=1 and 0301_emp.emp_id= ? ");
		list.add(Integer.toString(((Emp) map.get("emp")).getEmp_id()));
		PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
		Object[] param = list.toArray();
		int executeUpdate = BaseDBUtils.executeUpdate(pst, param);

		return executeUpdate;
	}

}
