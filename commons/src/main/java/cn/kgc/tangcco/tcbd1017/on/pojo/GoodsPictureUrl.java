package cn.kgc.tangcco.tcbd1017.on.pojo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author zhangmiao
 * @version 2019年12月10日 下午12:01:10
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsPictureUrl {

	/**
	 * 商品图片路径id,商品id
	 */
	private int goods_picture_url_id, goods_id;

	/**
	 * 商品图片路径
	 */
	private String goods_picture_url;
	/**
	 * 商品图片创建时间,商品图片更新时间
	 */
	private Date goods_picture_url_create_time, goods_picture_url_update_time;

	/**
	 * 商品图片状态 1删除 2正常
	 */
	private int goods_picture_url_status;
}
