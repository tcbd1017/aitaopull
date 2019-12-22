package cn.kgc.tangcco.tcbd1017.lo.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.kgc.tangcco.tcbd1017.lo.GoodsService;
import cn.kgc.tangcco.tcbd1017.lo.commons.jdbc.ServiceFactory;
import cn.kgc.tangcco.tcbd1017.lo.commons.servlet.BaseServlet;
import cn.kgc.tangcco.tcbd1017.lo.impl.GoodsServiceImpl;



@WebServlet("/goods.action")
public class GoodsAction extends BaseServlet {

	GoodsService gs=(GoodsService) ServiceFactory.getService(new GoodsServiceImpl());
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 284395563233113389L;

	/**
	 * 	添加订单并返回订单id
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void addUseridAndLogisticsId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String user_uuid = request.getParameter("user_uuid");
		String logistics_commodity_name = request.getParameter("logistics_commodity_name");
		String logistics_sender_name = request.getParameter("logistics_sender_name");
		String logistics_sender_mobile = request.getParameter("logistics_sender_mobile");
		String logistics_sender_address = request.getParameter("logistics_sender_address");
		String logistics_receiver_name = request.getParameter("logistics_receiver_name");
		String logistics_receiver_mobile = request.getParameter("logistics_receiver_mobile");
		String logistics_receiver_address = request.getParameter("logistics_receiver_address");
		String logistics_weight = request.getParameter("logistics_weight");
		Map<String, Object> map = new HashMap<String, Object>();
		if (logistics_commodity_name == null && logistics_commodity_name.length() == 0
			&&logistics_sender_name == null && logistics_sender_name.length() == 0 
			&&logistics_sender_mobile == null && logistics_sender_mobile.length() == 0
			&&logistics_sender_address == null && logistics_sender_address.length() == 0
			&&logistics_receiver_name == null && logistics_receiver_name.length() == 0
			&&logistics_receiver_mobile == null && logistics_receiver_mobile.length() == 0
			&&logistics_receiver_address == null && logistics_receiver_address.length() == 0) {
			System.out.println("请输入完整信息");
		}
		map.put("logistics_commodity_name", logistics_commodity_name);
		map.put("logistics_sender_name", logistics_sender_name);
		map.put("logistics_sender_mobile", logistics_sender_mobile);
		map.put("logistics_sender_address", logistics_sender_address);
		map.put("logistics_receiver_name", logistics_receiver_name);
		map.put("logistics_receiver_mobile", logistics_receiver_mobile);
		map.put("logistics_receiver_address", logistics_receiver_address);
		map.put("user_uuid", user_uuid);
		Map<String, Object> addLogistics = gs.addUseridAndLogisticsId(map);
		printJson(response, addLogistics);
	}
	/**
	 * 	根据商户uuid查询全部订单信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void selectAllLogisticsByUserId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String goods_uuid = request.getParameter("goods_uuid");
		if (goods_uuid==null&&goods_uuid.length()==0) {
			System.out.println("请输入uuid");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("goods_uuid", goods_uuid);
		Map<String, Object> selectAllLogisticsByUserId = gs.selectAllLogisticsByUserId(map);
		printJson(response, selectAllLogisticsByUserId);
	}
	
	
	/**
	 *  根据订单uuid查看订单详情
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void selectLogisticsIdByLogistics(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String logistics_uuid = request.getParameter("logistics_uuid");
		if (logistics_uuid==null&&logistics_uuid.length()==0) {
			System.out.println("请输入uuid");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("logistics_uuid", logistics_uuid);
		Map<String, Object> selectLogisticsIdByLogistics = gs.selectLogisticsIdByLogistics(map);
			printJson(response, selectLogisticsIdByLogistics);
		
	}
}
