package cn.kgc.tangcco.tcbd1017.lo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 王立宁
 * @version 1.0 <br>
 * 创建时间：2019年12月13日上午8:43:31
 * 类描述：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogisticsMoneyStatus {
	/*
	 * 账单状态id
	 */
	private int money_status_id;
	/*
	 * 账单状态
	 */
	private String money_status;
}
