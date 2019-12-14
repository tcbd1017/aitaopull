package cn.kgc.tangcco.tcbd1017.on.seller;
/**
*@author   席首华
*@version  1.0   </br>
   创建时间   2019年12月13日     上午9:54:08
  类描述：
*
*/

import java.util.List;
import java.util.Map;

public interface SellerOrderDao {

	/**
	 * 查询该店铺的所有订单信息
	 * @param map 传入店铺id
	 * @return   
	 */
	public abstract List<Map<String, Object>> selectAllOrder(Map<String, Object> map);
	
	
	/**
	 * 模糊查询订单信息
	 * @param map 模糊查询的条件
	 * @return  返回查询到的订单信息列表
	 */
	public abstract List<Map<String, Object>> selectOrderByCondition(Map<String, Object> map);
	
}
