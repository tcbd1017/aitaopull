package cn.kgc.tangcco.tcbd1017.lo;

import java.util.Map;

/**
 *  用户注册登录相关接口
 * @author CUI
 *
 */
public interface UserDao {

	/**
	 * 	添加用户
	 * @param map 姓名  账号 密码 创建时间 手机号 身份证号 
	 * @return
	 */
	int addUser(Map<String ,Object> map);
	
	/**
	 * 修改用户信息 
	 * @param map 姓名 手机号 密码
	 * @return
	 */
	int updateUser(Map<String ,Object> map);
}
