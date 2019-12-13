package cn.kgc.tangcco.tcbd1017.on.pojo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author 刘凯
 * @version 1.0 <br>
 *          创建时间:2019年12月10日 上午10:24:43 <br>
 *          类描述: 部门权限表
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Deptpower {
	/**
	 * 部门权限id
	 */
	private int deptpower_id;
	/**
	 * 部门权限级别
	 */
	private int deptpower_level;
	/**
	 * 部门权限创建时间
	 */
	private Date deptpower_creat_time;
	/**
	 * 部门权限更新时间
	 */
	private Date deptpower_update_time;
	/**
	 * 部门权限状态 1删除 2正常
	 */
	private int deptpower_status;

}
