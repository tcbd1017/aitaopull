package cn.kgc.tangcco.tcbd1017.on.system.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.lihaozhe.commons.jdbc.PageRang;
import cn.kgc.tangcco.tcbd1017.on.pojo.SellerBuySetMealInfo;
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
	 *  修改套餐状态
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
	public List<ShopIncomeInfo> selectIncome(Map<String, Object>map) throws SQLException {
		StringBuilder sql=new StringBuilder(" SELECT  shop_income_info_id,shop_income_and_expanditure_type,shop_income_time,shop_income_money,seller_id,shop_income_info_create_time,shop_income_info_update_time from 030501_shop_income_info WHERE 1 = 1 ");
		sql.append(" and shop_income_info_status = 2 ");
		List<ShopIncomeInfo> list =new ArrayList<ShopIncomeInfo>();
		Connection conn = BaseDBUtils.getConnection();
		PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString(),(PageRang) map.get("pr"));
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
	public List<ShopExpenditureInfo> selectExpenditure(Map<String, Object>map) throws SQLException {
	StringBuilder sql= new StringBuilder(" SELECT shop_expenditure_info_id,shop_income_and_expanditure_type,shop_expenditure_info_time,shop_expenditure_info_money,expenditure_order_id,shop_expenditure_info_create_time,shop_expenditure_info_update_time FROM 030502_shop_expenditure_info WHERE 1 = 1 ");
	sql.append(" and shop_expenditure_info_status = 2 ");
	List<ShopExpenditureInfo> list =new ArrayList<ShopExpenditureInfo>();
	Connection conn = BaseDBUtils.getConnection();
	PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString(),(PageRang) map.get("pr"));
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
	
	/**
	 * 
	 * 显示未审核的购买套餐
	 */
	@Override
	public List<SellerBuySetMealInfo> selectSellerBuySetMealInfo(Map<String, Object>map) throws SQLException {
		StringBuilder sql= new StringBuilder(" SELECT seller_buy_set_meal_info_id,seller_id,set_meal_id,set_meal_strat_time,seller_buy_set_meal_info_creat_time,seller_buy_set_meal_info_update_time,person_in_charge_id FROM 030401_seller_buy_set_meal_info WHERE 1 = 1  ");
		sql.append(" and 030401_seller_buy_set_meal_info.seller_buy_set_meal_info_status = 3 ");
		List<Object>list1=new ArrayList<Object>();
		if (map!= null&&map.size()>0){
//			if ((int)map.get("empPower")==2||(int)map.get("empPower")==4) {
//				sql.append(" and person_in_charge_id = 0 ");
//			}
			
			if ((int)map.get("empPower")==1) {
				sql.append(" and person_in_charge_id = ? ");
				list1.add(map.get("person_in_charge_id"));
			}
		}
		Object param[]=list1.toArray();
		List<SellerBuySetMealInfo> list =new ArrayList<SellerBuySetMealInfo>();
		Connection conn = BaseDBUtils.getConnection();
		PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString(),(PageRang) map.get("pr"));
		ResultSet rs = BaseDBUtils.executeQuery(pst,param);
		while (rs.next()) {
			list.add( new SellerBuySetMealInfo(rs.getInt("seller_buy_set_meal_info_id"), rs.getInt("seller_id"), rs.getInt("set_meal_id"),rs.getDate("set_meal_strat_time"), rs.getDate("seller_buy_set_meal_info_creat_time"),rs.getDate("seller_buy_set_meal_info_update_time"),rs.getInt("person_in_charge_id")));
		}
		return list;	
	}
	
	/**
	 * 显示未审核通过的套餐个数
	 */
	@Override
	public Map<String, Object> selectSellerBuySetMealInfocount(Map<String, Object>map) throws SQLException {
		StringBuilder sql= new StringBuilder(" SELECT count(*) as number FROM 030401_seller_buy_set_meal_info WHERE 1 = 1 ");
		sql.append(" and 030401_seller_buy_set_meal_info.seller_buy_set_meal_info_status = 3 ");
		List<Object>list1= new ArrayList<Object>();
		if (map!= null&&map.size()>0){
//			if ((int)map.get("empPower")==2||(int)map.get("empPower")==4) {
//				sql.append(" and person_in_charge_id = 0 ");
//			}
			if ((int)map.get("empPower")==1) {
				sql.append(" and person_in_charge_id = ? ");
				list1.add(map.get("person_in_charge_id"));
			}
		}
		Object param[]=list1.toArray();
		Connection conn = BaseDBUtils.getConnection();
		PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
		ResultSet rs = BaseDBUtils.executeQuery(pst,param);
		Map<String, Object> map1 =new HashMap<String, Object>();
		while (rs.next()) {
			map1.put("number",rs.getInt("number"));
		}
		return map1;
	}
	
	/**
	 * 分配工作
	 */
	@Override
	public int updatePersonInCharge(Map<String, Object> map) throws SQLException {
		StringBuilder sql =new StringBuilder(" UPDATE 030401_seller_buy_set_meal_info set  person_in_charge_id = ? WHERE 1 = 1 ");
		sql.append(" and seller_buy_set_meal_info_id in( ");
		List<Object>list =(List<Object>) map.get("data");
		Iterator<Object> it = list.iterator();
		List<Object> list1 = new ArrayList<Object>();
		list1.add(map.get("person_in_charge_id"));
		while (it.hasNext()) {
			sql.append(" ?, ");
			list1.add(it.next());
		}
		sql.append(" 100000) ");
		Object param[]=list1.toArray();
		Connection conn = BaseDBUtils.getConnection();
		PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
		int rs = BaseDBUtils.executeUpdate(pst, param);
		return rs;
	}
	@Override
	public Map<String, Object> selectmany(Map<String, Object> map) throws SQLException {
		StringBuilder sql= new StringBuilder(" SELECT sm.set_meal_price FROM 030401_seller_buy_set_meal_info as ss ,0304_set_meal as sm WHERE 1 = 1 ");
		sql.append(" and ss.set_meal_id = ? ");
		sql.append(" and ss.set_meal_id = sm.set_meal_id ");
		Object param[]= {map.get("set_meal_id")};
		Connection conn = BaseDBUtils.getConnection();
		PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
		ResultSet rs = BaseDBUtils.executeQuery(pst,param);
		Map<String, Object> map1 =new HashMap<String, Object>();
		while (rs.next()) {
			map1.put("shop_income_money",rs.getInt("set_meal_price"));
		}
		return map1;
	}
	/**
	 * 收入总个数
	 */
	@Override
	public int selectIncomecount(Map<String, Object> map) throws SQLException {
		StringBuilder sql = new StringBuilder(" select count(*) as count FROM 030501_shop_income_info WHERE 1 = 1 ");
		sql.append(" and 030501_shop_income_info.shop_income_info_status != 1 ");
		Connection conn = BaseDBUtils.getConnection();
		PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
		ResultSet rs = BaseDBUtils.executeQuery(pst);
		int count = 0;
		if (rs.next()) {
			count = rs.getInt("count");
		}
		return count;
	}
	/**
	 * 支出总共个数
	 */
	@Override
	public int selectExpenditurecount(Map<String, Object> map) throws SQLException {
		StringBuilder sql= new StringBuilder(" select count(*) as count FROM 030502_shop_expenditure_info WHERE 1 = 1 ");
		sql.append(" and 030502_shop_expenditure_info.shop_expenditure_info_status != 1 ");
		Connection conn = BaseDBUtils.getConnection();
		PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
		ResultSet rs = BaseDBUtils.executeQuery(pst);
		int count = 0;
		if (rs.next()) {
			count = rs.getInt("count");
		}
		return count;
	}
	

}
