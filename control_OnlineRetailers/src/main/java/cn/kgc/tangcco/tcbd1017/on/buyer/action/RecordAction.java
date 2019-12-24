package cn.kgc.tangcco.tcbd1017.on.buyer.action;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;

import cn.kgc.tangcco.lihaozhe.commons.servlet.BaseServlet;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.buyer.RecordService;

/**
*@author 作者：肖越
*@version 1.0 创建时间:2019年12月14日上午9:27:21
*/
@WebServlet("/xy.action")
public class RecordAction extends BaseServlet{

	static RecordService recordService ;
	static ClassPathXmlApplicationContext path;
	static {
		path = new ClassPathXmlApplicationContext("ApplicationContext_on.xml");
		try {
			recordService = (RecordService) path.getBean(RecordService.class.getSimpleName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private static final long serialVersionUID = -8246976140474130692L;
	
	/**
	 * 根据买家id 显示出该买家所有历史足迹商品的信息以及买家所有信息和历史记录所有信息  (按照历史足迹时间降序排序)
	 * 设计了三表联查 ：  1. 买家表（0101_buyer）  2. 历史记录表（0103_record） 3.商品表（0203_goods）
	 * 我把三表联查的结果只要有的信息就全部返回了，如有需要直接调用    例：传过来的参数为： {"buyer_id":"1"}
	 */
	public void queryAllRecord(HttpServletRequest request,HttpServletResponse response,String buyer_id) {
		try {
			System.out.println("buyer_id"+buyer_id);
			Map map=null;
			if (!StringUtils.isEmpty(buyer_id)) {
				
				 map = JSON.parseObject(buyer_id,Map.class); 
				 System.out.println("进入方法");
			}
			
			System.out.println(map.containsKey("buyer_id"));
			 map = recordService.queryAllRecord(map.get("buyer_id").toString());
			printJson(response, map);
		} catch (Exception e) {
			System.out.println("有异常");
		}
	}
	/**
	 * 根据传过来的历史记录id删除相对应的商品足迹（单个删除）
	 * @param record_id 历史记录id    例：传过来的参数为：  {"record_id":"2"}
	 */
	public void removeRecordByRecord_id(HttpServletRequest request,HttpServletResponse response,String record_id) {
		Map map=null;
		if (!StringUtils.isEmpty(record_id)) {
			
			 map = JSON.parseObject(record_id,Map.class); 
			 System.out.println("进入方法");
			 System.out.println(record_id);
		}
		
		System.out.println(map.containsKey("record_id"));
		 map = recordService.removeRecordByRecord_id(map.get("record_id").toString());
		printJson(response, map);
		
		
	}
	/**
	 * 根据传过来的买家id 和商品名称（支持模糊查询）查询所对应的商品
	 *  例：传过来的json字符串      {"goods_name":"丹","buyer_id":"1"}
	 * 里面包含了买家id 和 （模糊或完整的）商品名称
	 */
	public void queryRecordAllByBuyerIdAndGoodsName(HttpServletRequest request,HttpServletResponse response,String buyerIdAndGoodsName) {
		Map map=null;
		if (!StringUtils.isEmpty(buyerIdAndGoodsName)) {
			
			 map = JSON.parseObject(buyerIdAndGoodsName,Map.class); 
			 System.out.println("进入方法");
			 System.out.println(buyerIdAndGoodsName);
		}
		
		System.out.println(map.containsKey("buyer_id"));
		System.out.println(map.containsKey("goods_name"));
		 map = recordService.queryRecordAllByBuyerIdAndGoodsName(map.get("buyer_id").toString(), map.get("goods_name").toString());
		printJson(response, map);
		
		
	}
	
	
	/**
	 * 根据传过来的 买家id和商品id 增加一条浏览记录
	 * （时间我写了方法会自动更新）
	 *  例：传过来的json字符串     {"goods_id":"6","buyer_id":"3"}
	 *   
	 */
	public void addRecordByBuyer_idAndGoods_id(HttpServletRequest request,HttpServletResponse response,String buyer_idAndGoods_id) {
		Map map=null;
		if (!StringUtils.isEmpty(buyer_idAndGoods_id)) {
			
			 map = JSON.parseObject(buyer_idAndGoods_id,Map.class); 
			 System.out.println("进入方法");
			 System.out.println(buyer_idAndGoods_id);
		}
		
		System.out.println(map.containsKey("buyer_id"));
		System.out.println(map.containsKey("goods_id"));
		 map = recordService.addRecordByBuyer_idAndGoods_id(map.get("buyer_id").toString(), map.get("goods_id").toString());
		printJson(response, map);
		
		
	}
	
	
	
	
	
	/**
	 * 《肖越根据前台页面需求额外新添加的方法 1》
	 * 大家如果有需要就调用就好了
	 * 此方法根据买家id返回买家详细信息    例：传过来的json字符串 {"buyer_Id":"1"}
	 * @param buyer_id 买家id
	 * @return 返回该买家详细信息
	 */
	public void queryBuyerInfoBybuyer_id(HttpServletRequest request,HttpServletResponse response,String buyer_Id) {
		
		Map map=null;
		if (!StringUtils.isEmpty(buyer_Id)) {
			
			 map = JSON.parseObject(buyer_Id,Map.class); 
			 System.out.println("进入方法");
			 System.out.println(buyer_Id);
		}
		
		System.out.println(map.containsKey("buyer_Id"));
		 map = recordService.queryBuyerInfoBybuyer_id(map.get("buyer_Id").toString());
		printJson(response, map);
		
	}
	
	
	/**
	 * 《肖越根据前台页面需求额外新添加的方法 2》
	 * 大家如果有需要就调用就好了
	 * 此方法根据买家id和当前页码  返回买家历史记录里商品图片url等信息 （并进行了分页）      例：传过来的json字符串    {"pageNo":"1","buyer_id":"1"}
	 * @param buyer_id 买家id
	 * @return 返回该买家历史记录里商品图片url等信息
	 */
	public void queryGoodsPictureUrlByBuyerId(HttpServletRequest request,HttpServletResponse response, String buyer_idAndPageNo) {
		Map map=null;
		if (!StringUtils.isEmpty(buyer_idAndPageNo)) {
			
			 map = JSON.parseObject(buyer_idAndPageNo,Map.class); 
			 System.out.println("进入方法");
			 System.out.println(buyer_idAndPageNo);
		}
		
		System.out.println(map.containsKey("buyer_id"));
		System.out.println(map.containsKey("pageNo"));
		 map = recordService.queryGoodsPictureUrlByBuyerId(map.get("buyer_id").toString(),map.get("pageNo").toString());
		printJson(response, map);
		
		
		
	}
	
	/**
	 * 《肖越根据前台页面需求额外新添加的方法 3》
	 * 大家如果有需要就调用就好了
	 * 此方法根据买家id获取买家历史记录总条数	   例：传过来的json字符串 {"buyer_id":"1"}
	 * @param buyer_id 买家id
	 * @return  根据买家id获取(buyerHistory)买家历史记录总条数    和 总页数(totalPages)
	 */
	public void queryCountBuyerHistory(HttpServletRequest request,HttpServletResponse response, String buyer_id) {
		Map map=null;
		if (!StringUtils.isEmpty(buyer_id)) {
			
			 map = JSON.parseObject(buyer_id,Map.class); 
			 System.out.println("进入方法");
			 System.out.println(buyer_id);
		}
		
		System.out.println(map.containsKey("buyer_id"));
		 map = recordService.queryCountBuyerHistory(map.get("buyer_id").toString());
		printJson(response, map);
	}
	
	
	/**
	 * 《肖越根据前台页面需求额外新添加的方法 4》
	 * 大家如果有需要就调用就好了
	 * 此方法根据买家id获取买家购物车前两条数据      例：传过来的json字符串  {"buyer_id":"1"}
	 * @param buyer_id 买家id
	 * @return 买家购物车前两条数据
	 */
	public void queryBuyerShoppingCart(HttpServletRequest request,HttpServletResponse response, String buyer_id) {
		Map map=null;
		if (!StringUtils.isEmpty(buyer_id)) {
			
			 map = JSON.parseObject(buyer_id,Map.class); 
			 System.out.println("进入方法");
			 System.out.println(buyer_id);
		}
		
		System.out.println(map.containsKey("buyer_id"));
		 map = recordService.queryBuyerShoppingCart(map.get("buyer_id").toString());
		printJson(response, map);
	}
	
	
	/**
	 * 《肖越根据前台页面需求额外新添加的方法 5》
	 * 大家如果有需要就调用就好了
	 * 此方法根据买家id和当前页码获取买家购物车的所有数据 (购物车商品图片、重量、长宽高...等信息)
	 * 每页显示五条数据    例：传过来的json字符串   {"buyer_id":"1","currentPage":"1"}
	 * @param buyer_id 买家id
	 * @return 买家购物车的所有数据
	 */
	public void queryAllBuyerShoppingCart(HttpServletRequest request,HttpServletResponse response, String buyer_idAndPages) {
		Map map=null;
		if (!StringUtils.isEmpty(buyer_idAndPages)) {
			 map = JSON.parseObject(buyer_idAndPages,Map.class); 
			 System.out.println("进入方法");
			 System.out.println(buyer_idAndPages);
		}
		
		System.out.println(map.containsKey("buyer_id"));
		 map = recordService.queryAllBuyerShoppingCart(map.get("buyer_id").toString(),map.get("currentPage").toString());
		printJson(response, map);
	}
	
	/**
	 * 《肖越根据前台页面需求额外新添加的方法 6》
	 * 大家如果有需要就调用就好了
	 * 此方法根据买家id获取买家购物车总条数和总页数    例：传过来的json字符串 {"buyer_id":"1"}
	 * @param buyer_id 买家id
	 * @return  根据买家id获取(shoppingCart)买家购物车总条数 和 总页数(totalPages 按照一页显示五条数据来计算)
	 */
	public void queryCountShoppingCart(HttpServletRequest request,HttpServletResponse response, String buyer_id) {
		Map map=null;
		if (!StringUtils.isEmpty(buyer_id)) {
			
			 map = JSON.parseObject(buyer_id,Map.class); 
			 System.out.println("进入方法");
			 System.out.println(buyer_id);
		}
		
		System.out.println(map.containsKey("buyer_id"));
		 map = recordService.queryCountShoppingCart(map.get("buyer_id").toString());
		printJson(response, map);
	}
	
	
	/**
	 * 《肖越根据前台页面需求额外新添加的方法 7》
	 * 大家如果有需要就调用就好了      例：传过来的json字符串     {"name":"三","buyer_id":"1"}
	 * 根据传过来的买家id和商品名称（支持模糊商品名称）查询购物车中相对应的商品
	 * @param buyer_id 买家id
	 * @param name 商品名
	 * @return 根据传过来的买家id和商品名称（支持模糊商品名称）查询购物车中相对应的商品
	 * @throws SQLException
	 */
	public void queryDimBuyerShoppingCart(HttpServletRequest request,HttpServletResponse response, String buyer_idAndName) {
		Map map=null;
		if (!StringUtils.isEmpty(buyer_idAndName)) {
			 map = JSON.parseObject(buyer_idAndName,Map.class); 
			 System.out.println("进入方法");
			 System.out.println(buyer_idAndName);
		}
		
		System.out.println(map.containsKey("buyer_id"));
		 map = recordService.queryDimBuyerShoppingCart(map.get("buyer_id").toString(),map.get("name").toString());
		printJson(response, map);
		
	}
	
	
	
	/**
	 * 《肖越根据前台页面需求额外新添加的方法 8》
	 * 根据买家id和购物车id删除对应的购物车商品       例：传过来的json字符串     {"shopping_scart_id":"2","buyer_id":"1"}
	 * @param buyer_id 买家id
	 * @param shopping_scart_id  购物车id
	 * @return
	 */
	public void removeShoppingCartByBuyerIdAndShoppingCartId(HttpServletRequest request,HttpServletResponse response,String shoppingScartIdAndBuyerId) {
		Map map=null;
		if (!StringUtils.isEmpty(shoppingScartIdAndBuyerId)) {
			
			 map = JSON.parseObject(shoppingScartIdAndBuyerId,Map.class); 
			 System.out.println("进入方法");
			 System.out.println(shoppingScartIdAndBuyerId);
		}
		
		System.out.println(map.containsKey("buyer_id"));
		 map = recordService.removeShoppingCartByBuyerIdAndShoppingCartId(map.get("buyer_id").toString(), map.get("shopping_scart_id").toString());
		printJson(response, map);
	}
	
	/**
	 * 《肖越根据前台页面需求额外新添加的方法 9》
	 * @param goods_id 商品id
	 * @return 根据商品id返回该商品所对应的详细信息,  例：传过来的json字符串  {"goods_id":"1"}
	 */
	public void queryProductDetailsByGoodsId(HttpServletRequest request,HttpServletResponse response, String goods_id) {
		Map map=null;
		if (!StringUtils.isEmpty(goods_id)) {
			 map = JSON.parseObject(goods_id,Map.class); 
			 System.out.println("进入方法");
			 System.out.println(goods_id);
		}
		
		System.out.println(map.containsKey("goods_id"));
		 map = recordService.queryProductDetailsByGoodsId(map.get("goods_id").toString());
		printJson(response, map);
		
		
	}
	
	/**
	 * 《肖越根据前台页面需求额外新添加的方法 10》
	 * @param goods_name 商品名称     例：传过来的json字符串    {"goods_name":"夏"}
	 * @return 根据商品名称（支持模糊查询），查询所对应的商品信息(只要第一条)
	 */
	public void queryDimDetailsByGoodsName(HttpServletRequest request,HttpServletResponse response, String goods_name) {
		Map map=null;
		if (!StringUtils.isEmpty(goods_name)) {
			 map = JSON.parseObject(goods_name,Map.class); 
			 System.out.println("进入方法");
			 System.out.println(goods_name);
		}
		
		System.out.println(map.containsKey("goods_name"));
		 map = recordService.queryDimDetailsByGoodsName(map.get("goods_name").toString());
		printJson(response, map);
		
	}
	
	
	/**
	 * 《肖越根据前台页面需求额外新添加的方法 11》
	 * @param goods_id 商品id   例：传过来的json字符串  {"goods_id":"1"}
	 * @return 根据商品id返回同一店铺相关商品的前两条数据
	 */
	public void queryRelatedProductsByGoodsId(HttpServletRequest request,HttpServletResponse response, String goods_id) {
		Map map=null;
		if (!StringUtils.isEmpty(goods_id)) {
			 map = JSON.parseObject(goods_id,Map.class); 
			 System.out.println("进入方法");
			 System.out.println(goods_id);
		}
		
		System.out.println(map.containsKey("goods_id"));
		 map = recordService.queryRelatedProductsByGoodsId(map.get("goods_id").toString());
		printJson(response, map);
		
	}
	
	
	/**
	 * 《肖越根据前台页面需求额外新添加的方法 12》
	 * @param buyer_id 买家id
	 * @param goods_id 商品id
	 * @param amount_of_goods 添加商品数量
	 * @param shopping_cart_create_time 购物车创建时间
	 * 例：传过来的json字符串             {"amount_of_goods":"5","goods_id":"73","buyer_id":"19","shopping_cart_create_time":"2019-12-21"}
	 * @return 根据买家id、商品id、商品数量、购物车创建时间增加购物车信息
	 */
	public void addAddShoppingCart(HttpServletRequest request,HttpServletResponse response,String shoppingCartRelatedInformation) {
		Map map=null;
		if (!StringUtils.isEmpty(shoppingCartRelatedInformation)) {
			
			 map = JSON.parseObject(shoppingCartRelatedInformation,Map.class); 
			 System.out.println("进入方法");
			 System.out.println(shoppingCartRelatedInformation);
		}
		
		System.out.println(map.containsKey("buyer_id"));
		System.out.println(map.containsKey("goods_id"));
		 map = recordService.addAddShoppingCart(map.get("buyer_id").toString(),map.get("goods_id").toString(),map.get("amount_of_goods").toString(),map.get("shopping_cart_create_time").toString());
		printJson(response, map);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
