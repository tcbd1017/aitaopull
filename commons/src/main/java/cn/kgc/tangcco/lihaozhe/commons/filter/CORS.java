package cn.kgc.tangcco.lihaozhe.commons.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class CORS
 */
@WebFilter("*.action")
public class CORS implements Filter {

	/**
	 * Default constructor.
	 */
	public CORS() {
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 强制转换参数的数据类型
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		// 处理中文乱码
		// 处理post请求的中文乱码
		req.setCharacterEncoding("utf-8");
		// 处理响应的字符集中文乱码
		resp.setCharacterEncoding("utf-8");
		// 处理浏览器字符集中文乱码
		resp.setContentType("text/html;charset=utf-8");

		String requestMethod = req.getMethod();
		String remoteHost = req.getRemoteHost();
		// System.out.println(remoteHost);
		System.out.println("请求方式 >>> " + requestMethod);
		if ("OPTIONS".equalsIgnoreCase(requestMethod)) {
			// ajax跨域
			resp.setHeader("Access-Control-Allow-Origin", "*");
//			resp.setHeader("Access-Control-Allow-Origin",  req.getHeader("Origin"));
			resp.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
			resp.setHeader("Access-Control-Max-Age", "3600");
			resp.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
			resp.setHeader("Access-Control-Allow-Credentials", "true");
			return;
		} else if (!"OPTIONS".equalsIgnoreCase(requestMethod)) {
			// ajax跨域
			resp.setHeader("Access-Control-Allow-Origin", "*");
//			resp.setHeader("Access-Control-Allow-Origin",  req.getHeader("Origin"));
			resp.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
			resp.setHeader("Access-Control-Max-Age", "3600");
			resp.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
			resp.setHeader("Access-Control-Allow-Credentials", "true");
			// 放行通过过滤器
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
