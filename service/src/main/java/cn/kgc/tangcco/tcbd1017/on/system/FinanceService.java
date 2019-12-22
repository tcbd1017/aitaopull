package cn.kgc.tangcco.tcbd1017.on.system;


import java.util.Map;



/**
* @author 许佳瑞
* @version 创建时间：2019年12月16日 下午3:18:35
* @edition 1.0
* @Description 类描述
*/
public interface FinanceService {
	/**
	 * 通过卖家购买套餐的情况
	 * 
	 */
	Map<String, Object> modifySetMealState(Map<String, Object> map);
	/**
	 * 查看公司收入
	 */
	Map<String, Object>queryIncome(Map<String, Object>map1);
	
	/**
	 * 查看公司支出
	 */
	Map<String, Object>queryExpenditure(Map<String, Object>map1);
	
	/**
	 *  查看未审核人数总和和详细信息
	 * 
	 */
	Map<String, Object>queryNumberAndDetailed(Map<String, Object>map);
	
	/**
	 * 
	 * 分配任务给员工
	 */
	Map<String, Object>modifyPersonInCharge(Map<String, Object>map);
	
}
