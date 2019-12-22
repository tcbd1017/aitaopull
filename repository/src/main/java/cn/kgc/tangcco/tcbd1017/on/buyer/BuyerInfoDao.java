package cn.kgc.tangcco.tcbd1017.on.buyer;
/**
 *  @author 朱浩
 *  @version 1.0  2019.12.16
 */

import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.tcbd1017.on.pojo.BuyerInfo;

public interface BuyerInfoDao {
	/**
	 * 查询所有
	 * @param map
	 * @return
	 */
	public List<BuyerInfo> selectAllBuyerInfosByBuyer_id(Map<String, Object> map);
	/**
	 * 增加  
	 * 
	 * @param map
	 * @return
	 */
	public  int  insertBuyerInfo(Map<String, Object>map) ;
	
	public  int  update(Map<String, Object> map);
}
