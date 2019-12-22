package cn.kgc.tangcco.tcbd1017.on.seller.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.mysql.fabric.xmlrpc.base.Array;

import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.pojo.Goods;
import cn.kgc.tangcco.tcbd1017.on.seller.SelectStorePendinginfoDao;
import cn.kgc.tangcco.tcbd1017.on.seller.SelectStorePendinginfoService;

public class SelectStorePendinginfoServiceImpl implements SelectStorePendinginfoService {

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
	 * @Title: findStorePending @Description: TODO(查询所有待发货信息) @param @return
	 * 设定文件 @return Map<String,Object> 返回类型 map.put("date","对象"); 返回具体数据
	 * map.put("status", "failed"); 查询失败 maps.put("status","success"); 查询成功
	 * map.put("msg", ""); 提示词 map.put("code", 0);固定返回值 @throws
	 */
	public Map<String, Object> findStorePending(Map<String, Object> maps) {
		List<Map> list = new ArrayList<Map>();
		Map<String, Object> map = new HashMap<String, Object>();
		// 默认查询失败
		map.put("status", "failed");
		map.put("msg", "查询失败");
		// 查询待发货订单数量
		int count = dao.selectOrder(maps);
		/*
		 * map.put("daifahuo", count); list.add(map); //查询已付款订单数量 int count1 =
		 * dao.selectOrderPending(); map.put("yifukuan", count1); list.add(map);
		 */
		if (count > 0) {
			map.put("date", count);
			map.put("status", "success");
			map.put("msg", "查询成功");
			map.put("code", 0);
		} else if (count == 0) {
			map.put("date", count);
			map.put("status", "success");
			map.put("msg", "暂时没有待发货");
			map.put("code", 0);
		}

		return map;
	}

	/**
	 * 
	 * @Title: findStorePending @Description: TODO(
	 * 查询所有待发货数量) @param @return设定文件 @return Map<String,Object> 返回类型
	 * map.put("date","对象"); 返回具体数据 map.put("status", "failed"); 查询失败
	 * maps.put("status","success"); 查询成功 map.put("msg", "");提示词 map.put("code",
	 * 0);固定返回值 @throws
	 */
	public Map<String, Object> findStorePaid(Map<String, Object> maps) {
		System.out.println("？？？？已付款订单数量？？");
		List<Map> list = new ArrayList<Map>();
		Map<String, Object> map = new HashMap<String, Object>();
		// 默认查询失败
		map.put("status", "failed");
		map.put("msg", "查询失败");
		// 查询待发货订单数量
		int count = dao.selectOrderPending(maps);
		/*
		 * map.put("daifahuo", count); list.add(map); //查询已付款订单数量 int count1 =
		 * dao.selectOrderPending(); map.put("yifukuan", count1); list.add(map);
		 */
		if (count > 0) {
			map.put("date", count);
			map.put("status", "success");
			map.put("msg", "查询成功");
			map.put("code", 0);
		} else if (count == 0) {
			map.put("date", count);
			map.put("status", "success");
			map.put("msg", "暂时没有已付款订单");
			map.put("code", 0);
		}

		return map;
	}

	/**
	 * 
	 * @Title: findStorePending @Description:
	 * TODO(查询店铺信誉度，被收藏次数，待发货订单数量,已完成订单交易金额总和) @param @return 设定文件 @return
	 * Map<String,Object> 返回类型 map.put("date","对象");返回具体数据 map.put("status",
	 * "failed");查询失败 maps.put("status","success");查询成功 map.put("msg", "");提示词
	 * map.put("code", 0);固定返回值 @throws
	 */
	public Map<String, Object> findStore(Map<String, Object> maps) {
		System.out.println("？？？？已付款订单数量和待发货数量还有店铺信誉度？？");

		Map<String, Object> map = new HashMap<String, Object>();
		/* int[] count = null; */
		// 默认查询失败
		map.put("status", "failed");
		map.put("msg", "查询失败");

		// 查询店铺信誉度，被收藏次数，待发货订单数量

		int selectOrderPending = dao.selectOrderPending(maps);

		int selectOrder = dao.selectOrder(maps);

		int selectreputationDao = dao.SelectreputationDao(maps);

		int selectCompleteTransactionDao = dao.SelectCompleteTransactionDao(maps);

		double selectCompleteTransactionMoneyDao = dao.SelectCompleteTransactionMoneyDao(maps);
		/*
		 * if(selectOrderPending > 0) { count[0] = selectOrderPending; } if (selectOrder
		 * > 0) { count[1] = selectOrder; } if (selectreputationDao > 0) { count[2] =
		 * selectreputationDao; }
		 */
		Object[] count = { selectOrderPending, selectOrder, selectreputationDao, selectCompleteTransactionDao,
				selectCompleteTransactionMoneyDao };
		if (count.length == 3) {

			map.put("date", count);
			map.put("status", "success");
			map.put("msg", "查询成功");
			map.put("code", 0);
		} else {
			map.put("date", count);
			map.put("status", "success");
			map.put("msg", "店铺信誉度，被收藏次数，待发货订单数量，交易完成订单数量,交易完成订单总金额查询失败");
			map.put("code", 0);
		}

		return map;
	}

	/**
	 * 
	 * @Title: findStorePending @Description: TODO(查询店铺下已上架所有商品信息) @param @return
	 * 设定文件 @return Map<String,Object> 返回类型 map.put("date","对象");返回具体数据
	 * map.put("status", "failed");查询失败 maps.put("status","success");查询成功
	 * map.put("msg", "");提示词 map.put("code", 0);固定返回值 @throws
	 */
	@Override
	public Map<String, Object> findGoodsService(Map<String, Object> maps) {
		System.out.println("？？？？已付款订单数量？？");
		List<Goods> goodslist = new ArrayList<Goods>();
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		// 默认查询失败
		map.put("status", "failed");
		map.put("msg", "查询失败");
		// 查询待发货订单数量
		goodslist = dao.SelectGoodsDao(maps);
		/*
		 * map.put("daifahuo", count); list.add(map); //查询已付款订单数量 int count1 =
		 * dao.selectOrderPending(); map.put("yifukuan", count1); list.add(map);
		 */
		if (goodslist.size() > 0) {
			
			map.put("code", 0);
			map.put("msg","");
			map.put("count",dao.SelectGoodsNumberDao(maps));
			map.put("data", goodslist);
			
			
		} else  {
			map.put("code", 0);
			map.put("msg","");
			map.put("count",0);
			map.put("data", goodslist);
		}

		return map;
	}

}
