package cn.kgc.tangcco.tcbd1017.on.system.action;


import java.util.HashMap;
import java.util.Map;


import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.nntp.NewGroupsOrNewsQuery;

import com.alibaba.fastjson.JSON;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.PageRang;
import cn.kgc.tangcco.lihaozhe.commons.servlet.BaseServlet;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.system.FinanceService;


/**
 * Servlet implementation class FinanceAction
 * 许佳瑞
 */
@WebServlet("/financeAction.action")
public class FinanceAction extends BaseServlet {
	private static final long serialVersionUID = -1043472544809819346L;
	static FinanceService financeService=null;
	static {
		ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("ApplicationContext_on.xml");
		try {
			financeService=(FinanceService) classPathXmlApplicationContext.getBean(FinanceService.class.getSimpleName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 审核卖家购买套餐审核情况
	 * 
	 * seller_id  商家的Id
	 * 
	 * set_meal_id 套餐的id
	 * 
	 * @param request
	 * @param response
	 */
	public void modifySetMealState(HttpServletRequest request,HttpServletResponse response,String json) {
//		int seller_id=Integer.parseInt(request.getParameter("seller_id"));
//		int set_meal_id=Integer.parseInt(request.getParameter("set_meal_id"));
//		Map<String, Object> map= new HashMap<String, Object>();
//		map.put("seller_id", seller_id);
//		map.put("set_meal_id",set_meal_id);
//		Map<String, Object> map2=new HashMap<String, Object>();
//		map2=financeService.modifySetMealState(map);
//		printJson(response, map2);
		
		Map map = null;
		if (!StringUtils.isEmpty(json)) {
			 map = JSON.parseObject(json,Map.class);
		}
		Map info = financeService.modifySetMealState(map);
		printJson(response, info);
	}
	
	/**
	 * 公司收入情况
	 * @param request
	 * @param response
	 */
	public void queryIncome(HttpServletRequest request,HttpServletResponse response,String json) {
		Map map = JSON.parseObject(json , Map.class);
		  int pageNum = (!map.containsKey("pageNumber")) ? 1
		    :  (int) map.get("pageNumber");
		  int pageSize = (!map.containsKey("pageSize")) ? 5
		    : (int) map.get("pageSize");
		  System.out.println(pageNum);
		  System.out.println(pageSize);
		  map.put("pr", new PageRang(pageNum, pageSize));
		  Map<String, Object> info = null;
		  info = financeService.queryIncome(map);
		  printJson(response, info);
		 }
		
	
	
	/**
	 * 公司支出情况
	 * @param request
	 * @param response
	 */
	public void queryExpenditure (HttpServletRequest request,HttpServletResponse response,String json) {
		Map map = JSON.parseObject(json , Map.class);
		  int pageNum = (!map.containsKey("pageNumber")) ? 1
		    :  (int) map.get("pageNumber");
		  int pageSize = (!map.containsKey("pageSize")) ? 5
		    : (int) map.get("pageSize");
		  map.put("pr", new PageRang(pageNum, pageSize));
		  Map<String, Object> info = null;
		  info = financeService.queryExpenditure(map);
		  printJson(response, info);
	}
	
	/**
	 * 获得未审核套餐的商家人数及详细信息
	 * map.get("data") 详细信息
	 * 
	 * map.get("number") 未审核的总人数
	 * @param request
	 * @param response
	 */
	public void queryNumberAndDetailed (HttpServletRequest request,HttpServletResponse response,String json) {
//		Map<String, Object> map = new HashMap<String, Object>();
//		Map<String, Object> map1 = new HashMap<String, Object>();
//		int pageNumber = (StringUtils.isEmpty(request.getParameter("pageNumber"))) ? 1
//				: (Integer.parseInt(request.getParameter("pageNumber")));
//		int pageSize = (StringUtils.isEmpty(request.getParameter("pageSize"))) ? 10
//				: (Integer.parseInt(request.getParameter("pageSize")));
//		int emp_id=Integer.parseInt(request.getParameter("emp_id"));
//		map1.put("person_in_charge_id", emp_id);
//		map1.put("pr", new PageRang(pageNumber, pageSize));
//		map= financeService.queryNumberAndDetailed(map1);
//		printJson(response, map);
		Map map = JSON.parseObject(json , Map.class);
		  int pageNum = (!map.containsKey("pageNumber")) ? 1
		    :  (int) map.get("pageNumber");
		  int pageSize = (!map.containsKey("pageSize")) ? 5
		    : (int) map.get("pageSize");
		  map.put("pr", new PageRang(pageNum, pageSize));
		  Map<String, Object> info = null;
		  info = financeService.queryNumberAndDetailed(map);
		  printJson(response, info);
	}
	
	/**
	 * 
	 * 分配员工任务
	 */
	
	public void modifyPersonInCharge(HttpServletRequest request,HttpServletResponse response,String json) {
//			Map parseObject = JSON.parseObject(json, Map.class);
//			Map<String, Object>map1= new HashMap<String, Object>();
//			map1.put("person_in_charge_id",parseObject.get("emp_id"));
//			map1.put("data",parseObject.get("data"));
//		  Map<String, Object> map =financeService.modifyPersonInCharge(map1);
//		  printJson(response, map);
		Map map = null;
		if (!StringUtils.isEmpty(json)) {
			 map = JSON.parseObject(json,Map.class);
		}
		Map info = financeService.modifyPersonInCharge(map);
		printJson(response, info);
	}
}
