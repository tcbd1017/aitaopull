package cn.kgc.tangcco.tcbd1017.on.system.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.lihaozhe.commons.jdbc.PageRang;
import cn.kgc.tangcco.tcbd1017.on.pojo.Seller;
import cn.kgc.tangcco.tcbd1017.on.system.ViewSellerDao;

/**
 * @author XUE TONG
 * @version 1.0 2019年12月16日上午9:48:20 </br>
 **/

public class ViewSellerDaoImpl implements ViewSellerDao {

	/**
	 * 查询所有卖家信息
	 */
	@Override
	public List<Seller> selectViewSeller(Map<String, Object> map) throws SQLException {
		StringBuilder sql = new StringBuilder("SELECT * FROM 0201_seller ");
		sql.append(" where 1 = 1  ");
		List<Seller> newsList = new ArrayList<Seller>();
		// 动态sql开始
		List<Object> list = new ArrayList<Object>();
		if (map != null && map.size() > 0) {

			if (map.containsKey("seller_id")) {
				sql.append(" and seller_id = ? ");
				list.add(map.get("seller_id"));
			}
			if (map.containsKey("buyer_id")) {
				sql.append(" and buyer_id = ? ");
				list.add(map.get("buyer_id"));
			}
			if (map.containsKey("seller_uuid")) {
				sql.append(" and seller_uuid = ? ");
				list.add(map.get("seller_uuid"));
			}
			if (map.containsKey("seller_face_token")) {
				sql.append(" and seller_face_token = ? ");
				list.add(map.get("seller_face_token"));
			}
			if (map.containsKey("seller_idcard_token")) {
				sql.append(" and seller_idcard_token = ? ");
				list.add(map.get("seller_idcard_token"));
			}
			if (map.containsKey("seller_create_time")) {
				sql.append(" and seller_create_time = ? ");
				list.add(map.get("seller_create_time").toString());
			}
			if (map.containsKey("seller_update_time")) {
				sql.append(" and seller_update_time = ? ");
				list.add(map.get("seller_update_time").toString());
			}
			if (map.containsKey("seller_status")) {
				sql.append(" and seller_status = ? ");
				list.add(map.get("seller_status"));
			}
			if (map.containsKey("store_id")) {
				sql.append(" and store_id = ? ");
				list.add(map.get("store_id"));
			}
			if (map.containsKey("storage_id")) {
				sql.append(" and storage_id = ? ");
				list.add(map.get("storage_id"));
			}
			if (map.containsKey("logistics_id")) {
				sql.append(" and logistics_id = ? ");
				list.add(map.get("logistics_id"));
			}
			if (map.containsKey("seller_icon_url")) {
				sql.append(" and seller_icon_url = ? ");
				list.add(map.get("seller_icon_url"));
			}
		}
		Object[] param = list.toArray();
		// 动态sql结束
		Connection conn;
		PreparedStatement pst;
		ResultSet rs;
		try {
			conn = BaseDBUtils.getConnection();
			pst = BaseDBUtils.getPreparedStatement(conn, sql.toString(), (PageRang) map.get("pr"));
			rs = BaseDBUtils.executeQuery(pst, param);
			while (rs.next()) {
				newsList.add(new Seller(rs.getInt("seller_id"), rs.getInt("buyer_id"), rs.getString("seller_uuid"),
						rs.getString("seller_face_token"), rs.getString("seller_idcard_token"),
						rs.getDate("seller_create_time"), rs.getDate("seller_update_time"), rs.getInt("seller_status"),
						rs.getInt("store_id"), rs.getInt("storage_id"), rs.getInt("logistics_id"),
						rs.getString("seller_icon_url")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return newsList;

	}

	@Override
	public int selectCountSeller(Map<String, Object> map) throws SQLException {
		List list = new ArrayList();
		StringBuffer sql = new StringBuffer(" select count(seller_id) from 0201_seller ");
		sql.append(" where 1=1 ");
		if (!ObjectUtils.isEmpty(map.get("seller_id"))) {
			sql.append(" and seller_id like ? ");
			list.add("%" + map.get("seller_id") + "%");
		}
		Object[] param = list.toArray();
		Connection conn = BaseDBUtils.getConnection();
		PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
		ResultSet rs = BaseDBUtils.executeQuery(pst, param);
		int count = 0;
		if (rs.first()) {
			rs.previous();
			if (rs.next()) {
				count = rs.getInt("count(seller_id)");
			}

		}
		return count;
	}

}
