package cn.kgc.tangcco.tcbd1017.on.pojo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
*@author   席首华
*@version  1.0   </br>
   创建时间   2019年12月10日     上午10:51:58
  类描述：
*
*/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Buyer {

	/**
	 * 买家id
	 */
	private int buyer_id;
	/**
	 * 买家唯一标识符 uuid
	 */
	private String buyer_uuid;
	/**
	 * 买家姓名  唯一 
	 */
	private String buyer_name;
	/**
	 * 买家手机号
	 */
	private String buyer_mobile;
	/**
	 * 买家邮箱
	 */
	private String buyer_mail;
	/**
	 * 买家创建时间
	 */
	private Date buyer_create_time;
	/**
	 * 买家更新时间
	 */
	private Date buyer_update_time;
	/**
	 * 买家状态 1失效 2正常
	 */
	private int buyer_status;
}
