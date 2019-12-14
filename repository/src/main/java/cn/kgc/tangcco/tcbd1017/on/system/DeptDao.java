package cn.kgc.tangcco.tcbd1017.on.system;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
* @author 作者 : 廖斌
* @version 创建时间：Dec 14, 2019 9:33:08 AM
* 	
*/
public interface DeptDao {
	/**
	 *  查找部门
	 * @author 廖斌
	 * @return map 
	 */
	public List selectByDept( Map map ) throws  SQLException;
	
	/**
	 *  增加部门
	 * @author 廖斌
	 * @return map 
	 */
	
}
