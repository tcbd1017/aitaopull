package cn.kgc.tangcco.tcbd1017.on.system.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;

import cn.kgc.tangcco.lihaozhe.commons.date.BaseDateUitls;
import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.system.ModifyEmpAllInfo;
import cn.kgc.tangcco.tcbd1017.on.system.UpdateEmpAllInfo;

/**
 * 
 * @author zhangmiao
 * @version	2019年12月18日	上午11:31:55
 *
 */
public class ModifyEmpAllInfoImpl implements ModifyEmpAllInfo{
	private static UpdateEmpAllInfo ueai;
	static {
		ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("ApplicationContext_on.xml");
		try {
			ueai=(UpdateEmpAllInfo)classPathXmlApplicationContext.getBean(UpdateEmpAllInfo.class.getSimpleName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public Map<String, Object> modifyEmpAllInfo(Map<String, Object> map) {
		
		Map<String, Object> modifyMap = new HashMap<String, Object>();
		modifyMap.put("status", "failed");
	
		if (!ObjectUtils.isEmpty(map.get("emp"))) {
			try {
				BaseDBUtils.startTransaction();
				 int modifyEmpAllInfo = ueai.updateEmpAllInfo(map);
				if (modifyEmpAllInfo>1) {
					modifyMap.put("status", "success");
					BaseDBUtils.commitAndClose();
				}else {
					BaseDBUtils.rollbackAndClose();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return modifyMap;
	}

}
