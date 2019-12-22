package cn.kgc.tangcco.tcbd1017.on.seller;


import java.util.Map;



/** 
 * @author 江岛
 * @ClassName: Store_Favorite_infoDao 
 * @Description: TODO(卖家查询店铺收藏收藏情况（数量）) 
 * @date 创建时间: 2019年12月9日 上午11:47:08
 */

public interface SelectStoreFavorlteinfoDao {
	/**
 	 * @Title: select_Store_Favorlte_Number
	 * @Description: TODO(从数据库中查询本店铺被收藏的次数) 
	 * @param  map（店铺的ID）
	 * @param @return    设定文件  返回一个数字   这个数字是当月收藏店铺的个数
	 * @return int（根据ID查询出来的店铺被收藏次数）    返回类型 
	 * @throws
	 */
	int SelectStoreFavorlteNumberDao(Map<String ,Object> map);
	/**
	 * 
	 * @author 江岛 
	 * @Title: selectGoodsFavoriteNumber 
	 * @Description: TODO(查询12个月每月收藏商品个数) 
	 * @param @param map
	 * @param @return    设定文件  返回一个数字   这个数字是当月收藏商品的个数
	 * @return int    返回类型  
	 * @throws
	 */
	public int selectGoodsFavoriteNumber(Map<String, Object> map) ;
	/**
	 * 
	 * @author 江岛 
	 * @Title: updateGoods 
	 * @Description: TODO(根据传入的商品信息上架商品) 
	 * @param @param map
	 * @param @return    设定文件  返回数字根据数字得知数据库受影响的数据条数
	 * @return int    返回类型 
	 * @throws
	 */
	public int updateGoods(Map<String,Object> map );
	 
}
