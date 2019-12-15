package cn.kgc.tangcco.tcbd1017.on;

import java.sql.SQLException;
import java.util.List;

import cn.kgc.tangcco.tcbd1017.on.pojo.Goods;

/**
 * @author 谷亚坤
 * @version 创建时间：2019年12月14日 下午2:25:27
 * @ClassName 类名称  0203 goods持久层
 * @Description 类描述  商品上架下架方法接口
 **/
public interface GoodsDao {
	/**
	 * 查看所有未上架的商品信息
	 * 
	 * @param map 前台传输进的值 初步传进来店铺id
	 * @return 返回List集合 承载商品信息 泛型Good实体类
	 * @throws SQLException sql异常
	 */
	List<Goods> selectOfflineGoods(int store_id) throws SQLException;

	/**
	 * 根据商品id查看商品详细信息
	 * 
	 * @param map 前台传输进的值 初步传进来商品id
	 * @return 返回List集合 承载商品信息 泛型Good实体类
	 * @throws SQLException sql异常
	 */
	List<Goods> selectAllGoods(int goods_id) throws SQLException;

	/**
	 * 查看所有以上架的商品信息
	 * 
	 * @param map 前台传输进的值 初步传进来店铺id
	 * @return 返回List集合 承载商品信息 泛型Good实体类
	 * @throws SQLException sql异常
	 */
	List<Goods> selectOnlineGoods(int store_id) throws SQLException;

	/**
	 * 根据商品id选择上架商品
	 * 
	 * @param goods_id 传进来的商品id
	 * @return 返回的修改参数 1 修改成功 0 修改失败
	 * @throws SQLException SQL异常
	 */
	int supdateGoods(int goods_id) throws SQLException;

	/**
	 * 根据商品id选择下架商品
	 * 
	 * @param goods_id 传进来的商品id
	 * @return 返回的修改参数 1 修改成功 0 修改失败
	 * @throws SQLException SQL异常
	 */
	int xupdateGoods(int goods_id) throws SQLException;

	/**
	 * 根据商品id修改商品价钱
	 * 
	 * @param goods_id 传进来的商品id
	 * @return 返回的修改参数 1 修改成功 0 修改失败
	 * @throws SQLException SQL异常
	 */
	int updateGoodsgoods_price(int goods_id, double goods_price) throws SQLException;
}
