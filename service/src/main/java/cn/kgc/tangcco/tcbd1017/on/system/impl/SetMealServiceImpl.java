package cn.kgc.tangcco.tcbd1017.on.system.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.lihaozhe.commons.jdbc.PageRang;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.pojo.SetMeal;
import cn.kgc.tangcco.tcbd1017.on.system.SetMealDao;
import cn.kgc.tangcco.tcbd1017.on.system.SetMealService;

/**
 * 
 * @author 薛彤
 * @version 1.0 2019年12月14日 上午10:37:05
 *
 */

public class SetMealServiceImpl implements SetMealService {

	private static SetMealDao setMealDao;
	private static ClassPathXmlApplicationContext path = new ClassPathXmlApplicationContext(
			"ApplicationContext_on.xml");
	static {
		try {
			setMealDao = (SetMealDao) path.getBean(SetMealDao.class.getSimpleName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询所有
	 */
	@Override
	public Map<String, Object> querySetMeal(Map<String, Object> map) {
		Map<String, Object> info = new LinkedHashMap<String, Object>();
		// 总页码号
		int pageCount = 1;
		info.put("code", 0);
		info.put("msg", "");
		info.put("count", 0);
		info.put("data", new ArrayList<SetMeal>());
		info.put("pageCount", pageCount);
		try {
			// 存储当前页码号和每页记录数
			PageRang pr = (PageRang) map.get("pr");
			// 获取总记录数
			int count = setMealDao.selectCountSetMeal(map);
			if (count > 0) {
				// 每页纪录数
				int pageSize = ((PageRang) map.get("pr")).getPageSize();
				// 总页面号
				pageCount = (int) ((count % pageSize != 0) ? Math.ceil((count + 0.0) / pageSize) : (count / pageSize));
				// 存储总页面数
				info.put("pageCount", pageCount);
				info.put("count", count);
				// 保护性代码保护传小于1的页面和大于最大页面的值
				int pageNumber = pr.getPageNumber();
				info.put("pr", pr);
				List<SetMeal> setMeal = setMealDao.selectAllSetMeal(map);
				if (setMeal != null) {
					info.put("data", setMeal);
				}

			}
			BaseDBUtils.closeAll();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				BaseDBUtils.closeAll();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		}
		return info;
	}
	/**
	 * 查询指定
	 */
	@Override
	public Map<String, Object> findSetMeal(int set_meal_id) {
		Map<String, Object> info = new HashMap<String, Object>();
		info.put("status", "failed");
		try {
			SetMeal setMeal = setMealDao.selectSetMeal(set_meal_id);
			if (setMeal != null) {
				info.put("status", "success");
				info.put("SetMeal", setMeal);
				BaseDBUtils.closeAll();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				BaseDBUtils.closeAll();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return info;
	}
	/**
	 * 添加
	 */
	@Override
	public Map<String, Object> addSetMeal(Map<String, Object> map) {
		Map<String, Object> info = new HashMap<String, Object>();
		info.put("status", "failed");
		try {
			BaseDBUtils.startTransaction();
			int count = setMealDao.insertSetMeal(map);
			if (count > 0) {
				info.put("status", "success");
				BaseDBUtils.commitAndClose();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BaseDBUtils.rollbackAndClose();
		}
		return info;
	}

	/**
	 * 修改
	 */
	@Override
	public Map<String, Object> updateSetMeal(SetMeal setMeal) {
		Map<String, Object> info = new HashMap<String, Object>();
		info.put("status", "failed");
		try {
			BaseDBUtils.startTransaction();
			int count = setMealDao.updateSetMeal(setMeal);
			if (count > 0) {
				info.put("status", "success");
				BaseDBUtils.commitAndClose();
			}

		} catch (SQLException e) {
			e.printStackTrace();
			BaseDBUtils.rollbackAndClose();
		}
		return info;
	}

	/**
	 * 删除
	 */
	@Override
	public Map<String, Object> removeSetMeal(int set_meal_id) {
		Map<String, Object> info = new HashMap<String, Object>();
		info.put("status", "failed");
		try {
			BaseDBUtils.startTransaction();
			int count = setMealDao.deleteSetMeal(set_meal_id);
			if (count > 0) {
				BaseDBUtils.commitAndClose();
				info.put("status", "success");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BaseDBUtils.rollbackAndClose();
		}
		return info;
	}


}
