package cn.kgc.tangcco.tcbd1017.on.system;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.tcbd1017.on.pojo.EmpAllInfo;

/**
* @author 作者 : 廖斌
* @version 创建时间：Dec 14, 2019 9:33:08 AM
* 	
*/
public interface DeptDao {
	/**
	 *  	查找部门
	 * @author 廖斌
	 * @return map 
	 */
	public List<EmpAllInfo> selectByDept( Map map ) throws  SQLException;
	
	/**
	 *  	修改部门
	 * @author 廖斌
	 * @return map 
	 */
	public int updateByDept(Map map)throws SQLException;
	
	/**
	 * 	增加部门
	 * 
	 */
	public int insertByDept(Map map)throws SQLException;
	
}
