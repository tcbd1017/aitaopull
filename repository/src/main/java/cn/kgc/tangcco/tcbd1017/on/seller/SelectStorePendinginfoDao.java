package cn.kgc.tangcco.tcbd1017.on.seller;

import java.util.List;
import java.util.Map;

import cn.kgc.tangcco.tcbd1017.on.pojo.Goods;
import cn.kgc.tangcco.tcbd1017.on.pojo.Order;

/**
 * 
 * @ClassName: SelectStorePending
 * @Description: TODO(对订单表已付款状态的修改和对所有待发货信息的查询)
 * @author A18ccms a18ccms_gmail_com
 * @date 2019年12月13日 下午4:18:34 * *
 */
public interface SelectStorePendinginfoDao {
	/**
	 * 
	 * @Title: selectOrder 
	 * @Description: TODO(查询所有的待发货的订单信息) 
	 * @param @param map
	 * @param @return    设定文件 
	 * @return List<Object> 返回类型  返回查询到的所有状态为待发货的订单信息
	 * @throws
	 */
	int selectOrder(Map<String,Object> map);
	
	/**
	 * 
	 * @Title: updateOrder 
	 * @Description: TODO(查询已付款的订单数量) 
	 * @param @param map
	 * @param @return    设定文件 
	 * @return int 返回类型 初始值为 -1 0 为失败 比零大即为修改成功
	 * @throws
	 */
	int selectOrderPending(Map<String,Object> map);
	
	/**
 	 * @Title: select_Store_Favorlte_Number
	 * @Description: TODO(从数据库中查询本店铺信誉度) 
	 * @param  map（店铺的ID）
	 * @param @return    设定文件 
	 * @return int（根据ID查询出来的店铺被收藏次数）    返回类型 
	 * @throws
	 */
	int SelectreputationDao(Map<String ,Object> map);
	/**
 	 * @Title: select_Store_Favorlte_Number
	 * @Description: TODO(从数据库中查询本店交易完成的订单) 
	 * @param  map（店铺的ID）
	 * @param @return    设定文件 
	 * @return int（根据ID查询出来的店铺被收藏次数）    返回类型 
	 * @throws
	 */
	int SelectCompleteTransactionDao(Map<String ,Object> map);
	/**
 	 * @Title: select_Store_Favorlte_Number
	 * @Description: TODO(从数据库中查询本店交易完成的订单金额) 
	 * @param  map（店铺的ID）
	 * @param @return    设定文件 
	 * @return int（根据ID查询出来的店铺被收藏次数）    返回类型 
	 * @throws
	 */
	double SelectCompleteTransactionMoneyDao(Map<String ,Object> map);
	/**
 	 * @Title: select_Store_Favorlte_Number
	 * @Description: TODO(从数据库中查询本店所有已上架宝贝) 
	 * @param  map（店铺的ID）
	 * @param @return    设定文件 
	 * @return int（根据ID查询出来的店铺被收藏次数）    返回类型 
	 * @throws
	 */
	int SelectGoodsNumberDao(Map<String ,Object> map);
	/**
 	 * @Title: select_Store_Favorlte_Number
	 * @Description: TODO(从数据库中查询本店所有已上架宝贝数量) 
	 * @param  map（店铺的ID）
	 * @param @return    设定文件 
	 * @return int（根据ID查询出来的店铺被收藏次数）    返回类型 
	 * @throws
	 */
	List<Goods> SelectGoodsDao(Map<String ,Object> map);
}
