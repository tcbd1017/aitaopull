package cn.kgc.tangcco.tcbd1017.on.buyer;
/**
 * 
 * @author 刘凯
 * @version 1.0 <br>
 *          创建时间:2019年12月10日 下午2:25:55 <br>
 *          类描述: 收货人信息dao
 */

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.tcbd1017.on.pojo.Address;
import cn.kgc.tangcco.tcbd1017.on.pojo.Buyer;
import cn.kgc.tangcco.tcbd1017.on.pojo.PostageInfo;

public interface PostageInfoDao {
	/**
	 * 根据买家id查询所有的收货人信息
	 * 
	 * @param map
	 * @return
	 */
	public List<PostageInfo> selectPostageInfosByBuyerId(Map<String, Object> map) throws SQLException;

	/**
	 * 查询地址的详细信息
	 * 
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public Address selectAddress(Map<String, Object> map) throws SQLException;

	/**
	 * 查询所有地址的详细信息
	 * 
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public List<Address> selectAllAddress(Map<String, Object> map) throws SQLException;

	/**
	 * 新增收件人信息
	 * 
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int insertPostageInfo(Map<String, Object> map) throws SQLException;

	/**
	 * 新增收件人信息id和买家id中间表信息
	 * 
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int insertbuyerAndPostageInfo(Map<String, Object> map) throws SQLException;


	/**
	 * 查询为默认收件地址的收件人信息
	 * @return
	 * @throws SQLException
	 */
	public PostageInfo selectPostageInfoByStatus(Map<String, Object> map) throws SQLException;

	
	/**
	 * 修改收件人信息
	 * 
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int updatePostageInfo(Map<String, Object> map) throws SQLException;

	/**
	 * 修改默认地址或删除地址
	 * 
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int updatePostageInfosByStatus(Map<String, Object> map) throws SQLException;

}
