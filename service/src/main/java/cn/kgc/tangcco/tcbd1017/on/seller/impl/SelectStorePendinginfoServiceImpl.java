package cn.kgc.tangcco.tcbd1017.on.seller.impl;

import java.util.HashMap;
import java.util.Map;

import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.seller.SelectStorePendinginfoDao;


public class SelectStorePendinginfoServiceImpl implements SelectStorePendinginfoDao{

	private static SelectStorePendinginfoDao dao;
	private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
			"ApplicationContext_on.xml");
	static {
		try {
			dao = (SelectStorePendinginfoDao) context.getBean(SelectStorePendinginfoDao.class.getSimpleName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Title: findStorePending @Description: TODO(首先调用方法 将数据库内所有的已付款变成待发货
	 *         然后调用另一个办法查询所有待发货信息) @param @return 设定文件 @return Map<String,Object>
	 *         返回类型 map.put("date","对象");返回具体数据 map.put("status", "failed");查询失败
	 *         maps.put("status","success");查询成功 map.put("msg", "");提示词
	 *         map.put("code", 0);固定返回值 @throws
	 */
	public Map<String, Object> findStorePending() {
		System.out.println("？？？？？？？");
		Map<String, Object> map = new HashMap<String, Object>();
		// 默认查询失败
		map.put("status", "failed");
		map.put("msg", "查询失败");

		int count = dao.selectOrder();
		if (count > 0) {
			map.put("date", count);
			map.put("status", "success");
			map.put("msg", "查询成功");
			map.put("code", 0);
		}

		return map;
	}

	@Override
	public int selectOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int selectOrderPending() {
		// TODO Auto-generated method stub
		return 0;
	}

}
