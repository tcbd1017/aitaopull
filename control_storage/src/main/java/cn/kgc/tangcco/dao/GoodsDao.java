package cn.kgc.tangcco.dao;

import java.sql.SQLException;
import java.util.Map;

/**
 * 货物信息表
 * @author 孙伊泽
 *
 */
public interface GoodsDao {
	
	/**
	 * 查看货物信息  
	 * 若是管理员查看 本仓库的所有货物  按类型     店铺  按仓库查询 
	 * 若是商铺查看 本仓库的自家的货  按型号品牌  类型
	 * 1
	 * @param map
	 * 		key:PagePang	value:分页工具类
	 * 		key:acccount	value:登录账号
	 * 		key:shop_id		value:店铺id
	 * @return
	 * 		key:allGoods	value:所有商品查询结果(list)
	 * @throws SQLException 
	 */
	public Map<String,Object> selectgoods(Map<String,Object> map) throws SQLException;
	/**
	 * 查看货物信息  
	 * 若是管理员查看 本仓库的所有货物  按类型     店铺  按仓库查询 
	 * 若是商铺查看 本仓库的自家的货  按型号品牌  类型
	 * 1
	 * @param map
	 * 		key:PagePang	value:分页工具类
	 * 		key:acccount	value:登录账号
	 * 		key:shop_id		value:店铺id
	 * @return
	 * 		查询所有商品总数量
	 * @throws SQLException 
	 */
	public int selectgoodsCount(Map<String, Object> map)  throws SQLException;
	/**
	 * 
	 * goods   jiance   
	 * 查看货物同一仓库里是否存在   参数 仓库的uuid   货物型号
	 * @param map
	 * 		key:jiance_cangkuuuid  value:仓库的uuid
	 * 		key:model_name 		  value:型号名称
	 * @return
	 * @throws SQLException 
	 */
	public boolean selectHuoWu(Map<String,Object>map) throws SQLException;
	
	
	/**
	 * 
	 * goods  jiance  
	 * 如果不存在   直接添加  添加数量 从jiance表计算出来
	 * @param map
	 * 		key:goods_count		value:货物数量
	 * 		key:goods_type_id	value:货物类别id
	 * 		key:goods_model_id	value:货物型号id
	 * 		key:goods_brand_id	value:货物品牌id
	 * 		key:goods_shop_id	value:店铺id
	 * 		key:goods_w_s_uuid	value:仓库uuid
	 * @return
	 * @throws SQLException 
	 */
	public int insert(Map<String,Object>map) throws SQLException;
	
	/**
	 * jiance   goods
	   *   同一仓库 ，有相同货物 如果存在   在原有基础上修改
	 * @param map
	 * 		key:goods_count			value:货物数量
	 * 		key:jiance_cangkuuuid	value:仓库的uuid
	 * 		key:model_name			value:型号名称
	 * @return
	 * @throws SQLException 
	 */
	public int update(Map<String,Object>map) throws SQLException;
	
	
	/**
	 * jiance   goods
	   *   出货员检测库存充足   出货 对个数减少
	 * @param map
	 * 		key:goods_count		value:货物数量
	 * 		key:goods_id		value:货物id
	 * @return
	 * @throws SQLException 
	 */
	//public int updateShengYuCount(Map<String,Object>map) throws SQLException;
	
	/**
	 * 查询库存
	*  条件货物型号 根据型号id   
	*  @param map
	*  		key:goods_model_id		value:货物型号id
	*  @return
	 * @throws SQLException 
	 * 
	 */
	public int ChaXunKuCun(Map<String,Object>map) throws SQLException;
	
	/**
	 * 根据仓库uuid  和  货物型号确定到  商品id
	 * @param map
	 * @return
	 * @throws SQLException
	 */
	int ChaGoodsId(Map<String, Object> map) throws SQLException;
	
	/**
	  * jiance   goods
	    *   出货员检测库存充足   出货 对个数减少
	  * @param map
	  *   key:goods_count  value:货物数量
	  *   key:goods_id  value:货物id
	  * @return
	  * @throws SQLException 
	  */
	int jianUpdateShengYuCount(Map<String, Object> map) throws SQLException;
	
	/**
	  * jiance   goods
	    *   出货员检测库存充足   出货 对个数增加
	  * @param map
	  *   key:goods_count  value:货物数量
	  *   key:goods_id  value:货物id
	  * @return
	  * @throws SQLException 
	  */
	int addUpdateShengYuCount(Map<String, Object> map) throws SQLException;
}
