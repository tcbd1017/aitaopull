package cn.kgc.tangcco.tcbd1017.on.seller.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.tcbd1017.on.seller.SellerOrderDao;

/**
*@author   席首华
*@version  1.0   </br>
   创建时间   2019年12月13日     上午11:14:43
  类描述：
*
*/
public class SellerOrderDaoimpl implements SellerOrderDao {

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
	
	/**
	 * 根据卖家id查询其全部的订单信息
	 */
	@Override
	public List<Map<String, Object>> selectAllOrder(Map<String, Object> map) {
		//存放返回的结果集
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		//存放单个的订单信息
		Map<String, Object> order ;
		//sql语句
		StringBuffer sql = new StringBuffer(" select order_goods_id , og.seller_id , og.order_id , og.goods_id , amount_of_goods , ");
		sql.append(" order_uuid , logistics_id , order_create_time , order_update_time , order_status , ord.buyer_id , order_payment , buyer_cash_voucher_id ,  ");
		sql.append(" order_payment_type , order_payment_time , order_consign_time  , order_end_time , order_close_time , order_buyer_message , ");
		sql.append(" buyer_uuid , buyer_name , buyer_mobile , buyer_mail , buyer_create_time , buyer_update_time , buyer_status, ");
		sql.append(" goods_uuid , goods_create_time , goods_update_time , goods_status , ");
		sql.append(" 0203_goods.goods_picture_url_id ,  goods_name ,goods_price , goods_brand , goods_type , goods_width , ");
		sql.append(" goods_height , goods_length , goods_presentation ,  storage_id , goods_picture_url , ");
		sql.append(" goods_picture_url_create_time , goods_picture_url_update_time , goods_picture_url_status ");
		sql.append(" from  0109_order_goods as og  ");
		sql.append(" inner join  0109_order as ord ");
		sql.append(" inner join 0101_buyer as buyer ");
		sql.append(" inner join  0203_goods  ");
		sql.append(" inner join 020301_goods_picture_url ");
		sql.append(" on og.seller_id= ? ");
		sql.append(" and og.order_id=ord.order_id  "); 
		sql.append(" and og.goods_id = 0203_goods.goods_id  ");
		sql.append(" and 0203_goods.goods_id = 020301_goods_picture_url.goods_id  ");
		sql.append(" and 0203_goods.goods_picture_url_id=020301_goods_picture_url.goods_picture_url_id ");
		sql.append(" and goods_picture_url_status = 2 ");
		sql.append(" and ord.buyer_id = buyer.buyer_id ");
		try {
			//获取商家id
			Object [] param = {map.get("seller_id")};
			Connection connection = BaseDBUtils.getConnection();
			PreparedStatement preparedStatement = BaseDBUtils.getPreparedStatement(connection, sql.toString());
			ResultSet resultSet = BaseDBUtils.executeQuery(preparedStatement, param);
			while (resultSet.next()) {
				//把单个结果集当到一个map中
				order=new HashMap<String, Object>();
				order.put("order_goods_id", resultSet.getInt("order_goods_id"));
				order.put("seller_id", resultSet.getInt("seller_id"));
				order.put("order_id", resultSet.getInt("order_id"));
				order.put("goods_id", resultSet.getInt("goods_id"));
				order.put("amount_of_goods", resultSet.getInt("amount_of_goods"));
				order.put("order_uuid", resultSet.getString("order_uuid"));
				order.put("logistics_id", resultSet.getInt("logistics_id"));
				order.put("order_create_time", resultSet.getDate("order_create_time"));
				order.put("order_update_time", resultSet.getDate("order_update_time"));
				order.put("order_status", resultSet.getInt("order_status"));
				order.put("buyer_id", resultSet.getInt("buyer_id"));
				order.put("order_payment", resultSet.getDouble("order_payment"));
				order.put("buyer_cash_voucher_id", resultSet.getInt("buyer_cash_voucher_id"));
				order.put("order_payment_type", resultSet.getInt("order_payment_type"));
				order.put("order_payment_time", resultSet.getDate("order_payment_time"));
				order.put("order_consign_time", resultSet.getDate("order_consign_time"));
				order.put("order_end_time", resultSet.getDate("order_end_time"));
				order.put("order_close_time", resultSet.getDate("order_close_time"));
				order.put("order_buyer_message", resultSet.getString("order_buyer_message"));
				order.put("buyer_uuid", resultSet.getString("buyer_uuid"));
				order.put("buyer_name", resultSet.getString("buyer_name"));
				order.put("buyer_mobile", resultSet.getString("buyer_mobile"));
				order.put("buyer_mail", resultSet.getString("buyer_mail"));
				order.put("buyer_create_time", resultSet.getDate("buyer_create_time"));
				order.put("buyer_update_time", resultSet.getDate("buyer_update_time"));
				order.put("buyer_status", resultSet.getInt("buyer_status"));
				order.put("goods_uuid", resultSet.getString("goods_uuid"));
				order.put("goods_create_time", resultSet.getDate("goods_create_time"));
				order.put("goods_update_time", resultSet.getDate("goods_update_time"));
				order.put("goods_status", resultSet.getInt("goods_status"));
				order.put("goods_picture_url_id", resultSet.getInt("goods_picture_url_id"));
				order.put("goods_name", resultSet.getString("goods_name"));
				order.put("goods_price", resultSet.getDouble("goods_price"));
				order.put("goods_brand", resultSet.getString("goods_price"));
				order.put("goods_type", resultSet.getString("goods_type"));
				order.put("goods_width", resultSet.getDouble("goods_width"));
				order.put("goods_height", resultSet.getDouble("goods_height"));
				order.put("goods_length", resultSet.getDouble("goods_length"));
				order.put("goods_presentation", resultSet.getString("goods_presentation"));
				order.put("storage_id", resultSet.getInt("storage_id"));
				order.put("goods_picture_url", resultSet.getString("goods_picture_url"));
				order.put("goods_picture_url_create_time", resultSet.getDate("goods_picture_url_create_time"));
				order.put("goods_picture_url_update_time", resultSet.getDate("goods_picture_url_update_time"));
				order.put("goods_picture_url_status", resultSet.getInt("goods_picture_url_status"));
				//把所有的单个结果map都放在list中
				list.add(order);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	/**
	 * 模糊查询订单信息
	 */
	@Override
	public List<Map<String, Object>> selectOrderByCondition(Map<String, Object> map) {
		//存放最终返回的结果集
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		//存放查询条件的list
		List<Object> condition = new ArrayList<Object>();
		//sql语句
		StringBuffer sql = new StringBuffer(" select order_goods_id , og.seller_id , og.order_id , og.goods_id , amount_of_goods , ");
		sql.append(" order_uuid , logistics_id , order_create_time , order_update_time , order_status , ord.buyer_id , order_payment , buyer_cash_voucher_id ,  ");
		sql.append(" order_payment_type , order_payment_time , order_consign_time  , order_end_time , order_close_time , order_buyer_message , ");
		sql.append(" buyer_uuid , buyer_name , buyer_mobile , buyer_mail , buyer_create_time , buyer_update_time , buyer_status, ");
		sql.append(" goods_uuid , goods_create_time , goods_update_time , goods_status , ");
		sql.append(" 0203_goods.goods_picture_url_id ,  goods_name ,goods_price , goods_brand , goods_type , goods_width , ");
		sql.append(" goods_height , goods_length , goods_presentation ,  storage_id , goods_picture_url , ");
		sql.append(" goods_picture_url_create_time , goods_picture_url_update_time , goods_picture_url_status ");
		sql.append(" from  0109_order_goods as og  ");
		sql.append(" inner join  0109_order as ord ");
		sql.append(" inner join 0101_buyer as buyer ");
		sql.append(" inner join  0203_goods  ");
		sql.append(" inner join 020301_goods_picture_url ");
		sql.append(" on og.seller_id= ? ");
		sql.append(" and og.order_id=ord.order_id  "); 
		sql.append(" and og.goods_id = 0203_goods.goods_id  ");
		sql.append(" and 0203_goods.goods_id = 020301_goods_picture_url.goods_id  ");
		sql.append(" and 0203_goods.goods_picture_url_id=020301_goods_picture_url.goods_picture_url_id ");
		sql.append(" and goods_picture_url_status = 2 ");
		sql.append(" and ord.buyer_id = buyer.buyer_id ");
		condition.add(map.get("seller_id"));
		try {
			//根据订单号order_id查询订单
			if (map.containsKey("order_id")) {
				sql.append(" and og.order_id=? ");
				condition.add(map.get("order_id"));
			}
			//根据商品名称goods_name模糊查询订单
			if (map.containsKey("goods_name")) {
				sql.append(" and goods_name like ? ");
				condition.add("%"+map.get("goods_name")+"%");
			}
			//根据用户名buyer_name模糊查询订单
			if (map.containsKey("buyer_name")) {
				sql.append(" and buyer_name like ? ");
				condition.add("%"+map.get("buyer_name")+"%");
			}
			//根据商品类别goods_type模糊查询订单
			if (map.containsKey("goods_type")) {
				sql.append(" and goods_type like ? ");
				condition.add("%"+map.get("goods_type")+"%");
			}
			//根据商品品牌goods_brand模糊查询订单
			if (map.containsKey("goods_type")) {
				sql.append(" and goods_type like ? ");
				condition.add("%"+map.get("goods_type")+"%");
			}
			//根据订单状态查询
			if (map.containsKey("order_status")) {
				sql.append(" and order_status = ? ");
				condition.add(map.get("order_status"));
			}
			//根据时间段模糊查询订单创建时间order_create_time在该范围之内的订单
			if (map.containsKey("date_from")) {
				sql.append(" and order_create_time > ? ");
				condition.add(map.get("date_from").toString());
			}
			if (map.containsKey("date_to")) {
				sql.append(" and order_create_time < ? ");
				condition.add(map.get("date_to").toString());
			}
			Connection connection = BaseDBUtils.getConnection();
			PreparedStatement preparedStatement = BaseDBUtils.getPreparedStatement(connection, sql.toString());
			ResultSet resultSet = BaseDBUtils.executeQuery(preparedStatement, condition.toArray());
			list = rsToList(resultSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	
}
