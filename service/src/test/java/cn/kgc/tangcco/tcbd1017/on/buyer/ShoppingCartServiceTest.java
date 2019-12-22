package cn.kgc.tangcco.tcbd1017.on.buyer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.alibaba.fastjson.JSON;

import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.buyer.ShoppingCartService;
import cn.kgc.tangcco.tcbd1017.on.buyer.impl.ShoppingCartServiceImpl;

/**
	 * @author DU MING
	 * @version 1.0	2019年12月15日	上午11:05:32
	 */

public class ShoppingCartServiceTest {
	private static ShoppingCartService shoppingCartService;
	private static ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("ApplicationContext_on.xml");
	static {
		try {
			shoppingCartService = (ShoppingCartService) classPathXmlApplicationContext.getBean(ShoppingCartService.class.getSimpleName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	ShoppingCartServiceImpl scsi = new ShoppingCartServiceImpl();
	@Test
	public void queryAllShoppingCartInfoByBuyerIdTest() {
		String json = "{\"buyer_id\":1,\"goods_name\":\"米\",\"enableFuzzySelect\":0}";
    	Map map = JSON.parseObject(json, Map.class);
		
		Map<String, Object> result = shoppingCartService.queryAllShoppingCartInfoByBuyerId(map);
		String status = (String)result.get("status");
		int count = (int)result.get("count");
		String msg = (String)result.get("msg");
		List<Map<String, Object>> data = (List<Map<String, Object>>)result.get("data");
		if (0 == data.size()) {
			System.out.println("Dao层无结果");
			System.exit(0);
		}
		
		Iterator<Map<String, Object>> it = data.listIterator();
    	while (it.hasNext()) {
			Map<String, Object> resultData = (Map<String, Object>) it.next();
			Set<String> keySet = resultData.keySet();
			Iterator<String> itSet = keySet.iterator();
			while (itSet.hasNext()) {
				String string = (String) itSet.next();
				System.out.print(string + "\t");
				System.out.print(resultData.get(string) + "\t");
			}
			System.out.println();
		}
    	System.out.println(count);
	}
	
	@Test
	public void addShoppingCartTest() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("buyerId",1);
		map.put("goodsId", 100);
		map.put("amountOfGoods", 1);
		
		Map<String, Object> result = shoppingCartService.addShoppingCart(map);
		String status = (String)result.get("status");
		String msg = (String)result.get("msg");
		
		System.out.print("添加方法状态" + status + "\t");
		System.out.println("添加方法的信息" + msg);
	}
	
	
	
	@Test
	public void modifyShoppingCart() {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("goodsId",2);
		queryMap.put("buyerId", 1);
		queryMap.put("amountOfGoods", 456);
		
		Map<String, Object> result = shoppingCartService.modifyShoppingCart(queryMap);
		String status = (String)result.get("status");
		System.out.println(status);
	}
	
	@Test
	public void removeShoppingCartTest() {
		Map<String, Object> queryMap = new HashMap<String, Object>();
		queryMap.put("goodsId",2);
		queryMap.put("buyerId", 1);
		
		Map<String, Object> result = shoppingCartService.removeShoppingCart(queryMap);
		String status = (String)result.get("status");
		System.out.println(status);
	}
	
	
	
	
	
	@Test
	public void existInShoppingCartByBuyerId() {
		scsi.existInShoppingCartByBuyerId(1, 1);
		scsi.existInShoppingCartByBuyerId(1, 2);
		scsi.existInShoppingCartByBuyerId(1, 3);
		scsi.existInShoppingCartByBuyerId(4, 2);
		scsi.existInShoppingCartByBuyerId(1, 1000);
		scsi.existInShoppingCartByBuyerId(4, 6);
		
//		System.out.println(result);
//		System.out.println(result1);
//		System.out.println(result2);
//		System.out.println(result3);
//		System.out.println(result4);
//		System.out.println(result5);
	}
}


