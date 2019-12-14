package cn.kgc.tangcco.tcbd1017.lo;

import java.util.Map;

/**
 * 
 * @author CUI
 *
 */
public interface AddressService {

	/**
	 *  根据userid查询自己对应的地址
	 * @param map
	 * @return
	 */
	Map<String ,Object> selectByUserId(Map<String ,Object > map);
	
	/**
	 * 根据地址id修改地址信息
	 * @param map
	 * @return
	 */
	Map<String ,Object> updateByAddressId(Map<String ,Object > map);
	
	/**
	 * 根据地址id删除地址信息
	 * @param map
	 * @return
	 */
	Map<String ,Object> deleteByAddressId(Map<String ,Object > map);
	
	
	/**
	 *	 添加地址
	 * @param map
	 * @return
	 */
	Map<String ,Object> addByUserId(Map<String ,Object > map);
}
