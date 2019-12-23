package cn.kgc.tangcco.dao;

import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.pojo.Shop;


/**
 * 对商铺查询 各种信息
 * @author 孙伊泽
 *
 */
public interface ShopDao {
	
	
	
	/**	
	 *  查询所有商铺  
	 * 
	 * @param map
	 * @return
	 */
	public List<Shop> selectShop();
	
	/**
	 * 根据uuid 查询店铺名字
	 * @param map
	 * @return
	 */
	public List<Shop> selectShopMingZi(Map<String,Object>map);
	
	/**	
	 *  查询所有商铺详情 根据商铺id查询所有
	 * @param map
	 * @return
	 */
	public List<Shop> selectShopXiangQing(Map<String,Object>map);
	
	
//	/**
//	 * 注册成功插入数据
//	 * @param map
//	 * @return
//	 */
//	public Map<String,Object> insertShop(Map<String,Object>map);
	
	
}
