package cn.kgc.tangcco.tcbd1017.on.system;
/**
 * 
 * @author zhangmiao
 * @version	2019年12月15日	下午2:59:44
 *
 */

import java.util.Map;

import cn.kgc.tangcco.tcbd1017.on.pojo.Emp;
import cn.kgc.tangcco.tcbd1017.on.pojo.EmpAllInfo;

public interface FindEmpAllInfo {
	
	/**
	 * 查找emp对象的所有相关信息
	 * @param emp emp对象
	 * @return EmpAllInfo对象信息
	 */
	Map<String, Object> findEmpAllInfo(Emp emp);
}
