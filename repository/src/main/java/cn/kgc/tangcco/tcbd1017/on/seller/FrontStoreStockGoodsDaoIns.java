package cn.kgc.tangcco.tcbd1017.on.seller;

import java.sql.SQLException;
import java.util.Map;

/** 
* @author 作者 Your-Name: 刘煜
* @version 创建时间：2019年12月16日 上午9:04:26 
*    类说明 ：商店进货
*/
public interface FrontStoreStockGoodsDaoIns {
	/**
	   *  商家进货
	 * @param map
	 * @return
	 * @throws SQLException 
	 */
	int insertStockGoods(Map<String,Object> map) throws SQLException;
}
