package cn.kgc.tangcco.tcbd1017.on.seller.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.tcbd1017.on.seller.FrontStoreStockGoodsDaoIns;

/** 
* @author 作者 Your-Name: 刘煜
* @version 创建时间：2019年12月16日 上午9:08:23 
*    类说明 
*/
public class FrontStoreStockGoodsDaoImpl implements FrontStoreStockGoodsDaoIns{

	@Override
	//商家购买货物
	public int insertStockGoods(Map<String, Object> maps) throws SQLException {
		Connection connection = BaseDBUtils.getConnection();
		PreparedStatement preparedStatement = null;
		StringBuilder sql = new StringBuilder(" INSERT INTO 0209_stock_goods ");
		sql.append(" (stock_goods_id,stock_goods_type,stock_goods_brand,stock_goods_amount,stock_goods_price,seller_id,stock_goods_create_time,stock_goods_status )  ");
		sql.append(" VALUES(NULL,?,?,?,?,?,?,'2') ");
		Object[] param = { maps.get("stock_goods_type"), maps.get("stock_goods_brand"), maps.get("stock_goods_amount"), maps.get("stock_goods_price"),maps.get("seller_id"),maps.get("stock_goods_create_time") };
		preparedStatement = BaseDBUtils.getPreparedStatement(connection, sql.toString());
		int rs = BaseDBUtils.executeUpdate(preparedStatement, param);
		return rs;
	}
}
