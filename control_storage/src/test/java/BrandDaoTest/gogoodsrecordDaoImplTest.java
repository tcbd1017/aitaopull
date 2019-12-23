package BrandDaoTest;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import cn.kgc.tangcco.dao.impl.GogoodSrecordDaoImpl;
import cn.kgc.tangcco.lihaozhe.commons.jdbc.PageRang;
import cn.kgc.tangcco.pojo.Chuku;
import cn.kgc.tangcco.pojo.Gogoodsrecord;
import cn.kgc.tangcco.pojo.Jiance;
import cn.kgc.tangcco.pojo.Model;
import cn.kgc.tangcco.pojo.RecordMonthAndSum;
import cn.kgc.tangcco.pojo.SelectCountByType;

/**
* @author 作者 :牛伟
* @version 创建时间：2019年12月15日 下午10:32:46
* 类说明
*/
public class gogoodsrecordDaoImplTest {
	
	GogoodSrecordDaoImpl impl = new GogoodSrecordDaoImpl();
	Map<String,Object> map = new HashMap<String, Object>();
	@Test
	public void selectRuKuJiLuTest() throws SQLException {
		map.put("pageRang", new PageRang(1,10));
	//	map.put("warehouseuuid", "仓库uuid001");
	//	map.put("shopuuid", "店铺uuid004");
	//	map.put("empname", "王五" );	
	//	map.put("year", "2019");
	//	map.put("month", "12");
		Map<String, Object> map2 = impl.selectChuKuJiLu(map);
		List<Gogoodsrecord> list = (List) map2.get("list");
		Iterator<Gogoodsrecord> e = list.iterator();
		while (e.hasNext()) {
			Gogoodsrecord gogoodSrecord = (Gogoodsrecord) e.next();
			System.out.println(gogoodSrecord);
		}
	}
	
	@Test
	public void selectRuKuJiLuBingZhuangTuTest() throws SQLException {
		 List<SelectCountByType> selectRChuKuJiLuBingZhuangTu = impl.selectRChuKuJiLuBingZhuangTu();
		 
		for (SelectCountByType object : selectRChuKuJiLuBingZhuangTu) {
			System.out.println(object);
		}
	}
	
	@Test
	public void selectRuKuJiLu2() throws SQLException {
		List<RecordMonthAndSum> fenLeiChaXun = impl.FenLeiChaXun();
		
		for (RecordMonthAndSum object : fenLeiChaXun) {
			System.out.println(object);
		}
	}
	
	@Test
	public void insertJiLuChuKu() throws SQLException {
		Chuku chuku = new Chuku();
		Model model = new Model();
		model.setModelId(40);
		chuku.setChukuGogoodsrecoredCount(11);
		chuku.setChukuCangkuuuid("仓库uuid003");
		chuku.setChukuCangkuuuid("入库记录uuid005");
		chuku.setModel(model);
		map.put("chuku", chuku);
		map.put("empid", 2);
		map.put("shopid", 3);
		int insertJiLuChuKu = impl.insertJiLuChuKu(map);
		System.out.println(insertJiLuChuKu);
	}
	
	@Test
	public void selectGoRecordCount() throws SQLException {
		//	map.put("warehouseuuid", "仓库uuid001");
		//	map.put("shopuuid", "店铺uuid004");
		//	map.put("empname", "王五" );	
		//	map.put("year", "2019");
		//	map.put("month", "12");
		 int count = impl.selectGoRecordCount(map);
			System.out.println(count);
	}
}
