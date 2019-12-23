package BrandDaoTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import cn.kgc.tangcco.dao.impl.JianCeDaoImpl;
import cn.kgc.tangcco.lihaozhe.commons.jdbc.PageRang;
import cn.kgc.tangcco.pojo.Brand;
import cn.kgc.tangcco.pojo.Jiance;
import cn.kgc.tangcco.pojo.Model;
import cn.kgc.tangcco.pojo.Type;

public class JianCeTest {
	
	@Test
	public void test1() {
		JianCeDaoImpl aa=new JianCeDaoImpl();
		Map<String,Object>map=new HashMap<>();
		map.put("account", "shouji");
		map.put("chuku_shangpuuuid", "店铺uuid001");
		map.put("page", new PageRang(1,20));
		List<Jiance> selectZhuCe = aa.selectZhuCe(map);
		for (Jiance jiance : selectZhuCe) {
			System.out.println(jiance.toString());
		}
	}
	
	@Test
	public void test2() {
		JianCeDaoImpl aa=new JianCeDaoImpl();
		Map<String,Object>map=new HashMap<>();
		Jiance ce=new Jiance();
		
		ce.setJianceComegoodsrecoredCount(122);
		Type y=new Type();
		y.setTypeId(1);
		
		ce.setType(y);
		
		Brand b=new Brand();
		b.setBrandId(2);
		
		ce.setBrand(b);
		
		Model m=new Model();
		m.setModelId(9);
		
		ce.setModel(m);
		ce.setJianceShangpuuuid("qqqq");
		
		ce.setJianceCangkuuuid("12345654323443");
		map.put("jiance", ce);
		
		int insertJianCeBiao = aa.insertJianCeBiao(map);
		System.out.println(insertJianCeBiao);
		
		
	}
	@Test
	public void test3() {
		JianCeDaoImpl aa=new JianCeDaoImpl();
		Map<String,Object>map=new HashMap<>();
		map.put("zhuangtai",2 );
		map.put("jiance_rukujiluuuid","入库记录uuid002" );
		int xiuGaiZhuangTaiRuKu = aa.XiuGaiZhuangTaiRuKu(map);
		System.out.println(xiuGaiZhuangTaiRuKu);
		
	}
	@Test
	public void test4() {
		JianCeDaoImpl aa=new JianCeDaoImpl();
		Map<String,Object>map=new HashMap<>();
		
		map.put("Shop", "店铺uuid001");
		int selectJianCeNum = aa.selectJianCeNum(map);
		System.out.println(selectJianCeNum);
	}
	
}
