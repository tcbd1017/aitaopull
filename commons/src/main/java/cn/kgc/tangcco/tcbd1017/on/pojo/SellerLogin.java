package cn.kgc.tangcco.tcbd1017.on.pojo;
/**
 * 
 * @author zhangmiao
 * @version	2019年12月10日	上午11:14:52
 *
 */

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellerLogin {
	
	/**
	 * 卖家登陆id
	 */
	private int buyer_login_id;
	
	/**
	 * 卖家唯一标识符 uuid,卖家登陆账号,卖家登陆密码,卖家登陆的人脸识别token值
	 */
	private String buyer_uuid,buyer_login_account,buyer_login_password,buyer_login_face_token;
	
	/**
	 * 卖家登陆创建时间,卖家登陆更新时间
	 */
	private Date buyer_login_create_time,buyer_login_update_time;
	
	/**
	 * 卖家登陆的状态  1删除 2正常 
	 */
	private int buyer_login_status;
}
