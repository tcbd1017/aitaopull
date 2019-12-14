package cn.kgc.tangcco.tcbd1017.on.seller.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.tcbd1017.on.seller.SellerGoodsFavoriteDao;

/**
*@author   席首华
*@version  1.0   </br>
   创建时间   2019年12月10日     下午4:59:25
  类描述：
*
*/
public class SellerGoodsFavoriteDaoImpl  implements SellerGoodsFavoriteDao{

	/**
	 * 查询所有收藏该商品的数量
	 * map中传入的是商品的id
	 * 返回的是该商品被收藏的数量
	 */
	@Override
	public int selectGoodsFavoriteCount(Map<String, Object> map) {
		//默认收藏的数量为0
		int count = 0;
		//sql语句
		StringBuffer sql = new StringBuffer(" select count(*) as count from  010601_goods_favorite ");
		sql.append(" where 1=1 ");
		sql.append(" and goods_favorite_status=2 ");
		sql.append(" and goods_id= ? ");
		//从map中拿到商品id
		Object [] param = {map.get("goods_id")};
		Connection connection;
		try {
			connection = BaseDBUtils.getConnection();
			PreparedStatement preparedStatement = BaseDBUtils.getPreparedStatement(connection, sql.toString());
			ResultSet resultSet = BaseDBUtils.executeQuery(preparedStatement, param);
			while (resultSet.next()) {
				count = resultSet.getInt("count");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	/**
	 * 查询收藏某一商品的全部买家信息
	 * map中传入 商品id
	 * 返回的是收藏信息和买家的信息的集合
	 */
	@Override
	public List<Map<String, Object>> selectGoodsFavoriteInformation(Map<String, Object> map) {
		//存放查询到的结果
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		//用来存放查询到的单条信息
		Map<String, Object> information ;
		//sql语句
		StringBuffer sql = new StringBuffer(" select goods_favorite_id,010601_goods_favorite.buyer_id, goods_id, ");
		sql.append(" goods_favorite_create_time,goods_favorite_update_time,goods_favorite_status, ");
		sql.append(" buyer_uuid , buyer_name , buyer_mail , buyer_status , buyer_mobile , buyer_update_time , buyer_create_time ");
		sql.append(" from  010601_goods_favorite  inner JOIN 0101_buyer  ");
		sql.append(" on  1=1  ");
		sql.append(" and goods_id= ? ");
		sql.append(" and goods_favorite_status=2 ");
		sql.append(" and buyer_status=2 ");
		sql.append(" and 010601_goods_favorite.buyer_id=0101_buyer.buyer_id ");
		try {
			//从map中拿到查询条件
			Object [] param = {map.get("goods_id")};
			Connection connection = BaseDBUtils.getConnection();
			PreparedStatement preparedStatement = BaseDBUtils.getPreparedStatement(connection, sql.toString());
			ResultSet resultSet = BaseDBUtils.executeQuery(preparedStatement, param);
			while(resultSet.next()) {
				information=new HashMap<String, Object>();
				information.put("goods_favorite_id", resultSet.getInt("goods_favorite_id"));
				information.put("buyer_id", resultSet.getInt("buyer_id"));
				information.put("goods_id", resultSet.getInt("goods_id"));
				information.put("goods_favorite_create_time", resultSet.getDate("goods_favorite_create_time"));
				information.put("goods_favorite_update_time", resultSet.getDate("goods_favorite_update_time"));
				information.put("goods_favorite_status", resultSet.getInt("goods_favorite_status"));
				information.put("buyer_uuid", resultSet.getString("buyer_uuid"));
				information.put("buyer_name",resultSet.getString("buyer_name"));
				information.put("buyer_mail", resultSet.getString("buyer_mail"));
				information.put("buyer_status", resultSet.getString("buyer_status"));
				information.put("buyer_mobile", resultSet.getString("buyer_mobile"));
				information.put("buyer_update_time", resultSet.getDate("buyer_update_time"));
				information.put("buyer_create_time", resultSet.getDate("buyer_create_time"));
				list.add(information);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

}
