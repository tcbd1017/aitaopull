package cn.kgc.tangcco.tcbd1017.on.buyer.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.lang3.StringUtils;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.lihaozhe.commons.jdbc.PageRang;
import cn.kgc.tangcco.tcbd1017.on.buyer.StoreFavoriteDao;
import cn.kgc.tangcco.tcbd1017.on.pojo.StoreFavorite;

/**
 * 
 * 	@author 和朋朋
 * 	@version 1.0<br>
 * 	创建时间：  2019年12月9日 上午11:12:20 <br>
 * 	类描述：收藏店铺持久层实现方法
 *	
 */

public class StoreFavoriteDaoImpl implements StoreFavoriteDao{
	QueryRunner qr=new QueryRunner();
//	/**
//	 * 查询所有
//	 */
//	@Override
//	public List selectAllStoreFavorite(Map<String, Object>map) throws SQLException {
//		StringBuilder  sql=new StringBuilder(" select store_favorite.store_favorite_id,");
//		sql.append(" store_favorite.buyer_id,store_favorite.store_id,");
//		sql.append(" store_favorite.store_favorite_create_time, ");
//		sql.append(" store_favorite.store_favorite_update_time,");
//		sql.append(" store_favorite.store_favorite_status,buyer.buyer_id,");
//		sql.append(" buyer.buyer_uuid,buyer.buyer_name,buyer.buyer_mobile,");
//		sql.append(" buyer.buyer_mail,buyer.buyer_create_time,buyer.buyer_update_time,");
//		sql.append(" buyer.buyer_status,store.store_id,store.goods_id,");
//		sql.append(" store.store_create_time,store.store_update_time,");
//		sql.append(" store.store_status,store.store_about,store.store_img,store.store_name");
//		sql.append(" from 010602_store_favorite as store_favorite ");
//		sql.append(" inner join 0101_buyer as buyer on store_favorite.buyer_id =buyer.buyer_id");
//		sql.append(" inner join 0207_store as store  on store_favorite.store_id = store.store_id");
//		sql.append(" and store_favorite.buyer_id= ? ");
//		Object[]param= {map.get("buyer_id")};
//		Connection conn = BaseDBUtils.getConnection();
//		PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString(),(PageRang)map.get("pr"));
//		ResultSet rs = BaseDBUtils.executeQuery(pst,param);
//		List list=null;
//		if (rs.first()) {
//			rs.previous();
//			list=new ArrayList();
//			while (rs.next()) {
//				Map<String, Object>map2=new HashMap<String, Object>();
//				map2.put("store_favorite_id", rs.getInt("store_favorite.store_favorite_id"));
//				map2.put("buyer_id", rs.getInt("store_favorite.buyer_id"));
//				map2.put("store_id", rs.getInt("store_favorite.store_id"));
//				map2.put("store_favorite_create_time", rs.getDate("store_favorite.store_favorite_create_time"));
//				map2.put("store_favorite_update_time", rs.getDate("store_favorite.store_favorite_update_time"));
//				map2.put("store_favorite_status", rs.getInt("store_favorite.store_favorite_status"));
//				map2.put("buyer_id", rs.getInt("buyer.buyer_id"));
//				map2.put("buyer_uuid", rs.getInt("buyer.buyer_uuid"));
//				map2.put("buyer_name", rs.getString("buyer.buyer_name"));
//				map2.put("buyer_mobile", rs.getString("buyer.buyer_mobile"));
//				map2.put("buyer_mail", rs.getString("buyer.buyer_mail"));
//				map2.put("buyer_create_time", rs.getDate("buyer.buyer_create_time"));
//				map2.put("buyer_update_time", rs.getDate("buyer.buyer_update_time"));
//				map2.put("buyer_status", rs.getInt("buyer.buyer_status"));
//				map2.put("store_id", rs.getInt("store.store_id"));
//				map2.put("goods_id", rs.getInt("store.goods_id"));
//				map2.put("store_create_time", rs.getDate("store.store_create_time"));
//				map2.put("store_update_time", rs.getDate("store.store_update_time"));
//				map2.put("store_status", rs.getInt("store.store_status"));
//				map2.put("store_about", rs.getString("store.store_about"));
//				map2.put("store_img", rs.getString("store.store_img"));
//				map2.put("store_name", rs.getString("store.store_name"));
//				list.add(map2);
//			}
//		}
//		return list;
//	}
	
	/**
	 * 查询
	 */
	@Override
	public List fuzzyQueryStoreFavorite(Map<String, Object>map) {
		StringBuilder  sql=new StringBuilder(" select store_favorite.store_favorite_id,");
		sql.append(" store_favorite.buyer_id,store_favorite.store_id,");
		sql.append(" store_favorite.store_favorite_create_time, ");
		sql.append(" store_favorite.store_favorite_update_time,");
		sql.append(" store_favorite.store_favorite_status,");
		sql.append(" buyer.buyer_uuid,buyer.buyer_name,buyer.buyer_mobile,");
		sql.append(" buyer.buyer_mail,buyer.buyer_create_time,buyer.buyer_update_time,");
		sql.append(" buyer.buyer_status,");
		sql.append(" store.store_create_time,store.store_update_time,");
		sql.append(" store.store_status,store.store_about,store.store_img,");
		sql.append(" store.store_name from 010602_store_favorite as store_favorite ");
		sql.append(" inner join 0101_buyer as buyer on store_favorite.buyer_id = buyer.buyer_id");
		sql.append(" inner join 0207_store as store  on store_favorite.store_id = store.store_id");
		sql.append(" and store_favorite.store_favorite_status= 2");
		sql.append(" and buyer.buyer_status = 2");
		sql.append(" and store.store_status = 2 ");
		List newslist = new ArrayList();
		
		//动态sql开始
		List list = new ArrayList();
		if (map != null && map.size() > 0) {
			if (map.containsKey("store_favorite_id")) {
				                          
				sql.append(" and store_favorite.store_favorite_id = ? ");
				list.add(map.get("store_favorite_id"));
			}
			
			if (map.containsKey("buyer_id")) {
                
				sql.append(" and store_favorite.buyer_id = ? ");
				list.add(map.get("buyer_id"));
			}
			
			
			if (map.containsKey("store_id")) {
                
				sql.append(" and store_favorite.store_id = ? ");
				list.add(map.get("store_id"));
			}
			
			if (map.containsKey("store_favorite_create_time")) {
				sql.append(" and store_favorite.store_favorite_create_time = date(?) ");
				list.add(map.get("store_favorite_create_time").toString());
			}
			
			if (map.containsKey("store_favorite_update_time")) {
				sql.append(" and store_favorite.store_favorite_update_time = date(?) ");
				list.add(map.get("store_favorite_update_time").toString());
			}
			
			if (map.containsKey("store_favorite_status")){
				sql.append(" and store_favorite.store_favorite_status = ? ");
				list.add(map.get("store_favorite_status"));
			}
			

			if (map.containsKey("buyer_uuid")) {
                
				sql.append(" and buyer.buyer_uuid = ? ");
				list.add(map.get("buyer_uuid"));
			}
			
			if (map.containsKey("buyer_name")) {
				sql.append(" and buyer.buyer_name = ? ");
				list.add( map.get("buyer_name"));
			}

			if (map.containsKey("buyer_mobile")) {
				sql.append(" and buyer.buyer_mobile = ? ");
				list.add( map.get("buyer_mobile"));
			}
			
			if (map.containsKey("buyer_mail")) {
				sql.append(" and buyer.buyer_mail = ? ");
				list.add( map.get("buyer_mail"));
			}
			
			if (map.containsKey("buyer_create_time")) {
				sql.append(" and buyer.buyer_create_time = date(?) ");
				list.add(map.get("buyer_create_time").toString());
			}
			
			if (map.containsKey("buyer_update_time")) {
				sql.append(" and buyer.buyer_update_time = date(?) ");
				list.add(map.get("buyer_update_time").toString());
			}
			
			if (map.containsKey("buyer_status")) {
			    
				sql.append(" and buyer.buyer_status = ? ");
				list.add(map.get("buyer_status"));
			}

			
			if (map.containsKey("store_create_time")) {
				sql.append(" and store.store_create_time = date(?) ");
				list.add(map.get("store_create_time").toString());
			}
			
			if (map.containsKey("store_update_time")) {
				sql.append(" and store.store_update_time = date(?) ");
				list.add(map.get("store_update_time").toString());
			}
			
			if (map.containsKey("store_status")) {
			    
				sql.append(" and store.store_status = ? ");
				list.add(map.get("store_status"));
			}
			
			if (map.containsKey("store_about")) {
				sql.append(" and store.store_about = ? ");
				list.add( map.get("store_about"));
			}
			
			if (map.containsKey("store_img")) {
				sql.append(" and store.store_img = ? ");
				list.add( map.get("store_img"));
			}
			
			if (map.containsKey("store_name")) {
				sql.append(" and store.store_name like ? ");
				list.add( "%"+map.get("store_name")+"%");
			}
			
		}
		
		Object[] param= list.toArray();
		//动态sql结束
		
		
		Connection conn;
		PreparedStatement pst;
		ResultSet rs;
		try {
			conn = BaseDBUtils.getConnection();
		    pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
			rs = BaseDBUtils.executeQuery(pst,param);
			while (rs.next()) {
				Map<String, Object>map2=new HashMap<String, Object>();
				map2.put("store_favorite_id", rs.getInt("store_favorite.store_favorite_id"));
				map2.put("buyer_id", rs.getInt("store_favorite.buyer_id"));
				map2.put("store_id", rs.getInt("store_favorite.store_id"));
				map2.put("store_favorite_create_time", rs.getDate("store_favorite.store_favorite_create_time"));
				map2.put("store_favorite_update_time", rs.getDate("store_favorite.store_favorite_update_time"));
				map2.put("store_favorite_status", rs.getInt("store_favorite.store_favorite_status"));
				map2.put("buyer_uuid", rs.getString("buyer.buyer_uuid"));
				map2.put("buyer_name", rs.getString("buyer.buyer_name"));
				map2.put("buyer_mobile", rs.getString("buyer.buyer_mobile"));
				map2.put("buyer_mail", rs.getString("buyer.buyer_mail"));
				map2.put("buyer_create_time", rs.getDate("buyer.buyer_create_time"));
				map2.put("buyer_update_time", rs.getDate("buyer.buyer_update_time"));
				map2.put("buyer_status", rs.getInt("buyer.buyer_status"));
				map2.put("store_create_time", rs.getDate("store.store_create_time"));
				map2.put("store_update_time", rs.getDate("store.store_update_time"));
				map2.put("store_status", rs.getInt("store.store_status"));
				map2.put("store_about", rs.getString("store.store_about"));
				map2.put("store_img", rs.getString("store.store_img"));
				map2.put("store_name", rs.getString("store.store_name"));
				newslist.add(map2);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return newslist;
	}
	/**
	 * 添加
	 */
	@Override
	public int addStoreFavorite(Map<String, Object>map) throws SQLException {
		StringBuilder sql=new StringBuilder("INSERT into 010602_store_favorite");
		sql.append(" (buyer_id,store_id,store_favorite_create_time,store_favorite_update_time)");
		sql.append(" SELECT	?,?,?,? ");
		sql.append(" from DUAL where  not exists ");
		sql.append(" (select store_id from 010602_store_favorite where store_id = ? )");
		Object[]param= {map.get("buyer_id"),map.get("store_id"),map.get("store_favorite_create_time"),map.get("store_favorite_update_time"),map.get("store_id")};
		Connection conn = BaseDBUtils.getConnection();
		PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
		return BaseDBUtils.executeUpdate(pst, param);
	}
	
	/**
	 * 删除
	 */
	@Override
	public int deleteStoreFavoriteByStoreFavoriteId(Map<String, Object>map) throws SQLException {
		StringBuilder  sql=new StringBuilder("UPDATE 010602_store_favorite set store_favorite_status=1 ");
		sql.append(" where store_favorite_id= ?");
		Object[]param= {map.get("store_favorite_id")};
		return qr.update(BaseDBUtils.getConnection(), sql.toString(), param);
	}
	

	@Override
	public int count() throws SQLException {
		StringBuilder sql=new StringBuilder(" select count(buyer.buyer_id) as count from 010602_store_favorite as store_favorite inner join 0101_buyer as buyer on store_favorite.buyer_id = buyer.buyer_id inner join 0207_store as store  on store_favorite.store_id = store.store_id  and store.store_status=2 and buyer.buyer_status=2 and store.store_status=2 ");
		Connection conn = BaseDBUtils.getConnection();
		PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
		ResultSet rs = BaseDBUtils.executeQuery(pst);
		int count=0;
		if (rs.first()) {
			rs.previous();
			while (rs.next()) {
				 count=rs.getInt("count");
			}
		}
		return count;
	}
	
//	/**
//	 * 批量删除
//	 * @param store_favorite_ids
//	 * @return
//	 */
//	public int delete(int[] store_favorite_ids)  {
//		StringBuilder  sql=new StringBuilder("UPDATE 010602_store_favorite set store_favorite_status=1 ");
//		sql.append(" where store_favorite_id in( ?");
//		List newslist=new ArrayList();
//		List list=new ArrayList();
//		Map<String, Object>map=new HashMap<String, Object>();
//		if (map != null && map.size() > 0) {
//			if (store_favorite_ids.length==1) {
//				sql.append(" )");
//				list.add(map.get("store_favorite_id"));
//			}else {
//				for (int i = 0; i < store_favorite_ids.length-1; i++) {
//					sql.append(" ,?");
//					list.add(map.get("store_favorite_id"));
//				}
//				sql.append(" )");
//			}
//		}
//		Object[]param=list.toArray();
//		Connection conn;
//		PreparedStatement pst;
//		ResultSet rs; 
//		try {
//			conn = BaseDBUtils.getConnection();
//			pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
//			rs = BaseDBUtils.executeQuery(pst,param);
//			while (rs.next()) {
//				StoreFavorite storeFavorite = new StoreFavorite();
//				storeFavorite.setStore_favorite_id(rs.getInt("store_favorite_id"));
//				newslist.add(storeFavorite);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		int count=0;
//		if (newslist!=null) {
//			count=1;
//		}
//		return count;
//	}
}
