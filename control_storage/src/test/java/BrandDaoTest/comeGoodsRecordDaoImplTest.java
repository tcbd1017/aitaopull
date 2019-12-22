package BrandDaoTest;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import org.junit.Test;

import cn.kgc.tangcco.dao.impl.comeGoodsRecordDaoImpl;
import cn.kgc.tangcco.lihaozhe.commons.jdbc.PageRang;
import cn.kgc.tangcco.pojo.Comegoodsrecord;
import cn.kgc.tangcco.pojo.Jiance;
import cn.kgc.tangcco.pojo.Model;
import cn.kgc.tangcco.pojo.RecordMonthAndSum;
import cn.kgc.tangcco.pojo.SelectCountByType;

/**
* @author 作者 :牛伟
* @version 创建时间：2019年12月15日 下午10:32:46
* 类说明
*/
public class comeGoodsRecordDaoImplTest {
	
	comeGoodsRecordDaoImpl impl = new comeGoodsRecordDaoImpl();
	Map<String,Object> map = new HashMap<String, Object>();
	@Test
	public void selectRuKuJiLuTest() throws SQLException {
		map.put("pageRang", new PageRang(1,20));
	//	map.put("warehouseuuid", "仓库uuid001");
	//	map.put("shopuuid", "店铺uuid004");
		map.put("empname", "张三" );
		
	//	map.put("year", "2019");
	//	map.put("month", "12");
		Map<String, Object> map2 = impl.selectRuKuJiLu(map);
		List<Comegoodsrecord> list = (List) map2.get("list");
		Iterator<Comegoodsrecord> e = list.iterator();
		while (e.hasNext()) {
			Comegoodsrecord comegoodsrecord = (Comegoodsrecord) e.next();
			System.out.println(comegoodsrecord);
		}
		
	}
	
	@Test
	public void selectRuKuJiLuBingZhuangTuTest() throws SQLException {
		 List<SelectCountByType> selectRuKuJiLuBingZhuangTu = impl.selectRuKuJiLuBingZhuangTu();
		 
		for (SelectCountByType object : selectRuKuJiLuBingZhuangTu) {
			System.out.println(object);
		}
	}
	
	@Test
	public void selectRuKuJiLu2() throws SQLException {
		 List<RecordMonthAndSum> selectRuKuJiLu2 = impl.selectRuKuJiLu2();
		
		for (RecordMonthAndSum object : selectRuKuJiLu2) {
			System.out.println(object);
		}
	}
	
	
	@Test
	public void insertJiLu() throws SQLException {
		Jiance jiance = new Jiance();
		Model model = new Model();
		model.setModelId(40);
		jiance.setJianceComegoodsrecoredCount(11);
		jiance.setJianceCangkuuuid("仓库uuid003");
		jiance.setJianceRukujiluuuid("入库记录uuid005");
		jiance.setModel(model);
		map.put("jiance", jiance);
		map.put("empid", 2);
		map.put("shopid", 3);
		int i= impl.insertJiLu(map);
		
		System.out.println(i);
	}
	
	@Test
	public void selectComeRecordCount() throws SQLException {
		map.put("warehouseuuid", "仓库uuid001");
	//	map.put("shopuuid", "店铺uuid004");
	//	map.put("empname", "张三" );
		
	//	map.put("year", "2019");
	//	map.put("month", "12");
		 int count = impl.selectComeRecordCount(map);
			System.out.println(count);
	}
}
