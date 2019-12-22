package cn.kgc.tangcco.tcbd1017.lo;

import java.util.Map;

/**
 *  用于查询前台下拉框显示商品类型
 * @author CUI
 *
 */
public interface TypeService {

	/**
	 *  查询数据
	 * @param map
	 * @return
	 */
	Map<String ,Object > selectType(Map<String ,Object > map);
}
