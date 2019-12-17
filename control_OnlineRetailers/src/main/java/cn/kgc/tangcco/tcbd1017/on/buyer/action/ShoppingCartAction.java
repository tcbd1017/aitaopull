package cn.kgc.tangcco.tcbd1017.on.buyer.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import cn.kgc.tangcco.lihaozhe.commons.servlet.BaseServlet;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.buyer.ShoppingCartService;

/**
	 * @author DU MING
	 * @version 1.0	2019年12月15日	下午4:43:39
	 */
@WebServlet("/shoppingCart.action")
public class ShoppingCartAction extends BaseServlet{

	private static final long serialVersionUID = 126195042645734182L;
	private static ShoppingCartService shoppingCartService;
	private static ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("ApplicationContext_on.xml");
	static {
		try {
			shoppingCartService = (ShoppingCartService) classPathXmlApplicationContext.getBean(ShoppingCartService.class.getSimpleName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 根据用户Id查询购物车信息 包含模糊查询
	 * 传入map 需包含物品Id int "goodsId" 用户Id int "buyerId" 可选物品名称(开启模糊查询)String "goodsName"
	 * 返回值 	"status"	成功 "success" 失败 "failed"

	 * 			"msg"		String 类型的一些message信息
	 * 			"count"		本次查询总记录数
	 * 			"data"		List<Map<String, Object>>类型的本次查询的数据
	 * 					包含物品的所有信息和购物车的所有信息（两表联查）
	 * @param request
	 * @param response
	 * @param json
	 */
	
	public void queryAllShoppingCartInfoByBuyerIdAddUrl(HttpServletRequest request, HttpServletResponse response, String json) {
		Map map = JSON.parseObject(json, Map.class);
		Map<String, Object> result = shoppingCartService.queryAllShoppingCartInfoByBuyerIdAddUrl(map);
		printJson(response, result);
	}
	
	public void queryAllShoppingCartInfoByBuyerId(HttpServletRequest request, HttpServletResponse response, String json) {
		Map map = JSON.parseObject(json, Map.class);
		Map<String, Object> result = shoppingCartService.queryAllShoppingCartInfoByBuyerId(map);
		printJson(response, result);
	}

	/**
	 * 根据传入的用户id和物品id增加购物车信息
	 * 如已存在于购物车，则自动调用更改数量方法去更新数量
	 * 传入map 需包含int buyerId, int goodsId 和int amountOfGoods
	 * 返回值 	"status"	成功 "success" 失败 "failed"
	 * 			"msg"		String 类型的一些message信息
	 * @param request
	 * @param response
	 * @param json
	 */
	public void addShoppingCart(HttpServletRequest request, HttpServletResponse response, String json) {
		Map map = JSON.parseObject(json, Map.class);
		Map<String, Object> result = shoppingCartService.addShoppingCart(map);
		printJson(response, result);
	}
	
	/**
	 * 根据传入的用户的物品新数量 更改购物车物品数量
	 * 传入map 需包含int buyerId, int goodsId 和int amountOfGoods
	 * 返回值 	"status"	成功 "success" 失败 "failed"
	 * 			"msg"		String 类型的一些message信息
	 * @param request
	 * @param response
	 * @param json
	 */
	public void modifyShoppingCart(HttpServletRequest request, HttpServletResponse response, String json) {
		Map map = JSON.parseObject(json, Map.class);
		Map<String, Object> result = shoppingCartService.modifyShoppingCart(map);
		printJson(response, result);
	}
	
	/**
	 * 根据传入的用户的物品删除信息 删除物品
	 * 传入map 需包含int buyerId, int goodsId 
	 * 返回值 	"status"	成功 "success" 失败 "failed"
	 * 			"msg"		String 类型的一些message信息
	 * @param request
	 * @param response
	 * @param json
	 */
	public void removeShoppingCart(HttpServletRequest request, HttpServletResponse response, String json) {
		Map map = JSON.parseObject(json, Map.class);
		Map<String, Object> result = shoppingCartService.removeShoppingCart(map);
		printJson(response, result);
	}

	

//	public static void main(String[] args) {
//		Map<Object, Object> map = new HashMap<>();
//		map.put("goodsId", 1);
//		map.put("buyerId", 1);
//		
//		
//		System.out.println(JSON.toJSONString(map));
//	}
}


