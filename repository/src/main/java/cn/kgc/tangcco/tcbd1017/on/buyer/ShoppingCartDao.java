package cn.kgc.tangcco.tcbd1017.on.buyer;

import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.tcbd1017.on.pojo.ShoppingCart;

/**
	 * @author DU MING
	 * @version 1.0	2019年12月10日	下午2:22:51
	 */

public interface ShoppingCartDao {

	/**
	 * 根据BuyerId查询当前购物车中的商品信息
	 * @param map 	map 中根据enableFuzzySelect 的字段决定是否开启模糊查询
	 * 				必须字段buyer_id 可选字段商品名称（根据商品id联合查询商品表）
	 * @return		包含独立Map(多表联查) 的list集合 以及此次查询出的总数量count
	 */
	public abstract List<Map<String ,Object>> selectShoppingCartInfoByBuyerId(Map<String, Object> map);
	/**
	 * 获取上面查询方法查询到的总数据量 (多少条)
	 * @return 查询到的总数据量
	 */
	public abstract int getShoppingCartCount();
	
	/**
	 * 新增当前购物车的信息
	 * @param shoppingCart 需要插入的购物车对象 (在service层组装)
	 * @return 方法执行的结果 0 失败 1 成功
	 */
	public abstract int insertShoppingCart(ShoppingCart shoppingCart);
	
	/**
	 * 在查询到当前商品已经存在于购物车中时用来增加购物车的数量
	 * 在查询到当前商品已经存在于购物车中时用来禁用(删除)当前物品信息
	 * 动态SQL完成
	 * @param shoppingCart 包含新的shoppingCart对象信息
	 * @return 方法的执行结果 0 失败 1 成功
	 */
	public abstract int updateShoppingCartInfo(ShoppingCart shoppingCart);
	

}


