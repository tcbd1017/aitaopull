package cn.kgc.tangcco.tcbd1017.on.seller.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import cn.kgc.tangcco.lihaozhe.commons.servlet.BaseServlet;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.seller.SelectStorePendinginfoService;
import cn.kgc.tangcco.tcbd1017.on.seller.impl.SelectStorePendinginfoServiceImpl;

/**
 * Servlet implementation class SelectStorePendingAction
 */
@WebServlet("/SelectStorePendingAction.action")
public class SelectStorePendingAction extends BaseServlet {

	private static final long serialVersionUID = 1941790783398387888L;
	private static SelectStorePendinginfoService service;
	private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
			"ApplicationContext_on.xml");
	static {
		try {
			service = (SelectStorePendinginfoService) context
					.getBean(SelectStorePendinginfoService.class.getSimpleName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response) 根据 seller_id查询本店铺的待发货订单数量
	 */

	public void findStorePending(HttpServletRequest request, HttpServletResponse response, String json)
			throws ServletException, IOException {
		System.out.println("查询待发货数量");
		Map<String , Object> parseObject = JSON.parseObject(json, Map.class);
		Map<String , Object> map = service.findStorePending(parseObject);
		printJson(response, map);
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response) 根据 seller_id查询本店铺的已付款货订单数量
	 */

	public void findStorePaid(HttpServletRequest request, HttpServletResponse response, String json)
			throws ServletException, IOException {
		System.out.println("查询已付款订单数量");
		Map<String, Object> parseObject = JSON.parseObject(json, Map.class);
		Map<String, Object> map = service.findStore(parseObject);
		printJson(response, map);
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response) 根据 seller_id查询本店铺的待发货订单数量和已付款订单数量
	 */
	public void findStore(HttpServletRequest request, HttpServletResponse response, String json)
			throws ServletException, IOException {
		System.out.println("根据 seller_id查询本店铺的待发货订单数量和已付款订单数量，店铺的信誉度 ");
		Map<String, Object> parseObject = JSON.parseObject(json, Map.class);
		Map<String, Object> map = service.findStore(parseObject);
		printJson(response, map);
	}
	
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response) 根据 seller_id查询本店铺的待发货订单数量和已付款订单数量
	 */
	public void findGoods(HttpServletRequest request, HttpServletResponse response, String json)
			throws ServletException, IOException {
		System.out.println("根据 seller_id查询本店铺的所有已上架商品 ");
		Map<String, Object> parseObject = JSON.parseObject(json, Map.class);
		System.out.println("分页模糊查询 = "+parseObject);
		Map<String, Object> map = service.findGoodsService(parseObject);
		//{limit=5, page=1, seller_id=1}
		printJson(response, map);
	}
}
