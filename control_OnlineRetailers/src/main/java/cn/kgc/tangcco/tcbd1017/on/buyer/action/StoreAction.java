package cn.kgc.tangcco.tcbd1017.on.buyer.action;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import cn.kgc.tangcco.lihaozhe.commons.servlet.BaseServlet;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.buyer.StoreFavoriteService;

/**
 * @author 和朋朋
 * 	@version 1.0<br>
 * 	创建时间：  2019年12月9日 下午11:01:57 <br>
 * 	类描述：action层
 * Servlet implementation class StoreFavorite
 */
@WebServlet("/StoreFavorite.action")
public class StoreAction extends BaseServlet {
	private static StoreFavoriteService ss;
	private static ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("ApplicationContext_on.xml");
	static {
		try {
			ss=(StoreFavoriteService) context.getBean(StoreFavoriteService.class.getSimpleName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 3622779785449267090L;

		
	/**
	 *从页面获取分页页数，分页记录数，买家id
	 * 将返回数据向页面输出
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	public void queryAllStoreFavorite(HttpServletRequest request, HttpServletResponse response,String json) throws ServletException, IOException {
		
		
		Map map=JSON.parseObject(json,Map.class);
		System.out.println(map.get("store_name").toString());
//		System.out.println(map.get("buyer_id"));
//		//获取分页页数
//		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
//		//获取分页记录数
//		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
//		//获取买家id
//		int buyer_id = Integer.parseInt(request.getParameter("buyer_id"));
//		Map<String, Object>map=new HashMap<String, Object>();
//		//向map中增加分页
//		map.put("pr", new PageRang(pageNumber, pageSize));
//		//向map中添加买家id
//		map.put("buyer_id", buyer_id);
		Map queryStoreFavorite = ss.queryStoreFavorite(map);
		// 将返回数据向页面输出
		printJson(response, queryStoreFavorite);
	}
	/**
	 * 从页面获取买家id，店铺id，店铺创建id，店铺修改时间
	 * 将返回数据向页面输出
	 * @param request
	 * @param response
	 */
	public void addStoreFavorite(HttpServletRequest request, HttpServletResponse response,String json)throws ServletException, IOException {
		Map map=JSON.parseObject(json,Map.class);
		System.out.println("buyer_id"+map.get("buyer_id"));
		System.out.println("store_id"+map.get("store_id"));
		System.out.println("store_favorite_create_time"+map.get("store_favorite_create_time"));
		System.out.println("store_favorite_update_time"+map.get("store_favorite_update_time"));
//		//获取买家id
//		int buyer_id = Integer.parseInt(request.getParameter("buyer_id"));
//		//获取店铺id
//		int store_id = Integer.parseInt(request.getParameter("store_id"));
//		//获取创建店铺时间
//		Date store_favorite_create_time = BaseDate.getDate(request.getParameter("store_favorite_create_time"));
//		//获取店铺修改时间
//		Date store_favorite_update_time = BaseDate.getDate(request.getParameter("store_favorite_update_time"));
		
		Map<String, Object> addStoreFavorite = ss.addStoreFavorite(map);
		// 将返回数据向页面输出
		printJson(response, addStoreFavorite);
	}
	/**
	 * 从页面获取收藏店铺id
	 * 将返回数据向页面输出
	 * @param request
	 * @param response
	 */
	public void removeStoreFavorite(HttpServletRequest request, HttpServletResponse response,String json)throws ServletException, IOException {
		Map map=JSON.parseObject(json,Map.class);
		System.out.println("store_favorite_id"+map.get("store_favorite_id"));
//		//获取收藏店铺id
//		int store_favorite_id = Integer.parseInt(request.getParameter("store_favorite_id"));
		Map<String, Object> removeStoreFavorite = ss.removeStoreFavorite(map);
		printJson(response, removeStoreFavorite);
	}
}
