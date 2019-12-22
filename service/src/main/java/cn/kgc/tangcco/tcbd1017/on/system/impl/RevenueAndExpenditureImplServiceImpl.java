package cn.kgc.tangcco.tcbd1017.on.system.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.system.FinanceDao;
import cn.kgc.tangcco.tcbd1017.on.system.RevenueAndExpenditure;
import cn.kgc.tangcco.tcbd1017.on.system.RevenueAndExpenditureImplService;

/**
* @author 许佳瑞
* @version 创建时间：2019年12月21日 上午10:36:16
* @edition 1.0
* @Description 类描述
*/
public class RevenueAndExpenditureImplServiceImpl implements RevenueAndExpenditureImplService {
	static RevenueAndExpenditure revenueAndExpenditure;
	static {
		ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("ApplicationContext_on.xml");
		try {
			revenueAndExpenditure =(RevenueAndExpenditure) classPathXmlApplicationContext.getBean(RevenueAndExpenditure.class.getSimpleName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public Map<String, Object> modifyRevenue() {
		Map<String, Object>map=new HashMap<String, Object>();
		map.put("msg", "");
		map.put("code", 0);
		map.put("status", "failed");
		List<Object>list = new ArrayList<Object>();
		try {
			
			list=revenueAndExpenditure.selectRevenue();
			if (list.size()>0) {
				map.put("status", "success");
				map.put("data",list);	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

	@Override
	public Map<String, Object> modifyExpenditureImpl() {
		Map<String, Object>map=new HashMap<String, Object>();
		map.put("msg", "");
		map.put("code", 0);
		map.put("status", "failed");
		List<Object>list = new ArrayList<Object>();
		try {
			
			list=revenueAndExpenditure.selectExpenditure();
			if (list.size()>0) {
				map.put("status", "success");
				map.put("data",list);	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

	

}
