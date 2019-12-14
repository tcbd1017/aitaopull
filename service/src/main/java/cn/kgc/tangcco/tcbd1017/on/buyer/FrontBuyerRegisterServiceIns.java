package cn.kgc.tangcco.tcbd1017.on.buyer;

import java.sql.SQLException;
import java.util.Map;

/** 
* @author 作者 Your-Name: 刘煜
* @version 创建时间：2019年12月10日 下午7:32:39 
*    类说明 
*/
public interface FrontBuyerRegisterServiceIns {
	/**
	    *    买家用户注册
	 * @return
	 * @throws SQLException 
	 */
	Map<String,Object> BuyerRegister(Map<String,Object> map) throws SQLException;
}
