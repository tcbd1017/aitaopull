package cn.kgc.tangcco.tbd1017.on.buyer;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.junit.Test;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.buyer.BuyerInfoService;
import cn.kgc.tangcco.tcbd1017.on.buyer.impl.BuyerInfoDaoImple;
import cn.kgc.tangcco.tcbd1017.on.buyer.impl.BuyerInfoServiceImple;
import cn.kgc.tangcco.tcbd1017.on.pojo.BuyerInfo;



/**
 * 
 * @author Administrator朱浩
 *
 */

public class seviceTest {
	
	private static BuyerInfoService buyerInfoService;
	private static ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("ApplicationContext_on.xml");
	static {
		try {
			buyerInfoService = (BuyerInfoService) classPathXmlApplicationContext.getBean(BuyerInfoService.class.getSimpleName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	BuyerInfoService buyerInfoService=new BuyerInfoServiceImple();

	@Test
	public void addBuyerInfo() {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("buyer_info_id", 15);
		map.put("buyer_id", 15);
		
		map.put("buyer_info_gender", 15);
		map.put("buyer_info_idcard", "15");
		map.put("buyer_info_idcard_name", "haah");
		map.put("buyer_info_birthday", new  Date());
		map.put("buyer_info_address", "lala");
		map.put("buyer_info_icon_url","155" );
		map.put("buyer_info_create_time", new Date() );
		map.put("buyer_info_update_time", new Date());
		map.put("buyer_info_status", 2);
		
		
		
		
		
		Map<String, Object> addBuyerInfo = buyerInfoService.addBuyerInfo(map);
		if (addBuyerInfo!=null ) {
			System.out.println(addBuyerInfo.get("date"));
		}
		
	}

		//查询
	@Test
	public void selectallbybuyer() throws SQLException {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("buyer_id", 313 );

		Map<String, Object> selectallbybuyer = buyerInfoService.queryBuyerInfoByBuyer_id(map);
		if (selectallbybuyer!=null ) {
			System.out.println(selectallbybuyer.get("status"));
			System.out.println(selectallbybuyer.get("date"));
		}
		
		
	}
	@Test
	public void updateBuyerInfo() {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("buyer_info_id", 1);
		map.put("buyer_id", 22);

		Map<String, Object> update = buyerInfoService.removeAndModify_BuyerInfoBybuyer_id(map);
		if (update!=null ) {
			System.out.println(update .get("date"));
			System.out.println(update .get("status"));
		}
		
	}
}
