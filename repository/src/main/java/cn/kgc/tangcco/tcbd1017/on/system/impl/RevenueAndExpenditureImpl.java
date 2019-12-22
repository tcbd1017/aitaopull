package cn.kgc.tangcco.tcbd1017.on.system.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.tcbd1017.on.pojo.Emp;
import cn.kgc.tangcco.tcbd1017.on.system.RevenueAndExpenditure;

/**
* @author 许佳瑞
* @version 创建时间：2019年12月21日 上午10:15:00
* @edition 1.0
* @Description 类描述
*/
public class RevenueAndExpenditureImpl implements RevenueAndExpenditure {

	@Override
	public List<Object> selectRevenue() throws SQLException {
		StringBuilder sql= new StringBuilder(" SELECT sum(shop_income_money)as money,FROM_UNIXTIME(UNIX_TIMESTAMP(shop_income_info_create_time), '%Y-%m-%d') as date FROM 030501_shop_income_info WHERE 1 = 1 ");
		sql.append(" and shop_income_info_status = 2 ");
		sql.append(" GROUP BY FROM_UNIXTIME(UNIX_TIMESTAMP(shop_income_info_create_time), '%Y-%m-%d') ");
		Connection conn = BaseDBUtils.getConnection();
		 PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
	     ResultSet rs = BaseDBUtils.executeQuery(pst);
	     List<Object>list = new ArrayList<Object>();
			// 遍历结果集
			while (rs.next()) {
			list.add(rs.getInt("money"));
			list.add(rs.getDate("date"));
			}
		return list;
	}

	@Override
	public List<Object> selectExpenditure() throws SQLException {
		StringBuilder sql= new StringBuilder(" SELECT sum(shop_expenditure_info_money)as money,FROM_UNIXTIME(UNIX_TIMESTAMP(shop_expenditure_info_create_time), '%Y-%m-%d') as date FROM 030502_shop_expenditure_info WHERE 1 = 1  ");
		sql.append(" and shop_expenditure_info_status= 2 ");
		sql.append(" GROUP BY FROM_UNIXTIME(UNIX_TIMESTAMP(shop_expenditure_info_create_time), '%Y-%m-%d') ");
		Connection conn = BaseDBUtils.getConnection();
		 PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
	     ResultSet rs = BaseDBUtils.executeQuery(pst);
	     List<Object>list = new ArrayList<Object>();
			// 遍历结果集
			while (rs.next()) {
			list.add(rs.getInt("money"));
			list.add(rs.getDate("date"));
			}
		return list;
	}

}
