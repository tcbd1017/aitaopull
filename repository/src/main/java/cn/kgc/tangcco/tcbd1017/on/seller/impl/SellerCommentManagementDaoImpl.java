package cn.kgc.tangcco.tcbd1017.on.seller.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;

import cn.kgc.tangcco.lihaozhe.commons.date.BaseDateUitls;
import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.lihaozhe.commons.jdbc.PageRang;
import cn.kgc.tangcco.lihaozhe.commons.localdatetime.LocalDateTimeUtil;
import cn.kgc.tangcco.tcbd1017.on.seller.SellerCommentManagementDao;

/**
*@author   郝彦冰<br>
*@version  创建时间 :  2019年12月16日  上午8:45:20<br>
*类说明:
*/
public class SellerCommentManagementDaoImpl implements SellerCommentManagementDao{
	/**
	 * 查询评论
	 */
	@Override		
	public List<Map<String, Object>> sellectComments(Map<String,Object> map) throws SQLException {
		StringBuffer sql = new StringBuffer();
		List<Object> list = new ArrayList<Object>();
		sql.append(" select 0109_order.order_id, 0109_order.order_uuid, 0109_order.order_create_time, 0109_order.goods_id,  ");
		sql.append(" 0109_order.goods_name,  0109_order.goods_price, 0109_order.seller_id, 0105_comment.comment_id, 0105_comment.comment_content, ");
		sql.append(" 0105_comment.comment_create_time, 0105_comment.comment_parent_id, 0105_comment.comment_grade, 0205_reply_comment.reply_comment_content, ");
		sql.append(" 0205_reply_comment.reply_comment_create_time  ");
		sql.append(" from 0109_order inner join 0105_comment on 0109_order.order_id = 0105_comment.order_id and 0105_comment.comment_parent_id =0 and 0105_comment.comment_status = 2 ");
		if (map.size() > 0 && map != null ) {
			if (map.containsKey("goods_name") && !ObjectUtils.isEmpty(map.get("goods_name"))){
				sql.append("and 0109_order.goods_name like ? ");
				//System.out.println(map.get("goods_name"));
				//System.out.println("45678");
				list.add("%"+map.get("goods_name")+"%");
			}
			if (map.containsKey("order_uuid")&& !ObjectUtils.isEmpty(map.get("order_uuid"))) {
				sql.append(" and 0109_order.order_uuid = ? ");
				list.add(map.get("order_uuid"));
			}
			if (map.containsKey("order_create_time_from")&& !ObjectUtils.isEmpty(map.get("order_create_time_from"))) {
				sql.append(" and  0109_order.order_create_time >= ? ");
			  try {
				list.add(BaseDateUitls.parse(map.get("order_create_time_from").toString()));
			} catch (ParseException e) {
				e.printStackTrace();
			 }
			}
			if (map.containsKey("order_create_time_to")&& !ObjectUtils.isEmpty(map.get("order_create_time_to"))) {
				sql.append(" and  0109_order.order_create_time <= ? ");
			  try {
				list.add(BaseDateUitls.parse(map.get("order_create_time_to").toString()));
			} catch (ParseException e) {
				e.printStackTrace();
			 }
			}
			if (map.containsKey("comment_grade")&& !ObjectUtils.isEmpty(map.get("comment_grade"))) {
				sql.append(" and  0105_comment.comment_grade = ? ");
			    list.add(map.get("comment_grade"));
			
			}
		}
		sql.append(" left join 0205_reply_comment on  0109_order.order_id=0205_reply_comment.order_id   and 0205_reply_comment.reply_comment_status= 2 and  0109_order.seller_id = ? ");
		
		list.add(map.get("seller_id"));
		
		sql.append(" ORDER BY 0109_order.order_create_time ");
		Object[] param = list.toArray();
		Connection conn = BaseDBUtils.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		if (map.containsKey("pr")) {
			pst = BaseDBUtils.getPreparedStatement(conn, sql.toString(),(PageRang)map.get("pr"));
		} else {
			pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
		}
		if (param.length>0) {
			rs = BaseDBUtils.executeQuery(pst, param);
		}
		List<Map<String, Object>> rsToList = rsToList( rs);
		
		
		
		
		return rsToList;
	}
	/**
	 * 删除评论
	 */
	@Override
	public int updateComments(Map<String,Object> map) throws SQLException {
		StringBuffer sql = new StringBuffer(" update 0105_comment set 0105_comment.comment_status = 1  where  1 = 1 and  0105_comment.comment_id = ? ");
		Object[] param = {map.get("comment_id")};
		Connection conn = BaseDBUtils.getConnection();
		PreparedStatement pst = null;
		int rs = 0;
		pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
		rs = BaseDBUtils.executeUpdate(pst, param);
		
		return rs;
	}
	 
	/**
	 * 商家回复评论
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	public int insertSellerComments(Map<String,Object> map)throws SQLException{
		StringBuffer sql = new StringBuffer(" insert into 0205_reply_comment  (seller_id,order_id,reply_comment_content ,reply_comment_create_time ,reply_comment_updatet_time  ) ");
		sql.append(" select ?, ? , ? , ? , ?  from dual ");
		sql.append(" where not exists ");
		sql.append(" (select order_id  from  0205_reply_comment  where order_id = ? ) ");
		List<Object> list = new ArrayList<Object>();
		list.add(map.get("seller_id"));
		list.add(map.get("order_id"));
		list.add(map.get("reply_comment_content"));
		
			list.add(new Date());
			list.add(new Date());
			list.add(map.get("order_id"));
		
		Object[] param = list.toArray();
		Connection conn = BaseDBUtils.getConnection();
		PreparedStatement pst = null;
		int rs = 0;
		pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
		
		if (param.length>0) {
			rs = BaseDBUtils.executeUpdate(pst, param);
		}
		return rs;
	}
	/**
	 * 判断卖家是否回复用户评论
	 */
	 
	  
//	
//	
//	public int count() {
//		StringBuffer sql = new StringBuffer(" select count(reply_comment_id) as count ");
//		sql.append(" from  0205_reply_comment  ");
//		sql.append(" where 1 = 1  ");
//		sql.append(" and  0205_reply_comment.reply_comment_status = 2  ");
//		Connection conn = null;
//		PreparedStatement pst = null;
//		ResultSet rs = null;
//		int c = 0;
//		try {
//			conn = BaseDBUtils.getConnection();
//			pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
//			rs = BaseDBUtils.executeQuery(pst);
//			while (rs.next()) {
//				c= rs.getInt("count");
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		
//		return c;
//	}
//	public boolean panduan() {
//		int count = count();
//		StringBuffer sql = new StringBuffer(" select * from  0109_order as o ");
//		sql.append(" inner join 0205_reply_comment as r " );
//		sql.append(" inner join 0105_comment as c ");
//		sql.append(" on 1 = 1 " );
//		sql.append(" and o.order_id = r.order_id " );
//		sql.append(" and o.order_id = c.order_id ");
//		sql.append(" and c.comment_status = 2 and c.comment_parent_id =0 ");
//		sql.append("and r.reply_comment_status = 2 " );
//		Connection conn = null;
//		PreparedStatement pst = null;
//		ResultSet rs = null;
//		try {
//			conn = BaseDBUtils.getConnection();
//			pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
//			rs = BaseDBUtils.executeQuery(pst);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		List<Map<String, Object>> rsToList = rsToList(rs);
//		
//		boolean bool = false;
//		if (rsToList.size() == count) {
//			bool = true;
//		} 
//		return bool;
//		
//	}

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
