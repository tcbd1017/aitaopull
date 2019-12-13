package cn.kgc.tangcco.tcbd1017.on.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderGoods {
	/**
     * 	商品订单id
     */
	private int order_goods_id; 
	/**
     * 	卖家id
     */
	private int seller_id; 
	/**
     * 	订单id
     */
	private int order_id;
	/**
     * 	商品订单id
     */
	private int goods_id;  
	/**
     * 	购买数量
     */
	private int amount_of_goods; 
	
}
