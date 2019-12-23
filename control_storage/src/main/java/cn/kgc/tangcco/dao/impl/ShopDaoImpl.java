package cn.kgc.tangcco.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.dao.ShopDao;
import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.pojo.Emp;
import cn.kgc.tangcco.pojo.Shop;

public class ShopDaoImpl implements ShopDao {

	@Override
	public List<Shop> selectShop() {
		List<Shop> list = new ArrayList<Shop>();
		try {
			StringBuilder sql = new StringBuilder(
					"SELECT shop_id,shop_name,shop_uuid,shop_address,shop_account,shop_password,shop_phone from shop where 1 = 1 ");
			Object[] param = null;
			Connection conn = BaseDBUtils.getConnection();
			PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
			ResultSet rs = BaseDBUtils.executeQuery(pst, param);
			if (rs.first()) {
				rs.previous();
				while (rs.next()) {
					Shop shop = new Shop();
					shop.setShopId(rs.getInt("shop_id"));
					shop.setShopName(rs.getString("shop_name"));
					shop.setShopUuid(rs.getString("shop_uuid"));
					shop.setShopAddress(rs.getString("shop_address"));
					shop.setShopAccount(rs.getString("shop_account"));
					shop.setShopPassword(rs.getString("shop_password"));
					shop.setShopPhone(rs.getString("shop_phone"));
					list.add(shop);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Shop> selectShopMingZi(Map<String, Object> map) {
		List<Shop> listShop = new ArrayList<Shop>();
		try {
			StringBuilder sql = new StringBuilder("SELECT shop_name from shop where 1 = 1 ");
			sql.append(" AND shop_uuid = ? ");
			List list = new ArrayList();
			list.add(map.get("shop_uuid"));
			Object[] param = list.toArray();
			Connection conn = BaseDBUtils.getConnection();
			PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
			ResultSet rs = BaseDBUtils.executeQuery(pst, param);
			if (rs.first()) {
				rs.previous();
				while (rs.next()) {
					Shop shop = new Shop();
					shop.setShopName(rs.getString("shop_name"));
					listShop.add(shop);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listShop;
	}

	@Override
	public List<Shop> selectShopXiangQing(Map<String, Object> map) {
		List<Shop> listShop = new ArrayList<Shop>();
		List list = new ArrayList();
		try {
			StringBuilder sql = new StringBuilder("SELECT shop_name,shop_uuid,shop_address,shop_account,shop_password,shop_phone from shop where 1 = 1 ");
			if (   map.containsKey("shop_id") &&  map.get("shop_id")!=null ) {
				sql.append(" AND shop_id = ? ");				
				list.add(map.get("shop_id"));
			}
			if (   map.containsKey("shop_name") &&  map.get("shop_name")!=null ) {
				sql.append(" AND shop_name = ? ");				
				list.add(map.get("shop_name"));
			}
			
			Object[] param = list.toArray();
			Connection conn = BaseDBUtils.getConnection();
			PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
			ResultSet rs = BaseDBUtils.executeQuery(pst, param);
			if (rs.first()) {
				rs.previous();
				while (rs.next()) {
					Shop shop = new Shop();
					shop.setShopName(rs.getString("shop_name"));
					shop.setShopUuid(rs.getString("shop_uuid"));
					shop.setShopAddress(rs.getString("shop_address"));
					shop.setShopAccount(rs.getString("shop_account"));
					shop.setShopPassword(rs.getString("shop_password"));
					shop.setShopPhone(rs.getString("shop_phone"));
					listShop.add(shop);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listShop;
	}

	
	
	
	
	
//	@Override
//	public Map<String, Object> insertShop(Map<String, Object> map) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
