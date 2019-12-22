package cn.kgc.tangcco.tcbd1017.on.seller.action;

import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.PageRang;
import cn.kgc.tangcco.lihaozhe.commons.servlet.BaseServlet;
import cn.kgc.tangcco.tcbd1017.on.seller.SellerOrderService;
import cn.kgc.tangcco.tcbd1017.on.seller.impl.SellerOrderServiceImpl;

/**
*@author   席首华
*@version  1.0   </br>
   创建时间   2019年12月16日     下午3:22:29
  类描述：
*
*/
@WebServlet(urlPatterns = "/sellerOrder.action")
public class SellerOrderAction extends BaseServlet{
	private static final long serialVersionUID = 423232843842041384L;

	SellerOrderService sellerOrderService = new SellerOrderServiceImpl();
	
	public void getOrderInformation(HttpServletRequest request , HttpServletResponse response , String json) {
		Map map = JSON.parseObject(json,Map.class);
		map.put("page", new PageRang((Integer)map.get("page"),(Integer)map.get("limit")));
		Map<String, Object> queryOrder = sellerOrderService.queryOrder(map);
		printJson(response, queryOrder);
	}
	
}
