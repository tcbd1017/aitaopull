package cn.kgc.tangcco.tcbd1017.on.pojo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

	/**
	 * @author DU MING
	 * @version 1.0	2019年12月10日	上午10:31:34
	 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuyerLogin {
	/**
	 * 买家登陆id
	 */
	private int buyer_login_id;
	/**
	 * 买家唯一标识符uuid
	 */
	private String buyer_uuid;
	/**
	 * 买家登陆帐号
	 */
	private String buyer_login_account;
	/**
	 * 买家登陆密码
	 */
	private String buyer_login_password;
	/**
	 * 买家登陆人脸识别token
	 */
	private String buyer_login_face_token;
	/**
	 * 买家登陆创建时间
	 */
	private Date buyer_login_create_time;
	/**
	 * 买家登陆更新时间
	 */
	private Date buyer_login_update_time;
	/**
	 * 买家登陆记录启用状态 1失效 2有效
	 */
	private int buyer_login_status;
}


