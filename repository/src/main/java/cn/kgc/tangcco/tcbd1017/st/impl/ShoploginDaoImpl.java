package cn.kgc.tangcco.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.lang3.StringUtils;

import cn.kgc.tangcco.dao.ShoploginDao;
import cn.kgc.tangcco.lihaozhe.commons.bianhao.BaseBianHao;
import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.lihaozhe.commons.uuid.BaseUUID;
import cn.kgc.tangcco.pojo.Emp;
import cn.kgc.tangcco.pojo.Function;
import cn.kgc.tangcco.pojo.Role;
import cn.kgc.tangcco.pojo.Shop;

public class ShoploginDaoImpl implements ShoploginDao {
	QueryRunner qr = new QueryRunner();
//	@Override
//	public Map<String, Object> zhuce(Map<String, Object> map) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public int insertShop(Map<String, Object> map) throws SQLException {
		StringBuilder sql = new StringBuilder(
				"INSERT INTO shop (shop_name , shop_uuid , shop_address , shop_account , shop_password, shop_phone, shop_role_id ) ");
		sql.append(" SELECT ?,?,?,?,?,?,4 FROM DUAL ");
		sql.append(" WHERE NOT EXISTS ");
		sql.append(" (SELECT shop_uuid FROM shop WHERE shop_name = ?) ");
		List<Object> list = new ArrayList<Object>();
		/*
		 * list.add(map.get("shop_name")); String generate = BaseUUID.generate();
		 * list.add(generate); list.add(map.get("shop_address")); String
		 * indexOf=BaseBianHao.generateUniqueKey(); list.add(indexOf);
		 * list.add(map.get("shop_password"));
		 * 
		 * list.add(map.get("shop_phone")); list.add(map.get("shop_name"));
		 */
		list.add(map.get("shop_name"));
		list.add(map.get("shop_uuid"));
		list.add(map.get("shop_address"));
		list.add(map.get("shop_account"));
		list.add(map.get("shop_password"));
		list.add(map.get("shop_phone"));
		list.add(map.get("shop_name"));
		Object[] param = list.toArray();
		return qr.update(BaseDBUtils.getConnection(), sql.toString(), param);
	}

	@Override
	public List<Object> loginShop(Map<String, Object> map) {
		List shopLoginList = new ArrayList();
		try {
			StringBuilder sql = new StringBuilder(
					"SELECT shop_id,shop_uuid,shop_name,shop_address,shop_phone,function_name,function_path,role_name from shop ");
			sql.append(" INNER JOIN role INNER JOIN function on 1 = 1 ");
			sql.append(" and shop_role_id = role_id ");
			sql.append(" and function_role_id = role_id ");
			sql.append(" and shop_account = ? ");
			sql.append(" and shop_password = ? ");
			List list = new ArrayList();
			if (map.containsKey("shop_account") && map.containsKey("shop_password")) {
				list.add(map.get("shop_account"));
				list.add(map.get("shop_password"));
			}
			Object[] param = list.toArray();
			Connection conn = BaseDBUtils.getConnection();
			PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
			ResultSet rs = BaseDBUtils.executeQuery(pst, param);
			if (rs.first()) {
				rs.previous();
				while (rs.next()) {
					Shop shop = new Shop();
					Function function = new Function();
					Role role = new Role();
					shop.setShopId(rs.getInt("shop_id"));
					shop.setShopUuid(rs.getString("shop_uuid"));
					shop.setShopName(rs.getString("shop_name"));
					shop.setShopAddress(rs.getString("shop_address"));
					shop.setShopPhone(rs.getString("shop_phone"));
					function.setFunctionName(rs.getString("function_name"));
					function.setFunctionPath(rs.getString("function_path"));
					role.setRoleName(rs.getString("role_name"));
					shop.setF(function);
					shop.setS(shop);
					shopLoginList.add(shop);
					/*
					 * shopLoginList.add(function); shopLoginList.add(role);
					 */
				}
			}
			for (Object object : shopLoginList) {
				System.out.println(object.toString());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return shopLoginList;
	}

	@Override
	public int updateMiMa(Map<String, Object> map) {
		try {
			StringBuilder sql = new StringBuilder("UPDATE shop SET shop_password = ? where 1 = 1 ");
			sql.append(" AND shop_id = ? ");
			List<Object> list = new ArrayList<Object>();
			if (map.containsKey("shop_password") && map.containsKey("shop_id")) {
				list.add(map.get("shop_password"));
				list.add(map.get("shop_id"));
				Object[] param = list.toArray();
				return qr.update(BaseDBUtils.getConnection(), sql.toString(), param);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Object> empLogin(Map<String, Object> map) {
		
		StringBuilder sql = new StringBuilder("SELECT * FROM emp INNER JOIN FUNCTION INNER JOIN role WHERE emp.`emp_role_id`=role.`role_id` AND  role.`role_id`=function.`function_role_id` AND emp_account=? AND emp_password=? ");
		List list = new ArrayList();
		List<Object>list1=new ArrayList<>();
		if (map.containsKey("emp_account") && map.containsKey("emp_password")) {
			list.add(map.get("emp_account"));
			list.add(map.get("emp_password"));
		}
		Object[] param = list.toArray();
		try {
			Connection conn = BaseDBUtils.getConnection();
			PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
			ResultSet rs = BaseDBUtils.executeQuery(pst, param);
			Emp emp =null;
			if (rs.first()) {
				rs.previous();
				while (rs.next()) {
					emp =new Emp();
					Function function = new Function();
					//Role role = new Role();
					emp.setEmpId(rs.getInt("emp_id"));
					emp.setEmpName(rs.getString("emp_name"));
					function.setFunctionName(rs.getString("function_name"));
					function.setFunctionPath(rs.getString("function_path"));
					//role.setRoleName(rs.getString("role_name"));
					emp.setF(function);
					list1.add(emp);
				}
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return list1;
	}

}
