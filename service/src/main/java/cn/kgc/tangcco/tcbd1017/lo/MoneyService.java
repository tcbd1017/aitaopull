package cn.kgc.tangcco.tcbd1017.lo;

import java.util.Map;
 
public interface MoneyService {

	/**
	 * 根据user的id查询账单
	 * @param map
	 * @return
	 */
	Map<String ,Object> selectLogisticsByUserId(Map<String ,Object > map);
	
}
