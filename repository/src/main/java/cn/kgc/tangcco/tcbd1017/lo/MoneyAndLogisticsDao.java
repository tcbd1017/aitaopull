
package cn.kgc.tangcco.tcbd1017.lo;

import java.util.Map;

/**
 * 	账单与运单关联的接口
 * @author CUI
 *
 */
public interface MoneyAndLogisticsDao {

	

	/**
	 * 根据物流id
	 * @param map 物流id
	 * @return 成功或失败
	 */
	int updateMoneyByLogisticsId(Map<String ,Object> map);
	
	/**
	 * 将物流id和账单id添加到中间表
	 * @param map
	 * @return
	 */
	int addMoneyAndLogistics(Map<String ,Object> map);
}
