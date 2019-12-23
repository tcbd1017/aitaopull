package cn.kgc.tangcco.tcbd1017.lo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 王立宁
 * @version 1.0 <br>
 * 创建时间：2019年12月13日上午8:46:12
 * 类描述：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogisticStatus {
	/*
	 * 物流状态id
	 */
	private int logistics_status_id;
	/*
	 * 物流状态
	 */
	private String logistic_status_name;
}
