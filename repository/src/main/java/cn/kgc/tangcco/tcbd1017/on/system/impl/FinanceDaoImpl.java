package cn.kgc.tangcco.tcbd1017.on.system.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.tcbd1017.on.pojo.ShopExpenditureInfo;
import cn.kgc.tangcco.tcbd1017.on.pojo.ShopIncomeInfo;
import cn.kgc.tangcco.tcbd1017.on.system.FinanceDao;

/**
* @author 许佳瑞
* @version 创建时间：2019年12月16日 上午10:43:07
* @edition 1.0
* @Description 类描述
*/
public class FinanceDaoImpl implements FinanceDao {
	/**
	 * 修改套餐状态
	 */
	@Override
	public int updateSetMealState(Map<String, Object> map) throws SQLException {
		StringBuilder sql=new StringBuilder(" update 030401_seller_buy_set_meal_info as si set si.seller_buy_set_meal_info_status = 2 WHERE  1 = 1 ");
		sql.append(" and si.seller_buy_set_meal_info_status != '1' ");
		sql.append(" and si.seller_id = ? ");
		Object param[]= {map.get("seller_id")};
		Connection conn = BaseDBUtils.getConnection();
		PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
		int rs = BaseDBUtils.executeUpdate(pst, param);
		return rs;
	}
	/**
	 * 状态修改后添加信息
	 */
	@Override
	public int insertIncome(Map<String, Object> map) throws SQLException {
		StringBuilder sql= new StringBuilder(" INSERT INTO 030501_shop_income_info (shop_income_and_expanditure_type,shop_income_time,shop_income_money,seller_id,shop_income_info_create_time,shop_income_info_update_time)VALUES(1,now(),?,?,now(),now()) ");
		Object param[]= {map.get("shop_income_money"),map.get("seller_id")};
		Connection conn = BaseDBUtils.getConnection();
		PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
		int rs = BaseDBUtils.executeUpdate(pst, param);
		return rs;
	}
	/**
	 * 显示收入情况
	 */
	@Override
	public List<ShopIncomeInfo> selectIncome() throws SQLException {
		StringBuilder sql=new StringBuilder(" SELECT  shop_income_info_id,shop_income_and_expanditure_type,shop_income_time,shop_income_money,seller_id,shop_income_info_create_time,shop_income_info_update_time from 030501_shop_income_info WHERE 1 = 1 ");
		sql.append(" and shop_income_info_status = 2 ");
		List<ShopIncomeInfo> list =new ArrayList<ShopIncomeInfo>();
		Connection conn = BaseDBUtils.getConnection();
		PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
		ResultSet rs = BaseDBUtils.executeQuery(pst);
		while (rs.next()) {
			ShopIncomeInfo shopIncomeInfo= new ShopIncomeInfo();
			shopIncomeInfo.setSeller_id(rs.getInt("seller_id"));
			shopIncomeInfo.setShop_income_and_expanditure_type(rs.getInt("shop_income_and_expanditure_type"));
			shopIncomeInfo.setShop_income_time(rs.getDate("shop_income_time"));
			shopIncomeInfo.setShop_income_money(rs.getDouble("shop_income_money"));
			shopIncomeInfo.setSeller_id(rs.getInt("seller_id"));
			shopIncomeInfo.setShop_income_info_create_time(rs.getDate("shop_income_info_create_time"));
			shopIncomeInfo.setShop_income_info_update_time(rs.getDate("shop_income_info_update_time"));
			list.add(shopIncomeInfo);
		}
		return list;
	}
	/**
	 * 显示支出情况
	 */
	@Override
	public List<ShopExpenditureInfo> selectExpenditure() throws SQLException {
	StringBuilder sql= new StringBuilder(" SELECT shop_expenditure_info_id,shop_income_and_expanditure_type,shop_expenditure_info_time,shop_expenditure_info_money,expenditure_order_id,shop_expenditure_info_create_time,shop_expenditure_info_update_time FROM 030502_shop_expenditure_info WHERE 1 = 1 ");
	sql.append(" and shop_expenditure_info_status = 2 ");
	List<ShopExpenditureInfo> list =new ArrayList<ShopExpenditureInfo>();
	Connection conn = BaseDBUtils.getConnection();
	PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
	ResultSet rs = BaseDBUtils.executeQuery(pst);
	while (rs.next()) {
		ShopExpenditureInfo shopExpenditureInfo= new ShopExpenditureInfo();
		shopExpenditureInfo.setExpenditure_order_id(rs.getInt("expenditure_order_id"));
		shopExpenditureInfo.setShop_expenditure_info_create_time(rs.getDate("shop_expenditure_info_create_time"));
		shopExpenditureInfo.setShop_expenditure_info_id(rs.getInt("shop_expenditure_info_id"));
		shopExpenditureInfo.setShop_expenditure_info_money(rs.getDouble("shop_expenditure_info_money"));
		shopExpenditureInfo.setShop_expenditure_info_time(rs.getDate("shop_expenditure_info_time"));
		shopExpenditureInfo.setShop_expenditure_info_update_time(rs.getDate("shop_expenditure_info_update_time"));
		shopExpenditureInfo.setShop_income_and_expanditure_type(rs.getInt("shop_income_and_expanditure_type"));
		list.add(shopExpenditureInfo);
	}
		return list;
	}
	

}
