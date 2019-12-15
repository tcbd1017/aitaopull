package cn.kgc.tangcco.tcbd1017.on.buyer.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.buyer.CashVoucherDao;
import cn.kgc.tangcco.tcbd1017.on.buyer.CashVoucherService;

/**
 * @author 赵瑞涛
 * @version v1.0<br>
 * 	创建时间:	2019年12月9日	上午11:15:12<br>
 * 	类描述:
 */
public class CashVoucherServiceImpl implements CashVoucherService{
	
	public static CashVoucherDao cashVoucherDao;
	public static ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("ApplicationContext_on.xml");
	static {
		try {
			cashVoucherDao = (CashVoucherDao) classPathXmlApplicationContext.getBean(CashVoucherDao.class.getSimpleName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	/**
	 *  通过id和status查询优惠券信息
	 */
	public Map<String, Object> queryByIdAndStatus(Map<String, Object> map) {
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("status", "feild");
		try {
			List<Map<String, Object>> list = cashVoucherDao.selectByBuyerId(map);
			BaseDBUtils.closeAll();
			if (list.size()>0) {
				map1.put("info", list);
				map1.put("status","success");
				return map1;
			}else {
				return map1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map1;
	}
	
	@Override
	/**
	 * 	通过id和status查询符合调件的优惠券数量
	 */
	public Map<String, Object> queryNumberByIdAndStatus(Map<String, Object> map) {
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("status","feild");
		try {
			int a = cashVoucherDao.selectByNumber(map);
			BaseDBUtils.closeAll();
			if (a>0) {
				map1.put("info",a);
				map1.put("status","success");
				return map1;
			}else {
				return map1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return map1;
	}
	
	@Override
	/**
	 * 	删除一个优惠券
	 */
	public Map<String, Object> removeByUuid(Map<String, Object> map) {
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("status","feild");
		try {
			BaseDBUtils.startTransaction();
			int a = cashVoucherDao.deleteByUuid(map);
			BaseDBUtils.commitAndClose();
			if (a>0) {
				map1.put("status","success");
				return map1;
			}else {
				return map1;
			}
		} catch (SQLException e) {
			try {
				BaseDBUtils.rollbackAndClose();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return map1;
	}

	@Override
	/**
	 * 	查询并分页
	 */
	public Map<String, Object> queryByIdAndStatusAndPagereng(Map<String, Object> map) {
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("status","feild");
		try {
			
			List<Map<String, Object>> list = cashVoucherDao.selectByBuerIdAndStatus(map);
			BaseDBUtils.closeAll();
			if (list.size()>0) {
				map1.put("info", list);
				map1.put("status","success");
				return map1;
			}else {
				return map1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map1;
	}

	@Override
	/**
	 * 	通过店名查询
	 */
	public Map<String, Object> queryByIdAndStatusAndStoreName(Map<String, Object> map) {
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("status", "feild");
		List<Map<String, Object>> list;
		try {
			list = cashVoucherDao.selectByIdAndStatusAndStoreName(map);
			BaseDBUtils.closeAll();
			if (list.size()>0) {
				map2.put("info",list);
				map2.put("status","success");
				return map2;
			}else {
				return map2;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map2;
	}
	
	
}
