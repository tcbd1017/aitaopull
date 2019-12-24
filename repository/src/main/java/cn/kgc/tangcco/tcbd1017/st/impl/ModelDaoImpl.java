package cn.kgc.tangcco.tcbd1017.st.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.tcbd1017.st.modelDao;
import cn.kgc.tangcco.tcbd1017.st.pojo.Model;

public class ModelDaoImpl implements modelDao {

	@Override
	public List<Model> selectXingHao(Map<String, Object> map) {
		StringBuffer sql = new StringBuffer();
		List sList=new ArrayList<>();
		sql.append(" select * from model where 1=1  ");
		if (map.containsKey("model_brand_id") && map.get("model_brand_id")!=null ) {
			sql.append("  and model_brand_id=? ");
			sList.add(map.get("model_brand_id"));
		}
		Object[] array = sList.toArray();
		List<Model> list = new ArrayList<>();
		Model model = null;
		try {

			
			Connection connection = BaseDBUtils.getConnection();
			PreparedStatement preparedStatement = BaseDBUtils.getPreparedStatement(connection, sql.toString());
			ResultSet executeQuery = BaseDBUtils.executeQuery(preparedStatement, array);

			if (executeQuery != null) {
				while (executeQuery.next()) {
					model = new Model();
					model.setModelId(executeQuery.getInt("model_id"));
					model.setModelName(executeQuery.getString("model_name"));
					model.setModelPrice(executeQuery.getDouble("model_price"));
					model.setModelSize(executeQuery.getInt("model_size"));

					list.add(model);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
	
	
	

	@Override
	public Model selectXingHaoById(int id) {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from model where 1=1  ");
		sql.append(" and model_id=? ");
		Model model = null;
		try {


			Connection connection = BaseDBUtils.getConnection();
			PreparedStatement preparedStatement = BaseDBUtils.getPreparedStatement(connection, sql.toString());
			ResultSet executeQuery = BaseDBUtils.executeQuery(preparedStatement, id);

			if (executeQuery != null) {
				while (executeQuery.next()) {
					model = new Model();
					model.setModelId(executeQuery.getInt("model_id"));
					model.setModelName(executeQuery.getString("model_name"));
					model.setModelPrice(executeQuery.getDouble("model_price"));
					model.setModelSize(executeQuery.getInt("model_size"));

					
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return model;
	}

	@Override
	public Double selectPrice( int a, int b,int c) {
		Double aa=0.00;
		StringBuffer sql=new StringBuffer();
		sql.append(" \r\n" + 
				"SELECT  model_price  FROM model,TYPE,brand  WHERE type.`type_id`=brand.`brand_type_id` AND model.`model_brand_id`=brand.`brand_id`\r\n" + 
				"AND type_id=? AND brand_id=? AND model_id=? ");
		Object []param= {a,b,c};
		try {
			Connection connection = BaseDBUtils.getConnection();
			PreparedStatement preparedStatement = BaseDBUtils.getPreparedStatement(connection, sql.toString());
			ResultSet executeQuery = BaseDBUtils.executeQuery(preparedStatement, param);
			while (executeQuery.next()) {
				aa=executeQuery.getDouble("model_price");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return aa;
	}

}
