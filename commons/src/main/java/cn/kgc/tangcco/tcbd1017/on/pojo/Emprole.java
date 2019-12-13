package cn.kgc.tangcco.tcbd1017.on.pojo;
/**
 * 
 * @author zhangmiao
 * @version	2019年12月10日	上午11:35:00
 *
 */

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emprole {
	
	/**
	 * 员工角色id
	 */
	private int emprole_id;
	
	/**
	 * 员工角色名称
	 */
	private String emprole_name;
	
	/**
	 * 员工角色的数量限制
	 */
	private int emprole_quantitative_limitation;
	
	/**
	 *员工角色创建时间,员工角色更新时间 
	 */
	private Date emprole_creat_time, emprole_update_time;
	
	/**
	 * 员工角色状态
	 */
	private int emprole_status;

}
