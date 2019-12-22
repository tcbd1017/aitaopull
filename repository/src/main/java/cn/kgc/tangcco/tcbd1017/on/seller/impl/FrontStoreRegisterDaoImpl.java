package cn.kgc.tangcco.tcbd1017.on.seller.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.tcbd1017.on.seller.FrontStoreRegisterDaoIns;
/** 
* @author 作者 Your-Name: 刘煜
* @version 创建时间：2019年12月13日 下午3:51:38 
*    类说明 : 开店 （店铺注册）
*/
public class FrontStoreRegisterDaoImpl implements FrontStoreRegisterDaoIns{

	@Override
	//商家个人信息注册(开店)
	public int insert_seller_Register(Map<String, Object> maps) throws SQLException {
		Connection connection = BaseDBUtils.getConnection();
		PreparedStatement preparedStatement = null;
		StringBuilder sql = new StringBuilder(" INSERT INTO `dxm`.`0201_seller` (`seller_id`,`buyer_id`, `seller_uuid`, `seller_face_token`, `seller_idcard_token`, ");
		sql.append(" `seller_create_time`, `seller_update_time`, `seller_status`, `store_id`, `storage_id`, `logistics_id`, `seller_icon_url`) ");
		sql.append(" VALUES (null, ?, ?, null, ?, ?, null,'2', ?, null , null ,? );  ");
		Object[] param = { maps.get("buyer_id"), maps.get("seller_uuid"), maps.get("seller_idcard_token"), maps.get("seller_create_time"),maps.get("storage_id"),maps.get("seller_icon_url") };
		preparedStatement = BaseDBUtils.getPreparedStatement(connection, sql.toString());
		int rs = BaseDBUtils.executeUpdate(preparedStatement, param);
		return rs;
	}
	//商家店铺信息注册 
	@Override
	public int insert_Store_Register(Map<String, Object> maps) throws SQLException {
		Connection connection = BaseDBUtils.getConnection();
		PreparedStatement preparedStatement = null;
		StringBuilder sql = new StringBuilder(" INSERT INTO `dxm`.`0207_store` (`store_id`, `store_create_time`, `store_update_time`, `store_status`, `store_about`, `store_img`,`store_name`) "); ;
		sql.append(" VALUES (?,?,null,'2', ?, ?, ?);  ");
		Object[] param = { maps.get("store_id"), maps.get("store_create_time"), maps.get("store_about"), maps.get("store_img"),maps.get("store_name")};
		preparedStatement = BaseDBUtils.getPreparedStatement(connection, sql.toString());
		int rs = BaseDBUtils.executeUpdate(preparedStatement, param);
		return rs;
	}
	
	@Override
	public boolean select__Store_Uuid(Map<String, Object> maps) throws SQLException {
		Connection connection = BaseDBUtils.getConnection();
		StringBuilder sql=new StringBuilder(" SELECT `seller_uuid` FROM `0201_seller` WHERE 1=1 AND `seller_uuid`= ? ");
		Object[] param = { maps.get("seller_uuid")};
		PreparedStatement preparedStatement = BaseDBUtils.getPreparedStatement(connection, sql.toString());
		ResultSet rs = BaseDBUtils.executeQuery(preparedStatement, param);
		if (rs.first()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean select__Store_Id(Map<String, Object> maps) throws SQLException {
		Connection connection = BaseDBUtils.getConnection();
		StringBuilder sql=new StringBuilder(" SELECT `seller_uuid` FROM `0201_seller` WHERE 1=1 AND `store_id`= ? ");
		Object[] param = { maps.get("store_id")};
		PreparedStatement preparedStatement = BaseDBUtils.getPreparedStatement(connection, sql.toString());
		ResultSet rs = BaseDBUtils.executeQuery(preparedStatement, param);
		if (rs.first()) {
			return true;
		}
		return false;
	}
	@Override
	public boolean seller_idcard_token(Map<String, Object> maps) throws SQLException {
		Connection connection = BaseDBUtils.getConnection();
		StringBuilder sql=new StringBuilder(" SELECT `seller_idcard_token` FROM `0201_seller` WHERE 1=1 AND `seller_idcard_token`=? ");
		Object[] param = { maps.get("seller_idcard_token")};
		PreparedStatement preparedStatement = BaseDBUtils.getPreparedStatement(connection, sql.toString());
		ResultSet rs = BaseDBUtils.executeQuery(preparedStatement, param);
		if (rs.first()) {
			return true;
		}
		return false;
	}

	
	
}
