package cn.kgc.tangcco.tcbd1017.on.seller.Impl;

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
		StringBuilder sql = new StringBuilder("select COUNT(store_id) as count from 010602_store_favorite  ");
		sql.append(" where 1 = 1  ");
		sql.append(" and store_id = ?");
		
		// 动态sql开始
		List<Object> list = new ArrayList<Object>();
		list.add(map.get("store_id"));
		if (map != null && map.size() > 0) {
			if (map.containsKey("store_favorite_create_time")) {
				sql.append(" and store_favorite_create_time > ? ");
				list.add(map.get("store_favorite_create_time"));
			}
			if (map.containsKey("store_favorite_update_time")) {
				sql.append(" and store_favorite_update_time > ? ");
				list.add(map.get("store_favorite_update_time"));
			}
		}
		
		Object[] param = list.toArray();
		// 动态sql结束
		
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

	

	

}
