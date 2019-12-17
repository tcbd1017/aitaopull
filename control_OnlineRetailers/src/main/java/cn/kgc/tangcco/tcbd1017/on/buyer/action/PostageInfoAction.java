package cn.kgc.tangcco.tcbd1017.on.buyer.action;

import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import cn.kgc.tangcco.lihaozhe.commons.servlet.BaseServlet;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.buyer.PostageInfoService;

/**
 * @author LIU KAI
 * @version 1.0 2019年12月14日 下午7:07:17 </br>
 */
@WebServlet("/postageInfo.action")
@SuppressWarnings("unchecked")
public class PostageInfoAction extends BaseServlet {

	private static final long serialVersionUID = 878456895342001853L;
	private static PostageInfoService postageInfoService;
	private static ClassPathXmlApplicationContext path;
	static {
		path = new ClassPathXmlApplicationContext("ApplicationContext_on.xml");
		try {
			postageInfoService = (PostageInfoService) path.getBean(PostageInfoService.class.getSimpleName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询所有收件人信息
	 * 
	 * @param map
	 * @return
	 */
	public void queryPostageInfosByBuyerId(HttpServletRequest request, HttpServletResponse response, String json) {
		Map map = JSON.parseObject(json, Map.class);
		Map<String, Object> info = postageInfoService.queryPostageInfosByBuyerId(map);
		printJson(response, info);

	}

	/**
	 * 新增收件人信息
	 * 
	 * @param map
	 * @return
	 */
	public void addPostageInfo(HttpServletRequest request, HttpServletResponse response, String json) {
		Map map = JSON.parseObject(json, Map.class);
		Map<String, Object> info = postageInfoService.addPostageInfo(map);
		printJson(response, info);
	}

	/**
	 * 修改收件人信息
	 * 
	 * @param map
	 * @return
	 */
	public void modifyPostageInfo(HttpServletRequest request, HttpServletResponse response, String json) {
		Map map = JSON.parseObject(json, Map.class);
		Map<String, Object> info = postageInfoService.modifyPostageInfo(map);
		printJson(response, info);
	}

	/**
	 * 修改默认收件人信息
	 * 
	 * @param map
	 * @return
	 */
	public void modifyPostageInfosByStatus(HttpServletRequest request, HttpServletResponse response, String json) {
		Map map = JSON.parseObject(json, Map.class);
		Map<String, Object> info = postageInfoService.modifyPostageInfosByStatus(map);
		printJson(response, info);

	}

	/**
	 * 删除收件人信息
	 * 
	 * @param map
	 * @return
	 */
	public void removePostageInfosByStatus(HttpServletRequest request, HttpServletResponse response, String json) {
		Map map = JSON.parseObject(json, Map.class);
		Map<String, Object> info = postageInfoService.removePostageInfosByStatus(map);
		printJson(response, info);
	}

	/**
	 * 根据id查询地址
	 * 
	 * @param map
	 * @return
	 */
	public void findAddressById(HttpServletRequest request, HttpServletResponse response, String json) {
		Map map = JSON.parseObject(json, Map.class);
		Map<String, Object> info = postageInfoService.findAddressById(map);
		printJson(response, info);
	}

	/**
	 * 根据pid查询地址集合
	 * 
	 * @param map
	 * @return
	 */
	public void queryAddressesByPid(HttpServletRequest request, HttpServletResponse response, String json) {
		Map map = JSON.parseObject(json, Map.class);
		Map<String, Object> info = postageInfoService.queryAddressesByPid(map);
		printJson(response, info);
	}
}
