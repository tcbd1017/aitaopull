package cn.kgc.tangcco.tcbd1017.lo;

import java.util.List;
import java.util.Map;


public interface GoodsService {

	
	
	/**
	 * 	添加订单并返回订单全部信息
	 * @param map 订单id，用户id
	 * @return 成功或失败
	 */
	Map<String ,Object> addUseridAndLogisticsId(Map<String ,Object> map);
	
	
	/**
	 * 	根据uuid查询全部运单
	 * @param map
	 * @return
	 */
	Map<String ,Object> selectAllLogisticsByUserId(Map<String ,Object> map);
	
	
	/**
	 * 	根据运单id查询运单详情
	 * @param map
	 * @return
	 */
	Map<String ,Object> selectLogisticsIdByLogistics(Map<String ,Object > map);
	
}
