package cn.kgc.tangcco.tbd1017.on.system;
/**
 * 
 * @author zhangmiao
 * @version	2019年12月15日	下午3:38:16
 *
 */

import java.util.Map;

import org.junit.Test;

import cn.kgc.tangcco.tcbd1017.on.pojo.Emp;
import cn.kgc.tangcco.tcbd1017.on.system.impl.FindEmpAllInfoImpl;

public class FindEmpAllInfoTest {
	@Test
	public void findEmpAllInfoTest() {
		FindEmpAllInfoImpl feaii = new FindEmpAllInfoImpl();
		
		Map<String, Object> map = feaii.findEmpAllInfo(new Emp(1, null, null, null, null, null, null, 2));
		if (map.containsKey("data")) {
			System.out.println(map.get("data"));
		}
	}
	

}
