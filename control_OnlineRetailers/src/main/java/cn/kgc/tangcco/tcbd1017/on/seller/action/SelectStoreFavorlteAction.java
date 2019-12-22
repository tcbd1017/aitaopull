package cn.kgc.tangcco.tcbd1017.on.seller.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import cn.kgc.tangcco.lihaozhe.commons.servlet.BaseServlet;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.seller.SelectStoreFavorlteinfoService;

/**
 * @江岛
 */
@WebServlet("/SelectStoreFavorlte.action")
public class SelectStoreFavorlteAction extends BaseServlet {

	private static final long serialVersionUID = 9199706110226455553L;
	private static SelectStoreFavorlteinfoService service;
	private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
			"ApplicationContext_on.xml");
	static {
		try {
			service = (SelectStoreFavorlteinfoService) context
					.getBean(SelectStoreFavorlteinfoService.class.getSimpleName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public SelectStoreFavorlteAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 从reques中获取前台页面传来的store_id，放在一个map中并向service传过去
	 */
	public void findStoreFavorlte(HttpServletRequest request, HttpServletResponse response, String json)
			throws ServletException, IOException {
		System.out.println("查询每月店铺被收藏次数");
		Map<String, Object> parseObject = JSON.parseObject(json, Map.class);
		Map<String, Object> map = service.findStoreFavorlte(parseObject);
		System.out.println("查询每月店铺被收藏次数向后传递参数 = " + map);
		printJson(response, map);
	}
	
	protected void findStoreFavorlteNumber(HttpServletRequest request, HttpServletResponse response ,String json)
			throws ServletException, IOException {
		System.out.println("按月份查询宝贝被收藏次数");
		Map parseObject2 = JSON.parseObject(json,Map.class);
		Map map = service.findGoodsFavoriteNumbers(parseObject2);
		printJson(response, map);
	}
	/**
	 * 
	 * @author 江岛 
	 * @Title: updateGoods 
	 * @Description: TODO(上架商品) 
	 * @param @param request
	 * @param @param response
	 * @param @param json
	 * @param @throws ServletException
	 * @param @throws IOException    设定文件 
	 * @return void    返回类型 
	 * @throws
	 */
	protected void updateGoods(HttpServletRequest request, HttpServletResponse response ,String json)
			throws ServletException, IOException {
		System.out.println("根据商品信息上架商品");
		Map parseObject2 = JSON.parseObject(json,Map.class);
		Map map = service.updateGoods(parseObject2);
		printJson(response, map);
	}

}
