package cn.kgc.tangcco.tcbd1017.on.seller.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.lihaozhe.commons.date.BaseDateUitls;
import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.lihaozhe.commons.localdatetime.LocalDateTimeUtil;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.seller.SellerOrderDeliveryAndInquiryDao;
import cn.kgc.tangcco.tcbd1017.on.seller.SellerOrderDeliveryAndInquiryService;

/**
*@author   郝彦冰<br>
*@version  创建时间 :  2019年12月15日  下午8:05:41<br>
*类说明:
*/
public class SellerOrderDeliveryAndInquiryServiceImpl implements  SellerOrderDeliveryAndInquiryService{
	private static SellerOrderDeliveryAndInquiryDao sellerOrderDeliveryAndInquiryDao;
	private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext_on.xml");
	static {
		try {
			sellerOrderDeliveryAndInquiryDao = (SellerOrderDeliveryAndInquiryDao) context.getBean(SellerOrderDeliveryAndInquiryDao.class.getSimpleName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 查询卖家与买家的发货、收货地址
	 */
//	@Override
//	public Map<String, Object> queryAddress()  {
//		Map<String, Object> map= new LinkedHashMap<String, Object>();
//		map.put("code", 0);
//		map.put("msg", "");
//		map.put("date","");
//		map.put("status", "failed");
//		List<Map<String, Object>> list;
//		try {
//			list = sellerOrderDeliveryAndInquiryDao.selectAddress();
//			if (list.size()>0) {
//				map.put("date",list);
//				map.put("status", "success");	
//			}
//			BaseDBUtils.closeAll();
//			return map;
//		} catch (SQLException e) {
//			try {
//				BaseDBUtils.closeAll();
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//			e.printStackTrace();
//		}
//		
//		
//		return map;
//	}
	/**
	 * 更改订单待发货状态
	 */
	@Override
	public Map<String, Object> modifyOrderStatus(Map<String, Object> map1)  {
		Map<String, Object> map= new LinkedHashMap<String, Object>();
		map.put("code", 0);
		map.put("msg", "");
		map.put("data","");
		map.put("status", "failed");
		try {
			BaseDBUtils.startTransaction();
			int s = sellerOrderDeliveryAndInquiryDao.updateOrderStatus(map1);
			if (s>0) {
				map.put("status", "success");
			}
			BaseDBUtils.commitAndClose();
			return map;
		} catch (SQLException e) {
			try {
				BaseDBUtils.commitAndClose();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 查询买家收货地址
	 */
	@Override
	public Map<String, Object> querySellerOrderDeliveryQueryBuyerReceiptInformation(Map<String, Object> map1) {
		Map<String, Object> map= new LinkedHashMap<String, Object>();
		map.put("code", 0);
		map.put("msg", "");
		map.put("count","");
		map.put("data",new ArrayList());
		//map.put("status", "failed");
		List<Map<String, Object>> list;
		try {
			list = sellerOrderDeliveryAndInquiryDao.selectSellerOrderDeliveryQueryBuyerReceiptInformation(map1);
			if (list.size()>0 && list != null) {
				map.put("data",list);
				map.put("count", list.size());
				//map.put("status", "success");	
			}else {
				System.out.println("service----list是空");
			}
			BaseDBUtils.closeAll();
			return map;
		} catch (SQLException e) {
			try {
				BaseDBUtils.closeAll();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		
		return map;
	}
	/**
	 * 查询卖家发货地址
	 */
	@Override
	public Map<String, Object> querySellerShippingAddress(Map<String, Object> map1) {
		Map<String, Object> map= new LinkedHashMap<String, Object>();
		map.put("code", 0);
		map.put("msg", "");
		map.put("count","");
		map.put("data",new ArrayList());
		//map.put("status", "failed");
		List<Map<String, Object>> list;
		try {
			list = sellerOrderDeliveryAndInquiryDao.selectSellerShippingAddress(map1);
			if (list.size()>0 && list != null) {
				map.put("data",list);
				map.put("count", list.size());
				//map.put("status", "success");	
			}
			BaseDBUtils.closeAll();
			return map;
		} catch (SQLException e) {
			try {
				BaseDBUtils.closeAll();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		
		return map;
	}

}
