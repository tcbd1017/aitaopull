package cn.kgc.tangcco.tcbd1017.lo.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;

import cn.kgc.tangcco.tcbd1017.lo.MoneyService;
import cn.kgc.tangcco.tcbd1017.lo.commons.alipay.AlipayConfig;
import cn.kgc.tangcco.tcbd1017.lo.commons.jdbc.ServiceFactory;
import cn.kgc.tangcco.tcbd1017.lo.commons.servlet.BaseServlet;
import cn.kgc.tangcco.tcbd1017.lo.impl.MoneyServiceImpl;



@WebServlet(urlPatterns = "/pay.action")
public class AlipayAction extends BaseServlet{
	private static final long serialVersionUID = 1116436214066428953L;
	
	MoneyService ms =(MoneyService) ServiceFactory.getService(new MoneyServiceImpl());
	
	public void alipay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置响应的数据为html
		response.setContentType("text/html;charset=utf-8");
		// 获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
				AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key,
				AlipayConfig.sign_type);

		// 设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(AlipayConfig.return_url);
		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

		String money_uuid = request.getParameter("money_uuid");
		String money = request.getParameter("money");
		// 商户订单号，商户网站订单系统中唯一订单号，必填
		String out_trade_no = money_uuid;
		System.out.println("商城自己生成的订单号是:" + out_trade_no);
		// 付款金额，必填
		String total_amount =money;
		System.out.println(total_amount);
		// 订单名称，必填
		String subject = "环球物流";
		System.out.println(subject.toString());
		// 商品描述，可空
		String body = request.getParameter("WIDbody");

		alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"total_amount\":\"" + total_amount
				+ "\"," + "\"subject\":\"" + subject + "\"," + "\"body\":\"" + body + "\","
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

		// 请求
		String result = null;
		try {
			result = alipayClient.pageExecute(alipayRequest).getBody();
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		// 输出
		response.getWriter().write(result);
	}
	
	public void callBack(HttpServletRequest request, HttpServletResponse response) throws IOException, AlipayApiException {
		/**
		 * 支付宝的回调 告诉你 1.支付宝订单号 2.自己商城生成的订单号 3.付款金额
		 */
		// 商户订单号
		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");
		// 支付宝交易号
		String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
		// 付款金额
		String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");
		//设置相应的编码格式是 html
		response.setContentType("text/html;charset=utf-8");
		System.out.println("订单号:"+out_trade_no);
		System.out.println("支付宝交易号"+trade_no);
		System.out.println("付款金额"+total_amount);
		Map<String, Object> map =new HashMap<String ,Object > ();
		map.put("money_uuid", out_trade_no);
		Map<String, Object> payByMoneyId = ms.payByMoneyId(map);
//		printJson(response, payByMoneyId);
	}
	
}
