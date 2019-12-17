package cn.kgc.tangcco.tcbd1017.on.buyer;
/**
 * 
 * 	@author 和朋朋
 * 	@version 1.0<br>
 * 	创建时间：  2019年12月9日 上午11:14:11 <br>
 * 	类描述：逻辑层
 *	
 */

import java.sql.SQLException;
import java.util.Map;

import cn.kgc.tangcco.tcbd1017.on.pojo.StoreFavorite;

public interface StoreFavoriteService {
	/**
	 * 查看所有店铺
	 * @return
	 * @throws SQLException 
	 */
	Map<String, Object>queryStoreFavorite(Map<String, Object>map) ;
	/**
	 * 把店铺收藏起来
	 * @param map
	 * @return
	 */
	Map<String, Object>addStoreFavorite(Map<String, Object>map);
	/**
	 * 删除收藏店铺
	 * @param store_favorite_id
	 * @return
	 */
	Map<String, Object>removeStoreFavorite(Map<String, Object>map);
	
}
