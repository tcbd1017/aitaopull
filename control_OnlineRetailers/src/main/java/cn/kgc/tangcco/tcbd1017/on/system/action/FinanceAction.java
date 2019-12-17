package cn.kgc.tangcco.tcbd1017.on.system.action;


import java.util.HashMap;
import java.util.Map;


import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import cn.kgc.tangcco.lihaozhe.commons.servlet.BaseServlet;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.system.FinanceService;


/**
 * Servlet implementation class FinanceAction
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
	 * shop_income_money 套餐的价格
	 * 
	 * @param request
	 * @param response
	 */
	public void modifySetMealState(HttpServletRequest request,HttpServletResponse response) {
		int seller_id=Integer.parseInt(request.getParameter("seller_id"));
		Double shop_income_money=Double.parseDouble(request.getParameter("shop_income_money"));
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("seller_id", seller_id);
		map.put("shop_income_money",shop_income_money);
		Map<String, Object> map2=new HashMap<String, Object>();
		map2=financeService.modifySetMealState(map);
		printJson(response, map2);
	}
	
	/**
	 * 公司收入情况
	 * @param request
	 * @param response
	 */
	public void queryIncome(HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object>map = new HashMap<String, Object>();
		map=financeService.queryIncome();
		printJson(response, map);
	}
	
	/**
	 * 公司支出情况
	 * @param request
	 * @param response
	 */
	public void queryExpenditure (HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map= financeService.queryExpenditure();
		printJson(response, map);
	}
	
	/**
	 * 获得未审核套餐的商家人数及详细信息
	 * map.get("data") 详细信息
	 * 
	 * map.get("number") 未审核的总人数
	 * @param request
	 * @param response
	 */
	public void queryNumberAndDetailed (HttpServletRequest request,HttpServletResponse response) {
		Map<String, Object> map = new HashMap<String, Object>();
		map= financeService.queryNumberAndDetailed();
		printJson(response, map);
	}
}
