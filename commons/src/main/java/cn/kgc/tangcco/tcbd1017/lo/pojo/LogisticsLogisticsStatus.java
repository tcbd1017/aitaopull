package cn.kgc.tangcco.tcbd1017.lo.pojo;
 /**
 * @author 王立宁
 * @version 1.0 <br>
 * 创建时间：2019年12月13日上午8:32:02
 * 类描述：
 */

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogisticsLogisticsStatus {
	/*
	 * 物流状态中间表id
	 */
	private int logistics_logistics_status_id;
	/*
	 * 物流状态id
	 */
	private int logistics_status;
	/*
	 * 物流uuid
	 */
	private String logistics_uuid;
	/*
	 * 物流创建时间
	 */
	private Date logistics_create;
	
	/**
	 * 物流经过公司
	 */
	private String logistics_company;
}
