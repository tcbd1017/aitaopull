package cn.kgc.tangcco.tcbd1017.on.buyer;

import java.sql.SQLException;
import java.util.Map;

/** 
* @author 作者 Your-Name: 刘煜
* @version 创建时间：2019年12月11日 下午6:34:45 
*    类说明 : 买家用户登录
*/
public interface FrontBuyerLoginDaoIns {
	/**
	    *  买家用户账号 ，密码，人脸登录
	 * @param map(买家账号 ，密码，人脸唯一token )
	 * @return 买家 uuid
	 * @throws SQLException 
	 */
	Map<String,Object> select_Buyer_Login_Account_Token(Map<String,Object> map) throws SQLException;
	/**
	   *  买家用户手机号登录
	 * @param map
	 * @return 买家 uuid
	 * @throws SQLException
	 */
	Map<String,Object> select_Buyer_Login_buyer_mobile(Map<String,Object> map) throws SQLException;
	
}
