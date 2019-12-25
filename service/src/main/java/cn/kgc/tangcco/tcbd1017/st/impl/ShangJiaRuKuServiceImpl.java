package cn.kgc.tangcco.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.dao.impl.GoodsDaoImpl;
import cn.kgc.tangcco.dao.impl.JianCeDaoImpl;
import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.pojo.Goods;
import cn.kgc.tangcco.pojo.Jiance;
import cn.kgc.tangcco.service.ShangJiaRuKuService;

public class ShangJiaRuKuServiceImpl implements ShangJiaRuKuService{

	@Override
	public Map<String, Object> ChaKanGoods(Map<String, Object> map) {
		Map<String, Object> map2 = null;
		try {
			map2 = new HashMap<String, Object>();
			map2.put("status", "faild");
			map2.put("code", 0);
			map2.put("count", 0);
			map2.put("msg", "");
			map2.put("data", "");
			
			GoodsDaoImpl goodsDaoImpl = new GoodsDaoImpl();
			Map<String, Object> selectgoods = goodsDaoImpl.selectgoods(map);
			
			List<Goods> list = (List<Goods>) selectgoods.get("allGoods");
			map2.put("status", "success");
			map2.put("data", list);
			map2.put("count", list.size());
			return map2;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map2;

	}

	@Override
	public Map<String, Object> KaiShiRuKu(Map<String, Object> map) {
		Map<String,Object> map2 = new HashMap<String, Object>();
		map2.put("status", "failed");
		if (map!=null) {
			int i = 0;
			try {
				BaseDBUtils.startTransaction();
				JianCeDaoImpl goodsDaoImpl = new JianCeDaoImpl();
				i = goodsDaoImpl.insertJianCeBiao(map);
				if (i>0) {
					map2.put("status", "success");
					map2.put("data", i);
					BaseDBUtils.commitAndClose();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				try {
					BaseDBUtils.closeAll();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		return map2;
	}
	
	@Override
	 public Map<String, Object> ChaXunZhuCe(Map<String, Object> map) {
	  Map<String,Object> map2 = new HashMap<String, Object>();
	  map2.put("status", "failed");
	  if (map!=null) {
	   try {
	    BaseDBUtils.startTransaction();
	    JianCeDaoImpl JianCeDaoImpl = new JianCeDaoImpl();
	    List<Jiance> selectZhuCe = JianCeDaoImpl.selectZhuCe(map);
	    if (selectZhuCe!=null) {
	     map2.put("status", "success");
	     map2.put("data", selectZhuCe);
	     BaseDBUtils.commitAndClose();
	    }
	   } catch (SQLException e) {
	    e.printStackTrace();
	    try {
	     BaseDBUtils.closeAll();
	    } catch (SQLException e1) {
	     e1.printStackTrace();
	    }
	   }
	  }
	  return map2;
	 }
}
