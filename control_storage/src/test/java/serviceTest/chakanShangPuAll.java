package serviceTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import cn.kgc.tangcco.pojo.Shop;
import cn.kgc.tangcco.service.impl.ShopServiceImpl;

public class chakanShangPuAll {
	ShopServiceImpl ss=new ShopServiceImpl();
	@Test
	public void test1() {
		Map<String,Object> map=new HashMap<>();
		//map.put("shop_id", 1);
		Map<String, Object> chakanShangPuAll = ss.chakanShangPuAll(map);
		 List<Shop> list=  (List<Shop>) chakanShangPuAll.get("data");
		 for (Shop shop : list) {
			System.out.println(shop.toString());
		}
	}
}
