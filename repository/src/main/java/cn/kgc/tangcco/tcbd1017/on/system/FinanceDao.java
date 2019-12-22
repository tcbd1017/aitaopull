package cn.kgc.tangcco.tcbd1017.on.system;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.tcbd1017.on.pojo.SellerBuySetMealInfo;
import cn.kgc.tangcco.tcbd1017.on.pojo.ShopExpenditureInfo;
import cn.kgc.tangcco.tcbd1017.on.pojo.ShopIncomeInfo;

/**
* @author 许佳瑞
* @version 创建时间：2019年12月16日 上午10:01:56
* @edition 1.0
* @Description 类描述
*/
public interface FinanceDao {
	/**
	 * 财务通过卖家购买套餐的状态
	 * 
	 */
	int updateSetMealState(Map<String, Object> map)throws SQLException;
	
	/**
	 * 通过 套餐id获得套餐价钱
	 */
	Map<String, Object> selectmany(Map<String, Object>map)throws SQLException;
	/**
	 * 并添加添加收入情况（套餐）
	 */
	int insertIncome(Map<String, Object>map)throws SQLException;
	
	/**
	 * 查看公司收入
	 */
	List<ShopIncomeInfo>selectIncome(Map<String, Object>map)throws SQLException;
	
	/**
	 * 查看公司支出
	 */
	List<ShopExpenditureInfo>selectExpenditure(Map<String, Object>map)throws SQLException;
	
	/**
	 * 查看卖家买套餐
	 */
	List<SellerBuySetMealInfo>selectSellerBuySetMealInfo(Map<String, Object>map)throws SQLException;
	/**
	 * 未审核人数
	 */
	Map<String, Object>selectSellerBuySetMealInfocount(Map<String, Object>map)throws SQLException;
	/**
	 * 分配员工
	 * 
	 */
	int updatePersonInCharge(Map<String, Object>map)throws SQLException;
	/**
	 * 查询收入总记录数
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	int selectIncomecount(Map<String, Object> map) throws SQLException;
	/**
	 * 查询支出总记录数
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	int selectExpenditurecount(Map<String, Object> map) throws SQLException;
	
}