package cn.kgc.tangcco.tcbd1017.on.system;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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
		 * 通过卖家购买套餐的情况
		 * 
		 */
		int updateSetMealState(Map<String, Object> map)throws SQLException;
		
		/**
		 * 添加收入情况（套餐）
		 */
		int insertIncome(Map<String, Object>map)throws SQLException;
		
		/**
		 * 查看公司收入
		 */
		List<ShopIncomeInfo>selectIncome()throws SQLException;
		
		/**
		 * 查看公司支出
		 */
		List<ShopExpenditureInfo>selectExpenditure()throws SQLException;
	}
