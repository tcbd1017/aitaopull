package cn.kgc.tangcco.tcbd10107.on.buyer;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import cn.kgc.tangcco.tcbd1017.on.buyer.BuyerInfoDao;
import cn.kgc.tangcco.tcbd1017.on.buyer.impl.BuyerInfoDaoImple;
import cn.kgc.tangcco.tcbd1017.on.pojo.BuyerInfo;
/**
 * 
 * @author Administrator 朱浩
 *
 */
public class Testll {
	
	//测试查询
		@Test	
		public void test() {
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("buyer_id", 313 );
			BuyerInfoDaoImple l=new BuyerInfoDaoImple();
			List<BuyerInfo> list=l.selectAllBuyerInfosByBuyer_id(map);
			
			System.out.println(list);
			
		}
		//测试修改
		@Test
		public void test01() {
			Map<String, Object> map=new HashMap<String, Object>();
			
			map.put("buyer_info_id", 11);
			
			map.put("buyer_info_gender", 2);
			map.put("buyer_info_idcard", "498");
			map.put("buyer_info_idcard_name", "hgfhfghah");
			map.put("buyer_info_birthday", new  Date());
			map.put("buyer_info_address", "lai");
			map.put("buyer_info_icon_url","56" );
			map.put("buyer_info_create_time", new Date() );
			map.put("buyer_info_update_time", new Date());
			map.put("buyer_info_status", 2);
		
			map.put("buyer_id", 22);

			
			BuyerInfoDao l=new BuyerInfoDaoImple();
			
			int up=l.updateBuyerInfo(map);
			
			if(up==0) {
				System.out.println("失败");
			}else if(up> 0) {
				System.out.println(up);
			}
		}
		
		//添加
		@Test
		public void Test02() {
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("buyer_info_id", 11);
			
			map.put("buyer_info_gender", 2);
			map.put("buyer_info_idcard", "4498");
			map.put("buyer_info_idcard_name", "hah");
			map.put("buyer_info_birthday", new  Date());
			map.put("buyer_info_address", "la");
			map.put("buyer_info_icon_url","55" );
			map.put("buyer_info_create_time", new Date() );
			map.put("buyer_info_update_time", new Date());
			map.put("buyer_info_status", 2);
		
			map.put("buyer_id", 22);
			BuyerInfoDao l=new BuyerInfoDaoImple();
			int insertBuyerInfo =l.insertBuyerInfo(map);	
			if(insertBuyerInfo==0) {
				System.out.println("shibai");
			}else if(insertBuyerInfo> 0) {
				System.out.println("成功");
			
		}
		}
}
