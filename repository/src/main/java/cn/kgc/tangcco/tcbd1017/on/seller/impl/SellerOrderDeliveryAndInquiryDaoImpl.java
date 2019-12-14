package cn.kgc.tangcco.tcbd1017.on.seller.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;



import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.lihaozhe.commons.jdbc.PageRang;
import cn.kgc.tangcco.tcbd1017.on.pojo.Order;
import cn.kgc.tangcco.tcbd1017.on.seller.SellerOrderDeliveryAndInquiryDao;

/**
*@author   郝彦冰<br>
*@version  创建时间 :  2019年12月13日  下午1:45:56<br>
*类说明:
*/
public class SellerOrderDeliveryAndInquiryDaoImpl implements SellerOrderDeliveryAndInquiryDao{
	/**
	 * 卖家订单发货查询买家收货信息
	 * @return    买家信息（主要是收货地址）
	 * @throws SQLException
	 */
	@Override
	public List<Map<String,Object>> selectSellerOrderDeliveryQueryBuyerReceiptInformation() throws SQLException {
		StringBuffer sql = new StringBuffer(" select p.postage_info_name, ");
		sql.append(" p.postage_info_mobile, ");
		sql.append(" p.postage_info_province_id  , ");
		sql.append(" p.postage_info_city_id  , ");
		sql.append(" p.postage_info_district_id  , ");
		sql.append(" p.postage_info_address ");
		sql.append(" from 010401_postage_info as p  ");
		sql.append(" inner join 04_address as address  ");
		sql.append(" inner join  0104_buyer_and_postage_info as bi  ");
		sql.append(" inner join 0101_buyer as buyer ");
		sql.append(" inner join 0109_order as o ");
		sql.append(" on 1 = 1 ");
		sql.append(" and  o.buyer_id = buyer.buyer_id ");
		sql.append(" and buyer.buyer_id = bi.buyer_id ");
		sql.append(" and bi.postage_info_id = p.postage_info_id ");
		sql.append(" and p.postage_info_province_id =address.address_id ");
		sql.append(" and o.order_status = 3 ");
		sql.append(" and  p.postage_info_status > 1 ");
		sql.append(" and buyer.buyer_status = 2 ");
		Connection conn = BaseDBUtils.getConnection();
		PreparedStatement pst = null; 
		ResultSet rs = null;
		//if (map1.containsKey("pr")) {
			//pst = BaseDBUtils.getPreparedStatement(conn, sql.toString(),(PageRang)map1.get("pr"));
		//} else {
			pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
		//}
		
		rs= BaseDBUtils.executeQuery(pst);
		Map<String , Object > map = null;
		List<Map<String , Object >> list= new ArrayList<Map<String,Object>>();
		if (rs.first()) {
			rs.previous();
			while (rs.next()) {
				map=new LinkedHashMap<String, Object>();
				map.put("postage_info_name", rs.getString("p.postage_info_name"));
				map.put("postage_info_mobile", rs.getString("p.postage_info_mobile"));
				map.put("postage_info_province", selectProvince(rs.getInt("p.postage_info_province_id")));
				map.put("postage_info_city", selectCity(rs.getInt("p.postage_info_city_id")));
				map.put("postage_info_district", selectDistrict(rs.getInt("p.postage_info_district_id")));
				map.put("postage_info_address", rs.getString("p.postage_info_address"));
				list.add(map);
			}
		}
		
		return list;
	}
	/**
	 * 根据省id查询省名称
	 * @param string
	 * @return
	 * @throws SQLException
	 */
	private String selectProvince(int string) throws SQLException {
		StringBuffer sql = new StringBuffer(" select address_name from 04_address as a  inner join 010401_postage_info on 1 = 1 and  a.address_id = ? ");
		Object [] param = {string};
		Connection conn = BaseDBUtils.getConnection();
		PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
		ResultSet rs = BaseDBUtils.executeQuery(pst, param);
		String province = null;
		if (rs.first()) {
			rs.previous();
			while (rs.next()) {
				province =rs.getString("address_name");
			}
		}		
		return province;
	}
	/**
	 * 根据市id查询市名称
	 * @param string
	 * @return
	 * @throws SQLException
	 */
	private String selectCity(int string) throws SQLException {
		StringBuffer sql = new StringBuffer(" select address_name from 04_address as a  inner join 010401_postage_info on 1 = 1 and  a.address_id = ? ");
		Object [] param = {string};
		Connection conn = BaseDBUtils.getConnection();
		PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
		ResultSet rs = BaseDBUtils.executeQuery(pst, param);
		String city = null;
		if (rs.first()) {
			rs.previous();
			while (rs.next()) {
				city =rs.getString("address_name");
			}
		}		
		return city;
	}
	/**
	 * 根据区/县id查询区名称
	 * @param string
	 * @return
	 * @throws SQLException
	 */
	private String selectDistrict(int string) throws SQLException {
		StringBuffer sql = new StringBuffer(" select address_name from 04_address as a  inner join 010401_postage_info on 1 = 1 and  a.address_id = ? ");
		Object [] param = {string};
		Connection conn = BaseDBUtils.getConnection();
		PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
		ResultSet rs = BaseDBUtils.executeQuery(pst, param);
		String district = null;
		if (rs.first()) {
			rs.previous();
			while (rs.next()) {
				district =rs.getString("address_name");
			}
		}		
		return district;
	}
	/**
	 * 查询卖家发货信息（商品名称，商品数量，卖家发货人姓名、手机号、地址）
	 * @param map1
	 * @return
	 * @throws SQLException
	 */
	public Map<String,Object> selectSellerShippingAddress() throws SQLException {
		StringBuffer sql = new StringBuffer(" select g.goods_name ,og.amount_of_goods , b.buyer_name,b.buyer_mobile,bi.buyer_info_address ");
		sql.append(" from 0201_seller as s ");
		sql.append(" inner join 0101_buyer as b ");
		sql.append(" inner join 010101_buyer_info as bi ");
		sql.append(" inner join 0203_goods as g ");
		sql.append(" inner join 0109_order_goods as og ");
		sql.append(" inner join 0109_order as o ");
		sql.append(" on 1 = 1 ");
		sql.append(" and s.buyer_id = b.buyer_id ");
		sql.append(" and  b.buyer_id = bi.buyer_id ");
		sql.append(" and g.goods_id = og.goods_id ");
		sql.append(" and  o.order_status = 3 ");
		sql.append(" and b.buyer_status =2 ");
		sql.append(" and s.seller_status = 3 ");
		Connection conn = BaseDBUtils.getConnection();
		PreparedStatement pst = null; 
		ResultSet rs = null;
		pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
		rs= BaseDBUtils.executeQuery(pst);
		Map<String , Object > map = null;
		List<Map<String , Object >> list= new ArrayList<Map<String,Object>>();
		if (rs.first()) {
			rs.previous();
			while (rs.next()) {
				map=new LinkedHashMap<String, Object>();
				map.put("goods_name", rs.getString("g.goods_name"));
				map.put("amount_of_goods", rs.getInt("og.amount_of_goods"));
				map.put("seller_name", rs.getString("b.buyer_name"));//姓名不一致
				map.put("seller_mobile", rs.getString("b.buyer_mobile"));
				map.put("seller_info_address", rs.getString("bi.buyer_info_address"));
			} 
		}
		return map;
	}
	/**
	 * 查询卖家与买家的发货地址、收货地址
	 */
	public List<Map<String,Object>> selectAddress() throws SQLException{
		//List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> map = selectSellerShippingAddress( );
		List<Map<String, Object>> list2 = selectSellerOrderDeliveryQueryBuyerReceiptInformation( );
		list2.add(map);
		return list2;
	}
	
	  
	  
	
	
	
	 
	 
	 
	 
	
	/**
	 * 查询未发货的订单(根据订单表中order_status)
	 * @return	未发货的订单  
	 * @throws SQLException
	 */
	@Override   //Order order
	public List selectUndeliveredOrder(Map<String , Object> map) throws SQLException {
		StringBuffer sql = new StringBuffer(" select * from 0109_order where 1 =1 and order_status= 3 ");
		Connection conn = BaseDBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		
		//Order o=(Order)map.get("order");
		//Object[] param = {((Order)map.get("order")).getOrder_status()};
		if (map.containsKey("pr")) {
			pst=BaseDBUtils.getPreparedStatement(conn, sql.toString(),(PageRang)map.get("pr"));
		}else {
			pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
		}
//		if (param.length>0) {
//			rs= BaseDBUtils.executeQuery(pst, param);
//		}else {
//			rs= BaseDBUtils.executeQuery(pst, 3);
//		}
		rs= BaseDBUtils.executeQuery(pst);
		return rsToList(rs);
	}
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
}
