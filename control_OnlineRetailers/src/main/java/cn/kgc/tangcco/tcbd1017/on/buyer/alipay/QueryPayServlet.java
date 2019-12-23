package cn.kgc.tangcco.tcbd1017.on.buyer.alipay;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/querypay.action")
public class QueryPayServlet extends HttpServlet {

	private static final long serialVersionUID = -8700370880413188725L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/**
		 * 支付宝的回调 告诉你 1.支付宝订单号 2.自己商城生成的订单号 3.付款金额
		 */

		// 商户订单号
		String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

		// 支付宝交易号
		String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");

		// 付款金额
		String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"), "UTF-8");

		// 设置相应的编码格式是 html
		response.setContentType("text/html;charset=utf-8");

//		 创建out的对象,打印出来信息
		response.getWriter()
				.write("商户订单号:" + out_trade_no + "<br/>" + "<br/>支付宝订单号:+" + trade_no + "付款金额:" + total_amount);
	}

}
