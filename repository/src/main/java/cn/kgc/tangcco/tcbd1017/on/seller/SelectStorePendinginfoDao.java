package cn.kgc.tangcco.tcbd1017.on.seller;

import java.util.List;
import java.util.Map;

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
	int selectOrder();
	/**
	 * 
	 * @Title: updateOrder 
	 * @Description: TODO(将所有的已付款的订单信息修改为待发货) 
	 * @param @param map
	 * @param @return    设定文件 
	 * @return int 返回类型 初始值为 -1 0 为失败 比零大即为修改成功
	 * @throws
	 */
	int updateOrder();
	/**
	 * 
	 * @Title: updateOrder 
	 * @Description: TODO(查询已付款的订单数量) 
	 * @param @param map
	 * @param @return    设定文件 
	 * @return int 返回类型 初始值为 -1 0 为失败 比零大即为修改成功
	 * @throws
	 */
	int selectOrderPending();
}
