package cn.kgc.tangcco.tbd1017.on.buyer;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.buyer.StoreFavoriteService;

/**
 * 
 * 	@author 和朋朋
 * 	@version 1.0<br>
 * 	创建时间：  2019年12月9日 下午11:01:57 <br>
 * 	类描述：逻辑层测试
 *	
 */

public class StoreFavoriteServiceTest {
	private static StoreFavoriteService ss;
	private static ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("ApplicationContext_on.xml");
	static {
		try {
			ss=(StoreFavoriteService) context.getBean(StoreFavoriteService.class.getSimpleName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void queryAllStoreFavorite() {
		Map<String, Object>map=new HashMap<String, Object>();
//		map.put("pr", new PageRang(1, 10));
//		map.put("buyer_id", 1);
		Map<String, Object> map2 = ss.queryStoreFavorite(map);
		System.out.println(map2.get("date").toString());
	}
	@Test
	public void addStoreFavorite() {
		Map<String, Object>map2=new HashMap<String, Object>();
		map2.put("buyer_id",1);
		map2.put("store_id",1);
		map2.put("store_favorite_create_time", new Date());
		map2.put("store_favorite_update_time", new Date());
		Map<String, Object> addmap = ss.addStoreFavorite(map2);
		System.out.println(addmap.get("status").toString());
		
	}
	@Test
	public void removeStoreFavorite() {
		Map<String, Object>map=new HashMap<String, Object>();
		map.put("store_favorite_id", 1);
		Map<String, Object> removeStoreFavorite = ss.removeStoreFavorite(map);
		System.out.println(removeStoreFavorite.get("status").toString());
	}
}
