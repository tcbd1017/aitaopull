package cn.kgc.tangcco.tcbd1017.on.pojo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
	 * @author DU MING
	 * @version 1.0	2019年12月10日	上午10:46:13
	 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
	
	/**
	 * 评论id 
	 */
	private int comment_id;
	/**
	 * 买家id
	 */
	private int buyer_id;
	/**
	 * 订单id
	 */
	private int order_id;
	/**
	 * 父级评论id
	 */
	private int comment_parent_id;
	/**
	 * 评论内容
	 */
	private String comment_content;
	/**
	 * 评论创建时间
	 */
	private Date comment_create_time;
	/**
	 * 评论更新时间
	 */
	private Date comment_update_time;
	/**
	 * 商品评分
	 * 1 极差 2 差 3 中等 4 好 5 极好
	 */
	private int comment_status;
	/**
	 * 当前评论状态 1 失效 2 正常
	 */
	private int comment_grade;
}


