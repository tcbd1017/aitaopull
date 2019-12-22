package cn.kgc.tangcco.tcbd1017.lo.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 王立宁
 * @version 1.0 <br>
 * 创建时间：2019年12月13日上午8:38:13
 * 类描述：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogisticsMoneyLogistics {
	/*
	 * 账单和物流对应表id
	 */
	private int money_logistics_id;
	/*
	 * 账单uuid
	 */
	private String money_uuid;
	/*
	 *物流id
	 */
	private String logistics_uuid;
}
