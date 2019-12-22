package cn.kgc.tangcco.tcbd1017.on.buyer.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.tcbd1017.on.buyer.FrontBuyerRegisterDaoIns;

/**
 * @author 作者 Your-Name: 刘煜
 * @version 创建时间：2019年12月10日 下午2:42:01 类说明 :买家注册实现
 */
public class FrontBuyerRegisterDaoImpl implements FrontBuyerRegisterDaoIns {

	public int insert_Account_Password_Byyer(Map<String, Object> map) throws SQLException {
		Connection connection = BaseDBUtils.getConnection();
		PreparedStatement preparedStatement = null;
		int rs = 0;
		// 账户密码注册
		if (map.get("buyer_login_account") != null && map.get("buyer_login_password") != null) {
			StringBuilder sql = new StringBuilder(
					" INSERT INTO `0102_buyer_login` (`buyer_login_id`, `buyer_uuid`, `buyer_login_account`, `buyer_login_password`, `buyer_login_face_token`, `buyer_login_create_time`, `buyer_login_update_time`, `buyer_login_status`) ");
			sql.append(" VALUES (NULL,?, ?, ?, null, ?, null, '2') ");
			Object[] param = { map.get("buyer_uuid"), map.get("buyer_login_account"), map.get("buyer_login_password"),
					map.get("buyer_login_create_time") };
			preparedStatement = BaseDBUtils.getPreparedStatement(connection, sql.toString());
			rs = BaseDBUtils.executeUpdate(preparedStatement, param);
		}
		return rs;
	}
	@Override
	public int insert_Account_Password(Map<String, Object> map) throws SQLException {
		System.out.println("insert_Account_Password");
		Connection connection = BaseDBUtils.getConnection();
		StringBuilder sql = new StringBuilder(
				" insert into 0101_buyer (buyer_uuid,buyer_create_time,buyer_update_time,buyer_status) ");
		sql.append(" VALUES(?,NOW(),NOW(),2) ");
		Object[] param = { map.get("buyer_uuid")};
		PreparedStatement preparedStatement = BaseDBUtils.getPreparedStatement(connection, sql.toString());
		int rs = BaseDBUtils.executeUpdate(preparedStatement, param);
		return rs;
	}

	@Override
	public int insert_Face(Map<String, Object> map) throws SQLException {
		// 人脸注册
		Connection connection = BaseDBUtils.getConnection();
		PreparedStatement preparedStatement = null;
		StringBuilder sql = new StringBuilder(
				" INSERT INTO `0102_buyer_login` (`buyer_login_id`, `buyer_uuid`, `buyer_login_account`, `buyer_login_password`, `buyer_login_face_token`, `buyer_login_create_time`, `buyer_login_update_time`, `buyer_login_status`) ");
		sql.append(" VALUES (NULL,?, null, null, ?, ?, null, '2') ");
		Object[] param = { map.get("buyer_uuid"), map.get("buyer_login_face_token"),
				map.get("buyer_login_create_time") };
		preparedStatement = BaseDBUtils.getPreparedStatement(connection, sql.toString());
		int rs = BaseDBUtils.executeUpdate(preparedStatement, param);
		return rs;
	}

	@Override
	public int insert_Mobile_Byyer(Map<String, Object> map) throws SQLException {
		// 手机号注册
		Connection connection = BaseDBUtils.getConnection();
		StringBuilder sql = new StringBuilder(
				" INSERT INTO `0101_buyer` (`buyer_id`, `buyer_uuid`, `buyer_name`, `buyer_mobile`, `buyer_mail`, `buyer_create_time`, `buyer_update_time`, `buyer_status`) ");
		sql.append(" VALUES (NULL,?, null, ?, null, ?, null, '2') ");
		Object[] param = { map.get("buyer_uuid"), map.get("buyer_mobile"), map.get("buyer_create_time") };
		PreparedStatement preparedStatement = BaseDBUtils.getPreparedStatement(connection, sql.toString());
		int rs = BaseDBUtils.executeUpdate(preparedStatement, param);
		return rs;
	}

	@Override
	public int insert_Mobile_Boyer_Login(Map<String, Object> map) throws SQLException {
		Connection connection = BaseDBUtils.getConnection();
		StringBuilder sql = new StringBuilder(
				" insert into 0102_buyer_login (`buyer_uuid`,buyer_login_account,buyer_login_create_time,buyer_login_update_time,buyer_login_status)  ");
		sql.append(" VALUES(?,?,now(),now(),2) ");
		Object[] param = { map.get("buyer_uuid"), uuname() };
		PreparedStatement preparedStatement = BaseDBUtils.getPreparedStatement(connection, sql.toString());
		int rs = BaseDBUtils.executeUpdate(preparedStatement, param);
		return rs;
	}

	// 随机生成账号
	public String uuname() {
		String str = "ceshi";
		Random ran = new Random();
		str += ran.nextInt(9999);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("buyer_login_account", str);
		try {
			boolean flag = select_buyer_login_account(map);
			if (flag) {
				uuname();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return str;

	}

	@Override
	public int insert_Boyer_Info(Map<String, Object> map) throws SQLException {
		System.out.println("insert_Boyer_Info");
		Connection connection = BaseDBUtils.getConnection();
		StringBuilder sql = new StringBuilder(
				" insert into 010101_buyer_info (buyer_id,buyer_info_create_time,buyer_info_update_time,buyer_info_status) ");
		sql.append(" VALUES(?,now(),now(),2) ");
		Object[] param = { map.get("buyer_id") };
		PreparedStatement preparedStatement = BaseDBUtils.getPreparedStatement(connection, sql.toString());
		int rs = BaseDBUtils.executeUpdate(preparedStatement, param);
		return rs;
	}

	@Override
	public int select_Buyer_Id(Map<String, Object> map) throws SQLException {
		System.out.println("select_Buyer_Id");
		Connection connection = BaseDBUtils.getConnection();
		StringBuilder sql = new StringBuilder("SELECT buyer_id FROM 0101_buyer WHERE 1 = 1  and buyer_uuid= ? ");
		Object[] param = { map.get("buyer_uuid") };
		PreparedStatement preparedStatement = BaseDBUtils.getPreparedStatement(connection, sql.toString());
		ResultSet rs = BaseDBUtils.executeQuery(preparedStatement, param);
		if (rs.next()) {
			return rs.getInt("buyer_id");
		}
		return 0;
	}

	@Override
	// 判断uuid是 否 重 复
	public boolean select_Byyer_Uuid(Map<String, Object> map) throws SQLException {
		Connection connection = BaseDBUtils.getConnection();
		StringBuilder sql = new StringBuilder(
				"SELECT `buyer_uuid` FROM 0102_buyer_login WHERE 1 = 1  and buyer_uuid=? ");
		Object[] param = { map.get("buyer_uuid") };
		PreparedStatement preparedStatement = BaseDBUtils.getPreparedStatement(connection, sql.toString());
		ResultSet rs = BaseDBUtils.executeQuery(preparedStatement, param);
		if (rs.first()) {
			return true;
		}
		return false;
	}

	@Override
	// 判断手机号是否被注册
	public boolean select_buyer_mobile(Map<String, Object> map) throws SQLException {
		Connection connection = BaseDBUtils.getConnection();
		StringBuilder sql = new StringBuilder(
				"SELECT `buyer_mobile` FROM `0101_buyer` WHERE 1 = 1  and `buyer_mobile`=? ");
		Object[] param = { map.get("buyer_mobile") };
		PreparedStatement preparedStatement = BaseDBUtils.getPreparedStatement(connection, sql.toString());
		ResultSet rs = BaseDBUtils.executeQuery(preparedStatement, param);
		if (rs.first()) {
			return true;
		}
		return false;
	}

	@Override
	// 判断账户是否被注册
	public boolean select_buyer_login_account(Map<String, Object> map) throws SQLException {
		Connection connection = BaseDBUtils.getConnection();
		StringBuilder sql = new StringBuilder(
				" SELECT `buyer_login_account` FROM `0102_buyer_login` WHERE 1=1 AND `buyer_login_account`=? ");
		Object[] param = { map.get("buyer_login_account") };
		PreparedStatement preparedStatement = BaseDBUtils.getPreparedStatement(connection, sql.toString());
		ResultSet rs = BaseDBUtils.executeQuery(preparedStatement, param);
		if (rs.first()) {
			return true;
		}
		return false;
	}
	

	

}
