package cn.kgc.tangcco.tcbd1017.on.seller;

import java.util.Map;


/**
 * @author 谷亚坤
 * @version 创建时间：2019年12月20日 上午9:02:55
 * @ClassName 类名称 逻辑层
 * @Description 类描述 商品上架下架逻辑层
 **/
public interface SellerGoodsService {
	/**
	 * 查看所有未上架的商品信息
	 * 
	 * @param map 前台传输进的值 初步传进来店铺id
	 * @return 返回Map集合 承载商品信息 成功与否
	 * @throws SQLException sql异常
	 */
	Map<String, Object> selectOfflineGoods(Map<String, Object> map);

	/**
	 * 查看所有以上架的商品信息
	 * 
	 * @param map 前台传输进的值 初步传进来店铺id
	 * @return 返回Map集合 承载商品信息 成功与否
	 * @throws SQLException sql异常
	 */
	Map<String, Object> selectOnlineGoods(Map<String, Object> map);
	/**
	 * 查看所有的商品信息
	 * 
	 * @param map 前台传输进的值 初步传进来店铺id
	 * @return 返回Map集合 承载商品信息 成功与否
	 * @throws SQLException sql异常
	 */
	Map<String, Object> selectWholeGoods(Map<String, Object> map);

	/**
	 * 根据商品id选择上架商品
	 * 
	 * @param goods_id 传进来的商品id
	 * @return 返回Map集合 承载商品修改信息 成功与否
	 * @throws SQLException SQL异常
	 */
	Map<String, Object> supdateGoods(Map<String, Object> map);

	/**
	 * 根据商品id选择下架商品
	 * 
	 * @param goods_id 传进来的商品id
	 * @return 返回Map集合 承载商品修改信息 成功与否
	 * @throws SQLException SQL异常
	 */
	Map<String, Object> xupdateGoods(Map<String, Object> map);

	/**
	 * 根据商品id修改商品价钱
	 * 
	 * @param goods_id 传进来的商品id
	 * @return 返回Map集合 承载商品修改信息 成功与否
	 * @throws SQLException SQL异常
	 */
	Map<String, Object> updateGoodsgoods_price(Map<String, Object> map);
}
