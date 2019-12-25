package cn.kgc.tangcco.tcbd1017.lo.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.kgc.tangcco.tcbd1017.lo.MoneyService;
import cn.kgc.tangcco.tcbd1017.lo.commons.jdbc.ServiceFactory;
import cn.kgc.tangcco.tcbd1017.lo.commons.servlet.BaseServlet;
import cn.kgc.tangcco.tcbd1017.lo.impl.MoneyServiceImpl;


/**
 * Servlet implementation class MoneyAction
 */
@WebServlet("/money.action")
public class MoneyAction extends BaseServlet {
	private static MoneyService moneyServiceImpl =(MoneyService) ServiceFactory.getService(new MoneyServiceImpl());
 
    /**
	 * @author 王立宁
	 * @version 1.0 <br>
	 * 创建时间：2019年12月14日下午11:08:04 
	 */
	private static final long serialVersionUID = -4924950777255020504L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public MoneyAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
    //根据user的id查询账单
	protected void selectBillByUserId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_uuid=request.getParameter("user_uuid");
		if (user_uuid==null||user_uuid.length()==0) {
			System.out.println("请输入uuid");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("status", "failed");
			printJson(response, map);
			return;
		}
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("user_uuid", user_uuid);
		Map<String, Object> selectLogisticsByUserId = moneyServiceImpl.selectLogisticsByUserId(map);
		if (selectLogisticsByUserId.get("status").toString().equals("success")) {
		System.out.println("查询成功");
		printJson(response, selectLogisticsByUserId);
		}
	}

}
