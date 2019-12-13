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
public class Store {
	/**
	 * 店铺id
	 */
	private int store_id;
	/**
	 * 商品id
	 */
	private int goods_id;
	/**
	 * 店铺创建时间
	 */
	private Date store_create_time;
	/**
	 * 店铺更新时间
	 */
	private Date store_update_time; 
	/**
	 * 店铺状态
	 */
	private int store_status;
	/**
	 * 店铺简介
	 */
	private String store_about;
	/**
	 * 店铺图片
	 */
	private String store_img;  

   
}