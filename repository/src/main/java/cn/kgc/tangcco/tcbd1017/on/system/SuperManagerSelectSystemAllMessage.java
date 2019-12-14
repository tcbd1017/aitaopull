package cn.kgc.tangcco.tcbd1017.on.system;
/**
 * 
 * @author zhangmiao
 * @version	2019年12月13日	下午4:08:00
 *
 */

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import cn.kgc.tangcco.tcbd1017.on.pojo.Emp;

public interface SuperManagerSelectSystemAllMessage {
	
	/**
	 * 超级管理员查看所有商城后台信息	
	 * @param service层传进的连接
	 * @return 所有商城信息
	 * @throws SQLException
	 */

	Map<String, Object> selectSystemAllMessage(Connection conn) throws SQLException;
	
	/**
	 * 
	 * @param emp
	 * @return
	 * @throws SQLException
	 */
	
}
