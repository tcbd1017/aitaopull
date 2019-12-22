package cn.kgc.tangcco.pojo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
/**
 * 仓库跟店铺关联表  相当于对仓库进行  监测   入库有剩余容量    和   到期时间
 *
 */
public class WarehouseShop {
	/**
	    * 仓库跟店铺关联表id
	    */
	    private Integer warehouseShopId;

	    /**
	    * 仓库id
	    */
	    private Warehouse warehouse;
	    
	    
	    /**
		    * 仓库名称
		    */
		    private String name;
		    
	    /**
	    * 店铺id
	    */
//	    private Integer warehouseShopShopId;
	    private Shop shop;

	    /**
	    * 购买日期
	    */
	    private Date warehouseShopBuytime;

	    /**
	    * 购买时长
	    */
	    private Integer warehouseShopBuyyear;

	    /**
	    * 到期日期
	    */
	    private Date warehouseShopDaoqitime;

	    /**
	    * 仓库唯一标识符
	    */
	    private String warehouseShopWarehouseuuid;

	    /**
	    * 商检购买仓库所剩容量
	    */
	    private Integer warehouseShopWarehousesize;
}
