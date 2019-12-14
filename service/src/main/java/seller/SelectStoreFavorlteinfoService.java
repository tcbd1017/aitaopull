package cn.kgc.tangcco.tcbd1017.on.seller;

import java.util.Map;
/**
 * 
 * @author 江岛
 * @ClassName: Select_Store_Favorlte_infoService 
 * @Description: TODO(通过将action传来的store_id(店铺id)传到Dao来查询店铺被收藏的次数) 
 * @date 创建时间: 2019年12月13日 下午2:47:00
 */
public interface SelectStoreFavorlteinfoService {
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
	public Map<String, Object> findStoreFavorlte(Map<String,Object> map);

	
}
