package cn.kgc.tangcco.tcbd1017.on.system;

import java.sql.SQLException;

import cn.kgc.tangcco.tcbd1017.on.pojo.Emp;
import cn.kgc.tangcco.tcbd1017.on.pojo.EmpAllInfo;



/**
	 * @author XUE TONG
	 * @version 1.0 2019年12月24日下午3:44:54
	 * </br>
	 **/

public interface EmpDao {

	/**
	 * 查询登录者的详细信息和部门id
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	EmpAllInfo selectEmp(Emp emp)throws SQLException;
	
}
