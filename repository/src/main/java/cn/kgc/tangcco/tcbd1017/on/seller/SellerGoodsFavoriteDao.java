package cn.kgc.tangcco.tcbd1017.on.seller;

import java.util.List;
import java.util.Map;

/**
*@author   席首华
*@version  1.0   </br>
   创建时间   2019年12月10日     下午4:59:04
  类描述：
*
*/
public interface SellerGoodsFavoriteDao {

	/**
	 * 查询商品收藏的总数量
	 * @param map 传入商品的id
	 * @return   返回该商品被收藏的数量
	 */
	public abstract int selectGoodsFavoriteCount(Map<String, Object> map);
	
	/**
	 * 查询收藏该商品的用户的具体信息
	 * @param map 传入商品的id 
	 * @return  返回收藏该商品的用户的集合
	 */
	public abstract List<Map<String, Object>> selectGoodsFavoriteInformation(Map<String, Object> map);
	
	
}
