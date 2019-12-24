package cn.kgc.tangcco.tcbd1017.st.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.lihaozhe.commons.jdbc.PageRang;
import cn.kgc.tangcco.tcbd1017.st.warehouseDao;
import cn.kgc.tangcco.tcbd1017.st.pojo.Warehouse;

/**
 * @author ZHOUxq<br>
 * @version v1.0<br>
 * @创建时间：2019年12月14日 下午11:07:49<br>
 * @类描述：
 */
public class WarehouseDaoImpl implements warehouseDao {

	@Override
	public Map<String, Object> selectCangKuLeixing(Map<String, Object> map) {
		StringBuilder sql = new StringBuilder();
		// 购买仓库时显示仓库的信息 每种仓库剩余个数和 详情
		sql.append(
				" SELECT w.warehouse_name,w.warehouse_typesize,w.warehouse_address,w.warehouse_price,wa.warehouseresour_count from warehouse as w ");
		sql.append(" INNER JOIN warehouseresour as wa ");
		sql.append(" on w.warehouse_id = wa.warehouseresour_warehouse_id ");
		List newList = new ArrayList();
		// 按仓库类型查询，(例：10万容量)
		if (map.containsKey("warehouse_id") && map.get("warehouse_id") != null) {
			sql.append(" and warehouse_id = ? ");
			newList.add(map.get("warehouse_id"));
		}
		Object[] param = newList.toArray();
		Map<String, Object> map2 = null;
		try {
			Connection conn = BaseDBUtils.getConnection();
			// PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn,
			// sql.toString(),(PageRang)map.get("PageRang"));
			PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
			ResultSet rs = BaseDBUtils.executeQuery(pst, param);
			if (rs.first()) {
				rs.previous();
				map2 = new HashMap<String, Object>();
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				while (rs.next()) {
					Map<String, Object> map3 = new HashMap<String, Object>();
					map3.put("warehouse_name", rs.getString("warehouse_name"));
					map3.put("warehouse_typesize", rs.getInt("warehouse_typesize"));
					map3.put("warehouse_address", rs.getString("warehouse_address"));
					map3.put("warehouse_price", rs.getDouble("warehouse_price"));
					map3.put("warehouseresour_count", rs.getInt("warehouseresour_count"));
					list.add(map3);
				}
				map2.put("selectCangKuLeixing", list);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map2;
	}

	@Override
	public List<Warehouse> selectCangKuLeixing() {
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from warehouse ");
		List<Warehouse> list = new ArrayList<Warehouse>();
		try {
			Connection conn = BaseDBUtils.getConnection();
			PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
			ResultSet rs = BaseDBUtils.executeQuery(pst);
			Warehouse w = new Warehouse();

			while (rs.next()) {
				w.setWarehouseId(rs.getInt("wasehouse_id"));
				w.setWarehouseTypeName(rs.getString("wasehouse_name"));
				list.add(w);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

}
