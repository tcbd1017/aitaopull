package cn.kgc.tangcco.tcbd1017.on.system;

import java.sql.SQLException;
import java.util.Map;

import cn.kgc.tangcco.tcbd1017.on.pojo.EmpAllInfo;

/**
 * 
 * @author zhangmiao
 * @version	2019年12月16日	上午9:44:27
 *
 */
public interface UpdateEmpAllInfo {
	
	/**
	 * 员工个人信息修改功能
	 * @param map 员工需要修改的数据
	 * @return 员工的所有信息
	 * @throws SQLException
	 */
	int updateEmpAllInfo(Map<String,  Object> map)throws SQLException;
	
}
