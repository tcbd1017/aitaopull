package cn.kgc.tangcco.tcbd1017.on.pojo;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnedGoods {
	/**
	 * 	退货表id
	 */
	private int returned_goods_id;  
	/**
	 * 	订单id
	 */
	private int order_id;  
	/**
	 * 	退货方式
	 */
	private int returned_goods_manner;  
	/**
	 * 	物流id
	 */
	private int logistics_id;  
	/**
	 * 	退货理由
	 */
	private Object returned_goods_;  
	/**
	 * 	退货创建时间
	 */
	private Date returned_goods_create_time;  
	/**
	 * 	退货更新时间
	 */
	private Date returned_goods_update_time;  
	/**
	 * 	退货状态
	 */
	private int returned_goods_status;  
}
