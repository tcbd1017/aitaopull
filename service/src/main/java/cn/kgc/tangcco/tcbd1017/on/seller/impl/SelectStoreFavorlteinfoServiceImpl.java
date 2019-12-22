package cn.kgc.tangcco.tcbd1017.on.seller.impl;

import java.sql.SQLException;
import java.util.HashMap;

import java.util.Map;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.lihaozhe.commons.localdatetime.LocalDateTimeUtil;
import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.seller.SelectStoreFavorlteinfoDao;
import cn.kgc.tangcco.tcbd1017.on.seller.SelectStoreFavorlteinfoService;

public class SelectStoreFavorlteinfoServiceImpl implements SelectStoreFavorlteinfoService {
	private static SelectStoreFavorlteinfoDao dao;
	private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
			"ApplicationContext_on.xml");
	static {
		try {
			dao = (SelectStoreFavorlteinfoDao) context.getBean(SelectStoreFavorlteinfoDao.class.getSimpleName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Title: SelectStoreFavorlte @Description:
	 *         TODO(通过将action传来的store_id传到Dao来查询店铺被收藏的次数) @param Map<String,Object>
	 *         传入值 需要传入一个map 里边有store_id（店铺id） @return Map<String,Object> 返回类型
	 *         返回从dao层查询到的数量放在map中返回 map.put("date","对象");返回具体数据 map.put("status",
	 *         "failed");查询失败 maps.put("status","success");查询成功 map.put("msg",
	 *         "");提示词 map.put("code", 0);固定返回值 @throws
	 */
	public Map<String, Object> findStoreFavorlte(Map<String, Object> map) {

		Map<String, Object> maps = new HashMap<String, Object>();
		int[] number = new int[12];
		// 默认查询失败！
		maps.put("status", "failed");
		maps.put("code", 0);
		int count = -1;

		// 判断店铺id在map中是否存在
		if (map.size() > 0) {
			// 获取当前日期
			String nian = LocalDateTimeUtil.getStringByCurrentLocalDateTime("yyyy");
			// String nian = str.substring(0, 4);

			for (int i = 0; i < 12; i++) {
				String aaak = nian + "-" + (i + 1) + "-" + "01" + " 00:00:00";
				String aaas = nian + "-" + (i + 1) + "-" + "31" + " 00:00:00";

				map.put("store_favorite_create_time_k", aaak);
				map.put("store_favorite_create_time_s", aaas);
				int sss = dao.SelectStoreFavorlteNumberDao(map);
				number[i] = sss;

			}
		}

		if (number.length != 0) {
			maps.put("date", number);
			maps.put("status", "success");
			maps.put("msg", "查询成功");
		} else {
			maps.put("msg", "查询失败");
		}
		return maps;
	}

	@Override
	public Map<String, Object> findGoodsFavoriteNumbers(Map<String, Object> map) {
		System.out.println("开始查询");
		Map<String, Object> maps = new HashMap<String, Object>();
		int[] number = new int[12];
		// 默认查询失败！
		maps.put("status", "failed");
		maps.put("code", 0);
		int count = -1;

		// 判断店铺id在map中是否存在
		if (map.size() > 0) {
			// 获取当前日期
			String nian = LocalDateTimeUtil.getStringByCurrentLocalDateTime("yyyy");
			// String nian = str.substring(0, 4);

			for (int i = 0; i < 12; i++) {
				String aaak = nian + "-" + (i + 1) + "-" + "01" + " 00:00:00";
				String aaas = nian + "-" + (i + 1) + "-" + "31" + " 00:00:00";
				maps.put("goods_id", 22);
				maps.put("goods_favorite_create_time_k", aaak);
				maps.put("goods_favorite_create_time_s", aaas);
				int sss = dao.selectGoodsFavoriteNumber(maps);
				number[i] = sss;
				System.out.println(sss);
			}
		}

		if (number.length != 0) {
			maps.put("date", number);
			maps.put("status", "success");
			maps.put("msg", "查询成功");
		} else {
			maps.put("msg", "查询失败");
		}
		return maps;
	}

	/**
	 * 根据商品信息上架商品
	 */
	@Override
	public Map<String, Object> updateGoods(Map<String, Object> map) {
		Map<String, Object> maps = new HashMap<String, Object>();
		// 默认查询失败！
		maps.put("status", "failed");
		maps.put("code", 0);
		if (map != null) {
			try {
				//开启事务
				BaseDBUtils.startTransaction();
				int updateGoods = dao.updateGoods(map);
				if (updateGoods > 0) {
					maps.put("date", updateGoods);
					maps.put("status", "success");
					maps.put("msg", "上架成功");
				}else {
					maps.put("date", updateGoods);
					maps.put("status", "success");
					maps.put("msg", "上架失败");
				}
				//关闭事务
				BaseDBUtils.commitAndClose();
			} catch (SQLException e) {
				try {
					//回滚并且关闭
					BaseDBUtils.rollbackAndClose();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e.printStackTrace();
			}
			
		}
		return maps;
	}
}
