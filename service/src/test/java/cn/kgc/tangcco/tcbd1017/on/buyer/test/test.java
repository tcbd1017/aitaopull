package cn.kgc.tangcco.tcbd1017.on.buyer.test;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import cn.kgc.tangcco.lihaozhe.commons.date.BaseDate;
import cn.kgc.tangcco.lihaozhe.commons.jdbc.Laypage;
import cn.kgc.tangcco.tcbd1017.on.buyer.impl.GoodsFavoriteServiceImpl;
import cn.kgc.tangcco.tcbd1017.on.pojo.GoodsFavorite;

/**
* @author 张忠华  
* @date 2019年12月13日  
* @version 1.0  
*/
public class test {
	
	public static void main(String[] args) {
		GoodsFavoriteServiceImpl service=new GoodsFavoriteServiceImpl();
		GoodsFavorite good=new GoodsFavorite();
		good.setGoods_favorite_id(18);
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("goods_favorite_id", good.getGoods_favorite_id());
		Map<String, Object> rem = service.removeGoodsFavorite(map);
		System.out.println(rem.get("status"));
	}
	/**
	 * 添加
	 */
	public void add() {
		GoodsFavoriteServiceImpl service=new GoodsFavoriteServiceImpl();
		
		Date date = BaseDate.getDate("2019-11-10 22:10:1");
		Date date1 = BaseDate.getDate("2019-12-12 22:10:1");
		GoodsFavorite good=new GoodsFavorite();
		good.setBuyer_id(5);
		good.setGoods_id(60);
		good.setGoods_favorite_create_time(date);
		good.setGoods_favorite_update_time(date1);
		
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("buyer_id", good.getBuyer_id());
		map.put("goods_id", good.getGoods_id());
		map.put("goods_favorite_create_time", good.getGoods_favorite_create_time());
		map.put("goods_favorite_update_time", good.getGoods_favorite_update_time());
	
			Map<String, Object> addGoodsFavorite = service.addGoodsFavorite(map);
			System.out.println(addGoodsFavorite.get("status"));
	}
	 /*
	 * 删除
	 */
	@Test
	public void dele() {
		GoodsFavoriteServiceImpl service=new GoodsFavoriteServiceImpl();

		Map<String,Object>map=new HashMap<String,Object>();
		map.put("goods_favorite_id",2);
		Map<String, Object> rem = service.removeGoodsFavorite(map);
		System.out.println(rem.get("status"));
	}
	/**
	 *查询
	 */
	@Test
	public void query() {
		GoodsFavoriteServiceImpl service=new GoodsFavoriteServiceImpl();
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("buyer_id", 1);
		map.put("laypage", new Laypage(1, 1));
		Map<String, Object> map2 = service.queryGoodsFavorite(map);
		System.out.println(map2);
	}
}
