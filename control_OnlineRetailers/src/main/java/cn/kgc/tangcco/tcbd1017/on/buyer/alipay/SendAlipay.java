package cn.kgc.tangcco.tcbd1017.on.buyer.alipay;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;

import cn.kgc.tangcco.lihaozhe.commons.alipay.config.AlipayConfig;
import cn.kgc.tangcco.lihaozhe.commons.alipay.config.IDUtils;

@WebServlet(urlPatterns = "/sendAlipay.action")
public class SendAlipay extends HttpServlet {

	private static final long serialVersionUID = -829187117635915433L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
				AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key,
				AlipayConfig.sign_type);

		// 设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(AlipayConfig.return_url);
		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

		// 商户订单号，商户网站订单系统中唯一订单号，必填
		String out_trade_no = IDUtils.createID();
		// 付款金额，必填
		String total_amount = new String(request.getParameter("WIDtotal_amount").getBytes("ISO-8859-1"), "UTF-8");
		// 订单名称，必填
		String subject = "支付宝第三方测试";
		// 商品描述，可空
		String body = "今晚全场的消费由赵公子买单,尖叫声！";

		alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"total_amount\":\"" + total_amount
				+ "\"," + "\"subject\":\"" + subject + "\"," + "\"body\":\"" + body + "\","
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

		// 若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
		// alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
		// + "\"total_amount\":\""+ total_amount +"\","
		// + "\"subject\":\""+ subject +"\","
		// + "\"body\":\""+ body +"\","
		// + "\"timeout_express\":\"10m\","
		// + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		// 请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节

		// 请求
		String result = null;
		try {
			result = alipayClient.pageExecute(alipayRequest).getBody();
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(result);
		// 设置编码的方式为html
		response.setContentType("text/html;charset=utf-8");

		// 创建out的对象(打印) 打印出来支付宝给我们自动生成的表单在页面上
		response.getWriter().write(JSON.toJSONString(result));
	}

}
