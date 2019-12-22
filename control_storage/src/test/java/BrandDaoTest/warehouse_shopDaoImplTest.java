package BrandDaoTest;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.junit.Test;

import cn.kgc.tangcco.dao.impl.warehouse_shopDaoImpl;
import cn.kgc.tangcco.lihaozhe.commons.jdbc.PageRang;

public class warehouse_shopDaoImplTest {
	warehouse_shopDaoImpl warehouse_shopDaoImpl = new warehouse_shopDaoImpl();
	
	//查询仓库与店铺的关联表 条件 按商家 查询 按购买年月 查询
	@Test
	public void selectCnagKuYuDianPuGuanLianBiao() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("shop_uuid","店铺uuid002");
		map.put("month",12);
		 List<Map<String, Object>> selectCnagKuYuDianPuGuanLianBiao = warehouse_shopDaoImpl.selectCnagKuYuDianPuGuanLianBiao(map);
		 for (Map<String, Object> map2 : selectCnagKuYuDianPuGuanLianBiao) {
			System.out.println(map2.toString());
		}
	}
	
	//查 看 详 情 管理员对以卖仓库查询详情 warehouse_shop 与 shop 与 warehouse
	@Test
	public void chakanxiangqing() {
		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("warehouse_id", 1);
		//map.put("warehouse_shop_warehouseuuid", "仓库uuid005");
		Map<String, Object> chakanxiangqing = warehouse_shopDaoImpl.chakanxiangqing(map);
		List object = (List)chakanxiangqing.get("chakanxiangqing");
		ListIterator it = object.listIterator();
		while (it.hasNext()) {
			Object object2 = (Object) it.next();
			System.out.println(object2);
		}
	}
	
	//家查看自家仓库剩余容量 条件 1 按仓库uuid
	@Test
	public void selectShengYuRongLiang() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("PageRang", new PageRang(1,5));
		//map.put("warehouse_id", 1);
		map.put("warehouse_shop_warehouseuuid", "仓库uuid005");
		Map<String, Object> selectShengYuRongLiang = warehouse_shopDaoImpl.selectShengYuRongLiang(map);
		List object = (List)selectShengYuRongLiang.get("chakanxiangqing");
		ListIterator it = object.listIterator();
		while (it.hasNext()) {
			Object object2 = (Object) it.next();
			System.out.println(object2);
			
		}
	}
	//warehouse_shop 查看仓库剩余时间 条件
	@Test
	public void selectShengYuTime() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("PageRang", new PageRang(1,5));
		map.put("warehouse_shop_warehouseuuid", "仓库uuid005");
		Map<String, Object> selectShengYuTime = warehouse_shopDaoImpl.selectShengYuTime(map);
		List object = (List)selectShengYuTime.get("chakanxiangqing");
		ListIterator it = object.listIterator();
		while (it.hasNext()) {
			Object object2 = (Object) it.next();
			System.out.println(object2);
			
		}
	}
	//购买仓库 插入
	@Test
	public void goumaiCangKu() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("warehouse_shop_warehouse_id", 1);
		map.put("warehouse_shop_shop_id", 3);
		map.put("warehouse_shop_buyyear", 2);
		map.put("warehouse_shop_warehousesize", 1000000);
		Map<String, Object> goumaiCangKu = warehouse_shopDaoImpl.goumaiCangKu(map);
		if ((int)goumaiCangKu.get("i")>0) {
			System.out.println("成功");
		}else {
			System.out.println("bu成功");
		}
	}
	
	//查询以卖仓库个数 以类别作为条件查询
	@Test
	public void YiFenLeiChaXunYiMaiCnagKuGeShu() {
		Map<String, Object> map = new HashMap<String,Object>();
		//map.put("warehouse_shop_warehouse_id", 2);
		Map<String, Object> YiFenLeiChaXunYiMaiCnagKuGeShu = (Map<String, Object>) warehouse_shopDaoImpl.YiFenLeiChaXunYiMaiCnagKuGeShu(map);
		System.out.println(YiFenLeiChaXunYiMaiCnagKuGeShu.get("i"));
	}
	
	//查询不同类别未卖仓库个数 warehouse 和 waerhouseresour
	@Test
	public void YiFenLeiChaXunShuYuCangKuDeShuLiang() {
		Map<String, Object> map = new HashMap<String,Object>();
		Map<String, Object> YiFenLeiChaXunShuYuCangKuDeShuLiang = warehouse_shopDaoImpl.YiFenLeiChaXunShuYuCangKuDeShuLiang(map);
		System.out.println(YiFenLeiChaXunShuYuCangKuDeShuLiang.toString());
	}
	
	@Test
	public void selectCangKuGenjuShangJiao() {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("shop_uuid", "店铺uuid003");
		map.put("PageRang", new PageRang(1,20));
		Map<String, Object> selectCangKuGenjuShangJiao = warehouse_shopDaoImpl.selectCangKuGenjuShangJiao(map);
		List<Map<String, Object>> list2=(List<Map<String, Object>>) selectCangKuGenjuShangJiao.get("chakanxiangqing");
		for (Map<String, Object> map2 : list2) {
			System.out.println(map2.toString());
		}
		
		
	}
	
	
	
	
	
	//查询所有以卖仓库个数 无条件
	@Test
	public void Count() {
		Map<String, Object> map = new HashMap<String,Object>();
		Map<String, Object> count = warehouse_shopDaoImpl.Count(map);
		System.out.println(count);
	}
	
	//根据月份查询本月卖出仓库数
	@Test
	public void YiYueFenChaXunYiMaiCnagKuGeShu() {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("month", 8);
		List<Map<String, Object>> yiYueFenChaXunYiMaiCnagKuGeShu = warehouse_shopDaoImpl.YiYueFenChaXunYiMaiCnagKuGeShu(map);
		for (Map<String, Object> map2 : yiYueFenChaXunYiMaiCnagKuGeShu) {
			System.out.println(map2.toString());
//			for (String key : map2.keySet()) {
//				System.out.println(key+"      :"+map2.get(key));
//			}
		}
	}
	
	//根据购买时间升序排列所有已卖仓库详情
	@Test
	public void ChaXunYueFen() {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("PageRang", new PageRang(1,5));
		List<Map<String, Object>> chaXunYueFen = (List<Map<String, Object>>) warehouse_shopDaoImpl.ChaXunYueFen(map);
		System.out.println(chaXunYueFen);
	}

	//修改仓库容量
	@Test
	public void updateCangKuRongLiang() {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("number", 10000);
		map.put("warehouse_shop_warehouseuuid", "仓库uuid001");
		int updateCangKuRongLiang = warehouse_shopDaoImpl.updateJianCangKuRongLiang(map);
		System.out.println(updateCangKuRongLiang);
	}
}
