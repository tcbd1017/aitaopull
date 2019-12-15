package cn.kgc.tangcco.tcbd1017.on.pojo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
	 * @author DU MING
	 * @version 1.0	2019年12月10日	上午11:03:35
	 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCart {
	
	/**
	 * 购物车id
	 */
	private int shopping_cart_id;
	/**
	 * 买家id
	 */
	private int buyer_id;
	/**
	 * 商品id
	 */
	private int goods_id;
	/**
	 * 添加商品的数量
	 */
	private int amount_of_goods;
	/**
	 * 购物车的创建时间
	 */
	private Date shopping_cart_create_time;
	/**
	 * 购物车的更新时间
	 */
	private Date shopping_cart_update_time;
	/**
	 * 购物车的状态 1 删除 2 正常
	 */
	private int shopping_cart_status;
}


