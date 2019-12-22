package cn.kgc.tangcco.tcbd1017.on.system.action;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;


import cn.kgc.tangcco.lihaozhe.commons.servlet.BaseServlet;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.system.EmpLoginService;

/**
 * @author XUE TONG
 * @version 1.0 2019年12月19日下午3:19:20 </br>
 **/
@WebServlet(urlPatterns = "/EmpLogin.action")
public class EmpLoginAction extends BaseServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9050697972201337116L;
	private static EmpLoginService empLoginService;
	private static ClassPathXmlApplicationContext path = new ClassPathXmlApplicationContext(
			"ApplicationContext_on.xml");
	static {
		try {
			empLoginService = (EmpLoginService) path.getBean(EmpLoginService.class.getSimpleName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selectEmpLoginAccountAndPassword(HttpServletRequest request, HttpServletResponse response,
			String json) {
		Map map = JSON.parseObject(json, Map.class);
		Map<String, Object> map1 = null;
		try {
			map1 = empLoginService.selectEmpLoginAccountAndPassword(map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		printJson(response, map1);
	}
}
