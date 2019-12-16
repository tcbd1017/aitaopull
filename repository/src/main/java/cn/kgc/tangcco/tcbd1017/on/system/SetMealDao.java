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
	 * 查询所有信息
	 * @param map   查询条件及分页
	 * @return    信息集合
	 * @throws SQLException
	 */
	List<SetMeal> selectAllSetMeal(Map<String, Object> map) throws SQLException;
	/**
	 * 查询总记录数
	 * @param map   查询条件
	 * @return    总记录数
	 * @throws SQLException
	 */
	int selectCountSetMeal(Map<String, Object> map) throws SQLException;
	/**
	 * 查询指定信息
	 * @param set_meal_id   平台套餐主键id
	 * @return    套餐信息
	 * @throws SQLException
	 */
	SetMeal selectSetMeal(int set_meal_id) throws SQLException;
	/**
	 * 添加信息
	 * @param map
	 * @return   成功或失败
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
	int deleteSetMeal(int set_meal_id) throws SQLException;
}