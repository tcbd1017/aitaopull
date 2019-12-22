package cn.kgc.tangcco.tcbd1017.on.system.action;
/**
 * 
 * @author zhangmiao
 * @version	2019年12月18日	上午11:59:18
 *
 */

import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

import cn.kgc.tangcco.lihaozhe.commons.servlet.BaseServlet;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.pojo.EmpLogin;
import cn.kgc.tangcco.tcbd1017.on.system.EmpLoginToEmpService;
import cn.kgc.tangcco.tcbd1017.on.system.ModifyEmpAllInfo;
@WebServlet(urlPatterns = "/ModifyEmpAllInfo.action")
public class ModifyEmpAllInfoAction extends BaseServlet {

	private static final long serialVersionUID = 5123197004747637589L;
	private static ModifyEmpAllInfo meai;
	private static EmpLoginToEmpService etes;
	static {
		ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(
				"ApplicationContext_on.xml");
		try {
			meai = (ModifyEmpAllInfo) classPathXmlApplicationContext.getBean(ModifyEmpAllInfo.class.getSimpleName());
			etes = (EmpLoginToEmpService) classPathXmlApplicationContext
					.getBean(EmpLoginToEmpService.class.getSimpleName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void modifyEmpAllInfoAction(HttpServletRequest request,HttpServletResponse response,String string) {
		EmpLogin empLogin = JSON.parseObject(string, EmpLogin.class);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("emp", etes.empLoginToEmpService(empLogin));
		Map<String, Object> modifyEmpAllInfo = meai.modifyEmpAllInfo(map);
		printJson(response, modifyEmpAllInfo);
		
	}
}
