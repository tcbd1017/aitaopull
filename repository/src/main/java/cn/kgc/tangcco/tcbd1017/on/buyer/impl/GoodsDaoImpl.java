package cn.kgc.tangcco.tcbd1017.on.buyer.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.tcbd1017.on.buyer.GoodsDao;
import cn.kgc.tangcco.tcbd1017.on.pojo.Goods;

/**
*@author 作者：肖越
*@version 1.0 创建时间:2019年12月13日下午20:40:40
*/
public class GoodsDaoImpl implements GoodsDao {
	SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * 查询所有的物品
	 */
	@Override
	public List<Goods> selectAllGoods() throws SQLException {
		List<Goods> list=new ArrayList<Goods>();
		try {
		Connection conn=BaseDBUtils.getConnection();
		String sql="SELECT * FROM 0203_goods";
		PreparedStatement pstmt=BaseDBUtils.getPreparedStatement(conn,sql);
		ResultSet rs=BaseDBUtils.executeQuery(pstmt);
			while(rs.next()) {
				Goods g=new Goods();
				int goods_id=rs.getInt("goods_id");//'商品id'
				String goods_uuid=rs.getString("goods_uuid");//'商品唯一标识符 uuld'
				String goods_create_time=rs.getString("goods_create_time");//'商品创建时间'
				String goods_update_time=rs.getString("goods_update_time");//'商品更新时间'
				int goods_status=rs.getInt("goods_status");//'商品状态 1删除2正常'
				int goods_picture_url_id=rs.getInt("goods_picture_url_id");//'商品图片地址'
				String goods_name=rs.getString("goods_name");//'商品名称'
				double goods_price=rs.getDouble("goods_price");//'商品价格'
				String goods_brand=rs.getString("goods_brand");//'商品品牌'
				String goods_type=rs.getString("goods_type");//'商品类型'
				double goods_width=rs.getDouble("goods_width");//'商品的宽'
				double goods_height=rs.getDouble("goods_height");//'商品的高'
				double goods_length=rs.getDouble("goods_length");//'商品的长'
				String goods_presentation=rs.getString("goods_presentation");//'商品简介'
				int seller_id=rs.getInt("seller_id");//'店铺id'
				int storage_id=rs.getInt("storage_id");//'仓库id'
				double goods_weight=rs.getDouble("goods_weight");//'商品重量'
				
				
				g.setGoods_id(goods_id);//'商品id'
				g.setGoods_uuid(goods_uuid);//'商品唯一标识符 uuld'
				g.setGoods_create_time(sim.parse(goods_create_time));//'商品创建时间'
				g.setGoods_update_time(sim.parse(goods_update_time));//'商品更新时间'
				g.setGoods_status(goods_status);//'商品状态 1删除2正常'
				g.setGoods_picture_url_id(goods_picture_url_id);//'商品图片地址'
				g.setGoods_name(goods_name);//'商品名称'
				g.setGoods_price(goods_price);//'商品价格'
				g.setGoods_brand(goods_brand);//'商品品牌'
				g.setGoods_type(goods_type);//'商品类型'
				g.setGoods_width(goods_width);//'商品的宽'
				g.setGoods_height(goods_height);//'商品的高'
				g.setGoods_length(goods_length);//'商品的长'
				g.setGoods_presentation(goods_presentation);//'商品简介'
				g.setSeller_id(seller_id);//'店铺id'
				g.setStorage_id(storage_id);//'仓库id'
				g.setGoods_weight(goods_weight);//'商品重量'
				list.add(g);
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	
	
	@Override
	public List<Goods> selectByGoodsType(String goodsType) throws SQLException {
		List<Goods> list=new ArrayList<Goods>();
		try {
		Connection conn=BaseDBUtils.getConnection();
		String sql="SELECT * FROM 0203_goods WHERE goods_type=?";
		PreparedStatement pstmt=BaseDBUtils.getPreparedStatement(conn,sql);
		ResultSet rs=BaseDBUtils.executeQuery(pstmt,goodsType);
			while(rs.next()) {
				Goods g=new Goods();
				int goods_id=rs.getInt("goods_id");//'商品id'
				String goods_uuid=rs.getString("goods_uuid");//'商品唯一标识符 uuld'
				String goods_create_time=rs.getString("goods_create_time");//'商品创建时间'
				String goods_update_time=rs.getString("goods_update_time");//'商品更新时间'
				int goods_status=rs.getInt("goods_status");//'商品状态 1删除2正常'
				int goods_picture_url_id=rs.getInt("goods_picture_url_id");//'商品图片地址'
				String goods_name=rs.getString("goods_name");//'商品名称'
				double goods_price=rs.getDouble("goods_price");//'商品价格'
				String goods_brand=rs.getString("goods_brand");//'商品品牌'
				String goods_type=rs.getString("goods_type");//'商品类型'
				double goods_width=rs.getDouble("goods_width");//'商品的宽'
				double goods_height=rs.getDouble("goods_height");//'商品的高'
				double goods_length=rs.getDouble("goods_length");//'商品的长'
				String goods_presentation=rs.getString("goods_presentation");//'商品简介'
				int seller_id=rs.getInt("seller_id");//'店铺id'
				int storage_id=rs.getInt("storage_id");//'仓库id'
				double goods_weight=rs.getDouble("goods_weight");//'商品重量'
				
				
				g.setGoods_id(goods_id);//'商品id'
				g.setGoods_uuid(goods_uuid);//'商品唯一标识符 uuld'
				g.setGoods_create_time(sim.parse(goods_create_time));//'商品创建时间'
				g.setGoods_update_time(sim.parse(goods_update_time));//'商品更新时间'
				g.setGoods_status(goods_status);//'商品状态 1删除2正常'
				g.setGoods_picture_url_id(goods_picture_url_id);//'商品图片地址'
				g.setGoods_name(goods_name);//'商品名称'
				g.setGoods_price(goods_price);//'商品价格'
				g.setGoods_brand(goods_brand);//'商品品牌'
				g.setGoods_type(goods_type);//'商品类型'
				g.setGoods_width(goods_width);//'商品的宽'
				g.setGoods_height(goods_height);//'商品的高'
				g.setGoods_length(goods_length);//'商品的长'
				g.setGoods_presentation(goods_presentation);//'商品简介'
				g.setSeller_id(seller_id);//'店铺id'
				g.setStorage_id(storage_id);//'仓库id'
				g.setGoods_weight(goods_weight);//'商品重量'
				list.add(g);
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}






	/**
	 * 按照商品名称、或者商品类型、或者商品简介 ,模糊查询出相关商品
	 */
	@Override
	public List<Goods> selectVagueByGoods_nameOrGoods_brandOrGoods_presentation(String vague) throws SQLException {
		List<Goods> list=new ArrayList<Goods>();
		try {
		Connection conn=BaseDBUtils.getConnection();
		String sql="SELECT * FROM 0203_goods WHERE goods_name LIKE ? OR goods_brand LIKE ? OR goods_presentation LIKE ?";
		PreparedStatement pstmt=BaseDBUtils.getPreparedStatement(conn,sql);
		ResultSet rs=BaseDBUtils.executeQuery(pstmt,"%"+vague+"%","%"+vague+"%","%"+vague+"%");
			while(rs.next()) {
				Goods g=new Goods();
				int goods_id=rs.getInt("goods_id");//'商品id'
				String goods_uuid=rs.getString("goods_uuid");//'商品唯一标识符 uuld'
				String goods_create_time=rs.getString("goods_create_time");//'商品创建时间'
				String goods_update_time=rs.getString("goods_update_time");//'商品更新时间'
				int goods_status=rs.getInt("goods_status");//'商品状态 1删除2正常'
				int goods_picture_url_id=rs.getInt("goods_picture_url_id");//'商品图片地址'
				String goods_name=rs.getString("goods_name");//'商品名称'
				double goods_price=rs.getDouble("goods_price");//'商品价格'
				String goods_brand=rs.getString("goods_brand");//'商品品牌'
				String goods_type=rs.getString("goods_type");//'商品类型'
				double goods_width=rs.getDouble("goods_width");//'商品的宽'
				double goods_height=rs.getDouble("goods_height");//'商品的高'
				double goods_length=rs.getDouble("goods_length");//'商品的长'
				String goods_presentation=rs.getString("goods_presentation");//'商品简介'
				int seller_id=rs.getInt("seller_id");//'店铺id'
				int storage_id=rs.getInt("storage_id");//'仓库id'
				double goods_weight=rs.getDouble("goods_weight");//'商品重量'
				
				
				g.setGoods_id(goods_id);//'商品id'
				g.setGoods_uuid(goods_uuid);//'商品唯一标识符 uuld'
				g.setGoods_create_time(sim.parse(goods_create_time));//'商品创建时间'
				g.setGoods_update_time(sim.parse(goods_update_time));//'商品更新时间'
				g.setGoods_status(goods_status);//'商品状态 1删除2正常'
				g.setGoods_picture_url_id(goods_picture_url_id);//'商品图片地址'
				g.setGoods_name(goods_name);//'商品名称'
				g.setGoods_price(goods_price);//'商品价格'
				g.setGoods_brand(goods_brand);//'商品品牌'
				g.setGoods_type(goods_type);//'商品类型'
				g.setGoods_width(goods_width);//'商品的宽'
				g.setGoods_height(goods_height);//'商品的高'
				g.setGoods_length(goods_length);//'商品的长'
				g.setGoods_presentation(goods_presentation);//'商品简介'
				g.setSeller_id(seller_id);//'店铺id'
				g.setStorage_id(storage_id);//'仓库id'
				g.setGoods_weight(goods_weight);//'商品重量'
				list.add(g);
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	
//	public static void main(String[] args) {
//		try {
//			List<Goods> list=new GoodsDaoImpl().selectVagueByGoods_nameOrGoods_brandOrGoods_presentation("肖");
//		for (int i = 0; i < list.size(); i++) {
//			Goods g=list.get(i);
//			System.out.println(g);
//		}
//			
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			System.out.println("出现异常！！！");
//		}
//	}
	
	
	
}
