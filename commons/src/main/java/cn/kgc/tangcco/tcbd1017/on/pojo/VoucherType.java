package cn.kgc.tangcco.tcbd1017.on.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author zhangmiao
 * @version	2019年12月10日	上午11:29:26
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoucherType {
	/**
	 * 优惠券类型id,优惠条件,优惠折扣,优惠上限  1 无上限 2有上限
	 */
	private int voucher_type_id,voucher_type_condition,voucher_type_discount,voucher_type_uplimit_flag;
	
	/**
	 * 上限金额
	 */
	private double voucher_type_limit_amount;
}
