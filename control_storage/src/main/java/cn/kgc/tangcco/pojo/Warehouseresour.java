package cn.kgc.tangcco.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
/**
 * 仓库类型信息  记录仓库剩余数量
 *
 */
public class Warehouseresour {
	 /**
	    * 仓库信息表id
	    */
	    private Integer warehouseresourId;

	    /**
	     * 仓库类型剩余数量
	    */
	    private Integer warehouseresourCount;

	    /**
	    * 仓库表id
	    */
	    private Warehouse warehouse;
//	    private Integer warehouseresourWarehouseId;
}
