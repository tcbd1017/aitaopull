package cn.kgc.tangcco.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.nntp.NewGroupsOrNewsQuery;

import cn.kgc.tangcco.dao.EmpDao;
import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.pojo.Emp;
import cn.kgc.tangcco.pojo.Function;
import cn.kgc.tangcco.pojo.Role;

public class EmpDaoImpl implements EmpDao {
	@Override
	public List<Object> loginEmp(Map<String, Object> map) {
		Map<String, Object> info = new HashMap<String, Object>();
		List empLoginList = new ArrayList();
		try {
			StringBuilder sql = new StringBuilder(
					"SELECT distinct emp_id,emp_name,emp_phone,emp_gender,emp_role_id,function_name,function_path,role_name from emp ");
			sql.append(" INNER JOIN role INNER JOIN function on 1 = 1 ");
			sql.append(" and emp_role_id = role_id ");
			sql.append(" and function_role_id = role_id ");
			sql.append(" and emp_account = ? ");
			sql.append(" and emp_password = ? ");
			List list = new ArrayList();
			if (!StringUtils.isEmpty(map.get("emp_account").toString())
					&& !StringUtils.isEmpty(map.get("emp_password").toString())) {
				list.add(map.get("emp_account"));
				list.add(map.get("emp_password"));
			}
			Object[] param = list.toArray();
			Connection conn = BaseDBUtils.getConnection();
			PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
			ResultSet rs = BaseDBUtils.executeQuery(pst, param);
			if (rs.first()) {
				rs.previous();
				while (rs.next()) {
					Emp emp = new Emp();
					Function function = new Function();
					Role role = new Role();
					emp.setEmpId(rs.getInt("emp_id"));
					emp.setEmpName(rs.getString("emp_name"));
					emp.setEmpPhone(rs.getString("emp_phone"));
					emp.setEmpGender(rs.getInt("emp_gender"));
					emp.setRole(new Role(rs.getInt("emp_role_id")));
					function.setFunctionName(rs.getString("function_name"));
					function.setFunctionPath(rs.getString("function_path"));
					role.setRoleName(rs.getString("role_name"));
					empLoginList.add(emp);
					empLoginList.add(function);
					empLoginList.add(role);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return empLoginList;
	}

	@Override
	public List<Emp> selectAllEmp(Map<String, Object> map) {
		List list = new ArrayList();
		try {
			StringBuilder sql = new StringBuilder("SELECT emp_name FROM emp INNER JOIN role on 1 = 1 ");
			sql.append(" and role_id <= 2 ");
			sql.append(" and emp_role_id = role_id ");
			Object[] param = null;
			Connection conn = BaseDBUtils.getConnection();
			PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
			ResultSet rs = BaseDBUtils.executeQuery(pst, param);
			if (rs.first()) {
				rs.previous();
				while (rs.next()) {
					Emp emp = new Emp();
					emp.setEmpName(rs.getString("emp_name"));
					list.add(emp);
//					list.add(rs.getString("role_name"));
					
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}
