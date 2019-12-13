package cn.kgc.tangcco.tcbd1017.on.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.OrderDao;
import cn.kgc.tangcco.tcbd1017.on.OrderService;
import cn.kgc.tangcco.tcbd1017.on.pojo.Order;

public class OrderServiceImpl implements OrderService{
	static OrderDao orderDaoIml = null;
	static {
		ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("ApplicationContext_on.xml");
		try {
			orderDaoIml = (OrderDaoImpl)ioc.getBean("OrderDao");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * @return List<Order>
	 * 	查询订单  格式
	 *  map.put("object","查询角色")；
	 *  map.put("data","返回数据");
	 *	map.put("status", "failed");
	 *	map.put("msg", "");
	 *	map.put("code", 0);
	 * @throws SQLException 
	 * @return Map
	 */
	@Override
	public Map<String,Object> selectByOrder(Map<String,Object> map)  {
		//获得返回的数据list集合，只有包含object和包含data才返回数据
		Map map1 = new LinkedHashMap();
		map1.put("msg", "null");
		map1.put("status", "failed");
		map1.put("code", 0);
		if (!map.isEmpty()&&map.containsKey("object")&&map.containsKey("data")) {
			try {
				List<Order> list = orderDaoIml.selectByOrder(map);
				map1.put("data", list) ;
			} catch (SQLException e) {
				e.printStackTrace();
			}
			map1.put("status", "success");
		}
		return map1;
	}
	
	
	/**
	 * @return int
	 * 	修改订单，删除订单
	 * 	查询订单  格式 	
	 *  map.put("object","查询角色")；
	 *  map.put("data","返回数据");
	 *	map.put("status", "failed");
	 *	map.put("msg", "");
	 *	map.put("code", 0);
	 */
	@Override
	public Map<String,Object> updateByOrder(Map<String,Object> map)  {
		try {
			BaseDBUtils.startTransaction();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		Map map1 = new HashMap();
		map1.put("msg", "");
		map1.put("status", "failed");
		map1.put("code", 0);
		if (!map.isEmpty()&&map.containsKey("object")&&map.containsKey("data")) {
			try {
				 int rs = orderDaoIml.updateByOrder(map);
				map1.put("data",rs );
				if (rs>0) {
					map1.put("status", "success");
					BaseDBUtils.commitAndClose();
				}else {
					BaseDBUtils.rollbackAndClose();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return map1;
	}
	
	
	/**
	 * @return int
	 * 	新增订单
	 */
	@Override
	public Map<String,Object> insertByOrder(Map<String,Object> map)   {
		try {
			BaseDBUtils.startTransaction();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		Map map1 = new HashMap();
		map1.put("msg", "");
		map1.put("status", "failed");
		map1.put("code", 0);
		if (!map.isEmpty()&&map.containsKey("object")&&map.containsKey("data")) {
			try {
				int rs = orderDaoIml.insertByOrder(map);
				map1.put("data", rs) ;
				if (rs>0) {
					map1.put("status", "success");
					BaseDBUtils.commitAndClose();
				}else {
					BaseDBUtils.rollbackAndClose();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			map1.put("status", "success");
		}
		return map1;
	}

}
