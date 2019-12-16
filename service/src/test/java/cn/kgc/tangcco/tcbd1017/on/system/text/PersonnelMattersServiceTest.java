package cn.kgc.tangcco.tcbd1017.on.system.text;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import cn.kgc.tangcco.tcbd1017.on.system.PersonnelMattersService;
import cn.kgc.tangcco.tcbd1017.on.system.impl.PersonnelMattersServiceImpl;

/**
* @author 许佳瑞
* @version 创建时间：2019年12月15日 下午5:30:48
* @edition 1.0
* @Description 类描述
*/
public class PersonnelMattersServiceTest {
	@Test
	public void name() {
		PersonnelMattersService personnelMattersService =new PersonnelMattersServiceImpl();
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("Operator",1);
		map.put("emp_id",3);
		map.put("dept_id",2);
		Map<String, Object>map2=new HashMap<String, Object>();
		map2=personnelMattersService.modifyEmpDept(map);
		System.out.println(map2.get("status"));
	}
	
}
