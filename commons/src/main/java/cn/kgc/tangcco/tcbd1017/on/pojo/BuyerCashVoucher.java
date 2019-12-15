package cn.kgc.tangcco.tcbd1017.on.pojo;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyerCashVoucher {
	/**
	 * 	买家优惠券id
	 */
	private int buyer_cash_voucher_id;  
	/**
	 * 	买家id
	 */
	private int buyer_id;  
	/**
	 * 	优惠券id
	 */
	private int cash_voucher_id;  
	/**
	 * 	买家优惠券创建时间
	 */
	private Date buyer_cash_voucher_create_time;  
	/**
	 * 	买家优惠券更新时间
	 */
	private Date buyer_case_voucher_update_time;  
	/**
	 * 	买家优惠券状态
	 */
	private int buyer_case_voucher_status;  
}
