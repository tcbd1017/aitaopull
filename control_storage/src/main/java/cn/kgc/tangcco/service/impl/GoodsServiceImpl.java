package cn.kgc.tangcco.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.dao.GoodsDao;
import cn.kgc.tangcco.dao.impl.GoodsDaoImpl;
import cn.kgc.tangcco.pojo.Goods;
import cn.kgc.tangcco.service.GoodsService;

public class GoodsServiceImpl implements GoodsService{
	GoodsDao goodsDao = new GoodsDaoImpl();
	@Override
	public Map<String, Object> SelectAllgoods(Map<String, Object> map) {
		Map<String, Object> mapss = new HashMap<String, Object>();
		mapss.put("status", "failed");
		mapss.put("code", 0);   
		mapss.put("msg", "");
		
		try {
			Map<String, Object> selectgoods = goodsDao.selectgoods(map);
			if(selectgoods != null) {
				mapss.put("status", "success");
				mapss.put("data", selectgoods);
				List<Goods> list=(List)selectgoods.get("allGoods");
				mapss.put("count", list.size());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mapss;
	}
}
