package cn.kgc.tangcco.tcbd1017.st.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.tcbd1017.st.warehouseresourDao;

/**
 * @author ZHOUxq<br>
 * @version v1.0<br>
 * @创建时间：2019年12月14日  下午10:15:38<br>
 * @类描述：	
 */
public class WarehouseresourDaoImpl implements warehouseresourDao {

	
	

	@Override
	 public int selectWeiMaiZongGeShu() {
	  StringBuilder sql=new StringBuilder();
	  sql.append(" SELECT SUM(`warehouseresour_count`) as count FROM `warehouseresour` ");
	  int count=-1;
	  try {
	   Connection conn = BaseDBUtils.getConnection();
	   PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
	   ResultSet rs = BaseDBUtils.executeQuery(pst);
	   if (rs.next()) {
	    count=rs.getInt("count");
	   }
	  } catch (SQLException e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	  }
	  return count;
	 }
	
	@Override
	public Map<String, Object> selectXingHao(Map<String, Object> map) {
		StringBuilder sql=new StringBuilder();
		//查看某类仓库剩余数量(返回剩余数量)
		sql.append(" select warehouseresour_count from warehouseresour where 1 = 1 ");
		sql.append(" and warehouseresour_warehouse_id = ?");
		Object[] param = {map.get("warehouse_id")};
		Map<String, Object> map2 = null;
		try {
			Connection conn = BaseDBUtils.getConnection();
			PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
			ResultSet rs = BaseDBUtils.executeQuery(pst, param);
			while (rs.next()) {
				map2=new HashMap<String, Object>();
				map2.put("warehouseresour_count", rs.getInt("warehouseresour_count"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map2;
	}

	
	@Override
	public Map<String, Object> updateCangKuShnegYuCount(Map<String, Object> map) {
		StringBuilder sql=new StringBuilder();
		//购买仓库成功   某个类型下的 剩余数量减少
		sql.append(" UPDATE warehouseresour set warehouseresour_count = warehouseresour_count-? where 1 = 1  ");
		sql.append(" and warehouseresour_id = ? ");
		Object[] param = {map.get("number"),map.get("warehouseresour_id")};
		Map<String, Object> map2 = null;
		try {
			Connection conn = BaseDBUtils.getConnection();
			PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
			int rs = BaseDBUtils.executeUpdate(pst, param);
			if (rs>0) {
				map2=new HashMap<String, Object>();
				map2.put("warehouseresour_count", rs);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map2;
	}

}
