package cn.kgc.tangcco.tcbd1017.lo;

import java.util.Map;


/**
 * @author 王立宁
 * @version 1.0 <br>
 * 创建时间：2019年12月13日上午9:12:00
 * 类描述：
 */
public interface UserService {
	/**
	 * 	添加用户
	 * @param map 姓名  账号 密码 创建时间 手机号 身份证号 
	 * @return
	 */
	Map<String, Object> addUser(Map<String ,Object> map);
	
	/**
	 * 修改用户信息 
	 * @param map 姓名 手机号 密码
	 * @return
	 */
	Map<String, Object> updateUser(Map<String ,Object> map);
	/**
	 * 用户登录
	 * @param map 姓名 手机号 密码
	 * @return  登陆对象
	 */
	Map<String, Object> selectUser(Map<String ,Object> map);
}
