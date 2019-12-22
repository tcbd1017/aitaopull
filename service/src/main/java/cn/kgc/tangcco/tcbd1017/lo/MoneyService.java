package cn.kgc.tangcco.tcbd1017.lo;

import java.util.Map;
/**
 * 
 * @author CUI
 *
 */
public interface MoneyService {

	/**
	 * 根据user的id查询账单
	 * @param map
	 * @return
	 */
	Map<String ,Object> selectLogisticsByUserId(Map<String ,Object > map);
	
	/**
	 * 	根据id修改账单状态
	 * @param map
	 * @return
	 */
	Map<String ,Object> payByMoneyId(Map<String ,Object > map);
	
}
