package cn.kgc.tangcco.tcbd1017.on.pojo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
	 * 
	 * @author 薛彤
	 * @version 1.0 2019年12月10日 上午11:02:19
	 *
	 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetMeal {

	/**
	 * 平台套餐主键id
	 */
	private int set_meal_id;
	
	/**
	 * 套餐名称
	 */
	private String set_meal_name;
	/**
	 * 套餐的创建时间
	 */
	private Date set_meal_creat_time;
	/**
	 * 套餐的更新时间
	 */
	private Date set_meal_update_time;
	/**
	 * 套餐价格
	 */
	private double set_meal_price;
	/**
	 * 套餐所收取的商品利息率
	 */
	private double set_meal_interest;
	/**
	 * 套餐的持续时间
	 */
	private long set_meal_duration;
	/**
	 * 套餐的状态
	 */
	private int set_meal_status;
}
