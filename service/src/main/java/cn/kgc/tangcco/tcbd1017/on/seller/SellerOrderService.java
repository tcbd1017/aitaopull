package cn.kgc.tangcco.tcbd1017.on.seller;
/**
*@author   席首华
*@version  1.0   </br>
   创建时间   2019年12月13日     下午7:26:45
  类描述：
*
*/

import java.util.Map;

public interface SellerOrderService {

	
	/**
	 * 查询店铺中的订单信息
	 * @param map 中 传入店铺id
	 * @return   返回店铺中所有信息的集合
	 */
	public abstract Map<String, Object> queryOrder(Map<String, Object> map);
	
}
