package cn.kgc.tangcco.tcbd1017.on.pojo;
/**
 * 
 * @author zhangmiao
 * @version	2019年12月10日	下午1:24:54
 *
 */

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Seller {
	
	/**
	 * 卖家id
	 */
	private int seller_id;
	
	/**
	 * 卖家唯一标识符uuid,卖家人脸认证token 值 , 卖家 身份证认证token值
	 */
	private String seller_uuid,seller_face_token,seller_idcard_token;
	
	/**
	 * 卖家创建时间,卖家更新时间
	 */
	private Date seller_create_time,seller_update_time;
	
	/**
	 * 卖家状态 1删除 2审核中 3审核通过  ,店铺id ,仓储id ,物流id
	 */
	private int seller_status,store_id,storage_id,logistics_id;
	
	/**
	 * 卖家头像的url
	 */
	private String seller_icon_url;
	
}
