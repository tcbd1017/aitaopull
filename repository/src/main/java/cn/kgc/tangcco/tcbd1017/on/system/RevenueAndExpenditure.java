package cn.kgc.tangcco.tcbd1017.on.system;
/**
* @author 许佳瑞
* @version 创建时间：2019年12月21日 上午9:03:29
* @edition 1.0
* @Description 类描述
*/

import java.sql.SQLException;
import java.util.List;

public interface RevenueAndExpenditure {
//	收入表
	List<Object>selectRevenue()throws SQLException;
	/**
	 * 支出表
	 */
	List<Object>selectExpenditure()throws SQLException;
}
