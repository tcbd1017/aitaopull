package cn.kgc.tangcco.tcbd1017.on.system;

import java.sql.SQLException;
import java.util.Map;

/**
* @author 许佳瑞
* @version 创建时间：2019年12月14日 下午7:33:02
* @edition 1.0
* @Description 类描述
*/

public interface PersonnelMattersDao {
		
	/**
	 * 
	 * 办理离职
	 */
	int delecteandupdateEmp(Map<String, Object> map);
	
	/**
	 * 分配员工部门
	 * 
	 */
	int updateEmpdept(Map<String, Object> map);
	
//	/**
//	 * 员工的升降职
//	 */
//	int updateEmpJurisdiction(Map<String, Object>map)throws SQLException;
}
	