package cn.kgc.tangcco.tcbd1017.on.system;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSON;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.PageRang;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.pojo.Seller;


/**
	 * @author XUE TONG
	 * @version 1.0 2019年12月16日上午11:39:28
	 * </br>
	 **/

public class ViewSellerDaoTest {
	private static ViewSellerDao viewSellerDao;
	private static ClassPathXmlApplicationContext path = new ClassPathXmlApplicationContext("ApplicationContext_on.xml");
	static {
		try {		
			viewSellerDao =  (ViewSellerDao) path.getBean(ViewSellerDao.class.getSimpleName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void selectViewSeller() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("buyer_id", "4");
		map.put("pr", new PageRang(1,5));
//		map.put("seller_create_time", "2019-11-27 10:38:57");
//		map.put("seller_update_time", "2019-12-18 10:39:00");
		try {
			List<Seller> seller = viewSellerDao.selectViewSeller(map);
			ListIterator<Seller> it = seller.listIterator();
			while (it.hasNext()) {
				Seller s =  it.next();
				System.out.println(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("buyer_id", "4");
		String json = JSON.toJSONString(map);
		System.out.println(json);
	}
	
	@Test
	public void selectCountSeller() {
		Map<String, Object> map = new HashMap<String, Object>();
			int count;
			try {
				count = viewSellerDao.selectCountSeller(map);
				System.out.println(count);
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
}
