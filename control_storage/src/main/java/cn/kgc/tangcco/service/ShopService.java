package cn.kgc.tangcco.service;

import java.util.Map;

public interface ShopService {
	
	/**
	 * 可按商铺id查
	   *    查看所有商铺的详情   (下拉框用)     ShopDaoImpl        selectShopXiangQing
	 * @return
	 */
	public Map<String,Object> chakanShangPuAll(Map<String,Object>map);
	
}
