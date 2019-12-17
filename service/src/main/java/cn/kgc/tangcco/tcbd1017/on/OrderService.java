package cn.kgc.tangcco.tcbd1017.on;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.tcbd1017.on.pojo.OrderGoods;

/**
 * 廖斌
 * @author Admin
 *
 */
public interface OrderService {
	/**
	 * @return List<Order>
	 * 	查询订单 
	 * @throws SQLException 
	 */
	public Map<String,Object> selectByOrder(Map<String,Object> map) throws SQLException;
	/**
	 * @return int
	 * 	修改订单，修改订单
	 */
	public Map<String,Object> updateByOrder( Map<String,Object> map )throws SQLException;
	/**
	 * @return int
	 * 	新增订单
	 */
	public Map<String,Object> insertByOrder(Map<String,Object> map)throws SQLException;
	
	/**
	 *  从购物车新增订单
	 * 
	 */
	public Map<String,Object> insertByOrderByShoppingCart(Map<String,String> map)throws SQLException;
	
}
