package cn.kgc.tangcco.tcbd1017.on.buyer;

import java.util.List;
import java.util.Map;

	/**
	 * @author DU MING
	 * @@
	 * @version 1.0	2019年12月13日	下午1:52:38
	 */

public interface ShoppingCartService {
	
	/**
	 * 用来查询某用户下所有有效的购物车中商品信息
	 * @param map 前台传入的，经过简单处理的大量信息。在Service层可能分Dao查询，并汇总送入CartDao层
	 * @return 包含独立Map(多表联查) 的list集合的Map,以及其总计数。还有其他查询信息如 success 
	 */
	public abstract Map<String, Object> queryAllShoppingCartInfoByBuyerId(Map<String, Object> map);
	
	public abstract Map<String, Object> queryAllShoppingCartInfoByBuyerIdAddUrl(Map<String, Object> map);
	
	/**
	 * 添加购物车信息
	 * @param map 前台传入的，经过简单处理的大量信息。
	 * @return 添加状态 key "status" value "failed or success" 和 msg
	 */
	public abstract Map<String, Object> addShoppingCart(Map<String, Object> map);
	
	/**
	 * 修改购物车信息
	 * @param map 前台传入的，经过简单处理的大量信息。
	 * @return 更新状态 key "status" value "failed or success" 和 msg
	 */
	public abstract Map<String, Object> modifyShoppingCart(Map<String, Object> map);
	
	/**
	 * 删除购物车信息(表字段修改为1)
	 * @param map 同修改方法 但只需要buyer_id, goods_id
	 * @return  删除执行状态 key "status" value "failed or success" 和 msg
	 */
	public abstract Map<String, Object> removeShoppingCart(Map<String, Object> map);
}


