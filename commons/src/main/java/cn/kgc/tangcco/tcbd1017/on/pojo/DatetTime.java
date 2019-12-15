package cn.kgc.tangcco.tcbd1017.on.pojo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
* @author 作者 Your-Name: 刘煜
* @version 创建时间：2019年12月10日 上午10:56:00 
*    类说明  ; 足迹时间表
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatetTime {
	/**
	 * 时间表id
	 */
	private int datetime_id;
	/**
	 * 时间
	 */
	private Date datetime;
	/**
	 *  时间状态 1失效 2正常
	 */
	private int datetime_status;
}
