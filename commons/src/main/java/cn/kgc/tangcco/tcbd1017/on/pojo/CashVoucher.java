package cn.kgc.tangcco.tcbd1017.on.pojo;
/**
*@author   席首华
*@version  1.0   </br>
   创建时间   2019年12月10日     上午10:22:19
  类描述：
*
*/

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CashVoucher {

	/**
	 * 优惠券id
	 */
	private int  cash_voucher_id;
	/**
	 *优惠券uuid 
	 */
	private String cash_voucher_uuid;
	/**
	 *优惠券类型  1满多少减多少  2满多少打多少折  3 每满多少减多少
	 */
	private int voucher_type_id;
	/**
	 * 优惠券创建时间
	 */
	private Date cash_voucher_create_time;
	/**
	 * 优惠券更新时间
	 */
	private Date cash_voucher_update_time;
	/**
	 * 优惠券状态  1无效  2有效  3已使用
	 */
	private int cash_voucher_status;
	/**
	 * 优惠券有效时间
	 */
	private Date cash_voucher_available_time;
	/**
	 * 优惠券数量
	 */
	private int cash_voucher_amount;
	/**
	 * 店铺id
	 */
	private int store_id;
	/**
	 * 优惠券剩余数量
	 */
	private int cash_voucher_remaining;
}
