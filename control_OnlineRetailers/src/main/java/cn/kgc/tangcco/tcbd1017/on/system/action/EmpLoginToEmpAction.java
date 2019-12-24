package cn.kgc.tangcco.tcbd1017.on.system.action;

import java.util.Date;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.ObjectUtils;

import com.alibaba.fastjson.JSON;

import cn.kgc.tangcco.lihaozhe.commons.servlet.BaseServlet;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.pojo.Emp;
import cn.kgc.tangcco.tcbd1017.on.pojo.EmpLogin;
import cn.kgc.tangcco.tcbd1017.on.system.EmpLoginToEmpService;

/**
 * 
 * @author zhangmiao 
 * @version 2019年12月18日 下午4:29:18
 *
 */
@WebServlet(urlPatterns = "/EmpLoginToEmp.action")
public class EmpLoginToEmpAction extends BaseServlet {

	private static final long serialVersionUID = 5348603069165745019L;

	private static EmpLoginToEmpService eltes;
	static {
		ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(
				"ApplicationContext_on.xml");
		try {
			eltes = (EmpLoginToEmpService) classPathXmlApplicationContext
					.getBean(EmpLoginToEmpService.class.getSimpleName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void empLoginToEmpAction(HttpServletRequest request , HttpServletResponse response , String string) {
		Emp emp=null;
		EmpLogin empLogin= null;
		int emp_login_id=0;
		int emp_login_status=0;
		String emp_uuid =null;
		String emp_login_face_token=null;
		String emp_login_password=null;
		String emp_login_account=null;
		Date emp_login_create_time=null;
		Date emp_login_update_time=null;
		if (string!=null) {
			System.out.println(string);
			empLogin = JSON.parseObject(string, EmpLogin.class);
			System.out.println(empLogin);
			
		}else {
			HttpSession session = request.getSession();
			if (!ObjectUtils.isEmpty(session.getAttribute("emp_uuid"))) {
				emp_uuid = (String)session.getAttribute("emp_uuid");
			}
			if (!ObjectUtils.isEmpty(session.getAttribute("emp_login_face_token"))) {
				emp_login_face_token = (String)session.getAttribute("emp_login_face_token");
			}
			if (!ObjectUtils.isEmpty(session.getAttribute("emp_login_password"))) {
				emp_login_password = (String)session.getAttribute("emp_login_password");
			}
			if (!ObjectUtils.isEmpty(session.getAttribute("emp_login_account"))) {
				emp_login_account = (String)session.getAttribute("emp_login_account");
				
			}
		
			empLogin= new EmpLogin(emp_login_id, emp_uuid, emp_login_account, emp_login_password, emp_login_face_token, emp_login_create_time, emp_login_update_time, emp_login_status);
			
		}
		emp = eltes.empLoginToEmpService(empLogin);
		
		printJson(response, emp);
		
	}
}
