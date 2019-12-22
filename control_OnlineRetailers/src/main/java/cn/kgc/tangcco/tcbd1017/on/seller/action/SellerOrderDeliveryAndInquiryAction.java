package cn.kgc.tangcco.tcbd1017.on.seller.action;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import cn.kgc.tangcco.lihaozhe.commons.servlet.BaseServlet;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.seller.SellerOrderDeliveryAndInquiryService;
/**
*@author   郝彦冰<br>
*@version  创建时间 :  2019年12月16日  下午1:41:24<br>
*类说明:  		商家发快递、更改待发货状态        action层
*/
/**
 * Servlet implementation class SellerOrderDeliveryAndInquiryAction
 */
@WebServlet(urlPatterns = "/SellerOrderDeliveryAndInquiry.action")
public class SellerOrderDeliveryAndInquiryAction extends BaseServlet {
	private static final long serialVersionUID = -6754948734293900058L;
	private static SellerOrderDeliveryAndInquiryService sellerOrderDeliveryAndInquiryService;
	private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext_on.xml");
	static {
		try {
			sellerOrderDeliveryAndInquiryService = (SellerOrderDeliveryAndInquiryService) context.getBean(SellerOrderDeliveryAndInquiryService.class.getSimpleName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
     * @see HttpServlet#HttpServlet()
     */
    public SellerOrderDeliveryAndInquiryAction() {
        super();
    }

	/**
	 * 查询地址
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
//	protected void queryAddress(HttpServletRequest request, HttpServletResponse response,String json) throws ServletException, IOException {
//		Map map = JSON.parseObject(json, Map.class);
//		Map queryAddress = sellerOrderDeliveryAndInquiryService.queryAddress();
//		printJson(response, queryAddress);
//	}
	/**
	 * 查询买家收货地址
	 * 需要前台传入 （ flig 订单状态 ；，   店铺id："store_id"  ；  例如：869910 ）
	 * ---------------------------------------------------------
	 * 返回数据  map
	 * key:order_id                    value:订单id
	 * key:postage_info_name           value:收货人姓名
	 * key:buyer_name                  value:买家姓名
	 * key:goods_name                  value:商品名称
	 * key:amount_of_goods             value:商品数量
	 * key:postage_info_mobile         value:收件人手机号
	 * key:postage_info_province       value:收件人所在省份
	 * key:postage_info_city           value:收件人所在城市
	 * key:postage_info_district       value:收件人所在县区
	 * key:postage_info_address        value:收件人详细地址
	 * key:postage_info_postcode       value:收件人邮编
	
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void querySellerOrderDeliveryQueryBuyerReceiptInformation(HttpServletRequest request, HttpServletResponse response,String json) throws ServletException, IOException {
		Map map = JSON.parseObject(json, Map.class);
		Map queryAddress = sellerOrderDeliveryAndInquiryService.querySellerOrderDeliveryQueryBuyerReceiptInformation(map);
		printJson(response, queryAddress);
	}
	/**
	 * 查询卖家发货
	 * 前台传入    店铺id： store_id                例如：869910
	 * --------------------------------------------------
	 * 返回
	 * key:seller_name                    	value:卖家姓名
	 * key:seller_mobile                    value:买家手机号
	 * key:seller_info_address              value:卖家发货地址
	 * 
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void querySellerShippingAddress(HttpServletRequest request, HttpServletResponse response,String json) throws ServletException, IOException {
		Map map = JSON.parseObject(json, Map.class);
		System.out.println("45678");
		Map queryAddress = sellerOrderDeliveryAndInquiryService.querySellerShippingAddress(map);
		printJson(response, queryAddress);
	}
	
	/**
	 * 更改订单待发货状态
	 * 前台 传入：   订单id    “order_id”
	 * ---------------------------------------
	 * 返回   status： success
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void modifyOrderStatus(HttpServletRequest request, HttpServletResponse response,String json) throws ServletException, IOException {
		Map map = JSON.parseObject(json, Map.class);
		Map modifyOrderStatus = sellerOrderDeliveryAndInquiryService.modifyOrderStatus(map);
		printJson(response, modifyOrderStatus);
	}

}
