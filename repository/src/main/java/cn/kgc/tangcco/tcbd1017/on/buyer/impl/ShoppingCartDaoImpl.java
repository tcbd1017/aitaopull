package cn.kgc.tangcco.tcbd1017.on.buyer.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.tcbd1017.on.buyer.ShoppingCartDao;
import cn.kgc.tangcco.tcbd1017.on.pojo.ShoppingCart;

/**
	 * @author DU MING
	 * @version 1.0	2019年12月10日	下午4:13:35
	 */

public class ShoppingCartDaoImpl implements ShoppingCartDao{
	QueryRunner qr = new QueryRunner();
	int count = 0;
	
	@Override
	public List<Map<String ,Object>> selectShoppingCartInfoByBuyerId(Map<String, Object> map) {
		List<Map<String ,Object>> list = null;
		
		// 判空说明有问题 直接返回空集
		if (map.isEmpty()) {
			return list;
		}
		
		// 如果有buyerId则进入逻辑
		if ((int)map.get("buyer_id") > 0) {
			list = new ArrayList<Map<String ,Object>>();
			StringBuilder sql = new StringBuilder(" SELECT ");
			sql.append(" g.goods_id, g.goods_uuid, g.goods_create_time, g.goods_update_time, ");
			sql.append(" g.goods_status, g.goods_picture_url_id, g.goods_name, g.goods_price, ");
			sql.append(" g.goods_brand, g.goods_type, g.goods_width, g.goods_height, g.goods_length, ");
			sql.append(" g.goods_presentation, g.seller_id, g.goods_weight, ");
			sql.append(" sc.shopping_cart_id, sc.buyer_id, sc.goods_id, sc.amount_of_goods, ");
			sql.append(" sc.shopping_cart_create_time, sc.shopping_cart_update_time, sc.shopping_cart_status ");
			sql.append(" FROM 0108_shopping_cart AS sc INNER JOIN 0203_goods AS g ON sc.goods_id = g.goods_id ");
			sql.append(" WHERE sc.buyer_id = ? AND sc.shopping_cart_status > 1 ");
			List<Object> params = new ArrayList<Object>();
			params.add(map.get("buyer_id"));
			if ((int)map.get("enableFuzzySelect") > 0) {
				sql.append(" AND g.goods_name LIKE ?");
//				StringBuilder fuzzy = new StringBuilder("%");
//				fuzzy.append((String)map.get("goods_name"));
//				fuzzy.append("%");
//				Object tempParam = fuzzy.toString();
				Object tempParam = "%" + (String)map.get("goods_name") + "%";
				params.add(tempParam);
			}
		
			try {
				Object[] param = params.toArray();
				Connection conn = BaseDBUtils.getConnection();
				PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString().trim());
				ResultSet rs = BaseDBUtils.executeQuery(pst, param);
				
				// 进入逻辑前清空计数器
				count = 0;
				list = this.rsToList(rs);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	@Override
	public int getShoppingCartCount() {
		return count;
	}

	@Override
	public int insertShoppingCart(ShoppingCart shoppingCart) {
		StringBuilder sql = new StringBuilder(" insert into 0108_shopping_cart ");
		sql.append(" select ?, ?, ?, ?, ?, ?, ? from dual");
		sql.append(" where not exists ");
		sql.append(" (select buyer_id, goods_id, shopping_cart_status from 0108_shopping_cart "
				+ "where goods_id = ? and buyer_id = ? and shopping_cart_status > 1)");
		
		Object[] param = {shoppingCart.getShopping_cart_id(), 
				shoppingCart.getBuyer_id(), 
				shoppingCart.getGoods_id(), 
				shoppingCart.getAmount_of_goods(), 
				shoppingCart.getShopping_cart_create_time(), 
				shoppingCart.getShopping_cart_update_time(), 
				shoppingCart.getShopping_cart_status(), 
				shoppingCart.getBuyer_id(), 
				shoppingCart.getGoods_id()};
		// 默认失败
		int result = 0;
		try {
			result =  qr.update(BaseDBUtils.getConnection(), sql.toString(), param);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
		
	}

	@Override
	public int updateShoppingCartInfo(ShoppingCart shoppingCart) {
		// 没有卖家id 和 物品id, 不能定位具体数据
		if ( 0 == shoppingCart.getBuyer_id() || 0 == shoppingCart.getGoods_id()) {
			return 0;
		}
		
		StringBuilder sql1 = new StringBuilder(" update 0108_shopping_cart set ");
		List<Object> list = new ArrayList<Object>();
		if (0 != shoppingCart.getShopping_cart_id()) {
			sql1.append(" shopping_cart_id = ?, ");
			list.add(shoppingCart.getShopping_cart_id());
		}
//		if (0 != shoppingCart.getBuyer_id()) {
//			sql1.append(" buyer_id = ?, ");
//			list.add(shoppingCart.getBuyer_id());
//		}
//		

		if (0 != shoppingCart.getAmount_of_goods()) {
			sql1.append(" amount_of_goods = ?, ");
			list.add(shoppingCart.getAmount_of_goods());
		}
		if (null != shoppingCart.getShopping_cart_create_time()) {
			sql1.append(" shopping_cart_create_time = ?, ");
			list.add(shoppingCart.getShopping_cart_create_time());
		}
		
		if (null != shoppingCart.getShopping_cart_update_time()) {
			sql1.append(" shopping_cart_update_time = ?, ");
			list.add(shoppingCart.getShopping_cart_update_time());
		}
		
		if (2 > shoppingCart.getShopping_cart_status()) {
			sql1.append(" shopping_cart_status = ?, ");
			list.add(shoppingCart.getShopping_cart_status());
		}
		
		// 去SQL两端空格
		String sql1Temp = sql1.toString().trim();
		// 去掉动态SQL的最后一个","
		sql1Temp = sql1Temp.substring(0, sql1Temp.length() - 1);
		
		StringBuilder sql = new StringBuilder(sql1Temp);
		sql.append(" where 1 = 1 ");
		

		sql.append(" and buyer_id = ? and goods_id = ? and shopping_cart_status = 2 ");
		list.add(shoppingCart.getBuyer_id());
		list.add(shoppingCart.getGoods_id());
		System.err.println(sql.toString());
		
		int result = 0;
		try {
			result = qr.update(BaseDBUtils.getConnection(), sql.toString(),list.toArray());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	
	/**
	 * 用来处理结果集的工具
	 * @param rs 用SQL语句查询出的结果集
	 * @return 包含每个字段值的一个list
	 */
	private List<Map<String ,Object>> rsToList(ResultSet rs){
		List<Map<String ,Object>> list = null;
		if(rs==null) {
			return list;
		}
		try {
			list = new ArrayList<Map<String ,Object>>();
			//获取总列数
			int columnCount = rs.getMetaData().getColumnCount();
			while(rs.next()){
				Map<String, Object> map = new HashMap<String, Object>();
				count++;
				// 遍历每一列,拿出列名和数据
				for (int i = 1; i <= columnCount; i++) {
					String columnLabel = rs.getMetaData().getColumnLabel(i);
					Object value = rs.getObject(i);
					map.put(columnLabel,value);
				}
			    list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}


