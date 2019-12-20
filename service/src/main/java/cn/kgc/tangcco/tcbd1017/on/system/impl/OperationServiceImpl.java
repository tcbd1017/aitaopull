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
import cn.kgc.tangcco.tcbd1017.on.pojo.Goods;
import cn.kgc.tangcco.tcbd1017.on.pojo.Seller;
import cn.kgc.tangcco.tcbd1017.on.system.OperationDao;
import cn.kgc.tangcco.tcbd1017.on.system.OperationService;
/**
	 * @author XUE TONG
	 * @version 1.0 2019年12月18日上午11:06:32
	 * </br>
	 **/

public class OperationServiceImpl implements OperationService{
	private static OperationDao operationDao;
	private static ClassPathXmlApplicationContext path = new ClassPathXmlApplicationContext(
			"ApplicationContext_on.xml");
	static {
		try {
			operationDao = (OperationDao) path.getBean(OperationDao.class.getSimpleName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询所有待审核卖家
	 */
	@Override
	public Map<String, Object> selectSellers(Map<String, Object> map) throws SQLException {
		Map<String, Object> info = new LinkedHashMap<String, Object>();
		// 总页码号
		int pageCount = 1;
		info.put("code", 0);
		info.put("msg", "");
		info.put("count", 0);
		info.put("data", new ArrayList<Seller>());
		info.put("pageCount", pageCount);
		try {
			// 存储当前页码号和每页记录数
			PageRang pr = (PageRang) map.get("pr");
			// 获取总记录数
			int count = operationDao.selectCountSeller(map);
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
				List seller = (List) operationDao.selectSellers(map);
				if (seller != null) {
					info.put("data", seller);
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
	 * 分配卖家入住商城的所有信息
	 */
	@Override
	public Map<String, Object> allocationSeller(Map<String, Object> map) throws SQLException {
		Map<String, Object> info = new HashMap<String, Object>();
		info.put("status", "failed");
		try {
			BaseDBUtils.startTransaction();
			int count = operationDao.allocationSeller(map);
			if (count > 0) {
				info.put("status", "success");
				BaseDBUtils.commitAndClose();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				BaseDBUtils.rollbackAndClose();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return info;
	}

	/**
	 * 对卖家入住商城的信息的审核
	 */
	@Override
	public Map<String, Object> auditSeller(Map<String, Object> map) throws SQLException {
		Map<String, Object> info = new HashMap<String, Object>();
		info.put("status", "failed");
		try {
			BaseDBUtils.startTransaction();
			int count = operationDao.auditSeller(map);
			if (count > 0) {
				info.put("status", "success");
				BaseDBUtils.commitAndClose();
			}

		} catch (SQLException e) {
			e.printStackTrace();
			try {
				BaseDBUtils.rollbackAndClose();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return info;
	}

	/**
	 * 查询所有待上架商品
	 */
	@Override
	public Map<String, Object> selectGoods(Map<String, Object> map) throws SQLException {
		Map<String, Object> info = new LinkedHashMap<String, Object>();
		// 总页码号
		int pageCount = 1;
		info.put("code", 0);
		info.put("msg", "");
		info.put("count", 0);
		info.put("data", new ArrayList<Goods>());
		info.put("pageCount", pageCount);
		try {
			// 存储当前页码号和每页记录数
			PageRang pr = (PageRang) map.get("pr");
			// 获取总记录数
			int count = operationDao.selectCountGoods(map);
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
				List goods = (List) operationDao.selectGoods(map);
				if (goods != null) {
					info.put("data", goods);
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
	 * 分配上架商品的所有信息
	 */
	@Override
	public Map<String, Object> allocationGoods(Map<String, Object> map) throws SQLException {
		Map<String, Object> info = new HashMap<String, Object>();
		info.put("status", "failed");
		try {
			BaseDBUtils.startTransaction();
			int count = operationDao.allocationGoods(map);
			if (count > 0) {
				info.put("status", "success");
				BaseDBUtils.commitAndClose();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				BaseDBUtils.rollbackAndClose();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return info;
	}

	/**
	 * 对商家要上架的商品的审核
	 */
	@Override
	public Map<String, Object> auditGoods(Map<String, Object> map) throws SQLException {
		Map<String, Object> info = new HashMap<String, Object>();
		info.put("status", "failed");
		try {
			BaseDBUtils.startTransaction();
			int count = operationDao.auditGoods(map);
			if (count > 0) {
				info.put("status", "success");
				BaseDBUtils.commitAndClose();
			}

		} catch (SQLException e) {
			e.printStackTrace();
			try {
				BaseDBUtils.rollbackAndClose();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return info;
	}


	
	
}
