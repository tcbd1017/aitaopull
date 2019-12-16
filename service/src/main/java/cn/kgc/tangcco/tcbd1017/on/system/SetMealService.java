package cn.kgc.tangcco.tcbd1017.on.system;

import java.util.Map;

import cn.kgc.tangcco.tcbd1017.on.pojo.SetMeal;

/**
 * 
 * @author 薛彤
 * @version 1.0 2019年12月14日 上午10:36:39
 *
 */

public interface SetMealService {
	/**
	 * 查询所有
	 * @param map
	 * @return
	 */
	Map<String, Object> querySetMeal(Map<String, Object> map);

	/**
	 * 查询指定
	 * @param set_meal_id
	 * @return
	 */
	Map<String, Object> findSetMeal(int set_meal_id);

	/**
	 * 增加
	 * @param setMeal
	 * @return
	 */
	Map<String, Object> addSetMeal(Map<String, Object> map);

	/**
	 * 修改
	 * @param setMeal
	 * @return
	 */
	Map<String, Object> updateSetMeal(SetMeal setMeal);

	/**
	 * 删除
	 * @param set_meal_id
	 * @return
	 */
	Map<String, Object> removeSetMeal(int set_meal_id);
}
