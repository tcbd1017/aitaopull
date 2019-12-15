package cn.kgc.tangcco.tcbd1017.on.pojo;
/**
 * 
 * @author zhangmiao
 * @version	2019年12月10日	上午11:32:12
 *
 */

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreType {
	
	/**
	 * 店铺类型 id
	 */
	private int store_type_id;
	
	/**
	 * 店铺类型名称
	 */
	private String store_type_name;
	
	/**
	 * 店铺类型创建时间，店铺类型时间
	 */
	private Date store_type_create_time,store_type_update_time;
	
	/**
	 * 店铺状态  1删除 2正常
	 */
	private int store_type_status;
}
