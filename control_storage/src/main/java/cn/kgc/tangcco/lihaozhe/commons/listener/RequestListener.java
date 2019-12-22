package cn.kgc.tangcco.lihaozhe.commons.listener;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

import cn.kgc.tangcco.lihaozhe.commons.localdatetime.LocalDateTimeUtil;

/**
 * Application Lifecycle Listener implementation class UserListener
 *
 */
@WebListener
public class RequestListener implements ServletRequestAttributeListener, ServletRequestListener {

	/**
	 * Default constructor.
	 */
	public RequestListener() {
	}

	/**
	 * @see ServletRequestListener#requestDestroyed(ServletRequestEvent)
	 */
	public void requestDestroyed(ServletRequestEvent sre) {

	}

	/**
	 * @see ServletRequestAttributeListener#attributeRemoved(ServletRequestAttributeEvent)
	 */
	public void attributeRemoved(ServletRequestAttributeEvent srae) {
	}

	/**
	 * @see ServletRequestListener#requestInitialized(ServletRequestEvent)
	 */
	public void requestInitialized(ServletRequestEvent sre) {
		// 获取ServletRequest对象
		ServletRequest servletRequest = sre.getServletRequest();
		// 将ServletRequest对象强转为HttpServletRequest对象
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		// 获取访问者IP地址
		String remoteAddr = request.getRemoteAddr();
		// 货期被访问页面
		String url = request.getRequestURL().toString();
		System.out.println(LocalDateTimeUtil.getStringByCurrentLocalDateTime() + "\t" + remoteAddr + "\t" + url);
	}

	/**
	 * @see ServletRequestAttributeListener#attributeAdded(ServletRequestAttributeEvent)
	 */
	public void attributeAdded(ServletRequestAttributeEvent srae) {
	}

	/**
	 * @see ServletRequestAttributeListener#attributeReplaced(ServletRequestAttributeEvent)
	 */
	public void attributeReplaced(ServletRequestAttributeEvent srae) {
	}

}
