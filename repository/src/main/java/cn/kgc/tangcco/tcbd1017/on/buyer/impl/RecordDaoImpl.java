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
			String sql="SELECT  * FROM 0203_goods g INNER JOIN 0103_record r ON g.goods_id=r.goods_id INNER JOIN  0101_buyer b ON r.buyer_id=b.buyer_id WHERE b.buyer_id=? AND buyer_status=2 AND record_status=2 AND goods_status>2 ORDER BY record_create_time DESC ";
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
	 * 返回买家历史记录里所对应的商品图片url等信息
	 * 
	 */
	@Override
	public List<Map> selectRecordAllByBuyerIdAndGoodsName(String buyer_Id, String goods_Name) throws SQLException {
		List<Map> list=new ArrayList<Map>();
		
		
		try {
			Connection conn=BaseDBUtils.getConnection();
			String sql="SELECT g.goods_id,goods_name,goods_price,goods_brand,goods_type,goods_presentation,buyer_name,buyer_mobile,buyer_mail,goods_picture_url FROM 0203_goods g INNER JOIN 0103_record r ON g.goods_id=r.goods_id INNER JOIN  0101_buyer b ON r.buyer_id=b.buyer_id INNER JOIN 020301_goods_picture_url p ON g.goods_id=p.goods_id WHERE b.buyer_id=? AND  goods_name LIKE ? AND buyer_status=2 AND record_status=2 AND goods_status>2  AND goods_picture_url_status=2 ORDER BY record_create_time DESC LIMIT 0,5"; 
					 
				
			PreparedStatement pstmt=BaseDBUtils.getPreparedStatement(conn,sql);
			ResultSet rs=BaseDBUtils.executeQuery(pstmt,buyer_Id,"%"+goods_Name+"%");
			
			while(rs.next()) {
				Map map=new HashMap();
				String goods_id=rs.getString("goods_id");
				String goods_name=rs.getString("goods_name");
				String goods_price=rs.getString("goods_price");
				String goods_brand=rs.getString("goods_brand");
				String goods_type=rs.getString("goods_type");
				String goods_presentation=rs.getString("goods_presentation");
				String buyer_name=rs.getString("buyer_name");
				String buyer_mobile=rs.getString("buyer_mobile");
				String buyer_mail=rs.getString("buyer_mail");
				String goods_picture_url=rs.getString("goods_picture_url");
				
				
				map.put("goods_id",goods_id);
				map.put("goods_name",goods_name);
				map.put("goods_price",goods_price);
				map.put("goods_brand",goods_brand);
				map.put("goods_type",goods_type);
				map.put("goods_presentation",goods_presentation);
				map.put("buyer_name",buyer_name);
				map.put("buyer_mobile",buyer_mobile);
				map.put("buyer_mail",buyer_mail);
				map.put("goods_picture_url",goods_picture_url);
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

	
	/**
	 * 《肖越根据前台页面需求额外新添加的方法 1》
	 * 大家如果有需要就调用就好了
	 * 此方法根据买家id返回买家详细信息
	 * @param buyer_id 买家id
	 * @return 返回该买家详细信息
	 * @throws SQLException
	 */
	@Override
	public List<Map> selectBuyerInfoBybuyer_id(String buyer_Id) throws SQLException {
		List<Map> list=new ArrayList<Map>();
		try {
			Connection conn=BaseDBUtils.getConnection();
			String sql="SELECT * FROM  010101_buyer_info WHERE buyer_id=? AND buyer_info_status=2"; 
				
			PreparedStatement pstmt=BaseDBUtils.getPreparedStatement(conn,sql);
			ResultSet rs=BaseDBUtils.executeQuery(pstmt,buyer_Id);
			while(rs.next()) {
				Map map=new HashMap();
				String buyer_info_id=rs.getString("buyer_info_id");
				String buyer_id=rs.getString("buyer_id");
				String buyer_info_gender=rs.getString("buyer_info_gender");
				String buyer_info_idcard=rs.getString("buyer_info_idcard");
				String buyer_info_idcard_name=rs.getString("buyer_info_idcard_name");
				String buyer_info_birthday=rs.getString("buyer_info_birthday");
				String buyer_info_address=rs.getString("buyer_info_address");
				String buyer_info_icon_url=rs.getString("buyer_info_icon_url");
				String buyer_info_create_time=rs.getString("buyer_info_create_time");
				String buyer_info_update_time=rs.getString("buyer_info_update_time");
				String buyer_info_status=rs.getString("buyer_info_status");
				
				
				map.put("buyer_info_id",buyer_info_id);
				map.put("buyer_id",buyer_id);
				map.put("buyer_info_gender",buyer_info_gender);
				map.put("buyer_info_idcard",buyer_info_idcard);
				map.put("buyer_info_idcard_name",buyer_info_idcard_name);
				map.put("buyer_info_birthday",buyer_info_birthday);
				map.put("buyer_info_address",buyer_info_address);
				map.put("buyer_info_icon_url",buyer_info_icon_url);
				map.put("buyer_info_create_time",buyer_info_create_time);
				map.put("buyer_info_update_time",buyer_info_update_time);
				map.put("buyer_info_status",buyer_info_status);
			
				list.add(map);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
	/**
	 * 《肖越根据前台页面需求额外新添加的方法 2》
	 * 大家如果有需要就调用就好了
	 * 此方法根据买家id和当前页码（第几页 ）   返回买家历史记录里商品图片url等信息
	 * @param buyer_id 买家id
	 * @return 返回该买家历史记录里商品图片url等信息
	 * @throws SQLException
	 */
	@Override
	public List<Map> selectGoodsPictureUrlBybuyer_id(String buyer_Id,String pageNo) throws SQLException {
		List<Map> list=new ArrayList<Map>();
		int i=Integer.parseInt(pageNo);
		int p=(i-1)*5;
		try {
			Connection conn=BaseDBUtils.getConnection();
			String sql="SELECT  g.goods_id,goods_name,goods_price,goods_brand,goods_type,goods_presentation,buyer_name,buyer_mobile,buyer_mail,goods_picture_url FROM 0203_goods g INNER JOIN 0103_record r ON g.goods_id=r.goods_id INNER JOIN  0101_buyer b ON r.buyer_id=b.buyer_id INNER JOIN 020301_goods_picture_url p ON g.goods_id=p.goods_id WHERE b.buyer_id=? AND buyer_status=2 AND record_status=2 AND goods_status>2  AND goods_picture_url_status=2 ORDER BY record_create_time DESC LIMIT "+p+",5" ; 
				
				
			PreparedStatement pstmt=BaseDBUtils.getPreparedStatement(conn,sql);
			ResultSet rs=BaseDBUtils.executeQuery(pstmt,buyer_Id);
			while(rs.next()) {
				Map map=new HashMap();
				String goods_id=rs.getString("goods_id");
				String goods_name=rs.getString("goods_name");
				String goods_price=rs.getString("goods_price");
				String goods_brand=rs.getString("goods_brand");
				String goods_type=rs.getString("goods_type");
				String goods_presentation=rs.getString("goods_presentation");
				String buyer_name=rs.getString("buyer_name");
				String buyer_mobile=rs.getString("buyer_mobile");
				String buyer_mail=rs.getString("buyer_mail");
				String goods_picture_url=rs.getString("goods_picture_url");
				
				
				map.put("goods_id",goods_id);
				map.put("goods_name",goods_name);
				map.put("goods_price",goods_price);
				map.put("goods_brand",goods_brand);
				map.put("goods_type",goods_type);
				map.put("goods_presentation",goods_presentation);
				map.put("buyer_name",buyer_name);
				map.put("buyer_mobile",buyer_mobile);
				map.put("buyer_mail",buyer_mail);
				map.put("goods_picture_url",goods_picture_url);
				list.add(map);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	
	
	/**
	 * 《肖越根据前台页面需求额外新添加的方法 3》
	 * 大家如果有需要就调用就好了
	 * 此方法根据买家id获取买家历史记录总条数
	 * @param buyer_id 买家id
	 * @return  根据买家id获取(buyerHistory)买家历史记录总条数    和 总页数(totalPages)
	 * @throws SQLException
	 */
	@Override
	public List<Map> selectCountBuyerHistory(String buyer_id) throws SQLException {
		List<Map> list=new ArrayList<Map>();
		try {
			Connection conn=BaseDBUtils.getConnection();
			String sql="SELECT COUNT(1) buyerHistory FROM  0103_record r INNER JOIN  0101_buyer b ON r.buyer_id=b.buyer_id WHERE b.buyer_id=? AND buyer_status=2 AND record_status=2"; 
					
				
				
			PreparedStatement pstmt=BaseDBUtils.getPreparedStatement(conn,sql);
			ResultSet rs=BaseDBUtils.executeQuery(pstmt,buyer_id);
			while(rs.next()) {
				Map map=new HashMap();
				String buyerHistory=rs.getString("buyerHistory");
				int totalPages=Integer.parseInt(buyerHistory);
				if(totalPages%5==0) {
					totalPages=totalPages/5;
				}else {
					totalPages=totalPages/5+1;	
				}
				
				
				
				map.put("buyerHistory",buyerHistory);
				map.put("totalPages",totalPages);
				list.add(map);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	
	
	/**
	 * 《肖越根据前台页面需求额外新添加的方法 4》
	 * 大家如果有需要就调用就好了
	 * 此方法根据买家id获取买家购物车前两条数据
	 * @param buyer_id 买家id
	 * @return 买家购物车前两条数据
	 * @throws SQLException
	 */
	@Override
	public List<Map> selectBuyerShoppingCart(String buyer_id) throws SQLException {
		List<Map> list=new ArrayList<Map>();
		try {
			Connection conn=BaseDBUtils.getConnection();
			String sql="SELECT goods_picture_url,goods_name,goods_price,goods_presentation,shopping_cart_id FROM 020301_goods_picture_url AS gpu INNER JOIN 0108_shopping_cart AS sc  ON gpu.goods_id=sc.goods_id INNER JOIN 0203_goods AS g  ON g.goods_id = sc.goods_id WHERE sc.buyer_id =? AND shopping_cart_status=2 AND goods_picture_url_status=2 AND goods_status>2 ORDER BY shopping_cart_update_time DESC LIMIT 0,2" ; 
					
				
				
			PreparedStatement pstmt=BaseDBUtils.getPreparedStatement(conn,sql);
			ResultSet rs=BaseDBUtils.executeQuery(pstmt,buyer_id);
			while(rs.next()) {
				Map map=new HashMap();
				String shopping_cart_id=rs.getString("shopping_cart_id");
				String goods_picture_url=rs.getString("goods_picture_url");
				String goods_name=rs.getString("goods_name");
				String goods_price=rs.getString("goods_price");
				String goods_presentation=rs.getString("goods_presentation");
				
				map.put("shopping_cart_id",shopping_cart_id);
				map.put("goods_picture_url",goods_picture_url);
				map.put("goods_name",goods_name);
				map.put("goods_price",goods_price);
				map.put("goods_presentation",goods_presentation);
				list.add(map);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	
	
	
	/**
	 * 《肖越根据前台页面需求额外新添加的方法 5》
	 * 大家如果有需要就调用就好了
	 * 此方法根据买家id和当前页码获取买家购物车的所有数据 (购物车商品图片、重量、长宽高...等信息)
	 * 每页显示五条数据
	 * @param buyer_id 买家id
	 * @return 买家购物车的所有数据
	 * @throws SQLException
	 */
	@Override
	public List<Map> selectAllBuyerShoppingCart(String buyer_id,String pages) throws SQLException {
		List<Map> list=new ArrayList<Map>();
		try {
			Connection conn=BaseDBUtils.getConnection();
			String sql="SELECT shopping_cart_id,amount_of_goods,goods_picture_url,goods_name,goods_presentation,goods_width,goods_height,goods_length,goods_weight,goods_price,goods_brand FROM 020301_goods_picture_url AS gpu INNER JOIN 0108_shopping_cart AS sc  ON gpu.goods_id=sc.goods_id INNER JOIN 0203_goods AS g  ON g.goods_id = sc.goods_id WHERE sc.buyer_id = ? AND shopping_cart_status=2 AND goods_picture_url_status=2 AND goods_status>2  ORDER BY shopping_cart_update_time DESC LIMIT ?,5 "; 
					
				int dqym=0;
				int page=Integer.parseInt(pages);
				dqym=(page-1)*5;
			PreparedStatement pstmt=BaseDBUtils.getPreparedStatement(conn,sql);
			ResultSet rs=BaseDBUtils.executeQuery(pstmt,buyer_id,dqym);
			while(rs.next()) {
				Map map=new HashMap();
				String shopping_cart_id=rs.getString("shopping_cart_id");
				String amount_of_goods=rs.getString("amount_of_goods");
				String goods_picture_url=rs.getString("goods_picture_url");
				String goods_name=rs.getString("goods_name");
				String goods_presentation=rs.getString("goods_presentation");
				String goods_width=rs.getString("goods_width");
				String goods_height=rs.getString("goods_height");
				String goods_length=rs.getString("goods_length");
				String goods_weight=rs.getString("goods_weight");
				String goods_price=rs.getString("goods_price");
				String goods_brand=rs.getString("goods_brand");
				
				map.put("shopping_cart_id",shopping_cart_id);
				map.put("amount_of_goods",amount_of_goods);
				map.put("goods_picture_url",goods_picture_url);
				map.put("goods_name",goods_name);
				map.put("goods_presentation",goods_presentation);
				map.put("goods_width",goods_width);
				map.put("goods_height",goods_height);
				map.put("goods_length",goods_length);
				map.put("goods_weight",goods_weight);
				map.put("goods_price",goods_price);
				map.put("goods_brand",goods_brand);
				list.add(map);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	
	/**
	 * 《肖越根据前台页面需求额外新添加的方法 6》
	 * 大家如果有需要就调用就好了
	 * 此方法根据买家id获取买家购物车总条数和总页数
	 * @param buyer_id 买家id
	 * @return  根据买家id获取(shoppingCart)买家购物车总条数 和 总页数(totalPages 按照一页显示五条数据来计算)
	 * @throws SQLException
	 */
	@Override
	public List<Map> selectCountShoppingCart(String buyer_id) throws SQLException {
		List<Map> list=new ArrayList<Map>();
		try {
			Connection conn=BaseDBUtils.getConnection();
			String sql="SELECT COUNT(1) shoppingCart FROM 020301_goods_picture_url AS gpu INNER JOIN 0108_shopping_cart AS sc  ON gpu.goods_id=sc.goods_id  INNER JOIN 0203_goods AS g ON g.goods_id = sc.goods_id WHERE sc.buyer_id = ? AND shopping_cart_status=2 AND goods_picture_url_status=2 AND goods_status>2  "; 
				
				
			PreparedStatement pstmt=BaseDBUtils.getPreparedStatement(conn,sql);
			ResultSet rs=BaseDBUtils.executeQuery(pstmt,buyer_id);
			while(rs.next()) {
				Map map=new HashMap();
				String shoppingCart=rs.getString("shoppingCart");
				int totalPages=Integer.parseInt(shoppingCart);
				if(totalPages%5==0) {
					totalPages=totalPages/5;
				}else {
					totalPages=totalPages/5+1;	
				}
				
				
				
				map.put("shoppingCart",shoppingCart);
				map.put("totalPages",totalPages);
				list.add(map);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 《肖越根据前台页面需求额外新添加的方法 7》
	 * 大家如果有需要就调用就好了
	 * 根据传过来的买家id和商品名称（支持模糊商品名称）查询购物车中相对应的商品
	 * @param buyer_id 买家id
	 * @param name 商品名
	 * @return 根据传过来的买家id和商品名称（支持模糊商品名称）查询购物车中相对应的商品
	 * @throws SQLException
	 */
	@Override
	public List<Map> selectDimBuyerShoppingCart(String buyer_id, String name) throws SQLException {
		List<Map> list=new ArrayList<Map>();
		try {
			Connection conn=BaseDBUtils.getConnection();
			String sql="SELECT shopping_cart_id,amount_of_goods,goods_picture_url,goods_name,goods_presentation,goods_width,goods_height,goods_length,goods_weight,goods_price,goods_brand FROM 020301_goods_picture_url AS gpu INNER JOIN 0108_shopping_cart AS sc  ON gpu.goods_id=sc.goods_id INNER JOIN 0203_goods AS g  ON g.goods_id = sc.goods_id WHERE sc.buyer_id = ? AND goods_name LIKE ? AND shopping_cart_status=2 AND goods_picture_url_status=2 AND goods_status>2  ORDER BY shopping_cart_update_time DESC LIMIT 0,5 "; 
				
			PreparedStatement pstmt=BaseDBUtils.getPreparedStatement(conn,sql);
			ResultSet rs=BaseDBUtils.executeQuery(pstmt,buyer_id,"%"+name+"%");
			while(rs.next()) {
				Map map=new HashMap();
				String shopping_cart_id=rs.getString("shopping_cart_id");
				String amount_of_goods=rs.getString("amount_of_goods");
				String goods_picture_url=rs.getString("goods_picture_url");
				String goods_name=rs.getString("goods_name");
				String goods_presentation=rs.getString("goods_presentation");
				String goods_width=rs.getString("goods_width");
				String goods_height=rs.getString("goods_height");
				String goods_length=rs.getString("goods_length");
				String goods_weight=rs.getString("goods_weight");
				String goods_price=rs.getString("goods_price");
				String goods_brand=rs.getString("goods_brand");
				
				map.put("shopping_cart_id",shopping_cart_id);
				map.put("amount_of_goods",amount_of_goods);
				map.put("goods_picture_url",goods_picture_url);
				map.put("goods_name",goods_name);
				map.put("goods_presentation",goods_presentation);
				map.put("goods_width",goods_width);
				map.put("goods_height",goods_height);
				map.put("goods_length",goods_length);
				map.put("goods_weight",goods_weight);
				map.put("goods_price",goods_price);
				map.put("goods_brand",goods_brand);
				list.add(map);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	
	/**
	 * 《肖越根据前台页面需求额外新添加的方法 8》
	 * 根据买家id和购物车id删除对应的购物车商品
	 * @param buyer_id 买家id
	 * @param shopping_scart_id  购物车id
	 * @return
	 * @throws SQLException
	 */
	@Override
	public int deleteShoppingCartByBuyerIdAndShoppingCartId(String buyer_id, String shopping_scart_id)
			throws SQLException {
		int count=0;
		try {
			Connection conn=BaseDBUtils.getConnection();
			String sql="UPDATE 0108_shopping_cart SET shopping_cart_status=1 WHERE shopping_cart_id=? AND buyer_id=?";
			PreparedStatement pstmt=BaseDBUtils.getPreparedStatement(conn,sql);
			count=BaseDBUtils.executeUpdate(pstmt,shopping_scart_id,buyer_id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	
	
	
	/**
	 * 《肖越根据前台页面需求额外新添加的方法 9》
	 * @param goods_id 商品id
	 * @return 根据商品id返回该商品所对应的详细信息
	 * @throws SQLException
	 */
	@Override
	public List<Map> selectProductDetailsByGoodsId(String goods_id) throws SQLException {
		List<Map> list=new ArrayList<Map>();
		try {
			Connection conn=BaseDBUtils.getConnection();
			String sql="SELECT goods_name,goods_price,goods_brand,goods_type,goods_width,goods_height,goods_length,goods_presentation,goods_weight,goods_picture_url,store_about,store_img,store_name,goods_uuid FROM 0203_goods g INNER JOIN 020301_goods_picture_url gpu ON g.goods_id=gpu.goods_id INNER JOIN 0207_store s ON s.store_id=g.seller_id WHERE g.goods_id=? AND goods_status>2 AND goods_picture_url_status=2 AND store_status=2"; 
				
			PreparedStatement pstmt=BaseDBUtils.getPreparedStatement(conn,sql);
			ResultSet rs=BaseDBUtils.executeQuery(pstmt,goods_id);
			while(rs.next()) {
				Map map=new HashMap();
				String goods_name=rs.getString("goods_name");
				String goods_price=rs.getString("goods_price");
				String goods_brand=rs.getString("goods_brand");
				String goods_type=rs.getString("goods_type");
				String goods_width=rs.getString("goods_width");
				String goods_height=rs.getString("goods_height");
				String goods_length=rs.getString("goods_length");
				String goods_presentation=rs.getString("goods_presentation");
				String goods_weight=rs.getString("goods_weight");
				String goods_picture_url=rs.getString("goods_picture_url");
				String store_about=rs.getString("store_about");
				String store_img=rs.getString("store_img");
				String store_name=rs.getString("store_name");
				String goods_uuid=rs.getString("goods_uuid");
				
				map.put("goods_name",goods_name);
				map.put("goods_price",goods_price);
				map.put("goods_brand",goods_brand);
				map.put("goods_type",goods_type);
				map.put("goods_width",goods_width);
				map.put("goods_height",goods_height);
				map.put("goods_length",goods_length);
				map.put("goods_presentation",goods_presentation);
				map.put("goods_weight",goods_weight);
				map.put("goods_picture_url",goods_picture_url);
				map.put("store_about",store_about);
				map.put("store_img",store_img);
				map.put("store_name",store_name);
				map.put("goods_uuid",goods_uuid);
				
				list.add(map);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	
	/**
	 * 《肖越根据前台页面需求额外新添加的方法 10》
	 * @param goods_name 商品名称
	 * @return 根据商品名称（支持模糊查询），查询所对应的商品信息(只要第一条)
	 * @throws SQLException
	 */
	@Override
	public List<Map> selectDimDetailsByGoodsName(String goods_Name) throws SQLException {
		List<Map> list=new ArrayList<Map>();
		try {
			Connection conn=BaseDBUtils.getConnection();
			String sql="SELECT goods_name,goods_price,goods_brand,goods_type,goods_width,goods_height,goods_length,goods_presentation,goods_weight,goods_picture_url,store_about,store_img,store_name,goods_uuid FROM 0203_goods g INNER JOIN 020301_goods_picture_url gpu ON g.goods_id=gpu.goods_id INNER JOIN 0207_store s ON s.store_id=g.seller_id WHERE goods_name LIKE ? AND goods_status>2 AND goods_picture_url_status=2 AND store_status=2 LIMIT 0,1"; 
				
				
			PreparedStatement pstmt=BaseDBUtils.getPreparedStatement(conn,sql);
			ResultSet rs=BaseDBUtils.executeQuery(pstmt,"%"+goods_Name+"%");
			while(rs.next()) {
				Map map=new HashMap();
				String goods_name=rs.getString("goods_name");
				String goods_price=rs.getString("goods_price");
				String goods_brand=rs.getString("goods_brand");
				String goods_type=rs.getString("goods_type");
				String goods_width=rs.getString("goods_width");
				String goods_height=rs.getString("goods_height");
				String goods_length=rs.getString("goods_length");
				String goods_presentation=rs.getString("goods_presentation");
				String goods_weight=rs.getString("goods_weight");
				String goods_picture_url=rs.getString("goods_picture_url");
				String store_about=rs.getString("store_about");
				String store_img=rs.getString("store_img");
				String store_name=rs.getString("store_name");
				String goods_uuid=rs.getString("goods_uuid");
				
				map.put("goods_name",goods_name);
				map.put("goods_price",goods_price);
				map.put("goods_brand",goods_brand);
				map.put("goods_type",goods_type);
				map.put("goods_width",goods_width);
				map.put("goods_height",goods_height);
				map.put("goods_length",goods_length);
				map.put("goods_presentation",goods_presentation);
				map.put("goods_weight",goods_weight);
				map.put("goods_picture_url",goods_picture_url);
				map.put("store_about",store_about);
				map.put("store_img",store_img);
				map.put("store_name",store_name);
				map.put("goods_uuid",goods_uuid);
				
				list.add(map);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	
	
	
	
	
	
	
	//测试
//	public static void main(String[] args) {
//		try {
//			List<Map> list=new RecordDaoImpl().selectDimDetailsByGoodsName("夏");
//			for (int i = 0; i < list.size(); i++) {
//				Map map=list.get(i);
//				System.out.println(map);
//			}
////			if(i>0) {
////				System.out.println("添加数据成功！！");
////			}
//			
////			int i=new RecordDaoImpl().deleteShoppingCartByBuyerIdAndShoppingCartId("1", "2");
////			System.out.println(i);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			System.out.println("出现异常！！！");
//		}
//	}

	
	
	
}
