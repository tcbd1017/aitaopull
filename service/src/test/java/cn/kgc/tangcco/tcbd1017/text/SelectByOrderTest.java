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

import com.mysql.fabric.xmlrpc.base.Data;

import cn.kgc.tangcco.lihaozhe.commons.date.BaseDateUitls;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.OrderService;
import cn.kgc.tangcco.tcbd1017.on.impl.OrderServiceImpl;
import cn.kgc.tangcco.tcbd1017.on.pojo.Buyer;
import cn.kgc.tangcco.tcbd1017.on.pojo.Order;
import cn.kgc.tangcco.tcbd1017.on.pojo.Seller;

/**
 * 
 * @author Administrator
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
		Seller seller = new Seller();
		seller.setSeller_id(1);
		map.put("object",seller );
		Order order = new Order();
		DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date myDate2;
		order.setOrder_buyer_message("2");
		map.put("data",order );
		
		try {
			
			System.out.println();
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
}
