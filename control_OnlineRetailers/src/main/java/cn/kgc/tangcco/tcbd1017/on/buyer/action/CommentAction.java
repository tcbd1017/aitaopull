package cn.kgc.tangcco.tcbd1017.on.buyer.action;

import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import cn.kgc.tangcco.lihaozhe.commons.servlet.BaseServlet;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.buyer.CommentService;


/**
* @author  余锴峰
* @version 创建时间:2019年12月13日 上午11:36:13
* @ClassName 类名称
* @Description 类描述
*/
@WebServlet(urlPatterns = "/comment.action") 
public class CommentAction extends BaseServlet{

	private static final long serialVersionUID = -1555502001113103862L;
	
	private static CommentService commentService = null;
	private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext_on.xml");
	static {
		try {  
			commentService = (CommentService) context.getBean(CommentService.class.getSimpleName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 添加评论
	 * @param request
	 * @param response
	 * @param json
	 */
	public void addComment(HttpServletRequest request, HttpServletResponse response,String json) {
		Map map = JSON.parseObject(json,Map.class);
		Map addComment = commentService.addComment(map);
		printJson(response,addComment);
	}
	
	/**
	 * 查询评论
	 * @param request
	 * @param response
	 * @param json
	 */
	public void queryComment(HttpServletRequest request, HttpServletResponse response,String json) {
		Map map = JSON.parseObject(json,Map.class);
		Map queryComment = commentService.queryComment(map);
		printJson(response,queryComment);
	}
	
}
