package cn.kgc.tangcco.tcbd1017.st;

import java.util.Map;

public interface ShangJiaAndEMpmLogin {
	
	/**
	 * 商家登录           
	 *      ShangJiChuKuService    loginShop（）
	 *      如果登录成功     把function表的信息读出来（上面那个方法中已包含）
	 * 登录成功      
	 * @param map
	 * @return 
	 */
	public Map<String,Object> ShopLogin(String account,String password);
	
	/**
	 * 注册              ShangJiChuKuService      insertShop（）
	 */
	public Map<String,Object> ShopZhuCe(Map<String,Object>map);
	
	/**
	 * 修改密码          修改成功  跳转页面到  从新登陆 
	 */
	public Map<String,Object> XiuGaiMiMa(Map<String,Object>map);
	
	/**
	 * 员工登录     
	 */
	public Map<String,Object> EmpLogin(String account,String password);
	
	
	
	
}
