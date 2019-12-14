package cn.kgc.tangcco.tcbd1017.on.system.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.tcbd1017.on.pojo.SetMeal;
import cn.kgc.tangcco.tcbd1017.on.system.SetMealDao;

/**
	 * 
	 * @author 薛彤
	 * @version 1.0 2019年12月13日 下午1:39:05
	 *
	 */

public class SetMealDaoImpl implements  SetMealDao{

	/**
	 * 查询所有
	 */
	@Override
	public List<SetMeal> selectAllSetMeal(Map<String, Object> map) throws SQLException {
		StringBuilder sql = new StringBuilder("SELECT * FROM 0304_set_meal  ");
		sql.append(" where 1 = 1  ");
		List<SetMeal> newsList = new ArrayList<SetMeal>();
		// 动态sql开始
		List<Object> list = new ArrayList<Object>();
		if (map != null && map.size() > 0) {

			if (map.containsKey("set_meal_id")) {
				sql.append(" and set_meal_id = ? ");
				list.add(map.get("set_meal_id"));
			}
			if (map.containsKey("set_meal_name")) {
				sql.append(" and set_meal_name = ? ");
				list.add(map.get("set_meal_name"));
			}
			if (map.containsKey("set_meal_creat_time")) {
				sql.append(" and set_meal_creat_time = date(?) ");
				list.add(map.get("set_meal_creat_time").toString());
			}
			if (map.containsKey("set_meal_update_time")) {
				sql.append(" and set_meal_update_time = date(?) ");
				list.add(map.get("set_meal_update_time").toString());
			}
			if (map.containsKey("set_meal_price")) {
				sql.append(" and set_meal_price = ? ");
				list.add(map.get("set_meal_price"));
			}
			if (map.containsKey("set_meal_interest")) {
				sql.append(" and set_meal_interest = ? ");
				list.add(map.get("set_meal_interest"));
			}
			if (map.containsKey("set_meal_duration")) {
				sql.append(" and set_meal_duration = ? ");
				list.add(map.get("set_meal_duration"));
			}
			if (map.containsKey("set_meal_status")) {
				sql.append(" and set_meal_status = ? ");
				list.add(map.get("set_meal_status"));
			}
		}
		Object[] param = list.toArray();
		// 动态sql结束
		Connection conn;
		PreparedStatement pst;
		ResultSet rs;
		try {
			conn = BaseDBUtils.getConnection();
			pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
			rs = BaseDBUtils.executeQuery(pst, param);
			while (rs.next()) {
				newsList.add(new SetMeal(rs.getInt("set_meal_id"),rs.getString("set_meal_name"),
						rs.getDate("set_meal_creat_time"),rs.getDate("set_meal_update_time"), rs.getDouble("set_meal_price"),
						rs.getDouble("set_meal_interest"),rs.getLong("set_meal_duration"),rs.getInt("set_meal_status")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return newsList;

	}

	/**
	 * 查询指定
	 */
	@Override
	public SetMeal selectSetMeal(int set_meal_id) throws SQLException {
		StringBuffer sql = new StringBuffer("select * from 0304_set_meal ");
		sql.append(" where 1=1 ");
		sql.append(" and set_meal_id = ? ");
		Connection conn = BaseDBUtils.getConnection();
		PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
		ResultSet rs = BaseDBUtils.executeQuery(pst, set_meal_id);
		SetMeal setMeal = null;
		if (rs.first()) {
			rs.previous();
			while (rs.next()) {
				setMeal = new SetMeal(rs.getInt("set_meal_id"),rs.getString("set_meal_name"), rs.getDate("set_meal_creat_time"),
						rs.getDate("set_meal_update_time"), rs.getDouble("set_meal_price"),
						rs.getDouble("set_meal_interest"),rs.getLong("set_meal_duration"),rs.getInt("set_meal_status"));
			}
		}
		return setMeal;
	}


	/**
	 * 增加
	 */
	@Override
	public int insertSetMeal(Map<String, Object> map) throws SQLException {
		StringBuffer sql = new StringBuffer(" INSERT INTO 0304_set_meal SELECT 0,?,now(),now(),?,?,?,2 FROM DUAL ");
		sql.append(" WHERE NOT EXISTS(SELECT set_meal_name FROM 0304_set_meal where set_meal_name = ?) ");
		Object[] param = { map.get("set_meal_name"), map.get("set_meal_price"),
				map.get("set_meal_interest"),map.get("set_meal_duration"),map.get("set_meal_name")};
		Connection conn = BaseDBUtils.getConnection();
		PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
		int count = BaseDBUtils.executeUpdate(pst, param);
		return count;
	}


	/**
	 * 修改
	 */
	@Override
	public int updateSetMeal(SetMeal setMeal) throws SQLException {
		StringBuffer sql = new StringBuffer(" update 0304_set_meal set ");
		List list = new ArrayList();
		if (setMeal.getSet_meal_id()>0) {
			sql.append(" set_meal_id = ?, ");
			list.add(setMeal.getSet_meal_id());
		}
		if (!StringUtils.isEmpty(setMeal.getSet_meal_name())) {
			sql.append(" set_meal_name = ?, ");
			list.add(setMeal.getSet_meal_name());
		}		
		if (!StringUtils.isEmpty(setMeal.getSet_meal_creat_time().toString())) { 
			sql.append(" set_meal_creat_time = ?, ");
			list.add(setMeal.getSet_meal_creat_time());
		}
		if (!StringUtils.isEmpty(setMeal.getSet_meal_update_time().toString())) { 
			sql.append(" set_meal_update_time = ?, ");
			list.add(setMeal.getSet_meal_update_time());
		}	
		if (!ObjectUtils.isEmpty(setMeal.getSet_meal_price())) {
			sql.append(" set_meal_price = ?, ");
			list.add(setMeal.getSet_meal_price());
		}
		if (!ObjectUtils.isEmpty(setMeal.getSet_meal_interest())) {
			sql.append(" set_meal_interest = ?, ");
			list.add(setMeal.getSet_meal_interest());
		}
		if (!ObjectUtils.isEmpty(setMeal.getSet_meal_duration())) {
			sql.append(" set_meal_duration = ?, ");
			list.add(setMeal.getSet_meal_duration());
		}
//		if (!ObjectUtils.isEmpty(setMeal.getSet_meal_status())) {
//			sql.append(" set_meal_status = ?, ");
//			list.add(setMeal.getSet_meal_status());
//		}
		String demo =sql.substring(0,sql.length()-2); 
		sql.delete(0, sql.length());
		sql.append(demo);
		sql.append(" where 1=1 ");
		sql.append(" and set_meal_id = ? ");
		list.add(setMeal.getSet_meal_id());
		Object[] param = list.toArray();
		Connection conn = BaseDBUtils.getConnection();
		PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
		int count = BaseDBUtils.executeUpdate(pst, param);
		return count;
	}


	/**
	 * 删除
	 */
	@Override
	public int deleteSetMeal(Map<String, Object> map) throws SQLException {
		StringBuffer sql = new StringBuffer(" update 0304_set_meal set set_meal_status=? ");
		sql.append(" where 1=1 ");
		sql.append(" and set_meal_id = ? ");
		Object[] param = {1,map.get("set_meal_id")};
		Connection conn = BaseDBUtils.getConnection();
		PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
		int count = BaseDBUtils.executeUpdate(pst, param);
		return count;
	}

}
