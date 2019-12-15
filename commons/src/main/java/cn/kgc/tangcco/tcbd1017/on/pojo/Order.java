package cn.kgc.tangcco.tcbd1017.on.pojo;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
	/**
	 *	订单id
	 */
	private int order_id;  
	/**
	 * 	订单编号
	 */
	private String order_uuid; 
	/**
	 * 	物流id
	 */
	private int logistics_id;  
	/**
	 * 	订单创建时间
	 */
	private Date order_create_time;  
	/**
	 * 	订单更新时间
	 */
	private Date order_update_time;  
	/**
	 * 	状态：1、未付款，2、已付款，3、未发货，4、已发货，5、待收货，6、待评价，7、交易完成，8、交易关闭
	 */
	private int order_status;  
	/**
	 * 	买家id
	 */
	private int buyer_id; 
	/**
	 * 	实际支付金额
	 */
	private double order_payment;  
	/**
	 * 	买家优惠券id
	 */
	private int buyer_cash_voucher_id;  
	/**
	 * 	支付类型
	 */
	private int order_payment_type;  
	/**
	 * 	支付时间
	 */
	private Date order_payment_time;  
	/**
	 * 	发货时间
	 */
	private Date order_consign_time;  
	/**
	 * 	交易完成时间
	 */
	private Date order_end_time;  
	/**	
	 * 	交易关闭时间
	 */
	private Date order_close_time;  
	/**
	 * 	用户留言信息
	 */
	private String order_buyer_message;  
}
