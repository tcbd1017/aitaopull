package cn.kgc.tangcco.tcbd1017.on.seller.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.tcbd1017.on.seller.SellerGoodsFavoriteDao;
import cn.kgc.tangcco.tcbd1017.on.seller.SellerGoodsFavoriteService;
import cn.kgc.tangcco.tcbd1017.on.seller.impl.SellerGoodsFavoriteDaoImpl;

/**
*@author   席首华
*@version  1.0   </br>
   创建时间   2019年12月9日     上午11:12:46
  类描述：
*
*/
public class SellerGoodsFavoriteServiceImpl implements SellerGoodsFavoriteService{

	SellerGoodsFavoriteDao sellerGoodsFavoriteDao = new SellerGoodsFavoriteDaoImpl();
	/**
	 * 查询某一商品被收藏的总数量
	 * map中传入的是 商品id
	 * 返回的结果中包含count 总数量
	 */
	@Override
	public Map<String, Object> findGoodsFavoriteCount(Map<String, Object> map) {
		Map<String, Object> result = new HashMap<String, Object>();
		int count = sellerGoodsFavoriteDao.selectGoodsFavoriteCount(map);
		result.put("count", count);
		return result;
	}
	
	/**
	 * 查询收藏某一商品的具体收藏信息和用户信息
	 * map中传入的是 商品id
	 * 返回的结果中包含  商品被收藏的总数以及收藏用户等具体信息
	 */
	@Override
	public Map<String, Object> queryGoodsFavoriteInformation(Map<String, Object> map) {
		Map<String, Object> result = new HashMap<String, Object>();
		int count = sellerGoodsFavoriteDao.selectGoodsFavoriteCount(map);
		List<Map<String, Object>> list = sellerGoodsFavoriteDao.selectGoodsFavoriteInformation(map);
		result.put("count", count);
		result.put("data", list);
		return result;
	}

	
	
	
}
