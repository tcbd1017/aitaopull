package cn.kgc.tangcco.tcbd1017.on.system;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.tcbd1017.on.pojo.ShopExpenditureInfo;
import cn.kgc.tangcco.tcbd1017.on.pojo.ShopIncomeInfo;
import cn.kgc.tangcco.tcbd1017.on.system.impl.FinanceDaoImpl;

/**
* @author 许佳瑞
* @version 创建时间：2019年12月16日 下午2:23:17
* @edition 1.0
* @Description 类描述
*/
public class FinanceDaoTest {
	
	@Test
	public void test01() {
		try {
			BaseDBUtils.startTransaction();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		FinanceDao financeDao= new FinanceDaoImpl();
		Map<String, Object> map =new HashMap<String, Object>();
		map.put("seller_id", 1);
		
	try {
		int i =financeDao.updateSetMealState(map);
		System.out.println(i);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
	}
	
	@Test
	public void test02() {
		FinanceDao financeDao= new FinanceDaoImpl();
		Map<String, Object> map =new HashMap<String, Object>();
		map.put("seller_id", 2);
		map.put("shop_income_money", 2.55);
		int i=0;
		try {
			i=financeDao.insertIncome(map);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(i);
	}
	
	@Test
	public void test03() throws SQLException {
		FinanceDao financeDao= new FinanceDaoImpl();
		List<ShopIncomeInfo>list =new ArrayList<ShopIncomeInfo>();
		list=financeDao.selectIncome();
		Iterator<ShopIncomeInfo> it = list.iterator();
		while (it.hasNext()) {
			ShopIncomeInfo shopIncomeInfo = (ShopIncomeInfo) it.next();
			System.out.println(shopIncomeInfo);
		}
	}
	@Test
	public void test04() throws SQLException {
		FinanceDao financeDao= new FinanceDaoImpl();
		List<ShopExpenditureInfo>list =new ArrayList<ShopExpenditureInfo>();
		list=financeDao.selectExpenditure();
		Iterator<ShopExpenditureInfo> it = list.iterator();
		while (it.hasNext()) {
			ShopExpenditureInfo shopIncomeInfo = (ShopExpenditureInfo) it.next();
			System.out.println(shopIncomeInfo);
		}
	}
}
