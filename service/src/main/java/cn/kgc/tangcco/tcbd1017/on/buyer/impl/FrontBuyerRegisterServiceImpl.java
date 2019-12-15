package cn.kgc.tangcco.tcbd1017.on.buyer.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.buyer.FrontBuyerRegisterDaoIns;
import cn.kgc.tangcco.tcbd1017.on.buyer.FrontBuyerRegisterServiceIns;



/** 
* @author 作者 Your-Name: 刘煜
* @version 创建时间：2019年12月10日 下午7:33:17 
*    类说明 
*/
public class FrontBuyerRegisterServiceImpl implements FrontBuyerRegisterServiceIns {
	static FrontBuyerRegisterDaoIns frontBuyerRegisterDao=null;
	static {
		try {
			ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("ApplicationContext_on.xml");
			frontBuyerRegisterDao = (FrontBuyerRegisterDaoIns)classPathXmlApplicationContext.getBean("Buyer_and_postage_infoDao");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public Map<String, Object> BuyerRegister(Map<String, Object> maps) throws SQLException {
		Map<String, Object> map= new HashMap<>();
		map.put("status", "failed");
		map.put("msg", "");
		map.put("code", 0);
		map.put("data",null);
		int number= 0 ;
		if(frontBuyerRegisterDao.select_Byyer_Uuid(maps)==false) {
			 //手机号注册
			 if( maps.get("buyer_mobile")!=null) {
				 if(frontBuyerRegisterDao.select_buyer_mobile(maps)==false) {
					 number = frontBuyerRegisterDao.insert_Mobile_Byyer(maps); 
					 map.put("status", "success");
				 }
				 map.put("msg", "该手机号已被注册");
			 }
			//账户密码注册
			 if( maps.get("buyer_login_account")!=null && maps.get("buyer_login_password")!=null) {
				 if(frontBuyerRegisterDao.select_buyer_login_account(maps)==false) {
					 number = frontBuyerRegisterDao.insert_Account_Password_Byyer(maps);
					 if(number>0) {
						 map.put("status", "success");
					 } 
				 }
				 map.put("msg", "该账号已被注册");
			 }
			 //人脸注册
			 if(maps.get("buyer_login_face_token")!=null) {
				 number = frontBuyerRegisterDao.insert_Account_Password_Byyer(maps);
				 if(number>0 ) {
					 map.put("status", "success");
				 }
			 }	
		}
		return map;
	}
	
}
