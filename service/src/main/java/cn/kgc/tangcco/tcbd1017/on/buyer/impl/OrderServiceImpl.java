package cn.kgc.tangcco.tcbd1017.on.buyer.impl;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.alibaba.fastjson.JSON;
import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.lihaozhe.commons.uuid.BaseUUID;
import cn.kgc.tangcco.tcbd1017.on.buyer.OrderDao;
import cn.kgc.tangcco.tcbd1017.on.buyer.OrderService;
import cn.kgc.tangcco.tcbd1017.on.buyer.ShoppingCartDao;
import cn.kgc.tangcco.tcbd1017.on.buyer.impl.OrderDaoImpl;
import cn.kgc.tangcco.tcbd1017.on.pojo.Buyer;
import cn.kgc.tangcco.tcbd1017.on.pojo.Order;
import cn.kgc.tangcco.tcbd1017.on.pojo.OrderGoods;
/**
 * 
 * @author 廖斌
 *
 */
public class OrderServiceImpl implements OrderService{
	static OrderDao orderDaoIml = null;
	static ShoppingCartDao shoppingCartDao=null;
	static {
		ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("ApplicationContext_on.xml");
		try {
			orderDaoIml = (OrderDaoImpl)ioc.getBean("OrderDao");
			shoppingCartDao = (ShoppingCartDao)ioc.getBean("ShoppingCartDao");
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
				//查询订单信息
				Order order =(Order)map.get("data");
				map.put("buyer_id", order.getBuyer_id());
				List<Order> list = orderDaoIml.selectByOrder(map);
				//查询购物车商品信息
				 //List<Map<String ,Object>> selectByGoods = shoppingCartDao.selectShoppingCartInfoByBuyerId(map);
				 //查询订单商品图片地址
				 List selectShoppingCartInfoByBuyerIdAddUrl = orderDaoIml.selectShoppingCartInfoByBuyerIdAddUrl(map);
				//查询订单总条数
				int selectByOrderPageCount = orderDaoIml.SelectByOrderPageCount(map);
				//查询商品详情总条数
				//int selectByOrderGoodsPageCount = orderDaoIml.SelectByOrderGoodsPageCount(map);
				map1.put("data", list) ;
				//map1.put("goods", selectByGoods);
				map1.put("goodsUrl", selectShoppingCartInfoByBuyerIdAddUrl);
				map1.put("selectByOrderPageCount", selectByOrderPageCount);
				//map1.put("selectByOrderGoodsPageCount", selectByOrderGoodsPageCount);
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

	 * 	物流id未完成，用的测试数据

	 * 

	 */
	@Override
	public Map<String, Object> insertByOrderByShoppingCart(Map<String,String> map) throws SQLException {
		String json = map.get("query");

		//储存购物车信息的map
		Map map1 = (Map)JSON.parseObject(json);
		String status = (String)map1.get("status");
		//储存结果集
		Order order = new Order();
		OrderGoods orderGoods = new OrderGoods();
		if (status.equals("success")) {
			List<Map<String ,Object>> list = (List)map1.get("data");
			Iterator<Map<String, Object>> it = list.listIterator();
	    	while (it.hasNext()) {
				Map<String, Object> result = (Map<String, Object>) it.next();
				Set<String> keySet = result.keySet();
				Iterator<String> itSet = keySet.iterator();
				while (itSet.hasNext()) {
					//"取出的字段名"
					String string = (String) itSet.next();
					if (string.equals("goods_id")) {
						orderGoods.setGoods_id((int)result.get(string));
					}
					if (string.equals("buyer_id")) {
						order.setBuyer_id((int)result.get(string));
					}
					if (string.equals("seller_id")) {
						orderGoods.setSeller_id((int)result.get(string));
					}
					if (string.equals("amount_of_goods")) {
						orderGoods.setAmount_of_goods((int)result.get(string));
					}
					if (string.equals("shopping_cart_id")) {
						order.setShopping_cart_id((int)result.get(string));
					}
					if (string.equals("goods_id")) {
						order.setGoods_id((int)result.get(string));
					}
					if (string.equals("amount_of_goods")) {
						order.setAmount_of_goods((int)result.get(string));
					}
					if (string.equals("goods_uuid")) {
						order.setGoods_uuid((String)result.get(string));
					}
					if (string.equals("goods_picture_url_id")) {
						order.setGoods_picture_url_id((int)result.get(string));
					}
					if (string.equals("goods_name")) {
						order.setGoods_name((String)result.get(string));
					}
				
					if (string.equals("goods_brand")) {
						order.setGoods_brand((String)result.get(string));
					}
					if (string.equals("goods_type")) {
						order.setGoods_type((String)result.get(string));
					}
					
					if (string.equals("goods_presentation")) {
						order.setGoods_presentation((String)result.get(string));
					}
					if (string.equals("seller_id")) {
						order.setSeller_id((int)result.get(string));
					}
					if (string.equals("storage_id")) {
						order.setStorage_id((int)result.get(string));
					}
					
					
					if (string.equals("goods_width")) {
						order.setGoods_width(Double.parseDouble(result.get(string).toString()));
					}
					if (string.equals("goods_height")) {
						order.setGoods_height(Double.parseDouble(result.get(string).toString()));
					}
					if (string.equals("goods_price")) {
						order.setGoods_price((BigDecimal)result.get(string));
					}
					if (string.equals("goods_length")) {
					System.out.println("取出的数据值："+ Double.parseDouble(result.get(string).toString()) );
					order.setGoods_length(Double.parseDouble(result.get(string).toString()));
					}
					if (string.equals("goods_weight")) {
					order.setGoods_weight(Double.parseDouble(result.get(string).toString()));
					}
					
				
					
				}
			}
		}
		//设置订单信息
		order.setOrder_uuid(BaseUUID.generate());
		Date date = new Date();
		order.setOrder_create_time(date);
		order.setOrder_update_time(date);
		order.setOrder_payment_time(date);
		order.setOrder_consign_time(date);
		order.setOrder_end_time(date);
		order.setOrder_close_time(date);
		order.setOrder_status(1);
		//测试数据部分
				order.setLogistics_id(12);
				order.setOrder_payment(Double.parseDouble(order.getGoods_price().toString()));
				order.setBuyer_cash_voucher_id(1);
				order.setOrder_payment_type(1);
				order.setOrder_buyer_message("廖斌测试数据");
		Buyer buyer = new Buyer();
		buyer.setBuyer_id(order.getBuyer_id());
		//储存向数据库新增的对象信息
		Map map2= new HashMap();
		map2.put("data", order);
		map2.put("object", buyer);
		//向数据库新增一条订单
		BaseDBUtils.startTransaction();
		int i = orderDaoIml.insertByOrder(map2);
		if (i>0) {
			BaseDBUtils.commitAndClose();
		}
		System.out.println(i);
		Map map3 = new HashMap();
		map3.put("msg", "新增失败");
		map3.put("status", "failed");
		map3.put("code", 0);
		int y=0;
		BaseDBUtils.startTransaction();
		if (i>0) {
			//查询订单是否存在，并获得订单号
			List<Order> list=orderDaoIml.selectByOrder(map2);
			if (!list.isEmpty()) {
				for (Order order2 : list) {
					//设置商品订单的order_id
					orderGoods.setOrder_id(order2.getOrder_id());
					map2.put("orderGoods", orderGoods);
					//新增订单商品信息
					y=orderDaoIml.insertByOrderGoods(map2);
					break;
				}
			}
			
		}
	
		//储存向action返回结果的信息
	
		if (i>0&&y>0) {
			map3.put("msg", "新增成功");
			map3.put("status", "success");
			BaseDBUtils.commitAndClose();
		}else {
			BaseDBUtils.rollbackAndClose();
		}
		return map3;
	}



	
}
