package cn.kgc.tangcco.tcbd1017.lo;

import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.tcbd1017.lo.pojo.LogisticsType;


/**
 * 这是邮寄商品类型dao
 * @author CUI
 *
 */
public interface TypeDao {

	/**
	 * 	 查询全部类型，用去前台页面下拉列表展示
	 * @param map
	 * @return
	 */
	List<LogisticsType> selectType(Map<String ,Object> map);
}
