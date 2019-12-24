package cn.kgc.tangcco.tcbd1017.on.buyer.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.tcbd1017.on.buyer.PostageInfoDao;
import cn.kgc.tangcco.tcbd1017.on.pojo.Address;
import cn.kgc.tangcco.tcbd1017.on.pojo.PostageInfo;

/**
 * 
 * @author 刘凯
 * @version 1.0 <br>
 *          创建时间:2019年12月10日 下午2:32:21 <br>
 *          类描述:收货人信息
 */

public class PostageInfoDaoImpl implements PostageInfoDao {

	@Override
	public List<PostageInfo> selectPostageInfosByBuyerId(Map<String, Object> map) throws SQLException {
		StringBuffer sql = new StringBuffer(

				" SELECT p.postage_info_postcode, p.`postage_info_id`,p.`postage_info_name`,p.`postage_info_mobile`,p.postage_info_province_id,p.postage_info_city_id,p.postage_info_district_id ");
		sql.append(
				" ,p.`postage_info_address`,p.postage_info_create_time,p.`postage_info_update_time`,p.`postage_info_status`,p.`postage_info_uuid`  ");
		sql.append(
				" FROM 0104_buyer_and_postage_info AS bp INNER JOIN 010401_postage_info AS p  INNER JOIN 0101_buyer AS b ");
		sql.append(" ON bp.`buyer_id` = b.`buyer_id` ");
		sql.append(" AND bp.`postage_info_id` = p.`postage_info_id` ");
		sql.append(" AND b.`buyer_status` = 2 ");
		sql.append(" AND p.`postage_info_status` > 1  ");
		sql.append(" AND b.`buyer_id` = ? ");
		sql.append(" ORDER BY p.`postage_info_status` desc  ");
		sql.append(" ,p.postage_info_create_time desc  ");
		sql.append(" limit 0 ,10 ");
		List<Object> list = new ArrayList<Object>();
		if (!ObjectUtils.isEmpty(map.get("buyer_id"))) {
			list.add(map.get("buyer_id"));
		}
		Object[] param = list.toArray();
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<PostageInfo> postageInfos = null;
		conn = BaseDBUtils.getConnection();
		pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
		rs = BaseDBUtils.executeQuery(pst, param);
		if (rs.first()) {
			rs.previous();
			postageInfos = new ArrayList<PostageInfo>();
			while (rs.next()) {
				postageInfos.add(new PostageInfo(rs.getInt("postage_info_id"), rs.getString("postage_info_name"),
						rs.getString("postage_info_mobile"), rs.getInt("postage_info_province_id"),
						rs.getInt("postage_info_city_id"), rs.getInt("postage_info_district_id"),
						rs.getString("postage_info_address"), rs.getTimestamp("postage_info_create_time"),
						rs.getTimestamp("postage_info_update_time"), rs.getInt("postage_info_status"),
						rs.getString("postage_info_uuid"), rs.getString("postage_info_postcode")));
			}
		}
		return postageInfos;
	}

	@Override
	public Address selectAddress(Map<String, Object> map) throws SQLException {
		StringBuffer sql = new StringBuffer(" SELECT address_id,address_parent_id,address_name FROM `04_address` ");
		sql.append(" where 1=1 ");
		List<Object> list = new ArrayList<Object>();
		if (map.containsKey("address_id") && !ObjectUtils.isEmpty(map.get("address_id"))) {
			sql.append(" and address_id = ? ");
			list.add(map.get("address_id"));
		}
		Object[] param = list.toArray();
		Address address = null;
		Connection conn = BaseDBUtils.getConnection();
		PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
		ResultSet rs = BaseDBUtils.executeQuery(pst, param);
		if (rs.first()) {
			rs.previous();
			while (rs.next()) {
				address = new Address(rs.getInt("address_id"), rs.getInt("address_parent_id"),
						rs.getString("address_name"));
			}
		}
		return address;
	}

	@Override
	public List<Address> selectAllAddress(Map<String, Object> map) throws SQLException {
		StringBuffer sql = new StringBuffer(" SELECT address_id,address_parent_id,address_name FROM `04_address` ");
		sql.append(" where 1=1 ");
		List<Object> list = new ArrayList<Object>();
		if (map.containsKey("address_parent_id") && !ObjectUtils.isEmpty(map.get("address_parent_id"))) {
			sql.append(" and address_parent_id = ? ");
			list.add(map.get("address_parent_id"));
		}
		Object[] param = list.toArray();
		List<Address> addressList = null;
		Connection conn = BaseDBUtils.getConnection();
		PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
		ResultSet rs = BaseDBUtils.executeQuery(pst, param);
		if (rs.first()) {
			rs.previous();
			addressList = new ArrayList<Address>();
			while (rs.next()) {
				addressList.add(new Address(rs.getInt("address_id"), rs.getInt("address_parent_id"),
						rs.getString("address_name")));
			}
		}
		return addressList;
	}

	@Override
	public int insertPostageInfo(Map<String, Object> map) throws SQLException {
		StringBuffer sql = new StringBuffer(" insert into 010401_postage_info  ");
		sql.append(
				" (postage_info_name,postage_info_mobile,postage_info_province_id,postage_info_city_id,postage_info_district_id, ");
		sql.append(
				" postage_info_address,postage_info_create_time,postage_info_update_time,postage_info_status,postage_info_uuid,postage_info_postcode) ");
		sql.append(" select ?,?,?,?,?,?,now(),now(),?,?,? from dual ");
		sql.append(
				" where not EXISTS (select postage_info_uuid FROM 010401_postage_info where postage_info_uuid = ? ) ");
		List<Object> list = new ArrayList<Object>();
		list.add(map.get("postage_info_name"));
		list.add(map.get("postage_info_mobile"));
		list.add(map.get("postage_info_province_id"));
		list.add(map.get("postage_info_city_id"));
		list.add(map.get("postage_info_district_id"));
		list.add(map.get("postage_info_address"));
		list.add(map.get("postage_info_status"));
		list.add(map.get("postage_info_uuid"));
		list.add(map.get("postage_info_postcode"));
		list.add(map.get("postage_info_uuid"));
		Object[] param = list.toArray();
		Connection conn = BaseDBUtils.getConnection();
		PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
		int count = BaseDBUtils.executeUpdate(pst, param);
		return count;

	}

	@Override
	public int insertbuyerAndPostageInfo(Map<String, Object> map) throws SQLException {
		StringBuffer sql = new StringBuffer(
				" insert into 0104_buyer_and_postage_info (buyer_id,postage_info_id) VALUES(?, ");
		sql.append(" (SELECT  postage_info_id from 010401_postage_info where postage_info_uuid = ?)) ");
		List<Object> list = new ArrayList<Object>();
		list.add(map.get("buyer_id"));
		list.add(map.get("postage_info_uuid"));
		Object[] param = list.toArray();
		Connection conn = BaseDBUtils.getConnection();
		PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
		int count = BaseDBUtils.executeUpdate(pst, param);
		return count;

	}

	@Override
	public int updatePostageInfo(Map<String, Object> map) throws SQLException {
		StringBuffer sql = new StringBuffer(" update  010401_postage_info set  ");
		sql.append(" postage_info_name = ?, ");
		sql.append("  postage_info_mobile = ?, ");
		sql.append("  postage_info_province_id=?, ");
		sql.append("  postage_info_city_id = ?,  ");
		sql.append(" postage_info_district_id = ?, ");
		sql.append("  postage_info_address = ?,  ");
		sql.append(" postage_info_update_time = now(), ");
		sql.append(" postage_info_status = ?,");
		sql.append(" postage_info_postcode = ? ");
		sql.append(" where 1=1  ");
		sql.append(" and postage_info_id = ?  ");
		List<Object> list = new ArrayList<Object>();
		list.add(map.get("postage_info_name"));
		list.add(map.get("postage_info_mobile"));
		list.add(map.get("postage_info_province_id"));
		list.add(map.get("postage_info_city_id"));
		list.add(map.get("postage_info_district_id"));
		list.add(map.get("postage_info_address"));
		list.add(map.get("postage_info_status"));
		list.add(map.get("postage_info_postcode"));
		list.add(map.get("postage_info_id"));
		Object[] param = list.toArray();
		Connection conn = BaseDBUtils.getConnection();
		PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
		int count = BaseDBUtils.executeUpdate(pst, param);
		return count;
	}

	@Override
	public int updatePostageInfosByStatus(Map<String, Object> map) throws SQLException {
		StringBuffer sql = new StringBuffer(" update  010401_postage_info set ");
		sql.append(" postage_info_status = ? ");
		sql.append(" where 1=1  ");
		sql.append(" and postage_info_id = ?  ");
		List<Object> list = new ArrayList<Object>();
		System.out.println(ObjectUtils.isEmpty(map.get("postageInfo")));
		if (!ObjectUtils.isEmpty(map.get("postageInfo"))) {
			PostageInfo postageInfo = (PostageInfo) map.get("postageInfo");
			list.add(postageInfo.getPostage_info_status());
			list.add(postageInfo.getPostage_info_id());
		} else {
			list.add(map.get("postage_info_status"));
			list.add(map.get("postage_info_id"));
		}
		Object[] param = list.toArray();
		for (Object object : param) {
			System.out.println(object);
		}
		Connection conn = BaseDBUtils.getConnection();
		PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
		int count = BaseDBUtils.executeUpdate(pst, param);
		return count;
	}

	@Override
	public PostageInfo selectPostageInfoByStatus(Map<String, Object> map) throws SQLException {
		StringBuffer sql = new StringBuffer(
				" select p.* from 010401_postage_info as p inner join 0101_buyer as b inner join 0104_buyer_and_postage_info as bp ");
		sql.append(" on 1=1 ");
		sql.append(" and p.postage_info_status = 3 ");
		sql.append(" and b.buyer_id = ? ");
		sql.append(" and bp.`buyer_id` = b.`buyer_id` ");
		sql.append(" AND bp.`postage_info_id` = p.`postage_info_id` ");
		sql.append(" AND b.`buyer_status` = 2 ");
		Connection conn = BaseDBUtils.getConnection();
		PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
		ResultSet rs = BaseDBUtils.executeQuery(pst, map.get("buyer_id"));
		PostageInfo postageInfo = null;
		while (rs.next()) {
			postageInfo = new PostageInfo(rs.getInt("p.postage_info_id"), rs.getString("p.postage_info_id"),
					rs.getString("p.postage_info_mobile"), rs.getInt("p.postage_info_province_id"),
					rs.getInt("p.postage_info_city_id"), rs.getInt("p.postage_info_district_id"),
					rs.getString("p.postage_info_address"), rs.getTimestamp("p.postage_info_create_time"),
					rs.getTimestamp("p.postage_info_update_time"), rs.getInt("p.postage_info_status"),
					rs.getString("p.postage_info_uuid"), rs.getString("p.postage_info_postcode"));
		}
		return postageInfo;
	}

	@Override
	public int selectCountPostageInfoByBuyerId(Map<String, Object> map) throws SQLException {
		StringBuffer sql = new StringBuffer(
				" select count(p.postage_info_id) from 010401_postage_info as p inner join 0101_buyer as b inner join 0104_buyer_and_postage_info as bp ");
		sql.append(" on 1=1 ");
		sql.append(" and p.postage_info_status > 1 ");
		sql.append(" and b.buyer_id = ? ");
		sql.append(" and bp.`buyer_id` = b.`buyer_id` ");
		sql.append(" AND bp.`postage_info_id` = p.`postage_info_id` ");
		sql.append(" AND b.`buyer_status` = 2 ");
		Connection conn = BaseDBUtils.getConnection();
		PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
		ResultSet rs = BaseDBUtils.executeQuery(pst, map.get("buyer_id"));
		int count = 0;
		while (rs.next()) {
			count = rs.getInt("count(p.postage_info_id)");
		}
		return count;
	}

	@Override
	public Map<String, Object> selectOrderByOrderUuid(Map<String, Object> map) throws SQLException {
		StringBuffer sql = new StringBuffer(" SELECT * from 0109_order as o ");
		sql.append(" INNER JOIN 0109_order_goods as og ");
		sql.append(" INNER JOIN 0203_goods as g ");
		sql.append(" INNER JOIN 020301_goods_picture_url as gpu ");
		sql.append(" INNER JOIN 0201_seller as se ");
		sql.append(" INNER JOIN 0207_store as st ");
		sql.append(" INNER JOIN 0101_buyer as b ");
		sql.append(" INNER JOIN 0104_buyer_and_postage_info as bap ");
		sql.append(" INNER JOIN 010401_postage_info as p ");
		sql.append(" on 1=1 ");
		sql.append(" and o.order_uuid = ? ");
		sql.append(" and o.order_id = og.order_id ");
		sql.append(" and g.goods_id = og.goods_id ");
		sql.append(" and gpu.goods_picture_url_id = g.goods_picture_url_id ");
		sql.append(" and se.seller_id = og.seller_id ");
		sql.append(" and st.store_id = se.store_id ");
		sql.append(" and b.buyer_id = o.buyer_id ");
		sql.append(" and b.buyer_id = bap.buyer_id ");
		sql.append(" and p.postage_info_id = bap.postage_info_id ");
		sql.append(" and p.postage_info_status = 3 ");
		Connection conn = BaseDBUtils.getConnection();
		PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
		ResultSet rs = BaseDBUtils.executeQuery(pst, map.get("order_uuid"));
		Map<String, Object> info = rsToMap(rs);
		return info;
	}

	private Map<String, Object> rsToMap(ResultSet rs) {
		Map<String, Object> info = null;
		try {
			if (rs.first()) {
				rs.previous();
				info = new HashMap<String, Object>();
				// 获取总列数
				int columnCount = rs.getMetaData().getColumnCount();
				while (rs.next()) {
					// 遍历每一列,拿出列名和数据
					for (int i = 1; i <= columnCount; i++) {
						String columnLabel = rs.getMetaData().getColumnLabel(i);
						Object value = rs.getObject(i);
						info.put(columnLabel, value);
					}
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return info;
	}

	/**
	 * 用来处理结果集的工具
	 * 
	 * @param rs 用SQL语句查询出的结果集
	 * @return 包含每个字段值的一个list
	 */
	private List<Map<String, Object>> rsToList(ResultSet rs) {
		List<Map<String, Object>> list = null;
		if (rs == null) {
			return list;
		}
		try {
			list = new ArrayList<Map<String, Object>>();
			// 获取总列数
			int columnCount = rs.getMetaData().getColumnCount();
			while (rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				// 遍历每一列,拿出列名和数据
				for (int i = 1; i <= columnCount; i++) {
					String columnLabel = rs.getMetaData().getColumnLabel(i);
					Object value = rs.getObject(i);
					map.put(columnLabel, value);
				}
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Map<String, Object> selectSellerByBuyer_id(Map<String, Object> map) throws SQLException {
		StringBuffer sql = new StringBuffer(" select * from 0101_buyer as b INNER JOIN 0201_seller as s  ");
		sql.append(" on 1=1 ");
		sql.append(" and b.buyer_id = ? ");
		sql.append(" and b.buyer_id = s.buyer_id ");
		Connection conn = BaseDBUtils.getConnection();
		PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
		ResultSet rs = BaseDBUtils.executeQuery(pst, map.get("buyer_id"));
		Map<String, Object> info = rsToMap(rs);
		return info;
	}
}
