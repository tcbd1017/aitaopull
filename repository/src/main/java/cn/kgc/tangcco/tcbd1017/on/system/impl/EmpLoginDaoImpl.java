package cn.kgc.tangcco.tcbd1017.on.system.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.tcbd1017.on.pojo.EmpLogin;
import cn.kgc.tangcco.tcbd1017.on.system.EmpLoginDao;

/**
 * @author XUE TONG
 * @version 1.0 2019年12月19日下午2:00:43 </br>
 **/

public class EmpLoginDaoImpl implements EmpLoginDao {

	/**
	 * 员工登录
	 * 
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	@Override
	public EmpLogin selectEmpLoginAccountAndPassword(Map<String, Object> map) throws SQLException {
		StringBuilder sql = new StringBuilder("SELECT * FROM 0303_emp_login  ");
		sql.append(" where 1 = 1 ");
		sql.append(" AND emp_login_account = ? ");
		sql.append(" AND emp_login_password = ? ");
		sql.append(" or emp_login_face_token = ? ");
		Object[] param = { map.get("account"), map.get("password"), map.get("emp_login_face_token") };
		Connection conn = BaseDBUtils.getConnection();
		PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
		ResultSet rs = BaseDBUtils.executeQuery(pst, param);
		EmpLogin empLogin = null;
		if (rs.first()) {
			rs.previous();
			while (rs.next()) {
				empLogin = new EmpLogin(rs.getInt("emp_login_id"), rs.getString("emp_uuid"),
						rs.getString("emp_login_account"), rs.getString("emp_login_password"),
						rs.getString("emp_login_face_token"), rs.getDate("emp_login_create_time"),
						rs.getDate("emp_login_update_time"), rs.getInt("emp_login_status"));
			}
		}
		return empLogin;
	}

}
