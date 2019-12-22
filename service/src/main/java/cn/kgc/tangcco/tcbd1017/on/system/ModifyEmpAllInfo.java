package cn.kgc.tangcco.tcbd1017.on.system;
/**
 * 
 * @author zhangmiao
 * @version	2019年12月18日	上午11:28:55
 *
 */

import java.util.Map;

public interface ModifyEmpAllInfo {
	/**
	 * 所有对象个人的所有信息的修改方法
	 * @param map 对象信息，和对象要修改的信息
	 * @return 修改情况
	 */
	Map<String, Object> modifyEmpAllInfo(Map<String, Object> map);
}
