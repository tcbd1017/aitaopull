package cn.kgc.tangcco.tcbd1017.on.buyer.impl;
/**
* @author  余锴峰
* @version 创建时间:2019年12月13日 上午11:00:51
* @ClassName 类名称
* @Description 类描述
*/

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.buyer.CommentDao;
import cn.kgc.tangcco.tcbd1017.on.buyer.CommentService;
import cn.kgc.tangcco.tcbd1017.on.pojo.Comment;

public class CommentServiceImpl implements CommentService{
	
	private static CommentDao commentDao = null;
	private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext_on.xml");
	static {
		try {
			commentDao = (CommentDao) context.getBean(CommentDao.class.getSimpleName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加评论
	 */
	@Override
	public Map<String, Object> addComment(Map<String, Object> map) {
		Map<String, Object> info = new HashMap<String, Object>();
		info.put("code", 0);
		info.put("msg", "");
		info.put("count", 0);
		info.put("data", new ArrayList<Comment>());
		info.put("status", "failed");
		try {
			int count = commentDao.insertComment(map);
			if(count>0) {
				info.put("count", count);
				info.put("status", "success");
			}
			BaseDBUtils.closeAll();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				BaseDBUtils.closeAll();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return info;
	}

	/**
	 * 查询评论
	 */
	@Override
	public Map<String, Object> queryComment(Map<String, Object> map) {
		Map<String, Object> info = new HashMap<String, Object>();
		info.put("code", 0);
		info.put("msg", "");
		info.put("count", 0);
		info.put("data", new ArrayList<Comment>());
		info.put("status", "failed");
		try {
			List<Map<String, Object>> selectComment = commentDao.selectComment(map);
			if(selectComment!=null && selectComment.size()>0) {
				info.put("status", "success");
				info.put("data", selectComment);
			}
			BaseDBUtils.closeAll();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				BaseDBUtils.closeAll();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return info;
	}
	
}
