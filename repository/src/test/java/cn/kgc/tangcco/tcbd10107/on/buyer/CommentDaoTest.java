
package cn.kgc.tangcco.tcbd10107.on.buyer;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.junit.Test;

import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.buyer.CommentDao;

/**
* @author  余锴峰
* @version 创建时间:2019年12月13日 上午11:01:39
* @ClassName 类名称
* @Description 类描述
*/
public class CommentDaoTest {
	
	private static CommentDao commentDao = null;
	private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext_on.xml");
	static {
		try {
			commentDao = (CommentDao) context.getBean(CommentDao.class.getSimpleName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("comment_id", 7);
		map.put("buyer_id", 4);
		map.put("order_id", 88);
		map.put("comment_parent_id", 10);
		map.put("comment_content", "噢噢噢噢哦哦哦哦哦哦哦哦哦哦哦哦");
		map.put("comment_create_time", "2019-12-13 10:33:45");
		map.put("comment_update_time", "2019-12-13 10:33:45");
		map.put("comment_status", 2);
		map.put("comment_grade", 5);
		try {
			int add = commentDao.insertComment(map);
			if(add>0) {
				System.out.println("添加成功");
			}else {
				System.out.println("添加失败");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test2() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("c.buyer_id", 2);
		map.put("b.buyer_id", 2);
		map.put("c.order_id", 1);
		map.put("o.order_id", 1);
		map.put("o.goods_name", "三星 Galaxy S4 (I9500) 16G版 星空黑 联通3G手机");
		try {
			List selectComment = commentDao.selectComment(map);
			if(selectComment!=null) {
				ListIterator<Map<String, Object>> it = selectComment.listIterator();
				while(it.hasNext()) {
					System.out.println(it.next());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
