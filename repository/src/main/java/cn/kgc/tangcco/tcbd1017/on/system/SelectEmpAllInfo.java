package cn.kgc.tangcco.tcbd1017.on.system;
/**
 * 
 * @author zhangmiao
 * @version	2019年12月14日	下午4:00:37
 *
 */

import java.sql.SQLException;

import cn.kgc.tangcco.tcbd1017.on.pojo.Emp;
import cn.kgc.tangcco.tcbd1017.on.pojo.EmpAllInfo;

public interface SelectEmpAllInfo {
	
	/**
	 * 用户登录后查询用户所有相关信息
	 * @param emp 登录用户
	 * @return 用户所有相关信息
	 * @throws SQLException
	 */
	EmpAllInfo selectEmpAllInfo(Emp emp) throws SQLException;
	
}
