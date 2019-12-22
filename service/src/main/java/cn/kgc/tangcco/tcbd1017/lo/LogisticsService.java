package cn.kgc.tangcco.tcbd1017.lo;

import java.util.Map;

/**
 *  
 * @author CUI
 *
 */
public interface LogisticsService {

	/**
	 * 运单查询
	 * 根据运单id查询运单状态
	 * @param map
	 * @return
	 */
	Map<String ,Object> selectLogisticsByLogisticsId(Map<String ,Object > map);
	
	/**
	 * 
	 * 我的订单
	 * 根据userid查询到所有的订单信息
	 * @param map
	 * @return
	 */
	Map<String ,Object> selectLogisticsByUserId(Map<String ,Object > map);
	
	
	
	/**
	 * 
	 * 	我的订单
	 * 	根据userid查询到所有的订单信息
	 * @param map
	 * @return
	 */
	Map<String ,Object> selectAllLogisticsByUserId(Map<String ,Object > map);
	
	/**
	 * 在线下单
	 * 向订单表添加信息，同时向账单表添加信息
	 * @param map
	 * @return
	 */
	Map<String ,Object> addLogistics(Map<String ,Object > map);
	
	/**
	 * 	根据运单id查询运单详情
	 * @param map
	 * @return
	 */
	Map<String ,Object> selectLogisticsIdByLogistics(Map<String ,Object > map);
	
	
	
}
