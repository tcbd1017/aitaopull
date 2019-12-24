package cn.kgc.tangcco.tcbd1017.st.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.tcbd1017.st.FunctionDao;
import cn.kgc.tangcco.tcbd1017.st.pojo.Function;

/**
* @author 作者 :牛伟
* @version 创建时间：2019年12月18日 下午7:31:47
* 类说明
*/
public class FunctionDaoImpl implements FunctionDao{

	@Override
	public List<Function> selectGongNeng(int emp_id) throws SQLException {
		List<Function> list = new ArrayList<Function>();
		Function function = null;
		StringBuilder sql = new StringBuilder(" select f.function_id,f.function_name,f.function_path ");
		sql.append(" from emp as e inner join role as r inner join function as f where 1 = 1  ");
		sql.append(" and e.emp_role_id = r.role_id ");
		sql.append(" and r.role_id = f.function_role_id ");
		sql.append(" and e.emp_id = ? ");
		Connection conn = BaseDBUtils.getConnection();	
		Object[] param = {emp_id};
		PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
		ResultSet rs = BaseDBUtils.executeQuery(pst,param);
		while (rs.next()) {
			int function_id = rs.getInt("function_id");
			String function_name = rs.getString("function_name");
			String function_path = rs.getString("function_path");
			function = new Function();
			function.setFunctionId(function_id);
			function.setFunctionName(function_name);
			function.setFunctionPath(function_path);
			list.add(function);
		}
		return list;
	}

}
