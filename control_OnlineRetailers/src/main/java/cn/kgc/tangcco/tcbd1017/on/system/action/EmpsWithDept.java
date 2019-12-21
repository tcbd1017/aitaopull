package cn.kgc.tangcco.tcbd1017.on.system.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.PageRang;
import cn.kgc.tangcco.lihaozhe.commons.servlet.BaseServlet;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.system.EmpsWithDeptService;



/**
 * Servlet implementation class EmpsWithDept
 */
@WebServlet("/EmpsWithDept.action")
public class EmpsWithDept extends BaseServlet {
	
	static EmpsWithDeptService empsWithDeptService= null;
	static {
		ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("ApplicationContext_on.xml");
		try {
			empsWithDeptService=(EmpsWithDeptService) classPathXmlApplicationContext.getBean("EmpsWithDeptService");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//EmpsWithDeptService empsWithDeptService = new  EmpsWithDeptServiceImpl();
	private static final long serialVersionUID = 6992300492863147987L;

		public void queryEmpsWithDept(HttpServletRequest request,HttpServletResponse response,String json) {
			Map map = JSON.parseObject(json , Map.class);
			  int pageNum = (!map.containsKey("pageNumber")) ? 1
			    :  (int) map.get("pageNumber");
			  int pageSize = (!map.containsKey("pageSize")) ? 5
			    : (int) map.get("pageSize");
			  int dept_id=(int) map.get("dept_id");
			  map.put("pr", new PageRang(pageNum, pageSize));
			  map.put("dept_id", dept_id);
			  Map<String, Object> info = null;
			  info = empsWithDeptService.queryEmpsWithDept(map);
			  printJson(response, info);
		}
}
