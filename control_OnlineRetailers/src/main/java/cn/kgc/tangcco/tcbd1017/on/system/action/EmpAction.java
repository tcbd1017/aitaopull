package cn.kgc.tangcco.tcbd1017.on.system.action;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import cn.kgc.tangcco.lihaozhe.commons.servlet.BaseServlet;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.pojo.Emp;
import cn.kgc.tangcco.tcbd1017.on.system.EmpService;

/**
 * @author XUE TONG
 * @version 1.0 2019年12月24日下午4:29:12 </br>
 **/
@WebServlet(urlPatterns = "/Emp.action")
public class EmpAction extends BaseServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2467869938043805880L;
	private static EmpService empService;
	private static ClassPathXmlApplicationContext path = new ClassPathXmlApplicationContext(
			"ApplicationContext_on.xml");
	static {
		try {
			empService = (EmpService) path.getBean(EmpService.class.getSimpleName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selectEmp(HttpServletRequest request, HttpServletResponse response, String json) {

	Emp emp = JSON.parseObject(json, Emp.class);
	Map<String, Object> map = null;
	try {
		map = empService.selectEmp(emp);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	printJson(response, map);
	}
}
