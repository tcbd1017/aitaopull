package cn.kgc.tangcco.tcbd1017.st;

import java.util.Map;

public interface GoodsService {
	
	
	/**
	 * 查询所有货物   GoodsDaoImpl    selectgoods()
	 * @param map
	 * 		key:PagePang	value:分页工具类
	 * 		key:account		value:登录账号
	 * 		key:shop_id		value:店铺id
	 * 		动态查询：
	 * 			key:goods_id		
	 * 			key:shop_name		
	 * 			key:goods_count		
	 * 			key:goods_w_s_uuid	
	 * 			key:type_name		
	 * 			key:brand_name		
	 * 			key:model_name		
	 * 			key:model_price		
	 * 			key:model_size 
	 * @return
	 * 		Map("data",Map("allGoods",list))
	 * 		
	 * 		
	 */
	public Map<String,Object> SelectAllgoods(Map<String,Object>map);
	
	
	
	
}
