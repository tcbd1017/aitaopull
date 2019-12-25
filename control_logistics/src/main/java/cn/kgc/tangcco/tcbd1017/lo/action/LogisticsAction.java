package cn.kgc.tangcco.tcbd1017.lo.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import cn.kgc.tangcco.tcbd1017.lo.LogisticsService;
import cn.kgc.tangcco.tcbd1017.lo.commons.jdbc.ServiceFactory;
import cn.kgc.tangcco.tcbd1017.lo.commons.servlet.BaseServlet;
import cn.kgc.tangcco.tcbd1017.lo.impl.LogisticsServiceImpl;


/**
 * Servlet implementation class AddressAction
 */
@WebServlet(urlPatterns = "/logistics.action")
public class LogisticsAction extends BaseServlet {
	private static LogisticsService logisticsServiceImpl=(LogisticsService) ServiceFactory.getService(new LogisticsServiceImpl());
	/**
	 * @author 王立宁
	 * @version 1.0 <br>
	 *          创建时间：2019年12月14日下午7:11:01
	 */
	private static final long serialVersionUID = 4972758871430206480L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogisticsAction() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * 	
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void queryLogisticsByLogisticsId(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String logistics_uuid = request.getParameter("logistics_uuid");
		if (logistics_uuid==null||logistics_uuid.length()==0) {
			System.out.println("请输入uuid");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("status", "failed");
			printJson(response, map);
			return;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("logistics_uuid", logistics_uuid);
		Map<String, Object> selectLogisticsByLogisticsId = logisticsServiceImpl.selectLogisticsByLogisticsId(map);
			printJson(response, selectLogisticsByLogisticsId);
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void queryUserAllOrder(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String user_uuid = request.getParameter("user_uuid");
		String logistics_status = request.getParameter("logistics_status");
		if (user_uuid==null||user_uuid.length()==0) {
			System.out.println("请输入uuid");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("status", "failed");
			printJson(response, map);
			return;
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_uuid", user_uuid);
		map.put("logistics_status", request.getParameter("logistics_status"));
		if(Integer.parseInt(logistics_status)==0 ) {
			Map<String, Object> selectAllLogisticsByUserId = logisticsServiceImpl.selectAllLogisticsByUserId(map);
			printJson(response, selectAllLogisticsByUserId);
			return;
		}
		Map<String, Object> selectLogisticsByUserId = logisticsServiceImpl.selectLogisticsByUserId(map);
			printJson(response, selectLogisticsByUserId);
	}
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void addOrderAndLogistics(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String user_uuid = request.getParameter("user_uuid");
		String logistics_commodity_name = request.getParameter("logistics_commodity_name");
		String logistics_sender_name = request.getParameter("logistics_sender_name");
		String logistics_sender_mobile = request.getParameter("logistics_sender_mobile");
		String logistics_sender_address = request.getParameter("logistics_sender_address");
		String logistics_receiver_name = request.getParameter("logistics_receiver_name");
		String logistics_receiver_mobile = request.getParameter("logistics_receiver_mobile");
		String logistics_receiver_address = request.getParameter("logistics_receiver_address");
		String logistics_weight = request.getParameter("logistics_weight");
		if (logistics_commodity_name == null || logistics_commodity_name.length() == 0
			||logistics_sender_name == null || logistics_sender_name.length() == 0 
			||logistics_sender_mobile == null || logistics_sender_mobile.length() == 0
			||logistics_sender_address == null || logistics_sender_address.length() == 0
			||logistics_receiver_name == null || logistics_receiver_name.length() == 0
			||logistics_receiver_mobile == null || logistics_receiver_mobile.length() == 0
			||logistics_receiver_address == null || logistics_receiver_address.length() == 0) {
			System.out.println("请输入完整信息");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("status", "failed");
			printJson(response, map);
			return;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("logistics_commodity_name", logistics_commodity_name);
		map.put("logistics_sender_name", logistics_sender_name);
		map.put("logistics_sender_mobile", logistics_sender_mobile);
		map.put("logistics_sender_address", logistics_sender_address);
		map.put("logistics_receiver_name", logistics_receiver_name);
		map.put("logistics_receiver_mobile", logistics_receiver_mobile);
		map.put("logistics_receiver_address", logistics_receiver_address);
		map.put("user_uuid", user_uuid);
		Map<String, Object> addLogistics = logisticsServiceImpl.addLogistics(map);
		printJson(response, addLogistics);
	}
	/**
	 * 根据运单id查询运单
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void selectLogisticsIdByLogistics(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String logistics_uuid = request.getParameter("logistics_uuid");
		if (logistics_uuid==null||logistics_uuid.length()==0) {
			System.out.println("请输入uuid");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("status", "failed");
			printJson(response, map);
			return;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("logistics_uuid", logistics_uuid);
		Map<String, Object> selectLogisticsIdByLogistics = logisticsServiceImpl.selectLogisticsIdByLogistics(map);
		System.out.println(selectLogisticsIdByLogistics.toString());
		printJson(response, selectLogisticsIdByLogistics);
		
	}
	
}
