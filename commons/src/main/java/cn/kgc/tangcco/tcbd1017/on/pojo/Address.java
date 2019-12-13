package cn.kgc.tangcco.tcbd1017.on.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
*@author   席首华
*@version  1.0   </br>
   创建时间   2019年12月10日     上午10:35:10
  类描述：
*
*/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Address {

	/**
	 * 地区代码
	 */
	private int address_id;
	
	/**
	 * 当前地区的上一级地区代码
	 */
	private int address_parent_id;
	
	/**
	 *地区名称 
	 */
	private String address_name;
	
}
