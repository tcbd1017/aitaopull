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
		  mapss.put("count", "");
		  mapss.put("data", "");
		  mapss.put("code", 0);   
		  mapss.put("msg", "");
		  
		  try {
		   Map<String, Object> selectgoods = goodsDao.selectgoods(map);
		   List<Goods> list = (List<Goods>) selectgoods.get("allGoods");
		   if(list.size()>0) {
		    mapss.put("data", list);
		    mapss.put("count", list.size());
		   }
		  } catch (SQLException e) {
		   e.printStackTrace();
		  }
		  return mapss;
		 }
}
