package cn.kgc.tangcco.tcbd1017.lo.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.kgc.tangcco.tcbd1017.lo.AddressService;
import cn.kgc.tangcco.tcbd1017.lo.commons.jdbc.ServiceFactory;
import cn.kgc.tangcco.tcbd1017.lo.commons.servlet.BaseServlet;
import cn.kgc.tangcco.tcbd1017.lo.impl.AddressServiceImpl;


	
/**
 * Servlet implementation class AddressAction
 */
@WebServlet("/address.action")
public class AddressAction extends BaseServlet {
	private static AddressService addressServiceImpl = (AddressService) ServiceFactory.getService(new AddressServiceImpl());
    /**
	 * @author 王立宁
	 * @version 1.0 <br>
	 * 创建时间：2019年12月14日下午11:13:04 
	 */
	private static final long serialVersionUID = 4972758871430206480L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public AddressAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
    //根据user_uuid查询自己对应的地址
	protected void selectAddressByUserId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_uuid=request.getParameter("user_uuid");
		if(user_uuid==null||user_uuid.length()==0) {
			System.out.println("错误访问");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("status", "failed");
			printJson(response, map);
			return;
		}
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("user_uuid", user_uuid);
		Map<String, Object> selectByUserId = addressServiceImpl.selectByUserId(map);
			System.out.println("查询成功"+selectByUserId);
			printJson(response, selectByUserId);
	}

	/**
	 * 	 添加
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	protected void addLogisticsAddress(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String user_uuid=request.getParameter("user_uuid");
		String logistics_address_nickname=request.getParameter("logistics_address_nickname");
		String logistics_address_mobile=request.getParameter("logistics_address_mobile");
		String logistics_province_id=request.getParameter("logistics_province_id");
		String logistics_city_id=request.getParameter("logistics_city_id");
		String logistics_district_id=request.getParameter("logistics_district_id");
		String logistics_address=request.getParameter("logistics_address");
		
		if(user_uuid==null||user_uuid.length()==0
		||logistics_address_nickname==null||logistics_address_nickname.length()==0
		||logistics_address_mobile==null||logistics_address_mobile.length()==0
		||logistics_province_id==null||logistics_province_id.length()==0
		||logistics_city_id==null||logistics_city_id.length()==0
		||logistics_district_id==null||logistics_district_id.length()==0
		||logistics_address==null||logistics_address.length()==0) {
			System.out.println("请输入完整地址");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("status", "failed");
			printJson(response, map);
			return;
		}
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("user_uuid", user_uuid);
		map.put("logistics_address_nickname",logistics_address_nickname);
		map.put("logistics_address_mobile", logistics_address_mobile);
		map.put("logistics_province_id",logistics_province_id );
		map.put("logistics_city_id", logistics_city_id);
		map.put("logistics_district_id",logistics_district_id );
		map.put("logistics_address", logistics_address);
		Map<String, Object> addByUserId = addressServiceImpl.addByUserId(map);
		System.out.println(addByUserId);
		printJson(response, addByUserId);
	}
	
	//删除地址
	protected void deleteAddress(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_uuid=request.getParameter("user_uuid");
		String logistics_address_uuid=request.getParameter("logistics_address_uuid");
		if(user_uuid==null||user_uuid.length()==0
				||logistics_address_uuid==null||logistics_address_uuid.length()==0
				) {
			
			System.out.println("错误访问");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("status", "failed");
			printJson(response, map);
			return;
		}
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("user_uuid", user_uuid);
		map.put("logistics_address_uuid",logistics_address_uuid);
		
		Map<String, Object> deleteByAddressId = addressServiceImpl.deleteByAddressId(map);
		System.out.println(deleteByAddressId);
		printJson(response, deleteByAddressId);
	}
	
	
	 
	protected void updateAddress(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_uuid=request.getParameter("user_uuid");
		String logistics_address_uuid=request.getParameter("logistics_address_uuid");
		String logistics_address_nickname=request.getParameter("logistics_address_nickname");
		String logistics_address_mobile=request.getParameter("logistics_address_mobile");
		String logistics_province_id=request.getParameter("logistics_province_id");
		String logistics_city_id=request.getParameter("logistics_city_id");
		String logistics_district_id=request.getParameter("logistics_district_id");
		String logistics_address=request.getParameter("logistics_address");
		
		if(user_uuid==null||user_uuid.length()==0
		||logistics_address_uuid==null||logistics_address_uuid.length()==0
		||logistics_address_nickname==null||logistics_address_nickname.length()==0
		||logistics_address_mobile==null||logistics_address_mobile.length()==0
		||logistics_province_id==null||logistics_province_id.length()==0
		||logistics_city_id==null||logistics_city_id.length()==0
		||logistics_district_id==null||logistics_district_id.length()==0
		||logistics_address==null||logistics_address.length()==0) {
			System.out.println("请输入完整地址");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("status", "failed");
			printJson(response, map);
			return;
		}
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("user_uuid", user_uuid);
		map.put("logistics_address_uuid", logistics_address_uuid);
		map.put("logistics_address_nickname",logistics_address_nickname);
		map.put("logistics_address_mobile", logistics_address_mobile);
		map.put("logistics_province_id",logistics_province_id );
		map.put("logistics_city_id", logistics_city_id);
		map.put("logistics_district_id",logistics_district_id );
		map.put("logistics_address", logistics_address);
		Map<String, Object> updateByAddressId = addressServiceImpl.updateByAddressId(map); 
		System.out.println(updateByAddressId);
		printJson(response, updateByAddressId);
		
	}
}
