package cn.kgc.tangcco.tcbd1017.on.seller.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.tcbd1017.on.seller.SellerModifyGoodsInformation;

/**
*@author   席首华
*@version  1.0   </br>
   创建时间   2019年12月21日     下午3:32:35
  类描述：
*
*/
public class SellerModifyGoodsInformationDaoImpl implements SellerModifyGoodsInformation {

	/**
	 * 根据商品id修改商品的某些详细信息
	 */
	@Override
	public int modifyGoodsInformation(Map<String, Object> map) {
		int result=0;
		StringBuffer sql = new StringBuffer(" update 0203_goods ");
		sql.append(" set goods_type = ? , ");
		sql.append(" goods_name = ? ,  ");
		sql.append(" goods_price = ? , ");
		sql.append(" goods_brand = ? , ");
		sql.append(" goods_update_time =? , ");
		sql.append(" goods_presentation=? ");
		sql.append("  where 1=1 ");
		sql.append(" and goods_id=?");
		Object [] param = {map.get("goods_type"),map.get("goods_name"),map.get("goods_price"),map.get("goods_brand"),map.get("goods_update_time"),map.get("goods_presentation"),map.get("goods_id")};
		try {
			Connection connection = BaseDBUtils.getConnection();
			PreparedStatement preparedStatement = BaseDBUtils.getPreparedStatement(connection, sql.toString());
			result = BaseDBUtils.executeUpdate(preparedStatement, param);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
