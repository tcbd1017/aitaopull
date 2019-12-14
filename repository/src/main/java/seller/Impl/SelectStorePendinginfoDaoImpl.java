package cn.kgc.tangcco.tcbd1017.on.seller.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
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
	public int selectOrder() {
		int select = selectOrderPending();
		int update = updateOrder();
		StringBuilder sql = new StringBuilder(
				" select COUNT(order_status) as count from 0109_order where order_status= 3 ");
		Connection conn;
		PreparedStatement pst;
		ResultSet rs;
		int count = -1;
		if (select == update) {

			try {
				conn = BaseDBUtils.getConnection();
				pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
				rs = BaseDBUtils.executeQuery(pst);
				while (rs.next()) {
					count = rs.getInt("count");

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return count;
	}

	@Override
	public int selectOrderPending() {
		StringBuilder sql = new StringBuilder(
				" select COUNT(order_status) as count from 0109_order where order_status= 2 ");
		Connection conn;
		PreparedStatement pst;
		ResultSet rs;
		int count = 0;
		try {
			conn = BaseDBUtils.getConnection();
			pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
			rs = BaseDBUtils.executeQuery(pst);
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
	public int updateOrder() {
		StringBuilder sql = new StringBuilder(" UPDATE 0109_order SET order_status= 3  WHERE order_status= 2 ");

		Connection conn;
		PreparedStatement pst;
		// ResultSet rs;
		int count = 0;
		try {
			conn = BaseDBUtils.getConnection();
			pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
			count = BaseDBUtils.executeUpdate(pst);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return count;

	}

}
