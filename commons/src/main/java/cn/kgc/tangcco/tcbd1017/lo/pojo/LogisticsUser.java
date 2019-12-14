package cn.kgc.tangcco.tcbd1017.lo.pojo;
 
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 王立宁
 * @version 1.0 <br>
 * 创建时间：2019年12月10日下午7:37:08
 * 类描述：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogisticsUser {
	/*
	 * 用户id
	 */
	private int user_id;
	/*
	 * 用户uuid（实际使用）
	 */
	private String user_uuid;
	/*
	 * 用户名
	 */
	private String nickname;
	/*
	 *账号 
	 */
	private String account;
	/*
	 * 密码
	 */
	private String password;
	/*
	 *手机号 
	 */
	private String mobile;
}
