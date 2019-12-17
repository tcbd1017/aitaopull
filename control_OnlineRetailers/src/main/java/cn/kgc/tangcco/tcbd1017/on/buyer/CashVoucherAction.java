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
 * @author 赵瑞涛,
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
		
		map = cashVoucherService.queryByIdAndStatus(map);
		System.out.println("返回的参数：");
		List<Map<String, Object>> list = (List<Map<String, Object>>) map.get("info");
		Iterator<Map<String, Object>> iterator = list.iterator();
		if (list.size()>0) {
			while (iterator.hasNext()) {
				Map<String, Object> map2 = (Map<String, Object>) iterator.next();
				System.out.println("店铺名称"+map2.get("store_name"));
				System.out.println("图片地址"+map2.get("voucher_type_picture_url"));
				System.out.println("开始使用的时间"+map2.get("cash_voucher_acailable_begin_time"));
				System.out.println("停止使用的时间"+map2.get("cash_voucher_available_time"));
				System.out.println("使用条件"+map2.get("voucher_type_condition"));
				System.out.println("见面金额"+map2.get("voucher_type_money"));
			}
		printJson(response, map);
		}
	}
	/**
	 * 					查询总数
	 * @param request	HttpServletRequest
	 * @param response	HttpServletResponse
	 * @param json		json字符串
	 */
	public void queryNumberByIdAndStatus(HttpServletRequest request,HttpServletResponse response,String json) {
		Map map=null;
		map=JSON.parseObject(json,Map.class);
		System.out.println("查询可以使用的优惠券总量：");
		System.out.println("参数：买家的id和优惠券的状态"+json);
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
		System.out.println("页数："+map.get("pageNumber")+"条数"+map.get("pageSize"));
		map = cashVoucherService.queryByIdAndStatusAndPagereng(map);
		System.out.println("返回参数"+map);
		printJson(response, map);
	}
	/**
	 * 					通过名称查询
	 * @param request	HttpServletRequest
	 * @param response	HttpServletResponse
	 * @param json		json字符串
	 */
	public void queryByIdAndStatusAndStoreName(HttpServletRequest request ,HttpServletResponse response ,String json) {
		Map map = null;
		map = JSON.parseObject(json,Map.class);
		System.out.println("通过买家查询：");
		System.out.println("返回的json"+json);
		System.out.println("买家id："+map.get("buyer_id"));
		System.out.println("优惠券的状态："+map.get("cash_voucher_status"));
		System.out.println("店铺名称："+map.get("store_name"));
		map = cashVoucherService.queryByIdAndStatusAndStoreName(map);
		System.out.println("返回的参数："+map);
		printJson(response, map);
	}
}
