package cn.kgc.tangcco.tcbd1017.on.pojo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
	 * 
	 * @author 薛彤
	 * @version 1.0 2019年12月10日 上午10:57:55
	 *
	 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpLogin {

	/**
	 * 员工登陆表的主键id
	 */
	private int emp_login_id;
	/**
	 * 员工唯一标识符 uuid
	 */
	private String emp_uuid;
	/**
	 * 员工登陆账号
	 */
	private String emp_login_account;
	/**
	 * 员工登陆密码
	 */
	private String emp_login_password;
	/**
	 * 员工登陆人脸识别token值
	 */
	private String emp_login_face_token;
	/**
	 * 员工登陆表中数据的创建时间
	 */
	private Date emp_login_create_time;
	/**
	 * 员工登陆表中数据的更新时间
	 */
	private Date emp_login_update_time;
	/**
	 * 员工登陆状态 1删除   2正常
	 */
	private int emp_login_status;
}
