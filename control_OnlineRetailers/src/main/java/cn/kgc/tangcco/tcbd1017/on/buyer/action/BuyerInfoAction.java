package cn.kgc.tangcco.tcbd1017.on.buyer.action;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;

import cn.kgc.tangcco.lihaozhe.commons.servlet.BaseServlet;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.buyer.BuyerInfoService;
import cn.kgc.tangcco.tcbd1017.on.buyer.impl.BuyerInfoServiceImple;
/**
 * 
 * @author Administrator 朱浩;
 *
 */
@WebServlet(urlPatterns = "/xinxi.action")
public class BuyerInfoAction extends BaseServlet {

	
	private static final long serialVersionUID = -706033932898887493L;
	private static BuyerInfoService buyerInfoService;
	private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
			"ApplicationContext_on.xml");
	static {
		try {
			buyerInfoService = ( BuyerInfoService) context.getBean( BuyerInfoService.class.getSimpleName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	BuyerInfoService buyerInfoService=new BuyerInfoServiceImple();
	/**
	 * 查询多个和单个
	 * @param request
	 * @param response
	 * @param json
	 * @throws SQLException 
	 */
	public void	 queryBuyerInfoByBuyer_id(HttpServletRequest request,HttpServletResponse response,String json) throws SQLException {
		Map map=JSON.parseObject(json,Map.class);
		Map queryBuyerInfoByBuyer_id = buyerInfoService.queryBuyerInfoByBuyer_id(map);
	  printJson(response, queryBuyerInfoByBuyer_id);
}
	/**
	 * 根据买家id修改和删除
	 * @param request
	 * @param response
	 * @param json
	 */
	public void	 removeAndModify_BuyerInfoBybuyer_id(HttpServletRequest request,HttpServletResponse response,String json) {
		Map map=JSON.parseObject(json,Map.class);
		Map removeAndModify_BuyerInfoBybuyer_id = buyerInfoService.removeAndModify_BuyerInfoBybuyer_id(map);
	  printJson(response, removeAndModify_BuyerInfoBybuyer_id);
	}
	//添加
	public void	 addBuyerInfo(HttpServletRequest request,HttpServletResponse response,String json) {
		Map map1=null;
		if (!StringUtils.isEmpty(json)) {
			
			map1 = JSON.parseObject(json,Map.class); 
			System.out.println("进入方法");
			System.out.println(json);
		}
		
	
		try {
			map1 =buyerInfoService.queryBuyerInfoByBuyer_id(map1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		printJson(response, map1);
	}
	
	
	
}
