package cn.kgc.tangcco.tcbd1017.on.buyer;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.PageRang;
import cn.kgc.tangcco.lihaozhe.commons.servlet.BaseServlet;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.buyer.CashVoucherService;

/**
 * @author 赵瑞涛
 * @version v1.0<br>
 * 	创建时间:	2019年12月13日	下午11:13:27<br>
 * 	类描述:
 */
@WebServlet("/CashVoucherAction.action")
public class CashVoucherAction extends BaseServlet{
	
	private static final long serialVersionUID = 856731823171557395L;
	/**
	 * 
	 */
	
	private static CashVoucherService cashVoucherService;
	private static ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("ApplicationContext_on.xml");
	static {
		try {
			cashVoucherService = (CashVoucherService) classPathXmlApplicationContext.getBean(CashVoucherService.class.getSimpleName());
		} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}
	
	/**
	 * 					通过id和状态查询优惠券信息
	 * @param request	HttpServletRequest
	 * @param response	HttpServletResponse
	 * @param json		json为字符串
	 */
	public void queryByIdAndStatus(HttpServletRequest request, HttpServletResponse response,String json) {
		Map map=null;
		map=JSON.parseObject(json,Map.class);
		System.out.println("查询可以使用的优惠券：");
		System.out.println("参数：买家的id和优惠券的状态"+json);
		map.put("cash_voucher_status", 2);
		map = cashVoucherService.queryByIdAndStatus(map);
		System.out.println("返回的参数："+map);
		List<Map<String, Object>> list = (List<Map<String, Object>>) map.get("info");
		printJson(response, map);
		}
	/**
	 * 					查询总数(可使用)
	 * @param request	HttpServletRequest
	 * @param response	HttpServletResponse
	 * @param json		json字符串
	 */
	public void queryNumberByIdAndStatus(HttpServletRequest request,HttpServletResponse response,String json) {
		Map map=null;
		map=JSON.parseObject(json,Map.class);
		System.out.println("查询可以使用的优惠券总量：");
		System.out.println("参数：买家的id和优惠券的状态"+json);
		map.put("cash_voucher_status", 2);
		map = cashVoucherService.queryNumberByIdAndStatus(map);
		System.out.println("返回的信息："+map);
		printJson(response, map);
	}
	/**
	 * 					查询总数(不可使用)
	 * @param request	HttpServletRequest
	 * @param response	HttpServletResponse
	 * @param json		json字符串
	 */
	public void queryNumberByIdAndStatus2(HttpServletRequest request,HttpServletResponse response,String json) {
		Map map=null;
		map=JSON.parseObject(json,Map.class);
		System.out.println("查询不可以使用的优惠券总量：");
		System.out.println("参数：买家的id和优惠券的状态"+json);
		map.put("cash_voucher_status", 1);
		map = cashVoucherService.queryNumberByIdAndStatus(map);
		System.out.println("返回的信息："+map);
		printJson(response, map);
	}
	/**
	 * 					删除一个优惠券
	 * @param request	HttpServletRequest
	 * @param response	HttpServletResponse
	 * @param json		json字符串
	 */
	public void removeByUuid(HttpServletRequest request ,HttpServletResponse response,String json) {
		Map map=null;
		map=JSON.parseObject(json,Map.class);
		map.put("cash_voucher_status", 2);
		System.out.println("删除一个优惠券：");
		System.out.println("uuid为"+json);
		map = cashVoucherService.removeByUuid(map);
		System.out.println("返回的参数"+map);
		printJson(response, map);
	}
	/**
	 * 					分页查询
	 * @param request	HttpServletRequest
	 * @param response	HttpServletResponse
	 * @param json		json
	 */
	public void queryByIdAndStatusAndPagereng(HttpServletRequest request ,HttpServletResponse response,String json) {
		Map map=null;
		map=JSON.parseObject(json,Map.class);
		System.out.println("分页查询：");
		System.out.println("买家的id和优惠券的状态以及分页的信息"+json);
		map.put("cash_voucher_status", 1); 
		System.out.println(map);
		map = cashVoucherService.queryByIdAndStatusAndPagereng(map);
		System.out.println("返回参数"+map);
		printJson(response, map);
	}
	
	
}
