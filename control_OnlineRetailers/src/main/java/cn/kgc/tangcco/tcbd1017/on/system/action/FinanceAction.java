package cn.kgc.tangcco.tcbd1017.on.system.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.kgc.tangcco.lihaozhe.commons.servlet.BaseServlet;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.system.FinanceService;
import cn.kgc.tangcco.tcbd1017.on.system.FindEmpAllInfo;

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
	
	

}
