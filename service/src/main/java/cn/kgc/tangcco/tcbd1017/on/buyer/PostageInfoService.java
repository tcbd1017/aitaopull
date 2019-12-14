package cn.kgc.tangcco.tcbd1017.on.buyer;

import java.util.Map;

/**
 * @author LIU KAI
 * @version 1.0 2019年12月14日 下午1:32:06 </br>
 */

public interface PostageInfoService {

	/**
	 * 查询所有收件人信息
	 * 
	 * @param map
	 * @return
	 */
	public Map<String, Object> queryPostageInfosByBuyerId(Map<String, Object> map);

	/**
	 * 新增收件人信息
	 * 
	 * @param map
	 * @return
	 */
	public Map<String, Object> addPostageInfo(Map<String, Object> map);

	/**
	 * 修改收件人信息
	 * 
	 * @param map
	 * @return
	 */
	public Map<String, Object> modifyPostageInfo(Map<String, Object> map);

	/**
	 * 修改默认收件人信息
	 * 
	 * @param map
	 * @return
	 */
	public Map<String, Object> modifyPostageInfosByStatus(Map<String, Object> map);

	/**
	 * 删除收件人信息
	 * 
	 * @param map
	 * @return
	 */
	public Map<String, Object> removePostageInfosByStatus(Map<String, Object> map);

}
