package cn.kgc.tangcco.tcbd1017.on.seller;

import java.util.Map;

/**
 * @author 谷亚坤
 * @version 创建时间：2019年12月14日 下午2:30:23
 * @ClassName 类名称 0203goodsService接口
 * @Description 类描述 逻辑层接口
 **/
public interface GoodsService {
	/**
	 * 查看所有未上架的商品信息
	 * 
	 * @param map 前台传输进的值 初步传进来店铺id
	 * @return 返回Map集合 承载商品信息 成功与否
	 * @throws SQLException sql异常
	 */
	Map<String, Object> selectOfflineGoods(int store_id);

	/**
	 * 查看所有以上架的商品信息
	 * 
	 * @param map 前台传输进的值 初步传进来店铺id
	 * @return 返回Map集合 承载商品信息 成功与否
	 * @throws SQLException sql异常
	 */
	Map<String, Object> selectOnlineGoods(int store_id);

	/**
	 * 根据商品id选择上架商品
	 * 
	 * @param goods_id 传进来的商品id
	 * @return 返回Map集合 承载商品修改信息 成功与否
	 * @throws SQLException SQL异常
	 */
	Map<String, Object> supdateGoods(int goods_id);

	/**
	 * 根据商品id选择下架商品
	 * 
	 * @param goods_id 传进来的商品id
	 * @return 返回Map集合 承载商品修改信息 成功与否
	 * @throws SQLException SQL异常
	 */
	Map<String, Object> xupdateGoods(int goods_id);

	/**
	 * 根据商品id修改商品价钱
	 * 
	 * @param goods_id 传进来的商品id
	 * @return 返回Map集合 承载商品修改信息 成功与否
	 * @throws SQLException SQL异常
	 */
	Map<String, Object> updateGoodsgoods_price(int goods_id, double goods_price);
}
