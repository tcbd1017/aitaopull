package cn.kgc.tangcco.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.dao.GoodsDao;
import cn.kgc.tangcco.dao.Panduan;
import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.lihaozhe.commons.jdbc.PageRang;
import cn.kgc.tangcco.pojo.Brand;
import cn.kgc.tangcco.pojo.Goods;
import cn.kgc.tangcco.pojo.Model;
import cn.kgc.tangcco.pojo.Shop;
import cn.kgc.tangcco.pojo.Type;

public class GoodsDaoImpl implements GoodsDao {

	Panduan panduan = new PanDuImpl();

	@Override
	public Map<String, Object> selectgoods(Map<String, Object> map) throws SQLException {
		Map<String, Object> mapss = new HashMap<String, Object>();
		int role = panduan.pandu((String) map.get("account"));
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		// 操作者为管理员时
		if (role > 0) {
			StringBuilder sql = new StringBuilder(
					" select g.goods_id,s.shop_name,g.goods_count,g.goods_w_s_uuid,t.type_name,b.brand_name,m.model_name,m.model_price,m.model_size from goods g ");
			sql.append(" INNER JOIN type t ");
			sql.append(" on g.goods_type_id=t.type_id ");
			sql.append(" INNER JOIN model m ");
			sql.append(" on g.goods_model_id=m.model_id ");
			sql.append(" INNER JOIN shop s ");
			sql.append(" on g.goods_shop_id=s.shop_id ");
			sql.append(" INNER JOIN brand b ");
			sql.append(" on g.goods_brand_id=b.brand_id ");
			sql.append(" where g.goods_flag=1 ");
			List<Object> param = new ArrayList<Object>();
			if (map.containsKey("goods_id") && map.get("goods_id") != null) {
				sql.append(" and goods_id=? ");
				param.add(map.get("goods_id"));
			}
			if (map.containsKey("shop_name") && map.get("shop_name") != null) {
				sql.append(" and shop_name like ? ");
				param.add("%" + map.get("shop_name") + "%");
			}
			if (map.containsKey("goods_count") && map.get("goods_count") != null) {
				sql.append(" and goods_count=? ");
				param.add(map.get("goods_count"));
			}
			if (map.containsKey("goods_w_s_uuid") && map.get("goods_w_s_uuid") != null) {
				sql.append(" and goods_w_s_uuid like ? ");
				param.add("%" + map.get("goods_w_s_uuid") + "%");
			}
			if (map.containsKey("type_name") && map.get("type_name") != null) {
				sql.append(" and type_name like ? ");
				param.add("%" + map.get("type_name") + "%");
			}
			if (map.containsKey("brand_name") && map.get("brand_name") != null) {
				sql.append(" and brand_name like ? ");
				param.add("%" + map.get("brand_name") + "%");
			}
			if (map.containsKey("model_name") && map.get("model_name") != null) {
				sql.append(" and model_name like ? ");
				param.add("%" + map.get("model_name") + "%");
			}
			if (map.containsKey("model_price") && map.get("model_price") != null) {
				sql.append(" and model_price=? ");
				param.add(map.get("model_price"));
			}
			if (map.containsKey("model_size") && map.get("model_size") != null) {
				sql.append(" and model_size=? ");
				param.add(map.get("model_size"));
			}
			Object[] array = param.toArray();
			conn = BaseDBUtils.getConnection();
			ps = BaseDBUtils.getPreparedStatement(conn, sql.toString(), (PageRang) map.get("PageRang"));
			rs = BaseDBUtils.executeQuery(ps, (PageRang) map.get("PageRang"), array);
			List<Goods> list = new ArrayList<Goods>();
			while (rs.next()) {
				Goods good = new Goods();
				good.setCount(rs.getInt("goods_count"));
				good.setGoodsId(rs.getInt("goods_id"));
				Shop p = new Shop();
				p.setShopName(rs.getString("shop_name"));
				good.setShop(p);
				good.setGoodsWSUuid(rs.getString("goods_w_s_uuid"));
				Type type = new Type();
				type.setName(rs.getString("type_name"));
				good.setType(type);
				Brand brand = new Brand();
				brand.setBrandName(rs.getString("brand_name"));
				good.setBrand(brand);
				good.setModel(new Model(null, rs.getString("model_name"), rs.getDouble("model_price"),
						rs.getInt("model_size")));
				list.add(good);
			}
			mapss.put("allGoods", list);
			return mapss;
		}
		// 操作者为店铺时
		else {
			StringBuilder sql = new StringBuilder(
					" select g.goods_id,s.shop_name,g.goods_count,g.goods_w_s_uuid,t.type_name,b.brand_name,m.model_name,m.model_price,m.model_size from goods g ");
			sql.append(" INNER JOIN type t ");
			sql.append(" on g.goods_type_id=t.type_id ");
			sql.append(" INNER JOIN model m ");
			sql.append(" on g.goods_model_id=m.model_id ");
			sql.append(" INNER JOIN shop s ");
			sql.append(" on g.goods_shop_id=s.shop_id ");
			sql.append(" INNER JOIN brand b ");
			sql.append(" on g.goods_brand_id=b.brand_id ");
			sql.append(" where s.shop_id=? ");
			sql.append(" and g.goods_flag=1 ");
			List<Object> param = new ArrayList<Object>();
			param.add(map.get("shop_id"));
			if (map.containsKey("goods_id") && map.get("goods_id") != null) {
				sql.append(" and goods_id=? ");
				param.add(map.get("goods_id"));
			}
			if (map.containsKey("shop_name") && map.get("shop_name") != null) {
				sql.append(" and shop_name like ? ");
				param.add("%" + map.get("shop_name") + "%");
			}
			if (map.containsKey("goods_count") && map.get("goods_count") != null) {
				sql.append(" and goods_count=? ");
				param.add(map.get("goods_count"));
			}
			if (map.containsKey("goods_w_s_uuid") && map.get("goods_w_s_uuid") != null) {
				sql.append(" and goods_w_s_uuid like ? ");
				param.add("%" + map.get("goods_w_s_uuid") + "%");
			}
			if (map.containsKey("type_name") && map.get("type_name") != null) {
				sql.append(" and type_name like ? ");
				param.add("%" + map.get("type_name") + "%");
			}
			if (map.containsKey("brand_name") && map.get("brand_name") != null) {
				sql.append(" and brand_name like ? ");
				param.add("%" + map.get("brand_name") + "%");
			}
			if (map.containsKey("model_name") && map.get("model_name") != null) {
				sql.append(" and model_name like ? ");
				param.add("%" + map.get("model_name") + "%");
			}
			if (map.containsKey("model_price") && map.get("model_price") != null) {
				sql.append(" and model_price=? ");
				param.add(map.get("model_price"));
			}
			if (map.containsKey("model_size") && map.get("model_size") != null) {
				sql.append(" and model_size=? ");
				param.add(map.get("model_size"));
			}
			Object[] array = param.toArray();

			conn = BaseDBUtils.getConnection();
			ps = BaseDBUtils.getPreparedStatement(conn, sql.toString(), (PageRang) map.get("PageRang"));
			rs = BaseDBUtils.executeQuery(ps, (PageRang) map.get("PageRang"), array);
			List<Goods> list = new ArrayList<Goods>();
			while (rs.next()) {
				Goods good = new Goods();
				good.setCount(rs.getInt("goods_count"));
				good.setGoodsId(rs.getInt("goods_id"));
				Shop p = new Shop();
				p.setShopName(rs.getString("shop_name"));
				good.setShop(p);
				good.setGoodsWSUuid(rs.getString("goods_w_s_uuid"));
				Type type = new Type();
				type.setName(rs.getString("type_name"));
				good.setType(type);
				Brand brand = new Brand();
				brand.setBrandName(rs.getString("brand_name"));
				good.setBrand(brand);
				good.setModel(new Model(null, rs.getString("model_name") ,rs.getDouble("model_price"),
						rs.getInt("model_size")));
				list.add(good);
			}
			mapss.put("allGoods", list);
			return mapss;

		}
	}

	@Override // 3是入库成功 true 直接添加 false 修改个数
	public boolean selectHuoWu(Map<String, Object> map) throws SQLException {

		boolean b = false;
		int a = 0;
		StringBuilder sql = new StringBuilder();
		sql.append(" select  count(*)  from  goods  where goods_w_s_uuid=?  and goods_model_id=? ");

		Object[] param = { map.get("goods_w_s_uuid"), map.get("goods_model_id") };
		Connection conn = BaseDBUtils.getConnection();
		PreparedStatement ps = BaseDBUtils.getPreparedStatement(conn, sql.toString());
		ResultSet rs = BaseDBUtils.executeQuery(ps, param);
		if (rs != null) {

			while (rs.next()) {
				a = rs.getInt("count(*)");
				if (a == 0) {
					b = true;
				}
			}

		}
		return b;
	}

	@Override
	public int insert(Map<String, Object> map) throws SQLException {
		StringBuilder sql = new StringBuilder();
		JianCeDaoImpl j=new JianCeDaoImpl();
		int id=j.selectShangPuid((String)map.get("goods_shop_uuid"));
		
		sql.append(
				"insert into goods (goods_count,goods_type_id,goods_model_id,goods_brand_id,goods_shop_id,goods_w_s_uuid,goods_flag) VALUES(?,?,?,?,?,?,?)");
		Object[] params = { map.get("goods_count"), map.get("goods_type_id"), map.get("goods_model_id"),
				map.get("goods_brand_id"),id, map.get("goods_w_s_uuid"), 1 };
		Connection conn = BaseDBUtils.getConnection();
		PreparedStatement ps = BaseDBUtils.getPreparedStatement(conn, sql.toString());
		int rs = BaseDBUtils.executeUpdate(ps, params);

		return rs;
	}

	@Override
	public int update(Map<String, Object> map) throws SQLException {
		
		StringBuilder sql = new StringBuilder(" UPDATE goods SET goods_count=? ");
		sql.append(" where goods_shop_id= ");
		sql.append(" (select shop.shop_id from shop ");
		sql.append(" INNER JOIN warehouse_shop ");
		sql.append(" on shop.shop_id=warehouse_shop.warehouse_shop_id ");
		sql.append(" where warehouse_shop.warehouse_shop_warehouseuuid=?) ");
		sql.append(" and goods.goods_model_id= ");
		sql.append(" (select model.model_id from model where model.model_id=?) ");
		sql.append(" and goods.goods_flag=1 ");
		Object[] params = {  map.get("goods_count"), map.get("goods_w_s_uuid"), map.get("goods_model_id") };
		Connection conn = BaseDBUtils.getConnection();
		PreparedStatement ps = BaseDBUtils.getPreparedStatement(conn, sql.toString());
		int rs = BaseDBUtils.executeUpdate(ps, params);
		return rs;
	}

	@Override
	public int jianUpdateShengYuCount(Map<String, Object> map) throws SQLException {
		
		int chaGoodsId = ChaGoodsId(map);
		JianCeDaoImpl j=new JianCeDaoImpl();
		StringBuilder sql = new StringBuilder(" update goods set goods_count=goods_count-? ");
		sql.append(" where goods_model_id=? ");
		Object[] params = { map.get("goods_count"), chaGoodsId };
		Connection conn = BaseDBUtils.getConnection();
		PreparedStatement ps = BaseDBUtils.getPreparedStatement(conn, sql.toString());
		int rs = BaseDBUtils.executeUpdate(ps, params);
		return rs;
	}

	@Override
	public int addUpdateShengYuCount(Map<String, Object> map) throws SQLException {
		int chaGoodsId = ChaGoodsId(map);
		JianCeDaoImpl j=new JianCeDaoImpl();
		StringBuilder sql = new StringBuilder(" update goods set goods_count=goods_count+? ");
		sql.append(" where goods_id=? ");
		System.out.println("入库数量"+map.get("goods_count"));
		Object[] params = {map.get("goods_count"),chaGoodsId };
		Connection conn = BaseDBUtils.getConnection();
		PreparedStatement ps = BaseDBUtils.getPreparedStatement(conn, sql.toString());
		int rs = BaseDBUtils.executeUpdate(ps, params);
		return rs;
	}

	@Override
	public int ChaXunKuCun(Map<String, Object> map) throws SQLException {
		int count = 0;
		
		StringBuilder sql = new StringBuilder(" select goods_count from goods ");
		sql.append(" where goods.goods_model_id=? and goods_w_s_uuid=? and goods_type_id=? and goods_brand_id=? ");
		Object[] params = { map.get("goods_model_id"), (String) map.get("goods_w_s_uuid"),
				map.get("goods_type_id"),  map.get("goods_brand_id") };
		Connection conn = BaseDBUtils.getConnection();
		PreparedStatement ps = BaseDBUtils.getPreparedStatement(conn, sql.toString());
		ResultSet rs = BaseDBUtils.executeQuery(ps, params);
		if (rs.first()) {
			count = rs.getInt("goods_count");
		}
		return count;
	}

	@Override
	public int ChaGoodsId(Map<String, Object> map) throws SQLException {
		int count = 0;
		StringBuilder sql = new StringBuilder(" select goods_id from goods ");
		sql.append(" where goods.goods_model_id=? and goods_w_s_uuid=? and goods_type_id=? and goods_brand_id=? ");
		Object[] params = {  map.get("goods_model_id"), (String) map.get("goods_w_s_uuid"),
				 map.get("goods_type_id"),  map.get("goods_brand_id") };
		Connection conn = BaseDBUtils.getConnection();
		PreparedStatement ps = BaseDBUtils.getPreparedStatement(conn, sql.toString());
		ResultSet rs = BaseDBUtils.executeQuery(ps, params);
		if (rs.first()) {
			count = rs.getInt("goods_id");
		}
		return count;
	}

	@Override
	public int selectgoodsCount(Map<String, Object> map) throws SQLException {
		int role = panduan.pandu((String) map.get("account"));
		int count = 0;
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		// 操作者为管理员时
		if (role > 0) {
			StringBuilder sql = new StringBuilder(" select count(*) as c from goods g ");
			sql.append(" INNER JOIN type t ");
			sql.append(" on g.goods_type_id=t.type_id ");
			sql.append(" INNER JOIN model m ");
			sql.append(" on g.goods_model_id=m.model_id ");
			sql.append(" INNER JOIN shop s ");
			sql.append(" on g.goods_shop_id=s.shop_id ");
			sql.append(" INNER JOIN brand b ");
			sql.append(" on g.goods_brand_id=b.brand_id ");
			sql.append(" where g.goods_flag=1 ");
			conn = BaseDBUtils.getConnection();
			ps = BaseDBUtils.getPreparedStatement(conn, sql.toString(), (PageRang) map.get("PageRang"));
			rs = BaseDBUtils.executeQuery(ps, (PageRang) map.get("PageRang"), null);
			if (rs.first()) {
				count = rs.getInt("c");
			}
			return count;
		}
		// 操作者为店铺时
		if (role == 0) {
			StringBuilder sql = new StringBuilder(" select count(*) as c from goods g ");
			sql.append(" INNER JOIN type t ");
			sql.append(" on g.goods_type_id=t.type_id ");
			sql.append(" INNER JOIN model m ");
			sql.append(" on g.goods_model_id=m.model_id ");
			sql.append(" INNER JOIN shop s ");
			sql.append(" on g.goods_shop_id=s.shop_id ");
			sql.append(" INNER JOIN brand b ");
			sql.append(" on g.goods_brand_id=b.brand_id ");
			sql.append(" where s.shop_id=? ");
			sql.append(" and g.goods_flag=1 ");
			Object[] params = { map.get("shop_id") };
			conn = BaseDBUtils.getConnection();
			ps = BaseDBUtils.getPreparedStatement(conn, sql.toString(), (PageRang) map.get("PageRang"));
			rs = BaseDBUtils.executeQuery(ps, (PageRang) map.get("PageRang"), params);
			if (rs.first()) {
				count = rs.getInt("c");
			}

			return count;

		}
		return count;
	}

//	@Override
//	public int updateShengYuCount(Map<String, Object> map) throws SQLException {
//		// TODO Auto-generated method stub
//		return 0;
//	}

}
