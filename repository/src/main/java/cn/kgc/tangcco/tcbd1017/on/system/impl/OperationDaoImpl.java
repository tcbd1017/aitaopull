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
import cn.kgc.tangcco.tcbd1017.on.pojo.Emp;
import cn.kgc.tangcco.tcbd1017.on.pojo.Goods;
import cn.kgc.tangcco.tcbd1017.on.pojo.Seller;
import cn.kgc.tangcco.tcbd1017.on.system.OperationDao;

/**
 * @author XUE TONG
 * @version 1.0 2019年12月17日上午8:46:22 </br>
 **/

public class OperationDaoImpl implements OperationDao {

	/**
	 * 查询所有运营部员工
	 */
	@Override
	public List<Emp> selectoperation(Map<String, Object> map) throws SQLException {
		StringBuffer sql = new StringBuffer(" select * from 0301_emp ");
		sql.append(" where 1 = 1  ");
		sql.append(" and 0301_emp.emp_id in ");
		sql.append(" (select 030100_emp_and_dept.emp_id from 030100_emp_and_dept ");
		sql.append(" where 1=1 ");
		sql.append(" and 030100_emp_and_dept.dept_id=3) ");
		List<Emp> newsList = new ArrayList<Emp>();
		List list = new ArrayList();
		Object[] param = list.toArray();
		Connection conn = BaseDBUtils.getConnection();
		PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
		ResultSet rs = BaseDBUtils.executeQuery(pst, param);
		while (rs.next()) {
			newsList.add(new Emp(rs.getInt("emp_id"), rs.getString("emp_uuid"), rs.getString("emp_name"),
					rs.getString("emp_mobile"), rs.getString("emp_mail"), rs.getDate("emp_creat_time"),
					rs.getDate("emp_update_time"), rs.getInt("emp_status")));
		}
		return newsList;
	}

	/**
	 * 查询所有待审核卖家
	 */
	@Override
	public List<Seller> selectSellers(Map<String, Object> map) throws SQLException {
		StringBuffer sql = new StringBuffer(" select * from 0201_seller ");
		sql.append(" where 1 = 1 ");
		sql.append(" and 0201_seller.seller_id not in ");
		sql.append(" (select 020102_seller_principal.seller_id from 020102_seller_principal ");
		sql.append(" where 1=1 ) ");
		sql.append(" and 0201_seller.seller_status = 2 ");
		List<Seller> newsList = new ArrayList<Seller>();
		List list = new ArrayList();
		Object[] param = list.toArray();
		Connection conn = BaseDBUtils.getConnection();
		PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
		ResultSet rs = BaseDBUtils.executeQuery(pst, param);
		while (rs.next()) {
			newsList.add(new Seller(rs.getInt("seller_id"), rs.getInt("buyer_id"), rs.getString("seller_uuid"),
					rs.getString("seller_face_token"), rs.getString("seller_idcard_token"),
					rs.getDate("seller_create_time"), rs.getDate("seller_update_time"), rs.getInt("seller_status"),
					rs.getInt("store_id"), rs.getInt("storage_id"), rs.getInt("logistics_id"),
					rs.getString("seller_icon_url")));
		}
		return newsList;
	}

	/**
	 *  查询待审核卖家的总记录数
	 */
	@Override
	public int selectCountSeller(Map<String, Object> map) throws SQLException {
		List list = new ArrayList();
		StringBuffer sql = new StringBuffer(" select count(seller_id) from 0201_seller ");
		sql.append(" where 1=1 ");
		sql.append(" and 0201_seller.seller_id not in ");
		sql.append(" (select 020102_seller_principal.seller_id from 020102_seller_principal ");
		sql.append(" where 1=1 ) ");
		sql.append(" and 0201_seller.seller_status = 2 ");
		if (!ObjectUtils.isEmpty(map.get("seller_uuid"))) {
			sql.append(" and seller_uuid like ? ");
			list.add("%" + map.get("seller_uuid") + "%");
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
	
	/**
	 * 分配卖家入住商城的所有信息
	 */
	@Override
	public int allocationSeller(Map<String, Object> map) throws SQLException {
		StringBuffer sql = new StringBuffer(" INSERT INTO 020102_seller_principal SELECT 0,?,? FROM DUAL ");
		sql.append(" WHERE NOT EXISTS(SELECT seller_id FROM 020102_seller_principal where seller_id = ?) ");
		Object[] param = { map.get("seller_id"), map.get("principal_id"), map.get("seller_id") };
		Connection conn = BaseDBUtils.getConnection();
		PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
		int count = BaseDBUtils.executeUpdate(pst, param);
		return count;
	}

	/**
	 * 对卖家入住商城的信息的审核
	 */
	@Override
	public int auditSeller(Map<String, Object> map) throws SQLException {
		StringBuffer sql = new StringBuffer(" update 0201_seller set seller_status=? ");
		sql.append(" where 1=1 ");
		sql.append(" and seller_id = ? ");
		Object[] param = { 3, map.get("seller_id") };
		Connection conn = BaseDBUtils.getConnection();
		PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
		int count = BaseDBUtils.executeUpdate(pst, param);
		return count;
	}

	/**
	 * 查询所有待上架商品
	 */
	@Override
	public List<Goods> selectGoods(Map<String, Object> map) throws SQLException {
		StringBuffer sql = new StringBuffer(" select * from 0203_goods ");
		sql.append(" where 1 = 1 ");
		sql.append(" and 0203_goods.goods_id not in ");
		sql.append(" (select 020302_goods_principal.goods_id from 020302_goods_principal ");
		sql.append(" where 1=1 ) ");
		sql.append(" and 0203_goods.goods_status = 2 ");
		List<Goods> newsList = new ArrayList<Goods>();
		List list = new ArrayList();
		Object[] param = list.toArray();
		Connection conn = BaseDBUtils.getConnection();
		PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
		ResultSet rs = BaseDBUtils.executeQuery(pst, param);
		while (rs.next()) {
			newsList.add(new Goods(rs.getInt("goods_id"), rs.getString("goods_uuid"), rs.getDate("goods_create_time"),
					rs.getDate("goods_update_time"), rs.getInt("goods_status"), rs.getInt("goods_picture_url_id"),
					rs.getString("goods_name"), rs.getDouble("goods_price"), rs.getString("goods_brand"),
					rs.getString("goods_type"), rs.getDouble("goods_width"), rs.getDouble("goods_height"),
					rs.getDouble("goods_length"), rs.getString("goods_presentation"), rs.getInt("seller_id"),
					rs.getInt("storage_id"), rs.getDouble("goods_weight")));
		}
		return newsList;
	}

	/**
	 *  查询待上架商品的总记录数
	 */
	@Override
	public int selectCountGoods(Map<String, Object> map) throws SQLException {
		List list = new ArrayList();
		StringBuffer sql = new StringBuffer(" select count(goods_id) from 0203_goods ");
		sql.append(" where 1 = 1 ");
		sql.append(" and 0203_goods.goods_id not in ");
		sql.append(" (select 020302_goods_principal.goods_id from 020302_goods_principal ");
		sql.append(" where 1=1 ) ");
		sql.append(" and 0203_goods.goods_status = 2 ");
		if (!ObjectUtils.isEmpty(map.get("goods_name"))) {
			sql.append(" and goods_name like ? ");
			list.add("%" + map.get("goods_name") + "%");
		}
		Object[] param = list.toArray();
		Connection conn = BaseDBUtils.getConnection();
		PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
		ResultSet rs = BaseDBUtils.executeQuery(pst, param);
		int count = 0;
		if (rs.first()) {
			rs.previous();
			if (rs.next()) {
				count = rs.getInt("count(goods_id)");
			}

		}
		return count;
	}

	/**
	 * 分配上架商品的所有信息
	 */
	@Override
	public int allocationGoods(Map<String, Object> map) throws SQLException {
		StringBuffer sql = new StringBuffer(" INSERT INTO 020302_goods_principal SELECT 0,?,? FROM DUAL ");
		sql.append(" WHERE NOT EXISTS(SELECT goods_id FROM 020302_goods_principal where goods_id = ?) ");
		Object[] param = { map.get("goods_id"), map.get("principal_id"), map.get("goods_id") };
		Connection conn = BaseDBUtils.getConnection();
		PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
		int count = BaseDBUtils.executeUpdate(pst, param);
		return count;
	}

	/**
	 * 对商家要上架的商品的审核
	 */
	@Override
	public int auditGoods(Map<String, Object> map) throws SQLException {
		StringBuffer sql = new StringBuffer(" update 0203_goods set goods_status=? ");
		sql.append(" where 1=1 ");
		sql.append(" and goods_id = ? ");
		Object[] param = { 3, map.get("goods_id") };
		Connection conn = BaseDBUtils.getConnection();
		PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
		int count = BaseDBUtils.executeUpdate(pst, param);
		return count;
	}

	
	
}
