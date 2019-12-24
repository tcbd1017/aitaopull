package cn.kgc.tangcco.tcbd1017.st.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
/**
 * 仓库信息表
 *
 */
public class Warehouse {
		/**
		 * 仓库id
	    */
	    private Integer warehouseId;

	    /**
		    * 仓库类型名称
		    */
		 private String warehouseTypeName;
	    
	    /**
	    * 仓库类型大小
	    */
	    private Integer warehouseTypesize;

	    /**
	    * 仓库地址
	    */
	    private String warehouseAddress;

	    /**
	    * 仓库价格
	    */
	    private Double warehousePrice;

	    /**
	    * 仓库总个数
	    */
	    private Integer warehouseZonggeshu;
}
