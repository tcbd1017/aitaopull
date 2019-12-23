package cn.kgc.tangcco.dao;

import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.pojo.Emp;

public interface EmpDao {
	
	/**
	 * 员工登录
	 * 1登录成功
	 *  把登陆者信息 读取出来 存在session
	 * 2跳转页面
	 * 3在页面js中读取登陆者id然后显示功能列表（注意功能列表自带路径）
	 * 通过三表联查           
	 * 
	 * @param map
	 * @return
	 */
	public List<Object> loginEmp(Map<String,Object>map);
	
	
	/**
	    * 查询所有的入货员和管理员   emp 和 role 
	 * @param map
	 * @return
	 */
	public List<Emp> selectAllEmp(Map<String,Object>map);
}
