package cn.kgc.tangcco.tcbd1017.on.pojo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
	 * 
	 * @author 薛彤
	 * @version 1.0 2019年12月10日 上午11:07:28
	 *
	 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyerInfo {

	/**
	 * 买家详细信息id
	 */
	private int buyer_info_id;
	/**
	 * 买家id
	 */
	private int buyer_id;
	/**
	 * 买家性别
	 */
	private int buyer_info_gender;
	/**
	 * 买家身份证号  唯一
	 */
	private String buyer_info_idcard;
	/**
	 * 买家身份证姓名
	 */
	private String buyer_info_idcard_name;
	/**
	 * 买家生日
	 */
	private Date buyer_info_birthday;
	/**
	 * 买家地址
	 */
	private String buyer_info_address;
	/**
	 * 买家头像地址
	 */
	private String buyer_info_icon_url;
	/**
	 * 买家信息创建时间
	 */
	private Date buyer_info_create_time;
	/**
	 * 买家信息更新时间
	 */
	private Date buyer_info_update_time;
	/**
	 * 买家信息状态   1失效 2正常 
	 */
	private int buyer_info_status;
	
}
