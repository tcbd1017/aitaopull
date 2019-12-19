package cn.kgc.tangcco.tcbd1017.on.seller.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.tcbd1017.on.pojo.Goods;
import cn.kgc.tangcco.tcbd1017.on.seller.GoodsDao;

/**
 * @author 谷亚坤
 * @version 创建时间：2019年12月14日 下午2:26:55
 * @ClassName 类名称 0203goods接口实现类
 * @Description 类描述 0203goods上架下架接口实现类
 **/
public class GoodsDaoImpl implements GoodsDao {

	QueryRunner qr = new QueryRunner();

	/**
	 * 查看所有未上架的商品
	 */
	@Override
	public List<Goods> selectOfflineGoods(int store_id) throws SQLException {
		/**
		 * SQL语句
		 */
		StringBuilder sql = new StringBuilder(
				"SELECT g.goods_id,g.goods_type,g.goods_brand,g.goods_name,g.goods_price,g.goods_presentation FROM 0203_goods AS g");
		sql.append(" WHERE 1 = 1  ");
		/**
		 * 动态SQL
		 */
		if (store_id > 0) {
			sql.append(" AND seller_id = ? ");
			sql.append(" AND goods_status = 2 ");
		} else {
			System.out.println("店铺id输入错误！");
		}
		/**
		 * 新建集合 用于存储信息
		 */
		List<Goods> newList = new ArrayList<Goods>();
		/**
		 * 数组存储数值
		 */
		Object[] param = { store_id };
		/**
		 * 获取链接
		 */
		Connection conn;
		/**
		 * 预编译SQK语句
		 */
		PreparedStatement pst;
		/**
		 * 返回的结果集
		 */
		ResultSet rs;
		return newList;
	}

	/**
	 * 根据商品id选择上架商品
	 */
	@Override
	public int supdateGoods(int goods_id) throws SQLException {
		StringBuilder sql = new StringBuilder("UPDATE 0203_goods SET goods_status = 3");
		if (goods_id > 0) {
			sql.append(" WHERE 1=1 ");
			sql.append(" AND goods_id = ? ");
		}
		Object[] params = { goods_id };
		return qr.execute(BaseDBUtils.getConnection(), sql.toString(), params);
	}

	/**
	 * 根据商品id选择下架商品
	 */
	@Override
	public int xupdateGoods(int goods_id) throws SQLException {
		/**
		 * SQL语句
		 */
		StringBuilder sql = new StringBuilder("UPDATE 0203_goods SET goods_status = 2");
		/**
		 * 动态SQL
		 */
		if (goods_id > 0) {
			sql.append(" WHERE 1=1 ");
			sql.append(" AND goods_id = ? ");
		}
		/**
		 * 参数数组
		 */
		Object[] params = { goods_id };
		/**
		 * 返回变化数字
		 */
		return qr.execute(BaseDBUtils.getConnection(), sql.toString(), params);
	}

	/**
	 * 根据商品id修改商品价钱
	 */
	@Override
	public int updateGoodsgoods_price(int goods_id, double goods_price) throws SQLException {
		/**
		 * SQL语句
		 */
		StringBuilder sql = new StringBuilder("UPDATE 0203_goods ");
		/**
		 * 动态SQL
		 */
		if (goods_price > 0) {
			sql.append(" SET goods_price = ? ");
		}
		sql.append(" WHERE 1=1 ");
		/**
		 * 动态SQL
		 */
		if (goods_id > 0) {
			sql.append(" AND goods_id = ? ");
		}
		/**
		 * 参数存储在数组中
		 */
		Object[] params = { goods_price, goods_id };
		/**
		 * 返回变化结果
		 */
		return qr.execute(BaseDBUtils.getConnection(), sql.toString(), params);
	}

	@Override
	public List<Goods> selectOnlineGoods(int store_id) throws SQLException {
		/**
		 * SQL语句
		 */
		StringBuilder sql = new StringBuilder(
				"SELECT g.goods_id,g.goods_type,g.goods_brand,g.goods_name,g.goods_price,g.goods_presentation FROM 0203_goods AS g");
		sql.append(" WHERE 1 = 1  ");
		/**
		 * 动态SQL
		 */
		if (store_id > 0) {
			sql.append(" AND seller_id = ? ");
			sql.append(" AND goods_status = 3 ");
		} else {
			System.out.println("店铺id输入错误！");
		}
		/**
		 * 新建集合 用于存储信息
		 */
		List<Goods> newList = new ArrayList<Goods>();
		/**
		 * 数组存储数值
		 */
		Object[] param = { store_id };
		/**
		 * 获取链接
		 */
		Connection conn;
		/**
		 * 预编译SQK语句
		 */
		PreparedStatement pst;
		/**
		 * 返回的结果集
		 */
		ResultSet rs;
		try {
			conn = BaseDBUtils.getConnection();
			pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
			rs = BaseDBUtils.executeQuery(pst, param);
			/**
			 * 集合遍历存储到List集合
			 */
			while (rs.next()) {
//				newList.add(new Goods(rs.getInt("goods_id"), rs.getString("goods_type"), rs.getString("goods_brand"),
//						rs.getString("goods_name"), rs.getDouble("goods_price"), rs.getString("goods_presentation")));
			}
		} catch (Exception e) {
		}
		return newList;
	}

	/**
	 * 根据商品id查看商品详细信息
	 */
	@Override
	public List<Goods> selectAllGoods(int goods_id) throws SQLException {
		/**
		 * SQL语句
		 */
		StringBuilder sql = new StringBuilder(
				"SELECT g.goods_id,g.goods_uuid,g.goods_create_time,g.goods_update_time,g.goods_status,g.goods_picture_url_id,g.goods_name,g.goods_price,g.goods_brand,g.goods_type,g.goods_width,g.goods_height,g.goods_length,g.goods_presentation,g.seller_id,g.storage_id,g.goods_weight FROM 0203_goods AS g");
		sql.append(" WHERE 1 = 1 ");
		/**
		 * 动态SQL
		 */
		if (goods_id > 0) {
			sql.append(" AND goods_id = ? ");
			sql.append(" AND goods_status = 2 ");
		} else {
			System.out.println("店铺id输入错误！");
		}
		/**
		 * 新建集合 用于存储信息
		 */
		List<Goods> newList = new ArrayList<Goods>();
		/**
		 * 数组存储数值
		 */
		Object[] param = { goods_id };
		/**
		 * 获取链接
		 */
		Connection conn;
		/**
		 * 预编译SQK语句
		 */
		PreparedStatement pst;
		/**
		 * 返回的结果集
		 */
		ResultSet rs;
		try {
			conn = BaseDBUtils.getConnection();
			pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
			rs = BaseDBUtils.executeQuery(pst, param);
			/**
			 * 集合遍历存储到List集合
			 */
			while (rs.next()) {
				newList.add(new Goods(rs.getInt("goods_id"), rs.getString("goods_uuid"),
						rs.getDate("goods_create_time"), rs.getDate("goods_update_time"), rs.getInt("goods_status"),
						rs.getInt("goods_picture_url_id"), rs.getString("goods_name"), rs.getDouble("goods_price"),
						rs.getString("goods_brand"), rs.getString("goods_type"), rs.getDouble("goods_width"),
						rs.getDouble("goods_height"), rs.getDouble("goods_length"), rs.getString("goods_presentation"),
						rs.getInt("seller_id"), rs.getInt("storage_id"), rs.getDouble("goods_weight")));
			}
		} catch (Exception e) {
		}
		return newList;
	}

}
