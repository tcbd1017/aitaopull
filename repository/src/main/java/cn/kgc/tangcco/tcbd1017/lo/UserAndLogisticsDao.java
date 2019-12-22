package cn.kgc.tangcco.tcbd1017.lo;

import java.util.List;
import java.util.Map;

/**
 * 用户和订单有关的接口
 * @author CUI
 *
 */
public interface UserAndLogisticsDao {

	
	/**
	 * 	将订单id和用户id添加到中间表中
	 * @param map 订单id，用户id
	 * @return 成功或失败
	 */
	int addUseridAndLogisticsId(Map<String ,Object> map);
	
	
	/**
	 *
	 * 	根据用户id查找用户所有订单(用于前台展示用户的所有订单)
	 * @param map 用户id
	 * @return 订单id或null
	 */
	List selectLogisticsIdByUserId(Map<String ,Object> map);
	
	/**
	 * 根据用户id查找到全部账单
	 * @param map
	 * @return
	 */
	List selectMoneyByUserId(Map<String ,Object> map);
	
	/**
	 * 	查询全部订单
	 * @param map
	 * @return
	 */
	List selectLogisticsByStutas(Map<String ,Object> map);
}
