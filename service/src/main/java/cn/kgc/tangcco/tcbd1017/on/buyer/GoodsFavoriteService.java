package cn.kgc.tangcco.tcbd1017.on.buyer;



import java.util.List;
import java.util.Map;

/**
* @author 张忠华  
* @date 2019年12月13日  
* @version 1.0  
*/
public interface GoodsFavoriteService {
	
	/**
	 *     添加收藏商品
	 * @param map 中传入用户的id buyer_id , 店铺的id goods_id , 添加时间 goods_favorite_create_time , 更新时间 goods_favorite_update_time
	 * @return  
	 */
	public abstract Map<String,Object> addGoodsFavorite(Map<String, Object> map);
	/**
	 * 删除收藏商品
	 * @param map  传入收藏商品的id
	 */
	public abstract Map<String,Object> removeGoodsFavorite(Map<String,Object>map);
	/**
	 * 查询商品
	 */
	public abstract Map<String,Object> queryGoodsFavorite(Map<String,Object>map);
	
}
