package cn.kgc.tangcco.tcbd1017.on.system.action;

import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import cn.kgc.tangcco.lihaozhe.commons.servlet.BaseServlet;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.system.FinanceService;
import cn.kgc.tangcco.tcbd1017.on.system.RevenueAndExpenditureImplService;

/**
 * Servlet implementation class RevenueAndExpenditureAction
 */
@WebServlet("/RevenueAndExpenditureAction.action")
public class RevenueAndExpenditureAction extends BaseServlet {

	private static final long serialVersionUID = 2815419490926526012L;
	static RevenueAndExpenditureImplService revenueAndExpenditureImplService;
	static {
		ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("ApplicationContext_on.xml");
		try {
			revenueAndExpenditureImplService=(RevenueAndExpenditureImplService) classPathXmlApplicationContext.getBean(RevenueAndExpenditureImplService.class.getSimpleName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * 每天的支出 和每天
	 * 收入  money
	 * date 那一天
	 * @param request
	 * @param response
	 * @param json
	 */
	public void queryExpenditure (HttpServletRequest request,HttpServletResponse response,String json) {
		Map info = revenueAndExpenditureImplService.modifyExpenditureImpl();
		printJson(response, info);
	}
	
	/**
	 * 每天的收入 和每天
	 * 收入  money
	 * date 那一天
	 * @param request
	 * @param response
	 * @param json
	 */
	public void queryRevenue (HttpServletRequest request,HttpServletResponse response,String json) {
		
		Map info = revenueAndExpenditureImplService.modifyRevenue();
		printJson(response, info);
	}
}
	


