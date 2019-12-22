package cn.kgc.tangcco.tcbd1017.on.seller.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.tcbd1017.on.pojo.StoreFavorite;
import cn.kgc.tangcco.tcbd1017.on.seller.SelectStoreFavorlteinfoDao;

/**
 * 
 * @author 江岛
 * @ClassName: Select_Seller_Store_Favorlte_infoDaoImpl
 * @Description: TODO(通过传来的map中的店铺卖家id来查询本店铺被收藏数量)
 * @date 创建时间: 2019年12月13日 下午2:16:16
 */
public class SelectStoreFavorlteinfoDaoImpl implements SelectStoreFavorlteinfoDao {

	@Override
	public int SelectStoreFavorlteNumberDao(Map<String, Object> map) {
		System.out.println(map);
		StringBuilder sql = new StringBuilder("select COUNT(store_id) as count from 010602_store_favorite  ");
		sql.append(" where 1 = 1  ");
		sql.append(" and store_id = ?");
		sql.append(" and store_favorite_create_time > ? ");
		sql.append(" and store_favorite_create_time < ? ");

		
		List<Object> list = new ArrayList<Object>();
		list.add(map.get("seller_id"));
		list.add(map.get("store_favorite_create_time_k"));
		list.add(map.get("store_favorite_create_time_s"));
		Object[] param = list.toArray();
		

		Connection conn;
		PreparedStatement pst;
		ResultSet rs;
		int count = -1;
		try {
			conn = BaseDBUtils.getConnection();
			pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
			rs = BaseDBUtils.executeQuery(pst, param);
			while (rs.next()) {
				count = rs.getInt("count");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return count;
	}

	/**jiangdao
	 * 查询所有收藏该商品的数量（按月的）
	 * map中传入的是商品的id
	 * 返回的是该商品被收藏的数量
	 */
	@Override
	public int selectGoodsFavoriteNumber(Map<String, Object> map) {
		//默认收藏的数量为0
		int count = 0;
		//sql语句
		StringBuffer sql = new StringBuffer(" select count(*) as count from  010601_goods_favorite ");
		sql.append(" where 1=1 ");
		sql.append(" and goods_favorite_status=2 ");
		sql.append(" and goods_id= ? ");
		sql.append(" and goods_favorite_create_time > ? ");
		sql.append(" and goods_favorite_create_time < ? ");
		
	
		//从map中拿到商品id
		Object [] param = {map.get("goods_id"),map.get("goods_favorite_create_time_k"),map.get("goods_favorite_create_time_s")};
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
	 * 根据前台传来的商品信息 ，来上架商品 （修改商品属性为已上架）
	 */
	@Override
	public int updateGoods(Map<String, Object> map) {
		StringBuffer sql = new StringBuffer(" UPDATE 0203_goods set goods_status = 3 where 1=1 and goods_id = ? ");
		
		
		int count = 0;
		//从map中拿到商品id
		Object [] param = {map.get("goods_id")};
		Connection connection;
		try {
			connection = BaseDBUtils.getConnection();
			PreparedStatement preparedStatement = BaseDBUtils.getPreparedStatement(connection, sql.toString());
			count = BaseDBUtils.executeUpdate(preparedStatement, param);
					//BaseDBUtils.executeQuery(preparedStatement, param);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}


}
