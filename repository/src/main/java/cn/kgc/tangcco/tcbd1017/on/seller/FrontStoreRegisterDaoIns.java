package cn.kgc.tangcco.tcbd1017.on.seller;

import java.sql.SQLException;
import java.util.Map;

/** 
* @author 作者 Your-Name: 刘煜
* @version 创建时间：2019年12月13日 上午10:58:55 
*    类说明 : 
*/

public interface FrontStoreRegisterDaoIns {
	/**
	   *  商家个人信息注册（店铺注册）
	 * @return  返回商户的是否注册成功
	 * @throws SQLException 
	 */
	int insert_seller_Register(Map<String,Object> maps) throws SQLException;
	/**
	   *  商家店铺信息注册（店铺注册）
	 * @return  返回商户的是否注册成功
	 * @throws SQLException 
	 */
	int insert_Store_Register(Map<String,Object> maps) throws SQLException;
	
	/**
	 * 判断seller_uuid是否重复
	 * @param maps
	 * @return
	 */
	boolean select__Store_Uuid(Map<String,Object> maps) throws SQLException;
	/**
	 * 判断store_id是否重复 
	 * @param maps
	 * @return 
	 */
	boolean select__Store_Id(Map<String,Object> maps) throws SQLException;
	/**
	 * @param maps
	 * @return判断 seller_idcard_token是否重复 
	 * @throws SQLException
	 */
	boolean seller_idcard_token(Map<String,Object> maps) throws SQLException;
}
