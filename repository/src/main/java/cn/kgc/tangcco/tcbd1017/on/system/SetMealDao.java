package cn.kgc.tangcco.tcbd1017.on.system;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.tcbd1017.on.pojo.SetMeal;


/**
	 * 
	 * @author 薛彤
	 * @version 1.0 2019年12月13日 下午1:38:11
	 *
	 */

public interface SetMealDao {
	/**
	 * 查询所有
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	List<SetMeal> selectAllSetMeal(Map<String, Object> map) throws SQLException;
	/**
	 * 查询指定
	 * @param set_meal_id
	 * @return
	 * @throws SQLException
	 */
	SetMeal selectSetMeal(int set_meal_id) throws SQLException;
	/**
	 * 添加
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	int insertSetMeal(Map<String ,Object> map) throws SQLException;
	/**
	 * 修改
	 * @param setMeal
	 * @return
	 * @throws SQLException
	 */
	int updateSetMeal(SetMeal setMeal) throws SQLException;
	/**
	 * 删除
	 * @param set_meal_id
	 * @return
	 * @throws SQLException
	 */
	int deleteSetMeal(Map<String, Object> map) throws SQLException;
}