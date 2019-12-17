package cn.kgc.tangcco.tcbd1017.on.buyer.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.tcbd1017.on.buyer.CommentDao;
import cn.kgc.tangcco.tcbd1017.on.pojo.Comment;

/**
* @author  余锴峰
* @version 创建时间:2019年12月13日 上午9:08:01
* @ClassName 类名称
* @Description 类描述
*/
public class CommentDaoImpl implements CommentDao{

	/**
	 * 添加评论
	 * 买家id不能重复 
	 */
	@Override
	public int insertComment(Map<String, Object> map) throws SQLException {
		StringBuilder sql = new StringBuilder(" insert into 0105_comment select 0,?,?,?,?,NOW(),NOW(),2,? from dual ");
		sql.append(" where not EXISTS ");
		sql.append(" (select comment_id from 0105_comment ");
		sql.append(" where buyer_id = ?) ");
		Object[] param = {map.get("buyer_id"),map.get("order_id"),map.get("comment_parent_id"),map.get("comment_content"),map.get("comment_grade"),map.get("buyer_id")};
		Connection conn= BaseDBUtils.getConnection();
		PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
		int count = BaseDBUtils.executeUpdate(pst, param);
		return count;
	}

	/**
	 * 查询所有评论信息
	 *    三表联查   1评论表   2,买家表  3,订单表
	 *    返回 评论表的所有信息,买家名字,订单状态(是否到货,到货才能评论) 
	 */
	@Override
	public List<Map<String ,Object>> selectComment(Map<String, Object> map) throws SQLException {
		List<Map<String ,Object>> list = null;
		StringBuilder sql = new StringBuilder(" select c.comment_id,c.buyer_id,c.order_id,c.comment_parent_id,c.comment_content,c.comment_create_time,c.comment_update_time, ");
		sql.append(" c.comment_status,c.comment_grade,b.buyer_id,b.buyer_name,o.order_id,o.order_status from 0105_comment as c ");
		sql.append(" inner join 0101_buyer as b ");
		sql.append(" inner join 0109_order as o ");
		sql.append(" where 1 = 1 ");
		sql.append(" and c.buyer_id = b.buyer_id ");
		sql.append(" and c.order_id = o.order_id ");
		Connection conn = BaseDBUtils.getConnection();
		PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
		ResultSet rs = BaseDBUtils.executeQuery(pst);
		list = rsToList(rs);
		return list;
	}

	
	/**
	 * 用来处理结果集的工具
	 * @param rs 用SQL语句查询出的结果集
	 * @return 包含每个字段值的一个list
	 */
	private List<Map<String ,Object>> rsToList(ResultSet rs){
		List<Map<String ,Object>> list = null;
		if(rs==null) {
			return list;
		}
		try {
			list = new ArrayList<Map<String ,Object>>();
			//获取总列数
			int columnCount = rs.getMetaData().getColumnCount();
			while(rs.next()){
				Map<String, Object> map = new HashMap<String, Object>();
				// 遍历每一列,拿出列名和数据
				for (int i = 1; i <= columnCount; i++) {
					String columnLabel = rs.getMetaData().getColumnLabel(i);
					Object value = rs.getObject(i);
					map.put(columnLabel,value);
				}
			    list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
