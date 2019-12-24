package cn.kgc.tangcco.tcbd1017.st.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* @author 作者 :牛伟
* @version 创建时间：2019年12月16日 下午1:37:29
* 类说明
*/

@AllArgsConstructor
@Data
@NoArgsConstructor
public class SelectCountByType {
	
	/**
	 * 类型名称
	 */
	private String type_name;
	
	/**
	 * 总记录数
	 */
	private int count;
	
}
