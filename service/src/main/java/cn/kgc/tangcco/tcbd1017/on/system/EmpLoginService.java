package cn.kgc.tangcco.tcbd1017.on.system;

import java.sql.SQLException;
import java.util.Map;


/**
	 * @author XUE TONG
	 * @version 1.0 2019年12月19日下午3:10:41
	 * </br>
	 **/

public interface EmpLoginService {

	/**
	 * 员工登录
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	Map<String, Object> selectEmpLoginAccountAndPassword(Map<String, Object> map) throws SQLException;
}
