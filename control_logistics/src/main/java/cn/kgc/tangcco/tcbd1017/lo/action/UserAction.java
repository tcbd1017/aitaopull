package cn.kgc.tangcco.tcbd1017.lo.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.kgc.tangcco.tcbd1017.lo.UserService;
import cn.kgc.tangcco.tcbd1017.lo.commons.jdbc.ServiceFactory;
import cn.kgc.tangcco.tcbd1017.lo.commons.servlet.BaseServlet;
import cn.kgc.tangcco.tcbd1017.lo.commons.suiji.SendSms;
import cn.kgc.tangcco.tcbd1017.lo.commons.suiji.SuiJi;
import cn.kgc.tangcco.tcbd1017.lo.impl.UserServiceImpl;


/**
 * Servlet implementation class UserAction
 */
@WebServlet("/user.action")
public class UserAction extends BaseServlet {
	private static UserService userServiceImpl= (UserService) ServiceFactory.getService(new UserServiceImpl());
	/**
	 * @author 王立宁
	 * @version 1.0 <br>
	 *          创建时间：2019年12月14日上午10:29:19
	 */
	private static final long serialVersionUID = 6589439513434730488L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void adduser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String password = request.getParameter("password");
		String mobile = request.getParameter("mobile");
		String user_uuid = SuiJi.getRandomNumber(14);
		if (
				password == null || password.length() == 0 
				|| mobile == null || mobile.length() == 0) {
			System.out.println("请输入完整信息");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("status", "failed");
			printJson(response, map);
			return;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("password", password);
		map.put("mobile", mobile);
		map.put("user_uuid", user_uuid);
		map.put("nickname", SuiJi.getRandomString(8));
		Map<String, Object> addUser = userServiceImpl.addUser(map);
		System.out.println(addUser.toString());
		printJson(response, addUser);
	}

	protected void loginUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		if (account == null|| account.length() == 0 
				|| password == null || password.length() == 0) {
			System.out.println("请输入完整信息");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("status", "failed");
			printJson(response, map);
			return;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("account", account);
		map.put("password", password);

		Map<String, Object> selectUser = userServiceImpl.selectUser(map);
			printJson(response, selectUser);
			System.out.println(selectUser);
			System.out.println("登陆成功");
	}
	
	protected void updateUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nickname = request.getParameter("nickname");
		String password = request.getParameter("password");
		String user_uuid = request.getParameter("user_uuid");
		if (nickname == null || nickname.length() == 0 
				|| password == null || password.length() == 0) {
			System.out.println("请输入完整信息");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("status", "failed");
			printJson(response, map);
			return;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nickname", nickname);
		map.put("password", password);
		map.put("user_uuid", user_uuid);
		Map<String, Object> updateUser = userServiceImpl.updateUser(map);
		printJson(response, updateUser);
		System.out.println(updateUser);
		System.out.println("修改成功");
	}
	
	protected void code(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String mobile = request.getParameter("mobile");
		String code = SendSms.smsSending(mobile);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "success");
		map.put("code", code);
		printJson(response, map);
	}
	
	protected void codeBi(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String code = request.getParameter("code");
		String Verification = request.getParameter("Verification");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "falied");
		if(code.equals(Verification)) {
			map.put("status", "success");
		}
		printJson(response, map);
	}
}
