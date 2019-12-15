package cn.kgc.tangcco.tcbd1017.on.pojo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author zhangmiao
 * @version 2019年12月10日 上午11:38:41
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emppower {
	/**
	 * 员工权限主键id,员工权限等级
	 */
	private int empower_id, emppower_level;

	/**
	 * 员工权限创建时间,员工权限更新时间
	 */
	private Date emppower_cerat_time, emppower_update_timg;

	/**
	 * 员工权限状态 1删除 2正常
	 */
	private int emppower_status;
}
