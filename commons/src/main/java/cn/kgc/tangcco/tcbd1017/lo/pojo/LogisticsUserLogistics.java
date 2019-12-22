package cn.kgc.tangcco.tcbd1017.lo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 王立宁
 * @version 1.0 <br>
 * 创建时间：2019年12月13日上午8:51:03
 * 类描述：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogisticsUserLogistics {
	/*
	 * 用户和物流关系id
	 */
	int user_logistics_id;
	/*
	 * 用户uuid
	 */
	String user_uuid;
	/*
	 * 物流uuid
	 */
	String logistics_uuid;
}
