package cn.kgc.tangcco.tcbd1017.on.system.action;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.PageRang;
import cn.kgc.tangcco.lihaozhe.commons.servlet.BaseServlet;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.pojo.SetMeal;
import cn.kgc.tangcco.tcbd1017.on.system.SetMealService;

/**
	 * @author XUE TONG
	 * @version 1.0 2019年12月15日下午2:25:31
	 * </br>
	 **/
@WebServlet(urlPatterns = "/SetMeal.action")
public class SetMealAction extends BaseServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5477239465073239314L;
	private static SetMealService setMealService;
	private static ClassPathXmlApplicationContext path = new ClassPathXmlApplicationContext(
			"ApplicationContext_on.xml");
	static {
		try {
			setMealService = (SetMealService) path.getBean(SetMealService.class.getSimpleName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * 查询所有并分页
	 * @param request
	 * @param response 
	 * @throws ServletException
	 * @throws IOException
	 */
	public void querySetMeal(HttpServletRequest request, HttpServletResponse response, String json){	
		Map map = JSON.parseObject(json , Map.class);
		int pageNum = (!map.containsKey("pageNum")) ? 1
				:  (int) map.get("pageNum");
		int pageSize = (!map.containsKey("pageSize")) ? 5
				: (int) map.get("pageSize");
		map.put("pr", new PageRang(pageNum, pageSize));
		Map<String, Object> info = setMealService.querySetMeal(map);
		printJson(response, info);
		

	}

	/**
	 * 查询指定
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findSetMeal(HttpServletRequest request, HttpServletResponse response,String json) {
		int set_meal_id = 0;
		if (!StringUtils.isEmpty(json)) {
			 set_meal_id = Integer.parseInt(json);
		}
		Map<String, Object> map = setMealService.findSetMeal(set_meal_id);
		printJson(response, map);
	}

	/**
	 * 增加
	 * @param request
	 * @param response
	 * @param json
	 */
	public void addSetMeal(HttpServletRequest request, HttpServletResponse response,String json){
		Map map = JSON.parseObject(json , Map.class);
		Map<String, Object> map1 = setMealService.addSetMeal(map);
		printJson(response, map1);
	}

	
	/**
	 * 修改
	 * @param request
	 * @param response
	 * @param json
	 */
	public void updateSetMeal(HttpServletRequest request, HttpServletResponse response,String json){
		SetMeal setMeal = JSON.parseObject(json, SetMeal.class);
		Map<String, Object> meal = setMealService.updateSetMeal(setMeal);
		printJson(response, meal);
		
	}

	/**
	 * 删除
	 * @param request
	 * @param response
	 * @param json
	 */
	public void removeSetMeal(HttpServletRequest request, HttpServletResponse response,String json){
		int set_meal_id = 0;
		if (!StringUtils.isEmpty(json)) {
			 set_meal_id = Integer.parseInt(json);
		}
		Map<String, Object> map = setMealService.removeSetMeal(set_meal_id);
		printJson(response, map);

	}
}
