package cn.kgc.tangcco.tcbd1017.on.pojo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
	 * @author DU MING
	 * @version 1.0	2019年12月10日	上午10:52:48
	 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RewardPoints {
	/**
	 * 积分id
	 */
	private int reward_points_id;
	/**
	 * 买家id
	 */
	private int buyer_id;
	/**
	 * 积分变化情况 1元=10积分
	 */
	private String reward_points_value_change;
	/**
	 * 积分的创建时间
	 */
	private Date reward_points_create_time;
	/**
	 * 积分的更新时间
	 */
	private Date reward_points_update_time;
	/**
	 * 积分的状态 1失效 2 正常
	 */
	private int reward_points_status;
}


