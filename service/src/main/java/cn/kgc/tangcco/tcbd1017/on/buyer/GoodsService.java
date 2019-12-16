package cn.kgc.tangcco.tcbd1017.on.buyer;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.tcbd1017.on.pojo.Goods;

/**
*@author 作者：肖越
*@version 1.0 创建时间:2019年12月13日上午11:21:53
*/
public interface GoodsService {
	/**
	 * 查詢所有物品
	 * @return
	 */

	public  Map<String,Object> queryAllGoods();

	
	
	/**
	 * 根据商品类型查询商品
	 * @param goodsType 商品类型名称
	 * @return map类型
	 */

	public  Map<String,Object> queryByGoodsType(String goodsType);

	



	/**
	 * 按照商品名称、或者商品类型、或者商品简介 ,模糊查询出相关商品
	 * @param vague 商品名称、或者商品类型、或者商品简介
	 * @return map类型
	 */
	public  Map<String,Object> queryVagueByGoods_nameOrGoods_brandOrGoods_presentation(String vague) ;






}
