package cn.kgc.tangcco.tcbd1017.on.system.action;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.PageRang;
import cn.kgc.tangcco.lihaozhe.commons.servlet.BaseServlet;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.system.OperationService;


/**
	 * @author XUE TONG
	 * @version 1.0 2019年12月18日下午2:59:38
	 * </br>
	 **/
@WebServlet(urlPatterns = "/Operation.action")
public class OperationAction extends BaseServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = -972157026816701357L;
	private static OperationService operationService;
	private static ClassPathXmlApplicationContext path = new ClassPathXmlApplicationContext(
			"ApplicationContext_on.xml");
	static {
		try {
			operationService = (OperationService) path.getBean(OperationService.class.getSimpleName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询所有待审核卖家并分页
	 * @param request
	 * @param response
	 * @param json
	 */
	public void selectSellers(HttpServletRequest request, HttpServletResponse response, String json){	
		Map map = JSON.parseObject(json , Map.class);
		int pageNum = (!map.containsKey("pageNum")) ? 1
				:  (int) map.get("pageNum");
		int pageSize = (!map.containsKey("pageSize")) ? 5
				: (int) map.get("pageSize");
		map.put("pr", new PageRang(pageNum, pageSize));
		Map<String, Object> info = null;
		try {
			info = operationService.selectSellers(map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		printJson(response, info);
	}
	
	/**
	 * 分配卖家入住商城的所有信息
	 */
	public void allocationSeller(HttpServletRequest request, HttpServletResponse response,String json){
		Map map = null;
		if (!StringUtils.isEmpty(json)) {
			 map = JSON.parseObject(json , Map.class);
		}
		Map<String, Object> map1 = null;
		try {
			map1 = operationService.allocationSeller(map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		printJson(response, map1);
	}
	
	/**
	 * 对卖家入住商城的信息的审核
	 */
	public void auditSeller(HttpServletRequest request, HttpServletResponse response,String json){
		Map map = JSON.parseObject(json , Map.class);
		Map<String, Object> map1 = null;
		try {
			map1 = operationService.auditSeller(map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		printJson(response, map1);
	}
	
	/**
	 * 查询所有待上架商品并分页
	 * @param request
	 * @param response
	 * @param json
	 */
	public void selectGoods(HttpServletRequest request, HttpServletResponse response, String json){	
		Map map = JSON.parseObject(json , Map.class);
		int pageNum = (!map.containsKey("pageNum")) ? 1
				:  (int) map.get("pageNum");
		int pageSize = (!map.containsKey("pageSize")) ? 5
				: (int) map.get("pageSize");
		map.put("pr", new PageRang(pageNum, pageSize));
		Map<String, Object> info = null;
		try {
			info = operationService.selectGoods(map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		printJson(response, info);
	}
	/**
	 * 分配上架商品的所有信息
	 */
	public void allocationGoods(HttpServletRequest request, HttpServletResponse response,String json){
		Map map = JSON.parseObject(json , Map.class);
		Map<String, Object> map1 = null;
		try {
			map1 = operationService.allocationGoods(map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		printJson(response, map1);
	}
	
	/**
	 * 对商家要上架的商品的审核
	 */
	public void auditGoods(HttpServletRequest request, HttpServletResponse response,String json){
		Map map = JSON.parseObject(json , Map.class);
		Map<String, Object> map1 = null;
		try {
			map1 = operationService.auditGoods(map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		printJson(response, map1);
	}
}
