package cn.kgc.tangcco.tcbd1017.on.pojo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 
* @author 作者 Your-Name: 刘煜
* @version 创建时间：2019年12月10日 上午11:13:01 
*    类说明 : 收藏店铺
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreFavorite {
	/**
	 * 店铺收藏id
	 */
	private int store_favorite_id;
	/**
	 * 买家id
	 */
	private int buyer_id;
	/**
	 * 店铺id
	 */
	private int store_id;
	/**
	 * 店铺收藏创建时间
	 */
	private Date store_favorite_create_time;
	/**
	 * 店铺收藏更新时间
	 */
	private Date store_favorite_update_time;
	/**
	 * 店铺收藏状态 1失效 2正常 
	 */
	private int store_favorite_status;

}
