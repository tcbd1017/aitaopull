package cn.kgc.tangcco.tcbd1017.on.pojo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
	 * 
	 * @author 薛彤
	 * @version 1.0 2019年12月10日 上午10:49:16
	 *
	 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Emp {

	/**
	 * 员工表的主键id
	 */
	private int emp_id;
	/**
	 * 员工的uuid
	 */
	private String emp_uuid;
	/**
	 * 员工姓名
	 */
	private String emp_name;
	/**
	 * 员工电话
	 */
	private String emp_mobile;
	/**
	 * 员工的邮箱
	 */
	private String emp_mail;
	/**
	 * 员工的创建时间
	 */
	private Date emp_creat_time;
	/**
	 * 员工的更新时间
	 */
	private Date emp_update_time;
	/**
	 * 员工状态 1离职 2正常 3病假 4.旷工 5……
	 */
	private int emp_status;
}
