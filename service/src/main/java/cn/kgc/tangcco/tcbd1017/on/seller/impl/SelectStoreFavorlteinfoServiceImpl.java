package cn.kgc.tangcco.tcbd1017.on.seller.impl;

import java.util.HashMap;
import java.util.Map;

import cn.kgc.tangcco.lihaozhe.commons.spring.ClassPathXmlApplicationContext;
import cn.kgc.tangcco.tcbd1017.on.seller.SelectStoreFavorlteinfoDao;
import cn.kgc.tangcco.tcbd1017.on.seller.SelectStoreFavorlteinfoService;

public class SelectStoreFavorlteinfoServiceImpl implements SelectStoreFavorlteinfoService {
	private static SelectStoreFavorlteinfoDao dao;
	private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
			"ApplicationContext_on.xml");
	static {
		try {
			dao = (SelectStoreFavorlteinfoDao) context
					.getBean(SelectStoreFavorlteinfoDao.class.getSimpleName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @Title: SelectStoreFavorlte 
	 * @Description: TODO(通过将action传来的store_id传到Dao来查询店铺被收藏的次数) 
	 * @param  Map<String,Object> 传入值   需要传入一个map  里边有store_id（店铺id）
	 * @return Map<String,Object> 返回类型   返回从dao层查询到的数量放在map中返回
	 *  map.put("date","对象");返回具体数据
	 *  map.put("status", "failed");查询失败
	 *  maps.put("status","success");查询成功
	 *  map.put("msg", "");提示词
	 *  map.put("code", 0);固定返回值
	 * @throws
	 */
	public Map<String, Object> findStoreFavorlte(Map<String, Object> map) {
		Map<String, Object> maps = new HashMap<String, Object>();
		
		//默认查询失败！
		maps.put("status", "failed");
		maps.put("code", 0);
		int count = -1;
		
		//判断店铺id在map中是否存在
		if(map.size() > 0) {
			 count = dao.SelectStoreFavorlteNumberDao(map);
			 if( count > 0 ) {
				 maps.put("date", count);
				 maps.put("status","success");
				 maps.put("msg", "查询成功");
			 }else {
				 maps.put("msg", "查询失败");
			 }
		}else {
			maps.put("msg", "查询失败");
		}
		return maps;
	}

}
