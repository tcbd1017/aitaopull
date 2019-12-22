package cn.kgc.tangcco.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.dao.ChuKuDao;
import cn.kgc.tangcco.dao.GoodsDao;
import cn.kgc.tangcco.lihaozhe.commons.bianhao.BaseBianHao;
import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.lihaozhe.commons.jdbc.PageRang;
import cn.kgc.tangcco.lihaozhe.commons.uuid.BaseUUID;
import cn.kgc.tangcco.pojo.Brand;
import cn.kgc.tangcco.pojo.Chuku;
import cn.kgc.tangcco.pojo.Goods;
import cn.kgc.tangcco.pojo.Model;
import cn.kgc.tangcco.pojo.Shop;
import cn.kgc.tangcco.pojo.Type;

public class ChuKuDaoImpl implements ChuKuDao{
	
	
	
	//两个页面   到时候表头有我自己填
	@Override
	public List<Chuku> selectChuKu(Map<String, Object> map) {
		BrandDaoImpl brand=new BrandDaoImpl();
		ModelDaoImpl model=new ModelDaoImpl();
		TypeDaoImpl type=new TypeDaoImpl();
		GoodsDao good=new GoodsDaoImpl();
		
		List<Chuku>list2=new ArrayList<>();
		
		Chuku chuku=null;
		PanDuImpl aa=new PanDuImpl();
		String account = (String) map.get("account");
		int pandu = aa.pandu(account);
		System.out.println(pandu);
		
		if(pandu!=0) {
		
			List list=new ArrayList<>();
			//Emp emp =(Emp) map.get("operator");
			StringBuffer sql=new StringBuffer();
			sql.append(" select * from chuku where 1=1 ");
			if(map.containsKey("Shop")) {
				if(map.get("Shop")!=null) {
					sql.append(" and chuku_shangpuuuid=? ");
					list.add((String)map.get("Shop"));
				}
			}
			if(map.containsKey("cangku")) {
				if(map.get("cangku")!=null) {
					sql.append(" and chuku_cangkuuuid=? ");
					list.add((String)map.get("cangku"));
				}
			}
			
			if(map.containsKey("flag")) {
				if(map.get("flag")!=null) {
					sql.append(" and chuku_flag=? ");
					list.add(map.get("flag"));
				}
			}
			if(map.containsKey("uuid")) {
				if(map.get("uuid")!=null) {
					sql.append(" and chuku_chukujiluuuid=? ");
					list.add((String)map.get("uuid"));
				}
			}
			Object[] array = list.toArray();
			try {
				Connection connection = BaseDBUtils.getConnection();
				PreparedStatement preparedStatement = BaseDBUtils.getPreparedStatement(connection, sql.toString(), (PageRang)map.get("page"));
				ResultSet executeQuery = BaseDBUtils.executeQuery(preparedStatement, array);
				if (executeQuery != null) {
					while (executeQuery.next()) {
						chuku=new Chuku();
						chuku.setChukuId(executeQuery.getInt("chuku_id"));
						
						int int1 = executeQuery.getInt("chuku_type_id");
						Type selectLeiXingById = type.selectLeiXingById(int1);
						chuku.setType(selectLeiXingById);
						
						int int2 = executeQuery.getInt("chuku_brand_id");
						Brand selectLeiXingByid2 = brand.selectLeiXingByid(int2);
						chuku.setBrand(selectLeiXingByid2);
						int int3 = executeQuery.getInt("chuku_model_id");
						Model selectXingHaoById = model.selectXingHaoById(int3);
						chuku.setModel(selectXingHaoById);
						chuku.setChukuChukujiluuuid(executeQuery.getString("chuku_chukujiluuuid"));
						chuku.setChukuGogoodsrecoredCount(executeQuery.getInt("chuku_gogoodsrecored_count"));
						
						chuku.setChukuShangpuuuid(executeQuery.getString("chuku_shangpuuuid"));
						
						chuku.setChukuPrice(executeQuery.getDouble("chuku_price"));
						
						chuku.setChukuCangkuuuid(executeQuery.getString("chuku_cangkuuuid"));
						
						chuku.setChukuFlag(executeQuery.getInt("chuku_flag"));
						
						Goods goods=new Goods();
						Map<String,Object>map3=new HashMap<String, Object>();
						map3.put("goods_model_id", int3);
						map3.put("goods_w_s_uuid", (String)executeQuery.getString("chuku_cangkuuuid"));
						map3.put("goods_type_id", int1);
						map3.put("goods_brand_id", int2);
						goods.setCount(good.ChaXunKuCun(map3));
						
						chuku.setGoods(goods);
						list2.add(chuku);
					}
				}		
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}else {
			
			//Shop emp =(Shop) map.get("operator");
			List list=new ArrayList<>();
			StringBuffer sql=new StringBuffer();
			sql.append(" select * from chuku where 1=1 and chuku_shangpuuuid=? ");
			list.add(map.get("chuku_shangpuuuid"));
			if(map.containsKey("cangku")) {
				if(map.get("cangku")!=null) {
					sql.append(" and chuku_cangkuuuid=? ");
					list.add((String)map.get("cangku"));
				}
			}
			
			System.out.println( "        asasa          " +map.get("flag"));
			if(map.containsKey("flag")) {
				if(map.get("flag")!=null) {
					sql.append(" and chuku_flag=? ");
					list.add(map.get("flag"));
					System.out.println(1111111);
				}
			}
			if(map.containsKey("uuid")) {
				if(map.get("uuid")!=null) {
					sql.append(" and chuku_chukujiluuuid=? ");
					list.add((String)map.get("uuid"));
				}
			}
			Object[] array = list.toArray();
			try {
				Connection connection = BaseDBUtils.getConnection();
				PreparedStatement preparedStatement = BaseDBUtils.getPreparedStatement(connection, sql.toString(), (PageRang)map.get("page"));
				ResultSet executeQuery = BaseDBUtils.executeQuery(preparedStatement, array);
				if (executeQuery != null) {
					while (executeQuery.next()) {
						chuku=new Chuku();
						
						chuku.setChukuId(executeQuery.getInt("chuku_id"));
						
						chuku.setChukuChukujiluuuid(executeQuery.getString("chuku_chukujiluuuid"));
						
						int int1 = executeQuery.getInt("chuku_type_id");
						Type selectLeiXingById = type.selectLeiXingById(int1);
						chuku.setType(selectLeiXingById);
						
						int int2 = executeQuery.getInt("chuku_brand_id");
						Brand selectLeiXingByid2 = brand.selectLeiXingByid(int2);
						chuku.setBrand(selectLeiXingByid2);
						
						
						
						int int3 = executeQuery.getInt("chuku_model_id");
						Model selectXingHaoById = model.selectXingHaoById(int3);
						chuku.setModel(selectXingHaoById);
						
						chuku.setChukuGogoodsrecoredCount(executeQuery.getInt("chuku_gogoodsrecored_count"));
						
						chuku.setChukuShangpuuuid(executeQuery.getString("chuku_shangpuuuid"));
						
						chuku.setChukuPrice(executeQuery.getDouble("chuku_price"));
						
						chuku.setChukuCangkuuuid(executeQuery.getString("chuku_cangkuuuid"));
						
						chuku.setChukuFlag(executeQuery.getInt("chuku_flag"));
						list2.add(chuku);
					}
				}
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		return list2;
	}

	
//	@Override
//	public Map<String, Object> selectShangPuid(Map<String, Object> map) {
//		//页面调用仓库   显示下拉框  id 和 商铺uuid
//		return null;
//	}

	
	
	
	@Override
	//uuid时自动生成的  此方法时商家操作的
	public int insertChuKu(Map<String, Object> map) {
		StringBuffer sql=new StringBuffer();
		ModelDaoImpl aa=new ModelDaoImpl();
		String generate = BaseBianHao.generateUniqueKey();
		Chuku chuku=(Chuku) map.get("Chuku");
		int aaa=0;
		sql.append(" INSERT INTO chuku (chuku_type_id,chuku_brand_id,chuku_model_id,chuku_gogoodsrecored_count,chuku_shangpuuuid,chuku_price,chuku_cangkuuuid,chuku_chukujiluuuid,chuku_flag)"); 
		sql.append(" VALUES(?,?,?,?,?,?,?,?,3)");
		Object array[] = {chuku.getType().getTypeId(),chuku.getBrand().getBrandId(),chuku.getModel().getModelId(),chuku.getChukuGogoodsrecoredCount(),chuku.getChukuShangpuuuid(),aa.selectPrice(chuku.getType().getTypeId(), chuku.getBrand().getBrandId(),chuku.getModel().getModelId() ),chuku.getChukuCangkuuuid(),generate};
		try {
			Connection connection = BaseDBUtils.getConnection();
			PreparedStatement preparedStatement = BaseDBUtils.getPreparedStatement(connection, sql.toString());
			aaa = BaseDBUtils.executeUpdate(preparedStatement, array);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return aaa;
	}
	
	
	
	@Override
	public int XiuGaiZhuangTaiChuKu(Map<String, Object> map) {
		//出货员  点击出货    直接减少货物并增加记录  并改状态
		StringBuffer sql=new StringBuffer();
		sql.append(" update chuku set  chuku_flag=?  where chuku_chukujiluuuid=?  ");
		int a=0;
		String chuku =((Chuku) map.get("chuku")).getChukuChukujiluuuid();
		Object array[]= {map.get("zhuangtai"),chuku};
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
		int aa=0;
		List list=new ArrayList<>();
		//Emp emp =(Emp) map.get("operator");
		StringBuffer sql=new StringBuffer();
		sql.append(" select count(*) from chuku where 1=1 ");
		if(map.containsKey("Shop")) {
			if(map.get("Shop")!=null) {
				sql.append(" and chuku_shangpuuuid=? ");
				list.add((String)map.get("Shop"));
			}
		}
		if(map.containsKey("cangku")) {
			if(map.get("cangku")!=null) {
				sql.append(" and chuku_cangkuuuid=? ");
				list.add((String)map.get("cangku"));
			}
		}
		System.out.println(11111);
		if(map.containsKey("flag")) {
			System.out.println(1111111);
			if(map.get("flag")!=null) {
				sql.append(" and chuku_flag=? ");
				list.add(map.get("flag"));
			}
		}
		if(map.containsKey("uuid")) {
			if(map.get("uuid")!=null) {
				sql.append(" and chuku_chukujiluuuid=? ");
				list.add((String)map.get("uuid"));
			}
		}
		Object[] array = list.toArray();
		
			try {
				Connection connection = BaseDBUtils.getConnection();
				PreparedStatement preparedStatement = BaseDBUtils.getPreparedStatement(connection, sql.toString());
				ResultSet executeQuery = BaseDBUtils.executeQuery(preparedStatement, array);
				while (executeQuery.next()) {
					aa=executeQuery.getInt("count(*)");					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return aa;
	}
}
