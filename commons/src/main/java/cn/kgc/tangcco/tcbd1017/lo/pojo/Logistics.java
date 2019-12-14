package cn.kgc.tangcco.tcbd1017.lo.pojo;
 /**
 * @author 王立宁
 * @version 1.0 <br>
 * 创建时间：2019年12月10日下午7:54:35
 * 类描述：
 */
 
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Logistics {
	/*
	 * 物流id
	 */
	private int logistics_id;
	/*
	 * 物流单号（实际使用）
	 */
	private String logistics_uuid;
	/*
	 *订单创建时间
	 */
	private Date logistics_create_time;
	/*
	 * 商品名
	 */
	private String logistics_commodity_name;
	/*
	 * 寄件人姓名
	 */
	private String logistics_sender_name;
	/*
	 * 寄件人电话
	 */
	private String logistics_sender_mobile;
	/*
	 *寄件人地址
	 */
	private String logistics_sender_address;
	/*
	 * 收件人姓名
	 */
	private String logistics_receiver_name;
	/*
	 * 收件人电话
	 */
	private String logistics_receiver_mobile;
	/*
	 * 收件人地址
	 */
	private String logistics_receiver_address;
	/*
	 * 备注
	 */
	private String logistics_note;
	/*
	 * 类型
	 */
	private String logistics_type;
	/*
	 * 重量
	 */
	private String logistics_weight;
}
