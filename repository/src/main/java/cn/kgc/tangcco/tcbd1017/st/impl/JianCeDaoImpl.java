package cn.kgc.tangcco.tcbd1017.st.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.lihaozhe.commons.bianhao.BaseBianHao;
import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.lihaozhe.commons.jdbc.PageRang;
import cn.kgc.tangcco.tcbd1017.st.GoodsDao;
import cn.kgc.tangcco.tcbd1017.st.JianCeDao;
import cn.kgc.tangcco.tcbd1017.st.pojo.Brand;
import cn.kgc.tangcco.tcbd1017.st.pojo.Jiance;
import cn.kgc.tangcco.tcbd1017.st.pojo.Model;
import cn.kgc.tangcco.tcbd1017.st.pojo.Type;
import cn.kgc.tangcco.tcbd1017.st.pojo.WarehouseShop;



public class JianCeDaoImpl implements JianCeDao {

	@Override
	public List<Jiance> selectZhuCe(Map<String, Object> map) {
		BrandDaoImpl brand = new BrandDaoImpl();
		ModelDaoImpl model = new ModelDaoImpl();
		TypeDaoImpl type = new TypeDaoImpl();
		GoodsDao good = new GoodsDaoImpl();

		List<Jiance> list2 = new ArrayList<>();

		Jiance jiance = null;
		PanDuImpl aa = new PanDuImpl();
		String account = (String) map.get("account");
		int pandu = aa.pandu(account);
		System.out.println(pandu);

		if (pandu != 0) {
			List list = new ArrayList<>();
			
			StringBuffer sql = new StringBuffer();
			sql.append(" select * from jance where 1=1 ");
			if (map.containsKey("Shop") && map.get("shop")!=null) {
				if (map.get("Shop") != null) {
					sql.append(" and jiance_shangpuuuid=? ");
					list.add((String) map.get("Shop"));
				}
			}
			if (map.containsKey("cangku") && map.get("cangku")!=null) {
				if (map.get("cangku") != null) {
					sql.append(" and jiance_cangkuuuid=? ");
					list.add(map.get("cangku"));
				}
			}

			if (map.containsKey("flag") && map.get("flag")!=null) {
				if (map.get("flag") != null) {
					sql.append(" and jiance_flag=? ");
					list.add( map.get("flag"));
				}
			}
			if (map.containsKey("uuid") && map.get("uuid")!=null) {
				if (map.get("uuid") != null) {
					sql.append(" and jiance_rukujiluuuid=? ");
					list.add( map.get("uuid"));
				}
			}
			Object[] array = list.toArray();
			try {
				Connection connection = BaseDBUtils.getConnection();
				PreparedStatement preparedStatement = BaseDBUtils.getPreparedStatement(connection, sql.toString(),
						(PageRang) map.get("page"));
				ResultSet executeQuery = BaseDBUtils.executeQuery(preparedStatement, array);
				if (executeQuery != null) {
					while (executeQuery.next()) {
						jiance = new Jiance();

						int int1 = executeQuery.getInt("jiance_type_id");
						Type selectLeiXingById = type.selectLeiXingById(int1);
						jiance.setType(selectLeiXingById);

						int int2 = executeQuery.getInt("jiance_brand_id");
						Brand selectLeiXingByid2 = brand.selectPinPaiByid(int2);
						jiance.setBrand(selectLeiXingByid2);
						int int3 = executeQuery.getInt("jiance_model_id");
						Model selectXingHaoById = model.selectXingHaoById(int3);
						jiance.setModel(selectXingHaoById);

						jiance.setJianceComegoodsrecoredCount(executeQuery.getInt("jiance_comegoodsrecored_count"));

						jiance.setJianceShangpuuuid(executeQuery.getString("jiance_shangpuuuid"));

						jiance.setJiancePrice(executeQuery.getDouble("jiance_price"));

						jiance.setJianceCangkuuuid(executeQuery.getString("jiance_cangkuuuid"));

						jiance.setJianceFlag(executeQuery.getInt("jiance_flag"));
						jiance.setJianceRukujiluuuid(executeQuery.getString("jiance_rukujiluuuid"));
						WarehouseShop warehouseShop = new WarehouseShop();
						Map<String, Object> map3 = new HashMap<String, Object>();

						warehouse_shopDaoImpl ws = new warehouse_shopDaoImpl();

						warehouseShop.setWarehouseShopDaoqitime(
								ws.ChaXunDaoQiTime(executeQuery.getString("jiance_cangkuuuid")));
						warehouseShop.setWarehouseShopWarehousesize(
								ws.ChaXunShengYuRongLiang(executeQuery.getString("jiance_cangkuuuid")));
						jiance.setWare(warehouseShop);
						
						list2.add(jiance);
					}
				}
			} catch (SQLException e) {

				e.printStackTrace();
			}
		} else if(pandu==0){

//			Shop emp = (Shop) map.get("operator");
			List list = new ArrayList<>();
			StringBuffer sql = new StringBuffer();
			sql.append(" select * from jance where 1=1 and jiance_shangpuuuid=? ");
			list.add(map.get("chuku_shangpuuuid"));
			if (map.containsKey("cangku") && map.get("cangku")!=null) {
				if (map.get("cangku") != null) {
					sql.append(" and jiance_cangkuuuid=? ");
					list.add((String) map.get("cangku"));
				}
			}

			System.out.println(" asasa " + map.get("flag"));
			if (map.containsKey("flag") && map.get("flag")!=null) {
				if (map.get("flag") != null) {
					sql.append(" and jiance_flag=? ");
					list.add( map.get("flag"));
					System.out.println(1111111);
				}
			}
			if (map.containsKey("uuid") && map.get("uuid")!=null) {
				if (map.get("uuid") != null) {
					sql.append(" and jiance_chukujiluuuid=? ");
					list.add((String) map.get("uuid"));
				}
			}
			Object[] array = list.toArray();
			try {
				Connection connection = BaseDBUtils.getConnection();
				PreparedStatement preparedStatement = BaseDBUtils.getPreparedStatement(connection, sql.toString(),
						(PageRang) map.get("page"));
				ResultSet executeQuery = BaseDBUtils.executeQuery(preparedStatement, array);
				if (executeQuery != null) {
					while (executeQuery.next()) {
						jiance = new Jiance();

						int int1 = executeQuery.getInt("jiance_type_id");
						Type selectLeiXingById = type.selectLeiXingById(int1);
						jiance.setType(selectLeiXingById);

						int int2 = executeQuery.getInt("jiance_brand_id");
						Brand selectLeiXingByid2 = brand.selectPinPaiByid(int2);
						jiance.setBrand(selectLeiXingByid2);
						int int3 = executeQuery.getInt("jiance_model_id");
						Model selectXingHaoById = model.selectXingHaoById(int3);
						jiance.setModel(selectXingHaoById);

						jiance.setJianceComegoodsrecoredCount(executeQuery.getInt("jiance_comegoodsrecored_count"));

						jiance.setJianceShangpuuuid(executeQuery.getString("jiance_shangpuuuid"));

						jiance.setJiancePrice(executeQuery.getDouble("jiance_price"));

						jiance.setJianceCangkuuuid(executeQuery.getString("jiance_cangkuuuid"));
						jiance.setJianceRukujiluuuid(executeQuery.getString("jiance_rukujiluuuid"));
						jiance.setJianceFlag(executeQuery.getInt("jiance_flag"));
						list2.add(jiance);
					}
				}

			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
		return list2;
	}

	@Override
	public int insertJianCeBiao(Map<String, Object> map) {
		StringBuffer sql = new StringBuffer();
		ModelDaoImpl aa = new ModelDaoImpl();
		String generate = BaseBianHao.generateUniqueKey();
		Jiance jiance = (Jiance) map.get("jiance");
		int aaa = 0;
		sql.append("\r\n"
				+ "INSERT INTO jance(jiance_price,jiance_comegoodsrecored_count,jiance_type_id,jiance_brand_id,jiance_model_id,jiance_shangpuuuid,jiance_cangkuuuid,jiance_rukujiluuuid,jiance_flag) ");
		sql.append(" VALUES(?,?,?,?,?,?,?,?,2)  ");
		Object array[] = {
				aa.selectPrice(jiance.getType().getTypeId(),
						jiance.getBrand().getBrandId(), jiance.getModel().getModelId()), jiance.getJianceComegoodsrecoredCount(), jiance.getType().getTypeId(),
				jiance.getBrand().getBrandId(), jiance.getModel().getModelId(),
				
				jiance.getJianceShangpuuuid(),jiance.getJianceCangkuuuid(), generate };
		try {
			Connection connection = BaseDBUtils.getConnection();
			PreparedStatement preparedStatement = BaseDBUtils.getPreparedStatement(connection, sql.toString());
			aaa = BaseDBUtils.executeUpdate(preparedStatement, array);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return aaa;
	}

	@Override
	public int XiuGaiZhuangTaiRuKu(Map<String, Object> map) {
		// 入货员 点击出货 直接增加货物并增加记录 并改状态 
		StringBuffer sql = new StringBuffer();
		sql.append(" update jance set  jiance_flag=?  where jiance_rukujiluuuid=?  ");
		int a = 0;
		System.out.println(map.get("zhuangtai"));
		System.out.println(map.get("jiance_rukujiluuuid"));
		Object array[] = { map.get("zhuangtai"), map.get("jiance_rukujiluuuid") };
		try {
			Connection connection = BaseDBUtils.getConnection();
			PreparedStatement preparedStatement = BaseDBUtils.getPreparedStatement(connection, sql.toString());
			a = BaseDBUtils.executeUpdate(preparedStatement, array);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return a;
	}


	
	@Override
	public int selectJianCeNum(Map<String, Object> map) {
		List list = new ArrayList<>();
		StringBuffer sql = new StringBuffer();
		sql.append(" select count(*) from jance where 1=1 ");
		if (map.containsKey("Shop")) {
			if (map.get("Shop") != null) {
				sql.append(" and jiance_shangpuuuid=? ");
				list.add((String) map.get("Shop"));
			}
		}
		if (map.containsKey("cangku")) {
			if (map.get("cangku") != null) {
				sql.append(" and jiance_cangkuuuid=? ");
				list.add((String) map.get("cangku"));
			}
		}

		if (map.containsKey("flag")) {
			if (map.get("flag") != null) {
				sql.append(" and jiance_flag=? ");
				list.add( map.get("flag"));
			}
		}
		if (map.containsKey("uuid")) {
			if (map.get("uuid") != null) {
				sql.append(" and jiance_chukujiluuuid=? ");
				list.add((String) map.get("uuid"));
			}
		}
		Object[] array = list.toArray();
		int aaa=0;
		try {
			Connection connection = BaseDBUtils.getConnection();
			PreparedStatement preparedStatement = BaseDBUtils.getPreparedStatement(connection, sql.toString());
			ResultSet executeQuery = BaseDBUtils.executeQuery(preparedStatement, array);
			while (executeQuery.next()) {
				aaa=executeQuery.getInt("count(*)");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return aaa;
	}

	@Override
	public int selectShangPuid(String uuid) {
		int aa=0;
		StringBuffer sql=new StringBuffer("SELECT shop_id FROM shop WHERE shop_uuid=? ");
		Object[]array= {uuid};
		try {
			Connection connection = BaseDBUtils.getConnection();
			PreparedStatement preparedStatement = BaseDBUtils.getPreparedStatement(connection, sql.toString());
			ResultSet executeQuery = BaseDBUtils.executeQuery(preparedStatement, array);
			while (executeQuery.next()) {
				aa=executeQuery.getInt("shop_id");
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return aa;
	}

}
