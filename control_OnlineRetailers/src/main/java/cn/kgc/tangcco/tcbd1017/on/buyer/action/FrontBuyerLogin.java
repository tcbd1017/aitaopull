package cn.kgc.tangcco.tcbd1017.on.buyer.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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
	/** Cookie键 */
	public static final String COOKIE_NAME = "ssocookie";
	/** Cookie值 */
	public static final String COOKIE_VALUE = "sso";

	/**
	 * 校验Cookie
	 * 
	 * @param request
	 * @return true正确；false错误
	 */
	public void checkCookie(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		Map<String, Object> info = new HashMap<String, Object>();
		boolean falg = false;
		if (cookies == null) {
			falg = false;
		} else {
			for (Cookie cookie : cookies) {
				if (COOKIE_NAME.equals(cookie.getName()) && COOKIE_VALUE.equals(cookie.getValue())) {
					falg = true;
				}
			}
		}
		info.put("falg", falg);
		printJson(response, info);
	}

	public void setCookie(HttpServletRequest request, HttpServletResponse response,String json) {
		Cookie cookie = new Cookie(COOKIE_NAME, COOKIE_VALUE);
		// 顶级域名下，所有应用都是可见的
		cookie.setPath("/");
		cookie.setSecure(false);
//		cookie.setDomain("http://127.0.0.1:5500");
		// 添加Cookie
		response.addCookie(cookie);
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
//		response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
//		if (front_Buyer_Login.get("status").equals("success")) {
//			OkHttpClient client = new OkHttpClient();
//			String url = "http://127.0.0.1/control_OnlineRetailers/FrontBuyerLogin.action?methodName=setCookie";
//			MediaType jsonc = MediaType.parse("application/json;charset=utf-8");
//			RequestBody body = RequestBody.create(jsonc,json);
//			Request req = new Request.Builder().url(url).post(body).build();
//			try {
//				Response execute = client.newCall(req).execute();
//				String string = execute.body().string();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
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
