package cn.kgc.tangcco.tcbd1017.on.system;

import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.pojo.Emp;
import cn.kgc.tangcco.tcbd1017.on.pojo.EmpLogin;

/**
 * 
 * @author zhangmiao
 * @version 2019年12月18日 上午10:58:53
 *
 */
public interface EmpLoginToEmpService {
	
	
	/**
	 * emp的登录对象转化为emp对象
	 * @param empLigin emp的登录对象
	 * @return emp对象
	 */
	Emp empLoginToEmpService(EmpLogin empLigin);
}
