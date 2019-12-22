package serviceTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import cn.kgc.tangcco.dao.impl.ChuKuDaoImpl;
import cn.kgc.tangcco.dao.impl.GogoodSrecordDaoImpl;
import cn.kgc.tangcco.lihaozhe.commons.jdbc.PageRang;
import cn.kgc.tangcco.pojo.Chuku;
import cn.kgc.tangcco.pojo.Comegoodsrecord;
import cn.kgc.tangcco.pojo.Gogoodsrecord;
import cn.kgc.tangcco.pojo.Goods;
import cn.kgc.tangcco.pojo.Jiance;
import cn.kgc.tangcco.pojo.Model;
import cn.kgc.tangcco.pojo.RecordMonthAndSum;
import cn.kgc.tangcco.pojo.SelectCountByType;
import cn.kgc.tangcco.pojo.Shop;
import cn.kgc.tangcco.pojo.WarehouseShop;
import cn.kgc.tangcco.service.impl.GuanLiYuanRuKuAndChuKuImpl;

public class GuanLiYuan {
	GuanLiYuanRuKuAndChuKuImpl aa=new GuanLiYuanRuKuAndChuKuImpl();
	
	private GogoodSrecordDaoImpl go = new GogoodSrecordDaoImpl();
	
	@Test
	public void testChaKanRuKuBiao() {
		
		
		Map<String,Object>map2=new HashMap<String,Object>();
		map2.put("account", "niuwei");
		//map2.put("chuku_shangpuuuid", "店铺uuid001");
		//map2.put("account", "shouji");
		map2.put("uuid","入库记录uuid011" );
		map2.put("flag",1);
		map2.put("page", new PageRang(1,29));
		Map<String, Object> chaKanRuKuBiao = aa.ChaKanRuKuBiao(map2);
		List<Jiance>list = (List<Jiance>) chaKanRuKuBiao.get("data");
		for (Jiance jiance : list) {
			System.out.println(jiance.toString());
		}
	}
	
	
	@Test
	public void UpdateRuKuZhuangTai() {
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("goods_w_s_uuid", "仓库uuid001");
		map.put("goods_model_id",1 );
		map.put("goods_count",134232);
		map.put("goods_type_id",1 );		
		map.put("goods_brand_id",1);		
		map.put("goods_shop_uuid","店铺uuid001");
		map.put("zhuangtai", 19);
		Goods goods=new Goods();
		Model mo=new Model();
		mo.setModelId((int)map.get("goods_model_id"));
		goods.setModel(mo);
		goods.setGoodsWSUuid((String)map.get("goods_w_s_uuid"));		
		Jiance a=new Jiance();
		a.setJianceRukujiluuuid("入库记录uuid001");
		map.put("jiance", a);
		map.put("empid", 1);
		map.put("goods", goods);
		map.put("jiance_rukujiluuuid","入库记录uuid001" );
		Map<String, Object> updateRuKuZhuangTai = aa.UpdateRuKuZhuangTai(map);
		String object = (String) updateRuKuZhuangTai.get("status");
		System.out.println(object);
	}
	
	@Test
	public void ChaKanAllCangKuUUid() {
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("warehouse_shop_shop_id",1);
		Map<String, Object> chaKanAllCangKuUUid = aa.ChaKanAllCangKuUUid(map);
		List<WarehouseShop>list=(List<WarehouseShop>) chaKanAllCangKuUUid.get("data");
		for (WarehouseShop warehouseShop : list) {
			System.out.println(warehouseShop.toString());
		}
		
	}
	
	
	
	@Test
	public void ChaKanShopJianLue() {
		Map<String, Object> chaKanShopJianLue = aa.ChaKanShopJianLue();
		List<Shop>list=(List<Shop>) chaKanShopJianLue.get("data");
		for (Shop shop : list) {
			System.out.println(shop.toString());
		}
	}
	
	
	
	@Test
	public void ChaXunCangKuJianLue() {
		Map<String,Object>map=new HashMap<>();
		map.put("warehouse_shop_shop_id", 1);
		Map<String, Object> chaKanShopJianLue = aa.ChaXunCangKuJianLue(map);
		List<WarehouseShop>list=(List<WarehouseShop>) chaKanShopJianLue.get("data");
		for (WarehouseShop shop : list) {
			System.out.println(shop.toString());
		}
	}
	
	@Test
	public void ChaXunYueFenYuMeiYuChuHuoLiang() {
		
		Map<String, Object> chaXunYueFenYuMeiYuChuHuoLiang = aa.ChaXunYueFenYuMeiYuChuHuoLiang();
		List<RecordMonthAndSum>list=(List<RecordMonthAndSum>) chaXunYueFenYuMeiYuChuHuoLiang.get("data");
		
		for (RecordMonthAndSum recordMonthAndSum : list) {
			System.out.println(recordMonthAndSum.toString());
		}		
	}
	
	@Test
	public void ChaXunLeiBieYuLeiBieChuHuoLiang() {
		 Map<String, Object> chaXunLeiBieYuLeiBieChuHuoLiang = aa.ChaXunLeiBieYuLeiBieChuHuoLiang();
		List<SelectCountByType>list=(List<SelectCountByType>) chaXunLeiBieYuLeiBieChuHuoLiang.get("data");
		
		for (SelectCountByType recordMonthAndSum : list) {
			System.out.println(recordMonthAndSum.toString());
		}		
	}
	
	
	
	@Test
	public void ChaXunSuoYouChuHuo() {
		Map<String,Object>map=new HashMap<String,Object>();
		//map.put("account", "niuwei");
		map.put("uuid", "出库记录uuid002");
		map.put("chuku_shangpuuuid", "店铺uuid001");
		map.put("flag", 1);
		map.put("account","shouji");
		map.put("page", new PageRang(1,20));
		Map<String, Object> chaXunSuoYouChuHuo = aa.ChaXunSuoYouChuHuo(map);
		List<Chuku> list=(List<Chuku>)chaXunSuoYouChuHuo.get("data");
		
		for (Chuku recordMonthAndSum : list) {
			System.out.println(recordMonthAndSum.toString());
		}		
	}
	
	@Test
	public void XiuGaiChuKuZhuangTai() {
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("goods_shop_uuid","店铺uuid001" );
		map.put("zhuangtai",1232 );
		
		Goods goods=new Goods();
		Model mo=new Model();
		mo.setModelId(1);
		
		goods.setModel(mo);
		
		map.put("goods", goods);
		map.put("empid",21);
		Chuku chuku =new Chuku();
		chuku.setChukuChukujiluuuid("出库记录uuid001");//出库记录
		
		
		map.put("chuku", chuku);
		map.put("goods_count", 123432);
		map.put("goods_w_s_uuid", "仓库uuid001");
		
		goods.setGoodsWSUuid((String)map.get("goods_w_s_uuid"));//仓库uuid
		
		Map<String, Object> xiuGaiChuKuZhuangTai = aa.XiuGaiChuKuZhuangTai(map);
		String aa=(String)xiuGaiChuKuZhuangTai.get("status");
		System.out.println(aa);
	}
	
	
	
	@Test
	public void ChaXunJinHuoZheXianTu() {
		Map<String, Object> chaXunJinHuoZheXianTu = aa.ChaXunJinHuoZheXianTu();
		List<RecordMonthAndSum>list=(List<RecordMonthAndSum>) chaXunJinHuoZheXianTu.get("data");
		for (RecordMonthAndSum recordMonthAndSum : list) {
			System.out.println(recordMonthAndSum.toString());
		}
	}
	
	
	@Test
	public void ChaZunJinHuoBingZhuangTu() {
		Map<String, Object> chaZunJinHuoBingZhuangTu = aa.ChaZunJinHuoBingZhuangTu();
		List<SelectCountByType> selectRuKuJiLu2=(List<SelectCountByType>) chaZunJinHuoBingZhuangTu.get("data");
		for (SelectCountByType recordMonthAndSum : selectRuKuJiLu2) {
			System.out.println(recordMonthAndSum.toString());
		}
	}
	@Test
	public void ChaJinHuoJiLuBiao() {
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("pageRang", new PageRang(1,20));
		Map<String, Object> chaJinHuoJiLuBiao = aa.ChaJinHuoJiLuBiao(map);
		List<Comegoodsrecord>list=(List<Comegoodsrecord>) chaJinHuoJiLuBiao.get("data");
		for (Comegoodsrecord gogoodsrecord : list) {
			System.out.println(gogoodsrecord.toString());
		}
	}
	
	@Test
	public void ChaChuHuoJiLuBiao() {
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("pageRang", new PageRang(1,20));
		Map<String, Object> chaJinHuoJiLuBiao = aa.ChaChuHuoJiLuBiao(map);
		List<Gogoodsrecord>list=(List<Gogoodsrecord>) chaJinHuoJiLuBiao.get("data");
		for (Gogoodsrecord gogoodsrecord : list) {
			System.out.println(gogoodsrecord.toString());
		}
	}
}
