package cn.kgc.tangcco.tcbd1017.on.system;

import java.sql.SQLException;

import cn.kgc.tangcco.tcbd1017.on.pojo.Emp;
import cn.kgc.tangcco.tcbd1017.on.pojo.EmpLogin;

/**
 * 
 * @author zhangmiao
 * @version	2019年12月14日	下午6:40:26
 *
 */
public interface EmpLoginToEmp {
	
	/**
	 * 通过登录信息查找返回对象信息
	 * @param empLogin 登录pojo对象
	 * @return 登录的Emp对象
	 * @throws SQLException
	 */
	Emp loginToEmp(EmpLogin empLogin)throws SQLException;
}
