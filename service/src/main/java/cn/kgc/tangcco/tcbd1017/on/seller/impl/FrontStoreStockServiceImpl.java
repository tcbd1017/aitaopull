package cn.kgc.tangcco.tcbd1017.on.seller.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.seller.FrontStoreStockGoodsDaoIns;
import cn.kgc.tangcco.tcbd1017.on.seller.FrontStoreStockServiceIns;

/**
 * @author 作者 Your-Name: 刘煜
 * @version 创建时间：2019年12月16日 上午10:01:12 类说明
 */
public class FrontStoreStockServiceImpl implements FrontStoreStockServiceIns {
	static FrontStoreStockGoodsDaoIns bean = null;
	static {
		try {
			ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(
					"ApplicationContext_on.xml");
			bean = (FrontStoreStockGoodsDaoIns) classPathXmlApplicationContext.getBean("FrontStoreStockGoodsDaoIns");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Map<String, Object> add_Store_Stock_Goods(Map<String, Object> maps) {
		Map<String, Object> map = new HashMap<>();
		try {
			map.put("status", "failed");
			map.put("msg", "");
			map.put("code", 0);
			map.put("data", null);
			int insert_seller_Register = bean.insertStockGoods(maps);
			if (insert_seller_Register > 0) {
				map.put("status", "success");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
}
