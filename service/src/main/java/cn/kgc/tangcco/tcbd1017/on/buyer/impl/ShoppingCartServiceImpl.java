package cn.kgc.tangcco.tcbd1017.on.buyer.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.buyer.ShoppingCartDao;
import cn.kgc.tangcco.tcbd1017.on.buyer.ShoppingCartService;
import cn.kgc.tangcco.tcbd1017.on.pojo.ShoppingCart;

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
		returnedMap.put("count", 0);
		returnedMap.put("msg", null);
		returnedMap.put("data", null);
		
//		Integer goodsId = null;
		Integer buyerId = null;
		String goodsName = null;
		// 直接拿到传入的物品Id
//		if (null != map.get("goodsId") && map.get("goodsId") instanceof Integer) {
//			goodsId = (int)map.get("goodsId"); 
//		}
		// 根据买家名称拿到买家的id
		System.out.println("进入service成功");
		if (null != map.get("buyerId") && map.get("buyerId") instanceof Integer) {
			buyerId = (int)map.get("buyerId"); 
			System.out.println("放入成功");
			
		}		
		// 看前台是否传入了模糊查询需求
		if (null != map.get("goodsName") && map.get("goodsName") instanceof String) {
			goodsName = (String)map.get("goodsName"); 
		}
		
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("buyer_id", buyerId);
		queryMap.put("enableFuzzySelect", 0);
		queryMap.put("goods_name", null);
		if (null != goodsName) {
			queryMap.put("enableFuzzySelect", 1);
			queryMap.put("goods_name", map.get("goodsName"));
		}
		
		List<Map<String, Object>> list = shoppingCartDao.selectShoppingCartInfoByBuyerId(queryMap);
		if (!list.isEmpty()) {
			returnedMap.put("status", "success");
			returnedMap.put("msg", "查询成功");
			returnedMap.put("data", list);
			returnedMap.put("count", shoppingCartDao.getShoppingCartCount());
			return returnedMap;
		}
		
		returnedMap.put("msg", "service层服务开小差了，马上回来");
		return returnedMap;
	}
	
	
	@Override
	public Map<String, Object> queryAllShoppingCartInfoByBuyerIdAddUrl(Map<String, Object> map) {
		Map<String, Object> returnedMap = new HashMap<String, Object>();
		returnedMap.put("status", "failed");
		returnedMap.put("count", 0);
		returnedMap.put("msg", null);
		returnedMap.put("data", null);
		
//		Integer goodsId = null;
		Integer buyerId = null;
		String goodsName = null;
		// 直接拿到传入的物品Id
//		if (null != map.get("goodsId") && map.get("goodsId") instanceof Integer) {
//			goodsId = (int)map.get("goodsId"); 
//		}
		// 根据买家名称拿到买家的id
		if (null != map.get("buyerId") && map.get("buyerId") instanceof Integer) {
			buyerId = (int)map.get("buyerId"); 
			
		}		
//		// 看前台是否传入了模糊查询需求
//		if (null != map.get("goodsName") && map.get("goodsName") instanceof String) {
//			goodsName = (String)map.get("goodsName"); 
//		}
		
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("buyer_id", buyerId);
		queryMap.put("enableFuzzySelect", 0);
//		queryMap.put("goods_name", null);
//		if (null != goodsName) {
//			queryMap.put("enableFuzzySelect", 1);
//			queryMap.put("goods_name", map.get("goodsName"));
//		}
		
		List<Map<String, Object>> list = shoppingCartDao.selectShoppingCartInfoByBuyerIdAddUrl(queryMap);
		if (!list.isEmpty()) {
			returnedMap.put("status", "success");
			returnedMap.put("msg", "查询成功");
			returnedMap.put("data", list);
			returnedMap.put("count", shoppingCartDao.getShoppingCartCount());
			return returnedMap;
		}
		
		returnedMap.put("msg", "service层服务开小差了，马上回来");
		return returnedMap;
	}

	@Override
	public Map<String, Object> addShoppingCart(Map<String, Object> map) {
		Map<String, Object> returnedMap = new HashMap<String, Object>();
		int buyerId = (int)map.get("buyerId");
		int goodsId = (int)map.get("goodsId");
		Map<String, Object> exist = existInShoppingCartByBuyerId(buyerId, goodsId);
		switch ((String)exist.get("status")) {
		case "success":
			int amountInDatabase = (int)exist.get("amountOfGoods");
			int amountPassedIn = (int)map.get("amountOfGoods");
			int newAmount = amountInDatabase + amountPassedIn;
			System.out.println(newAmount);
			map.put("amountOfGoods", newAmount);
			returnedMap = modifyShoppingCart(map);
			returnedMap.put("msg", "已经存在于购物车了，转到修改数量方法了哦");
			break;

		case "failed":
			returnedMap.put("status", "failed");
			returnedMap.put("msg", null);
			
			int amountOfGoods = (int)map.get("amountOfGoods");
			
			// 根据传入的信息打包一个购物车的对象
			ShoppingCart shoppingCart = new ShoppingCart(0,buyerId,goodsId,amountOfGoods,new Date(),new Date(),2);
			
			int result = shoppingCartDao.insertShoppingCart(shoppingCart);
			
			if (result == 0) {
				returnedMap.put("status", "failed");
				returnedMap.put("msg", "Dao层执行不成功");
			} else {
				returnedMap.put("status", "success");
				returnedMap.put("msg", "恭喜小主驯服Dao层");
			}
			
			
			break;
		default:
			break;
		}
		return returnedMap;
	}

	@Override
	public Map<String, Object> modifyShoppingCart(Map<String, Object> map) {
		Map<String, Object> returnedMap = new HashMap<String, Object>();
		returnedMap.put("status", "failed");
		returnedMap.put("msg", null);
		
		int buyerId = (int)map.get("buyerId");
		int goodsId = (int)map.get("goodsId");
		int amountOfGoods = (int)map.get("amountOfGoods");
		
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.setGoods_id(goodsId);
		shoppingCart.setBuyer_id(buyerId);
		shoppingCart.setAmount_of_goods(amountOfGoods);
		shoppingCart.setShopping_cart_update_time(new Date());
		shoppingCart.setShopping_cart_status(2);
		
		int result = shoppingCartDao.updateShoppingCartInfo(shoppingCart);
		if (result == 1) {
			returnedMap.put("status", "success");
			returnedMap.put("msg", "恭喜小主驯服Dao层更新方法");
			return returnedMap;
		}
		
		returnedMap.put("msg", "哦豁,Dao层说更新失败了");
		return returnedMap;
	}

	@Override
	public Map<String, Object> removeShoppingCart(Map<String, Object> map) {
		Map<String, Object> returnedMap = new HashMap<String, Object>();
		returnedMap.put("status", "failed");
		returnedMap.put("msg", null);
		
		int buyerId = (int)map.get("buyerId");
		int goodsId = (int)map.get("goodsId");
		
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.setGoods_id(goodsId);
		shoppingCart.setBuyer_id(buyerId);
		shoppingCart.setShopping_cart_update_time(new Date());
		shoppingCart.setShopping_cart_status(1);
		
		
		int result = shoppingCartDao.updateShoppingCartInfo(shoppingCart);
		if (result == 1) {
			returnedMap.put("status", "success");
			returnedMap.put("msg", "恭喜小主驯服Dao层删除方法");
			return returnedMap;
		}
		
		returnedMap.put("msg", "哦豁,Dao层说删除失败了");
		return returnedMap;
		
	}

	
	public Map<String, Object> existInShoppingCartByBuyerId (int buyerId ,int goodsId) {
		System.out.println("进入检查是否存在方法");
		Map<String, Object> returnedMap = new HashMap<String, Object>();
		// 默认不存在
		returnedMap.put("status", "failed");
		returnedMap.put("amountOfGoods", 0);
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("buyer_id", buyerId);
		queryMap.put("enableFuzzySelect", 0);
		
		List<Map<String, Object>> list = shoppingCartDao.selectShoppingCartInfoByBuyerId(queryMap);
		Iterator<Map<String, Object>> it = list.listIterator();
		while (it.hasNext()) {
			Map<String, Object> map = (Map<String, Object>) it.next();
			int resultGoodsId = (int)map.get("goods_id");
			System.out.println("goodsId" + goodsId);
			System.out.println("resultGoodsId" + resultGoodsId);
			if (goodsId == resultGoodsId) {
				// 存在则返回存在
				returnedMap.put("status", "success");
				returnedMap.put("amountOfGoods", map.get("amount_of_goods"));
				System.out.print("检查方法");
				System.out.println(map.get("amount_of_goods"));
				return returnedMap;
			}
		}
		return returnedMap;
	}

}


