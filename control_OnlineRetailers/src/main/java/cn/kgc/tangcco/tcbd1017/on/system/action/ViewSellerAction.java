package cn.kgc.tangcco.tcbd1017.on.system.action;

import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.PageRang;
import cn.kgc.tangcco.lihaozhe.commons.servlet.BaseServlet;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.system.ViewSellerService;

/**
 * @author XUE TONG
 * @version 1.0 2019年12月16日下午3:15:20 </br>
 **/
@WebServlet(urlPatterns = "/ViewSeller.action")
public class ViewSellerAction extends BaseServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6851634894953845582L;
	private static ViewSellerService viewSellerService;
	private static ClassPathXmlApplicationContext path = new ClassPathXmlApplicationContext(
			"ApplicationContext_on.xml");
	static {
		try {
			viewSellerService = (ViewSellerService) path.getBean(ViewSellerService.class.getSimpleName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询所有并分页
	 * @param request
	 * @param response
	 * @param json
	 */
	public void querySeller(HttpServletRequest request, HttpServletResponse response, String json) {
		Map map = JSON.parseObject(json , Map.class);
		int pageNum = (!map.containsKey("pageNum")) ? 1
				:  (int) map.get("pageNum");
		int pageSize = (!map.containsKey("pageSize")) ? 5
				: (int) map.get("pageSize");
		map.put("pr", new PageRang(pageNum, pageSize));
		Map<String, Object> info = viewSellerService.querySeller(map);	
		printJson(response, info);
	}

}
