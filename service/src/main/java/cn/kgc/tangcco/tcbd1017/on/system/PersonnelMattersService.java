package cn.kgc.tangcco.tcbd1017.on.system;

import java.util.Map;

/**
* @author 许佳瑞
* @version 创建时间：2019年12月15日 下午3:01:46
* @edition 1.0
* @Description 类描述
*/
public interface PersonnelMattersService {
	
	/**
	 * 删除员工
	 * @param map
	 * @return
	 */
	Map<String, Object>removeandmodifyEmp (Map<String, Object>map);
	
	/**
	 * 根据权限修改员工部门
	 * @param map
	 * @return
	 */
	Map<String, Object>modifyEmpDept( Map<String, Object> map);
	
//	/**
//	 * 员工升降职
//	 */
//	Map<String, Object>modifyEmpJurisdiction(Map<String, Object>map);
	
}
