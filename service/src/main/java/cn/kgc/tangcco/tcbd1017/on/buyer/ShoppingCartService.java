package cn.kgc.tangcco.tcbd1017.on.buyer;

import java.util.List;
import java.util.Map;

	/**
	 * @author DU MING
	 * 
	 * @version 1.0	2019年12月13日	下午1:52:38
	 */

public interface ShoppingCartService {
	
	/**
	 * 用来查询某用户下所有有效的购物车中商品信息
	 * @param map 前台传入的，经过简单处理的大量信息。在Service层可能分Dao查询，并汇总送入CartDao层
	 * @return 包含独立Map(多表联查) 的list集合的Map,还有其他查询信息如 success
	 */
	public abstract Map<String, Object> queryAllShoppingCartInfoByBuyerId(Map<String, Object> map);
	
	/**
	 * 添加购物车信息
	 * @param map 前台传入的，经过简单处理的大量信息。在Service层分Dao查询，如：把名称换成id并重新组装后送入Dao层
	 * @return 添加状态 0 失败 1 成功
	 */
	public abstract int addShoppingCart(Map<String, Object> map);
	
	/**
	 * 修改购物车信息
	 * @param map 前台传入的，经过简单处理的大量信息。 在service层分Dao查询，进行id替换后的信息去Dao层更新
	 * @return 更新状态 0 失败 1 成功
	 */
	public abstract int modifyShoppingCart(Map<String, Object> map);
	
	/**
	 * 删除购物车信息(表字段修改为1)
	 * @param map 同修改方法 但只需要buyer_id, goods_name
	 * @return  删除执行状态 0 失败 1 成功
	 */
	public abstract int removeShoppingCart(Map<String, Object> map);
}


