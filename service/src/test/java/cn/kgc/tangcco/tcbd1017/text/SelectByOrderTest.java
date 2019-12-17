package cn.kgc.tangcco.tcbd1017.text;

import java.sql.SQLException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mysql.fabric.xmlrpc.base.Data;

import cn.kgc.tangcco.lihaozhe.commons.date.BaseDateUitls;
import cn.kgc.tangcco.lihaozhe.commons.jdbc.PageRang;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.OrderService;
import cn.kgc.tangcco.tcbd1017.on.impl.OrderServiceImpl;
import cn.kgc.tangcco.tcbd1017.on.pojo.Buyer;
import cn.kgc.tangcco.tcbd1017.on.pojo.Order;
import cn.kgc.tangcco.tcbd1017.on.pojo.Seller;
import cn.kgc.tangcco.tcbd1017.on.pojo.ShoppingCart;

/**
 * 
 * @author 廖斌
 * @
 *
 */

public class SelectByOrderTest {
	static OrderService orderService =null;
	static {
		 ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("ApplicationContext_on.xml");
		 try {
			 orderService =(OrderServiceImpl)ioc.getBean("OrderService");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}  
	 
	@Test
	public void test01() { 
		Map map = new HashMap();
		//分页信息
		PageRang pr = new PageRang(1,5);
		map.put("pr", pr);
		
//		Seller seller = new Seller();
//		seller.setSeller_id(1);
//		map.put("object",seller );
		
		Buyer buyer = new Buyer();
		buyer.setBuyer_id(1);
		map.put("object", buyer);
		
		//订单信息
		Order order = new Order();
		order.setBuyer_id(1);
		order.setOrder_id(1);
		map.put("data",order );
		System.out.println(JSON.toJSON(map));
		
		try {
			map =orderService.selectByOrder(map);
			//System.out.println(JSON.toJSON(map));
			System.out.println("map打印："+map);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void test03() {
		
		Map map = new HashMap();
		Buyer buyer = new Buyer();
		buyer.setBuyer_id(1);
		map.put("object",buyer );
		
		Order order = new Order();
		DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date myDate2;
		try {
			myDate2 = dateFormat2.parse("1996-12-10 15:35:53");
			order.setOrder_payment_time(myDate2);
			map.put("data",order );
			System.out.println(JSON.toJSON(map));
			Map map1 = orderService.updateByOrder(map);
			System.out.println(map1.get("data"));
		} catch (ParseException | SQLException e1) {
			e1.printStackTrace();
		}
	}
	@Test
	public void Test04() {
		Map map = new HashMap();
		PageRang pr = new PageRang(1,5);
		map.put("pr", pr);
		
//		Seller seller = new Seller();
//		seller.setSeller_id(1);
//		map.put("object",seller );
		
		Buyer buyer = new Buyer();
		buyer.setBuyer_id(1);
		map.put("object", buyer);
		
		Order order = new Order();
		order.setBuyer_id(1);
		order.setOrder_id(1);
		map.put("data",order );
		
		try {
			map=orderService.selectByOrder(map);
			System.out.println(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void cteateJson() {
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.setBuyer_id(1);
		shoppingCart.setGoods_id(100);
		System.out.println(JSON.toJSON(shoppingCart));
	}
	

	/**
	 *  测试从购物车新增订单
	 * 
	 */
	@Test
	public void test05() {
		String json = "{\"query\": \"{\\\"msg\\\":\\\"查询成功\\\",\\\"data\\\":[{\\\"goods_status\\\":2,\\\"goods_name\\\":\\\"苹果\\\",\\\"goods_picture_url_id\\\":1,\\\"goods_price\\\":3.00,\\\"amount_of_goods\\\":9999,\\\"goods_id\\\":1,\\\"buyer_id\\\":1,\\\"goods_update_time\\\":1580370512000,\\\"shopping_cart_create_time\\\":1576226795000,\\\"goods_length\\\":44.0,\\\"goods_height\\\":44.0,\\\"shopping_cart_status\\\":2,\\\"goods_width\\\":66.0,\\\"shopping_cart_update_time\\\":1576463160000,\\\"goods_presentation\\\":\\\"44\\\",\\\"goods_type\\\":\\\"33\\\",\\\"shopping_cart_id\\\":1,\\\"goods_uuid\\\":\\\"1\\\",\\\"goods_create_time\\\":1575964109000,\\\"goods_brand\\\":\\\"33\\\",\\\"seller_id\\\":1,\\\"goods_weight\\\":44.0},{\\\"goods_status\\\":2,\\\"goods_name\\\":\\\"酷狗蓝牙耳机\\\",\\\"goods_picture_url_id\\\":333,\\\"goods_price\\\":199.00,\\\"amount_of_goods\\\":103,\\\"goods_id\\\":3,\\\"buyer_id\\\":1,\\\"goods_update_time\\\":1575442736000,\\\"shopping_cart_create_time\\\":1576651104000,\\\"shopping_cart_status\\\":2,\\\"shopping_cart_update_time\\\":1576399091000,\\\"goods_presentation\\\":\\\"好用的蓝牙耳机\\\",\\\"goods_type\\\":\\\"耳机\\\",\\\"shopping_cart_id\\\":4,\\\"goods_uuid\\\":\\\"333\\\",\\\"goods_create_time\\\":1575529132000,\\\"goods_brand\\\":\\\"酷狗\\\",\\\"seller_id\\\":4,\\\"goods_weight\\\":400.0},{\\\"goods_status\\\":2,\\\"goods_name\\\":\\\"华为平板\\\",\\\"goods_picture_url_id\\\":444,\\\"goods_price\\\":2999.00,\\\"amount_of_goods\\\":101,\\\"goods_id\\\":4,\\\"buyer_id\\\":1,\\\"goods_update_time\\\":1576306833000,\\\"shopping_cart_create_time\\\":1576393756000,\\\"shopping_cart_status\\\":2,\\\"shopping_cart_update_time\\\":1576398723000,\\\"goods_presentation\\\":\\\"爱国牌平板\\\",\\\"goods_type\\\":\\\"平板电脑\\\",\\\"shopping_cart_id\\\":6,\\\"goods_uuid\\\":\\\"444\\\",\\\"goods_create_time\\\":1575961228000,\\\"goods_brand\\\":\\\"华为\\\",\\\"seller_id\\\":1,\\\"goods_weight\\\":20.0}],\\\"count\\\":3,\\\"status\\\":\\\"success\\\"}\"}";
		Map map = (Map)JSON.parseObject(json);
		try {
			map=orderService.insertByOrderByShoppingCart(map);
			System.out.println(map.get("status"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
