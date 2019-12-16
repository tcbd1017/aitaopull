package cn.kgc.tangcco.tcbd10107.on.buyer;
/**
 * @author 赵瑞涛
 * @version v1.0<br>
 * 	创建时间:	2019年12月9日	下午3:29:15<br>
 * 	类描述:
 */


import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.lihaozhe.commons.jdbc.PageRang;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.buyer.CashVoucherDao;

public class CashVoucherDaoTest {
	
	public static CashVoucherDao cashVoucherDao;
	public static ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("ApplicationContext_on.xml");
	static {
		try {
			cashVoucherDao = (CashVoucherDao) classPathXmlApplicationContext.getBean(CashVoucherDao.class.getSimpleName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	/**
	 *  查
	 */
	public void chaall() {
		int id = 1;
		int status = 2;
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("buyer_id", id);
		map.put("cash_voucher_status", status);
		try {
			List<Map<String, Object>> list = cashVoucherDao.selectByBuyerId(map);
			Iterator<Map<String, Object>> iterator = list.iterator();
			while (iterator.hasNext()) {
				Map<String,Object> map2 = (Map<String,Object>) iterator.next();
				BaseDBUtils.closeAll();
				System.out.println("店铺名称"+map2.get("store_name"));
				System.out.println("图片地址"+map2.get("voucher_type_picture_url"));
				System.out.println("开始使用的时间"+map2.get("cash_voucher_acailable_begin_time"));
				System.out.println("停止使用的时间"+map2.get("cash_voucher_available_time"));
				System.out.println("使用条件"+map2.get("voucher_type_condition"));
				System.out.println("见面金额"+map2.get("voucher_type_money"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	/**
	 * 总量
	 */
	public void chazongliang() {
		int id =1;
		int status = 2;
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("buyer_id", id);
		map.put("cash_voucher_status", status);
		try {
			int a =cashVoucherDao.selectByNumber(map);
			BaseDBUtils.closeAll();
			System.out.println("查询到的总数"+a);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
	@Test
	/**
	 * 	删减
	 */
	public void shanjian() {
		String uuid = "4";
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("cash_voucher_uuid", uuid);
		try {
			BaseDBUtils.startTransaction();
			int a =cashVoucherDao.deleteByUuid(map);
			BaseDBUtils.commitAndClose();
			if(a>0) {
				System.out.println("成功");
			}else {
				System.out.println("失败");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	/**
	 *  查
	 */
	public void chaallfen() {
		int id = 1;
		int status =2;
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("buyer_id", id);
		map.put("cash_voucher_status", status);
		map.put("pageNumber", 1);
		map.put("pageSize", 2);
		try {
			List<Map<String, Object>> list = cashVoucherDao.selectByBuerIdAndStatus(map);
			Iterator<Map<String, Object>> iterator = list.iterator();
			while (iterator.hasNext()) {
				Map<String,Object> map2 = (Map<String,Object>) iterator.next();
				BaseDBUtils.closeAll();
				System.out.println("cash_voucher_id"+map2.get("cash_voucher_id"));
				System.out.println("店铺名称"+map2.get("store_name"));
				System.out.println("图片地址"+map2.get("voucher_type_picture_url"));
				System.out.println("开始使用的时间"+map2.get("cash_voucher_acailable_begin_time"));
				System.out.println("停止使用的时间"+map2.get("cash_voucher_available_time"));
				System.out.println("使用条件"+map2.get("voucher_type_condition"));
				System.out.println("见面金额"+map2.get("voucher_type_money"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	/**
	 * 	通过店名查询
	 */
	public void chaming(){
		int id = 1;
		int status = 2;
		String name = "111";
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("buyer_id", id);
		map.put("cash_voucher_status", status);
		map.put("store_name", name);
		try {
			List<Map<String, Object>> list = cashVoucherDao.selectByIdAndStatusAndStoreName(map);
			Iterator<Map<String, Object>> iterator = list.iterator();
			while (iterator.hasNext()) {
				Map<String,Object> map2 = (Map<String,Object>) iterator.next();
				BaseDBUtils.closeAll();
				System.out.println("店铺名称"+map2.get("store_name"));
				System.out.println("图片地址"+map2.get("voucher_type_picture_url"));
				System.out.println("开始使用的时间"+map2.get("cash_voucher_acailable_begin_time"));
				System.out.println("停止使用的时间"+map2.get("cash_voucher_available_time"));
				System.out.println("使用条件"+map2.get("voucher_type_condition"));
				System.out.println("见面金额"+map2.get("voucher_type_money"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
