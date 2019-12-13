package cn.kgc.tangcco.tcbd1017.on.pojo;



import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author 许佳瑞
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellerIncomeAndExpanditureInfo {
	/**
	 * 卖家收入支出明细的id
	 */
	private int seller_income_and_expanditure_info_id;
	/**
	 * 收入支出类型
	 */
	private int seller_income_and_expanditure_type_id; 
	/**
	 * 收入支出金额
	 */
	private double seller_income_and_expanditure_money;  
	/**
	 * 收入支出时间
	 */
	private Date seller_income_and_expanditure_time; 
	/**
	 * 订单id
	 */
	private int order_id;  

}