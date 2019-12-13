package cn.kgc.tangcco.lihaozhe.commons.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;

/**
 * Servlet implementation class BaseServlet
 */
public class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BaseServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String methodName = Optional.ofNullable(request.getParameter("methodName")).orElse("");
		if (StringUtils.isNotEmpty(methodName)) {
			try {
				if (StringUtils.isNotEmpty(request.getHeader("content-type"))) {
					if (getContentType(request).contains("application/json")) {
						// ajax方式
						BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream) request.getInputStream(), "utf-8"));
						StringBuffer sb = new StringBuffer();
						String temp;
						while ((temp = br.readLine()) != null) {
							sb.append(temp);
						}
						br.close();
						Method method = this.getClass().getDeclaredMethod(methodName, HttpServletRequest.class,HttpServletResponse.class, String.class);
						method.setAccessible(true);
						method.invoke(this, request, response, sb.toString());
					} else {
						// 传统表单方式
						Method method = this.getClass().getDeclaredMethod(methodName, HttpServletRequest.class,
								HttpServletResponse.class);
						method.setAccessible(true);
						method.invoke(this, request, response);
					}

				} else {
					// RequertHeader中没有Content-Type 使用传统表单方式
					Method method = this.getClass().getDeclaredMethod(methodName, HttpServletRequest.class,
							HttpServletResponse.class);
					method.setAccessible(true);
					method.invoke(this, request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	private String getContentType(HttpServletRequest request) {
		Optional<String> ofNullable = Optional.ofNullable(request.getHeader("content-type"));
		return ofNullable.orElse("");
	}
	/**
	 * 将字符串向客户端输出
	 * @param response	HttpServletResponse
	 * @param json		传入的字符串
	 */
	public void printJson(HttpServletResponse response, String json) {
		try {
			PrintWriter writer = response.getWriter();
			writer.print(json);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 将对象转为json字符串向客户端输出
	 * @param response	HttpServletResponse
	 * @param object	传入的对象
	 */
	public void printJson(HttpServletResponse response,Object object) {
		printJson(response, JSON.toJSONString(object));
	}
	/**
	 * 目标地址增加默认前缀
	 * 
	 * @param path 目标地址
	 * @return 增加前缀后的目标地址
	 */
	public String prefix(String path) {
		return "/WEB-INF/" + path;
	}
	/**
	 * 目标地址增加后缀
	 * @param path	目标地址
	 * @return		增加后缀后的目标地址
	 */
	public String subfix(String path) {
		return path + ".jsp";
	}
	/**
	 * 请求转发
	 * @param request	HttpServletRequest
	 * @param response	HttpServletResponse
	 * @param path		跳转路径
	 */
	public void forword(HttpServletRequest request, HttpServletResponse response, String path) {
		try {
			request.getRequestDispatcher(path).forward(request, response);
			return;
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 网页重定向
	 * @param request	HttpServletRequest
	 * @param response	HttpServletResponse
	 * @param location	跳转路径
	 */
	public void redirect(HttpServletRequest request, HttpServletResponse response, String location) {
		try {
			response.sendRedirect(location);
			return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
