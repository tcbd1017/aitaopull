package cn.kgc.tangcco.tcbd1017.on.seller.impl;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;

import cn.kgc.tangcco.tcbd1017.on.seller.SellerCommentManagementDao;
import cn.kgc.tangcco.tcbd1017.on.seller.SellerCommentManagementService;

/**
*@author   郝彦冰<br>
*@version  创建时间 :  2019年12月16日  下午1:41:24<br>
*类说明:
*/
public class SellerCommentManagementServiceImpl implements SellerCommentManagementService{
	private static SellerCommentManagementDao sellerCommentManagementDao;
	private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext_on.xml");
	static {
		try {
			sellerCommentManagementDao = (SellerCommentManagementDao) context.getBean(SellerCommentManagementDao.class.getSimpleName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 查看评论
	 * @return
	 * @throws SQLException
	 */
	@Override 
	public Map<String, Object> queryComments(Map<String, Object> map) {
		Map<String, Object> map1 = new LinkedHashMap<String, Object>();
		map1.put("code", 0);
		map1.put("msg", "");
		map.put("count", 10);
		map1.put("data","对象");
		//map1.put("status", "failed");
		try {
			List<Map<String, Object>> list = sellerCommentManagementDao.sellectComments(map);
			if (list.size()>0) {
				//map1.put("status", "success");
				map1.put("data",list);
				map1.put("count", list.size());
				}
			BaseDBUtils.closeAll();
			return map1;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return map1;
	}
	/**
	 * 删除评论
	 * @return
	 * @throws SQLException
	 */
	@Override
	public Map<String, Object> removeComments(Map<String, Object> map) {
		Map<String, Object> map1 = new LinkedHashMap<String, Object>();
		map1.put("code", 0);
		map1.put("msg", "");
		//map.put("count", );
		map1.put("data","对象");
		map1.put("status", "failed");
		try {
			BaseDBUtils.startTransaction();
			int s = sellerCommentManagementDao.updateComments(map);
			if (s>0) {
				map1.put("data",s);
				map1.put("status", "success");
			}
			BaseDBUtils.commitAndClose();
			return map1;
		} catch (SQLException e) {
			try {
				BaseDBUtils.rollbackAndClose();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return map1;
	}
	/**
	 * 商家回复评论
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	@Override
	public Map<String, Object> addSellerComments(Map<String, Object> map) {
		Map<String, Object> map1 = new LinkedHashMap<String, Object>();
		map1.put("code", 0);
		map1.put("msg", "");
		//map.put("count", );
		map1.put("data","对象");
		map1.put("status", "failed");
		try {
			BaseDBUtils.startTransaction();
			int s = sellerCommentManagementDao.insertSellerComments(map);
			if (s>0) {
				map1.put("data",s);
				map1.put("status", "success");
			}
			BaseDBUtils.commitAndClose();
			return map1;
		} catch (SQLException e) {
			try {
				BaseDBUtils.rollbackAndClose();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return map1;
	}

}
