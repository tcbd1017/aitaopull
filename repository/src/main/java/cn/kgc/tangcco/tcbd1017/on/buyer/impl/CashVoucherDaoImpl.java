package cn.kgc.tangcco.tcbd1017.on.buyer.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.omg.PortableInterceptor.INACTIVE;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.lihaozhe.commons.jdbc.PageRang;
import cn.kgc.tangcco.tcbd1017.on.buyer.CashVoucherDao;
/**
 * @author 赵瑞涛
 * 
 * @version v1.0<br>
 * 	创建时间:	2019年12月9日	上午11:13:57<br>
 * 	类描述:
 */
public class CashVoucherDaoImpl implements CashVoucherDao{
	/**
	 * 通过id和状态查找所有的优惠信息
	 */
	@Override
	public List<Map<String, Object>> selectByBuyerId(Map<String, Object> map) throws SQLException {
		StringBuilder sql = new StringBuilder("select v.*,vt.*,s.*,b.* FROM 020401_voucher_type as vt ");
		sql.append("inner join 0204_cash_voucher as v ");
		sql.append("inner join 0207_store as s ");
		sql.append("inner join 0110_buyer_cash_voucher as b on 1 = 1 ");
		sql.append("and s.store_id = v.store_id ");
		sql.append("and v.voucher_type_id = vt.voucher_type_id ");
		sql.append("and b.cash_voucher_id = v.cash_voucher_id ");
		sql.append("and b.buyer_id = ? ");
		sql.append("and v.cash_voucher_status = ? ");
		Object [] params= {map.get("buyer_id"),map.get("cash_voucher_status")};
		Connection connection = BaseDBUtils.getConnection();
		PreparedStatement preparedStatement = BaseDBUtils.getPreparedStatement(connection, sql.toString());
		ResultSet resultSet = BaseDBUtils.executeQuery(preparedStatement, params);
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(resultSet.first()) {
			resultSet.previous();
			while (resultSet.next()) {
				Map<String, Object> map1 = new HashMap<String, Object>();
				map1.put("cash_voucher_id",resultSet.getInt("cash_voucher_id"));
				map1.put("store_id",resultSet.getInt("store_id"));
				
				map1.put("cash_voucher_uuid",resultSet.getString("cash_voucher_uuid"));
				map1.put("voucher_type_id",resultSet.getInt("voucher_type_id"));
				map1.put("cash_voucher_create_time",resultSet.getDate("cash_voucher_create_time"));
				map1.put("cash_voucher_update_time",resultSet.getDate("cash_voucher_update_time"));
				map1.put("cash_voucher_status",resultSet.getInt("cash_voucher_status"));
				map1.put("cash_voucher_available_time",resultSet.getDate("cash_voucher_available_time"));
				map1.put("cash_voucher_amount",resultSet.getInt("cash_voucher_amount"));
				map1.put("cash_voucher_remaining",resultSet.getInt("cash_voucher_remaining"));
				map1.put("cash_voucher_acailable_begin_time",resultSet.getDate("cash_voucher_acailable_begin_time"));
				
				map1.put("voucher_type_condition",resultSet.getInt("voucher_type_condition"));
				map1.put("voucher_type_discount",resultSet.getInt("voucher_type_discount"));
				map1.put("voucher_type_uplimit_flag",resultSet.getInt("voucher_type_uplimit_flag"));
				map1.put("voucher_type_limit_amount",resultSet.getDouble("voucher_type_limit_amount"));
				map1.put("voucher_type_picture_url", resultSet.getString("voucher_type_picture_url"));
				map1.put("voucher_type_money", resultSet.getString("voucher_type_money"));
				
				map1.put("buyer_cash_voucher_id", resultSet.getInt("buyer_cash_voucher_id"));
				map1.put("buyer_id", resultSet.getInt("buyer_id"));
				map1.put("buyer_cash_voucher_create_time", resultSet.getDate("buyer_cash_voucher_create_time"));
				map1.put("buyer_case_voucher_update_time", resultSet.getDate("buyer_case_voucher_update_time") );
				map1.put("buyer_case_voucher_status", resultSet.getInt("buyer_case_voucher_status"));
				
				map1.put("goods_id", resultSet.getInt("goods_id"));
				map1.put("store_create_time", resultSet.getDate("store_create_time"));
				map1.put("store_update_time", resultSet.getDate("store_update_time"));
				map1.put("store_status", resultSet.getInt("store_status"));
				map1.put("store_about", resultSet.getString("store_about"));
				map1.put("store_img", resultSet.getString("store_img"));
				map1.put("store_name", resultSet.getString("store_name"));	
				list.add(map1);
			}
		}
		return list;
	}
	/**
	 * 查询数据总量
	 */
	@Override
	public int selectByNumber(Map<String, Object> map) throws SQLException {
		StringBuilder sql = new StringBuilder("select count(v.cash_voucher_uuid) as a FROM 020401_voucher_type as vt ");
		sql.append("inner join 0204_cash_voucher as v ");
		sql.append("inner join 0207_store as s ");
		sql.append("inner join 0110_buyer_cash_voucher as b on 1 = 1 ");
		sql.append("and s.store_id = v.store_id ");
		sql.append("and v.voucher_type_id = vt.voucher_type_id ");
		sql.append("and b.cash_voucher_id = v.cash_voucher_id ");
		sql.append("and b.buyer_id = ? ");
		sql.append("and v.cash_voucher_status = ? ");
		Object [] params= {map.get("buyer_id"),map.get("cash_voucher_status")};
		Connection connection = BaseDBUtils.getConnection();
		PreparedStatement preparedStatement = BaseDBUtils.getPreparedStatement(connection, sql.toString());
		ResultSet resultSet = BaseDBUtils.executeQuery(preparedStatement, params);
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		int a = 0;
		if(resultSet.first()) {
			resultSet.previous();
			while (resultSet.next()) {
				a = resultSet.getInt("a");
				}
			}
		return a;
	}
	/**
	 * 	删除一个优惠券
	 */
	@Override
	public int deleteByUuid(Map<String, Object> map) throws SQLException {
		StringBuilder sql = new StringBuilder("update 0204_cash_voucher set ");
		sql.append("cash_voucher_status = 4 ");
		sql.append("where  1 = 1 and cash_voucher_uuid = ? ");
		Object param = map.get("cash_voucher_uuid");
		Connection connection = BaseDBUtils.getConnection();
		PreparedStatement preparedStatement = BaseDBUtils.getPreparedStatement(connection, sql.toString());
		int resultSet = BaseDBUtils.executeUpdate(preparedStatement, param);
		return resultSet;
	}
	/**
	 * 	分页查询
	 */
	@Override
	public List<Map<String, Object>> selectByBuerIdAndStatus(Map<String, Object> map) throws SQLException {
		StringBuilder sql = new StringBuilder("select v.*,vt.*,s.*,b.* FROM 020401_voucher_type as vt ");
		sql.append("inner join 0204_cash_voucher as v ");
		sql.append("inner join 0207_store as s ");
		sql.append("inner join 0110_buyer_cash_voucher as b on 1 = 1 ");
		sql.append("and s.store_id = v.store_id ");
		sql.append("and v.voucher_type_id = vt.voucher_type_id ");
		sql.append("and b.cash_voucher_id = v.cash_voucher_id ");
		sql.append("and b.buyer_id = ? ");
		sql.append("and v.cash_voucher_status = ? ");
		Object [] params= {map.get("buyer_id"),map.get("cash_voucher_status")};
		Connection connection = BaseDBUtils.getConnection();
		PreparedStatement preparedStatement = BaseDBUtils.getPreparedStatement(connection, sql.toString());
		ResultSet resultSet = BaseDBUtils.executeQuery(preparedStatement,new PageRang((int)Integer.parseInt(map.get("pageNumber").toString()), (int)Integer.parseInt(map.get("pageSize").toString())), params);
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(resultSet.first()) {
			resultSet.previous();
			while (resultSet.next()) {
				Map<String, Object> map1 = new HashMap<String, Object>();
				map1.put("cash_voucher_id",resultSet.getInt("cash_voucher_id"));
				map1.put("store_id",resultSet.getInt("store_id"));
				
				map1.put("cash_voucher_uuid",resultSet.getString("cash_voucher_uuid"));
				map1.put("voucher_type_id",resultSet.getInt("voucher_type_id"));
				map1.put("cash_voucher_create_time",resultSet.getDate("cash_voucher_create_time"));
				map1.put("cash_voucher_update_time",resultSet.getDate("cash_voucher_update_time"));
				map1.put("cash_voucher_status",resultSet.getInt("cash_voucher_status"));
				map1.put("cash_voucher_available_time",resultSet.getDate("cash_voucher_available_time"));
				map1.put("cash_voucher_amount",resultSet.getInt("cash_voucher_amount"));
				map1.put("cash_voucher_remaining",resultSet.getInt("cash_voucher_remaining"));
				map1.put("cash_voucher_acailable_begin_time",resultSet.getDate("cash_voucher_acailable_begin_time"));
				
				map1.put("voucher_type_condition",resultSet.getInt("voucher_type_condition"));
				map1.put("voucher_type_discount",resultSet.getInt("voucher_type_discount"));
				map1.put("voucher_type_uplimit_flag",resultSet.getInt("voucher_type_uplimit_flag"));
				map1.put("voucher_type_limit_amount",resultSet.getDouble("voucher_type_limit_amount"));
				map1.put("voucher_type_picture_url", resultSet.getString("voucher_type_picture_url"));
				map1.put("voucher_type_money", resultSet.getString("voucher_type_money"));
				
				map1.put("buyer_cash_voucher_id", resultSet.getInt("buyer_cash_voucher_id"));
				map1.put("buyer_id", resultSet.getInt("buyer_id"));
				map1.put("buyer_cash_voucher_create_time", resultSet.getDate("buyer_cash_voucher_create_time"));
				map1.put("buyer_case_voucher_update_time", resultSet.getDate("buyer_case_voucher_update_time") );
				map1.put("buyer_case_voucher_status", resultSet.getInt("buyer_case_voucher_status"));
				
				map1.put("goods_id", resultSet.getInt("goods_id"));
				map1.put("store_create_time", resultSet.getDate("store_create_time"));
				map1.put("store_update_time", resultSet.getDate("store_update_time"));
				map1.put("store_status", resultSet.getInt("store_status"));
				map1.put("store_about", resultSet.getString("store_about"));
				map1.put("store_img", resultSet.getString("store_img"));
				map1.put("store_name", resultSet.getString("store_name"));	
				list.add(map1);
			}
		}
		return list;
	}


}
