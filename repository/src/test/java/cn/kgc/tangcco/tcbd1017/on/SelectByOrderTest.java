package cn.kgc.tangcco.tcbd1017.on;

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
import cn.kgc.tangcco.tcbd1017.on.impl.OrderDaoImpl;
import cn.kgc.tangcco.tcbd1017.on.pojo.Buyer;
import cn.kgc.tangcco.tcbd1017.on.pojo.Order;
import cn.kgc.tangcco.tcbd1017.on.pojo.Seller;

public class SelectByOrderTest {
	static OrderDao orderdao =null;
	static {
		ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("ApplicationContext_on.xml");
		try {
			orderdao = (OrderDaoImpl)ioc.getBean("OrderDao");
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
		try {
			myDate2 = dateFormat2.parse("2019-12-10 15:35:54");
			order.setOrder_payment_time(myDate2);
			map.put("data",order );
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		
		
		System.out.println(JSON.toJSON(map));
		try {
			List<Order> selectByOrder = orderdao.selectByOrder(map);
			Iterator<Order> iterator = selectByOrder.iterator();
			while (iterator.hasNext()) {
				Order order2 = (Order) iterator.next();
				System.out.println(order2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void test02() {
		Map map = new HashMap();
		Buyer buyer = new Buyer();
		buyer.setBuyer_id(1);
		map.put("object",buyer );
		
		Order order = new Order();
		DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date myDate2;
		try {
			myDate2 = dateFormat2.parse("1995-12-10 15:35:53");
			order.setOrder_payment_time(myDate2);
			map.put("data",order );
			int selectByOrder = orderdao.updateByOrder(map);
			System.out.println(selectByOrder);
		} catch (ParseException | SQLException e1) {
			e1.printStackTrace();
		}
	}
	@Test
	public void test03() {
		DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
		Map map = new HashMap();
		Date myDate2 = BaseDateUitls.parse("2019-12-10 15:35:54");
			Order order = new Order(2, "2", 2, myDate2, myDate2, 1, 1, 1, 1, 1, myDate2, myDate2, myDate2, myDate2, "123");
			map.put("data",order );
			int selectByOrder = orderdao.insertByOrder(map);
			System.out.println(selectByOrder);
		} catch (ParseException | SQLException e1) {
			e1.printStackTrace();
		}
	}
	
}
