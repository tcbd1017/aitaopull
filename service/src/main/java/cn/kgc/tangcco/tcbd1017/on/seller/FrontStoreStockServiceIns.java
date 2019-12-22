package cn.kgc.tangcco.tcbd1017.on.seller;

import java.util.Map;

/** 
* @author 作者 Your-Name: 刘煜
* @version 创建时间：2019年12月16日 上午9:51:48 
*    类说明 
*/
public interface FrontStoreStockServiceIns {
	/**
	   *   进货 
	 * @return
	 */
	Map<String,Object> add_Store_Stock_Goods(Map<String,Object> map);
}
