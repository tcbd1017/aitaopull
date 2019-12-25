package cn.kgc.tangcco.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import cn.kgc.tangcco.dao.brandDao;
import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.pojo.Brand;
import cn.kgc.tangcco.pojo.Model;

public class BrandDaoImpl implements brandDao{

	@Override
	public List<Brand> selectPinPai(Map<String, Object> map) {
		StringBuffer sql=new StringBuffer();
		List sList=new ArrayList<>();
		sql.append(" select * from brand where 1=1  ");
		if(map.containsKey("type") && map.get("type")!=null) {
			sql.append("  and brand_type_id=? ");
			sList.add(map.get("type"));
		}
		Object[] array = sList.toArray();
		List<Brand>list=new ArrayList<>();
		Brand brand=null;
		try {
			
			Connection connection = BaseDBUtils.getConnection();
			PreparedStatement preparedStatement = BaseDBUtils.getPreparedStatement(connection, sql.toString());
			ResultSet executeQuery = BaseDBUtils.executeQuery(preparedStatement, array);
			
			if(executeQuery!=null) {
				while (executeQuery.next()) {
					brand=new Brand();
					brand.setBrandId(executeQuery.getInt("brand_id"));
					brand.setBrandName(executeQuery.getString("brand_name"));
					
					list.add(brand);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return list;
	}
	

	@Override
	public Brand selectPinPaiByid(int id) {
		StringBuffer sql=new StringBuffer();
		sql.append(" select * from brand where 1=1 ");
		sql.append(" and brand_id=? ");
		
		Brand brand=null;
		try {
			
			Connection connection = BaseDBUtils.getConnection();
			PreparedStatement preparedStatement = BaseDBUtils.getPreparedStatement(connection, sql.toString());
			ResultSet executeQuery = BaseDBUtils.executeQuery(preparedStatement, id);
			if(executeQuery!=null) {
				while (executeQuery.next()) {
					brand=new Brand();
					brand.setBrandId(executeQuery.getInt("brand_id"));
					brand.setBrandName(executeQuery.getString("brand_name"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return brand;
	}

}
