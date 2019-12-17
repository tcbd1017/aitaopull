package cn.kgc.tangcco.tcbd1017.on.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.PageRang;
import cn.kgc.tangcco.lihaozhe.commons.servlet.BaseServlet;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.OrderDao;
import cn.kgc.tangcco.tcbd1017.on.impl.OrderDaoImpl;
import cn.kgc.tangcco.tcbd1017.on.impl.OrderServiceImpl;
import cn.kgc.tangcco.tcbd1017.on.pojo.Buyer;
import cn.kgc.tangcco.tcbd1017.on.pojo.Order;
import cn.kgc.tangcco.tcbd1017.on.pojo.Seller;

/**
* @author 作者 : 廖斌
* @version 创建时间：Dec 13, 2019 9:46:07 AM
* 	
*/
@WebServlet(urlPatterns = "/Order.action")
public class OrderAction extends BaseServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2501440925281808854L;
	static OrderServiceImpl orderServiceImpl =null;
	static {
		ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("ApplicationContext_on.xml");
		try {
			orderServiceImpl = (OrderServiceImpl)ioc.getBean("OrderService");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 *  			Internet Describe
	 * --------------------------------------------
	 *  后台返回给前台的数据使用说明：
	 *  	Map :
	 *  		key:"data",	需要查询的订单信息，以Order对象储存
	 * 			key:"orderGoods", 订单关联表，订单详细商品信息，以OrderGoods对象储存
	 * 			key:"selectByOrderPageCount" , 订单表查询信息总页数（总条数）
	 * 			key:"selectByOrderGoodsPageCount", 订单表关联表，订单详细商品信息总页数 （总条数）
	 * 		整个Map转成json,以Request域的方式，向前台发送
	 * --------------------------------------------
	 * 	前台给后台的数据要求说明：
	 * 		Map:
	 * 			key:"object", 储存查询角色信息，比如Seller_Id、Buyer_Id，以Seller或Buyer对象储存
	 * 			key:"data", 储存订单查询信息，比如Order_Id、order_update_time等等，以Order对象储存；
	 * 			key:"pr", 储存分页信息，以PageRang对象进行储存 
	 * 		整个Map转成json, 以AJAX的试向后台发送	
	 * --------------------------------------------
	 * @param request
	 * @param response
	 * @param string
	 */
	public void selectByOrder(HttpServletRequest request , HttpServletResponse response, String string) {
		//接收值
		Map map =JSON.parseObject(string,Map.class);
		if (map.get("object").toString().contains("seller")) {
			Seller seller = JSON.parseObject(map.get("object").toString(),Seller.class);
			map.put("object", seller);
		}else if(map.get("object").toString().contains("buyer")){
			Buyer buyer = JSON.parseObject(map.get("object").toString(),Buyer.class);
			map.put("object", buyer);
		}
		//解析order
		Order order = JSON.parseObject(map.get("data").toString(),Order.class);
		map.put("data", order);
		//解析分页信息
		PageRang pr = JSON.parseObject(map.get("pr").toString(),PageRang.class);
		map.put("pr", pr);
		 Map map1 = new HashMap();
		 
		 if (map!=null&&map.size()>0) {
			 //处理值；
			 map1=orderServiceImpl.selectByOrder(map);
		}
		 //响应值
		printJson(response, map1);
	}
	
	
	/**
	 * 
	 *  更新订单表
	 * @param request
	 * @param response
	 * @param string
	 */
	public void updateByOrder(HttpServletRequest request , HttpServletResponse response, String string) {
		//接收值
				Map map =JSON.parseObject(string,Map.class);
				if (map.get("object").toString().contains("seller")) {
					Seller seller = JSON.parseObject(map.get("object").toString(),Seller.class);
					map.put("object", seller);
				}else if(map.get("object").toString().contains("buyer")){
					Buyer buyer = JSON.parseObject(map.get("object").toString(),Buyer.class);
					map.put("object", buyer);
				}
				//解析order
				Order order = JSON.parseObject(map.get("data").toString(),Order.class);
				map.put("data", order);
				 Map map1 = null;
				 
				 if (map!=null&&map.size()>0) {
					 //处理值；
					 map1=orderServiceImpl.updateByOrder(map);
					 String status = (String)map1.get("status");
					 System.out.println(status);
				}
				 //响应值
				printJson(response, map1);
		
	}
	

	/**
	 * 
	 *  	新增订单表
	 * @param request
	 * @param response
	 * @param string
	 */
	public void insertByOrder(HttpServletRequest request , HttpServletResponse response, String string) {
		//接收值
				Map map =JSON.parseObject(string,Map.class);
				if (map.get("object").toString().contains("seller")) {
					Seller seller = JSON.parseObject(map.get("object").toString(),Seller.class);
					map.put("object", seller);
				}else if(map.get("object").toString().contains("buyer")){
					Buyer buyer = JSON.parseObject(map.get("object").toString(),Buyer.class);
					map.put("object", buyer);
				}
				//解析order
				Order order = JSON.parseObject(map.get("data").toString(),Order.class);
				map.put("data", order);
				 Map map1 = null;
				 
				 if (map!=null&&map.size()>0) {
					 //处理值；
					 map1=orderServiceImpl.insertByOrder(map);
					 String status = (String)map1.get("status");
					 System.out.println(status);
				}
				 //响应值
				printJson(response, map1);
		
	}
	
}
