package cn.kgc.tangcco.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.kgc.tangcco.dao.Panduan;
import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;

public class PanDuImpl implements Panduan{

	@Override
	public int pandu(String account) {
		StringBuffer sql=new StringBuffer();
		int a=0;
		sql.append(" SELECT COUNT(*) FROM emp WHERE emp_account=?  ");
		try {
			Connection connection = BaseDBUtils.getConnection();
			PreparedStatement preparedStatement = BaseDBUtils.getPreparedStatement(connection, sql.toString());
			ResultSet executeQuery = BaseDBUtils.executeQuery(preparedStatement, account);
			if(executeQuery!=null) {
				while (executeQuery.next()) {
					a=executeQuery.getInt("count(*)");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return a;
	}
}
