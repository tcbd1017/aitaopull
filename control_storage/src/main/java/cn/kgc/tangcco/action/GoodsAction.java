package cn.kgc.tangcco.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.PageRang;
import cn.kgc.tangcco.lihaozhe.commons.servlet.BaseServlet;
import cn.kgc.tangcco.service.GoodsService;
import cn.kgc.tangcco.service.impl.GoodsServiceImpl;

@WebServlet(urlPatterns = "/GoodsAction.action")
public class GoodsAction extends BaseServlet {
	private static final long serialVersionUID = 2076536214735242560L;
	GoodsService goodsService = new GoodsServiceImpl();

	public void SelectAllgoods(HttpServletRequest request, HttpServletResponse response) {

		// 查询全部 当为商家时 需要传入一个商铺id
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("PageRang", new PageRang(Integer.parseInt(request.getParameter("page")),
				Integer.parseInt(request.getParameter("limit"))));
		map.put("account", request.getParameter("account"));
		map.put("goods_id", request.getParameter("goods_id"));
		map.put("shop_name", request.getParameter("shop_name"));
		map.put("goods_count", request.getParameter("goods_count"));
		map.put("goods_w_s_uuid", request.getParameter("goods_w_s_uuid"));
		map.put("type_name", request.getParameter("type_name"));
		map.put("brand_name", request.getParameter("brand_name"));
		map.put("model_name", request.getParameter("model_name"));
		map.put("model_price", request.getParameter("model_price"));
		map.put("model_size", request.getParameter("model_size"));
		map.put("shop_id", request.getParameter("shop_id"));

		Map<String, Object> selectAllgoods = goodsService.SelectAllgoods(map);
//		System.out.println(selectAllgoods.toString());
		printJson(response, selectAllgoods.get("data"));

	}

}
