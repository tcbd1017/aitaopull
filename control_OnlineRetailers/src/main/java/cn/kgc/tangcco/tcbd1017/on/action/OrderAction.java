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
	 * 	data ： dao层返回的数据
	 * 	status : 查询是否成功信息
	 * 	msg : 后台给的其他信息
	 * 	传进来的json要求：包含角色对象，包含order订单查询信息对象；
	 * @param request
	 * @param response
	 * 	@return 返回一个map，key:data，格式:list，list中包含order对象；
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
		 Map map1 = null;
		 
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
	
}
