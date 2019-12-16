package cn.kgc.tangcco.tbd1017.on.buyer;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.buyer.CommentService;

/**
* @author  余锴峰
* @version 创建时间:2019年12月13日 上午11:16:46
* @ClassName 类名称
* @Description 类描述
*/
public class CommentServiceTest {

	
	private static CommentService commentService = null;
	private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext_on.xml");
	static {
		try {
			commentService = (CommentService) context.getBean(CommentService.class.getSimpleName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("buyer_id", 8);
		map.put("order_id", 222);
		map.put("comment_parent_id", 10);
		map.put("comment_content", "噢噢噢噢哦哦哦哦哦哦哦哦哦哦哦哦");
		map.put("comment_create_time", "2019-12-13 10:33:45");
		map.put("comment_update_time", "2019-12-13 10:33:45");
		map.put("comment_status", 2);
		map.put("comment_grade", 5);
		Map<String, Object> addComment = commentService.addComment(map);
		System.out.println(addComment.toString());
	}
}
