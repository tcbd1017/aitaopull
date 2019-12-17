package cn.kgc.tangcco.tcbd1017.on.system.text;
/**
* @author 许佳瑞
* @version 创建时间：2019年12月17日 上午8:51:04
* @edition 1.0
* @Description 类描述
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import cn.kgc.tangcco.tcbd1017.on.pojo.ShopIncomeInfo;
import cn.kgc.tangcco.tcbd1017.on.system.FinanceService;
import cn.kgc.tangcco.tcbd1017.on.system.impl.FinanceServiceImpl;


public class FinanceServiceTest {
	FinanceService financeService =new FinanceServiceImpl();
	@Test
	public void test01() {
		Map<String, Object> map =new HashMap<String, Object>();
		map.put("seller_id", 1);
		map.put("shop_income_money", 25588566);
		Map<String, Object> map2 =new HashMap<String, Object>();
		map2=financeService.modifySetMealState(map);
		System.out.println(map2.get("status"));
	}
	
	@Test
	public void test02() {
		Map<String, Object>map =new HashMap<String, Object>();
		map=financeService.queryIncome();
		System.out.println(map.size());
		List<ShopIncomeInfo> list =new ArrayList<ShopIncomeInfo>();
		list=(List<ShopIncomeInfo>) map.get("data");
		System.out.println(list.toString());
		Iterator<ShopIncomeInfo> it = list.iterator();
		while (it.hasNext()) {
			ShopIncomeInfo shopIncomeInfo = (ShopIncomeInfo) it.next();
			System.out.println(shopIncomeInfo);
		}
	}
	@Test
	public void test03() {
		Map<String, Object>map =new HashMap<String, Object>();
		map=financeService.queryExpenditure();
		System.out.println(map.get("status"));
		
	}
	
}
