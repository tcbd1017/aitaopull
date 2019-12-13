package cn.kgc.tangcco.tcbd1017.on.pojo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
*@author   席首华
*@version  1.0   </br>
   创建时间   2019年12月10日     上午10:39:58
  类描述：
*
*/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Path {
	
	/**
	 * 路径的id
	 */
	private int path_id;
	/**
	 * 路径的名称
	 */
	private String path_name;
	/**
	 * 路径的URL地址
	 */
	private String path_url;
	/**
	 * 路径的创建时间
	 */
	private Date path_creat_time;
	/**
	 * 路径的更新时间
	 */
	private Date path_update_time;
	/**
	 * 路径的状态 1删除 2正常
	 */
	private int path_status;
	
	
	
	
	
	
}
