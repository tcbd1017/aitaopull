package cn.kgc.tangcco.tcbd1017.on.system.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import cn.kgc.tangcco.lihaozhe.commons.servlet.BaseServlet;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.system.PersonnelMattersService;

/**
 * Servlet implementation class Personnelmatters
 */
@WebServlet("/Personnelmatters.action")
public class Personnelmatters extends BaseServlet {
		
	private  static PersonnelMattersService personnelMattersService =null;
	static {
		ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("ApplicationContext_on.xml");
		try {
			personnelMattersService = (PersonnelMattersService) ioc.getBean("Personnelmatters");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static final long serialVersionUID = -8879665024566944638L;
		/**
		 * 修改或删除员工
		 * 
		 * @param request
		 * @param response
		 */
		@SuppressWarnings("unused")
		public void removeandmodifyEmp(HttpServletRequest request,HttpServletResponse response) {
			//被操作者id
			int emp_id=Integer.parseInt(request.getParameter("emp_id"));
			//员工姓名
			String emp_name=request.getParameter("emp_name");
			//员工手机号
			String emp_mobile=request.getParameter("emp_mobile");
			//员工邮箱
			String emp_mail =request.getParameter("emp_mail");
			//员工的状态
			int emp_status=Integer.parseInt(request.getParameter("emp_id"));
			//操作者的id
			int Operator=Integer.parseInt(request.getParameter("Operator"));
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("emp_id",emp_id );
			map.put("emp_name", emp_name);
			map.put("emp_mobile", emp_mobile);
			map.put("emp_mail", emp_mail);
			map.put("emp_status", emp_status);
			map.put("Operator", Operator);
			Map<String, Object>mapstates=personnelMattersService.removeandmodifyEmp(map);
			//返回前台的操作状态
			printJson(response, mapstates);
		}
		
		/**
		 * 更改员工部门
		 * @param request
		 * @param response
		 */
		@SuppressWarnings("unused")
		public void modifyEmpDept(HttpServletRequest request,HttpServletResponse response) {
			//员工被操作者id
			int emp_id=(StringUtils.isEmpty(request.getParameter("emp_id"))) ? 1
					: (Integer.parseInt(request.getParameter("emp_id")));
			//操作者id
			int Operator=Integer.parseInt(request.getParameter("Operator"));
			//部门id
			int dept_id=Integer.parseInt(request.getParameter("dept_id"));
			Map<String, Object>map = new HashMap<String, Object>();
			map.put("emp_id", emp_id);
			map.put("Operator",Operator);
			map.put("dept_id", dept_id);
			Map<String, Object> mapstates=personnelMattersService.modifyEmpDept(map);
			//返回前台的状态
			printJson(response, mapstates);
		}
}
