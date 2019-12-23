package cn.kgc.tangcco.tcbd1017.on.seller;

import java.sql.SQLException;
import java.util.Map;

/** 
* @author 作者 Your-Name: 刘煜
* @version 创建时间：2019年12月14日 上午10:01:21 
*    类说明 
*/
public interface FrontStoreRegisterServiceIns {
	/**
	 *   商户开店
	 * @return 返回注册信息
	 * @throws SQLException 
	 */
	Map<String,Object> add_Seller_Register(Map<String,Object> maps) ;
	/**
	 * @param maps
	 * @return
	 * @throws SQLException
	 */
	Map<String,Object> add_Store_Register(Map<String,Object> maps) ;
}
