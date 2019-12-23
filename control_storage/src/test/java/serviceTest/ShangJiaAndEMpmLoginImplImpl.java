package serviceTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import cn.kgc.tangcco.pojo.Shop;
import cn.kgc.tangcco.service.impl.ShangJiaAndEMpmLoginImpl;

public class ShangJiaAndEMpmLoginImplImpl {
	ShangJiaAndEMpmLoginImpl aa=new ShangJiaAndEMpmLoginImpl();
	@Test
	public void updateMiMatest1() {
		Map<String,Object>map=new HashMap<>();
		map.put("shop_password", "123456765432");
		map.put("shop_id",2 );
		Map<String, Object> xiuGaiMiMa = aa.XiuGaiMiMa(map);
		String object = (String) xiuGaiMiMa.get("status");
		System.out.println(object);
	}
	
	
	@Test
	public void ShopZhuCetest1() {
		Map<String,Object>map=new HashMap<>();
		map.put("shop_name", "sho7ssaji");
		map.put("shop_address","谈啊" );
		
		map.put("shop_phone","1234565432" );
		
		map.put("shop_password","23456765" );
		Map<String, Object> shopZhuCe = aa.ShopZhuCe(map);
		String aa=(String) shopZhuCe.get("status");
		List<Shop> s= (List<Shop>) shopZhuCe.get("data");
		for (Shop shop : s) {
			System.out.println(shop.toString());
		}
	}
}
