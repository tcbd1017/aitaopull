package cn.kgc.tangcco.tcbd1017.on.system;

import java.sql.SQLException;
import java.util.Map;

import cn.kgc.tangcco.tcbd1017.on.pojo.EmpLogin;



/**
	 * @author XUE TONG
	 * @version 1.0 2019年12月19日下午1:44:46
	 * </br>
	 **/

public interface EmpLoginDao {

	/**
	 * 员工登录
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	EmpLogin selectEmpLoginAccountAndPassword(Map<String, Object> map) throws SQLException;
}
