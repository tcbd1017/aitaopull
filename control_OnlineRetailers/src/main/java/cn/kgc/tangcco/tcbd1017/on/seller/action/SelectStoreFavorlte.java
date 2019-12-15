package cn.kgc.tangcco.tcbd1017.on.seller.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.kgc.tangcco.lihaozhe.commons.servlet.BaseServlet;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.seller.SelectStoreFavorlteinfoService;



/**
 * @江岛
 */
@WebServlet("/SelectStoreFavorlte.action")
public class SelectStoreFavorlte extends BaseServlet {

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

	public SelectStoreFavorlte() {
		super();
		// TODO Auto-generated constructor stub
	}

	Map<String, Object> map = new HashMap<String, Object>();
	Map<String, Object> maps = new HashMap<String, Object>();

	/**
	 * 从reques中获取前台页面传来的store_id，放在一个map中并向service传过去
	 */
	protected void findStoreFavorlte(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("查询店铺收藏次数");
		String store_id = request.getParameter("store_id");
		String store_favorite_create_time = request.getParameter("store_favorite_create_time");
		String store_favorite_update_time = request.getParameter("store_favorite_update_time");
		map.put("store_id", store_id);
		if (store_favorite_create_time != null) {
			map.put("store_favorite_create_time", store_favorite_create_time);
		}
		if (store_favorite_update_time != null) {
			map.put("store_favorite_update_time", store_favorite_update_time);
		}
		maps = service.findStoreFavorlte(map);
		System.out.println(maps);
		printJson(response, maps);
	}

}
