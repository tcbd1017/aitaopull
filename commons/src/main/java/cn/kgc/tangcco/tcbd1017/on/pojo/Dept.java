package cn.kgc.tangcco.tcbd1017.on.pojo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
	 * 
	 * @author 薛彤
	 * @version 1.0 2019年12月10日 上午10:53:50
	 *
	 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dept {

	/**
	 * 部门的编号
	 */
	private int dept_id;
	/**
	 * 部门的名称
	 */
	private String dept_name;
	/**
	 * 部门的创建时间
	 */
	private Date dept_creat_time;
	/**
	 * 部门的更新时间
	 */
	private Date dept_update_time;
	/**
	 * 部门的状态 1删除  2正常
	 */
	private int dept_status;
}
