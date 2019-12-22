package cn.kgc.tangcco.tcbd1017.lo;

import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.tcbd1017.lo.pojo.Logistics;


public interface GoodsDao {

	
	/**
	 * 	将订单id和用户id添加到中间表中
	 * @param map 订单id，用户id
	 * @return 成功或失败
	 */
	int addUseridAndLogisticsId(Map<String ,Object> map);
	
	
	/**
	 * 	根据uuid查询全部运单
	 * @param map
	 * @return
	 */
	List<Logistics> selectAllLogisticsByUserId(Map<String ,Object> map);
	
}
