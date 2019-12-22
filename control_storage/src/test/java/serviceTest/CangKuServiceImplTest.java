package serviceTest;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.PageRang;
import cn.kgc.tangcco.pojo.BingZhuangTuDuiXiang;
import cn.kgc.tangcco.pojo.WarehouseShop;
import cn.kgc.tangcco.service.CangKuService;
import cn.kgc.tangcco.service.impl.CangKuServiceImpl;

/**
 * @author ZHOUxq<br>
 * @version v1.0<br>
 * @创建时间：2019年12月17日 下午4:54:43<br>
 * @类描述：
 */
public class CangKuServiceImplTest {
	private CangKuService cangKuService = new CangKuServiceImpl();

	// 查询所有未卖仓库
	// 在页面上只显示价格，图片，类型
	// 点击图片 显示，详情
	@Test
	public void ChaXunSuoYouWeiMaiCangKu() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("PageRang", new PageRang(1, 5));
		Map<String, Object> chaXunSuoYouWeiMaiCangKu = cangKuService.ChaXunSuoYouWeiMaiCangKu(map);
//		System.out.println(chaXunSuoYouWeiMaiCangKu.toString());
		Map<String, Object> map2 = (Map<String, Object>) chaXunSuoYouWeiMaiCangKu.get("selectCangKuLeixing");
		List<Map<String, Object>> list = (List<Map<String, Object>>) map2.get("selectCangKuLeixing");
		for (Map<String, Object> map3 : list) {
			System.out.println(map3.toString());
		}
	}

	// 买家查询自家所有仓库的详情
	@Test
	public void ShopChaXunZiJiaCangKu() {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("shop_uuid","店铺uuid003" );
		
		map.put("PageRang", new PageRang(1, 5));
		Map<String, Object> ShopChaXunZiJiaCangKu = cangKuService.ShopChaXunZiJiaCangKu(map);
		Map<String, Object> map2 = (Map<String, Object>) ShopChaXunZiJiaCangKu.get("selectCangKuLeixing");
		List<Map<String, Object>> list = (List<Map<String, Object>>) map2.get("chakanxiangqing");
		for (Map<String, Object> map3 : list) {
			System.out.println(map3.toString());
		}
	}

	// 购买仓库
	@Test
	public void GouMaiCnagKu() {
		Map<String, Object> map = new HashMap<String, Object>();
		// 购买个数（限制为1）
		map.put("number", 1);
		// 购买仓库类型id
		map.put("warehouseresour_id", 1);
		// warehouse_shop 添加购买记录
		// 仓库类型id
		map.put("warehouse_shop_warehouse_id", 1);
		// 商铺 id
		map.put("warehouse_shop_shop_id", 1);
		// 购买时间【单位：年】
		map.put("warehouse_shop_buyyear", 1);
		// 仓库容量
		map.put("warehouse_shop_warehousesize", 100000);
		Map<String, Object> gouMaiCnagKu = cangKuService.GouMaiCnagKu(map);
		Map<String, Object> map2 = (Map<String, Object>) gouMaiCnagKu.get("goumaiCangKu");
		if (map2.get("i") != null && map2.size() > 0) {
			System.out.println(map2);
		} else {
			System.out.println("系统异常~~~");
		}
	}

	// 查询 未卖仓库的总个数
	@Test
	public void ChaKanWeiMaiGeShu() {
		Map<String, Object> map = new HashMap<String, Object>();

		Map<String, Object> chaKanWeiMaiGeShu = cangKuService.ChaKanWeiMaiGeShu(map);
		System.out.println(chaKanWeiMaiGeShu.toString());

		if (chaKanWeiMaiGeShu.get("selectWeiMaiZongGeShu") != null) {
			System.out.println(chaKanWeiMaiGeShu.get("selectWeiMaiZongGeShu"));
		} else {
			System.out.println("系统异常~~~");
		}
	}

	// 查询 仓库记录表 每月 买的仓库数量 首页 折线图
	@Test
	public void CangKuZheXianTu() {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> cangKuZheXianTu = cangKuService.CangKuZheXianTu(map);
//			System.out.println(cangKuZheXianTu.toString());
		List<Map<String, Object>> list = (List<Map<String, Object>>) cangKuZheXianTu.get("selectWeiMaiZongGeShu");
		for (Map<String, Object> map2 : list) {
			System.out.println(map2.toString());
		}

	}

	// 查询 仓库记录表 类型 所购买数量 首页 饼状图
	@Test
	public void CangKuBingZhuangTu() {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> CangKuBingZhuangTu = cangKuService.CangKuBingZhuangTu(map);
//		System.out.println(CangKuBingZhuangTu.toString());
		List<BingZhuangTuDuiXiang> list = (List<BingZhuangTuDuiXiang>) CangKuBingZhuangTu
				.get("yiFenLeiChaXunYiMaiCnagKuGeShu");
		for (BingZhuangTuDuiXiang bingZhuangTuDuiXiang : list) {
			System.out.println(bingZhuangTuDuiXiang.toString());
		}
	}

	// 查询 仓库记录表 类型 未卖数量 首页 柱状图
	@Test
	public void CangKuZhuZHuangTu() {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> CangKuZhuZHuangTu = cangKuService.CangKuZhuZHuangTu(map);
//		System.out.println(CangKuBingZhuangTu.toString());
		Map<String, Object> map2 = (Map<String, Object>) CangKuZhuZHuangTu.get("yiFenLeiChaXunYiMaiCnagKuGeShu");
		List<Map<String, Object>> list = (List<Map<String, Object>>) map2.get("YiFenLeiChaXunShuYuCangKuDeShuLiang");
		for (Map<String, Object> bingZhuangTuDuiXiang : list) {
			System.out.println(bingZhuangTuDuiXiang.toString());
		}
	}

	// 查询 仓库记录表 所有仓库的详情 首页 表格
	@Test
	public void AllCangKu() {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> AllCangKu = cangKuService.AllCangKu(map);
//		System.out.println(CangKuBingZhuangTu.toString());
		List<Map<String, Object>> list = (List<Map<String, Object>>) AllCangKu.get("selectCnagKuYuDianPuGuanLianBiao");
		for (Map<String, Object> map2 : list) {
			System.out.println(map2.toString());
		}
	}

	// 查询仓库所有分类 首页 表格那块的下拉框
	@Test
	public void AllCangKuFenLei() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("PageRang", new PageRang(1, 5));
		Map<String, Object> AllCangKuFenLei = cangKuService.AllCangKuFenLei(map);
//		System.out.println(CangKuBingZhuangTu.toString());
		Map<String, Object> map2 = (Map<String, Object>) AllCangKuFenLei.get("selectCangKuLeixing");
		List<Map<String, Object>> list = (List<Map<String, Object>>) map2.get("selectCangKuLeixing");
		for (Map<String, Object> map21 : list) {
			System.out.println(map21.toString());
		}
	}

	// 查询 购买仓库的所有商家的 uuid 和id 首页 表格那块的下拉框
	@Test
	public void AllGouMaiCangKuShop() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("PageRang", new PageRang(1, 5));
		Map<String, Object> AllGouMaiCangKuShop = cangKuService.AllGouMaiCangKuShop(map);
//		System.out.println(CangKuBingZhuangTu.toString());
		List<WarehouseShop> list = (List<WarehouseShop>) AllGouMaiCangKuShop.get("chakanxiangqingAllCangKu");
		for (WarehouseShop warehouseShop : list) {
			System.out.println(warehouseShop.getWarehouseShopWarehouseuuid());
		}
	}

	// 查询商家所有购买的仓库详情（在商家查询进度的页面 此处是下拉框）
	@Test
	public void ChaKanShopGouMaiDeAllCangKu() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("PageRang", new PageRang(1, 5));
		Map<String, Object> ChaKanShopGouMaiDeAllCangKu = cangKuService.ChaKanShopGouMaiDeAllCangKu(map);
//		System.out.println(CangKuBingZhuangTu.toString());
		Map<String, Object> map2 = (Map<String, Object>) ChaKanShopGouMaiDeAllCangKu.get("selectCangKuGenjuShangJiao");
		List<Map<String, Object>> list = (List<Map<String, Object>>) map2.get("chakanxiangqing");
		for (Map<String, Object> map21 : list) {
			System.out.println(map21.toString());
		}
	}
}
