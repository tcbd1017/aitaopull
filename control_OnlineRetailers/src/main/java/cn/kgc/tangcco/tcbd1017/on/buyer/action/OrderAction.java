package cn.kgc.tangcco.tcbd1017.on.buyer.action;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.alibaba.fastjson.JSON;
import cn.kgc.tangcco.lihaozhe.commons.jdbc.PageRang;
import cn.kgc.tangcco.lihaozhe.commons.propertis.BaseProperties;
import cn.kgc.tangcco.lihaozhe.commons.servlet.BaseServlet;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.lihaozhe.commons.uuid.BaseUUID;
import cn.kgc.tangcco.tcbd1017.on.buyer.impl.OrderServiceImpl;
import cn.kgc.tangcco.tcbd1017.on.pojo.Buyer;
import cn.kgc.tangcco.tcbd1017.on.pojo.Order;
import cn.kgc.tangcco.tcbd1017.on.pojo.Seller;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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
	 * 			key:"sellerId、buyerid", 储存查询角色信息，比如Seller_Id、Buyer_Id，以Seller或Buyer对象储存
	 * 			key:"order", 储存订单查询信息，比如Order_Id、order_update_time等等，以Order对象储存；
	 * 			key:"pr", 储存分页信息，以PageRang对象进行储存
	 * 		模板 ：{"buyerId":1,"prPage":1,"prSize":5,"enableFuzzySelect":0} 

	 * 	
	 * 		整个Map转成json, 以AJAX的试向后台发送	
	 * --------------------------------------------
	 * @param request
	 * @param response
	 * @param string
	 */
	public void selectByOrder(HttpServletRequest request , HttpServletResponse response, String string) {
		
//		HttpSession session = request.getSession();
//		session.setAttribute("key1", "value1");
//		String id = session.getId();
//		System.out.println(id);
//		
//		Cookie cookie = new Cookie("key1", "value1");
//		cookie.setPath("/");
//		cookie.setSecure(false);
//		response.addCookie(cookie);
		//接收值
		System.out.println("前台给的数据："+string);
		//向持久层输送的数据map
		Map map =JSON.parseObject(string,Map.class);
		
		if (map.containsKey("sellerId")) {
			Seller seller = new Seller();
			seller.setSeller_id((int)map.get("sellerId"));
			map.put("object", seller);
		}else if(map.containsKey("buyerId")){
			Buyer buyer = new Buyer();
			buyer.setBuyer_id((int)map.get("buyerId"));
			map.put("object", buyer);
		}
		//解析order
		if (map.containsKey("buyerId")) {
		Order order = new Order();
		order.setBuyer_id((int)map.get("buyerId"));
			if (map.containsKey("orderStatus")) {
				order.setOrder_status((int)map.get("orderStatus"));
			}
		map.put("data", order);
		}
		//解析数组order订单号
		if (map.containsKey("chk_value")) {
			ArrayList list = new ArrayList();
			String  chk_value= (String) map.get("chk_value");
			//获得第一个点的位置
			int  indexOf = chk_value.indexOf(",");
			//获得第一个字符串  0,2
			
			if (chk_value.contains(",")) {
				
			
			list.add(Integer.parseInt((chk_value.substring(0,indexOf)).toString()));
			for (int i = 0; i < chk_value.length(); i++) {
				if (indexOf>=chk_value.lastIndexOf(",")) {
					list.add(Integer.parseInt(chk_value.substring(indexOf+1).toString()));
					break;
				}
					//获得第二个点开始的位置 4
					int index = indexOf+1;
					indexOf = chk_value.indexOf(",",indexOf+1);
					//获取第二个位置   
					list.add(Integer.parseInt(chk_value.substring(index,indexOf).toString()));
					
			}
			}else {
				list.add(Integer.parseInt(chk_value));
			}
			map.put("orderIdList", list);
		}
		
		
		//解析分页信息
		if (map.containsKey("prPage")) {
			PageRang pr = new PageRang();
			int pageNumbet=Integer.parseInt(map.get("prPage").toString()) ;
			if (pageNumbet<1) {
				pageNumbet=1;
			}
			pr.setPageNumber(pageNumbet);
			pr.setPageSize((int)map.get("prSize"));
			map.put("pr", pr);
		}
		
		//解析各状态的值；
		if (map.containsKey("allOrderStatus")) {
			Map allOrderStatus = JSON.parseObject(map.get("allOrderStatus").toString(),Map.class);
			map.put("allOrderStatus", allOrderStatus);
		}
		
		 Map map1 = new HashMap();
		 
		 if (map!=null&&map.size()>0) {
			 //处理值；
			 
			 map1=orderServiceImpl.selectByOrder(map);
		}
		 //响应值
		 System.out.println("响应的值"+map1);
		printJson(response, map1);
	}
	
	
	/**
	 * 
	 *  更新订单表
	 * @param request
	 * @param response
	 * @param string
	 * 	请求数据模板 ：{"buyerId":1,"orderStatus":8,"orderId":99}
	 */
	public void updateByOrder(HttpServletRequest request , HttpServletResponse response, String string) {
		//接收值
				Map map =JSON.parseObject(string,Map.class);
				System.out.println("接收到的值："+string);
				
				//获得修改对象
				Buyer buyer = new Buyer();
				if (map.containsKey("buyerId")) {
					
					buyer.setBuyer_id((int)map.get("buyerId"));
					map.put("object", buyer);
				}
				
				//解析数组order订单号
				Order order = new Order();
				if (map.containsKey("chk_value")) {
					order.setOrder_id(Integer.parseInt((map.get("chk_value").toString())));
					System.out.println("订单号："+order.getOrder_id());
				}
				
				//解析order，获得修改订单信息
				if (map.containsKey("buyerId")&&map.containsKey("orderStatus")) {
					order.setOrder_status(Integer.parseInt(map.get("orderStatus").toString()));
					System.out.println("要修改的订单状态号："+order.getOrder_status());
				}
				
				
				
				//解析order，获得修改订单信息
				if (map.containsKey("orderId")&&map.containsKey("orderStatus")) {
					order.setOrder_status((int)map.get("orderStatus"));
					order.setOrder_id((int)map.get("orderId"));
				}
				map.put("data", order);
				
				
				
				 Map map1 = new HashMap();
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
	 *  通过购物车，联动新增订单，则调用此接口
	 *  前台给后台格式:
	 * 	 Map :
	 * 			key:"shopping", 至少需要包含买家id，购物车id
	 * 	模板:{"goodsId":3,"buyerId":1,"enableFuzzySelect":0,"goodsName":"夏普"} 
	 *   必须属性： goodsId,buyerId,goodsName,enableFuzzySelect
	 *  
	 */
	public void insertByOrderByShoppingCart(HttpServletRequest request , HttpServletResponse response, String string) {
		//配置其他模块接口服务器地址；
		String ipAddress = BaseProperties.getProperties("/IpAddress.properties", "address");
		System.out.println("前台给的数据："+string);
		//接收购物车订单号
		//key:goodsId、buyerId、goodsName
		//调用杜明action接口，获得购物车信息
		OkHttpClient client = new OkHttpClient();
		//杜明接口地址： 根据Buyer_id返回购物车信息
		String url0 = ipAddress+"control_OnlineRetailers/shoppingCart.action?methodName=queryAllShoppingCartInfoByBuyerId";
		//标明接口地址：根据BuyerId和GoodsId,删除物品
		String url1 = ipAddress+"control_OnlineRetailers/shoppingCart.action?methodName=removeShoppingCart";
		System.out.println("购物车接口地址："+url0);
		//获得请求体
		 final MediaType json=MediaType.parse("application/json; charset=utf-8");
		 RequestBody queryBody = RequestBody.create(json, string );
		 RequestBody removeBody = RequestBody.create(json, string);
		//查询购物车http请求
		Request queryCart = new Request.Builder().url(url0).post(queryBody).build();
		//删除购物车http请求 
		Request removeCart = new Request.Builder().url(url1).post(removeBody).build();
		Response queryResponse = null;
		Response removeResponse = null;
		try {
			//获取查询购物车信息响应
			 queryResponse = client.newCall(queryCart).execute();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//解析响应,并获得响应数据；
		String queryRs=null;
		String removeRs=null;
		Map<String, Object> map3=new HashMap();
		map3.put("status", "failed");
			try {
				queryRs=queryResponse.body().string();
			
			System.out.println("查询购物车的响应体的字符串："+queryRs);
			//储存查询响应的数据
			Map<String,String> map2=new HashMap();
			map2.put("query", queryRs);
			if (queryRs.contains("success")) {
			//调用service,添加订单,返回订单后的结果；
			map3 = orderServiceImpl.insertByOrderByShoppingCart(map2);
			//获取删除购物车信息响应 
			}
			if (map3.get("status").equals("success")) {
				 removeResponse = client.newCall(removeCart).execute();
					//获取购物车删除信息； 
					 removeRs=removeResponse.body().string();
					if (removeRs.contains("success")) {
						printJson(response,removeRs);
					}else {
						printJson(response,removeRs);
					}
			}else {
				printJson(response, removeRs);
			}
			} catch (IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	

	/**
	 * 
	 *  	直接 新增订单表 ，调用此接口
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
					 //输出是否增加成功
					 String status = (String)map1.get("status");
					 System.out.println(status);
				}
				 //响应值
				printJson(response, map1);
	}
	
	
	/**
	 *  	查询地址
	 * 
	 */
	public void selectAddressIfo(HttpServletRequest request , HttpServletResponse response, String string) {
		System.out.println("请求地址成功；");
		Map map =JSON.parseObject(string,Map.class);
		try {
			Map selectAddress = orderServiceImpl.selectAddress(map);
			printJson(response, selectAddress);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 *  FTP接口 
	 * 
	 */
	public void upLoad(HttpServletRequest request , HttpServletResponse response) {
		//文件流
		InputStream inputStream=null;
		//获取系统临时储存文件夹的名字
		String property = System.getProperty("java.io.tmpdir");
		//如果request域中存在多媒体文件类型
	if( ServletFileUpload.isMultipartContent(request)) {
		//文件项工厂对象
		DiskFileItemFactory factory = new DiskFileItemFactory();    
		//设置文件的最大值
		factory.setSizeThreshold(100000000);
		//当文件大于50m时，以临时文件，储存在系统临时文件夹中；
		factory.setRepository(new File(property));
		
		//创建一个上传文件的实例对象
		ServletFileUpload servletFileUpload = new ServletFileUpload(factory);
		//设置上传文件对象；
		servletFileUpload.setFileSizeMax(100000000);
		servletFileUpload.setSizeMax(100000000);
		servletFileUpload.setHeaderEncoding("UTF-8");
		
		
		List<FileItem> list = null;
	
		try {
			//获得上传的文件流
			 list = servletFileUpload.parseRequest(request);
			 for (FileItem fileItem : list) {
				 //获得上传文件的流
				 inputStream = fileItem.getInputStream();
				 System.out.println("获得的文件流"+fileItem);
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
		FTPClient ftpClient = new FTPClient();
		try {
				//ftp服务器地址
				ftpClient.connect("v0.ftp.upyun.com");
				//ftp服务器授权码,及登陆ftp
				boolean login = ftpClient.login("tcbd1017/tcbd1017", "UEN2ZQre2befz2wx8CDfzTqS0a5IhjRe");
				  if(!login){
		                System.out.println("FTP登陆失败");
		            }else {
						//登陆成功
		            	  //设置缓冲大小
		                ftpClient.setBufferSize(2048);
		                ftpClient.setControlEncoding("UTF-8");
		                ftpClient.setFileType(ftpClient.BINARY_FILE_TYPE);
		                //上传文件
		                String generate = BaseUUID.generate();
		                ftpClient.storeFile(generate+".jpg",inputStream);
		                //远程URL图片地址；
		                System.out.println("远程图片访问地址："+"http://tcbd1017.test.upcdn.net/"+generate+".jpg");
		            	
				}
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		
	}
		
	
	
}
