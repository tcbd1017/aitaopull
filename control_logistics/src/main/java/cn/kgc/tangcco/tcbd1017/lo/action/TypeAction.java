package cn.kgc.tangcco.tcbd1017.lo.action;

import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.kgc.tangcco.tcbd1017.lo.TypeService;
import cn.kgc.tangcco.tcbd1017.lo.commons.jdbc.ServiceFactory;
import cn.kgc.tangcco.tcbd1017.lo.commons.servlet.BaseServlet;
import cn.kgc.tangcco.tcbd1017.lo.impl.TypeServiceImpl;


@WebServlet(urlPatterns = "/type.action")
public class TypeAction extends BaseServlet {

	public static TypeService ts=(TypeService) ServiceFactory.getService(new TypeServiceImpl());
	private static final long serialVersionUID = -9096192448208878416L;

	/**
	 * 	将商品类型返回
	 * @param request
	 * @param response
	 */
	protected void selectType(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> map = null;
		Map<String, Object> selectType = ts.selectType(map);
		printJson(response, selectType);
	}
}
