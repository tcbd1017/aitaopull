package cn.kgc.tangcco.tcbd1017.on.seller;
/**
*@author   席首华
*@version  1.0   </br>
   创建时间   2019年12月9日     上午11:12:23
  类描述：
*
*/

import java.util.Map;

public interface SellerGoodsFavoriteService {

	/**
	 * 查询某件商品被收藏的总数
	 * @param map 中传入商品id
	 * @return  返回收藏的总数
	 */
	public abstract Map<String, Object> findGoodsFavoriteCount(Map<String, Object> map);
	
	/**
	 * 查询收藏该商品的所有用户的具体信息
	 * @param map 中传入商品id
	 * @return 返回收藏的数量以及具体的收藏信息和收藏的用户信息
	 */
	public abstract Map<String, Object> queryGoodsFavoriteInformation(Map<String, Object> map);
	
}
