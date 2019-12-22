package cn.kgc.tangcco.tcbd1017.on.buyer.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.buyer.FrontBuyerLoginDaoIns;
import cn.kgc.tangcco.tcbd1017.on.buyer.FrontBuyerLoginServiceIns;

/**
 * @author 作者 Your-Name: 刘煜
 * @version 创建时间：2019年12月11日 下午8:23:35 类说明
 */
public class FrontBuyerLoginServiceImpl implements FrontBuyerLoginServiceIns {
	static FrontBuyerLoginDaoIns bean = null;
	static {
		try {
			ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(
					"ApplicationContext_on.xml");
			bean = (FrontBuyerLoginDaoIns) classPathXmlApplicationContext.getBean("FrontBuyerLoginDaoIns");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public Map<String, Object> Front_Buyer_Login(Map<String, Object> maps) {
		Map<String, Object> hashmap = new HashMap<>();
		try {
			hashmap.put("status", "failed");
			hashmap.put("msg", "");
			hashmap.put("code", 0);
			hashmap.put("data", null);
			Map<String, Object> map = null;
			// 账号密码登录
			if (maps.get("buyer_login_account") != null && maps.get("buyer_login_password") != null) {

				map = bean.select_Buyer_Login_Account_Token(maps);

			}
			// 人脸登录
			if (maps.get("buyer_login_face_token") != null) {
				map = bean.select_Buyer_Login_Account_Token(maps);
				System.out.println(map.toString());
			}
			// 手机登录
			if (maps.get("buyer_mobile") != null) {
				map = bean.select_Buyer_Login_buyer_mobile(maps);
			}
			if (map.get("buyer_uuid") != null) {
				hashmap.put("status", "success");
				hashmap.put("data", map);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hashmap;
	}

}
