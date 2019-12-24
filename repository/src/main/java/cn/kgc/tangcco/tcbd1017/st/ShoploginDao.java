package cn.kgc.tangcco.tcbd1017.st;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;



public interface ShoploginDao {

//	/**
//	 * 注测商家
//	 * 
//	 * 
//	 * @param map  手机号   商家姓名   uuid唯一标识符     商家所在的    密码    参数填写完毕自动生成账号  
//	 * @return
//	 */
//	public Map<String,Object> zhuce(Map<String,Object>map);
	
	/**
	 * 注册成功插入数据
	 * @param map
	 * @return
	 */
	public int insertShop(Map<String,Object>map) throws SQLException;
	
	/**
	 * 商家登录
	 * 1登录成功
	 *  把登陆者信息 读取出来 存在session
	 * 2跳转页面
	 * 3在页面js中读取登陆者uuid然后显示功能列表（注意功能列表自带路径）
	 * @param map
	 * @return
	 */
	public List<Object> loginShop(Map<String,Object>map);
	
	
	/**
	 * 员工登录
	 */
	public List<Object> empLogin(Map<String,Object>map);
	
	/**
	 * 修改密码
	 * 根据短信验证
	 */
	public int updateMiMa(Map<String,Object>map);
	
}
