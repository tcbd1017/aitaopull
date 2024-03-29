package cn.kgc.tangcco.tcbd1017.on.seller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
*@author   郝彦冰<br>
*@version  创建时间 :  2019年12月15日  下午8:03:26<br>
*类说明:
*/
public interface SellerOrderDeliveryAndInquiryService {
	/**
	 * 查询卖家与买家的发货地址信息、收货地址信息
	 * map存储在list中
	 * map.put(key,value)
	 * key: "order_id"                 value: 订单id
	 * key: "postage_info_name"        value: 收件人姓名
	 * key: "postage_info_mobile"      value: 收件人电话
	 * key: "postage_info_province"    value: 收件人所在省份
	 * key: "postage_info_city"        value: 收件人所在市
	 * key: "postage_info_district"    value: 收件人所在区
	 * key: "postage_info_address"     value: 收件人详细地址
	 * key: "goods_name"               value: 购买商品名称
	 * key: "amount_of_goods"          value: 购买商品数量
	 * key: "seller_name"              value: （卖家作为寄件人）     寄件人名称
	 * key: "seller_mobile"            value: （卖家作为寄件人）     寄件人手机
	 * key: "seller_info_address"      value: （卖家作为寄件人）     寄件人地址
	 * 
	 * @param map1  
	 * @return  List<Map<String,Object>>  地址信息
	 * @throws SQLException
	 */
//	Map<String,Object> queryAddress();
	/**
	 * 查询买家收货信息
	 * @return    买家信息（主要是收货地址）
	 * @throws SQLException
	 */
	Map<String, Object> querySellerOrderDeliveryQueryBuyerReceiptInformation(Map<String, Object> map)
			;
	
	/**
	 * 查询卖家发货信息（商品名称，商品数量，卖家发货人姓名、手机号、地址）
	 * @param map1
	 * @return
	 * @throws SQLException
	 */
	public Map<String, Object> querySellerShippingAddress(Map<String, Object> map);
	
	/**
	 * 修改订单待发货状态
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	Map<String,Object> modifyOrderStatus(Map<String,Object> map);

}
