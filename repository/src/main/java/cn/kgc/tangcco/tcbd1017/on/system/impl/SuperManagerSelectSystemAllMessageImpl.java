package cn.kgc.tangcco.tcbd1017.on.system.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.tcbd1017.on.system.SuperManagerSelectSystemAllMessage;


/**
 * 
 * @author zhangmiao0
 * @version	2019年12月13日	下午4:06:30
 *
 */
public class SuperManagerSelectSystemAllMessageImpl implements SuperManagerSelectSystemAllMessage {

	@Override
	public Map<String, Object> selectSystemAllMessage(Connection conn) throws SQLException {
		List<Object> list = null;
		Object[] param  =list.toArray();
		StringBuilder sql = new StringBuilder();
		PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
		
		ResultSet rs = BaseDBUtils.executeQuery(pst, param);
		return null;
	}


}
