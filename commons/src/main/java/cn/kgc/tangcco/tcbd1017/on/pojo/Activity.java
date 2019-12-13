package cn.kgc.tangcco.tcbd1017.on.pojo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
	 * 
	 * @author 薛彤
	 * @version 1.0 2019年12月10日 上午10:42:46
	 *
	 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Activity {

	/**
	 * 活动id
	 */
	private int activity_id;
	/**
	 * 活动内容
	 */
	private String activity_content;
	/**
	 * 活动开始时间
	 */
	private Date activity_start_time;
	/**
	 * 活动结束时间
	 */
	private Date activity_end_time;
	/**
	 * 活动创建时间
	 */
	private Date activity_create_time;
	/**
	 * 活动更新时间
	 */
	private Date activity_update_time;
	/**
	 * 活动状态 1未启用 2 启用 
	 */
	private int activity_status;
	/**
	 * 店铺id 
	 */
	private int store_id;
}
