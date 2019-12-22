package BrandDaoTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import cn.kgc.tangcco.dao.impl.ChuKuDaoImpl;
import cn.kgc.tangcco.lihaozhe.commons.jdbc.PageRang;
import cn.kgc.tangcco.pojo.Brand;
import cn.kgc.tangcco.pojo.Chuku;
import cn.kgc.tangcco.pojo.Model;
import cn.kgc.tangcco.pojo.Type;

public class ChukuTest {
	
	@Test
	public void test1() {
		ChuKuDaoImpl aa=new ChuKuDaoImpl();
		Map<String,Object>map=new HashMap<String, Object>();
		map.put("account", "niuwei");
		//map.put("Shop", "店铺uuid001");
		map.put("flag", 2);
		map.put("uuid", "出库记录uuid005");
		map.put("page", new PageRang(1,5));
		List<Chuku> selectChuKu = aa.selectChuKu(map);
		for (Chuku chuku : selectChuKu) {
			System.out.println(chuku.toString());
		}
	}
	
	@Test
	public void test2() {
		ChuKuDaoImpl aa=new ChuKuDaoImpl();
		Map<String,Object>map=new HashMap<String, Object>();
		Chuku aaa=new Chuku();
		
		aaa.setBrand(new Brand(2,null));
		aaa.setType(new Type(1,null));
		Model m=new Model();
		m.setModelId(9);
		aaa.setModel(m);
		aaa.setChukuGogoodsrecoredCount(100);
		aaa.setChukuShangpuuuid("qqqq");
		
		aaa.setChukuCangkuuuid("aaaaaaaaa");
		map.put("Chuku",aaa );
		int insertChuKu = aa.insertChuKu(map);
		System.out.println(insertChuKu);
		
	}
	@Test
	public void test3() {
		ChuKuDaoImpl aa=new ChuKuDaoImpl();
		Map<String,Object>map=new HashMap<String, Object>();
		map.put("zhuangtai", 2);
		map.put("chuku_chukujiluuuid","出库记录uuid005" );
		int xiuGaiZhuangTaiChuKu = aa.XiuGaiZhuangTaiChuKu(map);
	}
	
	@Test
	public void test4() {
		ChuKuDaoImpl aa=new ChuKuDaoImpl();
		Map<String,Object>map=new HashMap<String, Object>();
		map.put("flag", 2);
		
		
		int selectJianCeNum = aa.selectJianCeNum(map);
		
		System.out.println(selectJianCeNum);
	}
	
	
	
}
