package cn.kgc.tangcco.tcbd1017.on.pojo;

/**
 * 
 * @author 刘凯
 * @version 1.0 <br>
 *          创建时间:2019年12月10日 下午12:04:59 <br>
 *          类描述: 商品表
 */

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods {
	/**
	 * 商品id
	 */
	private int goods_id;
	/**
	 * 商品唯一标识符 uuld
	 */
	private String goods_uuid;
	/**
	 * 商品创建时间
	 */
	private Date goods_create_time;
	/**
	 * 商品更新时间
	 */
	private Date goods_update_time;
	/**
	 * 商品状态 1删除2正常
	 */
	private int goods_status;
	/**
	 * 商品图片地址
	 */
	private int goods_picture_url_id;
	/**
	 * 商品名称
	 */
	private String goods_name;
	/**
	 * 商品价格
	 */
	private double goods_price;
	/**
	 * 商品品牌
	 */
	private String goods_brand;
	/**
	 * 商品类型
	 */
	private String goods_type;
	/**
	 * 商品的宽
	 */
	private double goods_width;
	/**
	 * 商品的高
	 */
	private double goods_height;
	/**
	 * 商品的长
	 */
	private double goods_length;
	/**
	 * 商品简介
	 */
	private String goods_presentation;
	/**
	 * 店铺id
	 */
	private int seller_id;
	/**
	 * 仓库id
	 */
	private int storage_id;
	/**
	 * 商品重量
	 */
	private double goods_weight;

	public Goods(int goods_id, String goods_type, String goods_brand, String goods_name, double goods_price,
			String goods_presentation) {
		super();
		this.goods_id = goods_id;
		this.goods_type = goods_type;
		this.goods_brand = goods_brand;
		this.goods_name = goods_name;
		this.goods_price = goods_price;
		this.goods_presentation = goods_presentation;
	}

	@Override
	public String toString() {
		return "Goods [goods_id=" + goods_id + ", goods_uuid=" + goods_uuid + ", goods_create_time=" + goods_create_time
				+ ", goods_update_time=" + goods_update_time + ", goods_status=" + goods_status
				+ ", goods_picture_url_id=" + goods_picture_url_id + ", goods_name=" + goods_name + ", goods_price="
				+ goods_price + ", goods_brand=" + goods_brand + ", goods_type=" + goods_type + ", goods_width="
				+ goods_width + ", goods_height=" + goods_height + ", goods_length=" + goods_length
				+ ", goods_presentation=" + goods_presentation + ", seller_id=" + seller_id + ", storage_id="
				+ storage_id + ", goods_weight=" + goods_weight + "]";
	}
}
