package cn.kgc.tangcco.tcbd1017.on.system.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.pojo.SellerBuySetMealInfo;
import cn.kgc.tangcco.tcbd1017.on.system.FinanceDao;
import cn.kgc.tangcco.tcbd1017.on.system.FinanceService;

/**
* @author 许佳瑞
* @version 创建时间：2019年12月16日 下午3:27:00
* @edition 1.0
* @Description 类描述
*/
public class FinanceServiceImpl implements FinanceService {
	static FinanceDao financeDao;
	static {
		ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(
				"ApplicationContext_on.xml");
		try {
			financeDao =(FinanceDao) classPathXmlApplicationContext.getBean(FinanceDao.class.getSimpleName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	/**
	 *  审核商家购买套餐
	 */
	public Map<String, Object> modifySetMealState(Map<String, Object> map)  {
		Map<String, Object>mapstatas=new HashMap<String, Object>();
		mapstatas.put("status", "failed");
		int i=0;
		try {
			BaseDBUtils.startTransaction();
			i=financeDao.updateSetMealState(map);
			if (i>0) {
				int j=0;
				j=financeDao.insertIncome(map);
			if (j>0) {
				mapstatas.put("status", "success");
			  }
			}
			BaseDBUtils.commitAndClose();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				BaseDBUtils.rollbackAndClose();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		return mapstatas;
	}
	@Override
	public Map<String, Object> queryIncome() {
		Map<String, Object>map=new HashMap<String, Object>();
		map.put("msg", "");
		map.put("code", 0);
		map.put("status", "failed");
		try {
			if (ObjectUtils.isEmpty(financeDao.selectIncome())) {
				map.put("data", null);
				return map;
			}else {
				map.put("status", "success");
				map.put("data",financeDao.selectIncome());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

	@Override
	public Map<String, Object> queryExpenditure() {
		Map<String, Object>map=new HashMap<String, Object>();
		map.put("msg", "");
		map.put("code", 0);
		map.put("status", "failed");
		try {
			if (ObjectUtils.isEmpty(financeDao.selectExpenditure())) {
				map.put("data", null);
				return map;
			}else {
				map.put("status", "success");
				map.put("data",financeDao.selectExpenditure());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	@Override
	public Map<String, Object> queryNumberAndDetailed() {
		Map<String, Object>map=new HashMap<String, Object>();
		map.put("msg", "");
		map.put("code", 0);
		map.put("status", "failed");
		List<SellerBuySetMealInfo> list= new ArrayList<SellerBuySetMealInfo>();
		Map<String, Object> map2=new HashMap<String, Object>();
		try {
			list=financeDao.selectSellerBuySetMealInfo();
			map2 =financeDao.selectSellerBuySetMealInfocount();
			int number=(int) map2.get("number");
			if (list.size()>0&&number>0) {
				map.put("status", "success");
				map.put("data",list);
				map.put("number", number);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
