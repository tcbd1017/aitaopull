package cn.kgc.tangcco.tcbd1017.on.system;

import java.sql.SQLException;
import java.util.Map;

import cn.kgc.tangcco.tcbd1017.on.pojo.Emp;

/**
 * 
 * @author zhangmiao
 * @version	2019年12月13日	下午4:50:25
 *
 */
public interface SelectEmpPower {
	
	/**
	 * 查看员工权限等级
	 * @param emp 员工信息
	 * @return 权限等级最高级为3
	 * @throws SQLException
	 */
	Map<String, Object> selectEmpPower(Emp emp) throws SQLException;
}
