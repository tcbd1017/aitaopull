package cn.kgc.tangcco.tcbd1017.lo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LogisticsChineseAddress {

	private Integer address_id;
	private Integer address_parent_id;
	private String address_name;
}
