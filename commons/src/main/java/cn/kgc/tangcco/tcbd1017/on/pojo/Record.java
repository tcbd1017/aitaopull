package cn.kgc.tangcco.tcbd1017.on.pojo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

	/**
	 * @author DU MING
	 * @version 1.0	2019年12月10日	上午10:39:33
	 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Record {
	
	/**
	 * 历史记录(足迹)id
	 */
	private int record_id;
	/**
	 * 买家id
	 */
	private int buyer_id;
	/**
	 * 商品id
	 */
	private int goods_id;
	/**
	 * 历史记录(足迹)创建时间
	 */
	private Date record_create_time;
	/**
	 * 历史记录(足迹)更新时间
	 */ 
	private Date record_update_time;
	/**
	 * 历史记录(足迹)启用状态 1失效 2正常
	 */
	private int record_status;

}


