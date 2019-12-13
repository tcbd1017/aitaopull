package cn.kgc.tangcco.tcbd1017.on.pojo;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 作者 Your-Name: 刘煜
 * @version 创建时间：2019年12月9日 上午9:14:55 
 * 类说明: 商品收藏表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsFavorite {
	/**
	 * 商品收藏id
	 */
	private int goods_favorite_id;
	/**
	 * 买家id
	 */
	private int buyer_id;
	/**
	 * 商品id
	 */
	private int goods_id;
	/**
	 * 商品收藏创建时间
	 */
	private Date goods_favorite_create_time;
	/**
	 * 商品收藏更新时间
	 */
	private Date goods_favorite_update_time;
	/**
	 * 商品收藏状态 1失效 2正常
	 */
	private int goods_favorite_status;
}
