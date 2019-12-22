package cn.kgc.tangcco.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import cn.kgc.tangcco.dao.impl.BrandDaoImpl;
import cn.kgc.tangcco.dao.impl.ChuKuDaoImpl;
import cn.kgc.tangcco.dao.impl.ModelDaoImpl;
import cn.kgc.tangcco.dao.impl.warehouse_shopDaoImpl;
import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.pojo.Brand;
import cn.kgc.tangcco.pojo.Model;
import cn.kgc.tangcco.service.ShangJiChuKuService;

public class ShangJiChuKuServiceImpl implements ShangJiChuKuService{

	//	map.put("type", typeid);
	@Override
	public Map<String, Object> ChaXunPinPai(Map<String, Object> map) {
		Map<String,Object> map1 = new HashMap<String, Object>();
		map.put("status", "failed");
		if (map!=null) {
			BrandDaoImpl brandDaoImpl = new BrandDaoImpl();
			List<Brand> list = brandDaoImpl.selectLeiXing(map);
			map1.put("status", "success");
			map1.put("data", list);
			return map1;
		}
		return map1;
	}

	
	//map.put("model_brand_id", brandid);
	@Override
	public Map<String, Object> ChaXunXingHao(Map<String, Object> map) {
		Map<String,Object> map1 = new HashMap<String, Object>();
		map1.put("status", "failed");
		if (map!=null) {
			ModelDaoImpl modelDaoImpl = new ModelDaoImpl();
			List<Model> list = modelDaoImpl.selectXingHao(map);
			map1.put("status", "success");
			map1.put("data",list );
			return map1;
		}
		return map1;
	}

	@Override
	public Map<String, Object> ShopGaouMaiDeCangKu(Map<String, Object> map) {
		Map<String,Object> map1 = new HashMap<String, Object>();
		List  list1  = new ArrayList<>();
		map1.put("code", 0);
		map1.put("counnt", 0);
		map1.put("msg", "");
		map1.put("data", "");
		map1.put("status", "faild");
		warehouse_shopDaoImpl impl = new warehouse_shopDaoImpl();
		Map<String, Object> map2 = impl.selectShengYuRongLiang(map);
		if (map2!=null) {
			List list = (List) map2.get("chakanxiangqing");
			ListIterator it = list.listIterator();
			while (it.hasNext()) {
				Object object2 = (Object) it.next();
				System.out.println(object2.toString());
				list1.add(object2);
			}			
			map1.put("status", "success");
			map1.put("data", list1);
			map1.put("count", list1.size());
			System.out.println(list1.size());
			return map1;
		}
		return map1;
	}

	
	
	@Override
	public Map<String, Object> KaiShiChuKu(Map<String, Object> map) {
		Map<String, Object> map1 = new HashMap<String, Object>();
		map1.put("status", "failed");
		if (map!=null) {
			int i = 0;
			try {
				BaseDBUtils.startTransaction();
				ChuKuDaoImpl chuKuDaoImpl = new ChuKuDaoImpl();
				i = chuKuDaoImpl.insertChuKu(map);
				if (i>0) {
					map1.put("status", "success");
					map1.put("data", i);
					BaseDBUtils.commitAndClose();
				}
				return map1;
			} catch (SQLException e) {
				e.printStackTrace();
				try {
					BaseDBUtils.closeAll();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
		return map1;
	}
}
