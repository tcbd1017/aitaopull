package cn.kgc.tangcco.tcbd1017.st.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.lihaozhe.commons.jdbc.BaseDBUtils;
import cn.kgc.tangcco.tcbd1017.st.ShopService;
import cn.kgc.tangcco.tcbd1017.st.pojo.Shop;


public class ShopServiceImpl implements ShopService{
	
	ShopDaoImpl dao=new ShopDaoImpl();
	
	@Override
	//也可按id查  动态SQL
	//查看所有商铺的详情 (下拉框用) ShopDaoImpl selectShopXiangQing
	public Map<String, Object> chakanShangPuAll(Map<String, Object> map) {
		Map<String,Object>map2=new HashMap<>();
		try {
			map2.put("status", "failed");
			map2.put("data", null);
			List<Shop> selectShopXiangQing = dao.selectShopXiangQing(map);
			if(selectShopXiangQing.size()>0  && selectShopXiangQing!=null) {
				map2.put("status", "success");
				map2.put("data", selectShopXiangQing);
			}
			BaseDBUtils.closeAll();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return map2;
	}
}
