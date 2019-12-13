package cn.kgc.tangcco.tcbd1017.on.pojo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
* @author 作者 Your-Name: 刘煜
* @version 创建时间：2019年12月10日 上午11:02:41 
*    类说明 ：收获信息表
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostageInfo {
	/**
	 * 收货信息id
	 */
	private int postage_info_id;
	/**
	 * 收货人姓名
	 */
	private String postage_info_name;
	/**
	 * 收货人手机号
	 */
	private  String postage_info_mobile;
	/**
	 * 收货人的省/直辖市
	 */
	private int postage_info_province_id;
	/**
	 * 收货人的市
	 */
	private int postage_info_city_id;
	/**
	 * 收货人的县/区
	 */
	private int postage_info_district_id;
	/**
	 * 收货人的详细地址
	 */
	private String postage_info_address;
	/**
	 * 收货信息的创建时间
	 */
	private Date postage_info_create_time;
	/**
	 * 收货信息的更新时间
	 */
	private Date postage_info_update_time;
	/**
	 * 收货信息的状态 1失效 2正常 3默认
	 */
	private Date postage_info_status;
}
