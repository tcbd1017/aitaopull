package BrandDaoTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import cn.kgc.tangcco.dao.impl.BrandDaoImpl;
import cn.kgc.tangcco.dao.impl.ModelDaoImpl;
import cn.kgc.tangcco.dao.impl.PanDuImpl;
import cn.kgc.tangcco.dao.impl.TypeDaoImpl;
import cn.kgc.tangcco.lihaozhe.commons.bianhao.BaseBianHao;
import cn.kgc.tangcco.pojo.Brand;
import cn.kgc.tangcco.pojo.Model;
import cn.kgc.tangcco.pojo.Type;

public class BrandDaoTest {
	
	
	@Test
	public void test1() {
		BrandDaoImpl aa=new BrandDaoImpl();
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("type",1 );
		
	 List<Brand> selectLeiXing = aa.selectLeiXing(map);
		for (Brand brand : selectLeiXing) {
			System.out.println(brand.toString());
		}
	}
	
	@Test
	public void test1w() {
		String generateUniqueKey = BaseBianHao.generateUniqueKey();
		System.out.println(generateUniqueKey);
	}
	@Test
	public void test2() {
		BrandDaoImpl aa=new BrandDaoImpl();
		
		 Brand selectLeiXingByid = aa.selectLeiXingByid(1);
		System.out.println(selectLeiXingByid.toString());
	}
	
	@Test
	public void test3() {
		ModelDaoImpl aa=new ModelDaoImpl();
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("model_brand_id",1 );
		 List<Model> selectXingHao = aa.selectXingHao(map);
		for (Model model : selectXingHao) {
			System.out.println(model.toString());
		}
	}
	
	@Test

	public void test4() {
		ModelDaoImpl aa=new ModelDaoImpl();
	
		 Model se= aa.selectXingHaoById(1);
		
		System.out.println(se.toString());
		
	}
	@Test
	public void test5() {
		TypeDaoImpl aa=new TypeDaoImpl();
		Map<String,Object>map=new HashMap<String,Object>();
		
		List<Type> selectLeiXing = aa.selectLeiXing();
		
		for (Type type : selectLeiXing) {
			System.out.println(type.toString());
		}
		
	}
	
	@Test
	public void test6() {
		TypeDaoImpl aa=new TypeDaoImpl();
 
		Type selectLeiXingById = aa.selectLeiXingById(1);
			System.out.println(selectLeiXingById.toString());
		
	}
	
	@Test //PanDuImpl
	public void test7() {
		PanDuImpl aa=new PanDuImpl();
		int pandu = aa.pandu("niuwei");
		System.out.println(pandu);
	}
	
}
