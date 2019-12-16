package cn.kgc.tangcco.tcbd1017.on.pojo;

import java.util.Date;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;


/**
 * @@
 * @author zhangmiao
 * @version	2019年12月14日	下午2:04:40
 *
 */


@Data
@Setter
@Getter

public class EmpAllInfo {
	
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
	
	/**
	 * -----------------------------------------------------------
	 */
	/**
	 * 部门的编号
	 */
	private int dept_id;
	/**
	 * 部门的名称
	 */
	private String dept_name;
	
	/**
	 * 部门的状态 1删除  2正常
	 */
	private int dept_status;
	
	/**
	 * 部门角色id
	 */
	private int deptrole_id;
	/**
	 * 部门角色名称
	 */
	private String deptrole_name;
	/**
	 * 部门角色状态 1删除 2正常
	 */
	private int deptrole_status;
	/**
	 * 部门权限id
	 */
	private int deptpower_id;
	/**
	 * 部门权限级别
	 */
	private int deptpower_level;
	/**
	 * 部门权限状态 1删除 2正常
	 */
	private int deptpower_status;
	/**
	 * -------------------------------------------------
	 */
	
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
	 * 员工角色状态
	 */
	private int emprole_status;
	
	/**
	 * 员工权限主键id,员工权限等级
	 */
	private int empower_id, emppower_level;
	/**
	 * 员工权限状态 1删除 2正常
	 */
	private int emppower_status;
	/**
	 * 员工性别 默认1男 2女
	 */	
	private int emp_info_gender;
	/**
	 * 员工身份证号
	 */
	private String emp_info_idcard;
	/**
	 * 员工身份证名
	 */
	private String emp_info_idcard_name;
	/**
	 * 员工生日
	 */
	private Date emp_info_birthday;
	/**
	 * 员工头像的uil地址
	 */
	private String emp_info_icon_url;
	/**
	 * 员工地址
	 */
	private String emp_info_address;

}
