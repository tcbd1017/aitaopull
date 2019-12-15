package cn.kgc.tangcco.tcbd1017.on.system.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;

import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.pojo.Emp;
import cn.kgc.tangcco.tcbd1017.on.system.FindEmpAllInfo;
import cn.kgc.tangcco.tcbd1017.on.system.SelectEmpAllInfo;

/**
 * 
 * @author zhangmiao
 * @version 2019年12月15日 下午3:03:42
 *
 */
public class FindEmpAllInfoImpl implements FindEmpAllInfo {
	static SelectEmpAllInfo seai;
	static {
		ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(
				"ApplicationContext_on.xml");
		try {
			seai = (SelectEmpAllInfo) classPathXmlApplicationContext.getBean(SelectEmpAllInfo.class.getSimpleName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Map<String, Object> findEmpAllInfo(Emp emp) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("msg", "");
		map.put("code", 0);
		map.put("status", "failed");
		
		if (ObjectUtils.isEmpty(emp)) {
			return map;
		}else {
			try {
				if (ObjectUtils.isEmpty(seai.selectEmpAllInfo(emp))) {
					map.put("data", null);
					return map;
				}else {
					map.put("status", "success");
					map.put("data", seai.selectEmpAllInfo(emp));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return map;
	}

}
