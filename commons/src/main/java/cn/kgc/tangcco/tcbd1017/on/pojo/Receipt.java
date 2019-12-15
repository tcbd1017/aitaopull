package cn.kgc.tangcco.tcbd1017.on.pojo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
* @author 作者 Your-Name: 刘煜
* @version 创建时间：2019年12月10日 上午11:20:37 
*    类说明 ：发票
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Receipt {
	/**
	   * 发票id
	 */
	private int receipt_id;
	/**
	   * 发票类型
	 */
	private int receipt_type;
	/**
	   *抬头类型 1个人 2企业 
	 */
	private int receipt_head_type;
	/**
	 * 抬头信息(个人：姓名，企业：企业全称)
	 */
	private String receipt_head_info;
	/**
	 * 企业纳税识别号
	 */
	private String receipt_company_uuid;
	/**
	 *企业公司地址
	 */
	private String receipt_company_address;
	/**
	 *企业电话号
	 */
	private String receipt_company_mobile;
	/**
	 *企业开户行名称
	 */
	private String receipt_company_opening_bank;
	/**
	 *企业开户行账号
	 */
	private String receipt_company_opening_bank_account;
	/**
	 *订单id
	 */
	private int order_id;
	/**
	 *发票创建时间
	 */
	private Date receipt_cereate_time;
	/**
	 *发票更新时间
	 */
	private Date receipt_update_time;
	/**
	 *发票状态  1删除2正常 
	 */
	private int receipt_status;
	
}
