package cn.kgc.tangcco.tcbd1017.lo.pojo;
 /**
 * @author 王立宁
 * @version 1.0 <br>
 * 创建时间：2019年12月10日下午7:47:37
 * 类描述：
 */

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogisticsMoney {
	/*
	 * 账单id
	 */
	private int money_id;
	/*
	 * 账单uuid（实际使用）
	 */
	private String money_uuid;
	/*
	 * 账单金额
	 */
	private double money;
	/*
	 * 账单状态（1.未支付2.已支付）
	 */
	private int money_status_id;
	/*
	 * 账单创建时间
	 */
	private Date money_time;
	/*
	 * 账单更新时间
	 */
	private Date money_update_time;
}
