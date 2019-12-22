package cn.kgc.tangcco.tcbd1017.on.buyer;

import java.sql.SQLException;
import java.util.Map;
/**
 * 
 * @author Administrator 朱浩
 *
 */
public interface BuyerInfoService {
	/**
	 * 通过买家id   Buyer_id 查询所有个人信息
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public Map<String, Object> queryBuyerInfoByBuyer_id(Map<String, Object>map) throws SQLException ;
	/**
	 * 根据买家id  修改删除
	 * @param map
	 * @return
	 */
	public Map<String, Object> removeAndModify_BuyerInfoBybuyer_id(Map<String, Object> map);
	
	/**
	 * 增加
	 * @param map
	 * @return
	 */
	public Map<String, Object> addBuyerInfo(Map<String, Object> map);
}
