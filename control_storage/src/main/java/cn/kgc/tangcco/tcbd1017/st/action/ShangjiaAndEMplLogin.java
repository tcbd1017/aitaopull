package cn.kgc.tangcco.tcbd1017.st.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.kgc.tangcco.lihaozhe.commons.cryptography.BaseCryptographyUtils;
import cn.kgc.tangcco.lihaozhe.commons.propertis.BaseProperties;
import cn.kgc.tangcco.lihaozhe.commons.servlet.BaseServlet;
import cn.kgc.tangcco.lihaozhe.commons.uuid.BaseUUID;
import cn.kgc.tangcco.tcbd1017.st.impl.ShangJiaAndEMpmLoginImpl;


@WebServlet(urlPatterns = "/ShangJiaAndEmpLoginAction.action")
public class ShangjiaAndEMplLogin extends BaseServlet {

	private static final long serialVersionUID = 2275064013452728265L;

	ShangJiaAndEMpmLoginImpl impl = new ShangJiaAndEMpmLoginImpl();
	
	/**
	 * 商家登录
	 * @param request
	 * @param response
	 */
	public void ShopLogin(HttpServletRequest request, HttpServletResponse response) {
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		System.out.println(account);
		System.out.println(password);
		String deskey = BaseProperties.getProperties("key.properties", "deskey");
		password = BaseCryptographyUtils.desEncodeHexString(password, deskey);
		Map<String, Object> map = impl.ShopLogin(account,password);
		printJson(response, map);
	}

	/**
	 * 商家注册
	 * @param request
	 * @param response
	 */
	public void ShopZhuCe(HttpServletRequest request, HttpServletResponse response) {
		String shop_name = request.getParameter("shop_name");
		String shop_uuid = BaseUUID.generate();
		String shop_address = request.getParameter("shop_address");
		String shop_account = request.getParameter("shop_account");
		
		String shop_password = request.getParameter("shop_password");
		String deskey = BaseProperties.getProperties("key.properties", "deskey");
		shop_password = BaseCryptographyUtils.desEncodeHexString(shop_password, deskey);
		
		String shop_phone = request.getParameter("shop_phone");
		
		Map<String,Object>map=new HashMap<>();
		map.put("shop_name", shop_name);
		map.put("shop_uuid",shop_uuid );
		map.put("shop_address",shop_address );
		map.put("shop_account",shop_account );
		map.put("shop_password",shop_password );
		map.put("shop_phone",shop_phone );
		Map<String, Object> map2 = impl.ShopZhuCe(map);
		printJson(response, map2);
	}
	
	/**
	 * 商家修改密码
	 * @param request
	 * @param response
	 */
	public void XiuGaiMiMa(HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> map = new HashMap<String, Object>();
		String shop_password = request.getParameter("shop_password");
		String deskey = BaseProperties.getProperties("key.properties", "deskey");
		shop_password = BaseCryptographyUtils.desEncodeHexString(shop_password, deskey);
		
		String shop_ids = request.getParameter("shop_id");
		int shop_id = Integer.parseInt(shop_ids);
		
		map.put("shop_password", shop_password);
		map.put("shop_id", shop_id);
		Map<String, Object> xiuGaiMiMa = impl.XiuGaiMiMa(map);
		printJson(response, xiuGaiMiMa);
	}

	
	/**
	 * 员工登录
	 * @param request
	 * @param response
	 */
	public void EmpLogin(HttpServletRequest request, HttpServletResponse response) {
		String emp_account = request.getParameter("emp_account");
		String emp_password = request.getParameter("emp_password");
		String deskey = BaseProperties.getProperties("key.properties", "deskey");
		emp_password = BaseCryptographyUtils.desEncodeHexString(emp_password, deskey);
		Map<String, Object> map = impl.EmpLogin(emp_account,emp_password);
		List<Object>list=(List<Object>) map.get("data");
		for (Object object : list) {
			System.out.println(object.toString());
		}
		printJson(response, map);
	}
	
	
}
