package cn.kgc.tangcco.tcbd1017.on.buyer.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.buyer.ShoppingCartDao;
import cn.kgc.tangcco.tcbd1017.on.buyer.ShoppingCartService;

	/**
	 * @author DU MING
	 * @version 1.0	2019年12月13日	下午1:52:53
	 */

public class ShoppingCartServiceImpl implements ShoppingCartService{
	private static ShoppingCartDao shoppingCartDao = null;
    static {
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(
                "ApplicationContext_on.xml");
        try {
            shoppingCartDao = (ShoppingCartDao) classPathXmlApplicationContext.getBean(ShoppingCartDao.class.getSimpleName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
		

	@Override
	public Map<String, Object> queryAllShoppingCartInfoByBuyerId(Map<String, Object> map) {
		Map<String, Object> returnedMap = new HashMap<String, Object>();
		returnedMap.put("status", "failed");
		returnedMap.put("msg", null);
		returnedMap.put("data", null);
		// 根据物品名称获取到物品id
		String goodsId = null; // get goods_name
		// 根据买家名称拿到买家的id
		String buyerId = null; // get buyer_name
		// 看前台是否传入了模糊查询需求
		String goodsName = null; // get goods_name
		
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("buyer_id", buyerId);
		queryMap.put("goods_name", goodsName);
		queryMap.put("enableFuzzySelect", 0);
		if (!StringUtils.isEmpty(goodsName)) {
			queryMap.put("enableFuzzySelect", 1);
			queryMap.put("goods_name", map.get("goodsName"));
		}
		
		List<Map<String, Object>> list = shoppingCartDao.selectShoppingCartInfoByBuyerId(queryMap);
		if (!list.isEmpty()) {
			returnedMap.put("status", "success");
			returnedMap.put("msg", "查询成功");
			returnedMap.put("data", list);
			return returnedMap;
		}
		
		returnedMap.put("msg", "service层服务开小差了，马上回来");
		return returnedMap;
	}

	@Override
	public int addShoppingCart(Map<String, Object> map) {
		
		
		
		return 0;
	}

	@Override
	public int modifyShoppingCart(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeShoppingCart(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

}


