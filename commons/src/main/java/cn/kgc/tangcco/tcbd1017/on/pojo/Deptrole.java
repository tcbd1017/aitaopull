package cn.kgc.tangcco.tcbd1017.on.pojo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author 刘凯
 * @version 1.0 <br>
 *          创建时间:2019年12月10日 上午10:21:27 <br>
 *          类描述: 部门角色类
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Deptrole {
	/**
	 * 部门角色id
	 */
	private int deptrole_id;
	/**
	 * 部门角色名称
	 */
	private String deptrole_name;
	/**
	 * 部门角色创建时间
	 */
	private Date deptrole_creat_time;
	/**
	 * 部门角色更新时间
	 */
	private Date deptrole_update_time;
	/**
	 * 部门角色状态 1删除 2正常
	 */
	private int deptrole_status;
}
