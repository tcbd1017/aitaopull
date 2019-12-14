package cn.kgc.tangcco.tcbd1017.on.buyer.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.buyer.GoodsDao;
import cn.kgc.tangcco.tcbd1017.on.buyer.GoodsService;
import cn.kgc.tangcco.tcbd1017.on.pojo.Goods;

/**
*@author 作者：肖越
*@version 1.0 创建时间:2019年12月13日上午11:22:19
*/
public class GoodsServiceImpl implements GoodsService {
	static GoodsDao goodsDao;
	static ClassPathXmlApplicationContext path;
	static {
		path = new ClassPathXmlApplicationContext("ApplicationContext_on.xml");
		try {
			goodsDao = (GoodsDao) path.getBean(GoodsDao.class.getSimpleName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 查詢所有物品
	 * @return
	 */
	@Override
	public Map<String, Object> queryAllGoods(){
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("msg","");
		map.put("code",0);
		map.put("data",new ArrayList<Goods>());
		map.put("status", "failed");
		try {
			List<Goods> goods = goodsDao.selectAllGoods();
			if (goods !=null) {
				map.put("status", "success");
				map.put("data",goods);
			}
			BaseDBUtils.closeAll();
			return map;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				BaseDBUtils.closeAll();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return map;
	}
	
	
	/**
	 * 根据商品类型查询商品
	 * @param goodsType
	 * @return map类型
	 */
	@Override
	public Map<String, Object> queryByGoodsType(String goodsType) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("msg","");
		map.put("code",0);
		map.put("data",new ArrayList<Goods>());
		map.put("status", "failed");
		try {
			List<Goods> goods = goodsDao.selectByGoodsType(goodsType);
			if (goods !=null) {
				map.put("status", "success");
				map.put("data",goods);
			}
			BaseDBUtils.closeAll();
			return map;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				BaseDBUtils.closeAll();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return map;
	}


	
	
	/**
	 * 按照商品名称、或者商品类型、或者商品简介 ,模糊查询出相关商品
	 * @param vague 商品名称、或者商品类型、或者商品简介
	 * @return map类型
	 */
	@Override
	public Map<String, Object> queryVagueByGoods_nameOrGoods_brandOrGoods_presentation(String vague) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("msg","");
		map.put("code",0);
		map.put("data",new ArrayList<Goods>());
		map.put("status", "failed");
		try {
			List<Goods> goods = goodsDao.selectVagueByGoods_nameOrGoods_brandOrGoods_presentation(vague);
			if (goods !=null) {
				map.put("status", "success");
				map.put("data",goods);
			}
			BaseDBUtils.closeAll();
			return map;
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				BaseDBUtils.closeAll();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return map;
	}

	

}
