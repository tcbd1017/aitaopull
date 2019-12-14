package cn.kgc.tangcco.tcbd1017.lo.pojo;
 
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 王立宁
 * @version 1.0 <br>
 * 创建时间：2019年12月13日上午8:48:18
 * 类描述：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogisticsUserAddress {
	/*
	 * 物流用户地址id
	 */
	private int id;
	/*
	 * 物流用户uuid
	 */
	private String user_uuid;
	/*
	 * 物流用户地址uuid
	 */
	private String logistics_address_uuid;
}
