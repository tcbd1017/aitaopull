package cn.kgc.tangcco.tcbd1017.on.system;

import java.sql.SQLException;
import java.util.Map;

import cn.kgc.tangcco.tcbd1017.on.pojo.Emp;

/**
	 * @author XUE TONG
	 * @version 1.0 2019年12月24日下午4:22:25
	 * </br>
	 **/

public interface EmpService {

	/**
	 * 查询登录者的详细信息和部门id
	 * @param emp
	 * @return
	 * @throws SQLException
	 */
	Map<String, Object> selectEmp(Emp emp)throws SQLException;
}
