package cn.kgc.tangcco.tcbd1017.on.seller.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.lihaozhe.commons.jdbc.PageRang;
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
	 * 模糊查询订单信息
	 */
	@Override
	public List<Map<String, Object>> selectOrderByCondition(Map<String, Object> map) {
		//存放最终返回的结果集
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		//存放查询条件的list
		List<Object> condition = new ArrayList<Object>();
		//sql语句
		StringBuffer sql = new StringBuffer(" select order_goods_id , og.seller_id , og.order_id , og.goods_id , og.amount_of_goods , ");
		  sql.append(" order_uuid , logistics_id , order_create_time , order_update_time , order_status , ord.buyer_id , order_payment , buyer_cash_voucher_id ,  ");
		  sql.append(" order_payment_type , order_payment_time , order_consign_time  , order_end_time , order_close_time , order_buyer_message , ");
		  sql.append(" buyer_uuid , buyer_name , buyer_mobile , buyer_mail , buyer_create_time , buyer_update_time , buyer_status, ");
		  sql.append(" 0203_goods.goods_uuid , goods_create_time , goods_update_time , goods_status , ");
		  sql.append(" 0203_goods.goods_picture_url_id , 0203_goods.goods_name ,0203_goods.goods_price , 0203_goods.goods_brand , 0203_goods.goods_type , 0203_goods.goods_width , ");
		  sql.append(" 0203_goods.goods_height , 0203_goods.goods_length , 0203_goods.goods_presentation , 0203_goods.storage_id , goods_picture_url , ");
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
			//根据订单号order_uuid查询订单
			if (map.containsKey("order_uuid")&&!StringUtils.isEmpty(map.get("order_uuid").toString())) {
				sql.append(" and order_uuid=? ");
				condition.add(map.get("order_uuid"));
			}
			//根据商品名称goods_name模糊查询订单
			if (map.containsKey("goods_name")&&!StringUtils.isEmpty(map.get("goods_name").toString())) {
				sql.append(" and 0203_goods.goods_name like ? ");
				condition.add("%"+map.get("goods_name")+"%");
			}
			//根据用户名buyer_name模糊查询订单
			if (map.containsKey("buyer_name")&&!StringUtils.isEmpty(map.get("buyer_name").toString())) {
				sql.append(" and buyer_name like ? ");
				condition.add("%"+map.get("buyer_name")+"%");
			}
			//根据商品类别goods_type模糊查询订单
			if (map.containsKey("goods_type")&&!StringUtils.isEmpty(map.get("goods_type").toString())) {
				sql.append(" and 0203_goods.goods_type like ? ");
				condition.add("%"+map.get("goods_type")+"%");
			}
			//根据商品品牌goods_brand模糊查询订单
			if (map.containsKey("goods_brand")&&!StringUtils.isEmpty(map.get("goods_brand").toString())) {
				sql.append(" and 0203_goods.goods_brand like ? ");
				condition.add("%"+map.get("goods_brand")+"%");
			}
			//根据订单状态查询
			if (map.containsKey("order_status")&&!StringUtils.isEmpty(map.get("order_status").toString())) {
				sql.append(" and order_status = ? ");
				condition.add(map.get("order_status"));
			}
			//根据时间段模糊查询订单创建时间order_create_time在该范围之内的订单
			if (map.containsKey("date_from")&&!StringUtils.isEmpty(map.get("date_from").toString())) {
				sql.append(" and order_create_time > ? ");
				condition.add(map.get("date_from").toString());
			}
			if (map.containsKey("date_to")&&!StringUtils.isEmpty(map.get("date_to").toString())) {
				sql.append(" and order_create_time < ? ");
				condition.add(map.get("date_to").toString());
			}
			sql.append(" ORDER BY order_create_time desc ");
			PageRang pr = (PageRang) map.get("page");
			Connection connection = BaseDBUtils.getConnection();
			PreparedStatement preparedStatement = BaseDBUtils.getPreparedStatement(connection, sql.toString(),pr);
			ResultSet resultSet = BaseDBUtils.executeQuery(preparedStatement, condition.toArray());
			list = rsToList(resultSet);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public int selectOrderCount(Map<String, Object> map) {
		int count = 0;
		//存放查询条件的list
		List<Object> condition = new ArrayList<Object>();
		//sql语句
		StringBuffer sql = new StringBuffer(" select count(*) as count ");
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
			if (map.containsKey("goods_brand")) {
				sql.append(" and goods_brand like ? ");
				condition.add("%"+map.get("goods_brand")+"%");
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
			sql.append(" ORDER BY order_create_time desc ");
			Connection connection = BaseDBUtils.getConnection();
			PreparedStatement preparedStatement = BaseDBUtils.getPreparedStatement(connection, sql.toString());
			ResultSet resultSet = BaseDBUtils.executeQuery(preparedStatement, condition.toArray());
			while (resultSet.next()) {
				count = resultSet.getInt("count");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return count;
	}

	
}
