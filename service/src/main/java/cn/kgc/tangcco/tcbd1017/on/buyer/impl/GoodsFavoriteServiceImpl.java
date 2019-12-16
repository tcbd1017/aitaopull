package cn.kgc.tangcco.tcbd1017.on.buyer.impl;
/**
* @author 张忠华  
* @date 2019年12月13日  
* @version 1.0  
*/
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.buyer.GoodsFavoriteDao;
import cn.kgc.tangcco.tcbd1017.on.buyer.GoodsFavoriteService;


public class GoodsFavoriteServiceImpl implements GoodsFavoriteService{
	private static GoodsFavoriteDao goodsFavoriteDao;
	private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
			"ApplicationContext_on.xml");
	static {
		try {
			goodsFavoriteDao = (GoodsFavoriteDao) context.getBean(GoodsFavoriteDao.class.getSimpleName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public Map<String, Object> addGoodsFavorite(Map<String, Object> map)  {
		Map<String,Object> map1=new HashMap<String,Object>();
		map1.put("status", "failed");
		try {
			BaseDBUtils.startTransaction();
			int judgeGoodsFavorite = goodsFavoriteDao.judgeGoodsFavorite(map);
			System.out.println(judgeGoodsFavorite);
			if(judgeGoodsFavorite!= 1) {
				int addGoodsFavorite = goodsFavoriteDao.addGoodsFavorite(map);
				if(addGoodsFavorite>0 ) {			
				map1.put("status", "success");
			}
			}
			BaseDBUtils.commitAndClose();
		} catch (SQLException e) {
			
				try {
					BaseDBUtils.closeAll();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			
		}
		return map1;
		
	}
	@Override
	public Map<String, Object> removeGoodsFavorite(Map<String,Object> map) {
		Map<String,Object> map1=new HashMap<String,Object>();
		map1.put("status", "failed");
		try {
			BaseDBUtils.startTransaction();
			int deleteGoodsFavorite = goodsFavoriteDao.deleteGoodsFavorite(map);
			
			if(deleteGoodsFavorite>0) {
				map1.put("status", "success");
				
			}
			BaseDBUtils.commitAndClose();
		} catch (SQLException e) {
			try {
				BaseDBUtils.closeAll();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

		return map1;
	}
	@Override
	public Map<String, Object> queryGoodsFavorite(Map<String, Object> map) {
		Map<String,Object> map1=new HashMap<String,Object>();
		map1.put("status", "failed");
	//	map1.put("code",0);
	//	map1.put("count",0);
		map1.put("msg","");
		map1.put("data",new ArrayList());
		
		try {
//			int count= goodsFavoriteDao.countGoodsFavorite(map);
//			if(count>0) {
//				map1.put("count",count);
//			}			
			List<Map<String,Object>> data = goodsFavoriteDao.selectGoodsFavorite(map);
			if(data!=null&&data.size()>0) {
				map1.put("data",data);
				map1.put("status", "success");
			}else {
				map1.put("msg","请收藏宝贝");
			}
			BaseDBUtils.closeAll();
			
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				BaseDBUtils.closeAll();
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			}
		}
		return map1;
	}



	
}
