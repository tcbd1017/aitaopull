package cn.kgc.tangcco.tcbd1017.on.pojo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
*@author   席首华
*@version  1.0   </br>
   创建时间   2019年12月10日     上午10:44:44
  类描述：
*
*/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Logistics {

	/**
	 * 物流id
	 */
	private int logistics_id;
	/**
	 * 物流编号
	 */
	private String logistics_uuid;
	/**
	 * 物流创建时间
	 */
	private Date logistics_create_time;
	/**
	 * 物流更新时间
	 */
	private Date logistics_update_time;
	/**
	 * 物流状态
	 */
	private int logistics_status;
	
}
