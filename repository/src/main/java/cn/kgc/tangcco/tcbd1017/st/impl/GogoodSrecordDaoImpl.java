package cn.kgc.tangcco.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import cn.hutool.core.date.DateUtil;
import cn.kgc.tangcco.dao.GogoodSrecordDao;
import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.lihaozhe.commons.jdbc.PageRang;
import cn.kgc.tangcco.pojo.Brand;
import cn.kgc.tangcco.pojo.Chuku;
import cn.kgc.tangcco.pojo.Emp;
import cn.kgc.tangcco.pojo.Gogoodsrecord;
import cn.kgc.tangcco.pojo.Goods;
import cn.kgc.tangcco.pojo.Jiance;
import cn.kgc.tangcco.pojo.Model;
import cn.kgc.tangcco.pojo.RecordMonthAndSum;
import cn.kgc.tangcco.pojo.Role;
import cn.kgc.tangcco.pojo.SelectCountByType;
import cn.kgc.tangcco.pojo.Shop;
import cn.kgc.tangcco.pojo.Type;

/**
 * @author 作者 :牛伟
 * @version 创建时间：2019年12月15日 上午11:01:36 类说明
 */
public class GogoodSrecordDaoImpl implements GogoodSrecordDao {

	@Override
	public Map<String, Object> selectChuKuJiLu(Map<String, Object> map) throws SQLException {
		Map<String, Object> map2 = new HashMap<String, Object>();
		StringBuilder sql = new StringBuilder(
				" select distinct gg.gogoodsrecord_id,gg.gogoodsrecord_time,gg.gogoodsrecord_count,gg.gogoodsrecord_w_s_uuid,gg.gogoodsrecord_uuid, ");
		sql.append(" g.goods_id,g.goods_count,g.goods_flag, ");
		sql.append(" t.type_id,t.type_name, ");
		sql.append(" b.brand_id,b.brand_name, ");
		sql.append(" m.model_id,m.model_name,m.model_price,m.model_size, ");
		sql.append(" s.shop_id,s.shop_name,s.shop_uuid,s.shop_address,s.shop_account,s.shop_password,s.shop_phone, ");
		sql.append(" e.emp_id,e.emp_account,e.emp_password,e.emp_name,e.emp_phone,e.emp_gender,e.emp_flag, ");
		sql.append(" r.role_id,r.role_name ");
		sql.append(" from gogoodsrecord as gg ");
		sql.append(" inner join emp as e ");
		sql.append(" inner join shop as s ");
		sql.append(" inner join goods as g ");
		sql.append(" inner join warehouse_shop as ws ");
		sql.append(" inner join warehouse as w  ");
		sql.append(" inner join type as t ");
		sql.append(" inner join brand as b ");
		sql.append(" inner join model as m ");
		sql.append(" inner join role as r ");
		sql.append(" on gg.gogoodsrecord_emp_id= e.emp_id ");
		sql.append(" and gg.gogoodsrecord_shop_id = s.shop_id ");
		sql.append(" and gg.gogoodsrecord_goods_id = g.goods_id ");
		sql.append(" and s.shop_id = ws.warehouse_shop_shop_id ");
		sql.append(" and ws.warehouse_shop_warehouse_id = w.warehouse_id ");
		sql.append(" and g.goods_type_id = t.type_id ");
		sql.append(" and g.goods_brand_id = b.brand_id ");
		sql.append(" and g.goods_model_id = m.model_id ");
		sql.append(" and e.emp_role_id = r.role_id ");

		List<String> list = new ArrayList<String>();
		List<Gogoodsrecord> list1 = new ArrayList<Gogoodsrecord>();
		ResultSet rs = null;

		// 按仓库uuid查询 传入map(k:"warehouseuuid",value:"仓库uuid001") 返回map（k:"list"
		// value:list）
		if (map.containsKey("warehouseuuid") && !StringUtils.isEmpty((String) map.get("warehouseuuid"))) {
			sql.append(" and gogoodsrecord_w_s_uuid = ? ");
			list.add((String) map.get("warehouseuuid"));
			sql.append(" ORDER BY gg.gogoodsrecord_time desc ");
			Object[] param = list.toArray();
			Connection conn = BaseDBUtils.getConnection();
			PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString(),
					(PageRang) map.get("pageRang"));
			rs = BaseDBUtils.executeQuery(pst, param);
			// return map2;
		}
		// 按商家uuid查询 传入map(k:"shopuuid",value:"店铺uuid001") 返回map（k:"list" value:list）
		else if (map.containsKey("shopuuid") && !StringUtils.isEmpty((String) map.get("shopuuid"))) {
			sql.append(" and s.shop_uuid = ? ");
			list.add((String) map.get("shopuuid"));
			sql.append(" ORDER BY gg.gogoodsrecord_time desc ");
			Object[] param = list.toArray();
			Connection conn = BaseDBUtils.getConnection();
			PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString(),
					(PageRang) map.get("pageRang"));
			rs = BaseDBUtils.executeQuery(pst, param);
			// return map2;
		}
		// 按出货员查询 传入map(k:"empname",value:“出货员名字”) 返回map（k:"list" value:list）
		else if (map.containsKey("empname") && !StringUtils.isEmpty((String) map.get("empname"))) {
			sql.append(" and e.emp_name = ? ");
			// list.add((String) map.get("empname"));
			sql.append(" ORDER BY gg.gogoodsrecord_time desc ");

			// Object[] param = list.toArray();
			String param = (String) map.get("empname");
			System.out.println(param);
			Connection conn = BaseDBUtils.getConnection();
			PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString(),
					(PageRang) map.get("pageRang"));
			rs = BaseDBUtils.executeQuery(pst, param);
		}

		// 按年月查
		else if (map.containsKey("year") && map.containsKey("month") && !StringUtils.isEmpty((String) map.get("year"))
				&& !StringUtils.isEmpty((String) map.get("month"))) {
			sql.append(" and  Year(gogoodsrecord_time) = ? ");
			sql.append(" and Month(gogoodsrecord_time) = ? ");
			list.add((String) map.get("year"));
			list.add((String) map.get("month"));
			sql.append(" ORDER BY gg.gogoodsrecord_time desc ");
			Object[] param = list.toArray();
			Connection conn = BaseDBUtils.getConnection();
			PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString(),
					(PageRang) map.get("pageRang"));
			rs = BaseDBUtils.executeQuery(pst, param);
		}

		else {

			// 没有查询参数
			sql.append(" ORDER BY gg.gogoodsrecord_time desc ");

			Connection conn = BaseDBUtils.getConnection();
			PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString(),
					(PageRang) map.get("pageRang"));
			rs = BaseDBUtils.executeQuery(pst);
		}

		Gogoodsrecord gogoodsrecord = null;
		Goods goods = null;
		Type type = null;
		Brand brand = null;
		Model model = null;
		Shop shop = null;
		Emp emp = null;
		Role role = null;

		
			// 遍历结果集
			while (rs.next()) {
				gogoodsrecord = new Gogoodsrecord();
				gogoodsrecord.setGogoodsrecordId(rs.getInt("gogoodsrecord_id"));
				gogoodsrecord.setGogoodsrecordTime(rs.getDate("gogoodsrecord_time"));
				gogoodsrecord.setGogoodsrecordCount(rs.getInt("gogoodsrecord_count"));

				String cangkuuuid = rs.getString("gogoodsrecord_w_s_uuid");

				type = new Type();
				type.setTypeId(rs.getInt("type_id"));
				type.setName(rs.getString("type_name"));

				brand = new Brand();
				brand.setBrandId(rs.getInt("brand_id"));
				brand.setBrandName(rs.getString("brand_name"));

				model = new Model();
				model.setModelId(rs.getInt("model_id"));
				model.setModelName(rs.getString("model_name"));
				model.setModelPrice(rs.getDouble("model_price"));
				model.setModelSize(rs.getInt("model_size"));

				shop = new Shop();
				shop.setShopId(rs.getInt("shop_id"));
				shop.setShopName(rs.getString("shop_name"));
				shop.setShopUuid(rs.getString("shop_uuid"));
				shop.setShopAddress(rs.getString("shop_address"));
				shop.setShopAccount(rs.getString("shop_account"));
				shop.setShopPassword(rs.getString("shop_password"));
				shop.setShopPhone(rs.getString("shop_phone"));

				goods = new Goods();
				goods.setGoodsId(rs.getInt("goods_id"));
				goods.setCount(rs.getInt("goods_count"));
				goods.setModelFlag(rs.getInt("goods_flag"));
				goods.setGoodsWSUuid(cangkuuuid);
				goods.setBrand(brand);
				goods.setType(type);
				goods.setModel(model);
				goods.setShop(shop);

				emp = new Emp();
				emp.setEmpId(rs.getInt("emp_id"));
				emp.setEmpAccount(rs.getString("emp_account"));
				emp.setEmpPassword(rs.getString("emp_password"));
				emp.setEmpName(rs.getString("emp_name"));
				emp.setEmpPhone(rs.getString("emp_phone"));
				emp.setEmpGender(rs.getInt("emp_gender"));
				emp.setEmpFlag(rs.getInt("emp_flag"));
				emp.setRole(role);

				role = new Role();
				role.setRoleId(rs.getInt("role_id"));
				role.setRoleName(rs.getString("role_name"));

				gogoodsrecord.setGogoodsrecordWSUuid(cangkuuuid);
				gogoodsrecord.setGogoodsrecordUuid(rs.getString("gogoodsrecord_uuid"));
				gogoodsrecord.setEmp(emp);
				gogoodsrecord.setGoods(goods);
				gogoodsrecord.setShop(shop);
				// list = new ArrayList<Comegoodsrecord>();
				list1.add(gogoodsrecord);
				System.out.println("dao层                                                 "+list.size());
			
		}
		map2.put("list", list1);
		return map2;
	}


	@Override
	public List<SelectCountByType> selectRChuKuJiLuBingZhuangTu() throws SQLException {
		StringBuilder sql = new StringBuilder(" select t.type_name,sum(gg.gogoodsrecord_count) as sum ");
		sql.append(" FROM gogoodsrecord as gg inner join goods as g inner join type as t  ");
		sql.append(" on gg.gogoodsrecord_goods_id = g.goods_id  ");
		sql.append(" and g.goods_type_id = t.type_id ");
		sql.append(" GROUP BY t.type_name ");
		Connection conn = BaseDBUtils.getConnection();
		PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
		ResultSet rs = BaseDBUtils.executeQuery(pst);
		List<SelectCountByType> list = new ArrayList<SelectCountByType>();
		SelectCountByType clountByType = null;
		while (rs.next()) {	
			String type_name = rs.getString("type_name");
			int sum = rs.getInt("sum");
			clountByType = new SelectCountByType(type_name, sum);
			list.add(clountByType);
		}
		return list;
	}

	@Override
	public List<RecordMonthAndSum> FenLeiChaXun() throws SQLException {
		StringBuilder sql = new StringBuilder(" select DATE_FORMAT(gogoodsrecord_time,'%m') month,sum(gogoodsrecord_count) as sum from gogoodsrecord ");
		sql.append(" group by month ");
		Connection conn = BaseDBUtils.getConnection();
		PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
		ResultSet rs = BaseDBUtils.executeQuery(pst);
		List<RecordMonthAndSum> list = new ArrayList<RecordMonthAndSum>();
		RecordMonthAndSum recordMonthAndSum = null;
		while (rs.next()) {	
			String month = rs.getString("month");
			int sum = rs.getInt("sum");
			recordMonthAndSum = new RecordMonthAndSum(month, sum);
			list.add(recordMonthAndSum);
		}
		return list;
	}
	
	@Override
	public int insertJiLuChuKu(Map<String, Object> map) throws SQLException {
		//通过货物表中的仓库uuid查询货物id
				StringBuilder sql1 = new StringBuilder(" select  goods_id,goods_shop_id,goods_count,goods_w_s_uuid from  goods where goods_model_id=? and goods_w_s_uuid=? ");
				Connection conn1 = BaseDBUtils.getConnection();
				int model_id = ((Goods)map.get("goods")).getModel().getModelId();
				String goodsWSUuid = ((Goods)map.get("goods")).getGoodsWSUuid();
				Object[] param1 = {model_id,goodsWSUuid};
				PreparedStatement pst1 = BaseDBUtils.getPreparedStatement(conn1, sql1.toString());
				int goodsid = 0;
				int goods_shop_id = 0;
				int goods_count = 0; 
				String goods_w_s_uuid = null;
				ResultSet rs1 = BaseDBUtils.executeQuery(pst1,param1);
				System.out.println(rs1.toString());
				if (rs1!=null) {
					if(rs1.first()) {
						rs1.previous();
						while (rs1.next()) {
							goodsid = rs1.getInt("goods_id");
							goods_shop_id = rs1.getInt("goods_shop_id");
							goods_count = rs1.getInt("goods_count");
							goods_w_s_uuid = rs1.getString("goods_w_s_uuid");
						}
					}
				}
				
				
				System.out.println("service uuid         "+goods_w_s_uuid);
				
				//通过通过货物表中的仓库uuid查询货物id
				//向出库记录表中插入数据
				StringBuilder sql = new StringBuilder(" INSERT into gogoodsrecord(gogoodsrecord_time,gogoodsrecord_count,gogoodsrecord_w_s_uuid,gogoodsrecord_uuid,gogoodsrecord_emp_id,gogoodsrecord_shop_id,gogoodsrecord_goods_id) ");
				sql.append(" values (?,?,?,?,?,?,?) ");
				String today= DateUtil.today();
				String gogoodsrecord_uuid = ((Chuku)map.get("chuku")).getChukuChukujiluuuid();
				Object[] param = {today,goods_count,goods_w_s_uuid,gogoodsrecord_uuid,map.get("empid"),goods_shop_id,goodsid};
				Connection conn = BaseDBUtils.getConnection();
				PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
				System.out.println(param.length);
				int i = BaseDBUtils.executeUpdate(pst, param);
				return i;
			}
			
			
		//记录总条数    传入map()跟查询记录的一样    返回int
		public int selectGoRecordCount(Map<String, Object> map) throws SQLException {
			StringBuilder sql = new StringBuilder(" select count(*) as count");
			sql.append(" from gogoodsrecord as gg ");
			sql.append(" inner join emp as e ");
			sql.append(" inner join shop as s ");
			sql.append(" inner join goods as g ");
			sql.append(" inner join warehouse_shop as ws ");
			sql.append(" inner join warehouse as w  ");
			sql.append(" inner join type as t ");
			sql.append(" inner join brand as b ");
			sql.append(" inner join model as m ");
			sql.append(" inner join role as r ");
			sql.append(" on gg.gogoodsrecord_emp_id= e.emp_id ");
			sql.append(" and gg.gogoodsrecord_shop_id = s.shop_id ");
			sql.append(" and gg.gogoodsrecord_goods_id = g.goods_id ");
			sql.append(" and s.shop_id = ws.warehouse_shop_shop_id ");
			sql.append(" and ws.warehouse_shop_warehouse_id = w.warehouse_id ");
			sql.append(" and g.goods_type_id = t.type_id ");
			sql.append(" and g.goods_brand_id = b.brand_id ");
			sql.append(" and g.goods_model_id = m.model_id ");
			sql.append(" and e.emp_role_id = r.role_id ");

			List<String> list = new ArrayList<String>();
			int count = 0;
			ResultSet rs = null;
			
			// 按仓库uuid查询 总条数      传入map(k:"warehouseuuid",value:"仓库uuid001")   返回map（k:"list" value:list）
			if (map.containsKey("warehouseuuid") && !StringUtils.isEmpty((String) map.get("warehouseuuid"))) {
				sql.append(" and gogoodsrecord_w_s_uuid = ? ");
				list.add((String) map.get("warehouseuuid"));
				Object[] param = list.toArray();
				Connection conn = BaseDBUtils.getConnection();
				PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
				rs = BaseDBUtils.executeQuery(pst, param);
			//	return map2;
			}
			// 按商家uuid查询 总条数      传入map(k:"shopuuid",value:"店铺uuid001")   返回map（k:"list" value:list）
			else if (map.containsKey("shopuuid") && !StringUtils.isEmpty((String) map.get("shopuuid"))) {
				sql.append(" and s.shop_uuid = ? ");
				list.add((String) map.get("shopuuid"));
				Object[] param = list.toArray();
				Connection conn = BaseDBUtils.getConnection();
				PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
				rs = BaseDBUtils.executeQuery(pst, param);
			//	return map2;
			}
			// 按入货员查询 总条数   传入map(k:"empname",value:"张三")   返回map（k:"list" value:list）
			else if (map.containsKey("empname") && !StringUtils.isEmpty((String) map.get("empname"))) {
				sql.append(" and e.emp_name = ? ");
		//		list.add((String) map.get("empname"));							
		//		Object[] param = list.toArray();
				String param = (String) map.get("empname");
				Connection conn = BaseDBUtils.getConnection();
				PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());		
				rs = BaseDBUtils.executeQuery(pst, param);		
			}
			
			// 按年月查询 总条数
			else if (map.containsKey("year") && map.containsKey("month") && !StringUtils.isEmpty((String) map.get("year")) && !StringUtils.isEmpty((String) map.get("month"))) {
				sql.append(" and  Year(gogoodsrecord_time) = ? ");
				sql.append(" and Month(gogoodsrecord_time) = ? ");
				list.add((String)map.get("year"));
				list.add((String)map.get("month"));
				Object[] param = list.toArray();
				Connection conn = BaseDBUtils.getConnection();
				PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
				rs = BaseDBUtils.executeQuery(pst, param);
			}

			else {
				// 没有查询参数  总条数	
				Connection conn = BaseDBUtils.getConnection();
				PreparedStatement pst = BaseDBUtils.getPreparedStatement(conn, sql.toString());
				 rs = BaseDBUtils.executeQuery(pst);
			}
					// 遍历结果集
					while (rs.next()) {
						count = rs.getInt("count");
					}
				return count;
		}
}
