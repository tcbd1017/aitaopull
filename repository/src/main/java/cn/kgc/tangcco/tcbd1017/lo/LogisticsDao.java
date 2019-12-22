package cn.kgc.tangcco.tcbd1017.lo;

import java.util.List;
import java.util.Map;

/**
 * 运单有关的接口
 * @author CUI
 *
 */
public interface LogisticsDao {

	
	/**
	 * 	根据物流单号查询当前快递的状态，按时间降序排序
	 * @param map 物流单号
	 * @return 一串关于物流状态的信息
	 */
	List selectLogisticsStatusByLogisticsId(Map<String ,Object> map);
	
	/**
	 * 	添加物流信息 （service层需要与中间表联动）
	 * @param map 所有物流信息： 创建时间(可以数据库直接生成) 寄件人（姓名，电话，地址）  收件人（姓名，电话，地址）  商品
	 * @return 成功或失败
	 * 
	 */
	int addLogistics(Map<String ,Object> map);
	
	/**
	 * 	添加当前物流的状态 （这个功能不使用，写出来，与状态表联动，添加时判断状态是否正确）
	 * @param map 物流id，状态id
	 * @return 成功或失败
	 */
	int addLogisticsStatus(Map<String ,Object> map);
	
	
	/**
	 * 根据订单id查找订单详情(将订单的详细信息展示出来)
	 * @param map
	 * @return
	 */
	List selectLogisticsIdByLogistics(Map<String ,Object> map);
	
}
