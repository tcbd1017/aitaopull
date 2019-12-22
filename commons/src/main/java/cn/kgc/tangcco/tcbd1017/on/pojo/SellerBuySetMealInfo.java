package cn.kgc.tangcco.tcbd1017.on.pojo;

/**
 * 
 * @author 刘凯
 * @version 1.0 <br>
 *          创建时间:2019年12月10日 上午10:35:12 <br>
 *          类描述:  商家套餐表
 */

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellerBuySetMealInfo {
	/**
	 * 商家套餐的购买况餐的主键id
	 */
	private int seller_buy_set_meal_info_id;
	/**
	 * 卖家的id
	 */
	private int seller_id;
	/**
	 * 套餐的id
	 */
	private int set_meal_id;
	/**
	 * 套餐的开始时间
	 */
	private Date set_meal_strat_time;
	/**
	 * 卖家套餐购买的创建时间
	 */
	private Date seller_buy_set_meal_info_creat_time;
	/**
	 * 卖家套餐购买的更新时间
	 */
	private Date seller_buy_set_meal_info_update_time;
	/**
	 * 卖家套餐购买的状态1删除 2正常
	 */
	private int seller_buy_set_meal_info_status;
	
	/**
	 * 负责人Id
	 */
	private int person_in_charge_id;

	public SellerBuySetMealInfo(int seller_buy_set_meal_info_id, int seller_id, int set_meal_id,
			Date set_meal_strat_time, Date seller_buy_set_meal_info_creat_time,
			Date seller_buy_set_meal_info_update_time, int person_in_charge_id) {
		this.seller_buy_set_meal_info_id = seller_buy_set_meal_info_id;
		this.seller_id = seller_id;
		this.set_meal_id = set_meal_id;
		this.set_meal_strat_time = set_meal_strat_time;
		this.seller_buy_set_meal_info_creat_time = seller_buy_set_meal_info_creat_time;
		this.seller_buy_set_meal_info_update_time = seller_buy_set_meal_info_update_time;
		this.person_in_charge_id = person_in_charge_id;
	}
	
}
