package BrandDaoTest;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import cn.kgc.tangcco.dao.impl.GoodsDaoImpl;
import cn.kgc.tangcco.dao.impl.ModelDaoImpl;

public class GoodsTest {
	@Test
	public void test1() {
		GoodsDaoImpl aa=new GoodsDaoImpl();
		Map<String,Object>map3=new HashMap<String, Object>();
		map3.put("goods_model_id", 1);
		map3.put("goods_w_s_uuid", "仓库uuid001");
		map3.put("goods_type_id", 1);
		map3.put("goods_brand_id", 1);
		//SELECT goods_count FROM goods  WHERE goods.goods_model_id=1 AND goods_w_s_uuid="仓库uuid001" AND goods_type_id=1 AND goods_brand_id=1 

		try {
			int chaXunKuCun = aa.ChaXunKuCun(map3);
			System.out.println(chaXunKuCun);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	public void test2() {
		ModelDaoImpl aa=new ModelDaoImpl();
		Double selectPrice = aa.selectPrice(1, 2, 9);
		System.out.println(selectPrice);
	}
	
}
