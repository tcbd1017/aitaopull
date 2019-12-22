package cn.kgc.tangcco.tcbd1017.on.seller.action;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import cn.kgc.tangcco.lihaozhe.commons.servlet.BaseServlet;
import cn.kgc.tangcco.tcbd1017.on.seller.SellerGoodsService;
import cn.kgc.tangcco.tcbd1017.on.seller.impl.SellerGoodsServiceImpl;

/**
 * Servlet implementation class SellerGoodsAction
 */
@WebServlet("/SellerGoodsAction.action")
public class SellerGoodsAction extends BaseServlet {
	private static final long serialVersionUID = -4681593050984713835L;
	
	SellerGoodsService goodsService = new SellerGoodsServiceImpl();

	public SellerGoodsAction() {
        super();
    }

	/**
	 * 查询店铺所有未上架的商品 前台 store_id商铺id 查看所有商品 存储在Map里面
	 * 
	 * @param request  请求
	 * @param response 相应
	 * @param json     传输的数据
	 * @throws ServletException 异常
	 * @throws IOException      异常
	 */
	protected void selectOfflineGoods(HttpServletRequest request, HttpServletResponse response, String json)
			throws ServletException, IOException {
		System.out.println("查询所有未上架商品");
		Map parseObject = JSON.parseObject(json, Map.class);
		System.out.println(parseObject.toString());
		Map<String, Object> map = goodsService.selectOfflineGoods(parseObject);
		printJson(response, map);
	}

	/**
	 * 查询店铺所有以上架的商品 前台 store_id商铺id 查看所有商品 存储在Map里面
	 * 
	 * @param request  请求
	 * @param response 相应
	 * @param json     传输的数据
	 * @throws ServletException 异常
	 * @throws IOException      异常
	 */
	protected void selectOnlineGoods(HttpServletRequest request, HttpServletResponse response, String json)
			throws ServletException, IOException {
		System.out.println("查询所有上架商品");
		Map parseObject = JSON.parseObject(json, Map.class);
		System.out.println(parseObject.toString());
		Map map = goodsService.selectOnlineGoods(parseObject);
		printJson(response, map);
	}
	/**
	 * 查询店铺所有以上架的商品 前台 store_id商铺id 查看所有商品 存储在Map里面
	 * 
	 * @param request  请求
	 * @param response 相应
	 * @param json     传输的数据
	 * @throws ServletException 异常
	 * @throws IOException      异常
	 */
	protected void selectWholeGoods(HttpServletRequest request, HttpServletResponse response, String json)
			throws ServletException, IOException {
		System.out.println("查询所有商品信息");
		Map parseObject = JSON.parseObject(json, Map.class);
		System.out.println(parseObject.toString());
		Map map = goodsService.selectWholeGoods(parseObject);
		printJson(response, map);
	}

	/**
	 * 上架商品 根据goods_id商品id选择
	 * 
	 * @param request  请求
	 * @param response 相应
	 * @param json     数据
	 * @throws ServletException 异常
	 * @throws IOException      异常
	 */
	protected void supdateGoods(HttpServletRequest request, HttpServletResponse response, String json)
			throws ServletException, IOException {
		System.out.println("上架商品");
		Map parseObject = JSON.parseObject(json, Map.class);
		Map map = goodsService.supdateGoods(parseObject);
		printJson(response, map);
	}

	/**
	 * 下架商品 根据goods_id商品id选择
	 * 
	 * @param request  请求
	 * @param response 相应
	 * @param json     数据
	 * @throws ServletException 异常
	 * @throws IOException      异常
	 */
	protected void xupdateGoods(HttpServletRequest request, HttpServletResponse response, String json)
			throws ServletException, IOException {
		System.out.println("下架商品");
		Map parseObject = JSON.parseObject(json, Map.class);
		Map map = goodsService.xupdateGoods(parseObject);
		printJson(response, map);
	}

	/**
	 * 根据商品id 修改价格
	 * 
	 * @param request  请求
	 * @param response 相应
	 * @param json     数据
	 * @throws ServletException 异常
	 * @throws IOException      异常
	 */
	protected void updateGoodsgoods_price(HttpServletRequest request, HttpServletResponse response, String json)
			throws ServletException, IOException {
		System.out.println("修改商品");
		Map parseObject = JSON.parseObject(json, Map.class);
		Map map = goodsService.updateGoodsgoods_price(parseObject);
		printJson(response, map);
	}

}
