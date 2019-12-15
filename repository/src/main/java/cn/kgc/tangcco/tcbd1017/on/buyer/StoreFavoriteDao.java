package cn.kgc.tangcco.tcbd1017.on.buyer;
/**
 * 
 * 	@author 和朋朋
 * 	@version 1.0<br>
 * 	创建时间：  2019年12月9日 上午11:22:52 <br>
 * 	类描述：收藏店铺持久层
 *	
 */

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.tcbd1017.on.pojo.StoreFavorite;

public interface StoreFavoriteDao {
//	/**
//	 * 通过卖家id查看所有收藏的店铺
//	 * @param storeFavorite
//	 * @return
//	 * @throws SQLException 
//	 */
//	public abstract List selectAllStoreFavorite(Map<String, Object>map) throws SQLException;
	/**
	 * 查询店铺
	 * @param map
	 * @return
	 */
	public abstract List fuzzyQueryStoreFavorite(Map<String, Object>map);
	/**
	 * 添加收藏店铺
	 * @param storeFavorite
	 * @return
	 * @throws SQLException 
	 */
	public abstract int addStoreFavorite(Map<String, Object>map) throws SQLException;
	/**
	 * 删除收藏店铺
	 * @param store_favorite_id
	 * @return
	 * @throws SQLException 
	 */
	public abstract int deleteStoreFavoriteByStoreFavoriteId(Map<String, Object>map) throws SQLException;
	/**
	 * 查询店铺总数
	 * @throws SQLException 
	 */
	public abstract int count() throws SQLException;
//	/**
//	 * 批量删除
//	 * @param store_favorite_ids
//	 * @return
//	 */
//	public abstract int delete(int[] store_favorite_ids);
}
