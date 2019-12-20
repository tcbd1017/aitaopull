package cn.kgc.tangcco.tcbd1017.on.buyer.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;

import cn.kgc.tangcco.lihaozhe.commons.aliyun.mms.MmsTry;
import cn.kgc.tangcco.lihaozhe.commons.face.FaceDetect;
import cn.kgc.tangcco.lihaozhe.commons.face.FaceSearch;
import cn.kgc.tangcco.lihaozhe.commons.jdbc.TransactionManagementHandler;
import cn.kgc.tangcco.lihaozhe.commons.servlet.BaseServlet;
import cn.kgc.tangcco.tcbd1017.on.buyer.FrontBuyerLoginServiceIns;
import cn.kgc.tangcco.tcbd1017.on.buyer.impl.FrontBuyerLoginServiceImpl;

/**
 * 刘煜 类描述：买家 登录 Servlet implementation class FrontBuyerLogin
 */
@WebServlet("/FrontBuyerLogin.action")
public class FrontBuyerLogin extends BaseServlet {
	private static final long serialVersionUID = -686172120984958153L;
	static FrontBuyerLoginServiceIns proxy = null;
	static {
		proxy = (FrontBuyerLoginServiceIns) new TransactionManagementHandler(new FrontBuyerLoginServiceImpl())
				.getProxy();
	}

	// 用户登录
	public void front_Buyer_Login(HttpServletRequest request, HttpServletResponse response, String json) {
		Map<?, ?> maps = JSON.parseObject(json, Map.class);
		System.out.println(maps.toString());
		Map<String, Object> map = new HashMap<>();
		map.put("buyer_login_account", maps.get("buyer_login_account"));
		map.put("buyer_login_password", maps.get("buyer_login_password"));
		map.put("buyer_login_face_token", maps.get("buyer_login_face_token"));
		map.put("buyer_mobile", maps.get("buyer_mobile"));
		Map<String, Object> front_Buyer_Login = proxy.Front_Buyer_Login(map);
		printJson(response, front_Buyer_Login);
	}

	// 人脸检测
	public void front_Buyer_FaceDetect(HttpServletRequest request, HttpServletResponse response) {
		String img = request.getParameter("img");
		String result = FaceDetect.faceDetect(img);
		printJson(response, result);
	}

	// 人脸搜索
	public void front_Buyer_FaceSearch(HttpServletRequest request, HttpServletResponse response) {
		String img = request.getParameter("img");
		String result = FaceSearch.faceSearch(img);
		printJson(response, result);
	}

	/**
	 * 短信验证
	 * 
	 * @param request
	 * @param response
	 * @param json
	 */
	public void mms_verification(HttpServletRequest request, HttpServletResponse response, String json) {
		MmsTry s = new MmsTry();
		Map map = null;
		if (!StringUtils.isEmpty(json)) {
			map = JSON.parseObject(json, Map.class);
		}
		String ranCode = s.ranCode();
		map.put("ranCode", ranCode);
		s.sendMms(map);
		Map<String, String> info = new HashMap<String, String>();
		info.put("ranCode", ranCode);
		printJson(response, info);
	}
}
