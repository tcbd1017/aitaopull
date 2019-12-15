package cn.kgc.tangcco.tcbd1017.on.pojo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author 刘凯
 * @version 1.0 <br>
 *          创建时间:2019年12月10日 上午10:28:02 <br>
 *          类描述: 员工信息表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpInfo {
	/**
	 * 员工信息di
	 */
	private int emp_info_id;
	/**
	 * 员工id
	 */
	private int emp_id;
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
	/**
	 * 员工详细信息创建时间
	 */
	private Date emp_info_create_time;
	/**
	 * 员工详细信息更新时间
	 */
	private Date emp_info_update_time;
	/**
	 * 员工信息状态 1删除 2正常
	 */
	private int emp_info_status;

}
