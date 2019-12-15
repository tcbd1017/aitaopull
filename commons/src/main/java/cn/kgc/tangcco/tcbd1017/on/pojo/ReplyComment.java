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
public class ReplyComment {
	/**
	 * 卖家回复评论id
	 */
	private int reply_comment_id; 
	/**
	 * 卖家id
	 */
	private int seller_id;
	/**
	 * 订单id
	 */
	private int order_id;
	/**
	 * 回复评论内容
	 */
	private String reply_comment_content;
	/**
	 * 回复评论创建时间
	 */
	private Date reply_comment_create_time;
	/**
	 * 回复评论更新时间
	 */
	private Date reply_comment_updatet_time;  
	/**
	 * 回复评论状态
	 */
	private int reply_comment_status;  

    
}