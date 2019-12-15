package cn.kgc.tangcco.tcbd1017.on.pojo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author 刘凯
 * @version 1.0 <br>
 *          创建时间:2019年12月10日 上午10:55:57 <br>
 *          类描述 :    平台支出明细表
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopExpenditureInfo {
	/**
	 * 平台支出明细主键id
	 */
	private int shop_expenditure_info_id;
	/**
	 * 收入类型，1代表套餐收入 2利息收入 3采购支出 4物流支出
	 */
	private int shop_income_and_expanditure_type;
	/**
	 * 支出的出账时间
	 */
	private Date shop_expenditure_info_time;
	/**
	 * 支出金额
	 */
	private double shop_expenditure_info_money;
	/**
	 * 支出的订单id
	 */
	private int expenditure_order_id;
	/**
	 * 平台支出明细创建时间
	 */
	private Date shop_expenditure_info_create_time;
	/**
	 * 平台支出明细更新时间
	 */
	private Date shop_expenditure_info_update_time;
	/**
	 * 平台支出明细状态 1删除 2正常
	 */
	private int shop_expenditure_info_status;

}
