package cn.kgc.tangcco.tcbd1017.on.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.OrderDao;
import cn.kgc.tangcco.tcbd1017.on.OrderService;
import cn.kgc.tangcco.tcbd1017.on.pojo.Order;
import cn.kgc.tangcco.tcbd1017.on.pojo.OrderGoods;
/**
 * 
 * @author 廖斌
 *
 */
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
	 *  map.put("orderPageCount",总页数) 
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
				List<OrderGoods> selectByOrderGoods = orderDaoIml.SelectByOrderGoods(map);
				int selectByOrderPageCount = orderDaoIml.SelectByOrderPageCount(map);
				int selectByOrderGoodsPageCount = orderDaoIml.SelectByOrderGoodsPageCount(map);
				map1.put("data", list) ;
				map1.put("orderGoods", selectByOrderGoods);
				map1.put("selectByOrderPageCount", selectByOrderPageCount);
				map1.put("selectByOrderGoodsPageCount", selectByOrderGoodsPageCount);
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
				 int rs = 0;
				try {
					rs = orderDaoIml.updateByOrder(map);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
			//开启事务
			BaseDBUtils.startTransaction();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		//给map设置初始值
		Map map1 = new HashMap();
		map1.put("msg", "");
		map1.put("status", "failed");
		map1.put("code", 0);
		//判断map是否为空，并且要包含两个key
		if (!map.isEmpty()&&map.containsKey("object")&&map.containsKey("data")) {
			try {
				//向数据库增加订单；
				int rs = orderDaoIml.insertByOrder(map);
				map1.put("data", rs) ;
				//如果返回结果大于0，则代表增加成功，并关闭连接
				if (rs>0) {
					map1.put("status", "success");
					BaseDBUtils.commitAndClose();
				}else {
					//否则增加失败，并回滚事务，并关闭连接
					BaseDBUtils.rollbackAndClose();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//返回增加结果
		return map1;
	}

	/**
	 *  从购物车新增订单
	 * 
	 */
	@Override
	public Map<String, Object> insertByOrderByShoppingCart(Map<String,String> map) throws SQLException {
		String json = map.get("query");
		Map map1 = (Map)JSON.parseObject(json);
		String status = (String)map1.get("status");
		if (status.equals("success")) {
			List<Map<String ,Object>> list = (ArrayList)map1.get("data");
			for (Map<String, Object> map2 : list) {
				
			}
			
		}
		return null;
	}


	

}
