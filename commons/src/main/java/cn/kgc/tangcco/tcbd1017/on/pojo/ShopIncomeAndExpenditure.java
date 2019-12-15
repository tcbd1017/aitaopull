package cn.kgc.tangcco.tcbd1017.on.pojo;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author zhangmiao
 * @version	2019年12月10日	下午12:07:10
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopIncomeAndExpenditure {
	
	/**
	 * 平台收入支出类型id,收入类型，1代表套餐收入 2利息收入 3采购支出 4物流支出
	 */
	private int shop_income_and_expenditure_id,shop_income_and_expenditure_type;
}
