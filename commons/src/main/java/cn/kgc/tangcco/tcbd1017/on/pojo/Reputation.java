package cn.kgc.tangcco.tcbd1017.on.pojo;



import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author 许佳瑞
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reputation {
	/**
	 * 信誉度id
	 */
	private int reputation_id;
	/**
	 * 卖家id
	 */
	private int seller_id;
	/**
	 * 信誉度值
	 */
	private int reputation_value;
	/**
	 *评论id
	 */
	private int comment_id; 
	/**
	 * 信誉度创建时间
	 */
	private Date reputation_create_time;
	/**
	 * 信誉度更新时间
	 */
	private Date reputation_update_time; 
	/**
	 * 信誉度状态
	 */
	private int reputation_status;

}