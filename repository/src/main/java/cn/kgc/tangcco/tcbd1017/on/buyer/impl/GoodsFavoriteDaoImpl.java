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
import cn.kgc.tangcco.tcbd1017.on.buyer.GoodsFavoriteDao;


/**
* @author 张忠华  
* @date 2019年12月13日  
* @version 1.0  
*/
public class GoodsFavoriteDaoImpl implements GoodsFavoriteDao  {

	@Override
	public int addGoodsFavorite(Map<String, Object> map)throws SQLException {
		StringBuilder sql=new StringBuilder(" insert INTO 010601_goods_favorite ");
		sql.append(" (buyer_id,goods_id,goods_favorite_create_time,goods_favorite_update_time) ");
		sql.append(" VALUES(?,?,?,?) ");		
		Object[]params= {map.get("buyer_id"),map.get("goods_id"),map.get("goods_favorite_create_time"),map.get("goods_favorite_update_time")};
		Connection connection = BaseDBUtils.getConnection();
		PreparedStatement preparedStatement = BaseDBUtils.getPreparedStatement(connection, sql.toString());
		return BaseDBUtils.executeUpdate(preparedStatement, params);	
	}

	@Override
	public int deleteGoodsFavorite(Map<String, Object> map)throws SQLException {
		StringBuilder sql=new StringBuilder(" update 010601_goods_favorite ");
		sql.append(" SET goods_favorite_status = 1  ");
		sql.append(" where 1 = 1 AND goods_favorite_id = ?  ");
		sql.append(" and goods_favorite_status=2 ");
		Object[]params={map.get("goods_favorite_id")};
		Connection connection = BaseDBUtils.getConnection();
		PreparedStatement preparedStatement = BaseDBUtils.getPreparedStatement(connection, sql.toString());
		int	executeUpdate = BaseDBUtils.executeUpdate(preparedStatement, params);			
		
		return executeUpdate;
	}



	@Override
	public int judgeGoodsFavorite(Map<String, Object> map)throws SQLException {
		int result= 0;
		StringBuilder sql=new StringBuilder(" select goods_favorite_id,buyer_id from 010601_goods_favorite ");
		sql.append("  where 1 = 1 and goods_id = ? and goods_favorite_status = 2  ");
		Object[]params= {map.get("goods_id")};
		Connection connection = BaseDBUtils.getConnection();
		PreparedStatement preparedStatement = BaseDBUtils.getPreparedStatement(connection, sql.toString());
		ResultSet resultSet = BaseDBUtils.executeQuery(preparedStatement, params);
		while (resultSet.next()) {
			 if (resultSet.getInt("goods_favorite_id") != 0) {
				result = 1;
			}
		}
		return result; 
		} 
	
	@Override
	public List<Map<String, Object>> selectGoodsFavorite(Map<String, Object> map)throws SQLException {
		StringBuilder sql=new StringBuilder();
		sql.append("select gf.goods_favorite_id,gf.buyer_id,gf.goods_id, ");
		sql.append(" gf.goods_favorite_create_time,gf.goods_favorite_update_time,");
		sql.append(" gs.goods_uuid,gs.goods_create_time,gs.goods_update_time,gs.goods_picture_url_id, ");	
		sql.append(" gs.goods_name,gs.goods_price,gs.goods_type,gs.goods_brand,gs.goods_width, ");
		sql.append(" gs.goods_height,gs.goods_length,gs.seller_id,gs.storage_id, ");
		sql.append(" b.buyer_uuid,b.buyer_name,b.buyer_mail,b.buyer_mobile,b.buyer_create_time, ");
		sql.append(" b.buyer_update_time ");
		sql.append(" from 010601_goods_favorite as gf ");
		sql.append(" INNER JOIN 0203_goods as gs  on gf.goods_id=gs.goods_id ");
		sql.append(" INNER JOIN 0101_buyer as b on b.buyer_id=gf.buyer_id and gf.goods_favorite_status=2 ");
		sql.append("  and gs.goods_status=2 and b.buyer_status =2 ");
	
		List<Object>lists = new ArrayList<Object>();
		if (map != null && map.size() > 0) {		
			if ( map.containsKey("goods_name")) {				                          
				sql.append(" and gs.goods_name like ? ");
				lists.add( "%"+map.get("goods_name")+"%" );
			}
			if (map.containsKey("buyer_id")) {
                
				sql.append(" and gf.buyer_id = ? ");
				lists.add(map.get("buyer_id").toString());
			}			
			
		}
		Object[] params= lists.toArray();
		Connection connection = BaseDBUtils.getConnection();
		PreparedStatement preparedStatement = BaseDBUtils.getPreparedStatement(connection, sql.toString());
		ResultSet rs = BaseDBUtils.executeQuery(preparedStatement, params);
		List list = null;
		Map<String,Object>map1=null;
		if (rs.first()) {			
			rs.previous();	
			list=new ArrayList();
			while (rs.next()) {
				map1=new HashMap<String,Object>();
				map1.put("goods_favorite_id",rs.getInt("goods_favorite_id"));
				map1.put("buyer_id",rs.getInt("buyer_id"));
				map1.put("goods_id",rs.getInt("goods_id"));
				map1.put("goods_favorite_create_time",rs.getDate("goods_favorite_create_time"));
				map1.put("goods_favorite_update_time",rs.getDate("goods_favorite_update_time"));
				map1.put("goods_uuid",rs.getString("goods_uuid") );
				map1.put("goods_create_time",rs.getDate("goods_create_time"));
				map1.put("goods_update_time", rs.getDate("goods_update_time"));
				map1.put("goods_picture_url_id",rs.getInt("goods_picture_url_id"));
				map1.put("goods_name",rs.getString("goods_name"));
				map1.put("goods_price",rs.getDouble("goods_price"));
				map1.put("goods_type",rs.getString("goods_type"));
				map1.put("goods_brand",rs.getString("goods_brand"));
				map1.put("goods_width",rs.getDouble("goods_width"));
				map1.put("goods_height",rs.getDouble("goods_height"));
				map1.put("goods_length",rs.getDouble("goods_length"));
				map1.put("seller_id",rs.getInt("seller_id"));
				map1.put("storage_id",rs.getInt("storage_id"));
				map1.put("buyer_uuid",rs.getString("buyer_uuid"));
				map1.put("buyer_name",rs.getString("buyer_name"));
				map1.put("buyer_mail",rs.getString("buyer_mail"));
				map1.put("buyer_mobile",rs.getString("buyer_mobile"));
				map1.put("buyer_create_time",rs.getDate("buyer_create_time"));
				map1.put("buyer_update_time",rs.getDate("buyer_update_time"));
				list.add(map1);
				
			}		
		}
	  return list;

	}

	@Override
	public int countGoodsFavorite(Map<String, Object> map) throws SQLException {
		int count=0;
		StringBuilder sql=new StringBuilder();
		sql.append("select  COUNT(gf.goods_id) as count from 010601_goods_favorite as gf ");
		sql.append(" INNER JOIN 0203_goods as gs  on gf.goods_id=gs.goods_id  ");
		sql.append(" INNER JOIN 0101_buyer as b on b.buyer_id=gf.buyer_id and gf.goods_favorite_status=2 ");
		sql.append(" and gs.goods_status=2 and b.buyer_status =2 and b.buyer_id= ? ");
		Object[] param = {map.get("buyer_id")};
		System.out.println(param.toString());
		Connection conn = BaseDBUtils.getConnection();		
		PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());	
		ResultSet rs = BaseDBUtils.executeQuery(pst, param);
		if (rs.next()) {
			// 结果集游标向下移动判断第一位位置上有没有值,如果值将数据库中的数据获取并复制给总记录数count
			count = rs.getInt("count");
		}
		return count;
	}
}
