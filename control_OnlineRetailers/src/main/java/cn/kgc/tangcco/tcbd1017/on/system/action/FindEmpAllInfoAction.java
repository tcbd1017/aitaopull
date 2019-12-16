package cn.kgc.tangcco.tcbd1017.on.system.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import cn.kgc.tangcco.lihaozhe.commons.servlet.BaseServlet;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.pojo.Emp;
import cn.kgc.tangcco.tcbd1017.on.system.FindEmpAllInfo;

/**
 * 
 * @author zhangmiao
 * @version	2019年12月15日	下午3:53:40
 *
 */
@WebServlet(urlPatterns = "/findEmpAllInfo.action")
public class FindEmpAllInfoAction extends BaseServlet{


	private static final long serialVersionUID = 9119832521400380089L;
	static FindEmpAllInfo feaii;
	static {
		ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("ApplicationContext_on.xml");
		try {
			feaii=(FindEmpAllInfo)classPathXmlApplicationContext.getBean(FindEmpAllInfo.class.getSimpleName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void findEmpAllInfo(HttpServletRequest request,HttpServletResponse response, String empString) {
		Emp emp = JSON.parseObject(empString, Emp.class);
		Map<String, Object> map = feaii.findEmpAllInfo(emp);
		String mapString = JSON.toJSONString(map);
//		try {
//			PrintWriter writer = response.getWriter();
//			writer.print(mapString);
//			writer.flush();
//			writer.close();
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		printJson(response, mapString);
	}
	
	
}
