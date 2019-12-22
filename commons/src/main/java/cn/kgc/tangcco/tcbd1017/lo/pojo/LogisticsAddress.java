package cn.kgc.tangcco.tcbd1017.lo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 王立宁
 * @version 1.0 <br>
 * 创建时间：2019年12月13日上午8:16:41
 * 类描述：
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogisticsAddress {
	/*
	 *用户添加的地址的id 
	 */
	private int logistics_address_id;
	/*
	 *寄件人姓名 
	 */
	private String logistics_address_nickname;
	/*
	 *寄件人电话
	 */
	private String logistics_address_mobile;
	/*
	 *物流省份
	 */
	private String logistics_province_id;
	/*
	 *物流城市 
	 */
	private String logistics_city_id;
	/*
	 *物流区域 
	 */
	private String logistics_district_id;
	/*
	 *详细地址 
	 */
	private String logistics_address;
	/*
	 *实际使用uuid 
	 */
	private String logistics_address_uuid;
}
