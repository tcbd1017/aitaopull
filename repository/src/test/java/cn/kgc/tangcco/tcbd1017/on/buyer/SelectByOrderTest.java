package cn.kgc.tangcco.tcbd1017.on.buyer;

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

import cn.kgc.tangcco.lihaozhe.commons.date.BaseDateUitls;
import cn.kgc.tangcco.lihaozhe.commons.jdbc.PageRang;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.buyer.impl.OrderDaoImpl;
import cn.kgc.tangcco.tcbd1017.on.pojo.Buyer;
import cn.kgc.tangcco.tcbd1017.on.pojo.Order;
import cn.kgc.tangcco.tcbd1017.on.pojo.OrderGoods;
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
		PageRang pr = new PageRang(1,5);
		map.put("pr", pr);
		
//		Seller seller = new Seller();
//		seller.setSeller_id(1);
//		map.put("object",seller );
		
		Buyer buyer = new Buyer();
		buyer.setBuyer_id(1);
		map.put("object", buyer);
		
		Order order = new Order();
		order.setBuyer_id(1);;
		map.put("data",order );
		
		
		System.out.println("测试数据："+JSON.toJSON(map));
		try {
			List<Order> selectByOrder = orderdao.selectByOrder(map);
			System.out.println("数组大小："+selectByOrder.size());
			Iterator<Order> iterator = selectByOrder.iterator();
			while (iterator.hasNext()) {
				Order order2 = (Order) iterator.next();
				int i=0;
				
				System.out.println("第"+i+"次循环："+order2);
				i++;
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void test03() {
		DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
		Map map = new HashMap();
		Date myDate2 = BaseDateUitls.parse("2019-12-10 15:35:54");
			Order order = new Order();
			map.put("data",order );
			int selectByOrder = orderdao.insertByOrder(map);
			System.out.println(selectByOrder);
		} catch (ParseException | SQLException e1) {
			e1.printStackTrace();
		}
	}
	@Test
	public void Test() {
		Map map = new HashMap();
		Order order = new Order();
		order.setOrder_id(1);
		
		try {
			List<OrderGoods> list = orderdao.SelectByOrderGoods(map);
			System.out.println(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 *  返回订单分页总 页数测试
	 * 
	 */
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
			int i=orderdao.SelectByOrderPageCount(map);
			System.out.println(i);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 *  测试订单插入功能
	 * 
	 */
	
	
}
