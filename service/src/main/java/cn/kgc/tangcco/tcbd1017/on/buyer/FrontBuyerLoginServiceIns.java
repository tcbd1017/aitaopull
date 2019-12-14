package cn.kgc.tangcco.tcbd1017.on.buyer;

import java.util.Map;

/** 
* @author 作者 Your-Name: 刘煜
* @version 创建时间：2019年12月11日 下午8:18:33 
*    类说明 
*/
public interface FrontBuyerLoginServiceIns {
	/**
	   * 买家用户登录
	 * @param maps
	 * @return 买家唯一标识符uuid
	 */
	Map<String,Object> Front_Buyer_Login(Map<String,Object> maps);
}
