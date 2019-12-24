package cn.kgc.tangcco.tcbd1017.lo.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.kgc.tangcco.tcbd1017.lo.LogisticsTextService;
import cn.kgc.tangcco.tcbd1017.lo.commons.jdbc.ServiceFactory;
import cn.kgc.tangcco.tcbd1017.lo.commons.servlet.BaseServlet;
import cn.kgc.tangcco.tcbd1017.lo.impl.LogisticsTextServiceImpl;

/**
 * Servlet implementation class Messageboard
 */
@WebServlet("/messageboard.action")
public class MessageAction extends BaseServlet {
	private static final long serialVersionUID = 2799818851722108839L;
	private static LogisticsTextService lts=new LogisticsTextServiceImpl();
	/**
     * @see HttpServlet#HttpServlet()
     */
    public MessageAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void liuyan(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String xiaoxi = request.getParameter("xiaoxi");
		String user_uuid = request.getParameter("user_uuid");
		if (xiaoxi==null&&xiaoxi.length()==0) {
			System.out.println("没得留言");
		}
		Map<String, Object>map=new HashMap<String, Object>();
		map.put("logistics_text_txt", xiaoxi);
		map.put("logistics_user_id", user_uuid);
		Map<String, Object> insertText = lts.insertText(map);
		printJson(response, insertText);
	}
}
