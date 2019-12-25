package cn.kgc.tangcco.tcbd1017.st.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.PageRang;
import cn.kgc.tangcco.lihaozhe.commons.servlet.BaseServlet;
import cn.kgc.tangcco.service.impl.ShopServiceImpl;

@WebServlet(urlPatterns = "/ShopAction.action")
public class ShopAction extends BaseServlet{

	
	private static final long serialVersionUID = 4716038243868880078L;

	public void chakanShangPuAll(HttpServletRequest request, HttpServletResponse response) {
		ShopServiceImpl shopServiceImpl = new ShopServiceImpl();
		  // 声明一个map
		  Map<String, Object> map = new HashMap<String, Object>();
		  // 声明一个分页对象 获取当前页 获取每页的记录数
		  // 参数
		  map.put("shop_id", request.getParameter("shop_id"));
		  map.put("shop_name", request.getParameter("shop_name"));
		  
		  // 调用service层的方法
		  Map<String, Object> chakanShangPuAll = shopServiceImpl.chakanShangPuAll(map);

		  printJson(response, chakanShangPuAll);
	}
	
	
}
