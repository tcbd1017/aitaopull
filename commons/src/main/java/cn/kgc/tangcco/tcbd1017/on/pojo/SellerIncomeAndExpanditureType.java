package cn.kgc.tangcco.tcbd1017.on.pojo;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author zhangmiao
 * @version	2019年12月10日	下午12:05:00
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellerIncomeAndExpanditureType {
	
	/**
	 * 卖家收入支出类型主键,卖家收入支出类型名称 1店铺订单买东西收入 2退款支出
	 */
 private int seller_income_and_expanditure_type_id,seller_income_and_expanditure_type_name;
}
