package cn.kgc.tangcco.tcbd1017.on.seller.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.TransactionManagementHandler;
import cn.kgc.tangcco.lihaozhe.commons.servlet.BaseServlet;
import cn.kgc.tangcco.tcbd1017.on.seller.FrontStoreStockServiceIns;
import cn.kgc.tangcco.tcbd1017.on.seller.impl.FrontStoreStockServiceImpl;

/**
 * 刘煜
 * Servlet implementation class FrontStoreStockGoods
 */
@WebServlet("/FrontStoreStockGoods.action")
public class FrontStoreStockGoods extends BaseServlet {
	private static final long serialVersionUID = -5251637927079977776L;
	static FrontStoreStockServiceIns proxy= null;
	static {
		 proxy = (FrontStoreStockServiceIns) new TransactionManagementHandler(new FrontStoreStockServiceImpl()).getProxy();
	}
	//商城进货
	public void add_Store_Stock_Goods(HttpServletRequest request,HttpServletResponse response,String json) {
		Map<?, ?> maps = JSON.parseObject(json, Map.class);
		Map<String, Object> map = new HashMap<>();
		map.put("stock_goods_type",maps.get("stock_goods_type"));
		map.put("stock_goods_brand", maps.get("stock_goods_brand"));
		map.put("stock_goods_amount",maps.get("stock_goods_amount"));
		map.put("stock_goods_price",maps.get("stock_goods_price"));
		map.put("seller_id", maps.get("seller_id"));
		map.put("stock_goods_create_time",new Date());
		System.out.println(map.toString());
		Map<String, Object> add_Store_Stock_Goods = proxy.add_Store_Stock_Goods(map);
		printJson(response, add_Store_Stock_Goods);
	}

}
