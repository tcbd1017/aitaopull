package cn.kgc.tangcco.tcbd1017.on.system.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.lihaozhe.commons.jdbc.PageRang;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.pojo.Emp;
import cn.kgc.tangcco.tcbd1017.on.pojo.SellerBuySetMealInfo;
import cn.kgc.tangcco.tcbd1017.on.system.FinanceDao;
import cn.kgc.tangcco.tcbd1017.on.system.FinanceService;
import cn.kgc.tangcco.tcbd1017.on.system.SelectEmpPower;

/**
* @author 许佳瑞
* @version 创建时间：2019年12月16日 下午3:27:00
* @edition 1.0
* @Description 类描述
*/
public class FinanceServiceImpl implements FinanceService {
	static FinanceDao financeDao;
	static {
		ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("ApplicationContext_on.xml");
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
			Map<String, Object> map1 =new HashMap<String, Object>();
			map1=financeDao.selectmany(map);
			if (map1!=null&&map.size()>0) {
				map.put("shop_income_money", map1.get("shop_income_money"));
			}
			int j=financeDao.insertIncome(map);
			if (j>0) {
				mapstatas.put("status", "success");
				BaseDBUtils.commitAndClose();
			}
			}
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
	public Map<String, Object> queryIncome(Map<String, Object>map1) {
		Map<String, Object>map=new HashMap<String, Object>();
		map.put("msg", "");
		map.put("code", 0);
		map.put("status", "failed");
		map.put("pageCount", 0);
		try {
			int pageCount = 1;
			// 总记录数
			int count = financeDao.selectIncomecount(map1);
			if (count > 0) {
				// 存储当前页码号和每页记录数
				PageRang pr = (PageRang) map1.get("pr");
				// 每页记录数
				int pageSize = pr.getPageSize();
				// 总页面号
				pageCount = (int) ((count / pageSize != 0) ? Math.ceil((count + 0.0) / pageSize) : (count / pageSize));
				// 存储总页面数
				map.put("pageCount", pageCount);
				map.put("count", count);
				// 保护性代码保护传小于1的页面和大于最大页面的值
				int pageNumber = pr.getPageNumber();
				map.put("pr", pr);}
			if (ObjectUtils.isEmpty(financeDao.selectIncome(map1))) {
				map.put("data", null);
				return map;
			}else {
				map.put("status", "success");
				map.put("data",financeDao.selectIncome(map1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}

	@Override
	public Map<String, Object> queryExpenditure(Map<String, Object>map1) {
		Map<String, Object>map=new HashMap<String, Object>();
		map.put("msg", "");
		map.put("code", 0);
		map.put("status", "failed");
		map.put("pageCount", 0);
		try {
			int pageCount = 1;
			// 总记录数
			int count = financeDao.selectExpenditurecount(map1);
			if (count > 0) {
				// 存储当前页码号和每页记录数
				PageRang pr = (PageRang) map1.get("pr");
				// 每页记录数
				int pageSize = pr.getPageSize();
				// 总页面号
				pageCount = (int) ((count / pageSize != 0) ? Math.ceil((count + 0.0) / pageSize) : (count / pageSize));
				// 存储总页面数
				map.put("pageCount", pageCount);
				map.put("count", count);
				// 保护性代码保护传小于1的页面和大于最大页面的值
				int pageNumber = pr.getPageNumber();
				map.put("pr", pr);}
			if (ObjectUtils.isEmpty(financeDao.selectExpenditure(map1))) {
				map.put("data", null);
				return map;
			}else {
				map.put("status", "success");
				map.put("data",financeDao.selectExpenditure(map1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 查看未通过审核的总数并根据权限显示
	 * map  前端后端传的值 有 审核人的id 和审核的的权限
	 * map1 后端往前端穿的值   里边有 总数 和记录还有状态
	 * map2 获得记录的个数
	 * map3 或的员工的权限
	 */
	@Override
	public Map<String, Object> queryNumberAndDetailed(Map<String, Object>map) {
		Map<String, Object>map1=new HashMap<String, Object>();
		map1.put("msg", "");
		map1.put("code", 0);
		map1.put("status", "failed");
		map1.put("count", 0);
		List<SellerBuySetMealInfo> list= new ArrayList<SellerBuySetMealInfo>();
		Map<String, Object> map2=new HashMap<String, Object>();
		SelectEmpPower selectEmpPower= new SelectEmpPowerImpl();
		Emp emp =new Emp();
		int emp_id=(int)map.get("person_in_charge_id");
		emp.setEmp_id(emp_id);
		Map<String, Object> map3= new HashMap<String, Object>();
		try {
			map3=selectEmpPower.selectEmpPower(emp);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		map.put("empPower", map3.get("empPower"));
		map.put("deptPower", map3.get("deptPower"));
		System.out.println(map.get("person_in_charge_id"));
		try {
			list=financeDao.selectSellerBuySetMealInfo(map);
			map2 =financeDao.selectSellerBuySetMealInfocount(map);
			int count=(int) map2.get("number");
			int pageCount = 1;
			System.out.println(count);
			if (count > 0) {
				// 存储当前页码号和每页记录数
				PageRang pr = (PageRang) map.get("pr");
				// 每页记录数
				int pageSize = pr.getPageSize();
				// 总页面号
				pageCount = (int) ((count / pageSize != 0) ? Math.ceil((count + 0.0) / pageSize) : (count / pageSize));
				// 存储总页面数
				map1.put("pageCount", pageCount);
				map1.put("count", count);
				// 保护性代码保护传小于1的页面和大于最大页面的值
			int pageNumber = pr.getPageNumber();
			map1.put("pr", pr);}
			if (list.size()>0) {
				map1.put("status", "success");
				map1.put("data",list);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map1;
	}
	/**
	 * 分配员工工作
	 * 
	 */
	@Override
	public Map<String, Object> modifyPersonInCharge(Map<String, Object> map) {
		Map<String, Object>mapstatas=new HashMap<String, Object>();
		mapstatas.put("status", "failed");
		int i=0;
		try {
			BaseDBUtils.startTransaction();
			i=financeDao.updatePersonInCharge(map);
			if (i>0) {
				mapstatas.put("status", "success");
			}
			BaseDBUtils.commitAndClose();
		} catch (SQLException e) {
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
}
