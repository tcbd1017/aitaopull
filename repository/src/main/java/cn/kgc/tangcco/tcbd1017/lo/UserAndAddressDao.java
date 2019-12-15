package cn.kgc.tangcco.tcbd1017.lo;
 
import java.util.List;
import java.util.Map;

/**
 *  用户和地址有关的表
 * @author CUI
 *
 */
public interface UserAndAddressDao {

	
	/**
	 * 	将地址id和用户id添加到中间表中
	 * @param map 地址id 用户id
	 * @return
	 */
	int addAddressidAndUserid(Map<String ,Object> map);
	
	/**
	 *  根据用户查到全部地址id，并返回地址
	 * @param map 
	 * @return
	 */
	List selectAddressByUserid(Map<String ,Object> map);
	

	/**
	 * 	根据地址id删除地址中间表中信息
	 * @param map 地址id 用户id
	 * @return
	 */
	int deleteAddressByAddressid(Map<String ,Object> map);
	
}
