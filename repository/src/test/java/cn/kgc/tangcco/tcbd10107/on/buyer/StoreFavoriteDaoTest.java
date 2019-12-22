package cn.kgc.tangcco.tcbd10107.on.buyer;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.buyer.StoreFavoriteDao;

/**
 * 
 * 	@author 和朋朋
 * 	@version 1.0<br>
 * 	创建时间：  2019年12月9日 下午4:00:28 <br>
 * 	类描述：持久层测试
 *	
 */

public class StoreFavoriteDaoTest {
	private static StoreFavoriteDao sd;
	private static ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("ApplicationContext_on.xml");
	static {
		try {
			sd=(StoreFavoriteDao) context.getBean(StoreFavoriteDao.class.getSimpleName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	@Test
//	public void selectAllStore() {
//		Map<String, Object>map=new HashMap<String, Object>();
//		map.put("pr", new PageRang(1, 10));
////		map.put("store_about", "买手机的");
//		List selectAllStoreFavorite;
//		try {
//			selectAllStoreFavorite = sd.selectAllStoreFavorite(map);
//			Iterator  it=selectAllStoreFavorite.iterator();
//			while (it.hasNext()) {
//				Object object = (Object) it.next();
//				System.out.println(object);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
//	}
	/**
	 * 查询所有和按条件查询
	 */
	@Test
	public void fuzzyQueryStoreFavorite() {
		Map<String, Object>map=new HashMap<String, Object>();
//		map.put("store_favorite_id", 1);
		List fuzzyQueryStoreFavorite = sd.fuzzyQueryStoreFavorite(map);
		Iterator  it=fuzzyQueryStoreFavorite.iterator();
		while (it.hasNext()) {
			Object object = (Object) it.next();
			System.out.println(object);
		}
	}
	/**
	 * 添加功能
	 */
	@Test
	public void addStoreFavorite() {
		Map<String, Object>map2=new HashMap<String, Object>();
		map2.put("buyer_id",10);
		map2.put("store_id",10);
		map2.put("store_favorite_create_time", new Date());
		map2.put("store_favorite_update_time", new Date());
		try {
			int count = sd.addStoreFavorite(map2);
			if (count>0) {
				System.out.println("添加成功!");
			}else {
				System.out.println("添加失败!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 删除功能
	 */
	@Test
	public void deleteStoreFavoriteByStoreFavoriteId() {
		Map<String, Object>map=new HashMap<String, Object>();
		map.put("store_favorite_id", 1);
		try {
			int count = sd.deleteStoreFavoriteByStoreFavoriteId(map);
			if (count>0) {
				System.out.println("删除成功!");
			}else {
				System.out.println("删除失败!");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	@Test
//	public void delete() {
//		int []store_favorite_ids= {2,4,6};
//		int count = sd.delete(store_favorite_ids);
//		System.out.println(count);
//	}
//	@Test
//	public void count() {
//		try {
//			int i = sd.count();
//			System.out.println(i);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
