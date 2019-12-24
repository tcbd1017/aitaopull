package cn.kgc.tangcco.tcbd1017.st.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.tcbd1017.st.typeDao;
import cn.kgc.tangcco.tcbd1017.st.pojo.Type;

public class TypeDaoImpl implements typeDao{

	@Override
	public List<Type> selectLeiXing() {
		StringBuffer sql=new StringBuffer();
		sql.append(" select * from type ");
		List<Type>list=new ArrayList<Type>();
		Type dype=null;
		try {
			Connection connection = BaseDBUtils.getConnection();
			PreparedStatement preparedStatement = BaseDBUtils.getPreparedStatement(connection, sql.toString());
			ResultSet executeQuery = BaseDBUtils.executeQuery(preparedStatement);
			if(executeQuery!=null) {
				while (executeQuery.next()) {
					dype=new Type();
					dype.setTypeId(executeQuery.getInt("type_id"));
					dype.setName(executeQuery.getString("type_name"));
					list.add(dype);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public Type selectLeiXingById(int id) {
		StringBuffer sql=new StringBuffer();
		sql.append(" select * from type where type_id =? ");
		
		Type dype=null;
		try {
			Connection connection = BaseDBUtils.getConnection();
			PreparedStatement preparedStatement = BaseDBUtils.getPreparedStatement(connection, sql.toString());
			ResultSet executeQuery = BaseDBUtils.executeQuery(preparedStatement,id);
			if(executeQuery!=null) {
				while (executeQuery.next()) {
					dype=new Type();
					dype.setTypeId(executeQuery.getInt("type_id"));
					dype.setName(executeQuery.getString("type_name"));
					
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dype;
	}

}
