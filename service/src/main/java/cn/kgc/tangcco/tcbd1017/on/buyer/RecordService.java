package cn.kgc.tangcco.tcbd1017.on.buyer;

import java.sql.SQLException;
import java.util.Map;

/**
*@author 作者：肖越
*@version 1.0 创建时间:2019年12月14日上午8:57:34
*/
public interface RecordService {

	/**
	 * 根据买家id 显示出该买家所有历史足迹商品的信息以及买家所有信息和历史记录所有信息  (按照历史足迹时间降序排序)
	 * 设计了三表联查 ：  1. 买家表（0101_buyer）  2. 历史记录表（0103_record） 3.商品表（0203_goods）
	 * 我把三表联查的结果只要有的信息就全部返回了，如有需要直接调用
	 */
	public  Map<String,Object> queryAllRecord(String buyer_id) ;
	
	/**
	 * 根据传过来的历史记录id删除相对应的商品足迹（单个删除）
	 * @param record_id 历史记录id
	 * @return返回值 受影响行数
	 * 
	 */
	public Map<String,Object> removeRecordByRecord_id(String record_id);
	
	
	/**
	 * 根据传过来的买家id 和商品名称（支持模糊查询）
	 * 返回买家历史记录里所对应的商品图片url等信息
	 * 
	 */
	public  Map<String,Object> queryRecordAllByBuyerIdAndGoodsName(String buyer_Id,String goods_name);
	
	/**
	 * 
	 * @param buyer_id 买家id
	 * @param goods_id 商品id
	 * 
	 * 
	 */
	public Map<String,Object> addRecordByBuyer_idAndGoods_id(String buyer_id,String goods_id);
	
	/**
	 * 《肖越根据前台页面需求额外新添加的方法 1》
	 * 大家如果有需要就调用就好了
	 * 此方法根据买家id返回买家详细信息
	 * @param buyer_id 买家id
	 * @return 返回该买家详细信息
	 */
	public Map<String,Object> queryBuyerInfoBybuyer_id(String buyer_id);
	
	
	/**
	 * 《肖越根据前台页面需求额外新添加的方法 2》
	 * 大家如果有需要就调用就好了
	 * 此方法根据买家id和当前页码  返回买家历史记录里商品图片url等信息 （并进行了分页）      例：传过来的json字符串    {"pageNo":"1","buyer_id":"1"}
	 * @param buyer_id 买家id
	 * @return 返回该买家历史记录里商品图片url等信息
	 */
	public Map<String,Object> queryGoodsPictureUrlByBuyerId(String buyer_id,String pageNo);
	
	
	/**
	 * 《肖越根据前台页面需求额外新添加的方法 3》
	 * 大家如果有需要就调用就好了
	 * 此方法根据买家id获取买家历史记录总条数
	 * @param buyer_id 买家id
	 * @return  根据买家id获取(buyerHistory)买家历史记录总条数    和 总页数(totalPages)按照一页显示五条数据
	 */
	public Map<String,Object> queryCountBuyerHistory(String buyer_id);
	
	
	/**
	 * 《肖越根据前台页面需求额外新添加的方法 4》
	 * 大家如果有需要就调用就好了
	 * 此方法根据买家id获取买家购物车前两条数据
	 * @param buyer_id 买家id
	 * @return 买家购物车前两条数据
	 */
	public Map<String,Object> queryBuyerShoppingCart(String buyer_id);
	
	
	/**
	 * 《肖越根据前台页面需求额外新添加的方法 5》
	 * 大家如果有需要就调用就好了
	 * 此方法根据买家id和当前页码获取买家购物车的所有数据 (购物车商品图片、重量、长宽高...等信息)
	 * 每页显示五条数据
	 * @param buyer_id 买家id
	 * @return 买家购物车的所有数据
	 */
	public Map<String,Object> queryAllBuyerShoppingCart(String buyer_id,String pages);
	
	/**
	 * 《肖越根据前台页面需求额外新添加的方法 6》
	 * 大家如果有需要就调用就好了
	 * 此方法根据买家id获取买家购物车总条数和总页数
	 * @param buyer_id 买家id
	 * @return  根据买家id获取(shoppingCart)买家购物车总条数 和 总页数(totalPages 按照一页显示五条数据来计算)
	 */
	public Map<String,Object> queryCountShoppingCart(String buyer_id);
	
	
}
