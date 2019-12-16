package cn.kgc.tangcco.tcbd1017.on.buyer.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.tcbd1017.on.buyer.RecordDao;
import cn.kgc.tangcco.tcbd1017.on.pojo.Goods;
import cn.kgc.tangcco.tcbd1017.on.pojo.Record;

/**
*@author 作者：肖越
*@version 1.0 创建时间:2019年12月14日上午8:41:53
*/
public class RecordDaoImpl implements RecordDao {
	SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	
	/**
	 * 根据买家id 显示出该买家所有历史足迹商品的信息以及买家所有信息和历史记录所有信息
	 * 设计了三表联查 ：  1. 买家表（0101_buyer）  2. 历史记录表（0103_record） 3.商品表（0203_goods）
	 * 我把三表联查的结果只要有的信息就全部返回了，如有需要直接调用
	 */
	@Override
	public List<Map> selectRecordAllBybuyer_id(String buyer_Id) throws SQLException {
		List<Map> list=new ArrayList<Map>();
		try {
			Connection conn=BaseDBUtils.getConnection();

			String sql="SELECT  * FROM 0203_goods g INNER JOIN 0103_record r ON g.goods_id=r.goods_id INNER JOIN  0101_buyer b ON r.buyer_id=b.buyer_id WHERE b.buyer_id=? AND buyer_status=2 AND record_status=2 AND goods_status=2 ORDER BY record_create_time DESC ";

			

			PreparedStatement pstmt=BaseDBUtils.getPreparedStatement(conn,sql);
			ResultSet rs=BaseDBUtils.executeQuery(pstmt,buyer_Id);
			while(rs.next()) {
				Map map=new HashMap();
				String goods_id=rs.getString("goods_id");
				String goods_uuid=rs.getString("goods_uuid");
				String goods_create_time=rs.getString("goods_create_time");
				String goods_update_time=rs.getString("goods_update_time");
				String goods_status=rs.getString("goods_status");
				String goods_picture_url_id=rs.getString("goods_picture_url_id");
				String goods_name=rs.getString("goods_name");
				String goods_price=rs.getString("goods_price");
				String goods_brand=rs.getString("goods_brand");
				String goods_type=rs.getString("goods_type");
				String goods_width=rs.getString("goods_width");
				String goods_height=rs.getString("goods_height");
				String goods_length=rs.getString("goods_length");
				String goods_presentation=rs.getString("goods_presentation");
				String seller_id=rs.getString("seller_id");
				String storage_id=rs.getString("storage_id");
				String goods_weight=rs.getString("goods_weight");
				String record_id=rs.getString("record_id");
				String buyer_id=rs.getString("buyer_id");
				String record_create_time=rs.getString("record_create_time");
				String record_update_time=rs.getString("record_update_time");
				String record_status=rs.getString("record_status");
				String buyer_uuid=rs.getString("buyer_uuid");
				String buyer_name=rs.getString("buyer_name");
				String buyer_mobile=rs.getString("buyer_mobile");
				String buyer_mail=rs.getString("buyer_mail");
				String buyer_create_time=rs.getString("buyer_create_time");
				String buyer_update_time=rs.getString("buyer_update_time");
				String buyer_status=rs.getString("buyer_status");
				
				
				map.put("goods_id",goods_id);
				map.put("goods_uuid",goods_uuid);
				map.put("goods_create_time",goods_create_time);
				map.put("goods_update_time",goods_update_time);
				map.put("goods_status",goods_status);
				map.put("goods_picture_url_id",goods_picture_url_id);
				map.put("goods_name",goods_name);
				map.put("goods_price",goods_price);
				map.put("goods_brand",goods_brand);
				map.put("goods_type",goods_type);
				map.put("goods_width",goods_width);
				map.put("goods_height",goods_height);
				map.put("goods_length",goods_length);
				map.put("goods_presentation",goods_presentation);
				map.put("seller_id",seller_id);
				map.put("storage_id",storage_id);
				map.put("goods_weight",goods_weight);
				map.put("record_id",record_id);
				map.put("buyer_id",buyer_id);
				map.put("record_create_time",record_create_time);
				map.put("record_update_time",record_update_time);
				map.put("record_status",record_status);
				map.put("buyer_uuid",buyer_uuid);
				map.put("buyer_name",buyer_name);
				map.put("buyer_mobile",buyer_mobile);
				map.put("buyer_mail",buyer_mail);
				map.put("buyer_create_time",buyer_create_time);
				map.put("buyer_update_time",buyer_update_time);
				map.put("buyer_status",buyer_status);
				list.add(map);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int deleteRecordByRecord_id(String record_id) throws SQLException {
		int count=0;
		try {
			Connection conn=BaseDBUtils.getConnection();
			String sql="UPDATE  0103_record SET record_status=1 WHERE record_id=?";
			PreparedStatement pstmt=BaseDBUtils.getPreparedStatement(conn,sql);
			count=BaseDBUtils.executeUpdate(pstmt,record_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}


	
	/**
	 * 根据传过来的买家id 和商品名称（支持模糊查询）
	 * 所对应的商品
	 */
	@Override
	public List<Map> selectRecordAllByBuyerIdAndGoodsName(String buyer_Id, String goods_Name) throws SQLException {
		List<Map> list=new ArrayList<Map>();
		try {
			Connection conn=BaseDBUtils.getConnection();
			String sql="SELECT  * FROM 0203_goods g INNER JOIN 0103_record r ON g.goods_id=r.goods_id INNER JOIN  0101_buyer b ON r.buyer_id=b.buyer_id WHERE b.buyer_id=? AND  goods_name LIKE ? AND buyer_status=2 AND record_status=2 AND goods_status=2   ORDER BY record_create_time DESC"; 
				
			PreparedStatement pstmt=BaseDBUtils.getPreparedStatement(conn,sql);
			ResultSet rs=BaseDBUtils.executeQuery(pstmt,buyer_Id,"%"+goods_Name+"%");
			while(rs.next()) {
				Map map=new HashMap();
				String goods_id=rs.getString("goods_id");
				String goods_uuid=rs.getString("goods_uuid");
				String goods_create_time=rs.getString("goods_create_time");
				String goods_update_time=rs.getString("goods_update_time");
				String goods_status=rs.getString("goods_status");
				String goods_picture_url_id=rs.getString("goods_picture_url_id");
				String goods_name=rs.getString("goods_name");
				String goods_price=rs.getString("goods_price");
				String goods_brand=rs.getString("goods_brand");
				String goods_type=rs.getString("goods_type");
				String goods_width=rs.getString("goods_width");
				String goods_height=rs.getString("goods_height");
				String goods_length=rs.getString("goods_length");
				String goods_presentation=rs.getString("goods_presentation");
				String seller_id=rs.getString("seller_id");
				String storage_id=rs.getString("storage_id");
				String goods_weight=rs.getString("goods_weight");
				String record_id=rs.getString("record_id");
				String buyer_id=rs.getString("buyer_id");
				String record_create_time=rs.getString("record_create_time");
				String record_update_time=rs.getString("record_update_time");
				String record_status=rs.getString("record_status");
				String buyer_uuid=rs.getString("buyer_uuid");
				String buyer_name=rs.getString("buyer_name");
				String buyer_mobile=rs.getString("buyer_mobile");
				String buyer_mail=rs.getString("buyer_mail");
				String buyer_create_time=rs.getString("buyer_create_time");
				String buyer_update_time=rs.getString("buyer_update_time");
				String buyer_status=rs.getString("buyer_status");
				
				
				map.put("goods_id",goods_id);
				map.put("goods_uuid",goods_uuid);
				map.put("goods_create_time",goods_create_time);
				map.put("goods_update_time",goods_update_time);
				map.put("goods_status",goods_status);
				map.put("goods_picture_url_id",goods_picture_url_id);
				map.put("goods_name",goods_name);
				map.put("goods_price",goods_price);
				map.put("goods_brand",goods_brand);
				map.put("goods_type",goods_type);
				map.put("goods_width",goods_width);
				map.put("goods_height",goods_height);
				map.put("goods_length",goods_length);
				map.put("goods_presentation",goods_presentation);
				map.put("seller_id",seller_id);
				map.put("storage_id",storage_id);
				map.put("goods_weight",goods_weight);
				map.put("record_id",record_id);
				map.put("buyer_id",buyer_id);
				map.put("record_create_time",record_create_time);
				map.put("record_update_time",record_update_time);
				map.put("record_status",record_status);
				map.put("buyer_uuid",buyer_uuid);
				map.put("buyer_name",buyer_name);
				map.put("buyer_mobile",buyer_mobile);
				map.put("buyer_mail",buyer_mail);
				map.put("buyer_create_time",buyer_create_time);
				map.put("buyer_update_time",buyer_update_time);
				map.put("buyer_status",buyer_status);
				list.add(map);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	
	/**
	 * 根据传过来的买家id和商品id添加一条历史足迹 
	 * @param buyer_id 买家id
	 * @param goods_id 商品id
	 * @return 受影响的行数
	 * @throws SQLException
	 */
	@Override
	public int insertRecordByBuyer_idAndGoods_id(String buyer_id, String goods_id) throws SQLException {
		int count=0;
		try {
			Connection conn=BaseDBUtils.getConnection();
			Date date=new Date();
			SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date1=sim.format(date);
			
			String sql="INSERT INTO  0103_record VALUES(NULL,?,?,'"+date1+"','"+date1+"',2)";
			PreparedStatement pstmt=BaseDBUtils.getPreparedStatement(conn,sql);
			count=BaseDBUtils.executeUpdate(pstmt,buyer_id,goods_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	
	
	
	

	
	
	
	//测试
//	public static void main(String[] args) {
//		try {

//			int i=new RecordDaoImpl().insertRecordByBuyer_idAndGoods_id("4", "6");
////		for (int i = 0; i < list.size(); i++) {
////			Map map=list.get(i);
////			System.out.println(map);
////		}
//			if(i>0) {
//				System.out.println("添加数据成功！！");
//			}

//			List<Map> list=new RecordDaoImpl().selectRecordAllBybuyer_id("1");
//		for (int i = 0; i < list.size(); i++) {
//			Map map=list.get(i);
//			System.out.println(map);
//		}
//			

//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			System.out.println("出现异常！！！");
//		}
//	}


	

////
//	

	
	
}
