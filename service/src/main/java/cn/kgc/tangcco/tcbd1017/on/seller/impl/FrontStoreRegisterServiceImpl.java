package cn.kgc.tangcco.tcbd1017.on.seller.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.seller.FrontStoreRegisterDaoIns;
import cn.kgc.tangcco.tcbd1017.on.seller.FrontStoreRegisterServiceIns;

/**
 * @author 作者 Your-Name: 刘煜
 * @version 创建时间：2019年12月14日 上午10:45:47 类说明 : 用户开店 （注册）: 店铺信息（添加店铺信息）
 */
public class FrontStoreRegisterServiceImpl implements FrontStoreRegisterServiceIns {
	static FrontStoreRegisterDaoIns bean = null;
	static {
		try {
			ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(
					"ApplicationContext_on.xml");
			bean = (FrontStoreRegisterDaoIns) classPathXmlApplicationContext.getBean("FrontStoreRegisterDaoIns");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 用户开店
	@Override
	public Map<String, Object> add_Seller_Register(Map<String, Object> maps) {
		Map<String, Object> map = new HashMap<>();
		try {
			map.put("status", "failed");
			map.put("msg", "");
			map.put("code", 0);
			map.put("data", null);
			if (bean.select__Store_Uuid(maps) == false && bean.select__Store_Id(maps) == false) {
				if( bean.seller_idcard_token(maps)==false) {
					int insert_seller_Register = bean.insert_seller_Register(maps);
					if (insert_seller_Register > 0) {
						map.put("status", "success");
					}
				}else {
					map.put("msg", "身份证注册过了");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}

	// 商店信息注册
	@Override
	public Map<String, Object> add_Store_Register(Map<String, Object> maps) {
		Map<String, Object> map = new HashMap<>();
		try {
			map.put("status", "failed");
			map.put("msg", "");
			map.put("code", 0);
			map.put("data", null);
			int insert_Store_Register = bean.insert_Store_Register(maps);
			if (insert_Store_Register > 0) {
				map.put("status", "success");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
}
