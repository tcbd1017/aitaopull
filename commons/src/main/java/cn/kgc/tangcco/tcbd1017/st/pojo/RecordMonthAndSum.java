package cn.kgc.tangcco.tcbd1017.st.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* @author 作者 :牛伟
* @version 创建时间：2019年12月16日 下午2:42:16
* 类说明
*/


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordMonthAndSum {
	
	/**
	 *月份
	 */
	private String month;
	
	/**
	 * 每月入库总数量
	 */
	private int sum; 
}
