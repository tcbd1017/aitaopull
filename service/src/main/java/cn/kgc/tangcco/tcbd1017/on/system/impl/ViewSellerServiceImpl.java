package cn.kgc.tangcco.tcbd1017.on.system.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.lihaozhe.commons.jdbc.PageRang;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.pojo.Seller;
import cn.kgc.tangcco.tcbd1017.on.system.ViewSellerDao;
import cn.kgc.tangcco.tcbd1017.on.system.ViewSellerService;

/**
	 * @author XUE TONG
	 * @version 1.0 2019年12月16日下午2:32:54
	 * </br>
	 **/

public class ViewSellerServiceImpl implements ViewSellerService{
	private static ViewSellerDao viewSellerDao;
	private static ClassPathXmlApplicationContext path = new ClassPathXmlApplicationContext("ApplicationContext_on.xml");
	static {
		try {		
			viewSellerDao =  (ViewSellerDao) path.getBean(ViewSellerDao.class.getSimpleName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Map<String, Object> querySeller(Map<String, Object> map) {
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
			int count = viewSellerDao.selectCountSeller(map);
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
				List<Seller> seller = viewSellerDao.selectViewSeller(map);
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

}
