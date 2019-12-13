package cn.kgc.tangcco.tcbd1017.on;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.tcbd1017.on.pojo.Order;
/**
 * 
 * @author 廖斌
 *
 */

public interface OrderDao6 {
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
		public int updateByOrder( Map<String,Object> map )throws SQLException;
		/**
		 * @return int
		 * 	新增订单
		 */
		public int insertByOrder(Map<String,Object> map)throws SQLException;
}
