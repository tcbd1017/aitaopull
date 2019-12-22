package cn.kgc.tangcco.tcbd1017.on.seller.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.lihaozhe.commons.jdbc.Laypage;
import cn.kgc.tangcco.lihaozhe.commons.jdbc.PageRang;
import cn.kgc.tangcco.tcbd1017.on.pojo.Goods;
import cn.kgc.tangcco.tcbd1017.on.pojo.Order;
import cn.kgc.tangcco.tcbd1017.on.seller.SelectStorePendinginfoDao;

/**
 * 
 * @ClassName: SelectStorePendingImpl
 * @Description: TODO(对订单表已付款状态的修改和对所有待发货信息的查询)
 * @author A18ccms a18ccms_gmail_com
 * @date 2019年12月13日 下午5:17:40 * *
 */
public class SelectStorePendinginfoDaoImpl implements SelectStorePendinginfoDao {
	/**
	 * 
	 */
	@Override
	public int selectOrder(Map<String, Object> map) {
		/**
		 * 查询待发货订单数量
		 */
		StringBuilder sql = new StringBuilder(
				" SELECT" + " count(0109_order.order_id) as count " + " FROM" + " 0109_order ," + " 0109_order_goods "
						+ " WHERE " + " 0109_order.order_id = 0109_order_goods.order_id AND "
						+ " 0109_order_goods.seller_id = ? " + " AND 0109_order.order_status = 3 ");
		Connection conn;
		PreparedStatement pst;
		ResultSet rs;
		int count = -1;
		Object[] param = { map.get("seller_id") };
		try {
			conn = BaseDBUtils.getConnection();
			pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
			rs = BaseDBUtils.executeQuery(pst, param);
			while (rs.next()) {
				count = rs.getInt("count");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return count;
	}

	/**
	 * 查询已付款订单数量
	 */
	@Override
	public int selectOrderPending(Map<String, Object> map) {
		StringBuilder sql = new StringBuilder(
				" SELECT" + " count(0109_order.order_id) as count " + " FROM" + " 0109_order ," + " 0109_order_goods "
						+ " WHERE " + " 0109_order.order_id = 0109_order_goods.order_id AND "
						+ " 0109_order_goods.seller_id = ? " + " AND 0109_order.order_status = 2 ");
		Object[] param = { map.get("seller_id") };
		Connection conn;
		PreparedStatement pst;
		ResultSet rs;
		int count = 0;
		try {
			conn = BaseDBUtils.getConnection();
			pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
			rs = BaseDBUtils.executeQuery(pst, param);
			while (rs.next()) {
				count = rs.getInt("count");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return count;
	}

	/**
	 * 根据seller_id查询本店信誉度
	 */
	@Override
	public int SelectreputationDao(Map<String, Object> map) {

		StringBuilder sql = new StringBuilder(
				"SELECT " + " reputation_value " + " FROM " + " 0202_reputation " + " WHERE " + " seller_id = ? ");

		Object[] param = { map.get("seller_id") };
		Connection conn;
		PreparedStatement pst;
		ResultSet rs;
		int count = 0;
		try {
			conn = BaseDBUtils.getConnection();
			pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
			rs = BaseDBUtils.executeQuery(pst, param);
			while (rs.next()) {
				count = rs.getInt("reputation_value");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;

	}

	/**
	 * 查询待发货订单数量
	 */
	@Override
	public int SelectCompleteTransactionDao(Map<String, Object> map) {
		StringBuilder sql = new StringBuilder(
				" SELECT" + " count(0109_order.order_id) as count " + " FROM" + " 0109_order ," + " 0109_order_goods "
						+ " WHERE " + " 0109_order.order_id = 0109_order_goods.order_id AND "
						+ " 0109_order_goods.seller_id = ? " + " AND 0109_order.order_status = 7 ");
		Connection conn;
		PreparedStatement pst;
		ResultSet rs;
		int count = -1;
		Object[] param = { map.get("seller_id") };
		try {
			conn = BaseDBUtils.getConnection();
			pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
			rs = BaseDBUtils.executeQuery(pst, param);
			while (rs.next()) {
				count = rs.getInt("count");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return count;
	}

	@Override
	public double SelectCompleteTransactionMoneyDao(Map<String, Object> map) {
		/**
		 * 查询交易完成的金额
		 */
		StringBuilder sql = new StringBuilder(" SELECT" + " Sum(0109_order.order_payment) as sum " + " FROM "
				+ " 0109_order ," + " 0109_order_goods " + " WHERE " + " 0109_order_goods.seller_id = ? AND "
				+ " 0109_order.order_id = 0109_order_goods.order_id ");
		Connection conn;
		PreparedStatement pst;
		ResultSet rs;
		double sum = -1;
		Object[] param = { map.get("seller_id") };
		try {
			conn = BaseDBUtils.getConnection();
			pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
			rs = BaseDBUtils.executeQuery(pst, param);
			while (rs.next()) {
				sum = rs.getDouble("sum");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sum;
	}

	/**
	 * 查询本店铺所有已上架商品信息
	 */
	@Override
	public List<Goods> SelectGoodsDao(Map<String, Object> map) {
		map.put("laypage",new PageRang((Integer)map.get("page"),(Integer)map.get("limit")));
		List<Goods> goodsList = new ArrayList<Goods>();
		StringBuilder sql = new StringBuilder(
				" SELECT goods_id,goods_uuid,goods_create_time,goods_update_time,goods_status,goods_picture_url_id,goods_name,goods_price,goods_brand,goods_type,goods_width,goods_height,goods_length,goods_presentation,seller_id,storage_id,goods_weight "
						+ " FROM 0203_goods " + " WHERE 1 = 1 and seller_id = ? " + " and goods_status = 3 ");
		// 动态SQL开始
				List<Object> arrayList = new ArrayList<Object>();
				arrayList.add(map.get("seller_id"));
				if (map != null && map.size() > 0) {
					if (map.containsKey("goods_name") && !StringUtils.isEmpty((String) map.get("goods_name"))) {
						sql.append(" and goods_name like ? ");
						arrayList.add("%"+map.get("goods_name")+"%");
					}
					if (map.containsKey("goods_type") && !StringUtils.isEmpty((String) map.get("goods_name"))) {
						sql.append(" and goods_type like ?  ");
						arrayList.add("%"+map.get("goods_type")+"%");
					}
					if (map.containsKey("goods_brand") && !StringUtils.isEmpty((String) map.get("goods_name"))) {
						sql.append(" and goods_brand like ?  ");
						arrayList.add("%"+map.get("goods_brand")+"%");
					}
				}

		Connection conn;
		PreparedStatement pst;
		ResultSet rs;

		Object[] param = arrayList.toArray();
		for (int i = 0; i < param.length; i++) {
			System.out.println("9999"+param[i]);
					}
		try {
			conn = BaseDBUtils.getConnection();
			pst = BaseDBUtils.getPreparedStatement(conn, sql.toString(), (PageRang) map.get("laypage"));//
			rs = BaseDBUtils.executeQuery(pst, param);
			while (rs.next()) {
				Goods goods = new Goods();
				goods.setGoods_id(rs.getInt("goods_id"));
				goods.setGoods_uuid(rs.getString("goods_uuid"));
				goods.setGoods_create_time(rs.getDate("goods_create_time"));
				goods.setGoods_update_time(rs.getDate("goods_update_time"));
				goods.setGoods_status(rs.getInt("goods_status"));
				goods.setGoods_picture_url_id(rs.getInt("goods_picture_url_id"));
				goods.setGoods_name(rs.getString("goods_name"));
				goods.setGoods_price(rs.getDouble("goods_price"));
				goods.setGoods_brand(rs.getString("goods_brand"));
				goods.setGoods_type(rs.getString("goods_type"));
				goods.setGoods_width(rs.getDouble("goods_width"));
				goods.setGoods_height(rs.getDouble("goods_height"));
				goods.setGoods_length(rs.getDouble("goods_length"));
				goods.setGoods_presentation(rs.getString("goods_presentation"));
				goods.setSeller_id(rs.getInt("seller_id"));
				goods.setStorage_id(rs.getInt("storage_id"));
				goods.setGoods_weight(rs.getDouble("goods_weight"));
				goodsList.add(goods);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return goodsList;
	}

	@Override
	public int SelectGoodsNumberDao(Map<String, Object> map) {
		/**
		 * 查询本店铺已上架商品数量
		 */
		StringBuilder sql = new StringBuilder(" SELECT count(goods_id) as count " + " FROM 0203_goods "
				+ " WHERE 1 = 1 and seller_id = ? " + " and goods_status = 3 ");
		// 动态SQL开始
		List<Object> arrayList = new ArrayList<Object>();
		arrayList.add(map.get("seller_id"));
		if (map != null && map.size() > 0) {
			
			if (map.containsKey("goods_name") && map.get("goods_name") != null) {
				sql.append(" and goods_name = ? ");
				arrayList.add("%"+map.get("goods_name")+"%");System.out.println("添加了goods_name");
			}
			if (map.containsKey("goods_type") && map.get("goods_type") != null) {
				sql.append(" and goods_type = ?  ");
				arrayList.add("%"+map.get("goods_type")+"%");System.out.println("添加了goods_type");
			}
			if (map.containsKey("") && map.get("goods_brand") != null) {
				sql.append(" and goods_brand = ?  ");
				arrayList.add("%"+map.get("goods_brand")+"%");System.out.println("添加了goods_brand");
			}
		}

		Connection conn;
		PreparedStatement pst;
		ResultSet rs;
		int count = -1;
		Object[] param = arrayList.toArray();
		for (int i = 0; i < param.length; i++) {
			System.out.println("9999"+param[i]);
		}
		try {
			conn = BaseDBUtils.getConnection();
			pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
			rs = BaseDBUtils.executeQuery(pst, param);
			while (rs.next()) {
				count = rs.getInt("count");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return count;
	}

}
