
package cn.kgc.tangcco.tcbd1017.lo;

import java.util.List;
import java.util.Map;

/**
 * 跟地址有关的接口
 * @author CUI
 *
 */
public interface AddressDao {

	
	/**
	 *  用户添加地址
	 * @param map 地址随机数id 三级联动地址 详细地址 姓名 电话
	 * @return
	 */
	int addAddress(Map<String ,Object> map);
	
	/**
	 * 	根据地址id修改地址 姓名 电话
	 * @param map 地址 姓名 电话
	 * @return 
	 */
	int updateAddressByAddressId(Map<String ,Object> map);
	
//	/**
//	 * 	根据地址id删除地址
//	 * @param map 地址
//	 * @return 
//	 */
//	int deleteAddressByAddress(Map<String ,Object> map);
}
