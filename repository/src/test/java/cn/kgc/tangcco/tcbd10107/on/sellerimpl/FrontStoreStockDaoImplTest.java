package cn.kgc.tangcco.tcbd10107.on.sellerimpl;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.seller.FrontStoreStockGoodsDaoIns;

/** 
* @author 作者 Your-Name: 刘煜
* @version 创建时间：2019年12月16日 上午9:41:02 
*    类说明 
*/
public class FrontStoreStockDaoImplTest {
	static FrontStoreStockGoodsDaoIns bean = null;
	static {
		try {
			ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(
					"ApplicationContext_on.xml");
			bean = (FrontStoreStockGoodsDaoIns) classPathXmlApplicationContext.getBean("FrontStoreStockGoodsDaoIns");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void insertStockGoods() {
		Map<String, Object> map = new HashMap<>();
		map.put("stock_goods_type", "保健品");
		map.put("stock_goods_brand", "脑北京");
		map.put("stock_goods_amount", "30");
		map.put("stock_goods_price", 199);
		map.put("seller_id", 2);
		map.put("stock_goods_create_time",new Date());
		try {
			BaseDBUtils.startTransaction();
			int insertStockGoods = bean.insertStockGoods(map);
			System.out.println(insertStockGoods);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
