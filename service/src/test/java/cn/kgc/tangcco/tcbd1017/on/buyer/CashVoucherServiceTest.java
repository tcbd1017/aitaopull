package cn.kgc.tangcco.tbd1017.on.buyer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.PageRang;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.buyer.CashVoucherService;

/**
 * @author 赵瑞涛
 * @version v1.0<br>
 * 	创建时间:	2019年12月13日	下午9:35:35<br>
 * 	类描述:
 */
public class CashVoucherServiceTest {
	private static CashVoucherService cashVoucherService;
	private static ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("ApplicationContext_on.xml");
	static {
		try {
			cashVoucherService = (CashVoucherService) classPathXmlApplicationContext.getBean(CashVoucherService.class.getSimpleName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	/**
	 * 	查询所有可使用
	 */
	public void queryyes(){
		int id = 1;
		int status = 2;
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("buyer_id", id);
		map.put("cash_voucher_status", status);
		map =  cashVoucherService.queryByIdAndStatus(map);
		System.out.println(map.get("status"));
		if (map.get("status").toString().equals("success")) {
			List<Map<String, Object>> list = (List<Map<String, Object>>) map.get("info");
			Iterator<Map<String, Object>> iterator = list.iterator();
			if (list.size()>0) {
				while (iterator.hasNext()) {
					Map<String, Object> map2 = (Map<String, Object>) iterator.next();
					System.out.println("店铺名称"+map2.get("store_name"));
					System.out.println("图片地址"+map2.get("voucher_type_picture_url"));
					System.out.println("开始使用的时间"+map2.get("cash_voucher_acailable_begin_time"));
					System.out.println("停止使用的时间"+map2.get("cash_voucher_available_time"));
					System.out.println("使用条件"+map2.get("voucher_type_condition"));
					System.out.println("见面金额"+map2.get("voucher_type_money"));
				}
			}else {
				System.out.println("kong");
			}
		}
	}
	
	
	@Test
	/**
	 * 	查詢总数
	 */
	public void queryNumber() {
		int id = 1;
		int status = 2;
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("buyer_id", id);
		map.put("cash_voucher_status", status);
		map =  cashVoucherService.queryNumberByIdAndStatus(map);
		if (map.get("status").toString().equals("success")) {
			int a = (int) map.get("info");
			if (a>0) {
				System.out.println("查询成功，总数为："+a);
				}
			}else {
				System.out.println("查询结果为空");
			}
	}
	
	@Test
	/**
	 * 	删除一个优惠券
	 */
	public void removeByUuid() {
		String uuid = "4";
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("cash_voucher_uuid", uuid);
		map = cashVoucherService.removeByUuid(map);
		if (map.get("status").toString().equals("success")) {
				System.out.println("删除成功");
		}else {
				System.out.println("删除失败");
		}
	}
	
	@Test
	/**
	 * 	分页查询
	 */
	public void queryByIdAndStatusAndPagereng() {
		int id = 1;
		int status = 2;
		PageRang pageRang = new PageRang(1, 3);
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("buyer_id", id);
		map.put("cash_voucher_status",status);
		map.put("pagerang", pageRang);
		map = cashVoucherService.queryByIdAndStatusAndPagereng(map);
		if (map.get("status").toString().equals("success")) {
			List<Map<String, Object>> list = (List<Map<String, Object>>) map.get("info");
			Iterator<Map<String, Object>> iterator = list.iterator();
			while (iterator.hasNext()) {
				Map<String,Object> map2 = (Map<String,Object>) iterator.next();
				System.out.println("店铺名称"+map2.get("store_name"));
				System.out.println("图片地址"+map2.get("voucher_type_picture_url"));
				System.out.println("开始使用的时间"+map2.get("cash_voucher_acailable_begin_time"));
				System.out.println("停止使用的时间"+map2.get("cash_voucher_available_time"));
				System.out.println("使用条件"+map2.get("voucher_type_condition"));
				System.out.println("见面金额"+map2.get("voucher_type_money"));
			}
		}else {
				System.out.println("没有记录");
		}
	}
	
	@Test
	/**
	 * 	通过店名查询
	 */
	public void queryByIdAndStatusAndStoreName(){
		int id = 1;
		int status = 2;
		String	name = "111";
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("buyer_id", id);
		map.put("cash_voucher_status",status);
		map.put("store_name", name);
		map = cashVoucherService.queryByIdAndStatusAndStoreName(map);
		if (map.get("status").toString().equals("success")) {
			List<Map<String, Object>> list = (List<Map<String, Object>>) map.get("info");
			Iterator<Map<String, Object>> iterator = list.iterator();
			while (iterator.hasNext()) {
				Map<String,Object> map2 = (Map<String,Object>) iterator.next();
				System.out.println("店铺名称"+map2.get("store_name"));
				System.out.println("图片地址"+map2.get("voucher_type_picture_url"));
				System.out.println("开始使用的时间"+map2.get("cash_voucher_acailable_begin_time"));
				System.out.println("停止使用的时间"+map2.get("cash_voucher_available_time"));
				System.out.println("使用条件"+map2.get("voucher_type_condition"));
				System.out.println("见面金额"+map2.get("voucher_type_money"));
			}
		}else {
				System.out.println("没有记录");
		}
	}
}