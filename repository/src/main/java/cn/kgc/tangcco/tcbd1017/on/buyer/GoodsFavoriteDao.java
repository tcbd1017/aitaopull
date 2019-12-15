package cn.kgc.tangcco.tcbd1017.on.buyer;
/**
* @author 张忠华  
* @date 2019年12月13日  
* @version 1.0  
*/
import java.sql.SQLException;

import java.util.List;
import java.util.Map;

public interface GoodsFavoriteDao {	
	
	/**
	 *     添加收藏商品
	 * @param map 中传入用户的id buyer_id , 店铺的id goods_id , 添加时间 goods_favorite_create_time , 更新时间 goods_favorite_update_time
	 * @return  返回整型  1 代表成功  0 代表失败
	 */
	public abstract int addGoodsFavorite(Map<String, Object> map) throws SQLException;
	
	/**
	 *  删除关注商品
	 * @param map 中传入该条收藏信息的goods_favorite_id
	 * @return  返回整型  1 代表成功  0 代表失败
	 */
	public abstract int deleteGoodsFavorite(Map<String, Object> map)throws SQLException;
	
	/**
	 *       判断该商品是否已经加入到收藏中
	 * @param map 中传入用户的 商品的 goods_id
	 * @return 1 存在   其他 不存在
	 */
	public abstract int judgeGoodsFavorite(Map<String, Object> map)throws SQLException;

	/**
	 *      根据条件模糊查询已经添加到收藏夹中的商品信息
	 * @param map 中传入用户的 buyer_id 和 查询条件（如：商品名称 goods_name  ）
	 * @return
	 */
	public abstract List<Map<String, Object>> selectGoodsFavorite(Map<String, Object> map)throws SQLException;
	
	/**
	 * 收藏宝贝个数
	 */
	public abstract int countGoodsFavorite(Map<String, Object> map)throws SQLException; 
}
