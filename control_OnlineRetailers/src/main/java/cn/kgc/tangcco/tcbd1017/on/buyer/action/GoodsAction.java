package cn.kgc.tangcco.tcbd1017.on.buyer.action;

import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;

import cn.kgc.tangcco.lihaozhe.commons.servlet.BaseServlet;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.buyer.GoodsDao;
import cn.kgc.tangcco.tcbd1017.on.buyer.GoodsService;

/**
*@author 作者：肖越 
*@version 1.0 创建时间:2019年12月13日下午12:03:36
*/
@WebServlet(urlPatterns = "/xiao.action")
public class GoodsAction extends BaseServlet {
	static GoodsService goodsService;
	static ClassPathXmlApplicationContext path;
	static {
		path = new ClassPathXmlApplicationContext("");
		try {
			goodsService = (GoodsService) path.getBean(GoodsService.class.getSimpleName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static final long serialVersionUID = -7013535404678491370L;
	
	
	

	/**
	 *
	 * 查询所有商品，不需要额外传参
	 */
	public void queryAllGoods(HttpServletRequest request,HttpServletResponse response,String str) {
		
		Map<String, Object> map = goodsService.queryAllGoods();
		printJson(response, map);
	}
	
	
	/**
	 * 
	 * 
	 *
	 * @param goodsType 传过来的参数为json类型的map  例：{"goodsType":"毛绒玩具"}
	 * 根据类型搜索物品
	 */
	public void queryByGoodsType(HttpServletRequest request,HttpServletResponse response,String goodsType) {
		Map map=null;
		if (!StringUtils.isEmpty(goodsType)) {
			
			 map = JSON.parseObject(goodsType,Map.class); 
			 System.out.println("进入方法");
			 System.out.println(goodsType);
		}
		
		System.out.println(map.containsKey("goodsType"));
		 map = goodsService.queryByGoodsType(map.get("goodsType").toString());
		printJson(response, map);
		
		
	}
	
	/**
	 * 按照商品名称、或者商品类型、或者商品简介 ,模糊查询出相关商品
	 * @param vague 商品名称、或者商品类型、或者商品简介
	 * @return map类型
	 */
	public void queryVagueByGoods_nameOrGoods_brandOrGoods_presentation(HttpServletRequest request,HttpServletResponse response,String vague) {
		Map map=null;
		if (!StringUtils.isEmpty(vague)) {
			
			 map = JSON.parseObject(vague,Map.class); 
			 System.out.println("进入方法");
			 System.out.println(vague);
		}
		
		System.out.println(map.containsKey("vague"));
		 map = goodsService.queryVagueByGoods_nameOrGoods_brandOrGoods_presentation(map.get("vague").toString());
		printJson(response, map);
	}
	
	
	
}
