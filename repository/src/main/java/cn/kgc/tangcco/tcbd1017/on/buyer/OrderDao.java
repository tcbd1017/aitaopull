package cn.kgc.tangcco.tcbd1017.on.buyer;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.tcbd1017.on.pojo.Order;
import cn.kgc.tangcco.tcbd1017.on.pojo.OrderGoods;
/**
 * 
 * @author 廖斌
 *
 */

public interface OrderDao {
		/**
		 * @return List<Order>
		 * 	查询订单 
		 * @throws SQLException 
		 */
		public List<Order> selectByOrder(Map<String,Object> map) throws SQLException;
		/**
		 * @return int
		 * 	修改订单，修改订单
		 */
		public int updateByOrder( Map<String,Object> map )throws Exception;
		/**
		 * @return int
		 * 	新增订单
		 */
		public int insertByOrder(Map<String,Object> map)throws SQLException;
		
		/**
		 *  查询商品信息订单
		 * 
		 */
		public List<OrderGoods> SelectByOrderGoods (Map<String,Object> map)throws SQLException;
		/**
		 *  新增商品信息订单
		 * 
		 */
		public int insertByOrderGoods(Map<String,Object> map)throws SQLException;
		/**
		 *  修改商品信息订单
		 * 
		 */
		public int updateByOrderGoods(Map<String,Object> map)throws SQLException;
		
		/**
		 *  返回查询订单表的分页总页数
		 *  
		 */
		public int  SelectByOrderPageCount(Map<String,Object> map)throws SQLException;
		/**
		 *  返回查询订单表的分页总页数
		 *  
		 */
		public int  SelectByOrderGoodsPageCount(Map<String,Object> map)throws SQLException;
		
		public List selectShoppingCartInfoByBuyerIdAddUrl(Map<String, Object> map)throws SQLException;
	
		
}
