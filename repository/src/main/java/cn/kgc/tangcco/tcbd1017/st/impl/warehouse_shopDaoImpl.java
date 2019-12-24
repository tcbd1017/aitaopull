package cn.kgc.tangcco.tcbd1017.st.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.lihaozhe.commons.bianhao.BaseBianHao;
import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.lihaozhe.commons.jdbc.PageRang;
import cn.kgc.tangcco.tcbd1017.st.warehouse_shopDao;
import cn.kgc.tangcco.tcbd1017.st.pojo.BingZhuangTuDuiXiang;
import cn.kgc.tangcco.tcbd1017.st.pojo.WarehouseShop;

public class warehouse_shopDaoImpl implements warehouse_shopDao {

	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	/**
	 * 查询仓库与店铺的关联表 条件 按商家 查询 按购买年月 查询
	* 
	 * 查看warehouse_shop 的详情 包括商家简略信息 （商家uuid 商家） 以表格显示到页面上
	* 
	* @param map
	* @return
	*/
	@Override
	public List<Map<String, Object>> selectCnagKuYuDianPuGuanLianBiao(Map<String, Object> map) {
		StringBuilder sql = new StringBuilder(
				"select shop_uuid,warehouse_name,shop_name,warehouse_shop_buytime,warehouse_shop_buyyear,warehouse_shop_daoqitime,warehouse_shop_warehouseuuid,warehouse_shop_warehousesize");
		sql.append(
				" from warehouse_shop as w INNER JOIN warehouse as e  on w. warehouse_shop_warehouse_id = e.warehouse_id ");
		sql.append(" INNER JOIN shop as s on w.warehouse_shop_shop_id = s.shop_id ");
		// 存储参数
		List list = new ArrayList();
		// 动态SQL开始
		// 按商家 查询
		if (map.containsKey("shop_uuid") && map.get("shop_uuid") != null) {
			sql.append(" and s.shop_uuid = ? ");
			list.add(map.get("shop_uuid"));
		}
		if (map.containsKey("warehouse_shop_warehouse_id")&& map.get("warehouse_shop_warehouse_id")!= null) {
			sql.append(" and w.warehouse_shop_warehouse_id = ? ");
			list.add(map.get("warehouse_shop_warehouse_id"));
		}
		// 按购买年月 查询
			if (map.containsKey("month")&& map.get("month")!= null) {
				sql.append(" and MONTH(warehouse_shop_buytime) = ? and YEAR(warehouse_shop_buytime) = '2019' ");
				list.add(map.get("month"));
			}
		Object[] param = list.toArray();
		List<Map<String, Object>> list2 =null;
		try {
			conn = BaseDBUtils.getConnection();
			pst = BaseDBUtils.getPreparedStatement(conn, sql.toString(),(PageRang)map.get("PageRang"));
			rs = BaseDBUtils.executeQuery(pst, param);
			if (rs.first()) {
				rs.previous();
				list2 = new ArrayList<Map<String, Object>>();
				while (rs.next()) {
					Map<String, Object> map3 = new HashMap<String, Object>();
					map3.put("shop_uuid", rs.getObject("shop_uuid"));
					map3.put("warehouse_name", rs.getObject("warehouse_name"));
					map3.put("shop_name", rs.getObject("shop_name"));
					map3.put("warehouse_shop_buytime", rs.getObject("warehouse_shop_buytime"));
					map3.put("warehouse_shop_buyyear", rs.getObject("warehouse_shop_buyyear"));
					map3.put("warehouse_shop_daoqitime", rs.getObject("warehouse_shop_daoqitime"));
					map3.put("warehouse_shop_warehouseuuid", rs.getObject("warehouse_shop_warehouseuuid"));
					map3.put("warehouse_shop_warehousesize", rs.getObject("warehouse_shop_warehousesize"));
					list2.add(map3);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list2;
	}

	/**
	 * 查 看 详 情 管理员对以卖仓库查询详情 warehouse_shop 与 shop 与 warehouse
	 * 
	 * @param map 参数 1 按照类别查询 2 按时间段查询 3按商铺uuid查询
	 * @return
	 */
	@Override
	public Map<String, Object> chakanxiangqing(Map<String, Object> map) {
		StringBuilder sql = new StringBuilder(
				"select w.warehouse_shop_warehouseuuid,sh.shop_uuid,h.warehouse_name,shop_name,w.warehouse_shop_buytime,w.warehouse_shop_buyyear,w.warehouse_shop_daoqitime,w.warehouse_shop_warehousesize,");
		sql.append(" warehouse_typesize,h.warehouse_address,warehouse_price from warehouse_shop as w ");
		sql.append(" INNER JOIN warehouse as h ");
		sql.append(" on w.warehouse_shop_warehouse_id = h.warehouse_id ");
		sql.append(" INNER JOIN shop as sh  ");
		sql.append(" on w.warehouse_shop_shop_id = sh.shop_id ");
		// 存储参数
		List list = new ArrayList();
		// 1 按照类别查询 warehouse_typesize是否存在 warehouse_typesize的值是否存在
		if (map.containsKey("warehouse_id") && map.get("warehouse_id") != null) {
			sql.append(" and h.warehouse_id = ? ");
			list.add(map.get("warehouse_id"));
		}
		// 2 按时间段查询

		// 3按商铺uuid查询
		if (map.containsKey("warehouse_shop_warehouseuuid") && map.get("warehouse_shop_warehouseuuid") != null) {
			sql.append(" and w.warehouse_shop_warehouseuuid = ?");
			list.add(map.get("warehouse_shop_warehouseuuid"));
		}
		// 把集合转为数组
		Object[] param = list.toArray();
		// 声明map为空
		Map<String, Object> map2 = null;
		try {
			conn = BaseDBUtils.getConnection();
			pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
			rs = BaseDBUtils.executeQuery(pst, param);
			// 判断第一位是否有值
			if (rs.first()) {
				// 指针往上移一位
				rs.previous();
				map2 = new HashMap<String, Object>();
				// 把map放集合里
				List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
				while (rs.next()) {
					Map<String, Object> map3 = new HashMap<String, Object>();
					map3.put("warehouse_shop_warehouseuuid", rs.getObject("warehouse_shop_warehouseuuid"));
					map3.put("shop_uuid", rs.getObject("shop_uuid"));
					map3.put("warehouse_name", rs.getObject("warehouse_name"));
					map3.put("shop_name", rs.getObject("shop_name"));
					map3.put("warehouse_shop_buytime", rs.getObject("warehouse_shop_buytime"));
					map3.put("warehouse_shop_buyyear", rs.getObject("warehouse_shop_buyyear"));
					map3.put("warehouse_shop_daoqitime", rs.getObject("warehouse_shop_daoqitime"));
					map3.put("warehouse_shop_warehousesize", rs.getObject("warehouse_shop_warehousesize"));
					map3.put("warehouse_typesize", rs.getObject("warehouse_typesize"));
					map3.put("warehouse_address", rs.getObject("warehouse_address"));
					map3.put("warehouse_price", rs.getObject("warehouse_price"));
					list2.add(map3);
				}
				map2.put("chakanxiangqing", list2);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map2;
	}

	/**
	 * 查询所有仓库 uuid
	 * 
	 * @param map
	 * @return
	 */
	@Override
	public List<WarehouseShop> chakanxiangqingAllCangKu(Map<String,Object>map) {
		StringBuilder sql = new StringBuilder();
		List<WarehouseShop>list=new ArrayList<>();
		List lista=new ArrayList();
		WarehouseShop aa=null;
		sql.append(" SELECT * FROM warehouse_shop where 1 =1 ");
		if (map.containsKey("warehouse_shop_shop_id") && map.get("warehouse_shop_shop_id") != null) {
			sql.append(" and warehouse_shop_shop_id = ? ");
			lista.add( map.get("warehouse_shop_shop_id"));
		}
		Object[] array = lista.toArray();
		try {
			Connection connection = BaseDBUtils.getConnection();
			PreparedStatement preparedStatement = BaseDBUtils.getPreparedStatement(connection, sql.toString()
					);
			ResultSet executeQuery = BaseDBUtils.executeQuery(preparedStatement,array);
			while (executeQuery.next()) {
				aa=new WarehouseShop();
				
				aa.setWarehouseShopWarehouseuuid(executeQuery.getString("warehouse_shop_warehouseuuid"));
				aa.setWarehouseShopBuytime(executeQuery.getDate("warehouse_shop_buytime"));
				aa.setWarehouseShopDaoqitime(executeQuery.getDate("warehouse_shop_daoqitime"));
				aa.setWarehouseShopWarehousesize(executeQuery.getInt("warehouse_shop_warehousesize"));
				aa.setWarehouseShopBuyyear(executeQuery.getInt("warehouse_shop_buyyear"));
				aa.setWarehouseShopId(executeQuery.getInt("warehouse_shop_id"));
				aa.setWarehouseShopWarehouseuuid(executeQuery.getString("warehouse_shop_warehouseuuid"));
				list.add(aa);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 商家查看自家仓库剩余容量等详细信息 条件
	 * 
	 * @param map 参数 商铺uuid 这个参数是从session中获取的
	 * @return
	 */
	@Override
	public Map<String, Object> selectCangKuGenjuShangJiao(Map<String, Object> map) {
		StringBuilder sql = new StringBuilder(
				"select w.warehouse_shop_warehouseuuid,sh.shop_uuid,h.warehouse_name,shop_name,w.warehouse_shop_buytime,w.warehouse_shop_buyyear,w.warehouse_shop_daoqitime,w.warehouse_shop_warehousesize,");
		sql.append(" warehouse_typesize,h.warehouse_address,warehouse_price from warehouse_shop as w ");
		sql.append(" INNER JOIN warehouse as h ");
		sql.append(" on w.warehouse_shop_warehouse_id = h.warehouse_id ");
		sql.append(" INNER JOIN shop as sh  ");
		sql.append(" on w.warehouse_shop_shop_id = sh.shop_id ");
		// 存储参数
		List list = new ArrayList();
		// 3按商铺uuid查询
		if (map.containsKey("shop_uuid") && map.get("shop_uuid") != null) {
			sql.append(" and sh.shop_uuid = ?");
			list.add(map.get("shop_uuid"));
		}
		// 把集合转为数组
		Object[] param = list.toArray();
		// 声明map为空
		Map<String, Object> map2 = null;
		try {
			conn = BaseDBUtils.getConnection();
			pst = BaseDBUtils.getPreparedStatement(conn, sql.toString(), (PageRang) map.get("PageRang"));
			rs = BaseDBUtils.executeQuery(pst, param);
			// 判断第一位是否有值
			if (rs.first()) {
				// 指针往上移一位
				rs.previous();
				map2 = new HashMap<String, Object>();
				// 把map放集合里
				List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
				while (rs.next()) {
					Map<String, Object> map3 = new HashMap<String, Object>();
					map3.put("warehouse_shop_warehouseuuid", rs.getObject("warehouse_shop_warehouseuuid"));
					map3.put("shop_uuid", rs.getObject("shop_uuid"));
					map3.put("warehouse_name", rs.getObject("warehouse_name"));
					map3.put("shop_name", rs.getObject("shop_name"));
					map3.put("warehouse_shop_buytime", rs.getObject("warehouse_shop_buytime"));
					map3.put("warehouse_shop_buyyear", rs.getObject("warehouse_shop_buyyear"));
					map3.put("warehouse_shop_daoqitime", rs.getObject("warehouse_shop_daoqitime"));
					map3.put("warehouse_shop_warehousesize", rs.getObject("warehouse_shop_warehousesize"));
					map3.put("warehouse_typesize", rs.getObject("warehouse_typesize"));
					map3.put("warehouse_address", rs.getObject("warehouse_address"));
					map3.put("warehouse_price", rs.getObject("warehouse_price"));
					list2.add(map3);
				}
				map2.put("chakanxiangqing", list2);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map2;
	}

	/**
	 * chuku warehouse_shop 商家查看自家仓库剩余容量 条件 1 按仓库uuid
	 * 
	 * @param map 参数 商铺uuid 这个参数是从session中获取的
	 * @return
	 */
	@Override
	public Map<String, Object> selectShengYuRongLiang(Map<String, Object> map) {
		StringBuilder sql = new StringBuilder("select w.warehouse_shop_warehousesize from warehouse_shop as w");
		sql.append(" INNER JOIN warehouse as h ");
		sql.append(" on w.warehouse_shop_warehouse_id = h.warehouse_id ");
		sql.append(" INNER JOIN shop as sh  ");
		sql.append(" on w.warehouse_shop_shop_id = sh.shop_id ");
		// 存储参数
		List list = new ArrayList();
		// 3按商铺uuid查询
		if (map.containsKey("warehouse_shop_warehouseuuid") && map.get("warehouse_shop_warehouseuuid") != null) {
			sql.append(" and w.warehouse_shop_warehouseuuid = ?");
			list.add(map.get("warehouse_shop_warehouseuuid"));
		}
		// 把集合转为数组
		Object[] param = list.toArray();
		// 声明map为空
		Map<String, Object> map2 = null;
		try {
			conn = BaseDBUtils.getConnection();
			pst = BaseDBUtils.getPreparedStatement(conn, sql.toString(), (PageRang) map.get("PageRang"));
			rs = BaseDBUtils.executeQuery(pst, param);
			// 判断第一位是否有值
			if (rs.first()) {
				// 指针往上移一位
				rs.previous();
				map2 = new HashMap<String, Object>();
				// 把map放集合里
				List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
				while (rs.next()) {
					Map<String, Object> map3 = new HashMap<String, Object>();
					map3.put("warehouse_shop_warehousesize", rs.getObject("warehouse_shop_warehousesize"));
					list2.add(map3);
				}
				map2.put("chakanxiangqing", list2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map2;
	}

	/**
	 * chuku warehouse_shop 查看仓库剩余时间 条件
	 * 
	 * @param map 仓库uuid
	 * @return
	 */
	@Override
	public Map<String, Object> selectShengYuTime(Map<String, Object> map) {
		StringBuilder sql = new StringBuilder(
				"select DATEDIFF(warehouse_shop_daoqitime,CURDATE()) as date from warehouse_shop where 1 = 1 ");
		// 存储参数
		List list = new ArrayList();
		// 3按商铺uuid查询
		if (map.containsKey("warehouse_shop_warehouseuuid") && map.get("warehouse_shop_warehouseuuid") != null) {
			sql.append(" and warehouse_shop_warehouseuuid = ?");
			list.add(map.get("warehouse_shop_warehouseuuid"));
		}
		// 把集合转为数组
		Object[] param = list.toArray();
		// 声明map为空
		Map<String, Object> map2 = null;
		try {
			conn = BaseDBUtils.getConnection();
			pst = BaseDBUtils.getPreparedStatement(conn, sql.toString(), (PageRang) map.get("PageRang"));
			rs = BaseDBUtils.executeQuery(pst, param);
			// 判断第一位是否有值
			if (rs.first()) {
				// 指针往上移一位
				rs.previous();
				map2 = new HashMap<String, Object>();
				// 把map放集合里
				List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
				while (rs.next()) {
					Map<String, Object> map3 = new HashMap<String, Object>();
					map3.put("date", rs.getObject("date"));
					list2.add(map3);
				}
				map2.put("chakanxiangqing", list2);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map2;
	}

	/**
	 * 购买仓库 插入
	 * 
	 * @param map warehouse_shop 这张表的信息 全是从页面上获取的 包括商铺 id 仓库类型 购买时间，时长，到期时间是计算出的
	 * @return
	 */
	@Override
	public Map<String, Object> goumaiCangKu(Map<String, Object> map) {
		StringBuilder sql = new StringBuilder();

		String generateUniqueKey = BaseBianHao.generateUniqueKey();

		sql.append(
				" insert into warehouse_shop(warehouse_shop_warehouse_id,warehouse_shop_shop_id,warehouse_shop_buytime,warehouse_shop_buyyear,warehouse_shop_daoqitime,warehouse_shop_warehouseuuid,warehouse_shop_warehousesize)");
		sql.append(" VALUES (?,?,CURDATE(),?,ADDDATE(CURDATE(),?*365),?,? )");
		Object[] param = { map.get("warehouse_shop_warehouse_id"), map.get("warehouse_shop_shop_id"),
				map.get("warehouse_shop_buyyear"), map.get("warehouse_shop_buyyear"), generateUniqueKey,
				map.get("warehouse_shop_warehousesize") };
		Map<String, Object> map2 = null;
		try {
			conn = BaseDBUtils.getConnection();
			pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
			int i = BaseDBUtils.executeUpdate(pst, param);
			if (i > 0) {
				map2 = new HashMap<String, Object>();
				map2.put("i", i);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map2;
	}

	/**
	  * 查询 仓库记录表 类型 所购买数量 首页 饼状图
	  * 
	  * @param map 类别
	  * @return
	  */
	 @Override
	 public List<BingZhuangTuDuiXiang> YiFenLeiChaXunYiMaiCnagKuGeShu(Map<String, Object> map) {
	  // 仓库以卖总数量
	  StringBuilder sql = new StringBuilder(
	    " SELECT warehouse.`warehouse_name` name,COUNT(warehouse.`warehouse_name`) count FROM warehouse_shop,warehouse ");
	  sql.append(" WHERE warehouse_shop.`warehouse_shop_warehouse_id`=warehouse.`warehouse_id` GROUP BY warehouse.`warehouse_name`");
	  
	  List<BingZhuangTuDuiXiang> list=null;
	  try {
	   conn = BaseDBUtils.getConnection();
	   pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
	   rs = BaseDBUtils.executeQuery(pst);
	   if (rs.first()) {
	    rs.previous();
	    list = new ArrayList<BingZhuangTuDuiXiang>();
	    while (rs.next()) {
	     list.add(new BingZhuangTuDuiXiang(rs.getString("name"), rs.getInt("count")));
	    }
	   }
	  } catch (SQLException e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	  }
	  return list;
	 }

	/**
	 * 查询不同类别未卖仓库个数     warehouse 和 waerhouseresour
	 * 
	 * @param map
	 * @return
	 */
	@Override
	public Map<String, Object> YiFenLeiChaXunShuYuCangKuDeShuLiang(Map<String, Object> map) {
		StringBuilder sql = new StringBuilder(
				"select warehouse_name,warehouseresour_count from warehouseresour INNER JOIN warehouse on warehouseresour_id = warehouse_id");
		Map<String, Object> map2 = null;
		try {
			conn = BaseDBUtils.getConnection();
			pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
			rs = BaseDBUtils.executeQuery(pst);
			if (rs.first()) {
				rs.previous();
				map2 = new HashMap<String, Object>();
				List<Map<String, Object>> list2 = new ArrayList<Map<String, Object>>();
				while (rs.next()) {
					Map<String, Object> map3 = new HashMap<String, Object>();
					map3.put("warehouse_name", rs.getObject("warehouse_name"));
					map3.put("warehouseresour_count", rs.getObject("warehouseresour_count"));
					list2.add(map3);
				}
				map2.put("YiFenLeiChaXunShuYuCangKuDeShuLiang", list2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map2;
	}

	/**
	 * 查询所有以卖仓库个数 无条件
	 * 
	 * @param map
	 * @return
	 */
	@Override
	public Map<String, Object> Count(Map<String, Object> map) {
		StringBuilder sql = new StringBuilder(
				"select count(warehouse_shop_shop_id) as aaa from warehouse_shop where 1 = 1");
		Map<String, Object> map2 = new HashMap<String, Object>();
		try {
			conn = BaseDBUtils.getConnection();
			pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
			rs = BaseDBUtils.executeQuery(pst);
			if (rs.next()) {
				int i = rs.getInt("aaa");
				map2 = new HashMap<String, Object>();
				map2.put("i", i);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map2;
	}

	/**
	 * 查询 不同月份以卖仓库个数 一时间顺寻拍 sql语句--》 以时间为条件 在把条数整合成个数
	 * 
	 * @param map
	 * @return
	 */
	@Override
	// 根据月份查询本月卖出仓库数s6

	public List<Map<String, Object>> YiYueFenChaXunYiMaiCnagKuGeShu(Map<String, Object> map) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				" select month(warehouse_shop_buytime) as yue,count(1) as count from warehouse_shop  group by month(warehouse_shop_buytime)");
		List<Map<String, Object>> mList = null;
		try {
			Connection conn = BaseDBUtils.getConnection();
			PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
			ResultSet rs = BaseDBUtils.executeQuery(pst);
			if (rs.first()) {
				rs.previous();
				mList = new ArrayList<Map<String, Object>>();
				while (rs.next()) {
					Map<String, Object> map2 = new HashMap<String, Object>();
					map2.put("yue", rs.getInt("yue"));
					map2.put("count", rs.getInt("count"));
					mList.add(map2);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mList;
	}

	/**
	 * 对月份做统计 一时间顺寻拍
	 * 
	 * @param map
	 * @return
	 */
	@Override
	public Map<String, Object> ChaXunYueFen(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 修改仓库容量         减少  入库所用
	 */
	@Override
	public int updateJianCangKuRongLiang(Map<String, Object> map) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				" UPDATE `warehouse_shop` SET `warehouse_shop_warehousesize` = warehouse_shop_warehousesize - ? WHERE `warehouse_shop_warehouseuuid`= ?");
		Object[] param = { map.get("goods_count"), map.get("goods_w_s_uuid") };
		int rs = -1;
		try {
			Connection conn = BaseDBUtils.getConnection();
			PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
			rs = BaseDBUtils.executeUpdate(pst, param);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return rs;

	}

	@Override
	public int updateaddCangKuRongLiang(Map<String, Object> map) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				" UPDATE `warehouse_shop` SET `warehouse_shop_warehousesize` = warehouse_shop_warehousesize + ? WHERE `warehouse_shop_warehouseuuid`= ?");
		Object[] param = { map.get("goods_count"), map.get("goods_w_s_uuid") };
		int rs = -1;
		try {
			Connection conn = BaseDBUtils.getConnection();
			PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
			rs = BaseDBUtils.executeUpdate(pst, param);

		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return rs;

	}
	
	@Override
	public int ChaXunShengYuRongLiang(String uuid) {
		int aa = 0;
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT warehouse_shop_warehousesize FROM warehouse_shop WHERE warehouse_shop_warehouseuuid=? ");
		Object[] array = { uuid };
		try {
			Connection connection = BaseDBUtils.getConnection();
			PreparedStatement preparedStatement = BaseDBUtils.getPreparedStatement(connection, sql.toString());
			ResultSet executeQuery = BaseDBUtils.executeQuery(preparedStatement, array);
			while (executeQuery.next()) {
				aa = executeQuery.getInt("warehouse_shop_warehousesize");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return aa;
	}

	@Override
	public Date ChaXunDaoQiTime(String uuid) {
		Date aa = null;
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT warehouse_shop_daoqitime FROM warehouse_shop WHERE warehouse_shop_warehouseuuid=? ");
		Object[] array = { uuid };
		try {
			Connection connection = BaseDBUtils.getConnection();
			PreparedStatement preparedStatement = BaseDBUtils.getPreparedStatement(connection, sql.toString());
			ResultSet executeQuery = BaseDBUtils.executeQuery(preparedStatement, array);
			while (executeQuery.next()) {
				aa = executeQuery.getDate("warehouse_shop_daoqitime");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return aa;
	}

}
