package serviceTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.PageRang;
import cn.kgc.tangcco.service.GoodsService;
import cn.kgc.tangcco.service.impl.GoodsServiceImpl;
import cn.kgc.tangcco.service.impl.ShangJiaAndEMpmLoginImpl;

public class GoodsServiceTest {
	
	ShangJiaAndEMpmLoginImpl aaa=new ShangJiaAndEMpmLoginImpl();
	@Test
	public void test() {
		GoodsService goodsService = new GoodsServiceImpl();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("PageRang", new PageRang(1, 20));
		map.put("account", "niuwei");
		//map.put("shop_id", 1);
//		map.put("goods_count", 100);
//		map.put("type_name", "电");
//		map.put("model_name", "6");
//		map.put("brand_name", "电脑");
		
		Map<String, Object> selectAllgoods = goodsService.SelectAllgoods(map);
		switch (selectAllgoods.get("status").toString()) {
		
		case "success":
			System.out.println(selectAllgoods.get("data"));
			break;

		default:
			System.out.println("没有数据");
			break;
		}
	}
	
	@Test
	public void test1() {
		Map<String, Object> empLogin = aaa.EmpLogin("niuwei", "1f4a79344c3e1f58");
		List  list=(List) empLogin.get("data");
		for (Object object : list) {
			System.out.println(object.toString());
		}
	}
}
