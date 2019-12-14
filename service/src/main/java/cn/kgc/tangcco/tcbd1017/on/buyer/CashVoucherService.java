package cn.kgc.tangcco.tcbd1017.on.buyer;

import java.util.Map;

/**
 * @author 赵瑞涛
 * @version v1.0<br>
 * 	创建时间:	2019年12月13日	下午3:17:14<br>
 * 	类描述:
 */
public interface CashVoucherService {
	/**
	 * 					查询所有未过期
	 * @param map		map查询参数
	 * @return			查询的状态、数据
	 */
	public Map<String, Object> queryByIdAndStatus(Map<String, Object> map);
	/**
	 * 					查询总数
	 * @param map		map查询参数的集合
	 * @return			查询的状态和数据
	 */
	public Map<String, Object> queryNumberByIdAndStatus(Map<String, Object> map);
	/**
	 * 					删除某个优惠券
	 * @param map		map删除参数的集合
	 * @return			删除的状态
	 */
	public Map<String, Object> removeByUuid(Map<String, Object>map);
	/**
	 * 					分页查询
	 * @param map		map查询参数的集合
	 * @return			参数的状态
	 */
	public Map<String, Object> queryByIdAndStatusAndPagereng(Map<String, Object>map);
	
}
