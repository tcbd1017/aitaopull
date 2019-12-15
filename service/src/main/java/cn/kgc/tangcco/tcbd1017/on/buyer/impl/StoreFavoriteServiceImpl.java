package cn.kgc.tangcco.tcbd1017.on.buyer.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.buyer.StoreFavoriteDao;
import cn.kgc.tangcco.tcbd1017.on.buyer.StoreFavoriteService;
import cn.kgc.tangcco.tcbd1017.on.pojo.StoreFavorite;

/**
 * 
 * 	@author 和朋朋
 * 	@version 1.0<br>
 * 	创建时间：  2019年12月9日 上午11:15:02 <br>
 * 	类描述：逻辑层实现
 *	
 */

public class StoreFavoriteServiceImpl implements StoreFavoriteService{
	private static StoreFavoriteDao sd;
	private static ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("ApplicationContext_on.xml");
	static {
		try {
			sd=(StoreFavoriteDao) context.getBean(StoreFavoriteDao.class.getSimpleName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public Map<String, Object> queryStoreFavorite(Map<String, Object> map)  {
		Map<String, Object>map2=new HashMap<String, Object>();
		map2.put("code", 0);
		map2.put("count", 0);
		map2.put("msg", "");
		map2.put("status", "failed");
		map2.put("date", new ArrayList());
		List list=null;
		list = sd.fuzzyQueryStoreFavorite(map);
//		try {
//
//			int count = sd.count();
//			if (count>0) {
//				map2.put("count", count);
//			}else {
//				System.out.println("零条记录");
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		if (list!=null) {
			map2.put("status", "success");
			map2.put("date", list);
		}else {
			map2.put("msg", "请先添加收藏的店铺!");
		}
		return map2;
	}
	@Override
	public Map<String, Object> addStoreFavorite(Map<String, Object>map) {
		Map<String, Object>map2=new HashMap<String, Object>();
		map2.put("code", 0);
		map2.put("msg", "");
		map2.put("status", "failed");
		try {
			BaseDBUtils.startTransaction();
			int count = sd.addStoreFavorite(map);
			if (count>0) {
				map2.put("status", "success");
				map2.put("date", count);
			}
			BaseDBUtils.commitAndClose();
		} catch (SQLException e) {
			try {
				BaseDBUtils.rollbackAndClose();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map2;
	}
	@Override
	public Map<String, Object> removeStoreFavorite(Map<String, Object>map) {
		Map<String, Object>map2=new HashMap<String, Object>();
		map2.put("code", 0);
		map2.put("msg", "");
		map2.put("status", "failed");
		try {
			BaseDBUtils.startTransaction();
			int count = sd.deleteStoreFavoriteByStoreFavoriteId(map);
			if (count>0) {
				map2.put("status", "success");
				map2.put("date", count);
			}
			BaseDBUtils.commitAndClose();
		} catch (SQLException e) {
			try {
				BaseDBUtils.rollbackAndClose();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map2;
	}
	
}
